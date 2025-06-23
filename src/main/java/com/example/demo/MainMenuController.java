package com.example.demo;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.*;

import com.example.demo.models.Note;
import com.example.demo.models.WindowManager;
import com.example.demo.models.database.DBHandler;
import com.example.demo.models.database.Test;
import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


public class MainMenuController {


    //Инициализация элементов



    @FXML
    private GridPane calendar;

    @FXML
    private Button deleteButton;

    @FXML
    private Button logOut;

    @FXML
    private HBox menuBox;

    @FXML
    private Label month_label;

    @FXML
    private Button nextMonth;

    @FXML
    private TextArea noteArea;

    @FXML
    private Button prevMonth;

    @FXML
    private Button saveButton;

    @FXML
    private VBox schedule;

    @FXML
    private VBox tests;

    @FXML
    private AnchorPane window;

    @FXML
    private Label year_label;

    //Инициализация элементов
    private LocalDate currentDate;
    protected static final String fxmlPath = "/main-menu-form.fxml";
    protected final static int fxmlWidth = 1280;
    protected final static int fxmlHeight = 700;
    Map<String, String> dictionary = new HashMap<>(12);

    @FXML
    void initialize() {
        currentDate = LocalDate.now();

        calendar.setGridLinesVisible(true);
        drawCalendar(currentDate.withDayOfMonth(1).getDayOfWeek().getValue());
        updateLabel();
        selectDate(currentDate.getDayOfMonth());
        today(currentDate.getDayOfMonth(), _day);
        toggleVBox(0);
        dictionaryFill();
        translate();
    }

    private void dictionaryFill() {
        dictionary.put("January".toUpperCase(), "Январь");
        dictionary.put("February".toUpperCase(), "Февраль");
        dictionary.put("March".toUpperCase(), "Март");
        dictionary.put("April".toUpperCase(), "Апрель");
        dictionary.put("May".toUpperCase(), "Май");
        dictionary.put("June".toUpperCase(), "Июнь");
        dictionary.put("July".toUpperCase(), "Июль");
        dictionary.put("August".toUpperCase(), "Август");
        dictionary.put("September".toUpperCase(), "Сентябрь");
        dictionary.put("October".toUpperCase(), "Октябрь");
        dictionary.put("November".toUpperCase(), "Ноябрь");
        dictionary.put("December".toUpperCase(), "Декабрь");
    }

    public void translate() {
        String eng = month_label.getText();
        String rus = dictionary.getOrDefault(eng, "Месяц");
        month_label.setText(rus);
    }


    //Верхние кнопки
    private void toggleVBox(int vbox) {
        Node[] views = new Node[] { schedule, tests };
        for (int i = 0; i < views.length; i++) {
            views[i].setVisible(i == vbox);
            views[i].setManaged(i == vbox);
        }
    }
    public void scheduleClick(MouseEvent event) {
        toggleVBox(0);
        translate();
    }

    public void testsClick(MouseEvent event) throws SQLException {
        toggleVBox(1);
        tests.getChildren().clear();
        testsLoad();
    }


    public void testsLoad() {
        DBHandler dbh = new DBHandler();
        List<Test> testList = dbh.loadTestsFromDB();

        for(Test t: testList) {
            VBox test1 = new VBox(5);
            test1.setStyle("-fx-margin-top: 15;");
            test1.setPadding(new Insets(15));
            test1.setAlignment(Pos.CENTER);
            test1.setFillWidth(true);

            Label testTextLabel = new Label(t.getText());
            testTextLabel.setId("testText");

            HBox answerBox = getHBox(t);

            test1.getChildren().add(testTextLabel);
            test1.getChildren().add(answerBox);
            tests.getChildren().add(test1);
        }
    }
    private HBox getHBox(Test t) {
        Button btnCorrect = new Button(t.getCorrect());
        Button btnWrong1 = new Button(t.getWrong1());
        Button btnWrong2 = new Button(t.getWrong2());
        btnCorrect.setOnMouseClicked(event -> {
            btnCorrect.setStyle("-fx-background-color: green");
            btnWrong1.setStyle("-fx-background-color: #0d3436");
            btnWrong2.setStyle("-fx-background-color: #0d3436");
        });

        btnWrong1.setOnMouseClicked(event -> {
            btnCorrect.setStyle("-fx-background-color: #0d3436");
            btnWrong1.setStyle("-fx-background-color: red");
            btnWrong2.setStyle("-fx-background-color: #0d3436");
        });

        btnWrong2.setOnMouseClicked(event -> {
            btnCorrect.setStyle("-fx-background-color: #0d3436");
            btnWrong1.setStyle("-fx-background-color: #0d3436");
            btnWrong2.setStyle("-fx-background-color: red");
        });

        HBox answerBox = new HBox(10, btnCorrect, btnWrong1, btnWrong2);
        answerBox.setAlignment(Pos.CENTER);
        return answerBox;
    }


    public void logOutClick(MouseEvent event) {
        logOut.getScene().getWindow().hide();

        WindowManager ws = new WindowManager();
        ws.window_switch(FirstFormController.fxmlPath, FirstFormController.fxmlHeight, FirstFormController.fxmlWidth);
    }
    //Верхние кнопки


    //Расписание

    private void updateLabel() {
        month_label.setText(currentDate.getMonth().toString());
        translate();
        year_label.setText(String.valueOf(currentDate.getYear()));
    }

    private int _day;
    private void drawCalendar(int firstDayOfWeek) {
        calendar.getChildren().clear();

        int daysInMonth = currentDate.lengthOfMonth();


        for(int i = 1; i <= daysInMonth; i++) {
            int row = (i + firstDayOfWeek - 2) / 7;
            int column = (i + firstDayOfWeek - 2) % 7;

            Button dayButton = new Button(String.valueOf(i));
            int finalI = i;
            dayButton.setOnMouseClicked(e -> selectDate(finalI));
            dayButton.setStyle("-fx-alignment: center;");
            dayButton.setStyle("-fx-font-size: 35");
            dates_load(dayButton, finalI);
            calendar.add(dayButton, column, row);
            today(currentDate.getDayOfMonth(), finalI);
        }
        dictionaryFill();
    }

    protected void today(int day, int value) {
        if (String.valueOf(value).equals(currentDate.getDayOfMonth()) && month_label.getText().equals(currentDate.getMonth().toString())) {
            calendar.getChildren().get(day-1).setStyle(
                    "-fx-text-fill: white;" +
                            "-fx-font-size: 25;" +
                            "-fx-background-color: #0d3436;" +
                            "-fx-border-radius: 100;" +
                            "-fx-border-color: white;" +
                            "-fx-border-width: 5");
        }


    }

    public void dates_load(Button button, int day) {
        LocalDate selectedDate = currentDate.withDayOfMonth(day);
        File file = new File("src/main/resources/static/" + selectedDate + ".json");
        if(file.exists()) {
            button.setStyle(
                    "-fx-background-color: green; " +
                            "-fx-font-size: 35; " +
                            "-fx-font-weight: bold; " +
                            "-fx-background-radius: 100");
        }
        else {
            noteArea.clear();
        }
    }

    protected void selectDate(int day) {
        _day = day;
        LocalDate selectedDate = currentDate.withDayOfMonth(day);
        int firstDayOfWeek = currentDate.withDayOfMonth(1).getDayOfWeek().getValue();
        calendar.getChildren().clear();
        drawCalendar(firstDayOfWeek);
        calendar.getChildren().get(selectedDate.getDayOfMonth() - 1).setStyle(
                "-fx-text-fill: #0d3436;" +
                        "-fx-font-size: 35;" +
                        "-fx-background-color: white");

        Gson gson = new Gson();
        try(FileReader reader = new FileReader("src/main/resources/static/" + selectedDate + ".json")){
            Note note = gson.fromJson(reader, Note.class);
            noteArea.setText(note.noteText);
        } catch(IOException e) {
            noteArea.clear();
        }
    }

    @FXML
    private void updateTests(MouseEvent event) throws SQLException {
        testsClick(event);
    }

    protected int dayGap;
    @FXML
    private void nextMonthClick(MouseEvent event) {
        dayGap += 1;
        currentDate = LocalDate.now().plusMonths(dayGap);
        int firstDayOfWeek = currentDate.withDayOfMonth(1).getDayOfWeek().getValue();
        updateLabel();
        drawCalendar(firstDayOfWeek);
    }


    @FXML
    private void prevMonthClick(MouseEvent event) {
        dayGap -= 1;
        currentDate = LocalDate.now().plusMonths(dayGap);
        int firstDayOfWeek = currentDate.withDayOfMonth(1).getDayOfWeek().getValue();
        updateLabel();
        drawCalendar(firstDayOfWeek);
    }

    @FXML
    private void saveButtonClick(MouseEvent event) {
        Note note = new Note();
        note.dateSet(currentDate.withDayOfMonth(_day).toString());

        note.setNoteText(noteArea.getText());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonString = gson.toJson(note);
        File saveFile = new File("src/main/resources/static/" + note.getDate() + ".json");
        if (!noteArea.getText().isEmpty()) {
            try(FileWriter writer = new FileWriter(saveFile)) {
                writer.write(jsonString);
                showAlert(Alert.AlertType.INFORMATION, "Успешно сохранено!", "Файл сохранён: " + saveFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            showAlert(Alert.AlertType.ERROR, "Ой!", "Заметка не может быть пустой.");
        }
    }

    @FXML
    private void deleteButtonClick(MouseEvent event) {
        LocalDate selectedDate = currentDate.withDayOfMonth(_day);
        File file = new File("src/main/resources/static/" + selectedDate + ".json");
        if(file.exists()) {
            boolean deleted = file.delete();
            if(deleted) {
                showAlert(Alert.AlertType.INFORMATION, "Заметка удалена!", "Файл успешно удален: " + file);
                noteArea.clear();
            }
            else {
                showAlert(Alert.AlertType.ERROR, "Ошибка!", "Не удалось удалить файл: " + file);
            }
        }
        else {
            showAlert(Alert.AlertType.WARNING, "Файл не найден", "Файл не существеут: " + file);
        }
    }

    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    //Расписание
}