import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.OrderModel;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static data.Constants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.apache.http.HttpStatus.*;

public class TestGetOrderList extends BaseTest {

    private Integer trackIdForTest;

    @Test
    @DisplayName("Получения листа заказов")
    @Description("Создается заказ и отправляется запрос на список заказов, ожидается успешное получение списка заказов")
    public void testRequestOrderListReturnsOrderList() {

        OrderModel order = new OrderModel(FIRSTNAME_ORDER,LASTNAME_ORDER,ADDRESS_ORDER,METRO_STATION_ORDER,
                PHONE_ORDER,RENT_TIME_ORDER,DELIVERY_DATE_ORDER,COMMENT_ORDER,COLOR_ORDER);
        ValidatableResponse response = orderApi.createOrder(order)
                .statusCode(SC_CREATED)
                .body("track", CoreMatchers.notNullValue());

        trackIdForTest = response.extract().path("id");

        orderApi.getOrdersList()
                .statusCode(SC_OK)
                .body("orders", is(not(empty())));

    }

    @After
    public void testOrderCancellation(){
        if (trackIdForTest != null) {
            orderApi.cancelOrder(trackIdForTest).statusCode(SC_OK);
        }
    }

}
