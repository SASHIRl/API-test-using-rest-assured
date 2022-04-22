package Teste.de.software.teste;

import Teste.de.software.dominio.Usuario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

public class RegistroTeste {

    @BeforeClass
    public static void setup() {
        //Usar o .log().all() faz o programa ser mais verboso no erro.
        //Esse comando subistituiu todos meus .log().all()
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://reqres.in";
        basePath = "/api";
    }

    @Test
    public void testNaoEfetuaRegistroQuandoSenhaEstaEmBranco() {
        Usuario usuario = new Usuario();
        usuario.setEmail("diego@reqress.in");
        given().
                contentType(ContentType.JSON).
                body(usuario).
        when().
                post("/register").
        then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("error", is("Missing password"));
    }
}