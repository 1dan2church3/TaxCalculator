package utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

import states.TaxBrackets;

public class Calculator {

	private static NumberFormat usFormat = NumberFormat.getCurrencyInstance();

	public static String calculateTax(String income, String filingStatus, BigDecimal[] taxBrackets,
			BigDecimal[] taxRates) {

		BigDecimal totalTax = new BigDecimal(0.0);

		BigDecimal taxableIncome = formatString(income).subtract(FilingStatus.getStdDeduction(filingStatus));

		int j = 0;

		for (int i = 1; i < taxBrackets.length; i++) {

			// If income higher than bracket, add tax for whole bracket
			if (taxableIncome.compareTo(taxBrackets[i]) == 1) {
				totalTax = totalTax.add(taxRates[j].multiply(taxBrackets[i].subtract(taxBrackets[i - 1])));
			}
			// Otherwise we fall somewhere in between brackets
			else {
				totalTax = totalTax.add(taxRates[j].multiply(taxableIncome.subtract(taxBrackets[i - 1])));
				break;
			}
			j++;
		}

		// If we are above all brackets, add tax on remaining income above highest
		// bracket
		if (taxableIncome.compareTo(taxBrackets[taxBrackets.length - 1]) == 1) {
			totalTax = totalTax.add(taxRates[j].multiply(taxableIncome.subtract(taxBrackets[taxBrackets.length - 1])));
		}

		totalTax = totalTax.setScale(2, BigDecimal.ROUND_HALF_UP);

		if (totalTax.compareTo(new BigDecimal("0")) == -1) {
			return usFormat.format(0);
		}

		return usFormat.format(totalTax);
	}

	public static String getTakeHome(String income, String stateTax, String federalTax) {

		BigDecimal incomeBd = formatString(income);
		BigDecimal stateTaxBd = formatString(stateTax);
		BigDecimal federalTaxBd = formatString(federalTax);

		BigDecimal takeHome = incomeBd.subtract(stateTaxBd.add(federalTaxBd));

		takeHome = takeHome.setScale(2, BigDecimal.ROUND_HALF_UP);

		if (takeHome.compareTo(new BigDecimal("0")) == -1) {
			return usFormat.format(0);
		}

		return usFormat.format(takeHome).toString();
	}

	public static String getTaxableIncome(String income, String filingStatus) {

		BigDecimal incomeBd = formatString(income);
		BigDecimal stdDeductionBd = FilingStatus.getStdDeduction(filingStatus);

		BigDecimal taxableIncome = incomeBd.subtract(stdDeductionBd);

		taxableIncome = taxableIncome.setScale(2, BigDecimal.ROUND_HALF_UP);

		// Return 0 if taxable income below $0
		if (taxableIncome.compareTo(new BigDecimal("0")) == -1) {
			return usFormat.format(0);
		}

		return usFormat.format(taxableIncome);
	}

	private static BigDecimal formatString(String rawString) {

		rawString = rawString.replace("$", "").replace(",", "").replace(".", "");

		StringBuilder sb = new StringBuilder(rawString);
		sb.insert(sb.length() - 2, '.');

		return new BigDecimal(sb.toString());
	}

	public static String getTotalTax(String federal, String state) {

		return usFormat.format(formatString(federal).add(formatString(state)));
	}

	public static String getMonthlyIncome(String yearlyIncome) {
		return usFormat.format(formatString(yearlyIncome).divide(new BigDecimal("12"), 2, BigDecimal.ROUND_HALF_UP));
	}

	public static String getBiweeklyIncome(String yearlyIncome) {
		return usFormat.format(formatString(yearlyIncome).divide(new BigDecimal("26"), 2, BigDecimal.ROUND_HALF_UP));
	}

	public static String getWeeklyIncome(String yearlyIncome) {
		return usFormat.format(formatString(yearlyIncome).divide(new BigDecimal("52"), 2, BigDecimal.ROUND_HALF_UP));
	}

	public static String getRothAmount(String pretaxIncome, String filingStatus, String state) {

		BigDecimal taxBracket = getTaxBracket(pretaxIncome, filingStatus, state);

		BigDecimal taxableIncome = formatString(getTaxableIncome(pretaxIncome, filingStatus));
		BigDecimal iraLimit = new BigDecimal("6000");

		BigDecimal rothAmount = iraLimit.subtract(taxableIncome.subtract(taxBracket));

		System.out.println("TaxBracket: " + taxBracket);
		System.out.println("Roth: " + rothAmount);

		if (rothAmount.compareTo(new BigDecimal("0")) == -1) {
			return usFormat.format(new BigDecimal("0"));
		}

		else {
			return usFormat.format(rothAmount);
		}
	}

	private static BigDecimal getTaxBracket(String pretaxIncome, String filingStatus, String state) {

		BigDecimal taxableIncome = formatString(getTaxableIncome(pretaxIncome, filingStatus));
		BigDecimal[] taxBrackets = TaxBrackets.getTaxBrackets(state);
		BigDecimal bracket = taxBrackets[0];

		for (BigDecimal currentBracket : taxBrackets) {
			if (taxableIncome.compareTo(bracket) == 1) {
				bracket = currentBracket;
			}
		}
		return bracket;
	}

	public static String getTaxableIncome() {
		return null;
	}
}
