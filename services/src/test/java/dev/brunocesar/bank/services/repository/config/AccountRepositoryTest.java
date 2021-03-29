package dev.brunocesar.bank.services.repository.config;

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
 * @since 2021-03-29
 */
@DisplayName("Database Service - Account")
@ContextConfiguration(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
public class AccountRepositoryTest {

    @Test
    @DisplayName("get with null account number")
    void nullAccountNumberMustReturnNullValue() {
//        var account = transferPort.getAccount(null);
//        assertNull(account);
        System.out.println("Ola");
    }

}
