package com.example.test.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends AppLayout {

    public MainView(@Autowired MessageBean bean) {
    	
    	VerticalLayout layout = new VerticalLayout();
    	layout.setSizeFull();
    	
    	FormLayout form = new FormLayout();
    	
    	TextField income = new TextField();
    	income.setLabel("Income");
    	income.setPlaceholder("$30,000");
    	
    	Select state = new Select();
    	
    	form.add(income);
    	form.setWidth("200px");
    	
    	layout.add(form);
    	setContent(layout);
    	
    	H4 title = new H4("EASY TAX CALCULATOR");
    	addToNavbar(new DrawerToggle(), title);
    	Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        addToDrawer(tabs);
    }
    
    private Component createCard() {
		Span card1Label = new Span("Card");
		FlexLayout card = new FlexLayout(card1Label);
		card.addClassName("card");
		card.setAlignItems(Alignment.CENTER);
		card.setJustifyContentMode(JustifyContentMode.CENTER);
		card.setWidth("100%");
		card.setHeight("200px");
		return card;
	}

}
