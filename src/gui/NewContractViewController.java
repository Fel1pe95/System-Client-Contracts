package gui;

import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Client;
import model.entities.Contracts;
import model.entities.Locations;
import model.entities.Product;
import model.service.ClientService;
import model.service.ContractService;
import model.service.LocationsService;
import model.service.ProductService;

public class NewContractViewController implements Initializable {

	private Contracts contractsEntity;
	private Client clientEntity;
	private ClientService clientService;
	private ProductService productService;
	private ContractService contractService;
	private LocationsService locationsService;

	private List<Locations> list = new ArrayList<Locations>();

	@FXML
	private TextField txtContractId;
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
	private Label labelTotalValue;
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
	public void onBtSaveAction(ActionEvent event) {

		Contracts contract = instantiateContract();
		contractService.save(contract);
		locationsService.saveLocations(list);
		Stage stage = Utils.currentStage(event);
		stage.close();

	}

	@FXML
	public void onBtAddProductAction() {
		if (txtProductId.getText() == null || txtProductId.getText() == "" || txtProductQuantity.getText() == null
				|| txtProductQuantity.getText() == "") {
			erroMenssage.setText("Necesario codigo e quantidade do produto!");
		} else {
			
			updateTableView();
			txtProductId.setText("");
			txtProductQuantity.setText("");
			erroMenssage.setText("");
		}
		double totalValue = 0;
		for (Locations loc : list) {
			totalValue += loc.getTotalValue();
		}
		labelTotalValue.setText(String.format("%.2f" + " R$", totalValue));

	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Stage stage = Utils.currentStage(event);
		stage.close();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productService = new ProductService();
		contractService = new ContractService();
		locationsService = new LocationsService();
		Constraints.setTextFieldInteger(txtRegistration);
		Constraints.setTextFieldInteger(txtProductId);
		Constraints.setTextFieldInteger(txtProductQuantity);
		Utils.formatDatePicker(datePickerInitialDate, "dd/MM/yyyy");
		Utils.formatDatePicker(datePickerFinalDate, "dd/MM/yyyy");
		Utils.formatTableColumnDouble(tableColumnValueTotal, 2);
		initiaizeNodes();

	}

	public Locations instantiateLocation() {
		try {
			Integer id = Integer.parseInt(txtContractId.getText().toString());
			Integer codProduct = Integer.parseInt(txtProductId.getText().toString());
			Product obj = productService.findById(codProduct);
			String name = obj.getName();
			Integer cod = obj.getId();
			Integer quantity = Integer.parseInt(txtProductQuantity.getText().toString());
			Double totalValue = obj.getValue() * quantity;
			Locations location = new Locations();

			location = new Locations(id, cod, name, quantity, totalValue);
			return location;
		} catch (NullPointerException e) {
			Alerts.showAlert("Código inválido", null, "Produto não encontrado!", AlertType.ERROR);
		}
		return null;
	}

	public Contracts instantiateContract() {

		Integer contractId = Integer.parseInt(txtContractId.getText().toString());
		Integer clientId = Integer.parseInt(txtRegistration.getText().toString());
		Instant initialInstant = Instant.from(datePickerInitialDate.getValue().atStartOfDay(ZoneId.systemDefault()));
		Date initialDate = Date.from(initialInstant);
		Instant finallInstant = Instant.from(datePickerFinalDate.getValue().atStartOfDay(ZoneId.systemDefault()));
		Date finalDate = Date.from(finallInstant);

		double totalValue = 0;

		for (Locations loc : list) {
			totalValue += loc.getTotalValue();
		}

		return new Contracts(contractId, clientId, initialDate, finalDate, totalValue);

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

	public void updateFormData() {

		if (clientEntity == null) {
			Alerts.showAlert("Erro de conexão", null, "Serviõ não disponível!", AlertType.ERROR);
		}
		contractsEntity = newContract();
		txtContractId.setText(contractsEntity.getContractId().toString());
		txtRegistration.setText(clientEntity.getRegistration().toString());
	}

	public void initiaizeNodes() {
		tableColumnCod.setCellValueFactory(new PropertyValueFactory<>("cod"));
		tableColumnProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
		tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		tableColumnValueTotal.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
	}

	public void setService(ClientService clientService) {
		this.clientService = clientService;

	}

	public void setClient(Client obj) {
		this.clientEntity = obj;
	}

	public Contracts newContract() {
		Random random = new Random();
		Integer contractId = random.nextInt(1000) + 1000;

		contractsEntity = contractService.findById(contractId);

		if (contractsEntity == null) {
			Contracts obj = new Contracts();
			obj.setContractId(contractId);
			return obj;
		} else {
			newContract();
		}

		return null;

	}

}
