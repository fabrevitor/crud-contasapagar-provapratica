package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Contas;
import model.services.ContasService;
import model.services.FilialService;

public class ListaContasController implements Initializable, DataChangeListener {

	private ContasService service;

	@FXML
	private TableView<Contas> tableViewContas;

	@FXML
	private TableColumn<Contas, Integer> tableColumnCodigo;

	@FXML
	private TableColumn<Contas, String> tableColumnDescricao;
	
	@FXML
	private TableColumn<Contas, Date> tableColumnDataRegistro;

	@FXML
	private TableColumn<Contas, Boolean> tableColumnFoiPago;
	
	@FXML
	private TableColumn<Contas, Double> tableColumnValor;
	
	@FXML
	private TableColumn<Contas, Integer> tableColumnFilial;
	
	@FXML
	private TableColumn<Contas, Contas> tableColumnEDIT;

	@FXML
	private TableColumn<Contas, Contas> tableColumnREMOVE;

	@FXML
	private Button btNovo;

	private ObservableList<Contas> obsList;

	@FXML
	public void onBtNovoAction(ActionEvent event) {
		System.out.println("DebugConsole: onBtNovoAction");

		Stage parentStage = Utils.currentStage(event);

		Contas obj = new Contas();

		createDialogForm(obj, "/gui/ContasForm.fxml", parentStage);
	}

	public void setContasService(ContasService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tableColumnDataRegistro.setCellValueFactory(new PropertyValueFactory<>("dataRegistro"));
		// Formata Data
		Utils.formatTableColumnDate(tableColumnDataRegistro, "dd/MM/yyyy");
		
		tableColumnFoiPago.setCellValueFactory(new PropertyValueFactory<>("foiPago"));
		tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		//Formata Double
		Utils.formatTableColumnDouble(tableColumnValor, 2);
		
		tableColumnFilial.setCellValueFactory(new PropertyValueFactory<>("filNome"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewContas.prefHeightProperty().bind(stage.heightProperty());

	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service nulo.");
		}

		List<Contas> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewContas.setItems(obsList);
		initEditButtons();
		initRemoveButtons();

	}

	// Carregar Janela do Form
	private void createDialogForm(Contas obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ContasFormController controller = loader.getController();
			controller.setContas(obj);
			controller.setServices(new ContasService(), new FilialService());
			controller.loadAssociatedObjetcs();
			controller.subscribeDataChangeListener(this);

			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados da Conta");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Erro ao carregar tela", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
	}

	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Contas, Contas>() {
			private final Button button = new Button("Editar");

			@Override
			protected void updateItem(Contas obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(obj, "/gui/ContasForm.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Contas, Contas>() {
			private final Button button = new Button("Remover");

			@Override
			protected void updateItem(Contas obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}

	private void removeEntity(Contas obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmação", "Você realmente deseja deletar esse registro?");
		
		if(result.get()==ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Serviço nulo.");
			}
			try {
				service.remove(obj);
				updateTableView();
			} catch(DbIntegrityException e) {
				Alerts.showAlert("Erro ao remover", null, e.getMessage(), AlertType.ERROR);
			}
			
		}
	}

}