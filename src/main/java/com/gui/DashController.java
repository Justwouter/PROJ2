package com.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.logic.User;

import javafx.beans.binding.ObjectExpression;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        String[] daysOfTheWeek = {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",};
        XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();

        System.out.println("Parsing data");//Debug

        for(int i=0;i< daysOfTheWeek.length;i++){
            Data<String,Number> vars = new XYChart.Data<String, Number>(daysOfTheWeek[i],Math.round(Math.random()*100));
            vars.setNode(createDataNode(vars.YValueProperty()));
            series.getData().add(vars);
        }

        System.out.println("Displaying data");
        //series.setName("Weekly CO2 Discharge");
        
        co2ThisWeekChart.getXAxis().setLabel("Day");
        co2ThisWeekChart.getYAxis().setLabel("Value");
        
        co2ThisWeekChart.setAnimated(false);
        co2ThisWeekChart.getData().clear();
        co2ThisWeekChart.getData().add(series);
        co2ThisWeekChart.setAnimated(true);
        co2ThisWeekChart.setLegendVisible(false);
    }


    /**
     * Creates floating lables containing the bar values for the Dashboard co2ThisWeek Chart
     * @param value
     */
    private static Node createDataNode(ObjectExpression<Number> value) {
        var label = new Label();
        label.textProperty().bind(value.asString());
        var pane = new Pane(label);
        label.translateYProperty().bind(label.heightProperty().divide(-1.0));
        //label.translateXProperty().bind(label.widthProperty().divide(-20));

        return pane;
    }

    
}
