package dev.brunocesar.bank.services.repository.config;

import dev.brunocesar.bank.systemcore.domain.model.BusinessException;
import dev.brunocesar.bank.systemcore.port.AccountRepository;
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
 * @since 2021-03-29
 */
@DisplayName("Database Service - Account")
@ContextConfiguration(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
public class AccountRepositoryTest {

    private static final Integer NONEXISTENT_ACCOUNT_NUMBER = 847;
    private static final Integer EXISTENT_ACCOUNT_NUMBER = 50;


    @Inject
    AccountRepository repository;

    @Test
    @DisplayName("get with null account number")
    void nullAccountNumberMustReturnNullAccount() {
        assertNull(repository.get(null));
    }

    @Test
    @DisplayName("get with a nonexistent account number")
    void nonexistentAccountNumberMustReturnNullValue() {
        assertNull(repository.get(NONEXISTENT_ACCOUNT_NUMBER));
    }

    @Test
    @DisplayName("get with an existent account number")
    void existentAccountNumberMustReturnValidAccount() {
        assertNotNull(repository.get(EXISTENT_ACCOUNT_NUMBER));
    }

    @Test
    @DisplayName("update null account must throws exception")
    void updateNullAccountMusThrowsException() {
        var exception = assertThrows(
                BusinessException.class,
                () -> repository.update(null));
        assertEquals(exception.getMessage(), "Account is required.");
    }

    @Test
    @DisplayName("update account with success")
    void updateAccountWithSuccess() {
        var account = repository.get(EXISTENT_ACCOUNT_NUMBER);
        account.setBalance(new BigDecimal("1.00"));
        account.setName("Name updated");
        repository.update(account);

        var accountUpdated = repository.get(EXISTENT_ACCOUNT_NUMBER);
        assertEquals(account.getBalance(), accountUpdated.getBalance());
        assertEquals(account.getName(), accountUpdated.getName());
    }

}
