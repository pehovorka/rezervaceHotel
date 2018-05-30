package com.github.pehovorka.rezervaceHotel.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
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
@SuppressWarnings("restriction")
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
	private Button selectAll;
	@FXML
	private ListView<String> seznamRezervaci;
	@FXML
	private TableView<String> table;
	
	private Hotel hotel;
	private List<String> seznamKlientu = new ArrayList<>();
	private List<String> seznamPokoju = new ArrayList<>();
	private List<String> seznamVsechRezervaci = new ArrayList<>();
	private List<String> seznamFilterRezervaci = new ArrayList<>();


	
	
	/**
	 * Metoda provede inicializaci grafických prvků
	 * 
	 * @param hotel
	 *            aktuální hotel
	 * 
	 */
	public void inicializuj(Hotel hotel) {
		this.hotel = hotel;
		for (String pokojKlic : hotel.getPokoje().keySet()) {
			seznamPokoju.add(hotel.getPokoje().get(pokojKlic).toString());
	    }
	    for (Integer klientKlic : hotel.getKlienti().keySet()) {
	      	seznamKlientu.add(hotel.getKlienti().get(klientKlic).toString());
	    }
		Collections.sort(seznamPokoju);
		Collections.sort(seznamKlientu);

		pokoj.getItems().addAll(seznamPokoju);
	    klient.getItems().addAll(seznamKlientu);
	    hotel.addObserver(this);
	}
	
	@FXML
	public void menuItemNovaRezervaceClick() throws Exception{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/novaRezervace.fxml"));
		Parent root = loader.load();
		ControllerNovaRezervace controller = loader.getController();
		controller.inicializuj(hotel);
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
		controller.inicializuj(hotel);
		Stage novyPokoj = new Stage();
		novyPokoj.setScene(new Scene(root));
		novyPokoj.show();
		novyPokoj.setTitle("Nový pokoj");
	}
	
	@FXML
	public void menuItemUlozitClick() throws Exception{
		hotel.ulozSoubor("klienti");
		hotel.ulozSoubor("pokoje");
	}
	
	@FXML
	public void buttonFiltrovatClick() throws Exception{	
	    seznamRezervaci.getItems().clear();
	    seznamVsechRezervaci.removeAll(seznamVsechRezervaci);
	    	    
		boolean isMyComboBoxEmpty2 = klient.getSelectionModel().isEmpty();
		boolean isMyComboBoxEmpty = pokoj.getSelectionModel().isEmpty();
		

		
		if (isMyComboBoxEmpty && isMyComboBoxEmpty2 && datum.getValue() == null) {
			for (Integer rezervaceId : hotel.getSeznamRezervaci().keySet()) {
		      	seznamVsechRezervaci.add(hotel.getSeznamRezervaci().get(rezervaceId).toString());
		    }
			Collections.sort(seznamVsechRezervaci);
		    seznamRezervaci.getItems().addAll(seznamVsechRezervaci);
		}
		
		if (!(datum.getValue() == null) && !isMyComboBoxEmpty && !isMyComboBoxEmpty2) {
			LocalDate date = datum.getValue();
/*			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			LocalDate date = LocalDate.from(instant);*/
			
			for (Integer rezervaceId : hotel.getSeznamRezervaci().keySet()) {
				NovaRezervace nr = hotel.getSeznamRezervaci().get(rezervaceId);	
				
/*				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				String a2 = format.format(nr.getDatumZacatek());
				DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				String a1 = date.format(format2);*/
				
				
				LocalDate datez = nr.getDatumZacatek();
				LocalDate datek = nr.getDatumKonec();
				
				String pokoj1 = pokoj.getValue();
				String pokoj2 = nr.getPokoj().getNazev();
				boolean containsP = pokoj1.contains(pokoj2);
				
				String klient1 = klient.getValue();
				String klient2 = String.valueOf(nr.getKlient().getCisloOP());
				boolean containsK = klient1.contains(klient2);
				
				if(((date.isAfter(datez) || date.equals(datez)) && (date.isBefore(datek) || date.equals(datek))) && containsP && containsK) {
					seznamVsechRezervaci.add(hotel.getSeznamRezervaci().get(rezervaceId).toString());
				}
			}
			seznamRezervaci.getItems().addAll(seznamVsechRezervaci);
		};
		
		if ((datum.getValue() == null) && !isMyComboBoxEmpty && !isMyComboBoxEmpty2) {
			
			for (Integer rezervaceId : hotel.getSeznamRezervaci().keySet()) {
				NovaRezervace nr = hotel.getSeznamRezervaci().get(rezervaceId);	
				
				String pokoj1 = pokoj.getValue();
				String pokoj2 = nr.getPokoj().getNazev();
				boolean containsP = pokoj1.contains(pokoj2);
				
				String klient1 = klient.getValue();
				String klient2 = String.valueOf(nr.getKlient().getCisloOP());
				boolean containsK = klient1.contains(klient2);
				
				if(containsP && containsK) {
					seznamVsechRezervaci.add(hotel.getSeznamRezervaci().get(rezervaceId).toString());
				}
			}
			seznamRezervaci.getItems().addAll(seznamVsechRezervaci);
		};
		
		if (!(datum.getValue() == null) && !isMyComboBoxEmpty && isMyComboBoxEmpty2) {
			LocalDate date = datum.getValue();
			/*Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			LocalDate date = LocalDate.from(instant);*/
			
			for (Integer rezervaceId : hotel.getSeznamRezervaci().keySet()) {
				NovaRezervace nr = hotel.getSeznamRezervaci().get(rezervaceId);	
				
/*				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				String a2 = format.format(nr.getDatumZacatek());
				DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				String a1 = date.format(format2);*/
				
				
				LocalDate datez = nr.getDatumZacatek();
				LocalDate datek = nr.getDatumKonec();
				
				String pokoj1 = pokoj.getValue();
				String pokoj2 = nr.getPokoj().getNazev();
				boolean containsP = pokoj1.contains(pokoj2);

				if(((date.isAfter(datez) || date.equals(datez)) && (date.isBefore(datek) || date.equals(datek))) && containsP) {
					seznamVsechRezervaci.add(hotel.getSeznamRezervaci().get(rezervaceId).toString());
				}
			}
			seznamRezervaci.getItems().addAll(seznamVsechRezervaci);
		};
		
		if (!(datum.getValue() == null) && isMyComboBoxEmpty && !isMyComboBoxEmpty2) {
			LocalDate date = datum.getValue();
			/*Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			LocalDate date = LocalDate.from(instant);*/
			
			for (Integer rezervaceId : hotel.getSeznamRezervaci().keySet()) {
				NovaRezervace nr = hotel.getSeznamRezervaci().get(rezervaceId);	
				
/*				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				String a2 = format.format(nr.getDatumZacatek());
				DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				String a1 = date.format(format2);*/
				
				
				LocalDate datez = nr.getDatumZacatek();
				LocalDate datek = nr.getDatumKonec();
				

				String klient1 = klient.getValue();
				String klient2 = String.valueOf(nr.getKlient().getCisloOP());
				boolean containsK = klient1.contains(klient2);
				
				if(((date.isAfter(datez) || date.equals(datez)) && (date.isBefore(datek) || date.equals(datek))) && containsK) {
					seznamVsechRezervaci.add(hotel.getSeznamRezervaci().get(rezervaceId).toString());
				}
			}
			seznamRezervaci.getItems().addAll(seznamVsechRezervaci);
		};
		
		
		
		if (!(datum.getValue() == null) && isMyComboBoxEmpty && isMyComboBoxEmpty2) {
			LocalDate date = datum.getValue();
			/*Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			LocalDate date = LocalDate.from(instant);*/
			
			for (Integer rezervaceId : hotel.getSeznamRezervaci().keySet()) {
				NovaRezervace nr = hotel.getSeznamRezervaci().get(rezervaceId);	
				
/*				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				String a2 = format.format(nr.getDatumZacatek());
				DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				String a1 = date.format(format2);*/
				
				
				LocalDate datez = nr.getDatumZacatek();
				LocalDate datek = nr.getDatumKonec();
				
				if((date.isAfter(datez) || date.equals(datez)) && (date.isBefore(datek) || date.equals(datek))) {
					seznamVsechRezervaci.add(hotel.getSeznamRezervaci().get(rezervaceId).toString());
				}
			}
			seznamRezervaci.getItems().addAll(seznamVsechRezervaci);
		};
		if(!isMyComboBoxEmpty && (datum.getValue() == null) && isMyComboBoxEmpty2) {
			for (Integer rezervaceId : hotel.getSeznamRezervaci().keySet()) {
				NovaRezervace nr = hotel.getSeznamRezervaci().get(rezervaceId);	
				
				String pokoj1 = pokoj.getValue();
				String pokoj2 = nr.getPokoj().getNazev();
				
				
				System.out.println(pokoj1);
				System.out.println(pokoj2);
				boolean contains = pokoj1.contains(pokoj2);
				if(contains) {
					seznamVsechRezervaci.add(hotel.getSeznamRezervaci().get(rezervaceId).toString());
				}
			}
			seznamRezervaci.getItems().addAll(seznamVsechRezervaci);

		};
		if(!isMyComboBoxEmpty2 && (datum.getValue() == null) && isMyComboBoxEmpty) {
			for (Integer rezervaceId : hotel.getSeznamRezervaci().keySet()) {
				NovaRezervace nr = hotel.getSeznamRezervaci().get(rezervaceId);	
				
				String klient1 = klient.getValue();
				String klient2 = String.valueOf(nr.getKlient().getCisloOP());
				boolean contains = klient1.contains(klient2);
				if(contains) {
					seznamVsechRezervaci.add(hotel.getSeznamRezervaci().get(rezervaceId).toString());
				}
			}
			seznamRezervaci.getItems().addAll(seznamVsechRezervaci);

		};
	}
	
	@FXML
	public void selectAllClick() throws Exception{
	    seznamRezervaci.getItems().clear();
	    seznamVsechRezervaci.removeAll(seznamVsechRezervaci);
		for (Integer rezervaceId : hotel.getSeznamRezervaci().keySet()) {
	      	seznamVsechRezervaci.add(hotel.getSeznamRezervaci().get(rezervaceId).toString());
	    }
		Collections.sort(seznamVsechRezervaci);
	    seznamRezervaci.getItems().addAll(seznamVsechRezervaci);
	}
	
	
	@FXML
	public void seznamRezervaciClick() throws Exception{
	    if (seznamRezervaci.getSelectionModel().isEmpty()) {}
	    else {
	    System.out.println(hotel.getSeznamRezervaci().get(Integer.parseInt(seznamRezervaci.getSelectionModel().getSelectedItem().split(",")[0])));
		NovaRezervace rezervace = hotel.getSeznamRezervaci().get(Integer.parseInt(seznamRezervaci.getSelectionModel().getSelectedItem().split(",")[0]));
	    FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/upravitRezervaci.fxml"));
		Parent root = loader.load();
		ControllerUpravitRezervaci controller = loader.getController();
		controller.inicializuj(hotel,rezervace);
		Stage upravitRezervaci = new Stage();
		upravitRezervaci.setScene(new Scene(root));
		upravitRezervaci.show();
		upravitRezervaci.setTitle("Editace rezervace ID: "+rezervace.getIdRezervace());
	    }
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	    pokoj.getItems().clear();
	    klient.getItems().clear();

	    seznamPokoju.removeAll(seznamPokoju);
	    seznamKlientu.removeAll(seznamKlientu);

		for (String pokojKlic : hotel.getPokoje().keySet()) {
			seznamPokoju.add(hotel.getPokoje().get(pokojKlic).toString());
	    }
	    for (Integer klientKlic : hotel.getKlienti().keySet()) {
	      	seznamKlientu.add(hotel.getKlienti().get(klientKlic).toString());
	    }
		seznamPokoju.sort(String.CASE_INSENSITIVE_ORDER);
		seznamKlientu.sort(String.CASE_INSENSITIVE_ORDER);
	    pokoj.getItems().addAll(seznamPokoju);
	    klient.getItems().addAll(seznamKlientu);
	}
	
	

}
