package com.github.pehovorka.rezervaceHotel;


import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.github.pehovorka.rezervaceHotel.logika.Hotel;
import com.github.pehovorka.rezervaceHotel.logika.NovaRezervace;
import com.github.pehovorka.rezervaceHotel.logika.Klient;
import com.github.pehovorka.rezervaceHotel.logika.Pokoj;

/*******************************************************************************
 * Testovací třída NacitaniTest slouží ke komplexnímu otestování
 * načítání vstupních dat
 *
 * @author     Aleksandr Kadesnikov
 * 
 */
public class TestRezervace {
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
     * Metoda pro testování zda rezervace existuje.
     *
     *     
     */
    @Test
	public void existujeRezervace() {
    	LocalDate date = LocalDate.now();
    	LocalDate date2 = LocalDate.now().plusDays(1);
    	Pokoj pokoj = hotel.getPokoj("A001");
    	Klient klient = hotel.getKlient(664542832);
    	NovaRezervace r = new NovaRezervace(6, date, date2, pokoj, klient, 333);
    	hotel.vlozRezervaci(r);
    	System.out.println(hotel.getSeznamRezervaci());
		assertTrue(hotel.getSeznamRezervaci().containsKey(6));
		hotel.odeberRezervaci(r);
		System.out.println(hotel.getSeznamRezervaci());
		assertTrue(!hotel.getSeznamRezervaci().containsKey(6));
		// pozn. test projde jelikož se vytvoří zadaný soubor.
    }
   
}