package pl.jsystems.qa.qaapi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.jsystems.qa.qaapi.model.MyUser;
import pl.jsystems.qa.qaapi.model.User;
import pl.jsystems.qa.qaapi.model.error.ErrorResponse;
import pl.jsystems.qa.qaapi.service.UserService;
import pl.jsystems.qa.qaapi.specification.Specifications;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@DisplayName("Api tests")

public class ApiTest {

    @Test
    @DisplayName("First rest assured test")

    public void firstTest() {
        RestAssured.given()
                .spec(Specifications.requestSpecBuilder())
                .when()
//                .get("http://www.mocky.io/v2/5a6b69ec3100009d211b8aeb")
                .get("/5a6b69ec3100009d211b8aeb")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("Piotr"))
                .body("surname", equalTo("Kowalski"));
    }

    @Test
    @DisplayName("Nested rest assured test")

    public void nestedTest() {
        RestAssured.given()
                .spec(Specifications.requestSpecBuilder())
                .when()
//                .get("http://www.mocky.io/v2/5a6a58222e0000d0377a7789")
                .get("/5a6a58222e0000d0377a7789")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].imie", equalTo("Piotr"))
                .body("[0].nazwisko", equalTo("Kowalski"))
                .body("[0].device[0].type", equalTo("computer"));
//                .body("[0].device[0].device.model.produce", equalTo("dell"));
    }

    @Test
    @DisplayName("Simple rest assured test")

    public void simpleTest() {

//        JsonPath jsonPath = RestAssured.given()
//                .spec(Specifications.requestSpecBuilder())
//                .when()
//                .get("/5a6a58222e0000d0377a7789")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract()
//                .body()
//                .jsonPath();

//        List<User> users = jsonPath.getList("", User.class);

        List<User> users = UserService.getUserList();
        assertThat(users.get(0).imie, equalTo("Piotr"));
        assertThat(users.get(0).nazwisko, equalTo("Kowalski"));
        assertThat(users.get(0).device.get(0).type, equalTo("computer"));
        assertThat(users.get(0).device.get(0).deviceModel.get(0).produce, equalTo("dell"));
        assertThat(users.get(0).device.get(0).deviceModel.get(0).screenSize, is(17.0));

    }
    @Test
    @DisplayName("JsonPathTest rest assured test")
    public void JsonPathTest() {

//        JsonPath jsonPath = RestAssured.given()
//                .spec(Specifications.requestSpecBuilder())
//                .when()
//                .get("/5a6b69ec3100009d211b8aeb")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract()
//                .body()
//                .jsonPath();
//        MyUser user = jsonPath()
//                .getObject("", MyUser.class);

        MyUser user = UserService.getMyUser();

        assertThat(user.name, equalTo("Piotr"));
        assertThat(user.surname, equalTo("Kowalski"));


    }
    @Test
    @DisplayName("RawResponseTest rest assured test")
    public void rawReponseTest() {

//        JsonPath jsonPath = RestAssured.given()
//                .spec(Specifications.requestSpecBuilder())
//                .when()
//                .get("/5a6b69ec3100009d211b8aeb")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract()
//                .body()
//                .jsonPath();
//        MyUser user = jsonPath()
//                .getObject("", MyUser.class);

        MyUser user = UserService.getMyUserResponse();

        assertThat(user.name, equalTo("Piotr"));
        assertThat(user.surname, equalTo("Kowalski"));


    }

    @Test
    @DisplayName("UsersResponseTest rest assured test")

    public void UsersResponseTest() {

        List<User> users = UserService.getUserResponseList();

        assertThat(users.get(0).imie, equalTo("Piotr"));
        assertThat(users.get(0).nazwisko, equalTo("Kowalski"));
        assertThat(users.get(0).device.get(0).type, equalTo("computer"));
        assertThat(users.get(0).device.get(0).deviceModel.get(0).produce, equalTo("dell"));
        assertThat(users.get(0).device.get(0).deviceModel.get(0).screenSize, is(17.0));

    }

    @Test
    @DisplayName("getErrorResponseTest rest assured test")

    public void getErrorResponseTest() {

        ErrorResponse errorResponse = UserService.getUserErrorReponse();

        assertThat(errorResponse.Error.errorCode, is(400));
        assertThat(errorResponse.Error.validationError, equalTo("invalid_email"));
        assertThat(errorResponse.Error.message, equalTo("your email is invalid"));
    }


}
