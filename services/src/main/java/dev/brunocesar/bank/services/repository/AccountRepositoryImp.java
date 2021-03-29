package dev.brunocesar.bank.services.repository;

import dev.brunocesar.bank.systemcore.domain.model.Account;
import dev.brunocesar.bank.systemcore.domain.model.BusinessException;
import dev.brunocesar.bank.systemcore.port.AccountRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

import static java.util.Objects.isNull;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-29
 */
@Named
public class AccountRepositoryImp implements AccountRepository {

    private static final String ERROR = "Unexpected error in access database";

    private JdbcTemplate jdbc;

    @Inject
    public AccountRepositoryImp(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Account get(Integer number) {
        if (isNull(number)) {
            return null;
        }
        var sql = "select * from account where number = ?";
        var pm = new Object[]{number};
        RowMapper<Account> orm = (rs, rm) -> new Account(rs.getInt(1), rs.getBigDecimal(2), rs.getString(3));

        try {
            var list = jdbc.query(sql, pm, orm);
            return list.isEmpty() ? null : list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ERROR);
        }
    }

    @Transactional
    @Override
    public void update(Account account) {
        if (isNull(account)) {
            throw new BusinessException("Account is required");
        }

        var sql = "update account set balance=?, name=? where number=?";
        var pm = new Object[]{account.getBalance(), account.getName(), account.getNumber()};

        try {
            jdbc.update(sql, pm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ERROR);
        }
    }

}
