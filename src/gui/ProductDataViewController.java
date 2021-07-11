package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.entities.Product;
import model.service.ProductService;

public class ProductDataViewController implements Initializable {

	private Product entity;
	private ProductService service;

	public void setProduct(Product entity) {
		this.entity = entity;
	}

	public void setService(ProductService service) {
		this.service = service;
	}

	@FXML
	private TextField txtId;
	@FXML
	private Button btFind;
	@FXML
	private Button btCancel;
	@FXML
	private Button btClear;
	@FXML
	private TableView<Product> tableViewProduct;

	@FXML
	private TableColumn<Product, String> tableColumnName;

	@FXML
	private TableColumn<Product, Integer> tableColumnCod;
	@FXML
	private TableColumn<Product, Double> tableColumnValue;

	private ObservableList<Product> obsList;

	@FXML
	private Label lbError;

	@FXML
	private VBox MainRightPaneProductData;

	@FXML
	public void onBtFindAction() {
		setTableViewProduct();
	}

	@FXML
	public void onBtCancelAction() {
		MainRightPaneProductData.setVisible(false);
	}

	@FXML
	private void onBtClearAction() {
		txtId.setText("");
		updateTableView(service);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ProductService service = new ProductService();
		initializeNodes();
		updateTableView(service);
		Constraints.setTextFieldInteger(txtId);
		

	}

	public void updateTableView(ProductService service) {
		if (service == null) {
			Alerts.showAlert("falha de conexão", null, "serviço nao disponivel!", AlertType.INFORMATION);
		}
		List<Product> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewProduct.setItems(obsList);
	}

	public void setTableViewProduct() {
		Integer registration;
		if (txtId.getText() == null || txtId.getText() == "") {
			registration = null;
			lbError.setText("Insira o código!");
		} else {
			registration = Integer.parseInt(txtId.getText().toString());
			lbError.setText("");
		}

		if (txtId.getText() == null || txtId.getText() == "") {
			updateTableView(service);
		}

		if (registration != null) {
			List<Product> list = new ArrayList<>();
			Product obj = service.findById(registration);
			if (obj != null) {
				list.add(obj);
			} else if (txtId.getText() == null || txtId.getText() == "") {
				updateTableView(service);
				Alerts.showAlert("Procurando!", null, "Produto não encontrado!", AlertType.INFORMATION);
			}
			obsList = FXCollections.observableArrayList(list);
			tableViewProduct.setItems(obsList);
		}
	}

	public void initializeNodes() {
		tableColumnCod.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));		
		tableColumnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		Utils.formatTableColumnDouble(tableColumnValue, 2);
	}

}
