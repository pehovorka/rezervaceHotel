package com.github.pehovorka.rezervaceHotel.ui;

import java.util.Map.Entry;

import com.github.pehovorka.rezervaceHotel.logika.Klient;
import com.github.pehovorka.rezervaceHotel.logika.Hotel;
import com.github.pehovorka.rezervaceHotel.logika.NovaRezervace;

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
 *  Kontroler, který zprostředkovává komunikaci mezi grafikou okna pro založení nového klienta a logikou rezervačního systému.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
public class ControllerKlient extends GridPane{
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
	
	private Hotel rezervace;
	
	private NovaRezervace nr;
	/**
	 * Metoda provede inicializaci grafických prvků
	 * 
	 * @param rezervace
	 *            aktuální rezervace
	 * 
	 */
	public void inicializuj(Hotel rezervace) {
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
		if (rezervace.getKlienti().containsKey(vkladany.getCisloOP()) && (!rezervace.getKlienti().get(vkladany.getCisloOP()).getJmeno().equals(vkladany.getJmeno()) || !rezervace.getKlienti().get(vkladany.getCisloOP()).getPrijmeni().equals(vkladany.getPrijmeni()))) {	
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Tento OP je již v databázi");
				alert.setHeaderText("Toto číslo občanského průkazu již zadal někdo kdo se jmenuje jinak než vy!");
				alert.showAndWait();
		}
		else {
		System.out.println("Vkládám: Jméno: "+vkladany.getJmeno()+" Příjmení: "+vkladany.getPrijmeni()+" Číslo OP: "+vkladany.getCisloOP());
		rezervace.vlozKlienta(vkladany);
		
		int op = vkladany.getCisloOP();
		
		Stage stage = (Stage) buttonPotvrdit.getScene().getWindow();
	    stage.close();
	    for(Entry<Integer, Klient> entry : rezervace.getKlienti().entrySet())
	    {  
	    	System.out.println(entry.getKey() + " : " +entry.getValue().getJmeno() + " " + entry.getValue().getPrijmeni());
	    }
		}
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
