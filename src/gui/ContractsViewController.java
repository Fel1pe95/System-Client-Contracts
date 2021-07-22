package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.Client;
import model.entities.Contracts;
import model.entities.Locations;
import model.service.ClientService;
import model.service.ContractService;
import model.service.LocationsService;

public class ContractsViewController implements Initializable {

	private Client client;
	private ClientService clientService;
	private ContractService contractService;
	private LocationsService locationsService;

	public void setServices(LocationsService locationsService, ContractService contractService,
			ClientService clientService) {
		this.contractService = contractService;
		this.locationsService = locationsService;
		this.clientService = clientService;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@FXML
	private ComboBox<Contracts> comboBoxContracts;
	@FXML
	private Label labelContract;
	@FXML
	private Button btClose;

	@FXML
	private TableView<Locations> tableViewLocation;
	@FXML
	private TableColumn<Locations, Locations> tableColumnQuantity;
	@FXML
	private TableColumn<Locations, Locations> tableColumnProductName;
	@FXML
	private TableColumn<Locations, Locations> tableColumnTotalValue;

	private ObservableList<Contracts> obsListContracts;
	private ObservableList<Locations> obsListLocations;

	@FXML
	public void onBtCloseAction(ActionEvent event) {
		Stage stage = Utils.currentStage(event);
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		initializeComboBoxContracts();
	}

	public void initializeNodes() {
		tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		tableColumnProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
		tableColumnTotalValue.setCellValueFactory(new PropertyValueFactory<>("totalValue"));

	}
	
	public void updateTableView(LocationsService service, Integer id) {
		if (service == null) {
			Alerts.showAlert("falha de conexão", null, "serviço nao disponivel!", AlertType.INFORMATION);
		}
		List<Locations> list = service.findAll(id);
		obsListLocations = FXCollections.observableArrayList(list);
		tableViewLocation.setItems(obsListLocations);
	}

	public void loadAssociateObjects() {

		if (contractService == null) {
			Alerts.showAlert("Erro de conexão", null, "Serviço não disponivel!", AlertType.ERROR);
		}
		if (clientService == null) {
			Alerts.showAlert("Erro de conexão", null, "Serviço não disponivel!", AlertType.ERROR);
		}
		Integer id = client.getRegistration();
		List<Contracts> list = contractService.findAll(id);
		obsListContracts = FXCollections.observableArrayList(list);
		comboBoxContracts.setItems(obsListContracts);
		comboBoxContracts.getSelectionModel();
	}

	private void initializeComboBoxContracts() {

		Callback<ListView<Contracts>, ListCell<Contracts>> factory = lv -> new ListCell<Contracts>() {
			@Override
			protected void updateItem(Contracts item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : String.format("%d", item.getContractId()));

			}

		};

		comboBoxContracts.setCellFactory(factory);
		comboBoxContracts.setButtonCell(factory.call(null));

		comboBoxContracts.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Contracts obj = comboBoxContracts.getSelectionModel().getSelectedItem();
				labelContract.setText(obj.toString());
				updateTableView(locationsService, obj.getContractId());
			}
		});
	}

}
