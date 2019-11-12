package utils;

import java.math.BigDecimal;

public abstract class Calculator {

	private double taxRates [] = null;
	private double brackets [] = null;
	
	public String calculateTax(double income) {
		
		double totalTax = 0.0;
		
		for(int i = 1; i < brackets.length; i++) {
			
			//If income higher than bracket, add tax for whole bracket
			if(brackets[i] < income) {
				System.out.println("I " + brackets[i]);
				if(brackets[i] > income) { // We found the bracket we are in
					totalTax += taxRates[i - 1] * (income - brackets[i - 1]);
					System.out.println("Paid here: " + totalTax);
				}
				else {
					totalTax += taxRates[i - 1] * (brackets[i] - brackets[i - 1]);
					System.out.println("Bracket: " + brackets[i]);
					System.out.println("Paid in this bracket: " + taxRates[i - 1] * (brackets[i] - brackets[i - 1]));
					System.out.println("Total Paid: " + totalTax);
				}
			}
		}
		
		BigDecimal bd = new BigDecimal(Double.toString(totalTax));
	    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return bd.toString();
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
