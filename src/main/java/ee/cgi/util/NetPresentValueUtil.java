package ee.cgi.util;

import ee.cgi.model.Loan;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@UtilityClass
public class NetPresentValueUtil {
    private static final Double DEFAULT_DISCOUNT_RATE = 0.1D;

    public static BigDecimal calculateNPV(Loan loan) {
        return calculateNPV(loan, DEFAULT_DISCOUNT_RATE);
    }

    public static BigDecimal calculateNPV(Loan loan, double discountRate) {
        BigDecimal principalAmount = loan.getPrincipalAmount();
        BigDecimal annualInterestRate = loan.getInterestRate().divide(BigDecimal.valueOf(1000), MathContext.DECIMAL128);
        int loanTermMonths = loan.getTerm();

        BigDecimal npv = BigDecimal.ZERO;
        BigDecimal[] cashFlows = generateMonthlyCashFlow(principalAmount, annualInterestRate, loanTermMonths);

        for (int i = 0; i < cashFlows.length; i++) {
            BigDecimal discountedCashFlow = cashFlows[i].divide(BigDecimal.valueOf(Math.pow(1 + discountRate, i + 1)), MathContext.DECIMAL128);
            npv = npv.add(discountedCashFlow);
        }

        // TODO Rounding mode needs to be verified
        return principalAmount.subtract(npv).setScale(2,RoundingMode.HALF_UP);
    }

    public static BigDecimal[] generateMonthlyCashFlow(BigDecimal principalAmount, BigDecimal annualInterestRate, int loanTermMonths) {

        BigDecimal monthlyPayment = calculateMonthlyPayment(principalAmount, annualInterestRate, loanTermMonths);
        BigDecimal[] cashFlows = new BigDecimal[loanTermMonths];
        for (int month = 1; month <= loanTermMonths; month++) {
            cashFlows[month - 1] = monthlyPayment;
        }

        return cashFlows;
    }

    public static BigDecimal calculateMonthlyPayment(BigDecimal principalAmount, BigDecimal annualInterestRate, int loanTermMonths) {
        // Convert annual interest rate to monthly interest rate
        BigDecimal monthlyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        BigDecimal onePlusR = monthlyInterestRate.add(BigDecimal.ONE);
        BigDecimal onePlusRPowerN = onePlusR.pow(loanTermMonths);

        BigDecimal numerator = principalAmount.multiply(monthlyInterestRate).multiply(onePlusRPowerN);
        BigDecimal denominator = onePlusRPowerN.subtract(BigDecimal.ONE);

        // Rounding mode needs to be verified
        return numerator.divide(denominator, 2, RoundingMode.HALF_UP);
    }
}
