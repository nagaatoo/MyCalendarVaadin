package ru.numbDev.MyCalendarVaadin.pages;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.numbDev.MyCalendarVaadin.model.Pages;
import ru.numbDev.MyCalendarVaadin.model.SystemConst;

@Component
@Scope("prototype")
@Route(Pages.CALENDAR_LIST)
@PageTitle("Календари")
public class CalendarPage extends Page {

    public CalendarPage() {
        String token = (String) VaadinSession.getCurrent().getAttribute(SystemConst.JWT.name());
        addContent(new Label(token));
    }

}
