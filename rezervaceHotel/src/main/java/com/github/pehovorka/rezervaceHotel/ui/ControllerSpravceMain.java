package com.github.pehovorka.rezervaceHotel.ui;

import com.github.pehovorka.rezervaceHotel.logika.Rezervace;

import javafx.scene.layout.GridPane;


/**
 *  Třída ControllerSpravceMain
 * 
 *  Kontroler, který zprostředkovává komunikaci mezi grafikou hlavního okna správce a logikou adventury.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
public class ControllerSpravceMain extends GridPane {
	
	Rezervace rezervace;
	
	
	/**
	 * Metoda provede inicializaci grafických prvků
	 * 
	 * @param rezervace
	 *            aktuální rezervace
	 * 
	 */
	public void inicializuj(Rezervace rezervace) {
		this.rezervace = rezervace;
	}

}
