package com.github.pehovorka.rezervaceHotel.logika;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import com.github.pehovorka.rezervaceHotel.logika.Klient;

/**
 * Třída Rezervace
 * 
 * Hlavní třída rezervačního systému. Tato třída se stará o udržování seznamu
 * klientů, pokojů a rezervací mezi nimi.
 *
 * @author Petr Hovorka, Aleksandr Kadesnikov
 * @version Alpha 1
 */
public class Rezervace extends Observable {

	private Map<Integer, Klient> seznamKlientu;
	private Map<String, Pokoj> seznamPokoju;
	private boolean rezimSpravce = false;
	private String[] tridyPokoju = { "Jednolůžko", "Jednolůžko premium", "Dvoulůžko - double", "Dvoulůžko - twin",
			"Apartmán" };
	private Integer[] poctyLuzek = new Integer[10];

	/**
	 * Konstruktor vytváří jednotlivé seznamy (klienti, pokoje a vztahy mezi nimi).
	 */
	public Rezervace() {
		seznamKlientu = new HashMap<>();
		seznamPokoju = new HashMap<>();
		poctyLuzek[0] = 1;
		for (int i = 0; i < 10; i++) {
			poctyLuzek[i] = i + 1;
		}
		File klientiSoubor = new File(getClass().getResource("/dataRezervaci/klienti.csv").getFile());
		nactiSoubor(klientiSoubor, "klienti");

	}

	/**
	 * Metoda vkládá klienta do mapy seznamKlientu.
	 * 
	 * @param k
	 *            Vkládaný Klient.
	 */
	public void vlozKlienta(Klient k) {
		seznamKlientu.put(k.getCisloOP(), k);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Metoda vrací mapu všech klientů.
	 * 
	 * @return seznamKlientu Mapa všech klientů.
	 */
	public Map<Integer, Klient> getKlienti() {
		return seznamKlientu;
	}

	/**
	 * Metoda vkládá pokoj do mapy seznamPokoju.
	 * 
	 * @param p
	 *            Vkládaný pokoj.
	 */
	public void vlozPokoj(Pokoj p) {
		seznamPokoju.put(p.getNazev(), p);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Metoda vrací mapu všech pokojů.
	 * 
	 * @return seznamPokoju Mapa všech pokojů.
	 */
	public Map<String, Pokoj> getPokoje() {
		return seznamPokoju;
	}

	/**
	 * Vrátí boolean, zda se používá režim správce či nikoli.
	 * 
	 * @return rezimSpravce boolean zda je uživatel v režimu správce.
	 */
	public boolean isRezimSpravce() {
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
	 * Metoda vrátí třídy pokojů.
	 * 
	 * @return tridyPokoju Všechny třídy pokojů.
	 */
	public String[] getTridyPokoju() {
		return tridyPokoju;
	}

	/**
	 * Metoda vrátí pole s počty lůžek.
	 * 
	 * @return poctyLuzek Pole s počty lůžek.
	 */
	public Integer[] getPoctyLuzek() {
		return poctyLuzek;
	}

	public void nacti(String nazevSouboru) {

	}

	public void nactiSoubor(File soubor, String typ) {
		try (BufferedReader ctecka = new BufferedReader(new FileReader(soubor))) {
			String radek = ctecka.readLine();
			if (typ.equals("klienti")) {
				System.out.println("klienti.csv");
				while (radek != null) {
					String[] cast = radek.split(",");
					if (cast.length != 3) {
						throw new Exception();
					}
					else {
					System.out.println(radek);
					Klient k = new Klient(cast[0], cast[1], Integer.parseInt(cast[2]));
					seznamKlientu.put(k.getCisloOP(), k);
					radek = ctecka.readLine();
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Soubor nenlezen");
		} catch (IOException e) {
			System.out.println("Chyba vstupu");
		} catch (Exception e) {
			System.out.println("Chyba při načítání řádku, neplatný počet parametrů");
		}
	}

}
