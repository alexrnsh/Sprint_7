package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;

public class constants {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    final static Faker user = new Faker();
    public static final String LOGIN = user.name().lastName() + user.number().digits(4);
    public static final String PASSWORD = user.regexify("[0-9]{4}");
    public static final String FIRSTNAME = user.name().firstName();

    final static Faker order = new Faker();
    public static final String FIRSTNAME_ORDER = order.name().firstName();
    public static final String LASTNAME_ORDER = order.name().lastName();
    public static final String ADDRESS_ORDER = order.address().streetAddress();
    public static final String METRO_STATION_ORDER = order.regexify("[1-100]{2}");
    public static final String PHONE_ORDER = order.phoneNumber().cellPhone();
    public static final int RENT_TIME_ORDER = order.number().numberBetween(1,10);
    public static final String DELIVERY_DATE_ORDER = LocalDate.now().plusDays(order.number().numberBetween(1, 7)).toString();
    public static final String COMMENT_ORDER = order.lorem().sentence();
    public static final String[] COLOR_ORDER = {"BLACK"};
}
