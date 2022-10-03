package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import static org.hamcrest.CoreMatchers.*;
import support.api.AnimalApi;
import support.domain.Animal;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class AnimalSteps {

    private AnimalApi animalApi;
    private List<Animal> animaisAtuais;
    private Response animaisAtuaisResponse;

    public AnimalSteps(){
        animalApi = new AnimalApi();
    }

    @Dado("que possuo animais {word}")
    public void quePossuoAnimaisAvailable(String status) throws JsonProcessingException {
    }

    @Quando("pesquiso por todos os animais {word}")
    public void pesquisoPorTodosOsAnimaisAvailable(String status) {
        animaisAtuais = animalApi.obterListaDePets(status);
    }

    @Então("recebo a lista de animais available")
    public void receboAListaDeAnimaisAvailable() {
        assertThat(animaisAtuais).isNotEmpty();
    }

    @E("recebo outra lista de animais {word}")
    public void receboOutraListaDeAnimaisPorStatus(String status) {
        Response animalDisponivel = animalApi.obterAnimalPorStatus(status);

        animaisAtuais = animalDisponivel.body().jsonPath().getList("", Animal.class);

        animalDisponivel
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body(
                            "size()", is(animaisAtuais.size()),
                            "findAll { it.status == '" + status + "' }.size()", is(animaisAtuais.size())
                    );
    }

    @Dado("que não possua animais {word}")
    public void queNãoPossuaAnimaisSold(String status) {
        animalApi.excluirAnimaisPorStatus(status);
    }

    @Então("recebo a lista com {} animal/animais")
    public void receboAListaComAnimais(int quantidadeAnimais) {
        assertThat(animaisAtuais.size()).isEqualTo(quantidadeAnimais);
    }

    @Quando("eu pesquiso por todos os animais {word}")
    public void euPesquisoPorTodosOsAnimaisAvailable(String status) {
        animaisAtuaisResponse = animalApi.obterAnimalPorStatus(status);
    }

    @Então("recebo a lista com {int} animais {word}")
    public void receboAListaComAnimaisAvailable(int quantidadeAnimal, String status) {
        animaisAtuaisResponse
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "size()", is(quantidadeAnimal),
                        "findAll { it.status == '" + status + "'}.size()", is(quantidadeAnimal)
                );
    }

    @E("{int} animais possuem o nome {word}")
    public void animaisPossuemONomeLion(int quantidadeAnimal, String nomeAnimal) {
        animaisAtuaisResponse
            .then()
                .body(
                        "findAll { it.name.contains('" + nomeAnimal + "') }.size()", is(quantidadeAnimal)
                        // "findAll { it.name.startsWith('" + nomeAnimal + "') }.size()", is(quantidadeAnimal)
                );
    }
}