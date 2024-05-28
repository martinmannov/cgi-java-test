package ee.cgi.service;


import ee.cgi.resource.LoanDto;

import java.util.List;
import java.util.Optional;

public interface LoanModificationService {

    /**
     * Gets list of loans for controller
     *
     * @return list of {@link LoanDto}
     */
    List<LoanDto> getLoanList();

    /**
     * Gets specific loan
     *
     * @param id {@link ee.cgi.model.Loan#getId()}
     * @return loan if found or empty optional
     */
    Optional<LoanDto> getLoan(Long id);

    /**
     * Creates loans
     *
     * @param loanDto {@link LoanDto}
     * @return loan with filled id object
     */
    LoanDto createLoan(LoanDto loanDto);

    /**
     * Updates loan
     *
     * @param id id of loan that is being updated
     * @param loanDto @{@link LoanDto}
     * @return updated loan
     */
    LoanDto updateLoan(Long id, LoanDto loanDto);
}
