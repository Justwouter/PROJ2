package com.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.logic.User;

import javafx.beans.binding.ObjectExpression;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

import javafx.scene.control.Label;

import javafx.scene.layout.Pane;

public class DashController implements Initializable, IController{

    private User user;

    @FXML
    private Label uitstootVergelijk;

    @FXML
    private BarChart<String,Number> co2ThisWeekChart;

    @FXML
    private LineChart<String,Number> medianLineChart;

    @FXML
    private Label pointsDash;

    @FXML
    private NumberAxis weekChartY = new NumberAxis();

    @FXML
    private CategoryAxis weekChartX = new CategoryAxis();

  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateWeeklyChart();
    }

    @Override
    public void setPresets(User user){} //Empty on purpose, needed to implement the interface

    @FXML
    private void switchToReisGegevens() throws IOException {
        Main.show("reisgegevens", user);
    }

    @FXML
    private void switchToLeaderboard() throws IOException {
        Main.show("leaderboard", user);
    }

    public void setUser(User u){
        this.user = u;
    }

    @Override
    public void setPoints(User user) {
        pointsDash.setText(user.getPoint().getPointsString());

    }

    @FXML
    public void setVergelijking(){
        uitstootVergelijk.setText(user.vergelijkPuntMetUitstoot());
    }
    /**
     * Wrapper method to update the barchart on the dashboard
     */
    @FXML
    public void triggerChartUpdate(){
        updateMedianLine(updateWeeklyChart());
        setVergelijking();
    }


    private void updateMedianLine(List<Long> averageList){

        medianLineChart.setLegendVisible(false);
        medianLineChart.setAnimated(false);
        medianLineChart.setCreateSymbols(true);
        medianLineChart.setAlternativeRowFillVisible(false);
        medianLineChart.setAlternativeColumnFillVisible(false);
        medianLineChart.setHorizontalGridLinesVisible(false);
        medianLineChart.setVerticalGridLinesVisible(false);
        medianLineChart.getXAxis().setVisible(false);
        medianLineChart.getYAxis().setVisible(false);

        medianLineChart.getData().clear();



        String[] daysOfTheWeek = {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        long average = 0;
        long highest = 0;
        for(Long l : averageList){
            average +=l;
            if(l > highest){
                highest = l;
            }
        }
        average = average/averageList.size();
        XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();
        for(int i=0;i< daysOfTheWeek.length;i++){
            Data<String,Number> vars = new XYChart.Data<String, Number>(daysOfTheWeek[i],averageList.get(i));
            series.getData().add(vars);
        }
        medianLineChart.getData().add(series);

    }































    /**
     *Updates the Bar Graph on the dashboard with the weekly values of the currently logged in user
        <p>
     * Temporary uses random numbers until a storage class is available
     */
    private List<Long> updateWeeklyChart(){ //TODO lock the XAxis values horizontal, fix/lock the weird YAxis scaling
        //ArrayList<Object> historicUserData = new ArrayList<>();
        List<Long> averageList = new ArrayList<>();
        String[] daysOfTheWeek = {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",};
        XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();

        System.out.println("Parsing data");//Debug

        for(int i=0;i< daysOfTheWeek.length;i++){
            Long userDataValue = Math.round(Math.random()*100);
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


        //Debug
        System.out.println("Average: "+average);
        System.out.println("Highest: " +highest);
        System.out.println("Displaying data");

        //Assign lables
        co2ThisWeekChart.setTitle("Weekly CO2 Discharge");
        weekChartX.setLabel("Day");
        weekChartY.setLabel("Value");
        
        //Load the data & Toggle animations to avoid 'Issues'
        co2ThisWeekChart.setLegendVisible(false);
        co2ThisWeekChart.setAnimated(false);
        co2ThisWeekChart.getData().clear();
        co2ThisWeekChart.getData().add(series);
        //weekChartY.setUpperBound(highest+20.0);
        co2ThisWeekChart.setAnimated(true);
        return averageList;
    }

    /**
     * Creates floating lables containing the bar values for the Dashboard co2ThisWeek Chart
     * @param value
     */
    private static Node createValueLabel(ObjectExpression<Number> value) {
        var label = new Label();
        label.textProperty().bind(value.asString());
        var pane = new Pane(label);
        label.translateYProperty().bind(label.heightProperty().divide(-1.0));
        //label.translateXProperty().bind(label.widthProperty().divide(-20));

        return pane;
    }
  
    /**
     * Generates node colors for the given XYChart data values
     * @param  data XYChart.Data
     */
    private void barColorSwitch(XYChart.Data<String, Number> data){
        Node node = data.getNode();
        if (data.getYValue().intValue() > 99) {
            node.setStyle("-fx-bar-fill: black");
        } 
        else if (data.getYValue().intValue() > 83) {
            node.setStyle("-fx-bar-fill: red");
        } 
        else if (data.getYValue().intValue() > 67) {
            node.setStyle("-fx-bar-fill: orange");
        } 
        else if (data.getYValue().intValue() > 50) {
            node.setStyle("-fx-bar-fill: yellow");
        }
        else if (data.getYValue().intValue() > 33) {
            node.setStyle("-fx-bar-fill: blue");
        }
        else {
            node.setStyle("-fx-bar-fill: green");
        }
    }
}
