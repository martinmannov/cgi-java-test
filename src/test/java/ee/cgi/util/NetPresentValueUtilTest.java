package ee.cgi.util;

import ee.cgi.model.Loan;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NetPresentValueUtilTest {

    @Test
    public void testCalculateNpv12Month() {
        // Create a sample loan object
        Loan loan = new Loan();
        loan.setPrincipalAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("3.5"));
        loan.setTerm(12);
        var npv = NetPresentValueUtil.calculateNPV(loan);

        //var expectedNPV = new BigDecimal("4213.676");
        var expectedNPV = new BigDecimal("4311.18");

        assertEquals(expectedNPV, npv);
    }

    @Test
    public void testCalculateNpv24Month() {
        // Create a sample loan object
        Loan loan = new Loan();
        loan.setPrincipalAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("3.5"));
        loan.setTerm(24);
        var npv = NetPresentValueUtil.calculateNPV(loan);

        var expectedNPV = new BigDecimal("6242.67");

        assertEquals(expectedNPV, npv);
    }

    @Test
    public void testCalculateMonthly() {
        BigDecimal principalAmount = new BigDecimal("10000.00");
        BigDecimal interestRate = new BigDecimal("0.0035"); // Decimals
        int termInMonths = 12;
        var monthlyPayment = NetPresentValueUtil.calculateMonthlyPayment(principalAmount, interestRate, termInMonths);

        var expectedMonthly = new BigDecimal("834.91");

        assertEquals(expectedMonthly, monthlyPayment);
    }

}
