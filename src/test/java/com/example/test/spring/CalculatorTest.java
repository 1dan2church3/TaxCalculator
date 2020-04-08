package com.example.test.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import states.TaxBrackets;
import states.TaxRates;
import utils.Calculator;

public class CalculatorTest {

	@Test
	void testCalculateTaxReturnsZeroWhenIncomeIsZero() {

		String tax = Calculator.calculateTax("$0.00", "Single", TaxBrackets.getTaxBrackets("Montana"),
				TaxRates.getTaxRates("Montana"));

		assertEquals(tax, "$0.00");
	}

	@Test
	void testGetTaxableIncome() {

	}

}
