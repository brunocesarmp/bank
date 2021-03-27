package dev.brunocesar.bank.systemcore.domain.model;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-27
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
