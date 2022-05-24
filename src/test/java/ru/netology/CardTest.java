package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


class CardDeliveryOrderTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldSuccessfullyBookTheDeliveryOfTheCard() {
        String date = generateDate(4);      //увеличиваем текущую дату на 4 дня
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);      //заполнить поле Дата встречи
        $("[data-test-id=date] input").setValue(date);      //заполнить поле Дата встречи
        $("[data-test-id=name] input").setValue("Иванов-Смирнов Иван");
        $("[data-test-id=phone] input").setValue("+71593577894");
        $("[data-test-id=agreement]").click();     //нажать на чекбокс согласия
        $x("//*[text()=\"Забронировать\"]").click();

        $(withText("Успешно!")).should(visible, Duration.ofSeconds(15));    //проверка на наличие текста на странице, время ожидания 15 с
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15));
    }
}
