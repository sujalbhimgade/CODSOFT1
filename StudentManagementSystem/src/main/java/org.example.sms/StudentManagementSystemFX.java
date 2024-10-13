package org.example.sms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StudentManagementSystemFX extends Application {

    private final StudentManagementSystem system = new StudentManagementSystem();
    private TextArea displayArea;

    @Override
    public void start(Stage primaryStage) {
        // Set up layout
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setStyle("-fx-padding: 10; -fx-background-color: #ffffff;"); // White background
        root.setAlignment(javafx.geometry.Pos.CENTER); // Center alignment

        // Title
        Label label = new Label("Student Management System");
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333;"); // Title styling

        // Input fields
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label rollLabel = new Label("Roll Number:");
        TextField rollField = new TextField();

        Label gradeLabel = new Label("Grade:");
        TextField gradeField = new TextField();

        Label branchLabel = new Label("Branch:");
        TextField branchField = new TextField();

        Label sectionLabel = new Label("Section:");
        TextField sectionField = new TextField();

        Label yearLabel = new Label("Year:");
        TextField yearField = new TextField();

        Label clearanceLabel = new Label("Clearance Status:");
        CheckBox clearanceCheckBox = new CheckBox("Clear");

        // Buttons in a horizontal box
        HBox buttonBox = new HBox(10); // Spacing of 10
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER); // Center alignment for buttons

        Button addButton = new Button("Add Student");
        Button showButton = new Button("Show Students");
        Button deleteButton = new Button("Delete Student");
        Button searchButton = new Button("Search Student");
        Button editButton = new Button("Edit Student");
        Button exitButton = new Button("Exit");

        // Display area
        displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-padding: 10px;");

        // Button styling
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        showButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        searchButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        editButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        exitButton.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");

        // Add button actions
        addButton.setOnAction(event -> {
            String name = nameField.getText();
            int rollNumber = Integer.parseInt(rollField.getText());
            String grade = gradeField.getText();
            String branch = branchField.getText();
            String section = sectionField.getText();
            int year = Integer.parseInt(yearField.getText());
            String clearanceStatus = clearanceCheckBox.isSelected() ? "Clear" : "Not Clear";

            system.addStudent(new StudentManagementSystem.Student(name, rollNumber, grade, branch, section, year, clearanceStatus));
            nameField.clear();
            rollField.clear();
            gradeField.clear();
            branchField.clear();
            sectionField.clear();
            yearField.clear();
            clearanceCheckBox.setSelected(false);
            displayArea.appendText("Student added successfully!\n");
        });

        showButton.setOnAction(event -> {
            displayArea.setText(system.displayAllStudents());
        });

        deleteButton.setOnAction(event -> {
            int rollNumber = Integer.parseInt(rollField.getText());
            system.removeStudent(rollNumber);
            displayArea.appendText("Student with Roll Number " + rollNumber + " deleted.\n");
            rollField.clear();
        });

        searchButton.setOnAction(event -> {
            try {
                int rollNumber = Integer.parseInt(rollField.getText());
                StudentManagementSystem.Student foundStudent = system.searchStudent(rollNumber);
                if (foundStudent != null) {
                    displayArea.setText("Found: " + foundStudent.toString());
                } else {
                    displayArea.setText("Student not found.");
                }
            } catch (NumberFormatException e) {
                displayArea.setText("Invalid roll number. Please enter a valid number.");
            } finally {
                rollField.clear();
            }
        });

        editButton.setOnAction(event -> {
            try {
                int rollNumber = Integer.parseInt(rollField.getText());
                String name = nameField.getText();
                String grade = gradeField.getText();
                String branch = branchField.getText();
                String section = sectionField.getText();
                int year = Integer.parseInt(yearField.getText());
                String clearanceStatus = clearanceCheckBox.isSelected() ? "Clear" : "Not Clear";

                system.editStudent(rollNumber, new StudentManagementSystem.Student(name, rollNumber, grade, branch, section, year, clearanceStatus));
                displayArea.appendText("Student with Roll Number " + rollNumber + " updated.\n");
                rollField.clear();
            } catch (NumberFormatException e) {
                displayArea.setText("Invalid input. Please check the values.");
            }
        });

        exitButton.setOnAction(event -> {
            System.exit(0);
        });

        // Add components to layout
        root.add(label, 0, 0, 2, 1);
        root.add(nameLabel, 0, 1);
        root.add(nameField, 1, 1);
        root.add(rollLabel, 0, 2);
        root.add(rollField, 1, 2);
        root.add(gradeLabel, 0, 3);
        root.add(gradeField, 1, 3);
        root.add(branchLabel, 0, 4);
        root.add(branchField, 1, 4);
        root.add(sectionLabel, 0, 5);
        root.add(sectionField, 1, 5);
        root.add(yearLabel, 0, 6);
        root.add(yearField, 1, 6);
        root.add(clearanceLabel, 0, 7);
        root.add(clearanceCheckBox, 1, 7);

        // Add buttons to buttonBox
        buttonBox.getChildren().addAll(addButton, showButton, deleteButton, searchButton, editButton, exitButton);

        root.add(buttonBox, 0, 8, 2, 1); // Add button box to grid
        root.add(displayArea, 0, 9, 2, 1); // Adjust position of display area

        // Set up the stage
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package org.example.sms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StudentManagementSystemFX extends Application {

    private final StudentManagementSystem system = new StudentManagementSystem();
    private TextArea displayArea;

    @Override
    public void start(Stage primaryStage) {
        // Set up layout
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setStyle("-fx-padding: 10; -fx-background-color: #ffffff;"); // White background
        root.setAlignment(javafx.geometry.Pos.CENTER); // Center alignment

        // Title
        Label label = new Label("Student Management System");
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333;"); // Title styling

        // Input fields
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label rollLabel = new Label("Roll Number:");
        TextField rollField = new TextField();

        Label gradeLabel = new Label("Grade:");
        TextField gradeField = new TextField();

        Label branchLabel = new Label("Branch:");
        TextField branchField = new TextField();

        Label sectionLabel = new Label("Section:");
        TextField sectionField = new TextField();

        Label yearLabel = new Label("Year:");
        TextField yearField = new TextField();

        Label clearanceLabel = new Label("Clearance Status:");
        CheckBox clearanceCheckBox = new CheckBox("Clear");

        // Buttons in a horizontal box
        HBox buttonBox = new HBox(10); // Spacing of 10
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER); // Center alignment for buttons

        Button addButton = new Button("Add Student");
        Button showButton = new Button("Show Students");
        Button deleteButton = new Button("Delete Student");
        Button searchButton = new Button("Search Student");
        Button editButton = new Button("Edit Student");
        Button exitButton = new Button("Exit");

        // Display area
        displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-padding: 10px;");

        // Button styling
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        showButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        searchButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        editButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        exitButton.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");

        // Add button actions
        addButton.setOnAction(event -> {
            String name = nameField.getText();
            int rollNumber = Integer.parseInt(rollField.getText());
            String grade = gradeField.getText();
            String branch = branchField.getText();
            String section = sectionField.getText();
            int year = Integer.parseInt(yearField.getText());
            String clearanceStatus = clearanceCheckBox.isSelected() ? "Clear" : "Not Clear";

            system.addStudent(new StudentManagementSystem.Student(name, rollNumber, grade, branch, section, year, clearanceStatus));
            nameField.clear();
            rollField.clear();
            gradeField.clear();
            branchField.clear();
            sectionField.clear();
            yearField.clear();
            clearanceCheckBox.setSelected(false);
            displayArea.appendText("Student added successfully!\n");
        });

        showButton.setOnAction(event -> {
            displayArea.setText(system.displayAllStudents());
        });

        deleteButton.setOnAction(event -> {
            int rollNumber = Integer.parseInt(rollField.getText());
            system.removeStudent(rollNumber);
            displayArea.appendText("Student with Roll Number " + rollNumber + " deleted.\n");
            rollField.clear();
        });

        searchButton.setOnAction(event -> {
            try {
                int rollNumber = Integer.parseInt(rollField.getText());
                StudentManagementSystem.Student foundStudent = system.searchStudent(rollNumber);
                if (foundStudent != null) {
                    displayArea.setText("Found: " + foundStudent.toString());
                } else {
                    displayArea.setText("Student not found.");
                }
            } catch (NumberFormatException e) {
                displayArea.setText("Invalid roll number. Please enter a valid number.");
            } finally {
                rollField.clear();
            }
        });

        editButton.setOnAction(event -> {
            try {
                int rollNumber = Integer.parseInt(rollField.getText());
                String name = nameField.getText();
                String grade = gradeField.getText();
                String branch = branchField.getText();
                String section = sectionField.getText();
                int year = Integer.parseInt(yearField.getText());
                String clearanceStatus = clearanceCheckBox.isSelected() ? "Clear" : "Not Clear";

                system.editStudent(rollNumber, new StudentManagementSystem.Student(name, rollNumber, grade, branch, section, year, clearanceStatus));
                displayArea.appendText("Student with Roll Number " + rollNumber + " updated.\n");
                rollField.clear();
            } catch (NumberFormatException e) {
                displayArea.setText("Invalid input. Please check the values.");
            }
        });

        exitButton.setOnAction(event -> {
            System.exit(0);
        });

        // Add components to layout
        root.add(label, 0, 0, 2, 1);
        root.add(nameLabel, 0, 1);
        root.add(nameField, 1, 1);
        root.add(rollLabel, 0, 2);
        root.add(rollField, 1, 2);
        root.add(gradeLabel, 0, 3);
        root.add(gradeField, 1, 3);
        root.add(branchLabel, 0, 4);
        root.add(branchField, 1, 4);
        root.add(sectionLabel, 0, 5);
        root.add(sectionField, 1, 5);
        root.add(yearLabel, 0, 6);
        root.add(yearField, 1, 6);
        root.add(clearanceLabel, 0, 7);
        root.add(clearanceCheckBox, 1, 7);

        // Add buttons to buttonBox
        buttonBox.getChildren().addAll(addButton, showButton, deleteButton, searchButton, editButton, exitButton);

        root.add(buttonBox, 0, 8, 2, 1); // Add button box to grid
        root.add(displayArea, 0, 9, 2, 1); // Adjust position of display area

        // Set up the stage
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
