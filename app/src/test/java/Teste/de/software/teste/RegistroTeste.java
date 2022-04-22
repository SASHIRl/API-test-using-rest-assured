package Teste.de.software.teste;

import Teste.de.software.dominio.Usuario;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class RegistroTeste extends BaseTeste{

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