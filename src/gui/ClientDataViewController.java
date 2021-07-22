package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Client;
import model.service.ClientService;
import model.service.ContractService;
import model.service.LocationsService;

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
	private Button btClear;
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
	@FXML
	private TableColumn<Client, Client> tableColumnNewContract;
	@FXML
	private TableColumn<Client, Client> tableColumnContracts;

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

	@FXML
	private void onBtClearAction() {
		txtRegistration.setText("");
		updateTableView(service);
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
		initNewContractButton();
		initContractsButton();
	}

	private void initNewContractButton() {
		tableColumnNewContract.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnNewContract.setCellFactory(param -> new TableCell<Client, Client>() {
			private final Button button = new Button("Novo contrato");

			@Override
			protected void updateItem(Client obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> LoadViewNewContract(obj, "/gui/NewContractView.fxml",
						Utils.currentStage(event), (NewContractViewController controller) -> {
							controller.setService(new ClientService());
						}));
			}
		});
	}

	private void initContractsButton() {
		tableColumnContracts.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnContracts.setCellFactory(param -> new TableCell<Client, Client>() {
			private final Button button = new Button("Contratos");

			@Override
			protected void updateItem(Client obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> LoadContratcView(obj, "/gui/ContractsView.fxml", Utils.currentStage(event),
						(ContractsViewController controller) -> {
						}));
			}
		});
	}

	private synchronized <T> void LoadViewNewContract(Client obj, String view, Stage parentStage,
			Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
			Pane newPane = loader.load();
			NewContractViewController controllerView = loader.getController();
			controllerView.setClient(obj);
			controllerView.updateFormData();
			Scene scene = new Scene(newPane);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Dados para novo Contrato");
			stage.initOwner(parentStage);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.showAndWait();

			T controller = loader.getController();
			initializingAction.accept(controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private synchronized <T> void LoadContratcView(Client obj, String view, Stage parentStage,
			Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
			Pane newPane = loader.load();
			ContractsViewController ViewController = loader.getController();
			ViewController.setServices(new LocationsService(), new ContractService(), new ClientService());
			ViewController.setClient(obj);
			ViewController.loadAssociateObjects();
			Scene scene = new Scene(newPane);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Contratos");
			stage.initOwner(parentStage);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.showAndWait();

			T controller = loader.getController();
			initializingAction.accept(controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
