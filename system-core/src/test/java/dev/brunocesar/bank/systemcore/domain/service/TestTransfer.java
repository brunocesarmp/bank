package dev.brunocesar.bank.systemcore.domain.service;

import dev.brunocesar.bank.systemcore.domain.model.Account;
import dev.brunocesar.bank.systemcore.domain.model.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.brunocesar.bank.systemcore.domain.model.Error.isRequired;
import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;



/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-28
 */
@DisplayName("Transfer rules")
public class TestTransfer {

    private final static BigDecimal ONE_HUNDRED = new BigDecimal("100");
    private final static BigDecimal TWENTY = new BigDecimal("20");

    private final Transfer transfer = new Transfer();

    private Account debitAccount;
    private Account creditAccount;

    @BeforeEach
    void before() {
        debitAccount = new Account(1, ONE_HUNDRED, "Fernando");
        creditAccount = new Account(2, ONE_HUNDRED, "Rebecca");
    }

    @Test
    @DisplayName("transfer null value throws exception")
    void transNullValueThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> transfer.execute(null, debitAccount, creditAccount));

        assertTrue(exception.getMessage().contains("Transfer value is required."));
    }

    @Test
    @DisplayName("debit account null throws exception")
    void debitAccountNullThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> transfer.execute(TWENTY, null, creditAccount));

        assertTrue(exception.getMessage().contains("Debit account is required."));
    }

    @Test
    @DisplayName("credit account null throws exception")
    void creditAccountNullThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> transfer.execute(TWENTY, debitAccount, null));

        assertTrue(exception.getMessage().contains("Credit account is required."));
    }

    @Test
    @DisplayName("Transfer valid value runs successfully")
    void transferValidValueRunsSuccessfully() {
        transfer.execute(TWENTY, debitAccount, creditAccount);
        assertEquals(debitAccount.getBalance(), ONE_HUNDRED.subtract(TWENTY));
        assertEquals(creditAccount.getBalance(), ONE_HUNDRED.add(TWENTY));
    }

}
