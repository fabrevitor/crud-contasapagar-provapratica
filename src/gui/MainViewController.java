package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemFilial;
	
	@FXML
	private MenuItem menuItemContas;
	
	@FXML
	private MenuItem menuItemSobre;
	
	
	@FXML
	public void onMenuItemFilalAction() {
		System.out.println("Debug: onMenuItemFilialAction");
	}
	
	@FXML
	public void onMenuItemContasAction() {
		System.out.println("Debug: onMenuItemContasAction");
	}
	
	@FXML
	public void onMenuItemSobreAction() {
		System.out.println("Debug: onMenuItemSobreAction");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		//TODO
	}

}
