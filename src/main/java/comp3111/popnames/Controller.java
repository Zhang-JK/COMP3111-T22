/**
 * Building on the sample skeleton for 'ui.fxml' Controller Class generated by SceneBuilder
 */
package comp3111.popnames;

import comp3111.popnames.core.ChartSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Controller {

    ObservableList<String> genderChoice = FXCollections.observableArrayList("M","F");
    ObservableList<String> ageChoice = FXCollections.observableArrayList("Young","Old");
    ObservableList<String> p5AlgorithmChoice = FXCollections.observableArrayList("T5X1","T5X2");
    ObservableList<String> p4AlgorithmChoice = FXCollections.observableArrayList("T4X1","T4X2");
    ObservableList<String> p4TypeChoice = FXCollections.observableArrayList("Popular","Unique");

    @FXML
    private Tab tabTaskZero;

    @FXML
    private TextField textfieldNameF;

    @FXML
    private TextField textfieldYear;

    @FXML
    private Button buttonRankM;

    @FXML
    private TextField textfieldNameM;

    @FXML
    private Button buttonRankF;

    @FXML
    private Button buttonTopM;

    @FXML
    private Button buttonTopF;

    @FXML
    private Button buttonSummary;

    @FXML
    private Tab tabReport1;

    @FXML
    private TextArea p1DescriputionBox;

    @FXML
    private TextField p1YearField;

    @FXML
    private TextField p1NField;

    @FXML
    private Button p1ResetButton;

    @FXML
    private Button p1SubmitButton;

    @FXML
    private Label p1YearErrorLabel;

    @FXML
    private Label p1NErrorLabel;

    @FXML
    private ToggleGroup T1;

    @FXML
    private Tab tabReport2;

    @FXML
    private ToggleGroup T11;

    @FXML
    private TextArea task2IntroBox;

    @FXML
    private TextField task2Year1TextField;

    @FXML
    private TextField task2Year2TextField;

    @FXML
    private TextField task2KTextField;

    @FXML
    private Label task2Year1ErrorLabel;

    @FXML
    private Label task2Year2ErrorLabel;

    @FXML
    private Label task2KErrorLabel;

    @FXML
    private ChoiceBox task2GenderChoiceBox;

    @FXML
    private Button task2SubmitButton;

    @FXML
    private Button task2ResetButton;

    @FXML
    private Tab tabReport3;

    @FXML
    private ToggleGroup T111;
    
    @FXML
    private TextArea task3IntroBox;

    @FXML
    private TextField task3Year1TextField;

    @FXML
    private TextField task3Year2TextField;

    @FXML
    private TextField task3NameTextField;

    @FXML
    private Label task3Year1ErrorLabel;

    @FXML
    private Label task3Year2ErrorLabel;

    @FXML
    private Label task3NameErrorLabel;

    @FXML
    private ChoiceBox task3GenderChoiceBox;

    @FXML
    private Button task3SubmitButton;

    @FXML
    private Button task3ResetButton;

    @FXML
    private Tab tabApp1;

    @FXML
    private Tab tabApp2;

    @FXML
    private TextArea task5IntroBox;

    @FXML
    private TextField task5iNameTextField;

    @FXML
    private ChoiceBox task5iGenderChoiceBox;

    @FXML
    private TextField task5iAgeTextField;

    @FXML
    private ChoiceBox task5iGenderMateChoiceBox;

    @FXML
    private ChoiceBox task5iPreferenceChoiceBox;

    @FXML
    private ChoiceBox task5AlgorithmChoiceBox;

    @FXML
    private Button task5SubmitButton;

    @FXML
    private Label task5iNameErrorLabel;

    @FXML
    private Label task5iAgeErrorLabel;

    @FXML
    private Button task5ResetButton;

    @FXML
    private Tab tabApp3;

    @FXML
    private TextArea textAreaConsole;

    @FXML
    private TableView<?> outputTable;

    @FXML
    private BarChart<String, Number> outputBarChart1;

    @FXML
    private BarChart<String, Number> outputBarChart2;

    @FXML
    private PieChart outputPieChart1;

    @FXML
    private PieChart outputPieChart2;

    @FXML
    private LineChart<Integer, Integer> outputLineChart1;

    @FXML
    private LineChart<Integer, Integer> outputLineChart2;

    @FXML
    private Button summaryButton;

    @FXML
    private Button tableButton;

    @FXML
    private Button barButton;

    @FXML
    private Button pieButton;

    @FXML
    private Button lineButton;

    @FXML
    private AnchorPane outputPanel;

    @FXML
    private SplitPane splitWindow;

    @FXML
    private TextField p4DadName;

    @FXML
    private TextField p4MomName;

    @FXML
    private TextField p4DadYob;

    @FXML
    private TextField p4MomYob;

    @FXML
    private ChoiceBox<String> p4AlgoChoiceBox;

    @FXML
    private Label p4DadNameError;

    @FXML
    private Label p4MomNameError;

    @FXML
    private Label p4DadYobError;

    @FXML
    private Label p4MomYobError;

    @FXML
    private Label p4AlgoError;

    @FXML
    private Button p4SubmitButton;

    @FXML
    private Button p4ResetButton;

    @FXML
    private Label p4TypeLabel;

    @FXML
    private ChoiceBox<String> p4TypeChoiceBox;

    // ----------------------------- Task0 Functions ------------------------//

    /**
     * Task Zero
     * To be triggered by the "Summary" button on the Task Zero Tab
     */
    @FXML
    void doSummary() {
        int year = Integer.parseInt(textfieldYear.getText());
        String oReport = AnalyzeNames.getSummary(year);
        textAreaConsole.setText(oReport);
    }


    /**
     * Task Zero
     * To be triggered by the "Rank (female)" button on the Task Zero Tab
     */
    @FXML
    void doRankF() {
        String oReport = "";
        String iNameF = textfieldNameF.getText();
        int iYear = Integer.parseInt(textfieldYear.getText());
        int oRank = AnalyzeNames.getRank(iYear, iNameF, "F");
        if (oRank == -1)
            oReport = String.format("The name %s (female) has not been ranked in the year %d.\n", iNameF, iYear);
        else
            oReport = String.format("Rank of %s (female) in year %d is #%d.\n", iNameF, iYear, oRank);
        textAreaConsole.setText(oReport);
    }


    /**
     * Task Zero
     * To be triggered by the "Rank (male)" button on the Task Zero Tab
     */
    @FXML
    void doRankM() {
        String oReport = "";
        String iNameM = textfieldNameM.getText();
        int iYear = Integer.parseInt(textfieldYear.getText());
        int oRank = AnalyzeNames.getRank(iYear, iNameM, "M");
        if (oRank == -1)
            oReport = String.format("The name %s (male) has not been ranked in the year %d.\n", iNameM, iYear);
        else
            oReport = String.format("Rank of %s (male) in year %d is #%d.\n", iNameM, iYear, oRank);
        textAreaConsole.setText(oReport);
    }


    /**
     * Task Zero
     * To be triggered by the "Top 5 (female)" button on the Task Zero Tab
     */
    @FXML
    void doTopF() {
        String oReport = "";
        final int topN = 5;
        int iYear = Integer.parseInt(textfieldYear.getText());
        oReport = String.format("Top %d most popular names (female) in the year %d:\n", topN, iYear);
        for (int i = 1; i <= topN; i++)
            oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "F"));
        textAreaConsole.setText(oReport);
    }


    /**
     * Task Zero
     * To be triggered by the "Top 5 (male)" button on the Task Zero Tab
     */
    @FXML
    void doTopM() {
        String oReport = "";
        final int topN = 5;
        int iYear = Integer.parseInt(textfieldYear.getText());
        oReport = String.format("Top %d most popular names (male) in the year %d:\n", topN, iYear);
        for (int i = 1; i <= topN; i++)
            oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "M"));
        textAreaConsole.setText(oReport);
    }



    // ----------------------------- Initialization of UI ------------------------//
    /**
     *  Initialize the content of all the ChoiceBox
     *
     */
    @FXML
    void initialize() {
        // ----------------------- General UI Initialization ------------------------//
        summaryButton.setVisible(true);
        tableButton.setVisible(false);
        barButton.setVisible(false);
        pieButton.setVisible(false);
        lineButton.setVisible(false);
        selectedTask = 0;
        // -------------------------- Task2 Initialization ------------------------//
        task2GenderChoiceBox.setValue("M");
        task2GenderChoiceBox.setItems(genderChoice);
        // -------------------------- Task3 Initialization ------------------------//
        task3GenderChoiceBox.setValue("M");
        task3GenderChoiceBox.setItems(genderChoice);
        // -------------------------- Task4 Initialization ------------------------//
        p4AlgoChoiceBox.setItems(p4AlgorithmChoice);
        p4AlgoChoiceBox.setValue(p4AlgorithmChoice.get(0));
        p4TypeChoiceBox.setItems(p4TypeChoice);
        p4TypeChoiceBox.setValue(p4TypeChoice.get(0));
        // -------------------------- Task5 Initialization ------------------------//
        task5iGenderChoiceBox.setValue("M");
        task5iGenderChoiceBox.setItems(genderChoice);
        task5iGenderMateChoiceBox.setValue("M");
        task5iGenderMateChoiceBox.setItems(genderChoice);
        task5iPreferenceChoiceBox.setValue("Young");
        task5iPreferenceChoiceBox.setItems(ageChoice);
        task5AlgorithmChoiceBox.setValue("T5X1");
        task5AlgorithmChoiceBox.setItems(p5AlgorithmChoice);
    }



    // ----------------------------- General Terminal UI Interface ------------------------//

    /**
     * Used to record which task is in use
     */
    int selectedTask = 0;

    /**
     * Used to record which button is selected
     */
    Button selectedButton = null;

    /**
     * switch between different buttons and set up UI
     * @param nowSelected the current selected button
     */
    void switchButton(Button nowSelected) {
        if(selectedButton == null) selectedButton = summaryButton;
        if(nowSelected == selectedButton) return;
        selectedButton.setDefaultButton(false);
        VBox.setMargin(selectedButton, new Insets(0, 0, 5, 10));
        nowSelected.setDefaultButton(true);
        VBox.setMargin(nowSelected, new Insets(0, 0, 5, 0));
        selectedButton = nowSelected;
    }

    /**
     * set all charts to invisible, used when switching buttons
     */
    void setAllChartsInvisible() {
        for(Node node : outputPanel.getChildren()) {
            node.setVisible(false);
        }
//        textAreaConsole.setVisible(true);
    }

    /**
     * clean all the user input
     * please add your input text box here
     */
    void clearAllInputBox() {
        // task1
        p1YearField.clear();
        p1YearErrorLabel.setVisible(false);
        p1NField.clear();
        p1NErrorLabel.setVisible(false);
        // task2
        // task3
        // task4
        p4AlgoChoiceBox.setValue(p4AlgorithmChoice.get(0));
        p4DadName.clear();
        p4DadYob.clear();
        p4MomName.clear();
        p4MomYob.clear();
        p4AlgoError.setVisible(false);
        p4DadNameError.setVisible(false);
        p4DadYobError.setVisible(false);
        p4MomNameError.setVisible(false);
        p4MomYobError.setVisible(false);
        p4TypeChoiceBox.setVisible(false);
        p4TypeLabel.setVisible(false);
        // task5
        // task6
    }

    /**
     * clean the console, used when switch between tabs(tasks)
     */
    void clearAllCharts() {
        textAreaConsole.clear();
        outputTable.getColumns().clear();
        outputBarChart1.getData().clear();
        outputBarChart2.getData().clear();
        outputPieChart1.getData().clear();
        outputPieChart2.getData().clear();
        outputLineChart1.getData().clear();
        outputLineChart2.getData().clear();
        setAllChartsInvisible();
        textAreaConsole.setVisible(true);
        switchButton(summaryButton);
    }

    /**
     * when summary is clicked
     */
    @FXML
    void clickSummary() {
        switchButton(summaryButton);
        setAllChartsInvisible();
        textAreaConsole.setVisible(true);
    }

    /**
     * when table is clicked
     */
    @FXML
    void clickTable() {
        switchButton(tableButton);
        setAllChartsInvisible();
        if(outputTable.getColumns().size() != 0)
            outputTable.setVisible(true);
    }

    /**
     * when bar chart is clicked
     */
    @FXML
    void clickBar() {
        switchButton(barButton);
        setAllChartsInvisible();
        if(outputBarChart1.getData().size() != 0)
            if(selectedTask == 1 || selectedTask == 4) {
                outputBarChart1.setVisible(true);
                outputBarChart1.setPrefWidth(outputPanel.getWidth()/2);
                outputBarChart2.setVisible(true);
                outputBarChart2.setPrefWidth(outputPanel.getWidth()/2);
            }
            else {
                outputBarChart1.setVisible(true);
                outputBarChart1.setPrefWidth(outputPanel.getWidth());
            }
    }

    /**
     * when pie chart is clicked
     */
    @FXML
    void clickPie() {
        switchButton(pieButton);
        setAllChartsInvisible();
        if(outputPieChart1.getData().size() != 0)
            if(selectedTask == 1 || selectedTask == 4) {
                outputPieChart1.setVisible(true);
                outputPieChart1.setPrefWidth(outputPanel.getWidth()/2);
                outputPieChart2.setVisible(true);
                outputPieChart2.setPrefWidth(outputPanel.getWidth()/2);
            }
            else {
                outputPieChart1.setVisible(true);
                outputPieChart1.setPrefWidth(outputPanel.getWidth());
            }
    }

    /**
     * when line chart is clicked
     */
    @FXML
    void clickLine() {
        switchButton(lineButton);
        setAllChartsInvisible();
        if(outputLineChart1.getData().size() != 0)
            if(selectedTask == 1 || selectedTask == 4) {
                outputLineChart1.setVisible(true);
                outputLineChart1.setPrefWidth(outputPanel.getWidth()/2);
                outputLineChart2.setVisible(true);
                outputLineChart2.setPrefWidth(outputPanel.getWidth()/2);
            }
            else {
                outputLineChart1.setVisible(true);
                outputLineChart1.setPrefWidth(outputPanel.getWidth());
            }
    }

    /**
     * prevent drag split panel
     */
    @FXML
    void dragSplitWindow() {
        splitWindow.setDividerPosition(0,0.135);
    }

    /**
     * when switch to tab0
     */
    @FXML
    void tab0Selected() {
        if(textAreaConsole == null) return;
        clearAllInputBox();
        clearAllCharts();
        summaryButton.setVisible(true);
        tableButton.setVisible(false);
        barButton.setVisible(false);
        pieButton.setVisible(false);
        lineButton.setVisible(false);
        selectedTask = 0;
    }

    /**
     * when switch to tab1
     */
    @FXML
    void tab1Selected() {
        clearAllInputBox();
        clearAllCharts();
        summaryButton.setVisible(true);
        tableButton.setVisible(true);
        barButton.setVisible(true);
        pieButton.setVisible(true);
        lineButton.setVisible(false);
        selectedTask = 1;
    }

    /**
     * when switch to tab2
     */
    @FXML
    void tab2Selected() {

        selectedTask = 2;
    }

    /**
     * when switch to tab3
     */
    @FXML
    void tab3Selected() {

        selectedTask = 3;
    }

    /**
     * when switch to tab4
     */
    @FXML
    void tab4Selected() {
        clearAllCharts();
        summaryButton.setVisible(true);
        tableButton.setVisible(true);
        barButton.setVisible(true);
        pieButton.setVisible(false);
        lineButton.setVisible(false);
        selectedTask = 4;
    }

    /**
     * when switch to tab5
     */
    @FXML
    void tab5Selected() {

        selectedTask = 5;
    }

    /**
     * when switch to tab6
     */
    @FXML
    void tab6Selected() {

        selectedTask = 6;
    }

    // ----------------------------- Task1 Function ------------------------//

    /**
     * record if there's any error in user's input
     */
    boolean hasErrorTask1 = false;

    /**
     * check year input
     */
    @FXML
    void doP1YearCheck() {
        String yearFieldText = p1YearField.getText();
        boolean hasError = false;
        if (yearFieldText.isBlank()) {
            p1YearErrorLabel.setText("Please Enter a Year Number");
            hasError = true;
        }
        else if (!StringUtils.isNumeric(yearFieldText) || Integer.parseInt(yearFieldText) > 2019 || Integer.parseInt(yearFieldText) < 1880) {
            p1YearErrorLabel.setText("Please Enter a Number Between 1880 and 2019");
            hasError = true;
        }
        p1YearErrorLabel.setVisible(hasError);
        hasErrorTask1 = hasError;
    }

    /**
     * check n input
     */
    @FXML
    void doP1NCheck() {
        String nFieldText = p1NField.getText();
        boolean hasError = false;
        if (nFieldText.isBlank()) {
            p1NErrorLabel.setText("Please Enter an N number");
            hasError = true;
        }
        else if (!StringUtils.isNumeric(nFieldText) || Integer.parseInt(nFieldText) > 10 || Integer.parseInt(nFieldText) < 1) {
            p1NErrorLabel.setText("Please Enter a Number Between 1 and 10");
            hasError = true;
        }
        p1NErrorLabel.setVisible(hasError);
        hasErrorTask1 = hasError;
    }

    /**
     * when click the submit button, check data and output result
     */
    @FXML
    void doP1Submit() {
        doP1YearCheck();
        doP1NCheck();
        if(hasErrorTask1) return;

        String yearFieldText = p1YearField.getText();
        String nFieldText = p1NField.getText();

        MostPopularNames task1 = new MostPopularNames();
        if(!task1.setData(Integer.parseInt(yearFieldText), Integer.parseInt(nFieldText)))
            return;

        textAreaConsole.setText(task1.getSummary());
        ChartSetter.BarChartSetter(outputBarChart1, "Male", "Name", "Occurrence", "number of babies", task1.getMaleList());
        ChartSetter.BarChartSetter(outputBarChart2, "Female", "Name", "Occurrence", "number of babies", task1.getFemaleList());
        ChartSetter.PieChartSetter(outputPieChart1, "Male", task1.getMaleList());
        ChartSetter.PieChartSetter(outputPieChart2, "Female", task1.getFemaleList());
        // TODO
    }

    /**
     * when click reset button, clear input box and output console
     */
    @FXML
    void doP1Reset() {
        p1YearField.clear();
        p1NField.clear();
        p1YearErrorLabel.setText("");
        p1YearErrorLabel.setVisible(false);
        p1NErrorLabel.setText("");
        p1NErrorLabel.setVisible(false);
        clearAllCharts();
    }

    // ----------------------------- Task2 Function ------------------------//

    /**
     * record if there's any error in user's input
     */
    boolean hasErrorTask2 = false;


    /**
     * check year1 input
     */
    @FXML
    void doTask2Year1Check() {
        String year1Text = task2Year1TextField.getText();
        boolean hasError = false;
        if (year1Text.isBlank() || !StringUtils.isNumeric(year1Text)) {
            task2Year1ErrorLabel.setText("Please Enter a Year Number");
            hasError = true;
        }
        else if (Integer.parseInt(year1Text) > 2019 || Integer.parseInt(year1Text) < 1880) {
            task2Year1ErrorLabel.setText("Please Enter a Number Between 1880 and 2019");
            hasError = true;
        }
        task2Year1ErrorLabel.setVisible(hasError);
        hasErrorTask2 = hasError;
    }

    /**
     * check year2 input
     */
    @FXML
    void doTask2Year2Check() {
        String year2Text = task2Year2TextField.getText();
        boolean hasError = false;
        if (year2Text.isBlank() || !StringUtils.isNumeric(year2Text) ) {
            task2Year2ErrorLabel.setText("Please Enter a Year Number");
            hasError = true;
        }
        else if (Integer.parseInt(year2Text) > 2019 || Integer.parseInt(year2Text) < 1880) {
            task2Year2ErrorLabel.setText("Please Enter a Number Between 1880 and 2019");
            hasError = true;
        }
        task2Year2ErrorLabel.setVisible(hasError);
        hasErrorTask2 = hasError;
    }

    /**
     * check whether year1 <= year2
     */
    @FXML
    void doTask2YearRelationCheck() {
        String year1Text = task2Year1TextField.getText();
        String year2Text = task2Year2TextField.getText();
        int year1 = Integer.parseInt(year1Text);
        int year2 = Integer.parseInt(year2Text);
        boolean hasError = false;
        if (year1 > year2) {
            task2Year2ErrorLabel.setText("Year2 should be larger than or equal to Year1");
            hasError = true;
        }
        task2Year2ErrorLabel.setVisible(hasError);
        hasErrorTask2 = hasError;
    }

    /**
     * check k input
     */
    @FXML
    void doTask2KCheck() {
        String kText = task2KTextField.getText();
        boolean hasError = false;
        if (kText.isBlank() || !StringUtils.isNumeric(kText) ) {
            task2KErrorLabel.setText("Please Enter an k number");
            hasError = true;
        }
        else if (Integer.parseInt(kText) < 1) {
            task2KErrorLabel.setText("Please Enter a Positive Integer");
            hasError = true;
        }
        task2KErrorLabel.setVisible(hasError);
        hasErrorTask2 = hasError;
    }

    /**
     *  Task Two
     *  To be triggered by the "Submit" button on the Task #2 Tab
     *
     */
    @FXML
    void task2SubmitData(ActionEvent event) {
        doTask2Year1Check();
        doTask2Year2Check();
        doTask2KCheck();
        if(hasErrorTask2) return;

        doTask2YearRelationCheck();
        if(hasErrorTask2) return;

        int year1 = Integer.parseInt(task2Year1TextField.getText());
        int year2 = Integer.parseInt(task2Year2TextField.getText());
        int k = Integer.parseInt(task2KTextField.getText());
        String gender = task2GenderChoiceBox.getValue().toString();

        List<String> testX = new ArrayList<>(Arrays.asList("1","2","3","4","5","6"));
        List<Number> testY = new ArrayList<>(Arrays.asList(1,1,4,5,1,4));

        KthPopularName task2 = new KthPopularName();
        if(! task2.setData(year1, year2, k, gender ) )
            return;

        String barTitle = "";
        barTitle += String.format("%d-th popular %s names over the period from %d to %d\n", k, (gender.equals("M")?"Male":"Female"), year1, year2);

        ChartSetter.BarChartSetter(outputBarChart1, barTitle, "Name", "Occurrence", "number of babies", task2.getModifiedList() );
        ChartSetter.PieChartSetter(outputPieChart1, barTitle, task2.getModifiedList() );
        outputBarChart1.setPrefWidth(600);
        outputBarChart2.setVisible(false);
        outputPieChart1.setPrefWidth(600);
        outputPieChart2.setVisible(false);

    }
    /**
     * do when leave the Task#2 Tab
     */
    @FXML
    void leaveTab2() {
        task2Year1TextField.clear();
        task2Year2TextField.clear();
        task2KTextField.clear();
        task2Year1ErrorLabel.setText("");
        task2Year1ErrorLabel.setVisible(false);
        task2Year2ErrorLabel.setText("");
        task2Year2ErrorLabel.setVisible(false);
        task2KErrorLabel.setText("");
        task2KErrorLabel.setVisible(false);
        clearAllCharts();
        outputBarChart1.setTitle("");
        outputBarChart1.setPrefWidth(307);
        outputPieChart1.setTitle("");
        outputPieChart1.setPrefWidth(307);
        outputLineChart1.setTitle("");
        outputLineChart1.setPrefWidth(307);
        outputBarChart2.setVisible(true);
        outputPieChart2.setVisible(true);
        outputLineChart2.setVisible(true);
    }

    /**
     * when click reset button, clear input box and output console
     */
    @FXML
    void task2Reset() {
        task2Year1TextField.clear();
        task2Year2TextField.clear();
        task2KTextField.clear();
        task2Year1ErrorLabel.setText("");
        task2Year1ErrorLabel.setVisible(false);
        task2Year2ErrorLabel.setText("");
        task2Year2ErrorLabel.setVisible(false);
        task2KErrorLabel.setText("");
        task2KErrorLabel.setVisible(false);
        clearAllCharts();
    }

    // ----------------------------- Task3 Function ------------------------//
    /**
     * record if there's any error in user's input
     */
    boolean hasErrorTask3 = false;

    /**
     * check year1 input
     */
    @FXML
    void doTask3Year1Check() {
        String year1Text = task3Year1TextField.getText();
        boolean hasError = false;
        if (year1Text.isBlank() || !StringUtils.isNumeric(year1Text)) {
            task3Year1ErrorLabel.setText("Please Enter a Year Number");
            hasError = true;
        }
        else if (Integer.parseInt(year1Text) > 2019 || Integer.parseInt(year1Text) < 1880) {
            task3Year1ErrorLabel.setText("Please Enter a Number Between 1880 and 2019");
            hasError = true;
        }
        task3Year1ErrorLabel.setVisible(hasError);
        hasErrorTask3 = hasError;
    }

    /**
     * check year2 input
     */
    @FXML
    void doTask3Year2Check() {
        String year2Text = task3Year2TextField.getText();
        boolean hasError = false;
        if (year2Text.isBlank() || !StringUtils.isNumeric(year2Text) ) {
            task3Year2ErrorLabel.setText("Please Enter a Year Number");
            hasError = true;
        }
        else if (Integer.parseInt(year2Text) > 2019 || Integer.parseInt(year2Text) < 1880) {
            task3Year2ErrorLabel.setText("Please Enter a Number Between 1880 and 2019");
            hasError = true;
        }
        task3Year2ErrorLabel.setVisible(hasError);
        hasErrorTask3 = hasError;
    }

    /**
     * check whether year1 <= year2
     */
    @FXML
    void doTask3YearRelationCheck() {
        String year1Text = task3Year1TextField.getText();
        String year2Text = task3Year2TextField.getText();
        int year1 = Integer.parseInt(year1Text);
        int year2 = Integer.parseInt(year2Text);
        boolean hasError = false;
        if (year1 > year2) {
            task3Year2ErrorLabel.setText("Year2 should be larger than or equal to Year1");
            hasError = true;
        }
        task3Year2ErrorLabel.setVisible(hasError);
        hasErrorTask3 = hasError;
    }

    /**
     *  Task Three
     *  To be triggered by the "Submit" button on the Task #2 Tab
     *
     */       
    @FXML
    void task3SubmitData() {
        String report = "";
    	
    	doTask3Year1Check();
        doTask3Year2Check();
        if(hasErrorTask3) return;

        doTask3YearRelationCheck();
        if(hasErrorTask3) return;
        
    	int year1 = Integer.parseInt(task3Year1TextField.getText());
    	int year2 = Integer.parseInt(task3Year2TextField.getText());
    	String name = task3NameTextField.getText();
    	String gender = (String) task3GenderChoiceBox.getValue();
    	
    	PopularityOfNames task3 = new PopularityOfNames();
    	task3.setData(year1, year2, name, gender);
    	task3.findMaxYear();
    	int maxYear = task3.getYear();
    	int maxRank = task3.getRank();
    	int occurrences = task3.getOccurrence();
    	String percentage = task3.getPercentage();
    	
    	report += String.format("The year when the name %s was most popular is %d at rank %d.\n"
				+ "In that year, the number of occurrences is %d,\n"
				+ "which represents %s of total %s births in %d.",
				name, maxYear, maxRank, occurrences, percentage, 
				gender.equals("M")?"male":"female", maxYear);
    	
    	textAreaConsole.setText(report);
    	ChartSetter.BarChartSetter2(outputBarChart1, "Popularity of name", "Year", "Occurrence", "number of babies", task3.getList());
    	ChartSetter.LineChartSetter(outputLineChart1,"Popularity of name", "Year", "Occurrence", "number of babies", task3.getList());
    }
    
    /**
     * when click reset button, clear input box and output console
     */
    @FXML
    void task3Reset() {
        task3Year1TextField.clear();
        task3Year2TextField.clear();
        task3NameTextField.clear();
        task3Year1ErrorLabel.setText("");
        task3Year1ErrorLabel.setVisible(false);
        task3Year2ErrorLabel.setText("");
        task3Year2ErrorLabel.setVisible(false);
        task3NameErrorLabel.setText("");
        task3NameErrorLabel.setVisible(false);
        clearAllCharts();
    }

    // ----------------------------- Task4 Function ------------------------//
    /**
     * indicates whether there's any mistake in the user input
     */
    boolean hasErrorTask4 = false;

    /**
     * check the dad name input
     */
    @FXML
    void p4DadNameCheck() {
        String name = p4DadName.getText();
        boolean hasError = false;
        if (name.isBlank() || StringUtils.isNumeric(name)) {
            p4DadNameError.setText("Please Enter a Name");
            hasError = true;
        }
        p4DadNameError.setVisible(hasError);
        hasErrorTask4 = hasError;
    }

    /**
     * check the dad YOB input
     */
    @FXML
    void p4DadYobCheck() {
        String year = p4DadYob.getText();
        boolean hasError = false;
        if (year.isBlank()) {
            p4DadYobError.setText("Please Enter a Year Number");
            hasError = true;
        }
        else if (!StringUtils.isNumeric(year) || Integer.parseInt(year) > 2019 || Integer.parseInt(year) < 1880) {
            p4DadYobError.setText("Please Enter a Number Between 1880 and 2019");
            hasError = true;
        }
        p4DadYobError.setVisible(hasError);
        hasErrorTask4 = hasError;
    }

    /**
     * check the mom name input
     */
    @FXML
    void p4MomNameCheck() {
        String name = p4MomName.getText();
        boolean hasError = false;
        if (name.isBlank() || StringUtils.isNumeric(name)) {
            p4MomNameError.setText("Please Enter a Name");
            hasError = true;
        }
        p4MomNameError.setVisible(hasError);
        hasErrorTask4 = hasError;
    }

    /**
     * check the mom YOB input
     */
    @FXML
    void p4MomYobCheck() {
        String year = p4MomYob.getText();
        boolean hasError = false;
        if (year.isBlank()) {
            p4MomYobError.setText("Please Enter a Year Number");
            hasError = true;
        }
        else if (!StringUtils.isNumeric(year) || Integer.parseInt(year) > 2019 || Integer.parseInt(year) < 1880) {
            p4MomYobError.setText("Please Enter a Number Between 1880 and 2019");
            hasError = true;
        }
        p4MomYobError.setVisible(hasError);
        hasErrorTask4 = hasError;
    }

    /**
     * display additional information is second algorithm is selected
     */
    @FXML
    void p4AlgoCheck() {
        String algo = p4AlgoChoiceBox.getValue().toString();
//        boolean hasError = false;
//        if ( !( algo.equals(p4AlgorithmChoice.get(0)) || algo.equals(p4AlgorithmChoice.get(1)) ) ) {
//            p4AlgoError.setText("Please Select an algorithm");
//            hasError = true;
//        }
//        p4AlgoError.setVisible(hasError);
//        hasErrorTask4 = hasError;
        p4TypeChoiceBox.setVisible(algo.equals(p4AlgorithmChoice.get(1)));
        p4TypeLabel.setVisible(algo.equals(p4AlgorithmChoice.get(1)));
    }

    /**
     * when click the submit button, check data and output result
     */
    @FXML
    void doP4Submit() {
        p4AlgoCheck();
        p4DadNameCheck();
        p4DadYobCheck();
        p4MomNameCheck();
        p4MomYobCheck();
        if(hasErrorTask4) return;

        String dadName = p4DadName.getText();
        String momName = p4MomName.getText();
        int dadYob = Integer.parseInt(p4DadYob.getText());
        int momYob = Integer.parseInt(p4MomYob.getText());
        boolean isAlgo1 = p4AlgoChoiceBox.getValue().equals(p4AlgorithmChoice.get(0));
        int type = isAlgo1 ? 0 : p4TypeChoiceBox.getValue().equals(p4TypeChoice.get(0)) ? 1 : 2;

        RecommendationOnNames task4 = new RecommendationOnNames();
        task4.setData(dadName, momName, dadYob, momYob, isAlgo1, type);
        if (isAlgo1) {
            pieButton.setVisible(false);
            lineButton.setVisible(false);
            textAreaConsole.setText(task4.getSummaryAlgo1());
            ChartSetter.BarChartSetter(outputBarChart1, "Boy's most popular names", "Name", "Occurrence", String.valueOf(dadYob), task4.getBoyRecommendList());
            ChartSetter.BarChartSetter(outputBarChart2, "Girl's most popular names", "Name", "Occurrence", String.valueOf(momYob), task4.getGirlRecommendList());
            // TODO
        }
        else {
            pieButton.setVisible(true);
            lineButton.setVisible(true);
            textAreaConsole.setText(task4.getSummaryAlgo2());
            // TODO
        }
    }

    /**
     * when click reset button, clear input box and output console
     */
    @FXML
    void doP4Reset() {
        p4AlgoChoiceBox.setValue(p4AlgorithmChoice.get(0));
        p4DadName.clear();
        p4DadYob.clear();
        p4MomName.clear();
        p4MomYob.clear();
        p4AlgoError.setVisible(false);
        p4DadNameError.setVisible(false);
        p4DadYobError.setVisible(false);
        p4MomNameError.setVisible(false);
        p4MomYobError.setVisible(false);
        p4TypeChoiceBox.setVisible(false);
        p4TypeLabel.setVisible(false);
        pieButton.setVisible(false);
        lineButton.setVisible(false);

        clearAllCharts();
    }

    // ----------------------------- Task5 Function ------------------------//

    /**
     * record if there's any error in user's input
     */
    boolean hasErrorTask5 = false;


    /**
     * check iName input
     */
    @FXML
    void doTask5iNameCheck() {
        String iName = task5iNameTextField.getText();
        boolean hasError = false;
        if ( iName.isBlank() ) {
            task5iNameErrorLabel.setText("Please Enter a Name");
            hasError = true;
        }
        task5iNameErrorLabel.setVisible(hasError);
        hasErrorTask5 = hasError;
    }

    /**
     * check iAge input
     */
    @FXML
    void doTask5iAgeCheck() {
        String iAgeText = task5iAgeTextField.getText();
        boolean hasError = false;
        if ( iAgeText.isBlank() || !StringUtils.isNumeric(iAgeText) ) {
            task5iAgeErrorLabel.setText("Please Enter an age number");
            hasError = true;
        }
        else if ( Integer.parseInt(iAgeText) < 1){
            task5iAgeErrorLabel.setText("Please Enter a Positive Integer");
            hasError = true;
        }
        task5iAgeErrorLabel.setVisible(hasError);
        hasErrorTask5 = hasError;
    }

    /**
     *  Task Five
     *  To be triggered by the "Submit" button on the Task#5 Tab
     *
     */
    @FXML
    void task5SubmitData(ActionEvent event) {
        doTask5iNameCheck();
        doTask5iAgeCheck();
        if(hasErrorTask5) return;

    }

    /**
     * when click reset button, clear input box and output console
     */
    @FXML
    void task5Reset() {
        task5iNameTextField.clear();
        task5iAgeTextField.clear();
        task5iNameErrorLabel.setText("");
        task5iNameErrorLabel.setVisible(false);
        task5iAgeErrorLabel.setText("");
        task5iAgeErrorLabel.setVisible(false);
        clearAllCharts();
    }



    // ----------------------------- Task6 Function ------------------------//


}





