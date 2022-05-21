package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


class CardDeliveryOrderTest {

  @BeforeEach
  void setup() {
    open("http://localhost:9999");      //перейти на страницу по URL
  }

  @Test
      void shouldSuccessfullyBookTheDeliveryOfTheCard() {
      Calendar c = new GregorianCalendar();
      c.add(Calendar.DAY_OF_YEAR, 4);   // увеличиваем на 4 дней от текущей даты
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");   //придаем нужный формат дате
    String date = format.format(c.getTime());

    $("[data-test-id=city] input").setValue("Екатеринбург");      //заполнить поле Город
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);      //заполнить поле Дата встречи
        $("[data-test-id=date] input").setValue(date);      //заполнить поле Дата встречи
        $("[data-test-id=name] input").setValue("Иванов-Смирнов Иван");       //заполнить поле Фамилия и имя
        $("[data-test-id=phone] input").setValue("+71593577894");       //заполнить поле Мобильный телефон
        $("[data-test-id=agreement]").click();     //нажать на чекбокс согласия
        $x("//*[text()=\"Забронировать\"]").click();      //нажать на кнопку Забронировать

      $(withText("Успешно!")).should(visible, Duration.ofSeconds(15));    //проверка на наличие текста на странице, время ожидания 15 с
    }
}
