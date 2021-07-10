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
import model.entities.Product;
import model.service.ProductService;

public class NewProductViewController implements Initializable{
	
	private Product entity;
	private ProductService service;
	
	public void setProduct(Product entity) {
		this.entity = entity;
	}
	public void setService(ProductService service) {
		this.service = service;
	}
	
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
	public void onBtSaveAction() {
		entity = getDataProduct();
		service.save(entity);
	}
	@FXML
	public void onBtCancelAction() {
		newProductPane.setVisible(false);
	}
	
	private Product getDataProduct() {
		Product obj = new Product();
		obj.setName(txtName.getText());
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setValue(Double.parseDouble(txtValue.getText().toString()));
		return obj;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldDouble(txtValue);
		Constraints.setTextFieldMaxLength(txtName, 20);
		
	}

}
