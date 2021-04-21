package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Filial;

public class ListaFilialController implements Initializable{

	@FXML
	private TableView<Filial> tableViewFilial;
	
	@FXML
	private TableColumn<Filial, Integer> tableColumnCodigo;
	
	@FXML
	private TableColumn<Filial, String> tableColumnNome;
	
	@FXML
	private Button btNovo;
	
	@FXML
	public void onBtNovoAction() {
		System.out.println("DebugConsole: onBtNovoAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		Stage stage =(Stage) Main.getMainScene().getWindow();
		tableViewFilial.prefHeightProperty().bind(stage.heightProperty());
		
	}

}