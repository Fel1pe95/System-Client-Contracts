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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Locations;
import model.entities.Product;
import model.service.ClientService;
import model.service.ProductService;

public class NewContractViewController implements Initializable {

	private ClientService service;
	private ProductService productService;

	public void setService(ClientService Service) {
		this.service = service;
	}

	private List<Locations> list = new ArrayList<Locations>();

	@FXML
	private TextField txtRegistration;
	@FXML
	private TextField txtProductId;
	@FXML
	private TextField txtProductQuantity;
	@FXML
	private DatePicker datePickerInitialDate;
	@FXML
	private DatePicker datePickerFinalDate;
	@FXML
	private TextField txtContractValue;
	@FXML
	private Label erroMenssage;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	@FXML
	private Button btAddProduct;

	@FXML
	private TableView<Locations> tableViewLocations;
	@FXML
	private TableColumn<Product, Integer> tableColumnCod;
	@FXML
	private TableColumn<Product, String> tableColumnProduct;
	@FXML
	private TableColumn<Product, Integer> tableColumnQuantity;
	@FXML
	private TableColumn<Product, Double> tableColumnValueTotal;

	private ObservableList<Locations> obsList;

	@FXML
	public void onBtSaveAction() {
	}

	@FXML
	public void onBtAddProductAction() {
		if (txtProductId.getText() == null || txtProductId.getText() == "" || txtProductQuantity.getText() == null
				|| txtProductQuantity.getText() == "") {
			erroMenssage.setText("Necesario codigo e quantidade do produto!");
		} else {
			updateTableView();
			erroMenssage.setText("");
		}

	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Stage stage = Utils.currentStage(event);
		stage.close();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productService = new ProductService();
		Constraints.setTextFieldInteger(txtRegistration);
		Constraints.setTextFieldInteger(txtProductId);
		Constraints.setTextFieldInteger(txtProductQuantity);
		Constraints.setTextFieldDouble(txtContractValue);
		Utils.formatDatePicker(datePickerInitialDate, "dd/MM/yyyy");
		Utils.formatDatePicker(datePickerFinalDate, "dd/MM/yyyy");
		Utils.formatTableColumnDouble(tableColumnValueTotal, 2);
		initiaizeNodes();

	}

	public Locations instantiateLocation() {
		try {
			Integer codProduct = Integer.parseInt(txtProductId.getText().toString());
			Product obj = productService.findById(codProduct);
			String name = obj.getName();
			Integer cod = obj.getId();
			Integer quantity = Integer.parseInt(txtProductQuantity.getText().toString());
			Double totalValue = obj.getValue() * quantity;
			Locations location = new Locations();

			location = new Locations(cod, name, quantity, totalValue);
			return location;
		} catch (NullPointerException e) {
			Alerts.showAlert("Código inválido", null, "Produto não encontrado!", AlertType.ERROR);
		}
		return null;
	}

	public void updateTableView() {
		if (productService == null) {
			Alerts.showAlert("falha de conexão", null, "serviço nao disponivel!", AlertType.INFORMATION);
		}

		Locations location = instantiateLocation();
		if (location != null) {
			list.add(location);
			obsList = FXCollections.observableArrayList(list);
			tableViewLocations.setItems(obsList);
		}
	}

	public void initiaizeNodes() {
		tableColumnCod.setCellValueFactory(new PropertyValueFactory<>("cod"));
		tableColumnProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
		tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		tableColumnValueTotal.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
	}

}
