package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Filial;
import model.services.FilialService;

public class ListaFilialController implements Initializable, DataChangeListener {

	private FilialService service;

	@FXML
	private TableView<Filial> tableViewFilial;

	@FXML
	private TableColumn<Filial, Integer> tableColumnCodigo;

	@FXML
	private TableColumn<Filial, String> tableColumnNome;

	@FXML
	TableColumn<Filial, Filial> tableColumnEDIT;

	@FXML
	private Button btNovo;

	private ObservableList<Filial> obsList;

	@FXML
	public void onBtNovoAction(ActionEvent event) {
		System.out.println("DebugConsole: onBtNovoAction");

		Stage parentStage = Utils.currentStage(event);

		Filial obj = new Filial();

		createDialogForm(obj, "/gui/FilialForm.fxml", parentStage);
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

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewFilial.prefHeightProperty().bind(stage.heightProperty());

	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service nulo.");
		}

		List<Filial> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewFilial.setItems(obsList);
		initEditButtons();

	}

	// Carregar Janela do Form
	private void createDialogForm(Filial obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			FilialFormController controller = loader.getController();
			controller.setFilial(obj);
			controller.setFilialService(new FilialService());

			controller.subscribeDataChangeListener(this);

			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados da Filial");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar tela", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
	}

	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Filial, Filial>() {
			private final Button button = new Button("Editar");

			@Override
			protected void updateItem(Filial obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/FilialForm.fxml", Utils.currentStage(event)));
			}
		});
	}

}