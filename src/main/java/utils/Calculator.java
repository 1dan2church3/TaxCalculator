package utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

public abstract class Calculator {

	private double taxRates [] = null;
	private double brackets [] = null;
	NumberFormat usFormat = NumberFormat.getCurrencyInstance();
	
	public String calculateTax(double income) {
//		System.out.println("Income: " + income);
//		
//		StringBuilder incomeSb = new StringBuilder(Double.toString(income));
//		incomeSb.insert(incomeSb.length() - 2, '.');
//		System.out.println("IncomeSb: " + incomeSb);
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
		
		return usFormat.format(bd).toString();
	}
	
	public String calculateTakeHome(double income) {
		String incomeString = Double.toString(income);
		incomeString.replace(",", "");
		
		BigDecimal bd = new BigDecimal(calculateTax(Double.parseDouble(incomeString)));
	    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return usFormat.format(bd).toString();
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
