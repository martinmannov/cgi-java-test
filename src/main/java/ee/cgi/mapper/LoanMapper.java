package ee.cgi.mapper;

import ee.cgi.model.Loan;
import ee.cgi.resource.LoanDto;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public static LoanDto toLoanDto(Loan loan) {
        return new LoanDto().setId(loan.getId())
                .setPrincipalAmount(loan.getPrincipalAmount())
                .setInterestRate(loan.getInterestRate())
                .setTerm(loan.getTerm());
    }

    public static Loan toLoan(LoanDto loanDto){
        var loan = new Loan();
        loan.setPrincipalAmount(loanDto.getPrincipalAmount());
        loan.setInterestRate(loanDto.getInterestRate());
        loan.setTerm(loanDto.getTerm());
        return loan;
    }
}
