package config;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class Configuracao {

    @Before
    public void setup(){
        enableLoggingOfRequestAndResponseIfValidationFails();

        baseURI = "http://localhost:1234";
        basePath = "/api";

        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", getToken())
                .setContentType(ContentType.JSON)
                .build();
    }

    private String getToken(){
        return "token qualquer";
    }

    @After("@deletaUsuarios")
    public void deletaTodosOsUsuarios(){
        System.out.println("deleta os usuarios");
    }
}
