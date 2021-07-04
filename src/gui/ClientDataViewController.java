package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.entities.Client;

public class ClientDataViewController implements Initializable {

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
	
	@FXML
	private VBox MainRightPaneClientData;

	@FXML
	public void onBtFindAction() {
		System.out.println("buscando!");
	}
	@FXML
	public void onBtCancelAction() {
		MainRightPaneClientData.setVisible(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldInteger(txtRegistration);

	}

}
