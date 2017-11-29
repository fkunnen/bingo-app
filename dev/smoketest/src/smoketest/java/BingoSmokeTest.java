import io.restassured.RestAssured;
import org.junit.Test;

public class BingoSmokeTest {

    @Test
    public void givenAStartedApplication_whenAskingForTheStatusPage_thenReturnHttp200() throws InterruptedException {
        Thread.sleep(30000);
        RestAssured.when().get("http://localhost:8888/bingo/health").then()
                .statusCode(200);
    }
}