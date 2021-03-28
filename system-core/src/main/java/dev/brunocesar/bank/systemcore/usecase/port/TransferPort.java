package dev.brunocesar.bank.systemcore.usecase.port;

import dev.brunocesar.bank.systemcore.domain.model.Account;

import java.math.BigDecimal;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-28
 */
public interface TransferPort {

    Account getAccount(Integer number);

    void transfer(Integer debitAccount, Integer creditAccount, BigDecimal value);

}
