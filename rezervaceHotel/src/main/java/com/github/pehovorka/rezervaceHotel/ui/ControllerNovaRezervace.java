package com.github.pehovorka.rezervaceHotel.ui;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.pehovorka.rezervaceHotel.logika.Hotel;
import com.github.pehovorka.rezervaceHotel.logika.NovaRezervace;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 *  Třída ControllerNovaRezervace
 * 
 *  Kontroler, který zprostředkovává komunikaci mezi grafikou rezervačního okna a logikou rezervačního systému.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
@SuppressWarnings("restriction")
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
	@FXML
	private ListView<String> volnePokoje;
	
	private List<String> seznamPokoju = new ArrayList<>();
	private List<String> seznamVolnychPokoju = new ArrayList<>();
	
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
	volnePokoje.getItems().clear();
    seznamVolnychPokoju.removeAll(seznamVolnychPokoju);
    
    for (String pokojKlic : rezervace.getPokoje().keySet()) {
		seznamVolnychPokoju.add(rezervace.getPokoje().get(pokojKlic).toString());
		String nazev = rezervace.getPokoje().get(pokojKlic).getNazev();
		LocalDate date = datumPrijezd.getValue();
   		LocalDate date2 = datumOdjezd.getValue();

   		
		int pocetLuzPok = rezervace.getPokoje().get(pokojKlic).getPocetLuzek();
		String pozadKatPok = rezervace.getPokoje().get(pokojKlic).getTrida();
		int pocetLuzUi = pocetLuzek.getValue();
        String pozadKatUi = pozadovanaKategorie.getValue();
        
        if((pocetLuzPok == pocetLuzUi) && (pozadKatUi.contains(pozadKatPok))) 
        {
        
		for (Integer rezervaceId : rezervace.getSeznamRezervaci().keySet()) {
           NovaRezervace nr = rezervace.getSeznamRezervaci().get(rezervaceId);
           
           String nazevRez = nr.getPokoj().getNazev();
       		LocalDate datez = nr.getDatumZacatek();
       		LocalDate datek = nr.getDatumKonec();
			
       		if(nazev.equals(nazevRez)) {
			
			if (datez.equals(date) || datek.equals(date) || datez.equals(date2) || datek.equals(date2) || (date.isBefore(datek) && date.isAfter(datez)) || (date2.isBefore(datek) && date2.isAfter(datez))) {
				seznamVolnychPokoju.remove(rezervace.getPokoje().get(pokojKlic).toString());
				break;
			}
			}
		}

		}
		else {
			seznamVolnychPokoju.remove(rezervace.getPokoje().get(pokojKlic).toString());
		}
        } 
    Collections.sort(seznamVolnychPokoju);
	volnePokoje.getItems().addAll(seznamVolnychPokoju);
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