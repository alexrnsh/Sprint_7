import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.OrderModel;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static data.Constants.*;
import static org.apache.http.HttpStatus.*;


@RunWith(Parameterized.class)
public class TestOrderCreationParameterized extends BaseTest {

    private Integer trackIdForTest;

    private final String[] color;

    public TestOrderCreationParameterized (String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет: {0}")
    public static Object[][] testData() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}},
        };
    }

    @Test
    @DisplayName("Проверка создания заказа с разными верными значениями необязательного параметра Color")
    @Description("Параметризованный тест с вводом верных значений BLACK и/или GREY или пустого массива, ожидается успешное оформление заказа")
    public void testCreateOrderReturnsTrack() {

        OrderModel order = new OrderModel(FIRSTNAME_ORDER,LASTNAME_ORDER,ADDRESS_ORDER,METRO_STATION_ORDER,
              PHONE_ORDER,RENT_TIME_ORDER,DELIVERY_DATE_ORDER,COMMENT_ORDER,color);

        ValidatableResponse response = orderApi.createOrder(order)
                .statusCode(SC_CREATED)
                .body("track", CoreMatchers.notNullValue());

        trackIdForTest = response.extract().path("id");

    }

    @After
    public void testOrderCancellation(){
        if (trackIdForTest != null) {
            orderApi.cancelOrder(trackIdForTest).statusCode(SC_OK);
        }
    }

}
