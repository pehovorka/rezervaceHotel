package com.github.pehovorka.rezervaceHotel.logika;

import java.util.HashMap;
import java.util.Map;
import com.github.pehovorka.rezervaceHotel.logika.Klient;

/**
 *  Třída Rezervace
 * 
 *  Hlavní třída rezervačního systému. Tato třída se stará o udržování seznamu klientů, pokojů a rezervací mezi nimi.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
public class Rezervace {
	
	private Map<Integer, Klient> seznamKlientu;
	private boolean rezimSpravce = false;
	private String[] tridyPokoju = {"Jednolůžko","Jednolůžko premium", "Dvoulůžko - double", "Dvoulůžko - twin", "Apartmán"};
	private Integer[] poctyLuzek = new Integer[10];

	
	/**
	 * Konstruktor vytváří jednotlivé seznamy (klienti, pokoje a vztahy mezi nimi).
	 */
	public Rezervace() {
		seznamKlientu = new HashMap<>();	
		poctyLuzek[0] = 1;
		for (int i = 0; i < 10; i++) {
		        poctyLuzek[i] = i + 1;
		}
		
	}
	
	/**
	 * Metoda vkládá klienta do mapy seznamKlientu.
	 * 
	 * @param k
	 *            Vkládáaný Klient. 
	 */
	public void vlozKlienta(Klient k) {
		seznamKlientu.put(k.getCisloOP(), k);
	}
	
    /**
     *  Metoda vrací mapu všech klientů.
     *  
     *  @return seznamKlientu Mapa všech klientů.
     */
	public Map<Integer, Klient> getKlienti() {	
		return seznamKlientu;
	}


    /**
     *  Vrátí boolean, zda se používá režim správce či nikoli.
     *  
     *  @return rezimSpravce boolean zda je uživatel v režimu správce.
     */
	public boolean isRezimSpravce(){
        return rezimSpravce;
    }
	
	/**
	 * Metoda nastavuje zda je nebo není zapnut režim správce.
	 * 
	 * @param rezimSpravce
	 *            Nastavuje boolean zapnutí režimu správce. 
	 */
	public void setRezimSpravce(boolean rezimSpravce) {
		this.rezimSpravce = rezimSpravce;
	}
	
    /**
     *  Metoda vrátí třídy pokojů.
     *  
     *  @return tridyPokoju Všechny třídy pokojů.
     */
    public String[] getTridyPokoju(){
        return tridyPokoju;
    }
    
    /**
     *  Metoda vrátí pole s počty lůžek.
     *  
     *  @return poctyLuzek Pole s počty lůžek.
     */
    public Integer[] getPoctyLuzek(){
        return poctyLuzek;
    }
	
}

