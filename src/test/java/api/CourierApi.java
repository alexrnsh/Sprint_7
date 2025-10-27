package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.CourierModel;

import static io.restassured.RestAssured.given;

public class CourierApi {

    private final static String COURIER_BASE_URL_V1 = "/api/v1/courier";
    private final static String LOGIN_PATH = "/login";

    @Step("Создание курьера через POST " + COURIER_BASE_URL_V1)
    public ValidatableResponse createCourier(CourierModel courier){
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .post(COURIER_BASE_URL_V1)
                .then()
                .log().all();
    }

    @Step ("Логин через " + COURIER_BASE_URL_V1 + LOGIN_PATH)
    public ValidatableResponse courierLogin(CourierModel loginRequest) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(loginRequest)
                .post(COURIER_BASE_URL_V1 + LOGIN_PATH)
                .then()
                .log().all();
    }

    @Step ("Удаление созданного в тесте курьера")
    public ValidatableResponse courierDelete (Integer courierId) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_BASE_URL_V1 + "/" + courierId)
                .then()
                .log()
                .all();
    }
}
