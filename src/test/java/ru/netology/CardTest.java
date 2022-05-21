package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

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
        $("[data-test-id=city] input").setValue("Екатеринбург");      //заполнить поле Город
        $("[data-test-id=date] input").setValue("26.05.2022");      //заполнить поле Дата встречи
        $("[data-test-id=name] input").setValue("Иванов-Смирнов Иван");       //заполнить поле Фамилия и имя
        $("[data-test-id=phone] input").setValue("+71593577894");       //заполнить поле Мобильный телефон
        $("[data-test-id=agreement]").click();     //нажать на чекбокс согласия
        $x("//*[text()=\"Забронировать\"]").click();      //нажать на кнопку Забронировать

      $(withText("Успешно!")).should(visible, Duration.ofSeconds(15));    //проверка на наличие текста на странице, время ожидания 15 с
    }
}
