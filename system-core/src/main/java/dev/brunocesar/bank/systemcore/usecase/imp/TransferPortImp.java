package dev.brunocesar.bank.systemcore.usecase.imp;

import dev.brunocesar.bank.systemcore.domain.model.Account;
import dev.brunocesar.bank.systemcore.usecase.port.TransferPort;

import java.math.BigDecimal;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-28
 */
public class TransferPortImp implements TransferPort {

    @Override
    public Account getAccount(Integer number) {
        return null;
    }

    @Override
    public void transfer(Integer debitAccount, Integer creditAccount, BigDecimal value) {

    }

}
