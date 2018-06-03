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
public class TestPokoj {
		private Hotel hotel;
		/**
	     * Metoda pro vytvoření podkladů pro testování
	     *     
	     */
	    @Before
	    public void setUp() {
	        hotel  = new Hotel();
	    }
	    
	    /**
	     * Metoda pro testování zda klient existuje.
	     *
	     *     
	     */
	    @Test
		public void existujeRezervace() {
	    	Pokoj pokoj = new Pokoj("X010","premium",10,1000,1200);
	    	hotel.vlozPokoj(pokoj);;
	    	System.out.println(hotel.getPokoje());
			assertTrue(hotel.getPokoje().containsKey("X010"));
	    }
   
}