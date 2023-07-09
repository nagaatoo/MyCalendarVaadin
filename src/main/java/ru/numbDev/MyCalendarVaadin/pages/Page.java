package ru.numbDev.MyCalendarVaadin.pages;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;
import ru.numbDev.MyCalendarVaadin.model.Path;
import ru.numbDev.MyCalendarVaadin.model.SystemConst;

public class Page extends VerticalLayout implements BeforeEnterObserver, RouterLayout {

    private final ControlPanel controlPanel = new ControlPanel();
    private final ContentPanel contentPanel = new ContentPanel();

    public Page() {
        init();
    }

    private void init() {
        setSpacing(false);
        add(controlPanel);
        add(contentPanel);

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    public void addContent(Component component, Path path) {
        contentPanel.addContent(component);
        controlPanel.setPath(path, "");
    }

    public void addControlButton(Button button) {
        controlPanel.addControls(button);
    }

    private static class ControlPanel extends HorizontalLayout {

        private final HorizontalLayout controls = new HorizontalLayout();

        private final Button calendarListNavigate = new Button();
        private final Button calendarNavigate = new Button();
        private final Button monthNavigate = new Button();
        private final Button userAccButton = new Button();

        public ControlPanel() {
            setHeight("50px");
            setWidth("80%");
            setAlignItems(Alignment.CENTER);
            setSpacing(true);
            setPadding(true);
            getStyle()
                    .set("background-color", "#79cadb")
                    .set("border-radius", "1em");
            setVisible(true);
            init();
        }

        private void init() {
            calendarListNavigate.setText("Календари");
            calendarNavigate.setText("Календарь");
            monthNavigate.setText("Месяц");

            calendarNavigate.setVisible(false);
            monthNavigate.setVisible(false);

            controls.setWidth("100%");
            controls.add(calendarListNavigate);
            controls.add(calendarNavigate);
            controls.add(monthNavigate);

            controls.setSpacing(true);

            add(controls);
            add(userAccButton);

            setVerticalComponentAlignment(Alignment.CENTER, userAccButton);
            setVerticalComponentAlignment(Alignment.CENTER, controls);
        }

        private void addControls(Component component) {

        }

        public void setPath(Path path, String name) {
            switch (path) {
                case CALENDAR_LIST -> {
                    // TODO
                }
                case CALENDAR -> {
                    calendarNavigate.setVisible(true);
                    calendarNavigate.setText(name);

                    monthNavigate.setVisible(false);
                }
                case MONTH -> {
                    monthNavigate.setVisible(true);
                    monthNavigate.setText(name);
                }
            }
        }
    }

    private static class ContentPanel extends VerticalLayout {
        public ContentPanel() {
            setHeight("100%");
            setWidth("70%");


            getStyle()
                    .set("background-color", "#F1F1F1")
                    .set("border-radius", "1em");
            setVisible(true);
//            setAlignItems(Alignment.CENTER);
        }

        private void addContent(Component component) {
            this.add(component);
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (VaadinSession.getCurrent().getAttribute(SystemConst.JWT.name()) == null) {
            event.rerouteTo(AuthPage.class);
        }
    }

}
