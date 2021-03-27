package dev.brunocesar.bank.systemcore.domain.model;

import java.math.BigDecimal;

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

    public void toCredit(BigDecimal value) throws BusinessException {

    }

    public void toDebit(BigDecimal value) throws BusinessException {

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
