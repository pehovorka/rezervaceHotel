package com.github.pehovorka.rezervaceHotel.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class ControllerNovaRezervace {
	@FXML
	private Button buttonPokracovat;
	@FXML
	private Button buttonZrusit;
	@FXML
	private Button buttonVyhledatPokoje;
	@FXML
	private DatePicker datumPrijezd;
	@FXML
	private DatePicker datumOdjezd;
	@FXML
	private ComboBox<Integer> pocetLuzek;
	@FXML
	private ComboBox<String> pozadovanaKategorie;

@FXML
public void buttonPokracovatClick() throws Exception{
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("/kontakt.fxml"));
	Parent root = loader.load();
	ControllerKontakt controller = loader.getController();
	Stage kontakt = new Stage();
	kontakt.setScene(new Scene(root));
	kontakt.show();
	kontakt.setTitle("Zadání kontaktních údajů");		
}

@FXML
public void buttonVyhledatPokojeClick() throws Exception{	
}

@FXML
public void datumPrijezdClick() throws Exception{	
}

@FXML
public void datumOdjezdClick() throws Exception{	
}

@FXML
public void buttonZrusitClick() throws Exception{	
	Stage stage = (Stage) buttonZrusit.getScene().getWindow();
    stage.close();
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("/vyberRezimu.fxml"));
	Parent root = loader.load();
	ControllerVyberRezimu controller = loader.getController();
	Stage vyberRezimu = new Stage();
	vyberRezimu.setScene(new Scene(root));
	vyberRezimu.show();
	vyberRezimu.setTitle("Výběr režimu");	
}
}