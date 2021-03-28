package dev.brunocesar.bank.systemcore.usecase;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

/**
 * Test Adapter + System-core + MockAdapter
 *
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-28
 */
@ContextConfiguration
@ComponentScan({
        "dev.brunocesar.bank.systemcore",
        "dev.brunocesar.bank.adapter"
})
public class BuildTestSystemMockConfig {
}
