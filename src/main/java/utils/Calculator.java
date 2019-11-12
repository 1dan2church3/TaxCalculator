package utils;

import java.math.BigDecimal;

public abstract class Calculator {

	private double taxRates [] = null;
	private double brackets [] = null;
	
	public String calculateTax(double income) {
		
		double totalTax = 0.0;
		int j = 0;
		
		for(int i = 1; i < brackets.length; i++) {
			
			//If income higher than bracket, add tax for whole bracket
			if(income > brackets[i]) {
				totalTax += taxRates[j] * (brackets[i] - brackets[i - 1]);
			}
			//Otherwise we fall somewhere in between brackets
			else {
				totalTax += taxRates[j] * (income - brackets[i - 1]);
				break;
			}
			j++;
		}
		
		//If we are above all brackets, add tax on remaining income above highest bracket
		if(income > brackets[brackets.length - 1])
		{
			totalTax += taxRates[j] * (income - brackets[brackets.length - 1]);
		}
		
		BigDecimal bd = new BigDecimal(Double.toString(totalTax));
	    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return bd.toString();
	}
	
	public String calculateTakeHome(double income) {
		double tax = Double.parseDouble(calculateTax(income));
		return Double.toString(income - tax);
	}

	public double[] getTaxRates() {
		return taxRates;
	}

	public void setTaxRates(double[] taxRates) {
		this.taxRates = taxRates;
	}

	public double[] getBrackets() {
		return brackets;
	}

	public void setBrackets(double[] brackets) {
		this.brackets = brackets;
	} 
}
