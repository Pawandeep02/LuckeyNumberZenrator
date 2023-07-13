package com.example.luckynumber;

import java.time.LocalDate;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class LuckyNumberGenerator extends Application {

    private Random random = new Random();
    static retrieveFileText rft=new retrieveFileText();
    static characterToNumber ctn=new characterToNumber();
    static dayToLine dtl=new dayToLine();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lucky Number Generator");

// Create UI controls
// Create a label for Name
        Label nameLabel = new Label("Name:");
// Set the font of the label to bold
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

// Create a text field for Name
        TextField nameTextField = new TextField();
// Set the prompt text for the text field
        nameTextField.setPromptText("Eg. John Doe");

// Create a label for Date of Birth
        Label dobLabel = new Label("Date of Birth:");
// Set the font of the label to bold
        dobLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

// Create a date picker control for Date of Birth
        DatePicker dobDatePicker = new DatePicker();

// Create two buttons: one for generating a result and one for canceling the operation
        Button generateButton = new Button("Generate");
        Button cancelButton = new Button("Cancel");

// Create a label to display the result
        Label resultLabel = new Label();
        resultLabel.setFont(Font.font("System",FontWeight.BOLD,12));
        Label resultLabel_luckyNumber = new Label();
        resultLabel_luckyNumber.setFont(Font.font("Your Lucky Number: ",FontWeight.BOLD,12));
        Label resultLabel_luckyName = new Label();
        resultLabel_luckyName.setFont(Font.font("Your Lucky Character: ",FontWeight.BOLD,12));
        Label resultLabel_luckyDay = new Label();
        resultLabel_luckyDay.setFont(Font.font("Your Lucky Day: ",FontWeight.BOLD,12));


// Create another label to display another result
        Label result_FortuneTeller=new Label("Check your fortune");
        resultLabel_luckyNumber.setFont(Font.font("System",FontWeight.BOLD,12));
        Label resultLabel1= new Label() ;

// Set the maximum width of the label to 100 pixels
        resultLabel1.setMaxWidth(100);

// Set the wrap text property of the label to true, so that the text wraps around if it exceeds the width of the label
        resultLabel1.setWrapText(true);

// Create an image view with a placeholder image, which can be replaced later
        ImageView imageView = new ImageView(new Image("https://via.placeholder.com/250x150"));

        // Set up the UI layout using a GridPane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setStyle("-fx-background-color: #7efafa"); // Set the background color of the GridPane
        gridPane.setHgap(10);
        gridPane.setVgap(10);

// Set the dimensions of the primary stage to be the same as the screen dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());

// Set the maximum width of the resultLabel1 to be the same as the GridPane
        resultLabel1.setMaxWidth(gridPane.getMaxWidth());
// Set the wrap text property of the resultLabel1 to true
        resultLabel1.setWrapText(true);

// Set the background color and font of the generateButton
        generateButton.setStyle("-fx-background-color: lightgreen;");
        generateButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));

// Set the background color and font of the cancelButton
        cancelButton.setStyle("-fx-background-color: red;");
        cancelButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));


// Set the padding of the GridPane
        gridPane.setPadding(new Insets(25, 25, 25, 25));

// Add UI controls to the GridPane at specific positions
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(dobLabel, 0, 1);
        gridPane.add(dobDatePicker, 1, 1);
        gridPane.add(generateButton, 0, 2, 2, 1); // span 2 columns
        gridPane.add(cancelButton, 3, 2, 2, 1); // span 2 columns
        gridPane.add(resultLabel, 0, 3, 2, 1); // span 2 columns
        gridPane.add(resultLabel_luckyNumber, 0, 3, 2, 1);
        gridPane.add(resultLabel_luckyName, 0, 4, 2, 1);
        gridPane.add(resultLabel_luckyDay, 0, 5, 2, 1);

        gridPane.add(result_FortuneTeller, 0, 6, 2, 1);
        gridPane.add(resultLabel1,0,8,10,4); // span 10 columns and 4 rows
        gridPane.add(imageView, 0, 4, 2, 1); // span 2 columns



        // Set up event handlers
        generateButton.setOnAction(event -> {
            LocalDate dob = dobDatePicker.getValue();
            if (dob == null) {
                resultLabel.setText("Please enter a valid date of birth");
                return;
            }
            else {
                result_FortuneTeller.setText("\n\nYour Fortune ");
            }
            // Set up event handler for cancelButton
            cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // Close the popup stage when cancelButton is clicked
                    Stage popupStage = (Stage) cancelButton.getScene().getWindow();
                    popupStage.close();
                }
            });

            String name = nameTextField.getText().trim();
            if (name.isEmpty()) {
                resultLabel.setText("Please enter your name");
                return;
            }

// Get the day of the week of the selected date of birth and convert it to a number using the DayToLine class
            String dayName= String.valueOf(dob.getDayOfWeek());
            int dayDate=dob.getDayOfMonth();
            int dayDateSum=calculateSum(dayDate);
            int monthNum=dob.getMonthValue();
            int monthNumSum=calculateSum(monthNum);
            int yearNum=dob.getYear();
            int yearNumSum=calculateSum(yearNum);
            int sumOfDates=dayDateSum+monthNumSum+yearNumSum;
            System.out.println("Sum of dates"+sumOfDates);
            System.out.println("Day: "+dayDate+" month "+monthNum+" year "+yearNum);
            int dayNumber=dtl.dayToLine(dayName);

// Get the first letter of the name and convert it to a number using the AlphabetToNumber class
            char firstChar = name.charAt(0);
            int luckyCharNumber=ctn.getAlphabetIndex(firstChar);

// Generate a random number between 0 and 9
            int luckyNumber = calculateSum(sumOfDates);

// Use the LineGenerator class to get the lucky day, lucky number, and lucky alphabet corresponding to the generated numbers
            String luckyDay=rft.lineDay(dayNumber);
            String alphabetLucky=rft.lineName(luckyCharNumber);
            String luckyNumberLine=rft.lineNumber(luckyNumber);

// Create a formatted string to display the results to the user
            String resultLuckyNumber = "Your lucky number is: "+luckyNumber;
            String lunckyName="Your lucky character is: "+Character.toUpperCase(firstChar);
            String luckyDay2="Your lucky Day is: "+dayName;
                    //luckyNumber, Character.toUpperCase(firstChar), dayName, getOrdinalSuffix(dayNumber));
            resultLabel.setText(resultLuckyNumber);
            resultLabel_luckyNumber.setText(resultLuckyNumber);
            resultLabel_luckyDay.setText(luckyDay2);
            resultLabel_luckyName.setText(lunckyName);

// Create a formatted string to display the fortune telling results to the user
            String result2=String.format("Your day signifies: %s\n\nYour number signifies: %s\n\nYour Alphabet Signifies: %s",luckyDay,luckyNumberLine,alphabetLucky);
            resultLabel1.setText(result2);


        });

        // Set up scene and show stage
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getOrdinalSuffix(int number) {
        if (number >= 11 && number <= 13) {
            return "th";
        } else {
            switch (number % 10) {
                case 1:
                    return "st";
                case 2:
                    return "nd";
                case 3:
                    return "rd";
                default:
                    return "th";
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static int calculateSum(int dayDate)
    {
        int num = dayDate;
        int sum = 0;

        while (num > 0 || sum > 9) {
            if (num == 0) {
                num = sum;
                sum = 0;
            }
            sum += num % 10;
            num /= 10;
        }

return sum;    }

}
