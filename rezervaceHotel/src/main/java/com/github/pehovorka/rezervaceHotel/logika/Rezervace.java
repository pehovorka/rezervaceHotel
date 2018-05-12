package com.github.pehovorka.rezervaceHotel.logika;

import java.util.HashMap;
import java.util.Map;
import com.github.pehovorka.rezervaceHotel.logika.Klient;


public class Rezervace {
	
	private Map<Integer, Klient> seznamKlientu;
	
	public Rezervace() {
		seznamKlientu = new HashMap<>();
		Klient test = new Klient("Jméno","Příjmení",123);
		//seznamKlientu.put(test.getCisloOP(), test);
		vlozKlienta(test);
		System.out.println(getKlienti());
	}
	
	public void vlozKlienta(Klient k) {
		seznamKlientu.put(k.getCisloOP(), k);
	}
	
	public Map<Integer, Klient> getKlienti() {	
		return seznamKlientu;
	}
	}


