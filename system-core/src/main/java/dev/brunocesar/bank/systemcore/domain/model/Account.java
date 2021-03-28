package dev.brunocesar.bank.systemcore.domain.model;

import java.math.BigDecimal;

import static dev.brunocesar.bank.systemcore.domain.model.Error.isRequired;
import static dev.brunocesar.bank.systemcore.domain.model.Error.isInsufficientBankBalance;
import static java.util.Objects.isNull;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-27
 */
public class Account {

    private Integer number;
    private BigDecimal balance;
    private String name;

    public Account() {
        number = 0;
        balance = BigDecimal.ZERO;
        name = "Uninformed";
    }

    public Account(Integer number, BigDecimal balance, String name) {
        this.number = number;
        this.balance = balance;
        this.name = name;
    }

    public void toCredit(BigDecimal credit) throws BusinessException {
        if (isNull(credit) || credit.compareTo(BigDecimal.ZERO) <= 0) {
            isRequired("Credit value");
        }
        balance = balance.add(credit);
    }

    public void toDebit(BigDecimal debit) throws BusinessException {
        if (isNull(debit) || debit.compareTo(BigDecimal.ZERO) <= 0) {
            isRequired("Debit value");
        }
        if (debit.compareTo(balance) > 0) {
            isInsufficientBankBalance();
        }
        balance = balance.subtract(debit);
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                '}';
    }

}
