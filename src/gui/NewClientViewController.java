package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewClientViewController implements Initializable{

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
	public void onBtSaveAction() {}
	@FXML
	public void onBtCancelAction() {}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
