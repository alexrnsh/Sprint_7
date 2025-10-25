import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.CourierModel;
import org.hamcrest.CoreMatchers;
import org.junit.After;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BaseCourierTest extends BaseTest {

    protected Integer courierId;

    private final static String COURIER_BASE_URL_V1 = "/api/v1/courier";
    private final static String LOGIN_PATH = "/login";

    @After
    public void tearDown(){
        if (courierId != null) {
            given()
                    .header("Content-type", "application/json")
                    .when()
                    .delete(COURIER_BASE_URL_V1  + "/" + courierId)
                    .then()
                    .statusCode(200);
        }
    }

    @Step("Создание курьера через POST " + COURIER_BASE_URL_V1)
    public void createCourierSuccess(CourierModel courier){
        given()
                .log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .post(COURIER_BASE_URL_V1)
                .then()
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Step("Создание курьера через POST " +  COURIER_BASE_URL_V1)
    public void createCourierError400(CourierModel courier){
        given()
                .log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .post(COURIER_BASE_URL_V1)
                .then()
                .log().all()
                .statusCode(400)
                .and()
                .body("message", CoreMatchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step ("Логин через " + COURIER_BASE_URL_V1 + LOGIN_PATH)
    public void courierLoginSuccess(CourierModel loginRequest){
        ValidatableResponse loginResponse = given()
                .log().all()
                .header("Content-type", "application/json")
                .body(loginRequest)
                .post(COURIER_BASE_URL_V1 + LOGIN_PATH)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", notNullValue());
        courierId = loginResponse.extract().path("id");
    }

    @Step ("Логин курьера через POST " + COURIER_BASE_URL_V1 + LOGIN_PATH)
    public void courierLoginError400( CourierModel loginRequest){
        given()
                .log().all()
                .header("Content-type", "application/json")
                .body(loginRequest)
                .post(COURIER_BASE_URL_V1 + LOGIN_PATH)
                .then()
                .log().all()
                .statusCode(400)
                .and()
                .body("message", CoreMatchers.equalTo("Недостаточно данных для входа"));
    }

    @Step ("Логин курьера через POST " + COURIER_BASE_URL_V1 + LOGIN_PATH)
    public void courierLoginError404( CourierModel loginRequest){
        given()
                .log().all()
                .header("Content-type", "application/json")
                .body(loginRequest)
                .when()
                .post(COURIER_BASE_URL_V1 + LOGIN_PATH)
                .then()
                .log().all()
                .statusCode(404)
                .and()
                .body("message", CoreMatchers.equalTo("Учетная запись не найдена"));
    }

}

