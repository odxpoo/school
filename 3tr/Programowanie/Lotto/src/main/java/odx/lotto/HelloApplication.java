package odx.lotto;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import odx.lotto.database.Data;
import odx.lotto.database.Database;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

public class HelloApplication extends Application {
    Database database = new Database();

    public HelloApplication() throws SQLException { }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Lotto");
        Image icon = new Image(getClass().getResourceAsStream("/odx/lotto/logo.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize() {
        setPotentialAnswers(generateNumbers());
        ErrorMessagePrompt.setVisible(false);
        try {
            displayDatabaseQuery(getDatabaseQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
    private ArrayList<Integer> inputAnswers;
    @FXML
    HBox InputBox = new HBox();
    @FXML
    VBox OutputVBox = new VBox();
    @FXML
    Button InputButton = new Button();
    @FXML
    Label ErrorMessagePrompt = new Label();
    @FXML
    public void resetDatabase() throws SQLException {
        database.executeUpdate("DELETE FROM chances");
        database.executeUpdate("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='chances'");
        displayDatabaseQuery(getDatabaseQuery());
    }
    @FXML
    public void changeButtonColorEnter(MouseEvent event) {
        InputButton.setStyle("-fx-background-color: #733b3b;");
    }
    @FXML
    public void changeButtonColorLeave(MouseEvent event) {
        InputButton.setStyle("-fx-background-color: #543736;");
    }

    @FXML
    public void inputReceive() throws SQLException {
        ObservableList<Node> nodes = InputBox.getChildren();
        inputAnswers = new ArrayList<>();
        try {
            for(Node node : nodes) {
                if(node instanceof TextField) {
                    int a = Integer.parseInt(((TextField) node).getText());
                    if(a > 49 || a < 1) {
                        throw new NumberFormatException();
                    }
                    inputAnswers.add(a);
                }
            }
            sendNewQuery(generateNumbers(), inputAnswers);
            setPotentialAnswers(generateNumbers());
            displayDatabaseQuery(getDatabaseQuery());
            ErrorMessagePrompt.setVisible(false);
        } catch(NumberFormatException e) {
            ErrorMessagePrompt.setVisible(true);
        }
    }
    private ArrayList<Data> getDatabaseQuery() throws SQLException {
        ResultSet resultSet = database.executeQuery("Select * from chances");
        ArrayList<Data> data = new ArrayList<>();
        if(resultSet != null) {
            while(resultSet.next()) {
                data.add(new Data(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        }
        return data;
    }
    private void displayDatabaseQuery(ArrayList<Data> data) {
        if (data != null) {
            OutputVBox.getChildren().clear();
            for (Data info : data) {
                HBox hBox = new HBox();
                Label label1 = new Label();
                label1.setText(info.getGeneratedNumbers());
                label1.setAlignment(Pos.CENTER);
                HBox.setHgrow(label1, Priority.ALWAYS);
                label1.setMaxWidth(Double.MAX_VALUE);
                label1.setFont(new Font(15));
                label1.setTextFill(Color.web("#fac5c5"));
                Label label2 = new Label();
                label2.setText(info.getInputNumbers());
                label2.setAlignment(Pos.CENTER);
                HBox.setHgrow(label2, Priority.ALWAYS);
                label2.setMaxWidth(Double.MAX_VALUE);
                label2.setFont(new Font(15));
                label2.setTextFill(Color.web("#fac5c5"));
                hBox.getChildren().addAll(label1, label2);
                OutputVBox.getChildren().add(hBox);
            }
        }
    }
    private ArrayList<Integer> generateNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        while (numbers.size() < 6) {
            int randomNumber = random.nextInt(49) + 1;
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }
    private void setPotentialAnswers(ArrayList<Integer> numbers) {
        ObservableList<Node> nodes = InputBox.getChildren();
        int i = 0;
        for(Node node : nodes) {
            if(node instanceof TextField) {
                ((TextField) node).setText(String.valueOf(numbers.get(i)));
                i++;
            }
        }
    }
    private void sendNewQuery(ArrayList<Integer> generatedNumbers, ArrayList<Integer> inputNumbers) {
        StringBuilder randNumbersBuilder = new StringBuilder();
        StringBuilder userNumbersBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (i > 0) {
                randNumbersBuilder.append(", ");
                userNumbersBuilder.append(", ");
            }
            randNumbersBuilder.append(generatedNumbers.get(i));
            userNumbersBuilder.append(inputNumbers.get(i));
        }
        String randNumbers = randNumbersBuilder.toString();
        String userNumbers = userNumbersBuilder.toString();
        database.executeUpdate("INSERT INTO chances (GeneratedNumbers, inputNumbers) VALUES ('" + randNumbers + "', '" + userNumbers + "');");
    }
}