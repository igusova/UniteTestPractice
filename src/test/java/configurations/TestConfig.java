package configurations;

public class TestConfig {
    private static final String BASE_URL = "https://petstore.swagger.io/v2/";
    public String getBaseUrl() {
        return System.getProperty("baseUrl", BASE_URL);
    }
}
