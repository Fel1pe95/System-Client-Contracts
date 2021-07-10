package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.service.ClientService;
import model.service.ProductService;

public class MainViewController implements Initializable {
	@FXML
	private ImageView logo;
	@FXML
	private Button buttonRegister;
	@FXML
	private Button buttonNewProduct;
	@FXML
	private Button buttonClientData;
	@FXML
	private Button buttonContractUpdate;
	@FXML
	private Button buttonProductConsult;
	@FXML
	private Button buttonCloseApp;
	@FXML
	private Label labelInformation;
	@FXML
	private Pane MainRightPane;

	@FXML
	public void onButtonRegisterAction() {
		LoadView("/gui/NewClientView.fxml", (NewClientViewController controller) -> {
			controller.setService(new ClientService());
		});
	}

	@FXML
	public void onButtonNewProductAction() {
		LoadView("/gui/NewProductView.fxml", (NewProductViewController controller) -> {
			controller.setService(new ProductService());
		});
	}

	@FXML
	public void onButtonClientDataAction() {
		LoadView("ClientDataView.fxml", (ClientDataViewController controller) -> {
			controller.setService(new ClientService());
		});
	}

	@FXML
	public void onButtonContractUpdateAction() {
	}

	@FXML
	private void onButtonProductConsultAction() {
	}

	@FXML
	public void onButtonCloseAppAction(ActionEvent event) {
		Stage stage = Utils.currentStage(event);
		stage.close();
	}

	@FXML
	public void onButtonRegisterEnteredMouse() {
		labelInformation.setText("Clique para cadastrar um novo cliente.");
	}

	@FXML
	public void onButtonNewProductEnteredMouse() {
		labelInformation.setText("Clique para cadastrar um novo produto.");
	}

	@FXML
	public void onButtonClientEnteredMouse() {
		labelInformation.setText("Clique para verificar os dados de um cliente.");
	}

	@FXML
	public void onButtonContractUpdateEnteredMouse() {
		labelInformation.setText("Clique para atualizar um contrato.");
	}

	@FXML
	public void onButtonConsultEnteredMouse() {
		labelInformation.setText("Clique para consultar os dados de um produto");
	}

	@FXML
	public void onButtonExited() {
		labelInformation.setText("");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setImage("logo.jpg", logo);

	}

	public void setImage(String image, ImageView view) {
		Image imagem = new Image(image);
		view.setImage(imagem);
		if (imagem != null) {
			double w = 0;
			double h = 0;
			double ratioX = logo.getFitWidth() / imagem.getWidth();
			double ratioY = logo.getFitHeight() / imagem.getHeight();
			double reducCoeff = 0;
			if (ratioX >= ratioY) {
				reducCoeff = ratioY;
			} else {
				reducCoeff = ratioX;
			}
			w = imagem.getWidth() * reducCoeff;
			h = imagem.getHeight() * reducCoeff;
			logo.setX((logo.getFitWidth() - w) / 2);
			logo.setY((logo.getFitHeight() - h) / 2);
		}
	}

	private synchronized <T> void LoadView(String view, Consumer<T> initializingAction) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
			Pane newPane = loader.load();
			MainRightPane.getChildren().clear();
			MainRightPane.getChildren().addAll(newPane);
			T controller = loader.getController();
			initializingAction.accept(controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Pane getPane() {
		return MainRightPane;
	}
}
