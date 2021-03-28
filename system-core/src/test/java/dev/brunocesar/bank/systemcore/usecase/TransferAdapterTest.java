package dev.brunocesar.bank.systemcore.usecase;

import dev.brunocesar.bank.systemcore.domain.model.BusinessException;
import dev.brunocesar.bank.systemcore.usecase.port.TransferPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-28
 */
@DisplayName("Use case - transfer service")
@ContextConfiguration(classes = BuildTestSystemMockConfig.class)
@ExtendWith(SpringExtension.class)
public class TransferAdapterTest {

    private static final Integer CREDIT_ACCOUNT_NUMBER = 10;
    private static final Integer DEBIT_ACCOUNT_NUMBER = 20;
    private static final Integer NONEXISTENT_ACCOUNT_NUMBER = 30;
    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
    private static final BigDecimal TWENTY = new BigDecimal("20");

    @Inject
    private TransferPort transferPort;

    @Test
    @DisplayName("get with null account number")
    void nullAccountNumberMustReturnNullValue() {
        var account = transferPort.getAccount(null);
        assertNull(account);
    }

    @Test
    @DisplayName("get with nonexistent account number")
    void nonexistentAccountNumberMustReturnNullValue() {
        var account = transferPort.getAccount(NONEXISTENT_ACCOUNT_NUMBER);
        assertNull(account);
    }

    @Test
    @DisplayName("get with existent account number")
    void existentAccountNumberMustReturnValidAccount() {
        var account = transferPort.getAccount(CREDIT_ACCOUNT_NUMBER);
        assertNotNull(account);
    }

    @Test
    @DisplayName("debit account is required")
    void debitAccountMustBeRequired() {
        var exception = assertThrows(
                BusinessException.class,
                () -> transferPort.transfer(null, CREDIT_ACCOUNT_NUMBER, TWENTY));
        assertEquals(exception.getMessage(), "Debit account number is required.");
    }

    @Test
    @DisplayName("credit account is required")
    void creditAccountMustBeRequired() {
        var exception = assertThrows(
                BusinessException.class,
                () -> transferPort.transfer(DEBIT_ACCOUNT_NUMBER, null, TWENTY));
        assertEquals(exception.getMessage(), "Credit account number is required.");
    }

    @Test
    @DisplayName("value to transfer is required")
    void transferValueMustBeRequired() {
        var exception = assertThrows(
                BusinessException.class,
                () -> transferPort.transfer(DEBIT_ACCOUNT_NUMBER, CREDIT_ACCOUNT_NUMBER, null));
        assertEquals(exception.getMessage(), "Value is required.");
    }

    @Test
    @DisplayName("debit account nonexistent throws exception")
    void debitAccountNonexistentThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> transferPort.transfer(NONEXISTENT_ACCOUNT_NUMBER, CREDIT_ACCOUNT_NUMBER, TWENTY));
        assertEquals(exception.getMessage(), "Debit account is nonexistent.");
    }

    @Test
    @DisplayName("credit account nonexistent throws exception")
    void creditAccountNonexistentThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> transferPort.transfer(DEBIT_ACCOUNT_NUMBER, NONEXISTENT_ACCOUNT_NUMBER, TWENTY));
        assertEquals(exception.getMessage(), "Credit account is nonexistent.");
    }

    @Test
    @DisplayName("same debit and credit accounts throws exception")
    void sameDebitAndCreditAccountsThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> transferPort.transfer(DEBIT_ACCOUNT_NUMBER, DEBIT_ACCOUNT_NUMBER, TWENTY));
        assertEquals(exception.getMessage(), "The debit and credit account must be different.");
    }

    @Test
    @DisplayName("transfer of twenty runs successfully")
    void transferOfTwentyValueRunsSuccessfully() {
        try {
            transferPort.transfer(DEBIT_ACCOUNT_NUMBER, CREDIT_ACCOUNT_NUMBER, TWENTY);
        } catch (BusinessException e) {
            fail("It mustn't throws exception");
        }

        var creditAccount = transferPort.getAccount(CREDIT_ACCOUNT_NUMBER);
        var debitAccount = transferPort.getAccount(DEBIT_ACCOUNT_NUMBER);

        assertEquals(creditAccount.getBalance(), ONE_HUNDRED.add(TWENTY));
        assertEquals(debitAccount.getBalance(), ONE_HUNDRED.subtract(TWENTY));
    }

}
