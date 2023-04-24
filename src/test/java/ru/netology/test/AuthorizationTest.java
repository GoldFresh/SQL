package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import ru.netology.sql.DbInteraction;

import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void cleanDb() {
        DbInteraction.deleteTables();
    }

    @Test
    void shouldAuthorization() {
        LoginPage loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.validVerify(DbInteraction.getCode());
    }

    @Test
    void shouldGetBlockMessage() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfoInvalid();
        loginPage.login(authInfo);
        loginPage.getInvalidLogin();
        loginPage.cleaning();
        loginPage.login(authInfo);
        loginPage.getInvalidLogin();
        loginPage.cleaning();
        loginPage.login(authInfo);
        loginPage.getBlockedMessage();
    }
}
