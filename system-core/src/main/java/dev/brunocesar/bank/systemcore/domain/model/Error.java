package dev.brunocesar.bank.systemcore.domain.model;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-27
 */
public class Error {

    public static void isRequired(String name) {
        throw new BusinessException(name + " is required.");
    }

    public static void isNonexistent(String name) {
        throw new BusinessException(name + " is nonexistent.");
    }

    public static void isInsufficientBankBalance() {
        throw new BusinessException("Is insufficient bank balance.");
    }

    public static void isSameAccount() {
        throw new BusinessException("The debit and credit account must be different.");
    }

}
