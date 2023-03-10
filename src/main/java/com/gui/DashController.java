package com.gui;

import com.logic.PuntMutatie;
import com.logic.User;

import javafx.beans.binding.ObjectExpression;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class DashController extends AController implements Initializable {

    private List<Long> avList;

    @FXML
    private AnchorPane dashMainPane;

    @FXML
    private Label uitstootVergelijk;

    @FXML
    private BarChart<String,Number> co2ThisWeekChart;

    @FXML
    private LineChart<String,Number> medianLineChart;

    @FXML
    private CheckBox dynAverage;

    @FXML
    private CheckBox staticAverage;

    @FXML
    private NumberAxis weekChartY = new NumberAxis(0,150,10);

    @FXML
    private CategoryAxis weekChartX = new CategoryAxis();

    @FXML
    private NumberAxis averageChartY = new NumberAxis(0,150,10);

  
    //Parent methods overrides
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveManager.saveState();        
    }

    @FXML
    public void setVergelijking(){
        uitstootVergelijk.setText(user.vergelijkPuntMetUitstoot());
    }

    /**
     * Wrapper method to update the average lines on the dashboard when they are en/disabled.
     */
    @FXML
    private void loadAverageLines(){
        updateMedianLine(avList);
    }

    /**
     * Loads the average lines if they are enabled in the GUI.
     * @param averageList List containing the barchart rng values. Can be removed when switching to stored userdata.
     */
    private void updateMedianLine(List<Long> averageList){
        //Hide the lineChart if no lines are selected to be visible
        if(!staticAverage.selectedProperty().get() && !dynAverage.selectedProperty().get()){
            medianLineChart.setVisible(false);
            System.out.println("Hiding chart"); //Debug
        }
        else{
            medianLineChart.setTitle(co2ThisWeekChart.getTitle());
            medianLineChart.getYAxis().setLabel(co2ThisWeekChart.getYAxis().getLabel());
            medianLineChart.setCreateSymbols(false);
            medianLineChart.getData().clear();

            String[] daysOfTheWeek = {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

            //Dynamic relative average
            if(dynAverage.selectedProperty().get()){
                System.out.println("Displaying dynAverage"); //Debug
                
                XYChart.Series<String,Number> adjustingAverageLine = new XYChart.Series<String, Number>();
                for(int i=0;i< daysOfTheWeek.length;i++){
                    Long yValue = calculateRelativeAverage(averageList,i);
                    Data<String,Number> vars = new XYChart.Data<String, Number>(daysOfTheWeek[i],yValue);
                    adjustingAverageLine.getData().add(vars);
                }
                medianLineChart.getData().add(adjustingAverageLine);
            }

            //Static average line
            if(staticAverage.selectedProperty().get()){
                System.out.println("Displaying staticAverage"); //Debug

                XYChart.Series<String,Number> averageLine = new XYChart.Series<String, Number>();
                for(int i=0;i< daysOfTheWeek.length;i++){
                    Data<String,Number> vars = new XYChart.Data<String, Number>(daysOfTheWeek[i],calculateRelativeAverage(averageList,daysOfTheWeek.length-1));
                    averageLine.getData().add(vars);
                }
                medianLineChart.getData().add(averageLine);
            } 
            medianLineChart.setVisible(true);
        }
    }

    /**
     * Calculates the average amount of CO2 on a day relative to earlier days
     * @param averageList
     * @param days
     * @return Long average
     */
    private Long calculateRelativeAverage(List<Long> averageList, int days){
        //Calculate the average
        long average = 0;
        long highest = 0;
        for(int i=0;i<days+1 ;i++){
            Long currentNumber = averageList.get(i);
            average +=currentNumber;
            if(currentNumber > highest){
                highest = currentNumber;
            }
        }
        return average/(days+1);
    }

    /**
     *Updates the Bar Graph on the dashboard with the weekly values of the currently logged in user
     *<p>
     * Temporary uses random numbers until a storage class is available
     */
    private List<Long> updateWeeklyChart(){                                                                         
        //ArrayList<Object> historicUserData = new ArrayList<>();
        List<Long> averageList = new ArrayList<>();
        String[] daysOfTheWeek = {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",};
        XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();
        System.out.println("Parsing data");//Debug

        //Populate the XYchart with random value nodes & add floating lables to said nodes 
        for(int i=0;i< daysOfTheWeek.length;i++){
            //Long userDataValue = Math.round(Math.random()*100);
            Long userDataValue = calculateDailyUsage(i+1);//Calendar week start at index 1
            averageList.add(userDataValue);
            Data<String,Number> vars = new XYChart.Data<String, Number>(daysOfTheWeek[i],userDataValue);
            vars.setNode(createValueLabel(vars.YValueProperty()));
            barColorSwitch(vars);
            series.getData().add(vars);
        }

        //Calculate the average for the given datases
        long average = 0;
        long highest = 0;
        for(Long l : averageList){
            average +=l;
            if(l > highest){
                highest = l;
            }
        }
        average = average/averageList.size();

        //Adjust the max amount of values the charts can contain. Always keep the linechart equal to the barchart.
        weekChartY.setUpperBound(calculateUpperBound((double)highest));
        averageChartY.setUpperBound(weekChartY.getUpperBound());


        //Debug
        System.out.println("Average: "+average);
        System.out.println("Highest: " +highest);
        System.out.println("Displaying bar");

        //Assign lables 
        co2ThisWeekChart.setTitle("Weekly CO2 Values");
        weekChartY.setLabel("Discharge");
        
        //Load the data & Toggle animations to avoid 'Issues'
        co2ThisWeekChart.setLegendVisible(false);
        co2ThisWeekChart.setAnimated(false);
        co2ThisWeekChart.getData().clear();
        co2ThisWeekChart.getData().add(series);
        co2ThisWeekChart.setAnimated(true);
        this.avList = averageList;
        return averageList;
    }


    /**
     * Calculates the upper bound for co2ThisWeekChart & averageChart.<P>
     * Nearest multiple of 10 to  the Highest value in chart + 20;
     * @param highest
     * @return
     */
    private double calculateUpperBound(double highest){
        double upperBound = highest+20;
        while(upperBound % 10 !=0){
            upperBound++;
        }
        return upperBound;

    }


    /**
     * Loops trough a User's point mutations & returns the values for the selected day in the current week.<p>
     * This method uses the Calendar date index e.g. Sunday = 1 and Saturday = 7.
     * @param day
     * @return Long containing the added (positive) values of the selected day.
     */
    private Long calculateDailyUsage(int day){
        ArrayList<PuntMutatie> allPointMutations = user.getUserPuntMutaties();
        Calendar currentDate = Calendar.getInstance();
        Long output = (long)0;
        for(PuntMutatie mutation : allPointMutations){
            int mutationWeek = mutation.datum.get(Calendar.WEEK_OF_YEAR);
            int currentWeek = currentDate.get(Calendar.WEEK_OF_YEAR);
            if(mutationWeek == currentWeek || (day==1) && mutationWeek == currentWeek-1){
                if(mutation.datum.get(Calendar.DAY_OF_WEEK) == day && mutation.uitstootCO2 != null){
                    output +=(mutation.uitstootCO2)/1000;
                    System.out.println(output);
                }
            }
        }
        return Math.abs(output);
    }

    /**
     * Creates floating lables containing the bar values for the Dashboard co2ThisWeek Chart 
     * @param value
     */
    private Node createValueLabel(ObjectExpression<Number> value) {
        var label = new Label();
        label.textProperty().bind(value.asString());
        var pane = new Pane(label);
        label.translateYProperty().bind(label.heightProperty().divide(-1.0));
        return pane;
    }
  
    /**
     * Generates node colors for the given XYChart data values
     * @param  data XYChart.Data
     */
    private void barColorSwitch(XYChart.Data<String, Number> data){
        Node node = data.getNode();
        if (data.getYValue().intValue() > 75) {
            node.setStyle("-fx-bar-fill: red");
        } 
        else if (data.getYValue().intValue() >= 25) {
            node.setStyle("-fx-bar-fill: orange");
        }
        else {
            node.setStyle("-fx-bar-fill: green");
        }
    }

    @Override
    public void setUser(User user) {
        super.setUser(user);
        updateMedianLine(updateWeeklyChart());
    }
}
