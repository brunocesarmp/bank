package dev.brunocesar.bank.systemcore.port;

import dev.brunocesar.bank.systemcore.domain.model.Account;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-28
 */
public interface AccountRepository {

    Account get(Integer number);

    void update(Account account);
    
}
