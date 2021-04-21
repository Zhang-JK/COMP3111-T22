package comp3111.popnames.core;

import com.sun.javafx.collections.ImmutableObservableList;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is used for generating charts and tables
 * @author ZHANG Jiekai LIANG Houdong
 * @version 1.1
 */
public class ChartSetter {

    /**
     * A bar chart generator using name record
     * @param chart the output chart
     * @param title the chart title
     * @param xTitle x-axis title/comment
     * @param yTitle y-axis title/comment
     * @param seriesTitle bar comment
     * @param nameRecords the data to show (the name and its occurrences)
     */
    static public void BarChartSetter(BarChart<String, Number> chart, String title, String xTitle, String yTitle, String seriesTitle, List<NameRecord> nameRecords) {
        List<String> xData = new ArrayList<String>();
        List<Number> yData = new ArrayList<Number>();
        for (NameRecord rec : nameRecords) {
            xData.add(rec.getName());
            yData.add(rec.getOccurrence());
        }
        BarChartSetter(chart, title, xTitle, yTitle, seriesTitle, xData, yData);
    }

    /**
     * A bar chart generator using occurrence record
     * @param chart the output chart
     * @param title the chart title
     * @param xTitle x-axis title/comment
     * @param yTitle y-axis title/comment
     * @param seriesTitle bar comment
     * @param record the data to show (year, occurrnce)
     */
    static public void BarChartSetter2(BarChart<String, Number> chart, String title, String xTitle, String yTitle, String seriesTitle, List<OccurrenceRecord> record) {
        List<String> xData = new ArrayList<String>();
        List<Number> yData = new ArrayList<Number>();
        for (OccurrenceRecord rec : record) {
            xData.add(String.valueOf(rec.getYear()));
            yData.add(rec.getOccurrence());
        }
        BarChartSetter(chart, title, xTitle, yTitle, seriesTitle, xData, yData);
    }
    
    /**
     * Full bar chart generator
     * You need to pass the raw data in List<String> for x and List<Number> for y
     * @param chart the output chart
     * @param title the chart title
     * @param xTitle x-axis title/comment
     * @param yTitle y-axis title/comment
     * @param seriesTitle bar comment
     * @param xData data for x-axis, should be List<String>
     * @param yData data for y-axis, should be List<Number>
     */
    static public void BarChartSetter(BarChart<String, Number> chart, String title, String xTitle, String yTitle, String seriesTitle,List<String> xData, List<Number> yData) {
        if(xData.size() != yData.size()) throw new RuntimeException();
        chart.getData().clear();
        chart.setTitle(title);
        chart.getXAxis().setLabel(xTitle);
        chart.getYAxis().setLabel(yTitle);

        XYChart.Series series = new XYChart.Series();
        for(int i = 0; i < xData.size(); i++) {
            XYChart.Data<String, Number> data = new XYChart.Data(xData.get(i), yData.get(i));
            series.getData().add(data);
//            data.nodeProperty().addListener((observable, oldValue, newValue) -> {
//                if (newValue != null) {
//                    System.out.println(newValue);
//                }
//            });
        }
        series.setName(seriesTitle);
        chart.getData().addAll(series);
    }
  
    /**
     * A pie chart generator using name record
     * @param chart the output chart
     * @param title the chart title
     * @param nameRecords data to display (name and its occurrences)
     */
    static public void PieChartSetter(PieChart chart, String title, List<NameRecord> nameRecords) {
        List<String> xData = new ArrayList<String>();
        List<Number> yData = new ArrayList<Number>();
        for (NameRecord rec : nameRecords) {
            xData.add(rec.getName());
            yData.add(rec.getOccurrence());
        }
        PieChartSetter(chart, title, xData, yData);
    }

    /**
     * Full version of pie chart generator
     * You need to pass the raw data in List<String> and List<Number>
     * @param chart the output chart
     * @param title the chart title
     * @param xData data in List<String>, which shows the name of each part
     * @param yData data in List<Number>, which shows the amount of each part
     */
    static public void PieChartSetter(PieChart chart, String title, List<String> xData, List<Number> yData) {
        if(xData.size() != yData.size()) throw new RuntimeException();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(int i=0; i<xData.size(); i++)
            pieChartData.add(new PieChart.Data(xData.get(i), yData.get(i).doubleValue()));

        chart.getData().clear();
        chart.setData(pieChartData);
        chart.setTitle(title);

        final Label caption = new Label("TESTING");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 12;");
        caption.setVisible(false);

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
//                            System.out.println(e.getSceneX());
//                            System.out.println(e.getSceneY());
//                            System.out.println(String.valueOf(data.getPieValue()));
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()));
                            caption.setVisible(true);
                        }
                    });
        }
    }
    
    static public void LineChartSetter(LineChart<Integer, Integer> chart, String title, String xTitle, String yTitle, String seriesTitle, List<OccurrenceRecord> record) {
        List<String> xData = new ArrayList<String>();
        List<Integer> yData = new ArrayList<Integer>();
        for (OccurrenceRecord rec : record) {
            xData.add(String.valueOf(rec.getYear()));
            yData.add(rec.getOccurrence());
        }
        LineChartSetter(chart, title, xTitle, yTitle, seriesTitle, xData, yData);
    }

    /**
     * Full version of line chart generator
     * You need to pass the raw data in List<String> and List<Integer>
     * @param chart the output chart
     * @param title the chart title
     * @param xTitle x-axis title/comment
     * @param yTitle y-axis title/comment
     * @param seriesTitle line comment
     * @param xData data in List<String>, which shows the name of each point
     * @param yData data in List<Integer>, which shows the number of each point
     */
    static public void LineChartSetter(LineChart<Integer, Integer> chart, String title, String xTitle, String yTitle, String seriesTitle, List<String> xData, List<Integer> yData) {
        if(xData.size() != yData.size()) throw new RuntimeException();
        chart.getData().clear();
        chart.setTitle(title);
        chart.getXAxis().setLabel(xTitle);
        chart.getYAxis().setLabel(yTitle);

        XYChart.Series series = new XYChart.Series();
        for(int i = 0; i < xData.size(); i++) {
            series.getData().add(new XYChart.Data(xData.get(i), yData.get(i)));
        }
        series.setName(seriesTitle);
        chart.getData().addAll(series);
    }

    public static final String[] ColumnMapKey = {"key1", "key2", "key3", "key4", "key5"};

    /**
     * Full version of table generator
     * You need to pass a list of data in Map structure
     * @param table the output table
     * @param columnNum the number of columns
     * @param columnName an array of column names
     * @param dataMap an ObservableList of Map contains data you want to display
     */
    static public void TableSetter(TableView table, int columnNum, String[] columnName, ObservableList<Map> dataMap) {
        table.getColumns().clear();
        ArrayList< TableColumn<Map,String> > columnList = new ArrayList<>();
        for(int i = 0; i < columnNum; i++){
          TableColumn<Map, String> dataColumn = new TableColumn<>(columnName[i]);
          dataColumn.setCellValueFactory(new MapValueFactory<>(ColumnMapKey[i]));
          columnList.add(dataColumn);
          table.getColumns().add(dataColumn);
        }
        table.getItems().setAll(dataMap);

    }

}
