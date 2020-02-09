package com.example.test.spring;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import states.TaxBrackets;
import states.TaxRates;
import utils.Calculator;
import utils.FilingStatus;

@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends AppLayout {

	private static final long serialVersionUID = 1L;

	public MainView(@Autowired MessageBean bean) {

		NumberFormat usFormat = NumberFormat.getCurrencyInstance();

		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeFull();
		layout.setJustifyContentMode(JustifyContentMode.CENTER);
		layout.setAlignItems(Alignment.CENTER);

		FormLayout form = new FormLayout();

		Select<String> state = new Select<String>();
		state.setRequiredIndicatorVisible(true);
		state.setLabel("State");
		state.setPlaceholder("Please select");
		state.setItems("Montana");

		Select<String> filingStatus = new Select<String>();
		filingStatus.setRequiredIndicatorVisible(true);
		filingStatus.setLabel("Filing Status");
		filingStatus.setPlaceholder("Please select");
		filingStatus.setItems("Single", "Married Filing Jointly", "Married Filing Seperately", "Head of Household",
				"Qualifying Widow(er) with Dependent Child");
		filingStatus.setWidth("380px");

		TextField income = new TextField();
		income.isRequired();
		income.setLabel("Income");
		income.setPlaceholder("$30,000");
		income.setValueChangeMode(ValueChangeMode.EAGER);
		income.setWidth("260px");
		income.addBlurListener(e -> income
				.setValue(usFormat.format(Double.parseDouble(income.getValue().replace("$", "").replace(",", "")))));

		TextField stateTax = new TextField();
		stateTax.setLabel("State Tax");
		stateTax.setReadOnly(true);
		stateTax.setWidth("260px");

		TextField federalTax = new TextField();
		federalTax.setLabel("Federal Tax");
		federalTax.setReadOnly(true);
		federalTax.setWidth("260px");

		TextField takeHome = new TextField();
		takeHome.setLabel("Take Home");
		takeHome.setReadOnly(true);
		takeHome.setWidth("260px");

		Button calcBtn = new Button();
		calcBtn.setText("Calculate");

		calcBtn.addClickListener(event -> federalTax.setValue(Calculator.calculateTax(
				new BigDecimal(income.getValue()).subtract(FilingStatus.getStdDeduction(filingStatus.getValue()),
				TaxBrackets.getTaxBrackets("Federal"), TaxRates.getTaxRates("Federal"))));
		calcBtn.addClickListener(event -> stateTax.setValue(Calculator.calculateTax(income.getValue(),
				TaxBrackets.getTaxBrackets(state.getValue()), TaxRates.getTaxRates(state.getValue()))));
		calcBtn.addClickListener(event -> takeHome
				.setValue(Calculator.calculateTakeHome(income.getValue(), stateTax.getValue(), federalTax.getValue())));
		calcBtn.setWidth("260px");

		calcBtn.addClickListener(event -> {
			if (income.isEmpty()) {

			}
		});

		form.add(state, filingStatus, income, calcBtn, stateTax, federalTax, takeHome);
		form.setMaxWidth("380px");

		layout.add(form);
		setContent(layout);

		H4 title = new H4("EASY TAX CALCULATOR");
		addToNavbar(new DrawerToggle(), title);
		Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
		tabs.setOrientation(Tabs.Orientation.VERTICAL);
		addToDrawer(tabs);
	}
}
