package ru.numbDev.MyCalendarVaadin;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@PWA(name = "MyCalendar", shortName = "MC")
@EnableFeignClients
public class MyCalendarVaadinApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(MyCalendarVaadinApplication.class, args);
	}

}
