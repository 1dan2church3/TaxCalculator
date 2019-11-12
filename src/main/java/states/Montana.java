package states;

import utils.Calculator;

public class Montana extends Calculator {
	
	private double taxRates [] = {0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.069};
	private double brackets [] = {0.00, 3000.0, 5200.0, 8000.0, 10800.0, 13900.0, 17900.0};
	
	public Montana() {
		this.setTaxRates(taxRates);
		this.setBrackets(brackets);
	}
}
