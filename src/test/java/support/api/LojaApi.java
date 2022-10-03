package support.api;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import support.domain.Pedido;

import static io.restassured.RestAssured.given;

public class LojaApi {

    private static final String CREATE_ORDER_ENDPOINT = "v3/store/order/";
    private static final String GET_ORDER_ENDPOINT = "v3/store/order/{id}";

    public Pedido criarPedido(Pedido pedido){
        return given()
                .body(pedido)
            .when()
                .post(CREATE_ORDER_ENDPOINT)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                    .body()
                    .as(Pedido.class);
    }

    public Response obterPedido(int id) {
        return given()
                .pathParam("id", id)
            .when()
                .get(GET_ORDER_ENDPOINT);
    }
}
