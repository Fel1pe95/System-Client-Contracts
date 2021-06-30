package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
	private Pane rightPane;

	@FXML
	public void onButtonRegisterAction() {
		LoadView("/gui/NewClientView.fxml");
	}

	@FXML
	public void onButtonNewProductAction() {
	}

	@FXML
	public void onButtonClientDataAction() {
	}

	@FXML
	public void onButtonContractUpdateAction() {
	}

	@FXML
	private void onButtonProductConsultAction() {
	}

	@FXML
	public void onButtonCloseAppAction() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setImage();
	}

	public void setImage() {
		Image imagem = new Image("logo.jpg");
		logo.setImage(imagem);
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

	public void LoadView(String view) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
			Pane newPane = loader.load();
			Pane mainPane = rightPane;
			mainPane.getChildren().clear();
			mainPane.getChildren().addAll(newPane.getChildren());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
