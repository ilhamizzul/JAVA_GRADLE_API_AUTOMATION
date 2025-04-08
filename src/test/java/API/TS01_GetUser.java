package API;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiClient;
import utils.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class TS01_GetUser extends BaseTest {
    private static final String ENDPOINT = "/public/v2/users";

    @Test
    public void TS0101_GetAllUsers() {
        Map<String, String> headers = new HashMap<>();

        Response response = ApiClient.get(ENDPOINT, headers);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("id"));
        Assert.assertNotNull(response.jsonPath().get("name"));
        Assert.assertNotNull(response.jsonPath().get("email"));
        Assert.assertNotNull(response.jsonPath().get("gender"));

        JsonArray jsonArray = JsonParser.parseString(response.body().asString()).getAsJsonArray();
        System.out.println("Output : " + jsonArray);
    }
}