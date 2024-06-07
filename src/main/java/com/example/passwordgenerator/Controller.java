package com.example.passwordgenerator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static boolean isNotNumber(String str) {
        try {
            Double.parseDouble(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    @FXML
    private CheckBox digitsCB;

    @FXML
    private Button copyBTN;

    @FXML
    private Button genBTN;

    @FXML
    private TextField lengthTF;

    @FXML
    private CheckBox lowercaseCB;

    @FXML
    private Slider lengthSlider;

    @FXML
    private TextField passwordTF;

    @FXML
    private CheckBox specialCB;

    @FXML
    private CheckBox uppercaseCB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        copyBTN.setOnAction(e -> {
            String text = passwordTF.getText();
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            System.out.println("Text copied to clipboard: " + text);
        });

        genBTN.setOnAction(e -> {
            PasswordGenerator passwordGenerator = new PasswordGenerator();
            passwordGenerator.setLowercase(lowercaseCB.isSelected());
            passwordGenerator.setUppercase(uppercaseCB.isSelected());
            passwordGenerator.setDigits(digitsCB.isSelected());
            passwordGenerator.setSpecial(specialCB.isSelected());
            passwordGenerator.setPassLength(Integer.parseInt(lengthTF.getText()));

            passwordTF.setText(passwordGenerator.generatePassword());
        });

        lengthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                lengthTF.setText(String.valueOf((int)lengthSlider.getValue()));
            }
        });

        lengthTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(
                        isNotNumber(lengthTF.getText())
                        || Integer.parseInt(lengthTF.getText()) > 32
                        || Integer.parseInt(lengthTF.getText()) < 0
                ) {
                    lengthTF.setText(String.valueOf((int)lengthSlider.getValue()));
                } else {
                    lengthSlider.setValue(Integer.parseInt(lengthTF.getText()));
                }
            }
        });
    }
}
