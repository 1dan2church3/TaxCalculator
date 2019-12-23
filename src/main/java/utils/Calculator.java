package utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

public abstract class Calculator {

	private BigDecimal taxRates [] = null;
	private BigDecimal brackets [] = null;
	NumberFormat usFormat = NumberFormat.getCurrencyInstance();
	
	public String calculateTax(String income) {

		income = income.replace(",", "").replace("$", "").replace(".", "");
		StringBuilder sb = new StringBuilder(income);
		
		//Remove zeros and add . in correct spot
		sb.insert(sb.length() - 2, '.');
		
		BigDecimal formattedIncome = new BigDecimal(sb.toString());
		
		BigDecimal totalTax = new BigDecimal(0.0);
		int j = 0;
		
		for(int i = 1; i < brackets.length; i++) {
			
			//If income higher than bracket, add tax for whole bracket
			if(formattedIncome.compareTo(brackets[i]) == 1) {
				totalTax = totalTax.add(taxRates[j].multiply(brackets[i].subtract(brackets[i - 1])));
			}
			//Otherwise we fall somewhere in between brackets
			else {
				totalTax = totalTax.add(taxRates[j].multiply(formattedIncome.subtract(brackets[i - 1])));
				break;
			}
			j++;
		}
		
		//If we are above all brackets, add tax on remaining income above highest bracket
		if(formattedIncome.compareTo(brackets[brackets.length - 1]) == 1)
		{
			totalTax = totalTax.add(taxRates[j].multiply(formattedIncome.subtract(brackets[brackets.length - 1])));
		}
		
	    totalTax = totalTax.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return usFormat.format(totalTax);
	}
	
	public String calculateTakeHome(String income, String stateTax, String federalTax) {
		
		BigDecimal incomeBd = new BigDecimal(income.replace("$", "").replace(",", ""));
		BigDecimal stateTaxBd = new BigDecimal(stateTax.replace("$", "").replace(",", ""));
		BigDecimal federalTaxBd = new BigDecimal(federalTax.replace("$", "").replace(",", ""));
		
		BigDecimal takeHome = incomeBd.subtract(stateTaxBd.add(federalTaxBd));
		
	    takeHome = takeHome.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return usFormat.format(takeHome).toString();
	}

	public BigDecimal[] getTaxRates() {
		return taxRates;
	}

	public void setTaxRates(BigDecimal[] taxRates) {
		this.taxRates = taxRates;
	}

	public BigDecimal[] getBrackets() {
		return brackets;
	}

	public void setBrackets(BigDecimal[] brackets) {
		this.brackets = brackets;
	} 
}
