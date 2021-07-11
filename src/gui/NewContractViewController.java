package gui;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.service.ClientService;

public class NewContractViewController {
	
	private ClientService service;
	
	public void setService(ClientService Service) {
		this.service = service;
	}

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
	public void onBtSaveAction() {}
	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Stage stage = Utils.currentStage(event);
		stage.close();
		
	}
	
}
