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
@DisplayName("Credit account rules")
public class CreditAccountTest {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private Account validAccount;

    @BeforeEach
    void before() {
        validAccount = new Account(1, ONE_HUNDRED, "Rebecca");
    }

    @Test
    @DisplayName("credit null value throws exception")
    void creditNullValueThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> validAccount.toCredit(null));

        assertTrue(exception.getMessage().contains("Credit value is required."));
    }

    @Test
    @DisplayName("credit zero value throws exception")
    void creditEqualsZeroValueThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> validAccount.toCredit(new BigDecimal(0)));

        assertTrue(exception.getMessage().contains("Credit value is required."));
    }

    @Test
    @DisplayName("credit less than zero value throws exception")
    void creditLessThanZeroValueThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> validAccount.toCredit(new BigDecimal(-1)));

        assertTrue(exception.getMessage().contains("Credit value is required."));
    }

    @Test
    @DisplayName("credit more than zero runs successfully")
    void creditMoreThanZeroRunsSuccessfully() {
        validAccount.toCredit(BigDecimal.ONE);
        var finalBalance = ONE_HUNDRED.add(BigDecimal.ONE);
        assertEquals(validAccount.getBalance(), finalBalance, "Total balance must be equals");
    }

}
