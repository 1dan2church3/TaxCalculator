package utils;
import java.math.BigDecimal;

public class FilingStatus {

	public static BigDecimal getStdDeduction(String status) {

		switch (status) {
		case ("HeadOfHousehold"):
			return new BigDecimal(18650);
		case ("MarriedFilingJointly"):
			return new BigDecimal(24400);

		case ("MarriedFilingSeperately"):
			return new BigDecimal(12400);
		case ("Single"):
			return new BigDecimal(12200);
		}
		return null;
	}
}
