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

public class Task3AndTask6Test extends ApplicationTest {
	private Scene s;
	private TextArea t;
	ObservableList<String> task6AlgorithmChoiceBox = FXCollections.observableArrayList("T6X1","T6X2");

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
	public void task3TextOutput() {
		clickOn("#tabReport3");
		((TextField)s.lookup("#task3Year1TextField")).setText("2017");
		((TextField)s.lookup("#task3Year2TextField")).setText("2019");
		((TextField)s.lookup("#task3NameTextField")).setText("Mike");
		clickOn("#task3SubmitButton");
		sleep(1000);
		String s1 = t.getText();
		((TextField)s.lookup("#task3Year1TextField")).setText("2016");
		((TextField)s.lookup("#task3Year2TextField")).setText("2019");
		((TextField)s.lookup("#task3NameTextField")).setText("Mike");
		clickOn("#task3SubmitButton");
		sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void task3TableOutput() {
		clickOn("#tabReport3");
		((TextField)s.lookup("#task3Year1TextField")).setText("2017");
		((TextField)s.lookup("#task3Year2TextField")).setText("2019");
		((TextField)s.lookup("#task3NameTextField")).setText("Mike");
		clickOn("#task3SubmitButton");
		sleep(1000);
		String result1 = ((TableColumn)((TableView)s.lookup("#outputTable1")).getColumns().get(1)).getCellData(1).toString();
		assertTrue(result1.equals("1131"));
	}
	
	@Test
	public void task6Algo1Output() {
		clickOn("#tabApp3");
		((TextField)s.lookup("#task6UserName")).setText("Dylan");
		((TextField)s.lookup("#task6MateName")).setText("Sarah");
		((TextField)s.lookup("#task6SelfYOB")).setText("1999");
		clickOn("#task6SubmitButton");
		sleep(1000);
		String s1 = t.getText();
		((TextField)s.lookup("#task6UserName")).setText("Mike");
		((TextField)s.lookup("#task6MateName")).setText("July");
		((TextField)s.lookup("#task6SelfYOB")).setText("2000");
		clickOn("#task6SubmitButton");
		sleep(1000);
		String s2 = t.getText();	
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void task6Algo2Output() {
		clickOn("#tabApp3");
		((TextField)s.lookup("#task6UserName")).setText("Dylan");
		((TextField)s.lookup("#task6MateName")).setText("Sarah");
		((TextField)s.lookup("#task6SelfYOB")).setText("1999");
		clickOn("#task6AlgorithmChoiceBox").clickOn("T6X2");
		clickOn("#task6SubmitButton");
		sleep(1000);
		String s1 = t.getText();
		((TextField)s.lookup("#task6UserName")).setText("Dylan");
		((TextField)s.lookup("#task6MateName")).setText("Sarah");
		((TextField)s.lookup("#task6SelfYOB")).setText("2000");
		clickOn("#task6SubmitButton");
		sleep(1000);
		String s2 = t.getText();	
		assertTrue(s1.equals(s2));
	}
	
}
