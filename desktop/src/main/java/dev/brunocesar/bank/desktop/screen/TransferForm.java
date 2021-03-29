package dev.brunocesar.bank.desktop.screen;

import dev.brunocesar.bank.systemcore.usecase.port.TransferPort;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

import static java.util.Objects.isNull;

// Responsável por desenhar a tela de transferencia com a tecnologia javafx.
@Named
public class TransferForm {

    private TextField tfDebit;
    private TextField tfDebitName;
    private TextField tfCredit;
    private TextField tfCreditName;
    private TextField tfValue;
    private TransferPort port;

    @Inject
    public TransferForm(TransferPort port) {
        this.port = port;
    }

    private void clean() {
        tfDebit.setText("");
        tfDebitName.setText("");
        tfCredit.setText("");
        tfCreditName.setText("");
        tfValue.setText("");
    }

    private Integer get(String value) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return null;
        }
    }

    private void message(String text) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Transferência Bancária");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    private void get(TextField tfEntry, TextField tfExit) {
        try {
            var account = port.getAccount(get(tfEntry.getText()));
            if (isNull(account)) {
                tfExit.setText("");
            } else {
                tfExit.setText(account.getName() + " - Saldo R$ " + account.getBalance());
            }
        } catch (Exception e) {
            message(e.getMessage());
        }
    }

    private BigDecimal get() {
        try {
            return new BigDecimal(tfValue.getText());
        } catch (Exception e) {
            return null;
        }
    }

    private FlowPane createScreen() {
        var pn = new FlowPane();
        pn.setHgap(10);
        pn.setVgap(10);

        pn.getChildren().add(new Label(" Conta Débito:"));
        tfDebit = new TextField();
        tfDebit.setPrefWidth(50);
        tfDebit.focusedProperty().addListener((o, v, n) -> {
            if (!n) get(tfDebit, tfDebitName);
        });

        pn.getChildren().add(tfDebit);

        tfDebitName = new TextField();
        tfDebitName.setPrefWidth(300);
        tfDebitName.setEditable(false);
        pn.getChildren().add(tfDebitName);

        pn.getChildren().add(new Label(" Conta Crédito:"));
        tfCredit = new TextField();
        tfCredit.setPrefWidth(50);
        tfCredit.focusedProperty().addListener((o, v, n) -> {
            if (!n) get(tfCredit, tfCreditName);
        });
        pn.getChildren().add(tfCredit);

        tfCreditName = new TextField();
        tfCreditName.setEditable(false);
        tfCreditName.setPrefWidth(300);
        pn.getChildren().add(tfCreditName);

        pn.getChildren().add(new Label(" Valor R$:"));
        tfValue = new TextField();
        tfValue.setPrefWidth(200);
        pn.getChildren().add(tfValue);

        var bt = new Button();
        bt.setOnAction((ev) -> {
            try {
                port.transfer(get(tfDebit.getText()), get(tfCredit.getText()), get());
                clean();
                message("Transferência feita com sucesso!");
            } catch (Exception e) {
                message(e.getMessage());
            }
        });
        bt.setText("Transferir");
        pn.getChildren().add(bt);
        return pn;
    }

    public void show(Stage stage) {
        stage.setTitle("Adaptador JavaFX");
        var scene = new Scene(createScreen(), 500, 100);
        stage.setScene(scene);
        stage.show();
    }
}
