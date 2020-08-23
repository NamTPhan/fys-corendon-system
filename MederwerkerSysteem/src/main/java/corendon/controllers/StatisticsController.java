package corendon.controllers;

import com.itextpdf.text.DocumentException;
import corendon.queries.StatsMath;
import corendon.utilities.PDFExport;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class StatisticsController implements Initializable {
    @FXML 
    private Label threeDays;
    
    @FXML 
    private Label twentyOneDays;
    
    @FXML 
    private Label thirtyOneDays;
    
    @FXML 
    private Label unresolvedAnnually;
    
    @FXML 
    private Button pExport;
  
    @FXML
    private LineChart<Number, Number> lineChart;
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

   
    @FXML
    private void handleButtonAction(ActionEvent event) throws DocumentException, IOException {
        PDFExport keksport = new PDFExport();
        keksport.toPDF();    
        pExport.setText("SEND!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        threeDays.setText(StatsMath.threeDaysResults() 
                + StatsMath.getTotalDays(3));
        twentyOneDays.setText(StatsMath.twentyOneDaysResults() 
                + StatsMath.getTotalDays(21));
        thirtyOneDays.setText(StatsMath.oneMonthResults() 
                + StatsMath.getTotalDays(31));
        unresolvedAnnually.setText(StatsMath.oneYearUnsolvedResults()
                + StatsMath.getTotalDays(365));
        
         // start of the linechart code
        lineChart.setTitle("Lost & Returned each month");
        XYChart.Series lost = new XYChart.Series();
        lost.setName("Lost");
        XYChart.Series found = new XYChart.Series();
        found.setName("Returned");
        
        // use this loop to populate the chart!
        // fount = returned
        for(int i = 0; i < 12; i++){
                lost.getData().add(new XYChart.Data(i, StatsMath.getMonthlyLost(i)));
                found.getData().add(new XYChart.Data(i, StatsMath.getMonthlyFound(i)));    
            }
        
        lineChart.getData().addAll(lost, found);

    }    
}
