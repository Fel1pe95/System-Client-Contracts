package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
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
import model.entities.Client;
import model.service.ClientService;

public class ClientDataViewController implements Initializable {

	private Client entity;
	private ClientService service;

	public void setClient(Client entity) {
		this.entity = entity;
	}

	public void setService(ClientService service) {
		this.service = service;
	}

	@FXML
	private TextField txtRegistration;
	@FXML
	private Button btFind;
	@FXML
	private Button btCancel;
	@FXML
	private TableView<Client> tableViewClient;

	@FXML
	private TableColumn<Client, String> tableColumnName;
	@FXML
	private TableColumn<Client, String> tableColumnEmail;
	@FXML
	private TableColumn<Client, Integer> tableColumnCpf;
	@FXML
	private TableColumn<Client, Integer> tableColumnRegistration;

	private ObservableList<Client> obsList;
	
	@FXML
	private Label lbError;

	@FXML
	private VBox MainRightPaneClientData;

	@FXML
	public void onBtFindAction() {
		setTableViewClient();
	}

	@FXML
	public void onBtCancelAction() {
		MainRightPaneClientData.setVisible(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ClientService service = new ClientService();
		initializeNodes();
		updateTableView(service);
		Constraints.setTextFieldInteger(txtRegistration);

	}

	public void updateTableView(ClientService service) {
		if (service == null) {
			Alerts.showAlert("falha de conexão", null, "serviço nao disponivel!", AlertType.INFORMATION);
		}
		List<Client> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewClient.setItems(obsList);
	}

	public void setTableViewClient() {
		Integer registration;
		if (txtRegistration.getText() == null || txtRegistration.getText() == "") {
			registration = null;
			lbError.setText("Insira a matricula!");
		} else {
			registration = Integer.parseInt(txtRegistration.getText().toString());
			lbError.setText("");
		}

		if (txtRegistration.getText() == null || txtRegistration.getText() == "") {
			updateTableView(service);
		}

		if (registration != null) {
			List<Client> list = new ArrayList<>();
			Client obj = service.findById(registration);
			if (obj != null) {
				list.add(obj);
			} else if (txtRegistration.getText() == null || txtRegistration.getText() == "") {
				updateTableView(service);
				Alerts.showAlert("Procurando!", null, "matricula não encontrada!", AlertType.INFORMATION);
			}
			obsList = FXCollections.observableArrayList(list);
			tableViewClient.setItems(obsList);
		}
	}

	public void initializeNodes() {
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tableColumnRegistration.setCellValueFactory(new PropertyValueFactory<>("registration"));
	}

}
