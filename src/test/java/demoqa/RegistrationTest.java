package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationTest extends TestBaseExtended {

    @Test
    @Tag("remote")
    void successfulRegistrationTest() {
        step("Open the form", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('#Advertisement-Section').remove()");
            executeJavaScript("$('footer').remove()");
        });
        step("Fill the form", () -> {
            $("#firstName").setValue("Sergey");
            $("#lastName").setValue("Konoplev");
            $("#userEmail").setValue("sergeyKonoplev@gmail.com");
            $("#genterWrapper").$(byText("Other")).click();
            $("#userNumber").setValue("89875058833");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("July");
            $(".react-datepicker__year-select").selectOption("2008");
            $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
            $("#subjectsInput").setValue("Math").pressEnter();
            $("#hobbiesWrapper").$(byText("Sports")).click();
            $("#uploadPicture").uploadFromClasspath("img/1.png");
            $("#currentAddress").setValue("Planetnaya 23");
            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Delhi")).click();
            $("#submit").click();
        });
        step("Verify results", () -> {
            $(".modal-dialog").shouldHave(appear);
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text("Sergey Konoplev"),
                    text("sergeyKonoplev@gmail.com"), text("Other"), text("8987505883"),
                    text("30 July,2008"), text("Maths"), text("Sports"),
                    text("1.png"), text("Planetnaya 23"), text("NCR Delhi"));
        });
    }
}
