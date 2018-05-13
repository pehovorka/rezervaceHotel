package com.github.pehovorka.rezervaceHotel.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.github.pehovorka.rezervaceHotel.logika.Rezervace;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *  Třída ControllerSpravceMain
 * 
 *  Kontroler, který zprostředkovává komunikaci mezi grafikou hlavního okna správce a logikou rezervačního systému.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
public class ControllerSpravceMain extends GridPane implements Observer {
	
	@FXML
	private MenuItem menuItemNovaRezervace;
	@FXML
	private MenuItem menuItemNovyPokoj;
	@FXML
	private MenuItem menuItemUlozit;
	@FXML
	private ComboBox<String> pokoj;
	@FXML
	private ComboBox<String> klient;
	@FXML
	private DatePicker datum;
	@FXML
	private Button buttonFiltrovat;
	@FXML
	private ListView<String> seznamRezervaci;
	
	private Rezervace rezervace;
	private List<String> seznamKlientu = new ArrayList<>();
	private List<String> seznamPokoju = new ArrayList<>();
	

	
	
	/**
	 * Metoda provede inicializaci grafických prvků
	 * 
	 * @param rezervace
	 *            aktuální rezervace
	 * 
	 */
	public void inicializuj(Rezervace rezervace) {
		this.rezervace = rezervace;
		for (String pokojKlic : rezervace.getPokoje().keySet()) {
			seznamPokoju.add(rezervace.getPokoje().get(pokojKlic).toString());
	    }
	    for (Integer klientKlic : rezervace.getKlienti().keySet()) {
	      	seznamKlientu.add(rezervace.getKlienti().get(klientKlic).toString());
	    }
		Collections.sort(seznamPokoju);
		Collections.sort(seznamKlientu);
	    pokoj.getItems().addAll(seznamPokoju);
	    klient.getItems().addAll(seznamKlientu);
	    rezervace.addObserver(this);
	}
	
	@FXML
	public void menuItemNovaRezervaceClick() throws Exception{
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
	public void menuItemNovyPokojClick() throws Exception{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/novyPokoj.fxml"));
		Parent root = loader.load();
		ControllerNovyPokoj controller = loader.getController();
		controller.inicializuj(rezervace);
		Stage novyPokoj = new Stage();
		novyPokoj.setScene(new Scene(root));
		novyPokoj.show();
		novyPokoj.setTitle("Nový pokoj");
	}
	
	@FXML
	public void menuItemUlozitClick() throws Exception{
		rezervace.ulozSoubor("klienti");
		rezervace.ulozSoubor("pokoje");
	}
	
	@FXML
	public void buttonFiltrovatClick() throws Exception{	
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	    pokoj.getItems().clear();
	    klient.getItems().clear();
	    seznamPokoju.removeAll(seznamPokoju);
	    seznamKlientu.removeAll(seznamKlientu);
		for (String pokojKlic : rezervace.getPokoje().keySet()) {
			seznamPokoju.add(rezervace.getPokoje().get(pokojKlic).toString());
	    }
	    for (Integer klientKlic : rezervace.getKlienti().keySet()) {
	      	seznamKlientu.add(rezervace.getKlienti().get(klientKlic).toString());
	    }
		seznamPokoju.sort(String.CASE_INSENSITIVE_ORDER);
		seznamKlientu.sort(String.CASE_INSENSITIVE_ORDER);
	    pokoj.getItems().addAll(seznamPokoju);
	    klient.getItems().addAll(seznamKlientu);
	}
	
	

}
