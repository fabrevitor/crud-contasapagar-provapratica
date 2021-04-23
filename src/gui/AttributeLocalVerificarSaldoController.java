package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.entities.AttributeLocal;
import model.services.AttributeLocalService;

public class AttributeLocalVerificarSaldoController implements Initializable{

	@FXML
	private Label labelSaldoTotal;
	
//	private AttributeLocal entity;
	
	private AttributeLocalService service;
	
//	public void setAttributeLocal(AttributeLocal entity) {
//		this.entity = entity;
//	}
	
	public void setAttributeLocalService(AttributeLocalService service) {
		this.service = service;
	}
	
	
	public void getLabelData() {
		AttributeLocal obj = service.find();
		labelSaldoTotal.setText("Seu saldo é de: R$" + Double.toString(obj.getSaldoTotal()));
	} 
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	

}
