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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 *  Třída ControllerUpravitRezervaci
 * 
 *  Kontroler, který zprostředkovává komunikaci mezi grafikou rezervačního okna a logikou rezervačního systému.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
public class ControllerUpravitRezervaci {
	@FXML
	private Button buttonPotvrdit;
	@FXML
	private Button buttonZrusit;
	@FXML
	private Button buttonDostupnostCena;
	@FXML
	private Button smazatRezervaci;
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
	private Label LabelPuvodniCena;
	@FXML
	private Label LabelPoUpraveCena;
	
	private List<String> seznamKlientu = new ArrayList<>();
	private List<String> seznamPokoju = new ArrayList<>();
	
	Hotel hotel;
	NovaRezervace rezervace;
	
	/**
	 * Metoda provede inicializaci grafických prvků
	 * 
	 * @param hotel
	 *            aktuální hotel
	 * 
	 * @param rezervace
	 *            aktuální rezervace
	 */
	public void inicializuj(Hotel hotel, NovaRezervace rezervace) {
		this.hotel = hotel;
		this.rezervace = rezervace;
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

	/**
	 * Metoda potvrzuje opravu rezervace.
	 * Vrací příslušný alert.
	 */
@FXML
public void buttonPotvrditClick() throws Exception{
	
	if (LabelPoUpraveCena.getText().equals("PoUprave")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Rezervace nebyla oveřena.");
			alert.setHeaderText("Nejdříve zkontrolujte dostupnost a cenu úpravy.");
			alert.showAndWait();
			return;
	}

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
	
	int cena = 0;
	int sezonaOdMesic = 6;
	int sezonaDoMesic = 9;
	while (!date.isAfter(date2.minusDays(1))) {
		if(date.getMonthValue() <= sezonaDoMesic && date.getMonthValue() >= sezonaOdMesic)
		{cena = cena + pokoj.getCenaSezona();}
		else {cena = cena + pokoj.getCena();}
		 date = date.plusDays(1);
		}
	rezervace.setCenaZaRezervaci(cena);
	
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Rezervace byla upravena.");
	alert.setHeaderText("Byly provedené příslušné opravy.");
	alert.showAndWait();
	Stage stage = (Stage) buttonPotvrdit.getScene().getWindow();
    stage.close();
}

/**
 * Metoda ověřuje jestli je ta rezervace volná.
 * 
 */
@FXML
public void buttonDostupnostCenaClick() throws Exception{
	if (datumOd.getValue() == null || datumDo.getValue() == null || comboBoxPokoj.getSelectionModel().getSelectedItem().isEmpty() || comboBoxKlient.getSelectionModel().getSelectedItem().isEmpty()) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Rezervace nebyla oveřena.");
		alert.setHeaderText("Zadejte všechny údaje.");
		alert.showAndWait();
		return;
	} 
	LocalDate date = datumOd.getValue();
	   LocalDate date2 = datumDo.getValue();
	if (date.isAfter(date2)) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Špatně zadané údaje");
		alert.setHeaderText("Datum příjezdu musí být dříve než datum odjezdu!");
		alert.showAndWait();
		return;
	}
	for (Integer rezervaceId : hotel.getSeznamRezervaci().keySet()) {
       NovaRezervace nr = hotel.getSeznamRezervaci().get(rezervaceId);
       String nazev = comboBoxPokoj.getSelectionModel().getSelectedItem();       
       String nazevRez = nr.getPokoj().getNazevSParametry();
   		LocalDate datez = nr.getDatumZacatek();
   		LocalDate datek = nr.getDatumKonec();
   	   
   		if(nazev.equals(nazevRez)) {
		
		if ((datez.equals(date) && datek.equals(date2)) || (date.isBefore(datek) && date.isAfter(datez)) || 
				(date2.isBefore(datek) && date2.isAfter(datez)) || (date.isBefore(datez) && date2.isAfter(datek)) ) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Rezervace má špatné nastavení.");
			alert.setHeaderText("V daném časovem rozmezí pokoj není dostupen.");
			alert.showAndWait();
			return;
		}
		}
	}
	String strP = comboBoxPokoj.getValue();
	String p = strP.substring(0,4);
    Pokoj pokoj = hotel.getPokoje().get(p);
	int cena = 0;
	int sezonaOdMesic = 6;
	int sezonaDoMesic = 9;
	while (!date.isAfter(date2.minusDays(1))) {
		if(date.getMonthValue() <= sezonaDoMesic && date.getMonthValue() >= sezonaOdMesic)
		{cena = cena + pokoj.getCenaSezona();}
		else {cena = cena + pokoj.getCena();}
		 date = date.plusDays(1);
		}
	LabelPoUpraveCena.setText("Cena po úpravě: "+cena+"Kč");
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Rezervace byla ověřena.");
	alert.setHeaderText("Rezervace byla ověřena.");
	alert.showAndWait();
}
/**
 * Metoda smaž tuhle rezervaci.
 * 
 */
@FXML
public void smazatRezervaciClick() throws Exception{
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Rezervace byla smazana.");
alert.setHeaderText("Rezervace byla smazana ze souboru.");
alert.showAndWait();
hotel.odeberRezervaci(rezervace);
Stage stage = (Stage) smazatRezervaci.getScene().getWindow();
stage.close();
}

/**
 * Metoda zruší okno.
 * 
 */
@FXML
public void buttonZrusitClick() throws Exception{	
	Stage stage = (Stage) buttonZrusit.getScene().getWindow();
    stage.close();

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
}


