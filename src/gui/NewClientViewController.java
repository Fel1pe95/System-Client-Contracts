package gui;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DBException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import model.entities.Client;
import model.exception.ValidationException;
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
	private Label txtErrorName;
	@FXML
	private Label txtErrorEmail;
	@FXML
	private Label txtErrorCpf;
	@FXML
	private Label txtErrorRegistration;

	@FXML
	private Pane MainRightPaneNewClient;

	@FXML
	public void onBtSaveAction() {
		try {
			entity = getDataClient();
			service.save(entity);
		} catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		} catch (DBException e) {
			Alerts.showAlert("Erro de conexão", null, "Erro ao salvar novo cliente", AlertType.ERROR);
		}
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
		ValidationException except = new ValidationException("Validation Error");
		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			except.addErrors("txtName", " *");
		}
		obj.setName(txtName.getText());
		if (txtEmail.getText() == null || txtEmail.getText().trim().equals("")) {
			except.addErrors("txtEmail", " *");
		}
		obj.setEmail(txtEmail.getText());
		if(txtCpf.getText() == null || txtCpf.getText().trim().equals("")) {
			except.addErrors("txtCpf", " *");
		}
		obj.setCpf(Utils.tryParseToInt(txtCpf.getText()));
		if (txtRegistration.getText() == null || txtRegistration.getText().trim().equals("")) {
			except.addErrors("txtRegistration", " *");
		}
		obj.setRegistration(Utils.tryParseToInt(txtRegistration.getText()));

		if (except.getErrors().size() > 0) {
			throw except;
		}
		return obj;
	}

	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();

		txtErrorName.setText((fields.contains("txtName") ? errors.get("txtName") : ""));

		txtErrorEmail.setText((fields.contains("txtEmail") ? errors.get("txtEmail") : ""));
		
		txtErrorCpf.setText((fields.contains("txtCpf") ? errors.get("txtCpf") : ""));

		txtErrorRegistration.setText((fields.contains("txtRegistration") ? errors.get("txtRegistration") : ""));

	}

	public void setClient(Client entity) {
		this.entity = entity;
	}

	public void setService(ClientService service) {
		this.service = service;

	}

}
