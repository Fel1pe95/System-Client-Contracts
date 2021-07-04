package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class NewProductViewController {
	
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtValue;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	@FXML
	private Pane newProductPane;
	
	@FXML
	public void onBtSaveAction() {}
	@FXML
	public void onBtCancelAction() {
		newProductPane.setVisible(false);
	}

}
