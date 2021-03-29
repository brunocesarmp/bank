package dev.brunocesar.bank;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-28
 */
@RunWith(JUnitPlatform.class)
@SelectPackages({
        "dev.brunocesar.bank.systemcore.domain.model",
        "dev.brunocesar.bank.systemcore.domain.service",
        "dev.brunocesar.bank.systemcore.usecase"
})
public class SuiteCore {
}
