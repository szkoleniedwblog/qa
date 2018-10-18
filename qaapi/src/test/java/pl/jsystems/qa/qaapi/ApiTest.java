package pl.jsystems.qa.qaapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbservice.UserDao;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdbiservice.UserJdbiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.jsystems.qa.qaapi.model.*;
import pl.jsystems.qa.qaapi.model.error.ErrorResponse;
import pl.jsystems.qa.qaapi.service.UserService;
import pl.jsystems.qa.qaapi.specification.Specifications;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


    @Test
    @DisplayName("postUser rest assured test")
    public void postUser() {

        String[] emptyTable = UserService.postMyUser(new MyUser("Adam", "Majewski"));

        assertTrue(Arrays.asList(emptyTable).isEmpty());

    }


    @Test
    @DisplayName("genericIntTest rest assured test")
    public void genericIntTest1() throws IOException {

        Response response = UserService.getGeneric("/5b05bf3f3200007100ebfa04");

        UserGeneric<Integer> userGeneric = new ObjectMapper().readValue(
                response
                .then()
                .extract()
                .body()
                .asInputStream(), new TypeReference<UserGeneric<Integer>>() {}
        );

        assertThat(userGeneric.id, is(1));

    }


    @Test
    @DisplayName("genericIntTest2 rest assured test")
    public void genericIntTest2() throws IOException {

        Response response = UserService.getGeneric("/5b05c83e3200009700ebfa2b");

        UserGeneric<String> userGeneric = new ObjectMapper().readValue(
                response
                        .then()
                        .extract()
                        .body()
                        .asInputStream(), new TypeReference<UserGeneric<String>>() {}
        );

        assertThat(userGeneric.id, equalTo("1a"));

    }

    @Test
    @DisplayName("AzureUserTest rest assured test")

    public void azureUser() {

        UserAzure userAzure = UserService.getUserAzureById(1);

        assertThat(userAzure.id, is(1));
        assertThat(userAzure.userName, equalTo("User 1"));
        assertThat(userAzure.password, equalTo("Password1"));
    }


    @Test
    @DisplayName("dbTest1 rest assured test")

    public void dbTest1() {

        UserDBTest userDBTest = UserDao.getOneById(1);
        System.out.println(userDBTest);

        List<UserDBTest> users = UserDao.getAll();
        System.out.println(users);

//        UserDao.saveOne(new UserDBTest(8, "Marian", "Kwaśny"));
//        System.out.println(UserDao.getOneById(8));

        UserDao.update(new UserDBTest(4, "Zofia", "Czerwona"), 4);
        System.out.println(UserDao.getOneById(4));
    }

    @Test
    @DisplayName("Awaitility test")

    public void awaitility() {
        await().untilAsserted(() -> {

            assertThat(UserService.getUserAzureById(1).id, is(1));
            assertThat(UserDao.getOneById(1).getId(), is(1));
                }
        );
    }

    @Test
    @DisplayName("JDBI test")

    public void jdbiTest() {

    assertThat(UserJdbiService.getTestUser(1L).getId(), is(1L));

    }
}
