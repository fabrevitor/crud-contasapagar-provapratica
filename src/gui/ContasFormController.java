package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
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
import model.entities.Contas;
import model.exceptions.ValidationException;
import model.services.ContasService;

public class ContasFormController implements Initializable{

	private Contas entity;
	
	private ContasService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtDescricao;
	
	@FXML
	private Label labelErrorDescricao;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;
	
	
	public void setContas(Contas entity) {
		this.entity = entity;
	}
	
	public void setContasService(ContasService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	//Pega os dados do Form e coloca num Obj.
	private Contas getFormData() {
		Contas obj = new Contas();
		
		ValidationException exception = new ValidationException("Erro na validação.");
		
		obj.setCodigo(Utils.tryParseToInt(txtCodigo.getText()));
		
		if(txtDescricao.getText()==null||txtDescricao.getText().trim().equals("")) {
			exception.addError("Descricao", "O campo não pode ser vazio.");
		}
		
		obj.setDescricao(txtDescricao.getText());
		
		if(exception.getErrors().size() > 0) {
			throw exception;
		}
		
		return obj;
	}
	
	//Atualiza os dados do Form de acordo com o que tem em tela.
	public void updateFormData() {
		if(entity==null) {
			throw new IllegalStateException("Entidade nula.");
		}
		
		txtCodigo.setText(String.valueOf(entity.getCodigo()));
		txtDescricao.setText(entity.getDescricao());
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
			
			notifyDataChangeListeners();
			
			Utils.currentStage(event).close();
		}
		catch(ValidationException e){
			setErrorMessages(e.getErrors());
		} 
		catch (DbException e) {
			Alerts.showAlert("Erro ao salvar o objeto", null, e.getMessage(), AlertType.ERROR);
		}	
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners){
			listener.onDataChanged();
		}
		
	}

	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		System.out.println("DebugConsole: onBtCancelarAction");
		Utils.currentStage(event).close();
	}

	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtCodigo);
		Constraints.setTextFieldMaxLength(txtDescricao, 60);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if (fields.contains("Descricao")) {
			labelErrorDescricao.setText(errors.get("Descricao"));
		}
	}
}
