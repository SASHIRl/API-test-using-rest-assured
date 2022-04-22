/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Teste.de.software.teste;

import Teste.de.software.dominio.Usuario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class UsuarioTeste {

    //roda apenas uma vez
    @BeforeClass
    public static void setup() {
        //Usar o .log().all() faz o programa ser mais verboso no erro.
        //Esse comando subistituiu todos meus .log().all()
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://reqres.in";
        basePath = "/api";
    }

    @Test
    public void testeListaMetaDadosDoUsuario() {
        given().
                param("page", "2").
        //Escrita do restAssured para testar a api do site https://reqres.in
        when().
                get("/users").
        then().
                //is() é do hamcrest, eu não coloquei a dependência dele no build.gradle
                //Portanto, ele puxou do JUNIT ou do restAssured
                statusCode(HttpStatus.SC_OK).
                body("page", is(2)).
                body("data", is(notNullValue()));
    }

    @Test
    public void testeCriaUsuarioComSucesso() {

        Usuario usuario = new Usuario("Diego", "Jogador");

        given().//.log().all().
                //Tenho que informar que estou passando um JSON
                contentType(ContentType.JSON).
                //Copiado do Postman
                body(usuario).
        when().
            post("/users").
        then().
            statusCode(HttpStatus.SC_CREATED).
            body("name", is("Diego"));
    }
}