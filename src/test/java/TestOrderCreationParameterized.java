import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static data.constants.*;


@RunWith(Parameterized.class)
public class TestOrderCreationParameterized extends BaseOrderTest {
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
        createOrderSuccess(order);

    }

}
