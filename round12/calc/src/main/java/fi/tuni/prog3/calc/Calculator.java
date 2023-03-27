package fi.tuni.prog3.calc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Calculator extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Create the GUI elements
    TextField fieldOp1 = new TextField();
    fieldOp1.setId("fieldOp1");
    TextField fieldOp2 = new TextField();
    fieldOp2.setId("fieldOp2");

    Label labelOp1 = new Label("First operand:");
    labelOp1.setId("labelOp1");
    Label labelOp2 = new Label("Second operand:");
    labelOp2.setId("labelOp2");

    Button btnAdd = new Button("Add");
    btnAdd.setId("btnAdd");
    Button btnSub = new Button("Subtract");
    btnSub.setId("btnSub");
    Button btnMul = new Button("Multiply");
    btnMul.setId("btnMul");
    Button btnDiv = new Button("Divide");
    btnDiv.setId("btnDiv");

    Label labelRes = new Label("Result:");
    labelRes.setId("labelRes");
    Label fieldRes = new Label("");
    fieldRes.setId("fieldRes");
    fieldRes.setStyle("-fx-background-color: white;");

    // Add event listeners to the buttons
    btnAdd.setOnAction(event -> {
      try {
        double op1 = Double.parseDouble(fieldOp1.getText());
        double op2 = Double.parseDouble(fieldOp2.getText());
        double result = op1 + op2;
        fieldRes.setText(Double.toString(result));
      } catch (NumberFormatException e) {
        fieldRes.setText("Invalid input");
      }
    });

    btnSub.setOnAction(event -> {
        try {
            double op1 = Double.parseDouble(fieldOp1.getText());
            double op2 = Double.parseDouble(fieldOp2.getText());
            double result = op1 - op2;
            fieldRes.setText(Double.toString(result));
        } catch (NumberFormatException e) {
            fieldRes.setText("Invalid input");
        }
    });

    btnMul.setOnAction(event -> {
        try {
            double op1 = Double.parseDouble(fieldOp1.getText());
            double op2 = Double.parseDouble(fieldOp2.getText());
            double result = op1 * op2;
            fieldRes.setText(Double.toString(result));
        } catch (NumberFormatException e) {
            fieldRes.setText("Invalid input");
        }
    });

    btnDiv.setOnAction(event -> {
        try {
            double op1 = Double.parseDouble(fieldOp1.getText());
            double op2 = Double.parseDouble(fieldOp2.getText());
            if (op2 == 0) {
                fieldRes.setText("Cannot divide by zero");
            } else {
                double result = op1 / op2;
                fieldRes.setText(Double.toString(result));
            }
        } catch (NumberFormatException e) {
            fieldRes.setText("Invalid input");
        }
    });

    // Create the layout
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(10));
    gridPane.setHgap(5);
    gridPane.setVgap(10);

    gridPane.add(labelOp1, 0, 0);
    gridPane.add(fieldOp1, 1, 0);
    gridPane.add(labelOp2, 0, 1);
    gridPane.add(fieldOp2, 1, 1);
    gridPane.add(btnAdd, 0, 2);
    gridPane.add(btnSub, 1, 2);
    gridPane.add(btnMul, 0, 3);
    gridPane.add(btnDiv, 1, 3);
    gridPane.add(labelRes, 0, 4);
    gridPane.add(fieldRes, 1, 4);

    // Create the scene and show the window
    Scene scene = new Scene(gridPane, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Pocket Calculator");
    primaryStage.show();
  }

  public static void main(String[] args) {
      launch(args);
  }
}