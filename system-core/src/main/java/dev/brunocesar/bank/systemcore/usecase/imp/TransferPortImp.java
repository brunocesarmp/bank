package dev.brunocesar.bank.systemcore.usecase.imp;

import dev.brunocesar.bank.systemcore.domain.model.Account;
import dev.brunocesar.bank.systemcore.domain.service.Transfer;
import dev.brunocesar.bank.systemcore.port.AccountRepository;
import dev.brunocesar.bank.systemcore.usecase.port.TransferPort;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

import static dev.brunocesar.bank.systemcore.domain.model.Error.*;
import static java.util.Objects.isNull;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-28
 */
@Named
public class TransferPortImp implements TransferPort {

    private AccountRepository repository;
    private Transfer transfer;

    @Inject
    public TransferPortImp(AccountRepository repository, Transfer transfer) {
        this.repository = repository;
        this.transfer = transfer;
    }

    @Override
    public Account getAccount(Integer number) {
        return repository.get(number);
    }

    @Override
    @Transactional
    public void transfer(Integer debitAccountNumber, Integer creditAccountNumber, BigDecimal value) {
        validate(debitAccountNumber, creditAccountNumber, value);

        var debitAccount = repository.get(debitAccountNumber);
        var creditAccount = repository.get(creditAccountNumber);

        transfer.execute(value, debitAccount, creditAccount);
        repository.update(debitAccount);
        repository.update(creditAccount);
    }

    private void validate(Integer debitAccountNumber, Integer creditAccountNumber, BigDecimal value) {
        if (isNull(debitAccountNumber)) {
            isRequired("Debit account number");
        }

        if (isNull(creditAccountNumber)) {
            isRequired("Credit account number");
        }

        if (isNull(value)) {
            isRequired("Value");
        }

        var debitAccount = repository.get(debitAccountNumber);
        if (isNull(debitAccount)) {
            isNonexistent("Debit account");
        }

        var creditAccount = repository.get(creditAccountNumber);
        if (isNull(creditAccount)) {
            isNonexistent("Credit account");
        }

        if (debitAccount.getNumber().equals(creditAccount.getNumber())) {
            isSameAccount();
        }
    }

}

