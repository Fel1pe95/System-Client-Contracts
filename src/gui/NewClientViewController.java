package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class NewClientViewController implements Initializable {

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
		System.out.println("Funcionado");
	}

	@FXML
	public void onBtCancelAction() {
		MainRightPaneNewClient.setVisible(false);
		
		
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
