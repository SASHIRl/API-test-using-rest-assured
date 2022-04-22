package Teste.de.software.teste;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class BaseTeste {
    //roda apenas uma vez
    @BeforeClass
    public static void setup() {
        //Usar o .log().all() faz o programa ser mais verboso no erro.
        //Esse comando subistituiu todos meus .log().all()
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://reqres.in";
        basePath = "/api";
    }
}