package comp3111.popnames;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Task2andTask5Test extends ApplicationTest {

    private Scene s;
    private TextArea t;
    ObservableList<String> genderChoice = FXCollections.observableArrayList("M", "F");
    ObservableList<String> ageChoice2 = FXCollections.observableArrayList("Younger","Older");
    ObservableList<String> p5AlgorithmChoice = FXCollections.observableArrayList("T5X1","T5X2");


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui.fxml"));
        VBox root = (VBox) loader.load();
        Scene scene =  new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Popular Names");
        stage.show();
        s = scene;
        t = (TextArea)s.lookup("#textAreaConsole");
    }

    @Test
    public void task2TextOutput() {
        clickOn("#tabReport2");
        ((TextField)s.lookup("#task2Year1TextField")).setText("2000");
        ((TextField)s.lookup("#task2Year2TextField")).setText("2010");
        ((TextField)s.lookup("#task2KTextField")).setText("10");
        clickOn("#task2SubmitButton");
        sleep(1000);
        String s1 = t.getText();

        clickOn("#tabReport2");
        ((TextField)s.lookup("#task2Year1TextField")).setText("2000");
        ((TextField)s.lookup("#task2Year2TextField")).setText("2010");
        ((TextField)s.lookup("#task2KTextField")).setText("10");
        clickOn("#task2SubmitButton");
        sleep(1000);
        String s2 = t.getText();

        assertTrue(s1.equals(s2));
    }

    @Test
    public void task2TableOutput() {
        clickOn("#tabReport2");
        ((TextField)s.lookup("#task2Year1TextField")).setText("2000");
        ((TextField)s.lookup("#task2Year2TextField")).setText("2010");
        ((TextField)s.lookup("#task2KTextField")).setText("10");
        clickOn("#task2SubmitButton");
        sleep(1000);

        String result1 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(0)).getCellData(0).toString();
        String result2 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(1)).getCellData(0).toString();
        String result3 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(0)).getCellData(1).toString();
        String result4 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(1)).getCellData(1).toString();

        assertTrue(result1.equals("Christopher"));
        assertTrue(result2.equals("3"));
        assertTrue(result3.equals("William"));
        assertTrue(result4.equals("2"));
    }

    @Test
    public void task5Algo1Output() {
        clickOn("#tabApp2");
        ((TextField)s.lookup("#task5iNameTextField")).setText("Louis");
        ((TextField)s.lookup("#task5iAgeTextField")).setText("2000");
        clickOn("#task5SubmitButton");
        sleep(1000);

        String result1 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(0)).getCellData(0).toString();
        String result2 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(1)).getCellData(0).toString();
        assertTrue(result1.equals("Jacob"));
        assertTrue(result2.equals("34465"));
    }

    @Test
    public void task5Algo2Output() {
        clickOn("#tabApp2");
        ((TextField)s.lookup("#task5iNameTextField")).setText("Louis");
        ((TextField)s.lookup("#task5iAgeTextField")).setText("2000");
        clickOn("#task5AlgorithmChoiceBox").clickOn("T5X2");
        sleep(1000);
        clickOn("#task5SubmitButton");
        sleep(40000);
        String result1 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(0)).getCellData(0).toString();
        String result2 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(0)).getCellData(1).toString();
        assertTrue(result1.equals("Jimmy"));
        assertTrue(result2.equals("Albert"));
    }



}
