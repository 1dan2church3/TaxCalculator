package states;

import java.math.BigDecimal;

public class TaxRates {

	public static BigDecimal[] getTaxRates(String state) {

		BigDecimal[] taxRates = null;
		switch (state) {

		case ("Federal"):
			taxRates = new BigDecimal[] { new BigDecimal(0.10), new BigDecimal(0.12), new BigDecimal(0.22),
					new BigDecimal(0.24), new BigDecimal(0.32), new BigDecimal(0.35), new BigDecimal(0.37) };
			break;

		case ("Montana"):
			taxRates = new BigDecimal[] { new BigDecimal(0.01), new BigDecimal(0.02), new BigDecimal(0.03),
					new BigDecimal(0.04), new BigDecimal(0.05), new BigDecimal(0.06), new BigDecimal(0.069) };
			break;
		}
		return taxRates;
	}
}
