package com.github.pehovorka.rezervaceHotel.ui;


import com.github.pehovorka.rezervaceHotel.logika.Rezervace;

import javafx.scene.layout.GridPane;

public class HomeController extends GridPane {

	private Rezervace rezervace;

	public void inicializuj(Rezervace rezervace) {
		this.rezervace = rezervace;
	}
}
