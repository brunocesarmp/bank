package dev.brunocesar.bank.desktop.dev;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Desktop Adapter + System-core + MockAdapter
 *
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-29
 */
@Configuration
@ComponentScan({
        "dev.brunocesar.bank.desktop.dev",
        "dev.brunocesar.bank.desktop.screen",
        "dev.brunocesar.bank.systemcore",
        "dev.brunocesar.bank.adapter"
})
public class BuildDesktopSystemMockConfig {
}
