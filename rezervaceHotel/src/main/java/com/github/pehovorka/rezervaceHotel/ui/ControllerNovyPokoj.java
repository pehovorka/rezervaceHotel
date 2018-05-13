package com.github.pehovorka.rezervaceHotel.ui;

import com.github.pehovorka.rezervaceHotel.logika.Rezervace;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *  Třída ControllerNovyPokoj
 * 
 *  Kontroler, který zprostředkovává komunikaci mezi grafikou okna pro založení nového pokoje a logikou rezervačního systému.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
public class ControllerNovyPokoj extends GridPane {
	
	@FXML
	private ComboBox<Integer> comboBoxPocetLuzek; 
	@FXML
	private ComboBox<String> comboBoxTrida; 
	@FXML
	private TextField textFieldCena; 
	@FXML
	private TextField textFieldCenaSezona; 
	@FXML
	private TextField textFieldNazev; 
	@FXML
	private Button buttonZrusit; 
	@FXML
	private Button buttonPotvrdit; 
	
	Rezervace rezervace;
	
	/**
	 * Metoda provede inicializaci grafických prvků
	 * 
	 * @param rezervace
	 *            aktuální rezervace
	 * 
	 */
	public void inicializuj(Rezervace rezervace) {
		this.rezervace = rezervace;
		comboBoxTrida.getItems().addAll(rezervace.getTridyPokoju());
		comboBoxPocetLuzek.getItems().addAll(rezervace.getPoctyLuzek());
		
	}
	
	@FXML
	public void buttonZrusitClick() throws Exception{
		Stage stage = (Stage) buttonZrusit.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void buttonPotvrditClick() throws Exception{
		if (textFieldCena.getText().equals("") || textFieldCenaSezona.getText().equals("") || textFieldNazev.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Špatně zadané údaje");
			alert.setHeaderText("Zadejte všechny údaje!");
			alert.showAndWait();
		}
	}
}

