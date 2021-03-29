package dev.brunocesar.bank.desktop.screen;

import dev.brunocesar.bank.desktop.dev.BuildDesktopSystemMockConfig;
import dev.brunocesar.bank.desktop.hml.BuildDesktopSystemHsqldbConfig;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-29
 */
public class JavaFxAdapter extends Application {

    private ApplicationContext spring;

    @Override
    public void init() throws Exception {
        System.out.println("Starting spring.");
//        spring = new AnnotationConfigApplicationContext(BuildDesktopSystemMockConfig.class);
        spring = new AnnotationConfigApplicationContext(BuildDesktopSystemHsqldbConfig.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var form = spring.getBean(TransferForm.class);
        form.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
