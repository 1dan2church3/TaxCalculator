package states;

import java.math.BigDecimal;

import utils.Calculator;

public class Federal extends Calculator {
	
	private final BigDecimal taxRates [] = {new BigDecimal(0.10), new BigDecimal(0.12), new BigDecimal(0.22), new BigDecimal(0.24),
			new BigDecimal(0.32), new BigDecimal(0.35), new BigDecimal(0.37)};
	private final BigDecimal brackets [] = {new BigDecimal(0.00), new BigDecimal(19750.0), new BigDecimal(80250.0), new BigDecimal(171050.0), 
			new BigDecimal(326600.0), new BigDecimal(414700.0), new BigDecimal(622050.0)};
	
	public Federal() {
		this.setTaxRates(taxRates);
		this.setBrackets(brackets);
	}
}
