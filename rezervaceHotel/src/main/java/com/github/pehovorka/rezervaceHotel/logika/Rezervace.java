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
	
	/**
	 * Konstruktor vytváří jednotlivé seznamy (klienti, pokoje a vztahy mezi nimi).
	 */
	public Rezervace() {
		seznamKlientu = new HashMap<>();
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
	}


