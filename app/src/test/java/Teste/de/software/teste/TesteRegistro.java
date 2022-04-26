package Teste.de.software.teste;

import Teste.de.software.dominio.Usuario;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class TesteRegistro extends TesteBase {
    private static final String REGISTRA_USUARIO_ENDPOINT = "/register";

    @Test
    public void testeNaoEfetuaRegistroQuandoSenhaEstaEmBranco() {
        Usuario usuario = new Usuario();
        usuario.setEmail("diego@reqress.in");
        given().
                body(usuario).
        when().
                post(REGISTRA_USUARIO_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("error", is("Missing password"));
    }
}