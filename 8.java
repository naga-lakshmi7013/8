# Build a Tip Calculator App Using JavaFX

In this project, you'll create a Tip Calculator using JavaFX that allows users to enter a bill amount, select a tip percentage, and calculate the tip and total bill through a graphical user interface (GUI).

You'll learn how to use JavaFX components and respond to user interactions with event handlers.

## 1. What the app will look like

# Tip Calculator

Calculate your tip in seconds

Bill amount (₹)1000

Tip percentage5%

10%

15 15%

20%

Number of people2

 Calculate Tip Tip amount

## ₹150.00

Total bill

## ₹1,150.00

Per person

## ₹575.00

Interactive preview of the app's behavior. The JavaFX version below is implemented in Java.

## 2. JavaFX components you'll use

| Component | Purpose |
| --- | --- |
| `Stage` | Main application window |
| `Scene` | Holds the GUI layout |
| `Label` | Displays text and results |
| `TextField` | Accepts the bill amount |
| `ComboBox` | Lets the user select a tip percentage |
| `Spinner` | Selects the number of people |
| `Button` | Triggers the calculation |
| `GridPane` | Arranges input fields in rows and columns |
| `VBox` | Organizes components vertically |
| `Alert` | Displays input validation errors |

## 3. Complete JavaFX program

Create a Java file named `TipCalculator.java` and add the following code.

java
```

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;

public class TipCalculator extends Application {

    @Override
    public void start(Stage stage) {

        // 1. Create GUI components
        Label title = new Label("Tip Calculator");

        Label billLabel = new Label("Bill Amount (₹):");
        TextField billField = new TextField();
        billField.setPromptText("Enter bill amount");

        Label tipLabel = new Label("Tip Percentage:");
        ComboBox<Integer> tipBox = new ComboBox<>();
        tipBox.getItems().addAll(5, 10, 15, 20);
        tipBox.setValue(15);

        Label peopleLabel = new Label("Number of People:");
        Spinner<Integer> peopleSpinner =
                new Spinner<>(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        1, 100, 1
                    )
                );
        peopleSpinner.setEditable(true);

        Button calculateButton = new Button("Calculate Tip");

        Label tipResult = new Label("Tip: ₹0.00");
        Label totalResult = new Label("Total: ₹0.00");
        Label perPersonResult = new Label("Per Person: ₹0.00");

        // 2. Arrange input components using GridPane
        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(15);

        grid.add(billLabel, 0, 0);
        grid.add(billField, 1, 0);

        grid.add(tipLabel, 0, 1);
        grid.add(tipBox, 1, 1);

        grid.add(peopleLabel, 0, 2);
        grid.add(peopleSpinner, 1, 2);

        // 3. Handle button click
        calculateButton.setOnAction(event -> {
            try {
                double bill = Double.parseDouble(
                    billField.getText().trim()
                );

                int tipPercent = tipBox.getValue();
                int people = peopleSpinner.getValue();

                if (!Double.isFinite(bill) || bill < 0) {
                    throw new IllegalArgumentException(
                        "Bill must be a finite, non-negative number."
                    );
                }

                if (people <= 0) {
                    throw new IllegalArgumentException(
                        "Number of people must be positive."
                    );
                }

                double tipAmount = bill * tipPercent / 100.0;
                double totalAmount = bill + tipAmount;
                double perPerson = totalAmount / people;

                tipResult.setText(
                    String.format(Locale.US, "Tip: ₹%.2f", tipAmount)
                );

                totalResult.setText(
                    String.format(Locale.US, "Total: ₹%.2f", totalAmount)
                );

                perPersonResult.setText(
                    String.format(
                        Locale.US, "Per Person: ₹%.2f", perPerson
                    )
                );

            } catch (NumberFormatException ex) {
                showError("Please enter a valid bill amount.");
            } catch (IllegalArgumentException ex) {
                showError(ex.getMessage());
            }
        });

        // 4. Style and arrange the layout
        title.setStyle(
            "-fx-font-size: 24px; -fx-font-weight: bold;"
        );

        calculateButton.setStyle(
            "-fx-background-color: #2878D0;"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 14px;"
        );

        VBox root = new VBox(
            18, title, grid, calculateButton,
            tipResult, totalResult, perPersonResult
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #F4F7FB;");

        // 5. Create the scene and display the window
        Scene scene = new Scene(root, 420, 440);

        stage.setTitle("JavaFX Tip Calculator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText("Unable to calculate tip");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

## 4. How the program works

### Step 1: Create the input controls

java
```
TextField billField = new TextField();
ComboBox<Integer> tipBox = new ComboBox<>();
tipBox.getItems().addAll(5, 10, 15, 20);
tipBox.setValue(15);
```

- `TextField` accepts the bill amount.
- `ComboBox` provides the tip percentages.
- `setValue(15)` selects a default tip of 15%.

### Step 2: Arrange the components

java
```
GridPane grid = new GridPane();
grid.setHgap(12);
grid.setVgap(15);

grid.add(billLabel, 0, 0);
grid.add(billField, 1, 0);
```

`GridPane` arranges the controls in rows and columns. The first number is the column index, and the second is the row index.

The `VBox` places the title, input grid, button, and result labels vertically.

### Step 3: Respond to user interactions

The most important part of this project is the event handler:

java
```
calculateButton.setOnAction(event -> {
    // Read input, calculate values,
    // and update the result labels.
});
```

`setOnAction()` registers code that runs when the user clicks the button.

When the button is clicked, the program:

1. Reads the bill amount from the text field.
2. Gets the selected tip percentage.
3. Gets the number of people.
4. Calculates the tip and total.
5. Updates the result labels.
6. Displays an error dialog if the input is invalid.

This is an example of event-driven programming: the application responds to actions performed by the user.

### Step 4: Understand the calculations

The app uses these formulas:

$$
\text{Tip}=\text{Bill}\times\frac{\text{Tip Percentage}}{100}
$$

$$
\text{Total}=\text{Bill}+\text{Tip}
$$

$$
\text{Per Person}=\frac{\text{Total}}{\text{Number of People}}
$$

For example, if the bill is ₹1,000, the tip is 15%, and two people share the bill:

| Result | Amount |
| --- | --- |
| Bill | ₹1,000.00 |
| Tip | ₹150.00 |
| Total | ₹1,150.00 |
| Per person | ₹575.00 |

## 5. How to run the application

1. Install a JDK compatible with your JavaFX version.
2. Install the JavaFX SDK from [Gluon JavaFX](<https://gluonhq.com/products/javafx/>).
3. Save the program as `TipCalculator.java`.
4. Compile and run it using your JavaFX SDK path.

For example, on a system where JavaFX is installed in `javafx-sdk`, use:

bash
```
javac --module-path "javafx-sdk/lib" \
      --add-modules javafx.controls TipCalculator.java

java --module-path "javafx-sdk/lib" \
     --add-modules javafx.controls TipCalculator
```

Replace `javafx-sdk/lib` with the actual path to your JavaFX `lib` directory. These commands work in a terminal where the JDK and JavaFX SDK are installed.

## 6. Practice exercises

Try these changes to understand JavaFX interactions better.

- Exercise 1: Add a 25% tip option to the `ComboBox`.
- Exercise 2: Add a Reset button that clears the bill field and restores the default selections and results.
- Exercise 3: Change the result labels' text color when the calculation succeeds.
- Exercise 4: Add a keyboard interaction so pressing Enter triggers the calculation.
- Exercise 5: Display the tip percentage and number of people in the final results.

Key takeaway: This project combines JavaFX controls, layout management, event handling, input validation, and dynamic label updates to build a working desktop application.
