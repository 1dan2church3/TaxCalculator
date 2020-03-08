package utils;

import java.math.BigDecimal;

public class FilingStatus {

	public static BigDecimal getStdDeduction(String status) {

		switch (status) {
		case ("Head of Household"):
			return new BigDecimal(18650);
		case ("Married Filing Jointly"):
			return new BigDecimal(24400);
		case ("Married Filing Seperately"):
			return new BigDecimal(12400);
		case ("Single"):
			return new BigDecimal(12200);
		}
		return null;
	}
}
