package com.gui;

import com.logic.SaveManager;
import com.logic.User;
import javafx.beans.binding.ObjectExpression;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class DashController extends AController {

    private MediaPlayer jukebox;
    private List<Long> avList;

    @FXML
    private Label uitstootVergelijk;

    @FXML
    private BarChart<String,Number> co2ThisWeekChart;

    @FXML
    private LineChart<String,Number> medianLineChart;

    @FXML
    private Label pointsDash;

    @FXML
    private CheckBox dynAverage;

    @FXML
    private CheckBox staticAverage;

    @FXML
    private NumberAxis weekChartY = new NumberAxis(0,150,10);

    @FXML
    private CategoryAxis weekChartX = new CategoryAxis();

    @FXML
    private void switchToReisGegevens() throws IOException {
        Main.show("reisgegevens", user);
    }

    @FXML
    private void switchToLeaderboard() throws IOException {
        Main.show("leaderboard", user);
    }

    @FXML
    public void switchToDashboard() throws IOException {
        Main.show("dashboard", user);
    }

    @FXML
    public void switchToInstellingen() throws IOException {
        Main.show("instellingen", user);
    }

    @FXML
    public void switchToShop() throws IOException {
        Main.show("shop", user);
    }

    //Parent methods overrides
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaveManager.saveState();
        LoadMusic(new File("src/main/resources/com/gui/Sounds/ding.wav"));
        updateMedianLine(updateWeeklyChart());
    }

    @Override
    public void setUser(User u){
        this.user = u;
    }

    @Override
    public void setPoints(User user) {
        pointsDash.setText(user.getPoint().getPointsString());
    }


    
    
    // TODO setVergelijking() weer functioneel weten te krijgen
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
     * Wrapper method to update the barchart on the dashboard.
     */
    @FXML
    public void triggerChartUpdate(){
        playMusic();
        updateMedianLine(updateWeeklyChart());
        setVergelijking();
    }

    /**
     * Loads a file into the jukebox.
     * @param file the file to be loaded.
     * @return Media object containing the given file.
     */
    private Media LoadMusic(File file){
        Media media = new Media(file.toURI().toString());
        this.jukebox = new MediaPlayer(media);
        return media;
    }

    /**
     * Plays the file stored in jukebox & resets if called again.
     */
    private void playMusic(){
        jukebox.play();
        jukebox.stop();
        jukebox.play();
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
            
            medianLineChart.setTitle("Weekly CO2 Values");
            medianLineChart.getXAxis().setLabel("Day");
            medianLineChart.getYAxis().setLabel("Value");
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
        System.out.println("Displaying bar"); //Debug

        //Assign lables 
        co2ThisWeekChart.setTitle("Weekly CO2 Values");
        weekChartX.setLabel("Day");
        weekChartY.setLabel("Value");
        
        //Load the data & Toggle animations to avoid 'Issues'
        co2ThisWeekChart.setLegendVisible(false);
        co2ThisWeekChart.setAnimated(false);
        co2ThisWeekChart.getData().clear();
        co2ThisWeekChart.getData().add(series);
        //co2ThisWeekChart.setAnimated(true);
        this.avList = averageList;
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
}
