package states;

import java.math.BigDecimal;

public class TaxBrackets {

	public static BigDecimal[] getTaxBrackets(String state) {

		BigDecimal[] taxBrackets = null;

		switch (state) {

		case ("Federal"):
			taxBrackets = new BigDecimal[] { new BigDecimal(0.00), new BigDecimal(19750.0), new BigDecimal(80250.0),
					new BigDecimal(171050.0), new BigDecimal(326600.0), new BigDecimal(414700.0),
					new BigDecimal(622050.0) };
			break;

		case ("Montana"):
			taxBrackets = new BigDecimal[] { new BigDecimal(0.00), new BigDecimal(3000.0), new BigDecimal(5200.0),
					new BigDecimal(8000.0), new BigDecimal(10800.0), new BigDecimal(13900.0), new BigDecimal(17900.0) };
			break;
		}
		return taxBrackets;
	}

}
