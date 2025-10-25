import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.OrderModel;
import org.junit.Test;
import static data.constants.*;

public class TestGetOrderList extends BaseOrderTest {

    @Test
    @DisplayName("Получения листа заказов")
    @Description("Создается заказ и отправляется запрос на список заказов, ожидается успешное получение списка заказов")
public void testRequestOrderListReturnsOrderList() {

        OrderModel order = new OrderModel(FIRSTNAME_ORDER,LASTNAME_ORDER,ADDRESS_ORDER,METRO_STATION_ORDER,
                PHONE_ORDER,RENT_TIME_ORDER,DELIVERY_DATE_ORDER,COMMENT_ORDER,COLOR_ORDER);
        createOrderSuccess(order);
        getOrdersList();

    }

}
