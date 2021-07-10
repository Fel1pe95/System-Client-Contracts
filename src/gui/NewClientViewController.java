package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.entities.Client;
import model.service.ClientService;

public class NewClientViewController implements Initializable {

	private Client entity;
	private ClientService service;

	@FXML
	private TextField txtName;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtRegistration;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;

	@FXML
	private Pane MainRightPaneNewClient;

	@FXML
	public void onBtSaveAction() {
		entity = getDataClient();
		service.save(entity);
	}

	@FXML
	public void onBtCancelAction() {
		MainRightPaneNewClient.setVisible(false);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldMaxLength(txtName, 40);
		Constraints.setTextFieldMaxLength(txtEmail, 60);
		Constraints.setTextFieldInteger(txtCpf);
		Constraints.setTextFieldInteger(txtRegistration);

	}

	private Client getDataClient() {
		Client obj = new Client();
		obj.setName(txtName.getText());
		obj.setEmail(txtEmail.getText());
		obj.setCpf(Utils.tryParseToInt(txtCpf.getText()));
		obj.setRegistration(Utils.tryParseToInt(txtRegistration.getText()));
		return obj;
	}

	public void setClient(Client entity) {
		this.entity = entity;
	}

	public void setService(ClientService service) {
		this.service = service;

	}

}
