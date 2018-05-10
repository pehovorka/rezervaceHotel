package com.github.pehovorka.rezervaceHotel.ui;

import com.github.pehovorka.rezervaceHotel.logika.Rezervace;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class HomeController extends GridPane {

	private Rezervace rezervace;
	
	@FXML
	private Button buttonSpravce;
	@FXML
	private Button buttonZakaznik;

	public void inicializuj(Rezervace rezervace) {
		this.rezervace = rezervace;
		
	}
	
	@FXML
	public void buttonZakaznikClick() {
		System.out.println("zakaznik");
		Platform.exit();
	}
	
	@FXML
	public void buttonSpravceClick() {
		System.out.println("spravce");
		
		Platform.exit();
	}
}
