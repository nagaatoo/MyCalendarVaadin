package ru.numbDev.MyCalendarVaadin.pages;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.VaadinSession;
import ru.numbDev.MyCalendarVaadin.model.SystemConst;

public class Page extends VerticalLayout implements BeforeEnterObserver {

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

    public void addContent(Component component) {
        contentPanel.addContent(component);
    }

    public void addControlButton(Button button) {
        controlPanel.addControls(button);
    }

    private static class ControlPanel extends HorizontalLayout {

        private final HorizontalLayout controls = new HorizontalLayout();
        private final Button userAccButton = new Button();

        public ControlPanel() {
            setHeight("100px");
            setWidth("80%");
            setAlignItems(Alignment.CENTER);
            getStyle()
                    .set("background-color", "#79cadb")
                    .set("border-radius", "1em");
            setVisible(true);
            init();
        }

        private void init() {
            add(controls);
            add(userAccButton);
            setVerticalComponentAlignment(Alignment.END, userAccButton);
            setVerticalComponentAlignment(Alignment.START, controls);
        }

        private void addControls(Component component) {

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
