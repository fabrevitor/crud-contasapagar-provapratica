package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Filial;

public class FilialFormController implements Initializable{

	private Filial entity;
	
	
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
	
	public void updateFormData() {
		if(entity==null) {
			throw new IllegalStateException("Entidade nula.");
		}
		
		txtCodigo.setText(String.valueOf(entity.getCodigo()));
		txtNome.setText(entity.getNome());
	}
	
	@FXML
	public void onBtSalvarAction() {
		System.out.println("DebugConsole: onBtSalvarAction");
	}
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("DebugConsole: onBtCancelarAction");
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
