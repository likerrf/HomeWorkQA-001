import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests extends TestBase {

    @Test
    void successfulFillFormTest() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue("Timur");
        $("[id=lastName]").setValue("Nikitin");
        $("[id=userEmail]").setValue("9509407766@gmail.com");
        $("[id=userNumber]").setValue("9509407766");
        $("#genterWrapper").$(byText("Male")).click();
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("2007");
        $(".react-datepicker__day--013:not(.react-datepicker__day--outside-month)").click();
        $("[id=subjectsInput]").setValue("play games");
        $(".subjects-auto-complete__option").click();
        $("#hobbiesWrapper").$$("label").findBy(text("Sports")).click();
        $("#hobbiesWrapper").$$("label").findBy(text("Music")).click();
        $("[id=uploadPicture]").uploadFromClasspath("image_3.png");
        $("#currentAddress").setValue("77 Zorge Street");

        $("#state").click();
        $$("[id^=react-select-3-option]").findBy(text("Haryana")).click();

        $("#city").click();
        $$("[id^=react-select-4-option]").findBy(text("Karnal")).click();

        $("[id=submit]").click();

        $(".table-responsive").shouldHave(text("Timur Nikitin"));
        $(".table-responsive").shouldHave(text("9509407766@gmail.com"));
        $(".table-responsive").shouldHave(text("9509407766"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("13 June,2007"));
        $(".table-responsive").shouldHave(text("play games"));
        $(".table-responsive").shouldHave(text("Haryana"));
        $(".table-responsive").shouldHave(text("Karnal"));
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("Music"));
        $(".table-responsive").shouldHave(text("77 Zorge Street"));
        $(".table-responsive").shouldHave(text("image_3.png"));
    }

    @Test
    void successfulFillRequiredFormTest() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue("Timur");
        $("[id=lastName]").setValue("Nikitin");
        $("[id=userNumber]").setValue("9509407766");
        $("#genterWrapper").$$("label").findBy(text("Male")).click();

        $("[id=submit]").click();

        $(".table-responsive").shouldHave(text("Timur Nikitin"));
        $(".table-responsive").shouldHave(text("9509407766"));
        $(".table-responsive").shouldHave(text("Male"));
    }

    @Test
    void unsuccessfulEmptyFormTest() {
        open("/automation-practice-form");

        $("[id=submit]").click();

        $(".modal-body").shouldNot(exist);
    }

    @Test
    void unsuccessfulEmptyFirstNameTest() {
        open("/automation-practice-form");

        $("[id=lastName]").setValue("Nikitin");
        $("[id=userNumber]").setValue("9509407766");
        $("#genterWrapper").$(byText("Male")).click();

        $("[id=submit]").click();

        $(".modal-body").shouldNot(exist);
    }

    @Test
    void unsuccessfulInvalidEmailTest() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue("Timur");
        $("[id=lastName]").setValue("Nikitin");
        $("[id=userNumber]").setValue("9509407766");
        $("#genterWrapper").$(byText("Male")).click();
        $("[id=userEmail]").setValue("mail.rу");

        $("[id=submit]").click();

        $(".modal-body").shouldNot(exist);
    }
}