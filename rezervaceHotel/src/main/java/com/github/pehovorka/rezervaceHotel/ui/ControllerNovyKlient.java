package com.github.pehovorka.rezervaceHotel.ui;

import com.github.pehovorka.rezervaceHotel.logika.Klient;
import com.github.pehovorka.rezervaceHotel.logika.Rezervace;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *  Třída ControllerNovyKlient
 * 
 *  Kontroler, který zprostředkovává komunikaci mezi grafikou okna pro založení nového klienta a logikou adventury.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
public class ControllerNovyKlient extends GridPane{
	@FXML
	private Button buttonPotvrdit;	
	@FXML
	private Button buttonZrusit;
	@FXML
	private TextField jmeno;
	@FXML
	private TextField prijmeni;
	@FXML
	private TextField cisloOP;
	
	private Rezervace rezervace;
	
	/**
	 * Metoda provede inicializaci grafických prvků
	 * 
	 * @param rezervace
	 *            aktuální rezervace
	 * 
	 */
	public void inicializuj(Rezervace rezervace) {
		this.rezervace = rezervace;	
	}
	
	@FXML
	public void buttonPotvrditClick() throws Exception{	
		if (jmeno.getText().equals("") || prijmeni.getText().equals("") || cisloOP.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Špatně zadané údaje");
			alert.setHeaderText("Zadejte všechny údaje!");
			alert.showAndWait();
		}
		else {
		try {
		Klient vkladany = new Klient(jmeno.getText(),prijmeni.getText(),Integer.parseInt(cisloOP.getText()));
		System.out.println("Vkládám: Jméno: "+vkladany.getJmeno()+" Příjmení: "+vkladany.getPrijmeni()+"  "+vkladany.getCisloOP());
		rezervace.vlozKlienta(vkladany);
		Stage stage = (Stage) buttonPotvrdit.getScene().getWindow();
	    stage.close();
	    System.out.println(rezervace.getKlienti().keySet());
		}
		catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Špatný formát čísla OP");
			alert.setHeaderText("Číslo OP musí být opravdu číslo!");
			alert.showAndWait();
		}
		}
	}
	@FXML
	public void buttonZrusitClick() throws Exception{	
		Stage stage = (Stage) buttonZrusit.getScene().getWindow();
	    stage.close();
	}
}
