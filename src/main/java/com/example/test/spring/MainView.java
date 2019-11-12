package com.example.test.spring;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import states.Montana;

@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends AppLayout {

	private static final long serialVersionUID = 1L;

	public MainView(@Autowired MessageBean bean) {
    	
		Montana montana = new Montana();
		
    	VerticalLayout layout = new VerticalLayout();
    	layout.setSizeFull();
    	
    	FormLayout form = new FormLayout();
    	
    	Select<String> state = new Select<String>();
    	state.setLabel("State");
    	state.setPlaceholder("Please select");
    	state.setItems("Montana");
    	
    	Select<String> filingStatus = new Select<String>();
    	filingStatus.setLabel("Filing Status");
    	filingStatus.setPlaceholder("Please select");
    	filingStatus.setItems("Single", "Married Filing Jointly");
    	
    	TextField income = new TextField();
    	income.setLabel("Income");
    	income.setPlaceholder("$30,000");
    	
    	Button calcBtn = new Button();
    	
    	TextField stateTax = new TextField();
    	stateTax.setLabel("State Tax");
    	stateTax.setReadOnly(true);
    	stateTax.setValue(montana.calculateTax(40000.00));
    	
    	TextField takeHome = new TextField();
    	takeHome.setLabel("Take Home");
    	takeHome.setReadOnly(true);
    	takeHome.setValue(montana.calculateTakeHome(40000.00));
    	
    	form.add(state, filingStatus, income, stateTax, takeHome);
    	form.setWidth("230px");
    	
    	layout.add(form);
    	setContent(layout);
    	
    	H4 title = new H4("EASY TAX CALCULATOR");
    	addToNavbar(new DrawerToggle(), title);
    	Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        addToDrawer(tabs);
    }
}
