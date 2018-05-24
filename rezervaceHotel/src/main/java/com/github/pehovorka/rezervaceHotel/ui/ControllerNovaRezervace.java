package com.github.pehovorka.rezervaceHotel.ui;

import com.github.pehovorka.rezervaceHotel.logika.Hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 *  Třída ControllerNovaRezervace
 * 
 *  Kontroler, který zprostředkovává komunikaci mezi grafikou rezervačního okna a logikou rezervačního systému.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
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
	private ComboBox<String> obsazeneRezervace;
	
	Hotel rezervace;
	
	/**
	 * Metoda provede inicializaci grafických prvků
	 * 
	 * @param rezervace
	 *            aktuální rezervace
	 * 
	 */
	public void inicializuj(Hotel rezervace) {
		this.rezervace = rezervace;
		pocetLuzek.getItems().addAll(rezervace.getPoctyLuzek());
		pozadovanaKategorie.getItems().addAll(rezervace.getTridyPokoju());
		//obsazeneRezervace.getItems().addAll(rezervace.getSeznamRezervaci());
		
		
	}

@FXML
public void buttonPokracovatClick() throws Exception{
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("/novyKlient.fxml"));
	Parent root = loader.load();
	ControllerNovyKlient controller = loader.getController();
	controller.inicializuj(rezervace);
	Stage novyKlient = new Stage();
	novyKlient.setScene(new Scene(root));
	novyKlient.show();
	novyKlient.setTitle("Zadání kontaktních údajů");		
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
	if (!rezervace.isRezimSpravce()) {
    FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("/vyberRezimu.fxml"));
	Parent root = loader.load();
	ControllerVyberRezimu controller = loader.getController();
	controller.inicializuj(rezervace);
	Stage vyberRezimu = new Stage();
	vyberRezimu.setScene(new Scene(root));
	vyberRezimu.show();
	vyberRezimu.setTitle("Výběr režimu");
	}
}
}