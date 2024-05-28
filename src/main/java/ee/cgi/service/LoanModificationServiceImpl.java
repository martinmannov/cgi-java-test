package ee.cgi.service;

import ee.cgi.LoanValidationException;
import ee.cgi.mapper.LoanMapper;
import ee.cgi.model.Loan;
import ee.cgi.repository.LoanRepository;
import ee.cgi.resource.LoanDto;
import ee.cgi.util.NetPresentValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoanModificationServiceImpl implements LoanModificationService {
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<LoanDto> getLoanList() {
        return loanRepository.findAll().stream()
                .map(LoanMapper::toLoanDto)
                .toList();
    }

    @Override
    public Optional<LoanDto> getLoan(Long id) {
        return loanRepository.findById(id)
                .map(LoanMapper::toLoanDto);
    }

    @Override
    public LoanDto createLoan(LoanDto loanDto) {
        var loan = LoanMapper.toLoan(loanDto);
        loan = loanRepository.save(loan);

        loanDto.setNpv(NetPresentValueUtil.calculateNPV(loan));
        loanDto.setId(loan.getId());
        return loanDto;
    }

    @Override
    public LoanDto updateLoan(Long id, LoanDto loanDto) {
        var savedLoan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanValidationException("Loan was not found for update"));
        BigDecimal oldNpv = NetPresentValueUtil.calculateNPV(savedLoan);

        Loan loan = LoanMapper.toLoan(loanDto);
        loan.setId(id);
        loanRepository.save(loan);

        BigDecimal newNpv = NetPresentValueUtil.calculateNPV(loan);

        loanDto = LoanMapper.toLoanDto(loan);
        loanDto.setOldNpv(oldNpv);
        loanDto.setNpv(newNpv);
        return loanDto;
    }
}
