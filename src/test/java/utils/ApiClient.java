package utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.Map;

public class ApiClient {

    public static Response get(String endpoint, Map<String, String> headers) {
        return given()
                .headers(headers)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response post(String endpoint, Map<String, String> headers, String body) {
        return given()
                .headers(headers)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }
}
