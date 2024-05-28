package ee.cgi.contoller;


import ee.cgi.resource.LoanDto;
import ee.cgi.service.LoanModificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/loan")
public class LoanModificationController {

    @Autowired
    private LoanModificationService loanModificationService;

    @GetMapping
    public List<LoanDto> getLoanList() {
        return loanModificationService.getLoanList();
    }

    @GetMapping("/{id}")
    public Optional<LoanDto> getLoan(@PathVariable Long id) {
        return loanModificationService.getLoan(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanDto createLoan(@Valid @RequestBody LoanDto loanDto) {
        return loanModificationService.createLoan(loanDto);
    }

    @PutMapping("/{id}")
    public LoanDto updateLoan(@PathVariable Long id, @RequestBody LoanDto loanDto) {
        return loanModificationService.updateLoan(id, loanDto);
    }
}
