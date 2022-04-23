package controller;

import calculator.CalcValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
    @FXML private Label infoLabel;
    @FXML private Label errorLabel;
    @FXML
    private TextField mainTField;
    private CalcValidator validator;

    public void initialize(){
        validator = new CalcValidator();

        // Text changed listener
        onTextChangedListener();
    }

    private void onTextChangedListener() {
        mainTField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                errorLabel.setText((!validator.staplesIsValid(newValue)) ? "Wrong staples placement" : "");
                mainTField.setText(validator.inputNormalizer(newValue));
                infoLabel.setText("I've entered " + validator.numberCounter(newValue) + " numbers");
            } else {
                errorLabel.setText("");
                infoLabel.setText("");
            }
        });
    }


}
