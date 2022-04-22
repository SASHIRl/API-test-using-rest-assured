/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Teste.de.software.teste;

import Teste.de.software.dominio.Usuario;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UsuarioTeste extends BaseTeste{

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
        //A API que eu estou trabalhando tem um problema.
        //OS campos de registro da API são apenas nome e job, ele deveria rejeitar o e-mail.
        //O correto seria a API rejeitar, vou trabalhar na classe de dominio como se houvesse a rejeição.
        //Há como usar o 'JsonIgonoreProperties' e ignorar uma propriedade específica ou todas desconhecidas.
        Usuario usuario = new Usuario("Diego", "Jogador", "email@reqres.com");

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

    @Test
    public void testListaUsuario() {
        given().
                pathParam("user", "2").
        when().
                get("/users/{user}").
        then().
                statusCode(HttpStatus.SC_OK).
                body("data.email", containsString("@reqres.in"));
    }
}