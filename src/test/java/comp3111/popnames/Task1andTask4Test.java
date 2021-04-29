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

public class Task1andTask4Test extends ApplicationTest {

	private Scene s;
	private TextArea t;
	ObservableList<String> p4AlgorithmChoice = FXCollections.observableArrayList("T4X1","T4X2");

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
	public void task1TextOutput() {
		clickOn("#tabReport1");
		((TextField)s.lookup("#p1YearField")).setText("2019");
		((TextField)s.lookup("#p1NField")).setText("10");
		clickOn("#p1SubmitButton");
		sleep(1000);
		String s1 = t.getText();
		((TextField)s.lookup("#p1YearField")).setText("2019");
		((TextField)s.lookup("#p1NField")).setText("5");
		clickOn("#p1SubmitButton");
		sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}

	@Test
	public void task1TableOutput() {
		clickOn("#tabReport1");
		((TextField)s.lookup("#p1YearField")).setText("2019");
		((TextField)s.lookup("#p1NField")).setText("10");
		clickOn("#p1SubmitButton");
		sleep(1000);
		String result1 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(0)).getCellData(0).toString();
		String result2 = ((TableColumn)((TableView)s.lookup("#outputTable2")).getColumns().get(0)).getCellData(0).toString();
		assertTrue(result1.equals("Liam"));
		assertTrue(result2.equals("Olivia"));
	}

	@Test
	public void task4Algo1Output() {
		clickOn("#tabApp1");
		((TextField)s.lookup("#p4DadName")).setText("test");
		((TextField)s.lookup("#p4MomName")).setText("test");
		((TextField)s.lookup("#p4DadYob")).setText("1984");
		((TextField)s.lookup("#p4MomYob")).setText("2019");
		clickOn("#p4SubmitButton");
		sleep(1000);
		String result1 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(0)).getCellData(0).toString();
		String result2 = ((TableColumn)((TableView)s.lookup("#outputTable2")).getColumns().get(0)).getCellData(0).toString();
		assertTrue(result1.equals("Michael"));
		assertTrue(result2.equals("Olivia"));
	}

	@Test
	public void task4Algo2Output() {
		clickOn("#tabApp1");
		((TextField)s.lookup("#p4DadName")).setText("Jackal");
		((TextField)s.lookup("#p4MomName")).setText("1Zhang");
		((TextField)s.lookup("#p4DadYob")).setText("1984");
		((TextField)s.lookup("#p4MomYob")).setText("2019");
		clickOn("#p4AlgoChoiceBox").clickOn("T4X2");
		sleep(1000);
		clickOn("#p4SubmitButton");
		sleep(1000);
		String result1 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(0)).getCellData(0).toString();
		String result2 = ((TableColumn)((TableView)s.lookup("#outputTable2")).getColumns().get(0)).getCellData(0).toString();
		assertTrue(result1.equals("Joshua"));
		assertTrue(result2.equals("Olivia"));
	}

	@Test
	public void task4Algo2UniqueOutput() {
		clickOn("#tabApp1");
		((TextField)s.lookup("#p4DadName")).setText("test");
		((TextField)s.lookup("#p4MomName")).setText("TEST");
		((TextField)s.lookup("#p4DadYob")).setText("2019");
		((TextField)s.lookup("#p4MomYob")).setText("2019");
		clickOn("#p4AlgoChoiceBox").clickOn("T4X2");
		clickOn("#p4MomYob");
		sleep(100);
		clickOn("#p4TypeChoiceBox").clickOn("Unique");
		clickOn("#p4SubmitButton");
		sleep(1000);
		String result1 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(0)).getCellData(0).toString();
		String result2 = ((TableColumn)((TableView)s.lookup("#outputTable2")).getColumns().get(0)).getCellData(0).toString();
		assertTrue(result1.equals("Tait"));
		assertTrue(result2.equals("Tempest"));
	}

}
