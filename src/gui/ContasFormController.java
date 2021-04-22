package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Contas;
import model.entities.Filial;
import model.exceptions.ValidationException;
import model.services.ContasService;
import model.services.FilialService;

public class ContasFormController implements Initializable {

	private Contas entity;

	private ContasService service;

	private FilialService filialService;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtCodigo;

	@FXML
	private TextField txtDescricao;

	@FXML
	private TextField txtValor;

	@FXML
	private ComboBox<Filial> comboBoxFilial;

	@FXML
	private Label labelErrorDescricao;

	@FXML
	private Label labelErrorValor;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;

	private ObservableList<Filial> obsList;

	public void setContas(Contas entity) {
		this.entity = entity;
	}

	public void setServices(ContasService service, FilialService filialService) {
		this.service = service;
		this.filialService = filialService;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	// Pega os dados do Form e coloca num Obj.
	private Contas getFormData() {
		Contas obj = new Contas();

		ValidationException exception = new ValidationException("Erro na validação.");

		obj.setCodigo(Utils.tryParseToInt(txtCodigo.getText()));

		if (txtDescricao.getText() == null || txtDescricao.getText().trim().equals("")) {
			exception.addError("Descricao", "O campo não pode ser vazio.");
		}

		obj.setDescricao(txtDescricao.getText());

		if (exception.getErrors().size() > 0) {
			throw exception;
		}

		if (txtValor.getText() == null || txtValor.getText().trim().equals("")) {
			exception.addError("Valor", "O campo não pode ser vazio.");
		}
		
		obj.setValor(Utils.tryParseToDouble(txtValor.getText()));
		
		obj.setFilial(comboBoxFilial.getValue());
		
		return obj;
	}

	// Atualiza os dados do Form de acordo com o que tem em tela.
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entidade nula.");
		}

		txtCodigo.setText(String.valueOf(entity.getCodigo()));
		txtDescricao.setText(entity.getDescricao());

		Locale.setDefault(Locale.US);
		txtValor.setText(String.format("%.2f", entity.getValor()));

		
		if(entity.getFilial() == null) {
			comboBoxFilial.getSelectionModel().selectFirst();
		}else {
			comboBoxFilial.setValue(entity.getFilial());
		}
		
	}

	public void loadAssociatedObjetcs() {
		List<Filial> list = filialService.findAll();
		obsList = FXCollections.observableArrayList(list);
		comboBoxFilial.setItems(obsList);

	}

	@FXML
	public void onBtSalvarAction(ActionEvent event) {
		System.out.println("DebugConsole: onBtSalvarAction");

		if (entity == null) {
			throw new IllegalStateException("Entidade nula.");
		}
		if (service == null) {
			throw new IllegalStateException("Service nulo.");
		}

		try {
			entity = getFormData();
			service.saveOrUpdate(entity);

			notifyDataChangeListeners();

			Utils.currentStage(event).close();
		} catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		} catch (DbException e) {
			Alerts.showAlert("Erro ao salvar o objeto", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
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
		Constraints.setTextFieldDouble(txtValor);
		
		initializeComboBoxFilial();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();

		if (fields.contains("Descricao")) {
			labelErrorDescricao.setText(errors.get("Descricao"));
		} else {
			labelErrorDescricao.setText("");
		}
		
		if (fields.contains("Valor")) {
			labelErrorValor.setText(errors.get("Valor"));
		} else {
			labelErrorValor.setText("");
		}
		
	}

	private void initializeComboBoxFilial() {
		Callback<ListView<Filial>, ListCell<Filial>> factory = lv -> new ListCell<Filial>() {
			@Override
			protected void updateItem(Filial item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		comboBoxFilial.setCellFactory(factory);
		comboBoxFilial.setButtonCell(factory.call(null));
	}

}
