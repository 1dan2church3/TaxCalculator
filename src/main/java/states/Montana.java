package states;

import java.math.BigDecimal;

import utils.Calculator;

public class Montana extends Calculator {
	
	private final BigDecimal taxRates [] = {new BigDecimal(0.01), new BigDecimal(0.02), new BigDecimal(0.03), new BigDecimal(0.04),
			new BigDecimal(0.05), new BigDecimal(0.06), new BigDecimal(0.069)};
	private final BigDecimal brackets [] = {new BigDecimal(0.00), new BigDecimal(3000.0), new BigDecimal(5200.0), new BigDecimal(8000.0), 
			new BigDecimal(10800.0), new BigDecimal(13900.0), new BigDecimal(17900.0)};
	
	public Montana() {
		this.setTaxRates(taxRates);
		this.setBrackets(brackets);
	}
}
