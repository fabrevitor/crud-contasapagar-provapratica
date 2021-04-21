package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Filial;
import model.services.FilialService;

public class ListaFilialController implements Initializable{

	private FilialService service; 
	
	@FXML
	private TableView<Filial> tableViewFilial;
	
	@FXML
	private TableColumn<Filial, Integer> tableColumnCodigo;
	
	@FXML
	private TableColumn<Filial, String> tableColumnNome;
	
	@FXML
	private Button btNovo;
	
	private ObservableList<Filial> obsList;
	
	
	@FXML
	public void onBtNovoAction() {
		System.out.println("DebugConsole: onBtNovoAction");
	}
	
	public void setFilialService(FilialService service) {
		this.service = service;
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
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service nulo.");
		}
		
		List<Filial> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewFilial.setItems(obsList);
		
	}

}