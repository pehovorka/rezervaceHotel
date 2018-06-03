package com.github.pehovorka.rezervaceHotel;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.pehovorka.rezervaceHotel.logika.Hotel;

/*******************************************************************************
 * Testovací třída NacitaniSouboru slouží ke komplexnímu otestování
 * načítání vstupních dat
 *
 * @author     Aleksandr Kadesnikov
 * 
 */
public class NacitaniSouboru {
	private Hotel hotel;
	String klientiSoubor = "./hotelData/klienti.csv";
	String pokojeSoubor = "./hotelData/pokoje.csv";
	String rezervaceSoubor = "./hotelData/rezervace.csv";
	/**
     * Metoda pro vytvoření podkladů pro testování
     *     
     */
    @Before
    public void setUp() {
        hotel  = new Hotel();
    }
    
    /**
     * Metoda pro testování zda soubor klienti existuje.
     * 
     *     
     */
    @Test
	public void existujeSouborKlient() {
    	hotel.nactiSoubor(klientiSoubor, "klienti");
		assertTrue(!hotel.getKlienti().isEmpty());
		// pozn. test projde jelikož se vytvoří zadaný soubor.
    }
    /**
     * Metoda pro testování zda soubor rezervace existuje.
     * 
     *     
     */
    @Test
	public void existujeSouborRezervace() {
    	hotel.nactiSoubor(rezervaceSoubor, "rezervace");
		assertTrue(!hotel.getSeznamRezervaci().isEmpty());
		// pozn. test projde jelikož se vytvoří zadaný soubor.
    }
    /**
     * Metoda pro testování zda soubor pokoje existuje.
     * 
     *     
     */
    @Test
	public void existujeSouborPokoj() {
    	hotel.nactiSoubor(pokojeSoubor, "pokoje");
		assertTrue(!hotel.getPokoje().isEmpty());
		// pozn. test projde jelikož se vytvoří zadaný soubor.
    }

}