package com.github.pehovorka.rezervaceHotel.ui;

import java.io.IOException;

import com.github.pehovorka.rezervaceHotel.logika.Hotel;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;;

/**
 *  Třída ControllerVyberRezimu
 * 
 *  Kontroler, který zprostředkovává komunikaci mezi grafikou okna pro výběr režimu a logikou rezervačního systému.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
@SuppressWarnings("restriction")
public class ControllerVyberRezimu extends GridPane {

	private Hotel rezervace;
	
	@FXML
	private Button buttonSpravce;
	@FXML
	private Button buttonZakaznik;

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
	public void buttonZakaznikClick() throws Exception{
	    Stage stage = (Stage) buttonZakaznik.getScene().getWindow();
	    stage.close();
	    rezervace.setRezimSpravce(false);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/novaRezervace.fxml"));
		Parent root = loader.load();
		ControllerNovaRezervace controller = loader.getController();
		controller.inicializuj(rezervace);
		Stage novaRezervace = new Stage();
		novaRezervace.setScene(new Scene(root));
		novaRezervace.show();
		novaRezervace.setTitle("Nová rezervace");
		novaRezervace.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	    FXMLLoader loader = new FXMLLoader();
	        		loader.setLocation(getClass().getResource("/vyberRezimu.fxml"));
	        		Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
	        		ControllerVyberRezimu controller = loader.getController();
	        		controller.inicializuj(rezervace);
	        		Stage vyberRezimu = new Stage();
	        		vyberRezimu.setScene(new Scene(root));
	        		vyberRezimu.show();
	        		vyberRezimu.setTitle("Výběr režimu");
	              }

	   }); 
	}
	
	@FXML
	public void buttonSpravceClick() throws Exception {
	    Stage stage = (Stage) buttonSpravce.getScene().getWindow();
	    stage.close();
	    rezervace.setRezimSpravce(true);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/spravceMain.fxml"));
		Parent root = loader.load();
		ControllerSpravceMain controller = loader.getController();
		controller.inicializuj(rezervace);
		Stage spravceMain = new Stage();
		spravceMain.setScene(new Scene(root));
		spravceMain.show();
		spravceMain.setTitle("Správa hotelu - režim správce");
		spravceMain.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	    FXMLLoader loader = new FXMLLoader();
	        		loader.setLocation(getClass().getResource("/vyberRezimu.fxml"));
	        		Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
	        		ControllerVyberRezimu controller = loader.getController();
	        		controller.inicializuj(rezervace);
	        		Stage vyberRezimu = new Stage();
	        		vyberRezimu.setScene(new Scene(root));
	        		vyberRezimu.show();
	        		vyberRezimu.setTitle("Výběr režimu");
	              }

	   }); 
	}
}
