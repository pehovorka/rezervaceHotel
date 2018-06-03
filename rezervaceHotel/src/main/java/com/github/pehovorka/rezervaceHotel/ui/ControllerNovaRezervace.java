package com.github.pehovorka.rezervaceHotel.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.github.pehovorka.rezervaceHotel.logika.Hotel;
import com.github.pehovorka.rezervaceHotel.logika.Klient;
import com.github.pehovorka.rezervaceHotel.logika.NovaRezervace;
import com.github.pehovorka.rezervaceHotel.logika.Pokoj;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
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
	private ListView<String> volnePokoje;
	@FXML
	private TextField klientPopis;
	
	@FXML
	private Button buttonPotvrdit;	
	@FXML
	private TextField jmeno;
	@FXML
	private TextField prijmeni;
	@FXML
	private TextField cisloOP;
	
	private List<String> seznamVolnychPokoju = new ArrayList<>();
	
	Hotel rezervace;
	NovaRezervace nr;
	
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

	/**
	 * Metoda pro vyhledávání volnych pokojů
	 * Vratí alert určující důsledek operace.
	 * 
	 */
@FXML
public void buttonVyhledatPokojeClick() throws Exception{	
	LocalDate date = datumPrijezd.getValue();
	LocalDate date2 = datumOdjezd.getValue();
	LocalDate dateNow = LocalDate.now();
	if (pozadovanaKategorie.getSelectionModel().isEmpty() || pocetLuzek.getSelectionModel().isEmpty() || datumPrijezd.getValue() == null || datumOdjezd.getValue() == null ) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Špatně zadané údaje");
		alert.setHeaderText("Zadejte všechny údaje!");
		alert.showAndWait();
		return;
	}
	if (date.isBefore(dateNow)) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Špatně zadané údaje");
		alert.setHeaderText("Datum příjezdu musí být později než dneska!");
		alert.showAndWait();
		return;
	}
	if (date.isAfter(date2)) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Špatně zadané údaje");
		alert.setHeaderText("Datum příjezdu musí být dříve než datum odjezdu!");
		alert.showAndWait();
		return;
	}
	volnePokoje.getItems().clear();
    seznamVolnychPokoju.removeAll(seznamVolnychPokoju);
    
    for (String pokojKlic : rezervace.getPokoje().keySet()) {
		seznamVolnychPokoju.add(rezervace.getPokoje().get(pokojKlic).toString());
		String nazev = rezervace.getPokoje().get(pokojKlic).getNazev();
		

   		
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
       			if ((datez.equals(date) && datek.equals(date2)) || (date.isBefore(datek) && date.isAfter(datez)) || 
       					(date2.isBefore(datek) && date2.isAfter(datez)) || (date.isBefore(datez) && date2.isAfter(datek)) || (date2.equals(datek) && date.isBefore(datez)) || (date.equals(datez) && date2.isAfter(datek))) {

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

/**
 * Metoda zruší okno.
 * 
 */
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

/**
 * Metoda pro potvrzení rezervace.
 * Vratí alert určující důsledek operace.
 * 
 */
@FXML
public void buttonPotvrditClick() throws Exception{	
	
	if (jmeno.getText().equals("") || prijmeni.getText().equals("") || cisloOP.getText().equals("") || pozadovanaKategorie.getSelectionModel().isEmpty() || pocetLuzek.getSelectionModel().isEmpty() || datumPrijezd.getValue() == null || datumOdjezd.getValue() == null || volnePokoje.getSelectionModel().isEmpty()) {
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
		return;
    }
	if (rezervace.getKlienti().containsKey(vkladany.getCisloOP())) {	
			
		int id = 0; 
		for (Integer rezervaceId : rezervace.getSeznamRezervaci().keySet()) {
			 id = rezervaceId;
			 System.out.println(id);
		 }
		String p = volnePokoje.getSelectionModel().getSelectedItem();
		String pok = p.substring(0, 4);
		Pokoj pokoj = rezervace.getPokoje().get(pok);
		
		
		
		String op = cisloOP.getText();
		Integer k = Integer.parseInt(op);
        Klient klient = rezervace.getKlienti().get(k);
		LocalDate date = datumPrijezd.getValue();
		LocalDate date2 = datumOdjezd.getValue();
		int idR = id + 1;
		
		int cena = 0;
		int sezonaOdMesic = 6;
		int sezonaDoMesic = 9;
		while (!date.isAfter(date2.minusDays(1))) {
			if(date.getMonthValue() <= sezonaDoMesic && date.getMonthValue() >= sezonaOdMesic)
			{cena = cena + pokoj.getCenaSezona();}
			else {cena = cena + pokoj.getCena();}
			 date = date.plusDays(1);
			}
		
		System.out.println(idR);
		NovaRezervace nr = new NovaRezervace(idR,date,date2,pokoj,klient,cena);
		rezervace.vlozRezervaci(nr);
		
		    Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Rezervace byla dokončena.");
			alert.setHeaderText("Děkujeme že jste opět využil(a) naše služby! Tešíme se na Vás." + " Cena rezervaci je " + cena + "Kč.");
			alert.showAndWait();
			rezervace.ulozSoubor("rezervace");
			Stage stage = (Stage) buttonPotvrdit.getScene().getWindow();
		    stage.close();
	}
	else {
		rezervace.vlozKlienta(vkladany);
		int id = 0; 
		for (Integer rezervaceId : rezervace.getSeznamRezervaci().keySet()) {
			 id = rezervaceId;
			 System.out.println(id);
		 }
		String p = volnePokoje.getSelectionModel().getSelectedItem();
		String pok = p.substring(0, 4);
		Pokoj pokoj = rezervace.getPokoje().get(pok);
		
		
		
		String op = cisloOP.getText();
		Integer k = Integer.parseInt(op);
        Klient klient = rezervace.getKlienti().get(k);
		LocalDate date = datumPrijezd.getValue();
		LocalDate date2 = datumOdjezd.getValue();
		int idR = id + 1;
		 System.out.println(idR);
		 
		 int cena = 0;
			int sezonaOdMesic = 6;
			int sezonaDoMesic = 9;
			while (!date.isAfter(date2.minusDays(1))) {
				if(date.getMonthValue() <= sezonaDoMesic && date.getMonthValue() >= sezonaOdMesic)
				{cena = cena + pokoj.getCenaSezona();}
				else {cena = cena + pokoj.getCena();}
				 date = date.plusDays(1);
				}
		 
		NovaRezervace nr = new NovaRezervace(idR,date,date2,pokoj,klient,cena);
		rezervace.vlozRezervaci(nr);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Rezervace byla dokončena.");
		alert.setHeaderText("Děkujeme, rezervace byla vytvořena. Těšíme se na Vás." + " Cena rezervaci je " + cena + " Kč.");
		alert.showAndWait();
		rezervace.ulozSoubor("rezervace");
		rezervace.ulozSoubor("klienti");
		Stage stage = (Stage) buttonPotvrdit.getScene().getWindow();
	    stage.close();
	    
    for(Entry<Integer, Klient> entry : rezervace.getKlienti().entrySet())
    {  
    	System.out.println(entry.getKey() + " : " +entry.getValue().getJmeno() + " " + entry.getValue().getPrijmeni());
    }
    for (Integer rezervaceId : rezervace.getSeznamRezervaci().keySet()) {
    	System.out.println(rezervace.getSeznamRezervaci().get(rezervaceId).toString());
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

}