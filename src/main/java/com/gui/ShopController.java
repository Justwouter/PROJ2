package com.gui;

import com.logic.Item;
import com.logic.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShopController extends AController implements Initializable {

    // TODO Remove before merging.
    @FXML
    private Label points;

    // TODO Remove before merging.
    @Override
    void setPoints(User user) {
        points.setText(user.getPoint().getPointsString());
    }

    public static List<Item> itemList = new ArrayList<>();

    @FXML
    private TableView<Item> tableView;

    @FXML
    private TableColumn<Object, Object> imageColumn = new TableColumn<>();

    @FXML
    private TableColumn<Object, Object> priceColumn = new TableColumn<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillBoard();
    }

    private void fillBoard(){
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("prijs"));
        ObservableList<Item> data = FXCollections.observableArrayList(itemList);
        tableView.setItems(data);
        addButtonToTable();
    }


    // TODO Make code readable.
    // It just works for now.
    private void addButtonToTable() {
        TableColumn<Item, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("Action");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Item data = getTableView().getItems().get(getIndex());
                            // TODO Make checker to not be able to get into negative by purchasing.
                            user.getPoint().addPoints(-data.getPrijs());
                            // TODO Reminder to make the point label an observer?
                            points.setText(user.getPoint().getPointsString());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableView.getColumns().add(colBtn);

    }
}
