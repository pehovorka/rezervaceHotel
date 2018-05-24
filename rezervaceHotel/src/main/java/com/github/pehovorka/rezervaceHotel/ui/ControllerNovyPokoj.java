package com.github.pehovorka.rezervaceHotel.ui;

import java.util.Map.Entry;

import com.github.pehovorka.rezervaceHotel.logika.Pokoj;
import com.github.pehovorka.rezervaceHotel.logika.Hotel;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Třída ControllerNovyPokoj
 * 
 * Kontroler, který zprostředkovává komunikaci mezi grafikou okna pro založení
 * nového pokoje a logikou rezervačního systému.
 *
 * @author Petr Hovorka, Aleksandr Kadesnikov
 * @version Alpha 1
 */
public class ControllerNovyPokoj extends GridPane {

	@FXML
	private ComboBox<Integer> comboBoxPocetLuzek;
	@FXML
	private ComboBox<String> comboBoxTrida;
	@FXML
	private TextField textFieldCena;
	@FXML
	private TextField textFieldCenaSezona;
	@FXML
	private TextField textFieldNazev;
	@FXML
	private Button buttonZrusit;
	@FXML
	private Button buttonPotvrdit;

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
		comboBoxTrida.getItems().addAll(rezervace.getTridyPokoju());
		comboBoxPocetLuzek.getItems().addAll(rezervace.getPoctyLuzek());

	}

	@FXML
	public void buttonZrusitClick() throws Exception {
		Stage stage = (Stage) buttonZrusit.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void buttonPotvrditClick() throws Exception {
		if (textFieldCena.getText().equals("") || textFieldCenaSezona.getText().equals("")
				|| textFieldNazev.getText().equals("") || comboBoxPocetLuzek.getSelectionModel().isEmpty()
				|| comboBoxPocetLuzek.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Špatně zadané údaje");
			alert.setHeaderText("Zadejte všechny údaje!");
			alert.showAndWait();
		} else {
			try {
				Pokoj vkladany = new Pokoj(textFieldNazev.getText(),
						comboBoxTrida.getSelectionModel().getSelectedItem(),
						comboBoxPocetLuzek.getSelectionModel().getSelectedItem(),
						Integer.parseInt(textFieldCena.getText()), Integer.parseInt(textFieldCenaSezona.getText()));
				if (rezervace.getPokoje().containsKey(vkladany.getNazev())) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Pokoj s tímto názvem již existuje");
					alert.setHeaderText("Zadejte jiný název pokoje, pokoj s tímto názvem již existuje!");
					alert.showAndWait();
				} else {
					rezervace.vlozPokoj(vkladany);
					Stage stage = (Stage) buttonPotvrdit.getScene().getWindow();
					stage.close();
					for (Entry<String, Pokoj> entry : rezervace.getPokoje().entrySet()) {
						System.out.println("Název: " + entry.getKey() + "; Třída: " + entry.getValue().getTrida()
								+ "; Počet lůžek: " + entry.getValue().getPocetLuzek() + "; Cena: "
								+ entry.getValue().getCena() + "; Cena v sezóně: " + entry.getValue().getCenaSezona());
					}
				}
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Špatný formát ceny");
				alert.setHeaderText("Cena musí být pouze celé číslo!");
				alert.showAndWait();
			}

		}
	}
}
