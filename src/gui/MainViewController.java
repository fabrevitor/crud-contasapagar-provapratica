package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.AttributeLocalService;
import model.services.ContasService;
import model.services.FilialService;

public class MainViewController implements Initializable {
	
	@FXML
	private MenuItem menuItemFilial;
	
	@FXML
	private MenuItem menuItemContas;
	
	@FXML
	private MenuItem menuItemVerificar;
	
	@FXML
	private MenuItem menuItemSobre;
	
	// Utilizando Expressão Lambda para fazer a ação do Botão de Cadastrar FILIAL
	@FXML
	public void onMenuItemFilalAction() {
		System.out.println("DebugConsole: onMenuItemFilialAction");
		loadView("/gui/ListaFilial.fxml", (ListaFilialController controller) -> {
			controller.setFilialService(new FilialService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemContasAction() {
		System.out.println("DebugConsole: onMenuItemContasAction");
		loadView("/gui/ListaContas.fxml", (ListaContasController controller) -> {
			controller.setContasService(new ContasService());
			controller.updateTableView();
		});
	}
	
	
	@FXML
	public void onMenuItemVerificarAction() {
		System.out.println("DebugConsole: onMenuItemVerificarAction");
		loadView("/gui/Verificar.fxml", (AttributeLocalVerificarSaldoController controller) -> {
			controller.setAttributeLocalService(new AttributeLocalService());
			controller.getLabelData();
		});
	}
	
	
	// Como o MenuItem Sobre não tem ação alguma, envia a Expressão Lambda vazia.
	@FXML
	public void onMenuItemSobreAction() {
		System.out.println("DebugConsole: onMenuItemSobreAction");
		loadView("/gui/Sobre.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a View", e.getMessage(), AlertType.ERROR);
		}
	}
	
}
