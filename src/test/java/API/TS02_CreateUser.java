package API;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiClient;
import utils.BaseTest;
import utils.ConfigLoader;
import utils.Generator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TS02_CreateUser extends BaseTest {
    private static final String ENDPOINT = "/public/v2/users";
    private static final String CONTENT_TYPE = "application/json";
    private static final String BEARER_TOKEN;
    private static final Generator generator = new Generator();

    static {
        String token = "";
        try {
            token = ConfigLoader.getBearerToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BEARER_TOKEN = token;
    }

    private Map<String, String> buildHeaders(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        headers.put("Content-Type", CONTENT_TYPE);
        return headers;
    }

    @Test
    public void TS0201_CreateUser() {

        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("name", generator.generateRandomName());
        bodyRequest.put("gender", generator.generateGender());
        bodyRequest.put("email", generator.generateRandomEmail());
        bodyRequest.put("status", "active");

        Response response = ApiClient.post(ENDPOINT, buildHeaders(BEARER_TOKEN), bodyRequest.toString());
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertNotNull(response.jsonPath().get("id"));
    }

    @Test
    public void TS0202_CreateUser_UsingInvalidAuth() {
        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("name", generator.generateRandomName());
        bodyRequest.put("gender", generator.generateGender());
        bodyRequest.put("email", generator.generateRandomEmail());
        bodyRequest.put("status", "active");

        Response response = ApiClient.post(ENDPOINT, buildHeaders("INVALID_TOKEN"), bodyRequest.toString());

        JsonObject resObject = JsonParser.parseString(response.body().asString()).getAsJsonObject();
        Assert.assertEquals(resObject.get("message").getAsString(), "Invalid token");
        Assert.assertEquals(response.statusCode(), 401);
    }

    @Test
    public void TS0203_CreateUser_WithInvalidGender() {

        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("name", generator.generateRandomName());
        bodyRequest.put("gender", "Non Binary");
        bodyRequest.put("email", generator.generateRandomEmail());
        bodyRequest.put("status", "active");

        Response response = ApiClient.post(ENDPOINT, buildHeaders(BEARER_TOKEN), bodyRequest.toString());

        JsonArray jsonArray = JsonParser.parseString(response.body().asString()).getAsJsonArray();
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

        Assert.assertEquals(jsonObject.get("message").getAsString(), "can't be blank, can be male of female");
        Assert.assertEquals(response.statusCode(), 422);
    }
}
