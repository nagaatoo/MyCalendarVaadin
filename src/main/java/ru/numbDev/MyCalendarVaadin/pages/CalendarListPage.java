package ru.numbDev.MyCalendarVaadin.pages;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.numbDev.MyCalendarVaadin.model.Pages;
import ru.numbDev.MyCalendarVaadin.model.Path;
import ru.numbDev.MyCalendarVaadin.model.SystemConst;

@Component
@Scope("prototype")
@Route(value = Pages.CALENDAR_LIST, layout = WorkPage.class)
@PageTitle("Календари")
public class CalendarListPage extends VerticalLayout {

    public CalendarListPage() {
        // TODO добавить в контекст
        String token = (String) VaadinSession.getCurrent().getAttribute(SystemConst.JWT.name());
        add(new Label(token));
//        addContent(new Label(token), Path.CALENDAR_LIST);
    }
}
