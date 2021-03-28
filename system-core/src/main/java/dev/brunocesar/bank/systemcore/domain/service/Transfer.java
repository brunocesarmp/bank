package dev.brunocesar.bank.systemcore.domain.service;

import dev.brunocesar.bank.systemcore.domain.model.Account;

import javax.inject.Named;
import java.math.BigDecimal;

import static dev.brunocesar.bank.systemcore.domain.model.Error.isRequired;
import static java.util.Objects.isNull;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-28
 */
@Named
public class Transfer {

    public void execute(BigDecimal value, Account debit, Account credit) {
        if (isNull(value)) {
            isRequired("Transfer value");
        }
        if (isNull(debit)) {
            isRequired("Debit account");
        }
        if (isNull(credit)) {
            isRequired("Credit account");
        }
        debit.toDebit(value);
        credit.toCredit(value);
    }

}
