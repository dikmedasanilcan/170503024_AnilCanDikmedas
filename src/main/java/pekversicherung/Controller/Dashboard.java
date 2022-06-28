package pekversicherung.controller;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import org.w3c.dom.events.MouseEvent;
import pekversicherung.DatabaseCustomer;
import pekversicherung.DatabaseInsurance;
import pekversicherung.DatabaseEmployee;
import pekversicherung.Main;
import pekversicherung.model.*;
import pekversicherung.model.InsuranceContract.PersonType;
import pekversicherung.DatabasePerson;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    @FXML
    private Label personal_infos_text_name;

    @FXML
    private Label personal_infos_text_role;
    
    @FXML
    private Button leftMenu_btnKunden;

    @FXML
    private Button leftMenu_btnMitarbeitern;

    @FXML
    private Button leftMenu_btnVersicherung;

    @FXML
    private Button leftMenu_btnAbmelden;


    /* Scene StackPane */
    @FXML
    private StackPane sceneStackPane;

    /* Scene Header */
    @FXML
    private Label sceneUrl;

    @FXML
    private Label sceneName;

    /* Scene Contents */
    @FXML
    private VBox sceneKunden;

    @FXML
    private VBox sceneMitarbeitern;

    @FXML
    private VBox sceneVersicherung;

    /* Scene - Kunden */
    @FXML
    private HBox sceneKunden_buttons;

    @FXML
    private VBox sceneKunden_alleKunden;

    @FXML
    private VBox sceneKunden_neueKunden;

    @FXML
    private StackPane sceneKunden_stackpane;

    @FXML
    private Button sceneKunden_button_auflisten;

    @FXML
    private Button sceneKunden_button_neueKunden;
    
    @FXML
    private TextField sceneKunden_alleKunden_field_name;

    @FXML
    private TableView<Customer> sceneKunden_alleKunden_table;

    @FXML
    private TableColumn<Customer, String> sceneKunden_alleKunden_table_column_id;
    @FXML
    private TableColumn<Customer, String> sceneKunden_alleKunden_table_column_ausweisNummer;
    @FXML
    private TableColumn<Customer, String> sceneKunden_alleKunden_table_column_vorName;
    @FXML
    private TableColumn<Customer, String> sceneKunden_alleKunden_table_column_nachName;
    @FXML
    private TableColumn<Customer, String> sceneKunden_alleKunden_table_column_geburstDatum;
    @FXML
    private TableColumn<Customer, String> sceneKunden_alleKunden_table_column_telefonNummer;
    @FXML
    private TableColumn<Customer, String> sceneKunden_alleKunden_table_column_adresse;

    @FXML
    private Button sceneKunden_alleKunden_button_delete;


    /* Scene - Kunden (neue Kunden) */
    @FXML
    private TextField sceneKunden_neueKunden_field_vorname;

    @FXML
    private TextField sceneKunden_neueKunden_field_nachname;

    @FXML
    private TextField sceneKunden_neueKunden_field_id;

    @FXML
    private TextField sceneKunden_neueKunden_field_geburstdatum;

    @FXML
    private TextField sceneKunden_neueKunden_field_telefonnummer;

    @FXML
    private TextField sceneKunden_neueKunden_field_adresse;

    @FXML
    private HBox sceneMitarbeiter_buttons;

    @FXML
    private VBox sceneMitarbeiter_alleMitarbeiter;

    @FXML
    private VBox sceneMitarbeiter_neueMitarbeiter;

    @FXML
    private StackPane sceneMitarbeiter_stackpane;

    @FXML
    private Button sceneMitarbeiter_button_auflisten;

    @FXML
    private Button sceneMitarbeiter_button_neueMitarbeiter;

    /* Scene - Mitarbeiter (alle Mitarbeiter) */
    @FXML
    private TextField sceneMitarbeiter_alleMitarbeiter_field_name;
    
    @FXML
    private TableView<Employee> sceneMitarbeiter_alleMitarbeiter_table;

    @FXML
    private TableColumn<Employee, String> sceneMitarbeiter_alleMitarbeiter_table_column_id;
    @FXML
    private TableColumn<Employee, String> sceneMitarbeiter_alleMitarbeiter_table_column_ausweisNummer;
    @FXML
    private TableColumn<Employee, String> sceneMitarbeiter_alleMitarbeiter_table_column_vorName;
    @FXML
    private TableColumn<Employee, String> sceneMitarbeiter_alleMitarbeiter_table_column_nachName;
    @FXML
    private TableColumn<Employee, String> sceneMitarbeiter_alleMitarbeiter_table_column_role;
    @FXML
    private TableColumn<Employee, String> sceneMitarbeiter_alleMitarbeiter_table_column_geburstDatum;
    @FXML
    private TableColumn<Employee, String> sceneMitarbeiter_alleMitarbeiter_table_column_telefonNummer;
    @FXML
    private TableColumn<Employee, String> sceneMitarbeiter_alleMitarbeiter_table_column_adresse;

    @FXML
    private Button sceneMitarbeiter_alleMitarbeiter_button_delete;

    /* Scene - Mitarbeiter (neue Mitarbeiter) */
    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_vorname;

    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_nachname;

    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_id;

    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_role;

    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_telefonnummer;

    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_adresse;


    @FXML
    private HBox sceneVersicherung_buttons;

    @FXML
    private VBox sceneVersicherung_alleVertraege;

    @FXML
    private VBox sceneVersicherung_neueVertraege_erstellen;

    @FXML
    private StackPane sceneVersicherung_stackpane;

    @FXML
    private VBox sceneVersicherung_VersicherungsTypen;

    @FXML
    private Button sceneVersicherung_button_vertraege_auflisten;

    @FXML
    private Button sceneVersicherung_button_vertraege_erstellen;

    @FXML
    private Button sceneVersicherung_button_typen_bearbeiten;

    @FXML
    private TextField sceneVersicherung_alleVertraege_field_name;

    @FXML
    private TableView<InsuranceContract> sceneVersicherung_alleVertraege_table;

    @FXML
    private TableColumn<InsuranceContract, String> sceneVersicherung_alleVertraege_table_column_id;

    @FXML
    private TableColumn<InsuranceContract, String> sceneVersicherung_alleVertraege_table_column_versicherungstyp;

    @FXML
    private TableColumn<InsuranceContract, String> sceneVersicherung_alleVertraege_table_column_vorName;

    @FXML
    private TableColumn<InsuranceContract, String> sceneVersicherung_alleVertraege_table_column_nachName;

    @FXML
    private TableColumn<InsuranceContract, String> sceneVersicherung_alleVertraege_table_column_ausweisNummer;

    @FXML
    private TableColumn<InsuranceContract, String> sceneVersicherung_alleVertraege_table_column_persontyp;

    @FXML
    private TableColumn<InsuranceContract, String> sceneVersicherung_alleVertraege_table_column_startDatum;

    @FXML
    private TableColumn<InsuranceContract, String> sceneVersicherung_alleVertraege_table_column_endDatum;

    @FXML
    private Button sceneVersicherung_alleVertraege_button_delete;

    @FXML
    private TextField sceneVersicherung_VersicherungsTypen_field_name;

    @FXML
    private TableView<InsuranceType> sceneVersicherung_VersicherungsTypen_table;

    @FXML
    private TableColumn<InsuranceType, String> sceneVersicherung_VersicherungsTypen_table_column_id;

    @FXML
    private TableColumn<InsuranceType, String> sceneVersicherung_VersicherungsTypen_table_column_typname;

    @FXML
    private Button sceneVersicherung_VersicherungsTypen_button_delete;

    @FXML
    private TextField sceneVersicherung_neueVertraege_field_id;

    @FXML
    private ComboBox<PersonType> sceneVersicherung_neueVertraege_combobox_persontyp = new ComboBox<>();

    @FXML
    private ComboBox<InsuranceType> sceneVersicherung_neueVertraege_combobox_versicherungstyp = new ComboBox<>();

    @FXML
    private DatePicker sceneVersicherung_neueVertraege_datepicker_startdatum;

    @FXML
    private DatePicker sceneVersicherung_neueVertraege_datepicker_enddatum;

    private ObservableList<Customer> datenKunden = FXCollections.observableArrayList();
    private ObservableList<Employee> datenMitarbeiter = FXCollections.observableArrayList();
    private ObservableList<InsuranceType> datenVersicherungsTypen = FXCollections.observableArrayList();
    private ObservableList<InsuranceContract> datenVersicherungsVertraege = FXCollections.observableArrayList();


    /* Override Methods */

    @FXML
    private void handleLeftMenuButtonClicks(ActionEvent event)
    {
        if(event.getSource() == leftMenu_btnAbmelden)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Abmelden");
            alert.setContentText("Möchten Sie sich wirklich abmelden?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                try{
                    Main main = new Main();
                    main.loadLogin();
                    main.getAccount().logout();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            return;
        }
        
        clearSceneHeader();
        hideAllSceneItems();
        if (event.getSource() == leftMenu_btnKunden)
        {
            sceneUrl.setText("/versicherung/kunden");
            sceneName.setText("Kunden");
            sceneKunden.setVisible(true);

            hideAllSceneKundenItems();
            sceneKunden_buttons.setVisible(true);
        }
        else if(event.getSource() == leftMenu_btnMitarbeitern)
        {
            sceneUrl.setText("/versicherung/mitarbeitern");
            sceneName.setText("Mitarbeitern");
            sceneMitarbeitern.setVisible(true);
            hideAllSceneMitarbeiterItems();
            sceneMitarbeiter_buttons.setVisible(true);
        }
        else if(event.getSource() == leftMenu_btnVersicherung)
        {
            sceneUrl.setText("/versicherung/versicherung");
            sceneName.setText("Versicherung");
            sceneVersicherung.setVisible(true);
            hideAllSceneVersicherungsItems();
            sceneVersicherung_buttons.setVisible(true);
        }

    }

    @FXML
    private void handleSceneKundenButtonClicks(ActionEvent event)
    {
        hideAllSceneKundenItems();
        if(event.getSource() == sceneKunden_button_auflisten){
            sceneKunden_alleKunden.setVisible(true);
            sceneUrl.setText("/versicherung/kunden/alleKunden");
            sceneName.setText("Alle Kunden");
            refreshAlleKundenList();
        }
        else if (event.getSource() == sceneKunden_button_neueKunden){
            sceneKunden_neueKunden.setVisible(true);
            sceneUrl.setText("/versicherung/kunden/neuerKunde");
            sceneName.setText("Neuer Kunde");
        }
    }

    @FXML
    private void handleBackToKundenClick(ActionEvent event){
        leftMenu_btnKunden.fire();
    }

    @FXML
    private void handleErstellenNeueKundenClick(ActionEvent event){
        int id = Integer.parseInt(sceneKunden_neueKunden_field_id.getText());
        String firstName = sceneKunden_neueKunden_field_vorname.getText();
        String lastName = sceneKunden_neueKunden_field_nachname.getText();
        double phoneNumber = Double.parseDouble(sceneKunden_neueKunden_field_telefonnummer.getText());
        String address = sceneKunden_neueKunden_field_adresse.getText();

        Customer customer = new Customer(id, firstName, lastName, phoneNumber, address);
        if (DatabaseCustomer.createNewCustomer(customer)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Neuen Kunde wurde erfolgreich erstellt.");
            alert.show();
            leftMenu_btnKunden.fire();
        }
    }

    @FXML
    private void handleBackToMitarbeiterClick(ActionEvent event){
        leftMenu_btnMitarbeitern.fire();
    }

    @FXML
    private void handleSceneMitarbeiterButtonClicks(ActionEvent event)
    {
        hideAllSceneMitarbeiterItems();
        if(event.getSource() == sceneMitarbeiter_button_auflisten){
            sceneMitarbeiter_alleMitarbeiter.setVisible(true);
            sceneUrl.setText("/versicherung/mitarbeiter/alleMitarbeiter");
            sceneName.setText("Alle Mitarbeiter");
            refreshAlleMitarbeiterList();
        }
        else if (event.getSource() == sceneMitarbeiter_button_neueMitarbeiter){
            sceneMitarbeiter_neueMitarbeiter.setVisible(true);
            sceneUrl.setText("/versicherung/kunden/neuerMitarbeiter");
            sceneName.setText("Neuer Mitarbeiter");
        }
    }

    @FXML
    private void handleErstellenNeueMitarbeiterClick(ActionEvent event){
        int id = Integer.parseInt(sceneMitarbeiter_neueMitarbeiter_field_id.getText());
        String firstName = sceneMitarbeiter_neueMitarbeiter_field_vorname.getText();
        String lastName = sceneMitarbeiter_neueMitarbeiter_field_nachname.getText();
        String role = sceneMitarbeiter_neueMitarbeiter_field_role.getText();
        double phoneNumber = Double.parseDouble(sceneMitarbeiter_neueMitarbeiter_field_telefonnummer.getText());
        String address = sceneMitarbeiter_neueMitarbeiter_field_adresse.getText();

        Employee mitarbeiter = new Employee(id, firstName, lastName, phoneNumber, address, role);
        if (DatabaseEmployee.createNewEmployee(mitarbeiter)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Neuer Mitarbeiter wurde erfolgreich erstellt.");
            alert.show();
            leftMenu_btnMitarbeitern.fire();
        }
    }

    @FXML
    private void handleLoeschenMitarbeiterClick(ActionEvent event){
        Employee selectedMitarbeiter = sceneMitarbeiter_alleMitarbeiter_table.getSelectionModel().getSelectedItem();

        if (selectedMitarbeiter == null)
            return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mitarbeiter Löschen");
        alert.setHeaderText(selectedMitarbeiter.getFirstName() + " " + selectedMitarbeiter.getLastName());
        alert.setContentText("Möchten Sie diesen Mitarbeiter wirklich löschen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (DatabaseEmployee.deleteEmployee(selectedMitarbeiter)){
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Mitarbeiter Löschen");
                confirmationAlert.setHeaderText(selectedMitarbeiter.getFirstName() + " " + selectedMitarbeiter.getLastName());
                confirmationAlert.setContentText("Mitarbeiter wurde erfolgreich gelöscht.");
                confirmationAlert.show();
                refreshAlleKundenList();
            }
            else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Mitarbeiter Löschen");
                errorAlert.setHeaderText(selectedMitarbeiter.getFirstName() + " " + selectedMitarbeiter.getLastName());
                errorAlert.setContentText("Mitarbeiter konnte nicht gelöscht werden.");
                errorAlert.show();
            }
        }
    }

    @FXML
    private void handleBackToVersicherungClick(ActionEvent event){
        leftMenu_btnVersicherung.fire();
    }

    @FXML
    private void handleSceneVersicherungButtonClicks(ActionEvent event) {
        hideAllSceneVersicherungsItems();
        if (event.getSource() == sceneVersicherung_button_vertraege_auflisten)
        {
            sceneVersicherung_alleVertraege.setVisible(true);
            sceneUrl.setText("/versicherung/versicherung/alleVertraege");
            sceneName.setText("Alle Vertraege");
            refreshVersicherungsVertraegenList();
        }
        else if (event.getSource() == sceneVersicherung_button_vertraege_erstellen){
            sceneVersicherung_neueVertraege_erstellen.setVisible(true);
            sceneUrl.setText("/versicherung/versicherung/neuerVertrag");
            sceneName.setText("Neuer Vertrag");
            refreshVersicherungsVertrageErtellenItems();
        }
        else if (event.getSource() == sceneVersicherung_button_typen_bearbeiten) {
            sceneVersicherung_VersicherungsTypen.setVisible(true);
            sceneUrl.setText("/versicherung/versicherung/versicherungsTyp");
            sceneName.setText("Versicherungstypen");
            refreshVersicherungsTypenList();
        }
    }


    @FXML
    private void handleLoeschenKundenClick(ActionEvent event){

        Customer selectedKunde = sceneKunden_alleKunden_table.getSelectionModel().getSelectedItem();

        if (selectedKunde == null)
            return;


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Kunde Löschen");
        alert.setHeaderText(selectedKunde.getFirstName() + " " + selectedKunde.getLastName());
        alert.setContentText("Möchten Sie diesen Kunde wirklich löschen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (DatabaseCustomer.deleteCustomer(selectedKunde)){
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Kunde Löschen");
                confirmationAlert.setHeaderText(selectedKunde.getFirstName() + " " + selectedKunde.getLastName());
                confirmationAlert.setContentText("Kunde wurde erfolgreich gelöscht.");
                confirmationAlert.show();
                refreshAlleKundenList();
            }
            else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Kunde Löschen");
                errorAlert.setHeaderText(selectedKunde.getFirstName() + " " + selectedKunde.getLastName());
                errorAlert.setContentText("Kunde konnte nicht gelöscht werden.");
                errorAlert.show();
            }
        }

    }

    @FXML
    private void handleErstellenNeueVersicherungsVertraegeClick(ActionEvent event){
        String ausweisNummer = sceneVersicherung_neueVertraege_field_id.getText();

        PersonType personTyp = sceneVersicherung_neueVertraege_combobox_persontyp.getValue();
        InsuranceType versicherungsTyp = sceneVersicherung_neueVertraege_combobox_versicherungstyp.getValue();

        String versicherungsTyp_id = versicherungsTyp.getId();

        Date startDatum = null;
        try {
            startDatum = new SimpleDateFormat("yyyy-MM-dd").parse(sceneVersicherung_neueVertraege_datepicker_startdatum.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date endDatum = null;
        try {
            endDatum = new SimpleDateFormat("yyyy-MM-dd").parse(sceneVersicherung_neueVertraege_datepicker_enddatum.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        InsuranceContract versicherungsVertrag;
        Person person = DatabasePerson.getPersonFrom(ausweisNummer, personTyp);
        if (person != null){
            versicherungsVertrag = new InsuranceContract(null, versicherungsTyp_id, versicherungsTyp.getName(), person, personTyp, startDatum, endDatum, null);
            if (DatabaseInsurance.createNewInsuranceContract(versicherungsVertrag)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Neuer Vertrag wurde erfolgreich erstellt.");
                alert.show();
                leftMenu_btnVersicherung.fire();
                refreshVersicherungsVertraegenList();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Person mit der Ausweisnummer " + ausweisNummer + " existiert nicht.");
            alert.show();
        }

    }

    private void handleLoeschenVersicherungsTypClick(ActionEvent event)
    {
        InsuranceType selectedVersicherungsTyp = sceneVersicherung_VersicherungsTypen_table.getSelectionModel().getSelectedItem();

        if (selectedVersicherungsTyp == null)
            return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("VersicherungsTyp Löschen");
        alert.setHeaderText(selectedVersicherungsTyp.getName());
        alert.setContentText("Möchten Sie diesen VersicherungsTyp wirklich löschen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           InsuranceType versicherungsTyp = new InsuranceType(selectedVersicherungsTyp.getId(), selectedVersicherungsTyp.getName());

           if (DatabaseInsurance.deleteInsuranceType(versicherungsTyp)){
               Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
               confirmationAlert.setTitle("VersicherungsTyp Löschen");
               confirmationAlert.setHeaderText(selectedVersicherungsTyp.getName());
               confirmationAlert.setContentText("VersicherungsTyp wurde erfolgreich gelöscht.");
               confirmationAlert.show();
               refreshVersicherungsTypenList();
           }
           else{
               Alert errorAlert = new Alert(Alert.AlertType.ERROR);
               errorAlert.setTitle("VersicherungsTyp Löschen");
               errorAlert.setHeaderText(selectedVersicherungsTyp.getName());
               errorAlert.setContentText("VersicherungsTyp konnte nicht gelöscht werden.");
               errorAlert.show();
           }

        }
    }

    @FXML
    private void handleErstellenVersicherungsTypClick(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Neuer VersicherungsTyp");

        dialog.setHeaderText("Neuer VersicherungsTyp");

        dialog.setContentText("Bitte geben Sie den Namen des neuen VersicherungsTyps ein:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            String versicherungsTypName = result.get();

            InsuranceType versicherungsTyp = new InsuranceType(null, versicherungsTypName);
            if (DatabaseInsurance.createNewInsuranceType(versicherungsTyp)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Neuer VersicherungsTyp ' " + versicherungsTypName + " ' erfolgreich erstellt.");
                alert.show();
                refreshVersicherungsTypenList();
            }
        }
    }

    private void handleLoeschenVersicherungsVertraegeClick(ActionEvent event)
    {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearSceneHeader();
        hideAllSceneItems();
        initializeAlleKundenTableView();
        initializeAlleMitarbeiterTableView();
    }

    public void clearSceneHeader()
    {
        sceneUrl.setText("");
        sceneName.setText("");
    }

    public void hideAllSceneItems()
    {
        sceneStackPane.getChildren().forEach((scene) -> {
            scene.setVisible(false);
        });
    }

    public void hideAllSceneKundenItems(){
        sceneKunden_buttons.setVisible(false);
        sceneKunden_alleKunden.setVisible(false);
        sceneKunden_neueKunden.setVisible(false);
    }

    public void hideAllSceneMitarbeiterItems()
    {
        sceneMitarbeiter_stackpane.getChildren().forEach((scene) -> {
            scene.setVisible(false);
        });
    }

    public void hideAllSceneVersicherungsItems()
    {
        sceneVersicherung_stackpane.getChildren().forEach((scene) -> {
            scene.setVisible(false);
        });
    }

    public void initializeAlleKundenTableView()
    {
        sceneKunden_alleKunden_table_column_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        sceneKunden_alleKunden_table_column_vorName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        sceneKunden_alleKunden_table_column_nachName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        sceneKunden_alleKunden_table_column_telefonNummer.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        sceneKunden_alleKunden_table_column_adresse.setCellValueFactory(new PropertyValueFactory<>("Address"));

        sceneKunden_alleKunden_button_delete.disableProperty().bind(Bindings.isEmpty(sceneKunden_alleKunden_table.getSelectionModel().getSelectedItems()));

        refreshAlleKundenList();
    }

    public void refreshAlleKundenList()
    {

        try{
            ArrayList<Customer> kundenList = DatabaseCustomer.listAllCustomers();
            datenKunden = FXCollections.observableList(kundenList);
            FilteredList<Customer> filteredData = new FilteredList<>(datenKunden, p -> true);

            sceneKunden_alleKunden_field_name.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(kunde -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (kunde.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (kunde.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Customer> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(sceneKunden_alleKunden_table.comparatorProperty());

            sceneKunden_alleKunden_table.setItems(sortedData);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void initializeAlleMitarbeiterTableView()
    {
        sceneMitarbeiter_alleMitarbeiter_table_column_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        sceneMitarbeiter_alleMitarbeiter_table_column_vorName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        sceneMitarbeiter_alleMitarbeiter_table_column_nachName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        sceneMitarbeiter_alleMitarbeiter_table_column_role.setCellValueFactory(new PropertyValueFactory<>("Role"));
        sceneMitarbeiter_alleMitarbeiter_table_column_telefonNummer.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        sceneMitarbeiter_alleMitarbeiter_table_column_adresse.setCellValueFactory(new PropertyValueFactory<>("Address"));

        sceneMitarbeiter_alleMitarbeiter_button_delete.disableProperty().bind(Bindings.isEmpty(sceneMitarbeiter_alleMitarbeiter_table.getSelectionModel().getSelectedItems()));

        refreshAlleMitarbeiterList();
    }

    public void refreshAlleMitarbeiterList()
    {
        try{
            ArrayList<Employee> mitarbeiterList = DatabaseEmployee.listAllEmployees();
            datenMitarbeiter = FXCollections.observableList(mitarbeiterList);
            FilteredList<Employee> filteredData = new FilteredList<>(datenMitarbeiter, p -> true);

            sceneMitarbeiter_alleMitarbeiter_field_name.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(mitarbeiter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (mitarbeiter.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (mitarbeiter.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Employee> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(sceneMitarbeiter_alleMitarbeiter_table.comparatorProperty());

            sceneMitarbeiter_alleMitarbeiter_table.setItems(sortedData);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }



    public void refreshVersicherungsVertraegenList()
    {
        try{
            ArrayList<InsuranceContract> versicherungsVertraegeList = DatabaseInsurance.listAllInsuranceContracts();
            datenVersicherungsVertraege = FXCollections.observableList(versicherungsVertraegeList);
            FilteredList<InsuranceContract> filteredData = new FilteredList<>(datenVersicherungsVertraege, p -> true);

            sceneVersicherung_alleVertraege_field_name.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(vertrag -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    Person person = vertrag.getPerson();
                    if (person.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if(person.getLastName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false; 
                });
            });

            SortedList<InsuranceContract> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(sceneVersicherung_alleVertraege_table.comparatorProperty());

            sceneVersicherung_alleVertraege_table.setItems(sortedData);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }




    public void refreshVersicherungsTypenList()
    {
        try{
            ArrayList<InsuranceType> versicherungsTypenList = DatabaseInsurance.listAllInsuranceTypes();
            datenVersicherungsTypen = FXCollections.observableList(versicherungsTypenList);
            
            FilteredList<InsuranceType> filteredData = new FilteredList<>(datenVersicherungsTypen, p -> true);

            sceneVersicherung_VersicherungsTypen_field_name.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(typ -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (typ.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<InsuranceType> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(sceneVersicherung_VersicherungsTypen_table.comparatorProperty());

            sceneVersicherung_VersicherungsTypen_table.setItems(sortedData);
            
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void refreshVersicherungsVertrageErtellenItems()
    {
        sceneVersicherung_neueVertraege_combobox_persontyp.getItems().setAll(PersonType.values());

        sceneVersicherung_neueVertraege_combobox_persontyp.setConverter(new StringConverter<PersonType>() {
            @Override
            public String toString(PersonType object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public PersonType fromString(String string) {
                return PersonType.valueOf(string);
            }
        });

        sceneVersicherung_neueVertraege_combobox_persontyp.setValue(PersonType.Kunde);

        sceneVersicherung_neueVertraege_combobox_persontyp.valueProperty().addListener((observable, oldValue, newValue) -> {

        });

        sceneVersicherung_neueVertraege_combobox_versicherungstyp.setItems(datenVersicherungsTypen);

        sceneVersicherung_neueVertraege_combobox_versicherungstyp.setConverter(new StringConverter<InsuranceType>() {
            @Override
            public String toString(InsuranceType object) {
                if (object == null) return null;
                return object.getName();
            }

            @Override
            public InsuranceType fromString(String string) {
                for (InsuranceType versicherungsTyp : datenVersicherungsTypen) {
                    if (versicherungsTyp.getName().equals(string)) {
                        return versicherungsTyp;
                    }
                }

                return null;
            }
        });

        if (!datenVersicherungsTypen.isEmpty())
            sceneVersicherung_neueVertraege_combobox_versicherungstyp.setValue(datenVersicherungsTypen.get(0));
    }

}
