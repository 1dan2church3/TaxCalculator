package com.example.test.spring;

import java.text.NumberFormat;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import states.TaxBrackets;
import states.TaxRates;
import utils.Calculator;

public class TaxForm extends FormLayout {

	private static final long serialVersionUID = 1L;

	public TaxForm() {
		NumberFormat usFormat = NumberFormat.getCurrencyInstance();

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

		// Federal tax
		calcBtn.addClickListener(event -> federalTax.setValue(Calculator.calculateTax(income.getValue(),
				filingStatus.getValue(), TaxBrackets.getTaxBrackets("Federal"), TaxRates.getTaxRates("Federal"))));

		// State tax
		calcBtn.addClickListener(
				event -> stateTax.setValue(Calculator.calculateTax(income.getValue(), filingStatus.getValue(),
						TaxBrackets.getTaxBrackets(state.getValue()), TaxRates.getTaxRates(state.getValue()))));

		// Take home
		calcBtn.addClickListener(event -> takeHome
				.setValue(Calculator.calculateTakeHome(income.getValue(), stateTax.getValue(), federalTax.getValue())));
		calcBtn.setWidth("260px");

		calcBtn.addClickListener(event -> {
			if (income.isEmpty()) {

			}
		});
		add(state, filingStatus, income, calcBtn, stateTax, federalTax, takeHome);
		setMaxWidth("380px");

	}
}
