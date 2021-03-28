package dev.brunocesar.bank.systemcore.adapter;

import dev.brunocesar.bank.systemcore.domain.model.Account;
import dev.brunocesar.bank.systemcore.port.AccountRepository;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static dev.brunocesar.bank.systemcore.domain.model.Error.isNonexistent;
import static java.util.Objects.isNull;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-28
 */
@Named
public class AccountAdapterMockImpl implements AccountRepository {

    private Map<Integer, Account> bank = new HashMap<>();

    public AccountAdapterMockImpl() {
        bank.put(10, new Account(10, new BigDecimal(100), "Fernando Fake"));
        bank.put(20, new Account(20, new BigDecimal(100), "Rebeca Fake"));
    }

    @Override
    public Account get(Integer number) {
        System.out.println("Fake database -> Account get(number)");
        return bank.get(number);
    }

    @Override
    public void update(Account account) {
        System.out.println("Fake database -> Account update(number)");
        if (!isNull(bank.get(account.getNumber()))) {
            bank.put(account.getNumber(), account);
        } else {
            isNonexistent("Account " + account.getNumber());
        }
    }

}
