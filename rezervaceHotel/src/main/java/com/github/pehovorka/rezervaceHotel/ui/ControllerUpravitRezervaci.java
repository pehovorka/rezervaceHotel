package com.github.pehovorka.rezervaceHotel.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.pehovorka.rezervaceHotel.logika.Hotel;
import com.github.pehovorka.rezervaceHotel.logika.Klient;
import com.github.pehovorka.rezervaceHotel.logika.NovaRezervace;
import com.github.pehovorka.rezervaceHotel.logika.Pokoj;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *  Třída ControllerUpravitRezervaci
 * 
 *  Kontroler, který zprostředkovává komunikaci mezi grafikou rezervačního okna a logikou rezervačního systému.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
@SuppressWarnings("restriction")
public class ControllerUpravitRezervaci {
	@FXML
	private Button buttonPotvrdit;
	@FXML
	private Button buttonZrusit;
	@FXML
	private Button buttonDostupnostCena;
	@FXML
	private DatePicker datumOd;
	@FXML
	private DatePicker datumDo;
	@FXML
	private ComboBox<String> comboBoxPokoj;
	@FXML
	private ComboBox<String> comboBoxKlient;
	@FXML
	private Label labelRezervaceInfo;
	@FXML
	private Label LabelZmenaCena;
	@FXML
	private Label LabelPuvodniCena;
	@FXML
	private Label LabelPoUpraveCena;
	
	private List<String> seznamKlientu = new ArrayList<>();
	private List<String> seznamPokoju = new ArrayList<>();
	
	Hotel hotel;
	NovaRezervace rezervace;
	NovaRezervace rezervaceOld;
	
	/**
	 * Metoda provede inicializaci grafických prvků
	 * 
	 * @param hotel
	 *            aktuální hotel
	 * 
	 */
	public void inicializuj(Hotel hotel, NovaRezervace rezervace, NovaRezervace rezervaceOld) {
		this.hotel = hotel;
		this.rezervace = rezervace;
		this.rezervace = rezervaceOld;
		for (String pokojKlic : hotel.getPokoje().keySet()) {
			seznamPokoju.add(hotel.getPokoje().get(pokojKlic).toString());
	    }
	    for (Integer klientKlic : hotel.getKlienti().keySet()) {
	      	seznamKlientu.add(hotel.getKlienti().get(klientKlic).toString());
	    }
		Collections.sort(seznamPokoju);
		Collections.sort(seznamKlientu);
		comboBoxPokoj.getItems().addAll(seznamPokoju);
		comboBoxKlient.getItems().addAll(seznamKlientu);
		labelRezervaceInfo.setText(rezervace.getIdRezervace()+", Od: "+rezervace.getDatumZacatek().getDayOfMonth()+"."+rezervace.getDatumZacatek().getMonthValue()+". "+rezervace.getDatumZacatek().getYear()+", Do: "+rezervace.getDatumKonec().getDayOfMonth()+"."+rezervace.getDatumKonec().getMonthValue()+". "+rezervace.getDatumKonec().getYear()+", Pokoj: "+rezervace.getPokoj().getNazev()+", Klient: "+rezervace.getKlient());
		LabelPuvodniCena.setText("Původní cena: "+rezervace.getCenaZaRezervaci().toString()+"Kč");
		System.out.println(rezervace.getDatumZacatek());
	}

@FXML
public void buttonPotvrditClick() throws Exception{

	String strP = comboBoxPokoj.getValue();
	String p = strP.substring(0,4);
    Pokoj pokoj = hotel.getPokoje().get(p);
    rezervace.setPokoj(pokoj);
    
    String str = comboBoxKlient.getValue();
    String str2 = str.substring(0, str.length() - 1);
    String k1 = str2.substring(str2.length()-9);
    Integer k = Integer.parseInt(k1);
    
    Klient klient = hotel.getKlienti().get(k);
    rezervace.setKlient(klient);
    
    LocalDate date = datumOd.getValue();
	LocalDate date2 = datumDo.getValue();
	rezervace.setDatumZacatek(date);
	rezervace.setDatumKonec(date2);
}

@FXML
public void buttonDostupnostCenaClick() throws Exception{

}

@FXML
public void datumOdClick() throws Exception{	
}

@FXML
public void datumDoClick() throws Exception{	
}


@FXML
public void comboBoxPokojClick() throws Exception{

}

@FXML
public void comboBoxKlientClick() throws Exception{

}

@FXML
public void buttonZrusitClick() throws Exception{	
	Stage stage = (Stage) buttonZrusit.getScene().getWindow();
    stage.close();
	}
}
