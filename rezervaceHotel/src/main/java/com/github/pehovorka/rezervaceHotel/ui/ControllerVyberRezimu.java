package com.github.pehovorka.rezervaceHotel.ui;

import com.github.pehovorka.rezervaceHotel.logika.Rezervace;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerVyberRezimu extends GridPane {

	private Rezervace rezervace;
	
	@FXML
	private Button buttonSpravce;
	@FXML
	private Button buttonZakaznik;

	public void inicializuj(Rezervace rezervace) {
		this.rezervace = rezervace;
	}
	
	@FXML
	public void buttonZakaznikClick() throws Exception{
	    Stage stage = (Stage) buttonZakaznik.getScene().getWindow();
	    stage.close();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/novaRezervace.fxml"));
		Parent root = loader.load();
		ControllerNovaRezervace controller = loader.getController();
		controller.inicializuj(rezervace);
		Stage novaRezervace = new Stage();
		novaRezervace.setScene(new Scene(root));
		novaRezervace.show();
		novaRezervace.setTitle("Nová rezervace");		
	}
	
	@FXML
	public void buttonSpravceClick() throws Exception {
	    Stage stage = (Stage) buttonSpravce.getScene().getWindow();
	    stage.close();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/spravceMain.fxml"));
		Parent root = loader.load();
		ControllerSpravceMain controller = loader.getController();
		//controller.inicializuj(rezervace);
		Stage spravceMain = new Stage();
		spravceMain.setScene(new Scene(root));
		spravceMain.show();
		spravceMain.setTitle("Správa hotelu - režim správce");		
	}

}
