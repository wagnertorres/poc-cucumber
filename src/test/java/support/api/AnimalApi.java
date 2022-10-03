package support.api;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import support.domain.Animal;
import java.util.List;

import static io.restassured.RestAssured.*;

public class AnimalApi {

    private static final String FIND_PETS_BY_STATUS_ENDPOINT = "/v3/pet/findByStatus?status={status}";
    private static final String PET_BY_ID_ENDPOINT = "/v3/pet/{id}";
    private static final String CREATE_PET_ENDPOINT = "/v3/pet";

    public List<Animal> obterListaDePets(String status){
        return given()
            .pathParams("status", status)
        .when()
            .get(FIND_PETS_BY_STATUS_ENDPOINT)
        .then()
            .extract()
                .body()
                .jsonPath()
                .getList("", Animal.class);
    }

    public Response obterAnimalPorStatus(String status) {
        return
                given()
                    .pathParams("status", status)
                .when()
                    .get(FIND_PETS_BY_STATUS_ENDPOINT);
    }

    public void excluirAnimaisPorStatus(String status){
        List<Integer> animaisId = given()
                .pathParam("status", status)
            .when()
                .get(FIND_PETS_BY_STATUS_ENDPOINT)
            .thenReturn()
                .path("id");

        if (!animaisId.isEmpty()) {
            for (Integer id : animaisId) {
                given().pathParam("id", id).delete(PET_BY_ID_ENDPOINT);
            }
        }
    }

    public Animal criarAnimal(Animal animal) {
        return given()
                .body(animal)
            .when()
                .post(CREATE_PET_ENDPOINT)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                    .body()
                        .as(Animal.class);
    }
}
