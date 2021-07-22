package gui;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import gui.util.Constraints;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.entities.Product;
import model.exception.ValidationException;
import model.service.ProductService;

public class NewProductViewController implements Initializable {

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
	private Label txtErrorId;
	@FXML
	private Label txtErrorName;
	@FXML
	private Label txtErrorValue;

	@FXML
	public void onBtSaveAction() {
		try {
			entity = getDataProduct();
			service.save(entity);
		} catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		}

	}

	@FXML
	public void onBtCancelAction() {
		newProductPane.setVisible(false);
	}

	private Product getDataProduct() {
		Product obj = new Product();
		ValidationException except = new ValidationException("Validation Errors");
		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			except.addErrors("txtName", " *");
		}
		obj.setName(txtName.getText());
		if (txtId.getText() == null || txtId.getText().trim().equals("")) {
			except.addErrors("txtId", " *");
		}
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		
		if (txtValue.getText() == null || txtValue.getText().trim().equals("")) {
			except.addErrors("txtValue", " *");
		}
		obj.setValue(Utils.tryParseToDouble(txtValue.getText()));
		if (except.getErrors().size() > 0) {
			throw except;
		}
		return obj;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldDouble(txtValue);
		Constraints.setTextFieldMaxLength(txtName, 20);

	}

	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();

		txtErrorId.setText((fields.contains("txtId") ? errors.get("txtId") : ""));

		txtErrorName.setText((fields.contains("txtName") ? errors.get("txtName") : ""));

		txtErrorValue.setText((fields.contains("txtValue") ? errors.get("txtValue") : ""));

	}

}
