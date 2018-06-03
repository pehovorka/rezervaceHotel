package com.github.pehovorka.rezervaceHotel;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.pehovorka.rezervaceHotel.logika.Hotel;
import com.github.pehovorka.rezervaceHotel.logika.Klient;

/*******************************************************************************
 * Testovací třída TestKlient slouží ke komplexnímu otestování
 * načítání vstupních dat
 *
 * @author     Aleksandr Kadesnikov
 * 
 */
public class TestKlient {
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
	public void existujeKlient() {
    	Klient klient = new Klient("a","b",444333888);
    	hotel.vlozKlienta(klient);
    	System.out.println(hotel.getKlienti());
		assertTrue(hotel.getKlienti().containsValue(klient));
    }
   
}