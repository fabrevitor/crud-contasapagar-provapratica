package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Filial;
import model.services.FilialService;

public class FilialFormController implements Initializable{

	private Filial entity;
	
	private FilialService service;
	
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private Label labelErrorNome;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;
	
	
	public void setFilial(Filial entity) {
		this.entity = entity;
	}
	
	public void setFilialService(FilialService service) {
		this.service = service;
	}
	
	
	
	public void updateFormData() {
		if(entity==null) {
			throw new IllegalStateException("Entidade nula.");
		}
		
		txtCodigo.setText(String.valueOf(entity.getCodigo()));
		txtNome.setText(entity.getNome());
	}
	
	@FXML
	public void onBtSalvarAction(ActionEvent event) {
		System.out.println("DebugConsole: onBtSalvarAction");
		
		if(entity == null) {
			throw new IllegalStateException("Entidade nula.");
		}	
		if(service == null) {
			throw new IllegalStateException("Service nulo.");
		}
		
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("Erro ao salvar o objeto", null, e.getMessage(), AlertType.ERROR);
		}		
		
	}
	
	private Filial getFormData() {
		Filial obj = new Filial();
		
		obj.setCodigo(Utils.tryParseToInt(txtCodigo.getText()));
		obj.setNome(txtNome.getText());
		
		return obj;
	}

	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		System.out.println("DebugConsole: onBtCancelarAction");
		Utils.currentStage(event).close();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtCodigo);
		Constraints.setTextFieldMaxLength(txtNome, 60);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
}
