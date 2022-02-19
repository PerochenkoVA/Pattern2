package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationInfo;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfullySendForm() {
        RegistrationInfo validUser = DataGenerator.Registration.generateRegisteredUser("active");
        $("[data-test-id=login] input").setValue(validUser.getLogin());
        $("[data-test-id=password] input").setValue(validUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Личный кабинет")).shouldBe(appear);

    }
    @Test
    void shouldGetErrorWithBlockedUser(){
        RegistrationInfo blockedUser = DataGenerator.Registration.generateRegisteredUser("blocked");
        $("[data-test-id=login] input").setValue(blockedUser.getLogin());
        $("[data-test-id=password] input").setValue(blockedUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Пользователь заблокирован")).shouldBe(appear);
    }
    


    @Test
    void noValidPassword(){
        RegistrationInfo wrongPassword = DataGenerator.Registration.shouldGetInvalidPassword();
        $("[data-test-id=login] input").setValue(wrongPassword.getLogin());
        $("[data-test-id=password] input").setValue(wrongPassword.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(appear);

    }
    @Test
    void noValidLogin(){
        RegistrationInfo wrongLogin = DataGenerator.Registration.shouldGetInvalidLogin();
        $("[data-test-id=login] input").setValue(wrongLogin.getLogin());
        $("[data-test-id=password] input").setValue(wrongLogin.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(appear);
    }

}
