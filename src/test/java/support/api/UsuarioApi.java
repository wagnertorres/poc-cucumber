package support.api;

import org.apache.http.HttpStatus;
import support.domain.Usuario;

import static io.restassured.RestAssured.given;

public class UsuarioApi {

    private static final String CREATE_USER_ENDPOINT = "/v3/user";
    private static final String USER_ENDPOINT = "/v3/user/{username}";

    public void criarUsuario(Usuario usuario){

        given()
            .body(usuario)
        .when()
            .post(CREATE_USER_ENDPOINT)
        .then()
            .statusCode(HttpStatus.SC_OK);
    }

    public String obterNomeUsuario(Usuario usuario) {
        return given()
            .pathParams("username", usuario.getUsername())
        .when()
            .get(USER_ENDPOINT)
        .thenReturn()
                .path("username");
    }


}
