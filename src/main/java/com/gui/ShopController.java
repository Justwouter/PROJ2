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
import javafx.scene.image.Image;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShopController extends AController implements Initializable {

    public static List<Item> itemList = new ArrayList<>();

    @FXML
    private TableView<Item> tableView;

    @FXML
    private TableColumn<Object, String> imageColumn = new TableColumn<>();

    @FXML
    private TableColumn<Object, String> nameColumn = new TableColumn<>();
    @FXML
    private TableColumn<Object, String> priceColumn = new TableColumn<>();

    private ObservableList<Item> data = FXCollections.observableArrayList(itemList);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayItems();
    }

    private void displayItems(){
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.setItems(data);
        addButtonToTable();
    }


    // TODO Make code readable.
    // It just works for now.
    private void addButtonToTable() {
        TableColumn<Item, Void> colBtn = new TableColumn("");

        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("Koop");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Item data = getTableView().getItems().get(getIndex());
                            purchaseItem(data);
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

    public void purchaseItem(Item item){
        if (user.getPoint().enoughBalance(item.getPrice())){
            user.getPoint().addPoints(-item.getPrice());
        }
    }
}
