package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentServices;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}
	//M�todo que passa o nome da tela para para o LoadView
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	
	
	private synchronized void loadView(String absoluteName) {
		try {
			//FXMLLoader Carrega uma tela
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			
			VBox newVBox = loader.load();
			//Pega a Scene principal
			Scene mainScene = Main.getmainScene();
			//Acessa a tag content
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			//Dentro da tag Content acessa o primeiro Children
			Node mainMenu = mainVBox.getChildren().get(0);
			//limpa os Children da VBox MainView
			mainVBox.getChildren().clear();		
			// Adiciona o menu da MainView
			mainVBox.getChildren().add(mainMenu);
			// Adiciona a cole��o de children da view que for chamada
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private synchronized void loadView2(String absoluteName) {
		try {
			//FXMLLoader Carrega uma tela
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			
			VBox newVBox = loader.load();
			//Pega a Scene principal
			Scene mainScene = Main.getmainScene();
			//Acessa a tag content
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			//Dentro da tag Content acessa o primeiro Children
			Node mainMenu = mainVBox.getChildren().get(0);
			//limpa os Children da VBox MainView
			mainVBox.getChildren().clear();		
			// Adiciona o menu da MainView
			mainVBox.getChildren().add(mainMenu);
			// Adiciona a cole��o de children da view que for chamada
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			DepartmentListController controller = loader.getController();
			
			controller.setDepartmentServiice(new DepartmentServices());
			controller.updateTableView();
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
