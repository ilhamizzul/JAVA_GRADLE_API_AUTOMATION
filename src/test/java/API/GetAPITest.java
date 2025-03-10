package API;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiClient;
import utils.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class GetAPITest extends BaseTest {

    @Test
    public void TS0101_GetAllUsers() {
        String endpoint = "/public/v2/users";
        Map<String, String> headers = new HashMap<>();

        Response response = ApiClient.get(endpoint, headers);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("id"));
    }

    @Test
    public void TS0201_CreateUser() {
        String endpoint = "/public/v2/users";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer ac58d158856b6345aeda231b9ac03acc48060839ebff47189b877a19969ede6f");
        headers.put("Content-Type", "application/json");

        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("name", "John");
        bodyRequest.put("gender", "male");
        bodyRequest.put("email", "jognnewmail123@mail.com");
        bodyRequest.put("status", "active");

        Response response = ApiClient.post(endpoint, headers, bodyRequest.toString());
        System.out.println("Response body: " + response.body().asString());
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertNotNull(response.jsonPath().get("id"));
    }
}