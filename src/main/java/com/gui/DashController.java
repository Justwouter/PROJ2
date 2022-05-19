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
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

import javafx.scene.control.Label;

import javafx.scene.layout.Pane;

public class DashController implements Initializable, IController{

    private User user;

    @FXML
    private StackedBarChart<String,Number> co2ThisWeekChart;

    @FXML
    private Label pointsDash;

    @FXML
    private NumberAxis weekChartY;

  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateWeeklyChart();
    }

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
    /**
     * Wrapper method to update the barchart on the dashboard
     */
    @FXML
    public void triggerChartUpdate(){
        updateWeeklyChart();
    }

    /**
     *Updates the Bar Graph on the dashboard with the weekly values of the currently logged in user
        <p>
     * Temporary uses random numbers until a storage class is available
     */
    private void updateWeeklyChart(){
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
            colorSwitch(vars);
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
        System.out.println("Displaying data");

        //Assign lables
        co2ThisWeekChart.setTitle("Weekly CO2 Discharge");
        co2ThisWeekChart.getXAxis().setLabel("Day");
        co2ThisWeekChart.getYAxis().setLabel("Value");
        
        //Load the data & Toggle animations to avoid 'Issues'
        co2ThisWeekChart.setAnimated(false);
        co2ThisWeekChart.getData().clear();
        co2ThisWeekChart.getData().add(series);
        weekChartY.setUpperBound(highest+20.0);
        co2ThisWeekChart.setAnimated(true);
        co2ThisWeekChart.setLegendVisible(false);
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
    private void colorSwitch(XYChart.Data<String, Number> data){
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
