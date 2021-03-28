package dev.brunocesar.bank.systemcore.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-27
 */
@DisplayName("Debit account rules")
public class DebitAccountTest {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private Account validAccount;

    @BeforeEach
    void before() {
        validAccount = new Account(1, ONE_HUNDRED, "Rebecca");
    }

    @Test
    @DisplayName("debit null value throws exception")
    void debitNullValueThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> validAccount.toDebit(null));

        assertTrue(exception.getMessage().contains("Debit value is required."));
    }

    @Test
    @DisplayName("debit zero value throws exception")
    void debitEqualsZeroValueThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> validAccount.toDebit(new BigDecimal(0)));

        assertTrue(exception.getMessage().contains("Debit value is required."));
    }

    @Test
    @DisplayName("debit less than zero value throws exception")
    void debitLessThanZeroValueThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> validAccount.toDebit(new BigDecimal(-1)));

        assertTrue(exception.getMessage().contains("Debit value is required."));
    }

    @Test
    @DisplayName("debit more than total balance throws exception")
    void debitMoreThanTotalBalanceThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> validAccount.toDebit(ONE_HUNDRED.add(BigDecimal.ONE)));

        assertTrue(exception.getMessage().contains("Is insufficient bank balance."));
    }

    @Test
    @DisplayName("debit total balance runs successfully")
    void debitTotalBalanceShouldRunsSuccessfully() {
        validAccount.toDebit(ONE_HUNDRED);
        assertEquals(validAccount.getBalance(), BigDecimal.ZERO, "Total balance must be zero");
    }

    @Test
    @DisplayName("debit partial value runs successfully")
    void debitPartialValueShouldRunsSuccessfully() {
        validAccount.toDebit(BigDecimal.TEN);
        var finalBalance = ONE_HUNDRED.subtract(BigDecimal.TEN);
        assertEquals(validAccount.getBalance(), finalBalance, "Total balance must equals");
    }

}
