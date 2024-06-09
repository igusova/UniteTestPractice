import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.matchesPattern;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersApiTests {

    private String USERNAME = "test_user_1";

    @AfterEach
    @BeforeEach
    void deleteCreatedUsers() {
        String endpoint = "https://petstore.swagger.io/v2/user/" + USERNAME;
        given().
                header("accept", "application/json").
                header("Content-Type", "application/json").
                when().
                delete(endpoint).
                then().
                log().
                all().
                assertThat().
                statusCode(200);
    }
    @Test
    @Order(1)
    void createUser() {
        String createUserEndpoint = "https://petstore.swagger.io/v2/user";
        User user = new User(0, USERNAME, "firstName1", "lastName1", "email1@gmail.com", "qwe123", "123123123", 0);
        ValidatableResponse createUserResponse =
                given().
                        header("accept", "application/json").
                        header("Content-Type", "application/json").
                        body(user).
                        when().
                        post(createUserEndpoint).
                        then().
                        log().
                        all();
        createUserResponse.statusCode(200);

        String getUserEndpoint = "https://petstore.swagger.io/v2/user/" + USERNAME;
        given().
                header("Cache-Control", "no-cache").
                when().
                get(getUserEndpoint).
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                body("username", equalTo(USERNAME)).
                body("firstName", startsWith("firstName1")).
                body("lastName", equalToIgnoringCase("LASTNAME1")).
                body("email", matchesPattern("^[a-zA-Z0-9._%+-]+@gmail\\.com$")).
                body("password", equalTo("qwe123")).
                body("phone", equalTo("123123123"));
    }

    @Test
    @Order(2)
    void updateUser() {
        String createUserEndpoint = "https://petstore.swagger.io/v2/user";
        String updateUserEndpoint = "https://petstore.swagger.io/v2/user/" + USERNAME;
        String getUserEndpoint = "https://petstore.swagger.io/v2/user/" + USERNAME;
        User user = new User(0, USERNAME, "firstName1", "lastName1", "email1@gmail.com", "qwe123", "123123123", 0);
        ValidatableResponse createUserResponse =
                given().
                        header("accept", "application/json").
                        header("Content-Type", "application/json").
                        body(user).
                        when().
                        post(createUserEndpoint).
                        then().
                        log().
                        all();
        createUserResponse.statusCode(200);

        User upadatedUser = new User(0, USERNAME, "firstName2", "lastName2", "email1@gmail.com", "qwe123", "123123123", 0);
        ValidatableResponse updateUserResponse =
                given().
                        header("accept", "application/json").
                        header("Content-Type", "application/json").
                        body(upadatedUser).
                        when().
                        put(updateUserEndpoint).
                        then().
                        log().
                        all();
        updateUserResponse.statusCode(200);

        ValidatableResponse getUserResponse =
        given().
                when().
                get(getUserEndpoint).
                then().
                log().
                all();
        getUserResponse.statusCode(200);
        getUserResponse.
                assertThat().
                body("username", equalTo(USERNAME)).
                body("firstName", startsWith("firstName2")).
                body("lastName", equalToIgnoringCase("LASTNAME2")).
                body("email", matchesPattern("^[a-zA-Z0-9._%+-]+@gmail\\.com$")).
                body("password", equalTo("qwe123")).
                body("phone", equalTo("123123123"));
    }
}
