package ru.numbDev.MyCalendarVaadin.pages;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.numbDev.MyCalendarVaadin.client.api.UserApiClient;
import ru.numbDev.MyCalendarVaadin.client.dto.UserAuth;
import ru.numbDev.MyCalendarVaadin.component.TestComponent;
import ru.numbDev.MyCalendarVaadin.model.Pages;
import ru.numbDev.MyCalendarVaadin.model.SystemConst;

import java.io.IOException;
import java.io.InputStream;

@Component
@Scope("prototype")
@Route("/auth")
@PageTitle("MyCalendar Auth")
public class AuthPage extends VerticalLayout {

    private final Resource resourceFile;
    private final UserApiClient userApiClient;

    public AuthPage(
            TestComponent testComponent,
            UserApiClient userApiClient,
            @Value("classpath:static/logo.jpg") Resource resourceFile
    ) throws IOException {
        this.resourceFile = resourceFile;
        this.userApiClient = userApiClient;
        add(logo());
        add(credentials());
        setAlignItems(Alignment.CENTER);
    }

    private Image logo() throws IOException {
        var resource = new StreamResource("logo", this::getLogo);
        return new Image(resource, "sdf");
    }

    private InputStream getLogo() {
        try {
            return resourceFile.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private LoginForm credentials() {
        LoginI18n i18n = LoginI18n.createDefault();

        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("MyCalendar");
        i18nForm.setUsername("Логин");
        i18nForm.setPassword("Пароль");
        i18nForm.setSubmit("Вход");
        i18nForm.setForgotPassword("Забыли пароль?");
        i18n.setForm(i18nForm);

        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(i18n);

        loginForm.addLoginListener(this::auth);
        loginForm.addForgotPasswordListener(e -> {});

        return loginForm;
    }

    private void auth(AbstractLogin.LoginEvent e) {
        var auth = new UserAuth();
        auth.setLogin(e.getUsername());
        auth.setPassword(e.getPassword());
        var result = userApiClient.auth(auth);

        if (result.getStatusCode() != HttpStatus.OK) {
            // TODO ошибки
        }
        VaadinSession.getCurrent().setAttribute(SystemConst.JWT.name(), result.getBody());
        UI.getCurrent().navigate(Pages.CALENDAR_LIST);
    }

}
