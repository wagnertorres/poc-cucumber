package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import support.api.AnimalApi;
import support.api.LojaApi;
import support.domain.Animal;
import support.domain.Pedido;
import support.domain.builders.PedidoBuilder;

import static org.hamcrest.CoreMatchers.is;

public class LojaStep {

    AnimalApi animalApi;
    LojaApi lojaApi;
    Animal animalEsperado;
    Pedido pedidoEsperado;

    public LojaStep(){
        animalApi = new AnimalApi();
        lojaApi = new LojaApi();
    }

    @Dado("que possua animal {word}")
    public void quePossuaAnimalAvailable(String status) {
        Animal animal = Animal.builder()
                .id(333)
                .status(status)
                .build();

        animalEsperado = animalApi.criarAnimal(animal);
    }

    @Quando("faço o pedido desse animal")
    public void façoOPedidoDesseAnimal() {
        Pedido pedido = new PedidoBuilder()
                .comId(100)
                .comPetId(animalEsperado.getId())
                .build();

        pedidoEsperado = lojaApi.criarPedido(pedido);
    }

    @Então("o pedido é aprovado")
    public void oPedidoÉAprovado() {
        Response pedidoAtual = lojaApi.obterPedido(pedidoEsperado.getId());

        pedidoAtual
                .then()
                .body(
                        "id", is(pedidoEsperado.getId()),
                        "petId", is(animalEsperado.getId()),
                        "quantity", is(pedidoEsperado.getQuantity()),
                        "status", is("approved")
                );
    }
}