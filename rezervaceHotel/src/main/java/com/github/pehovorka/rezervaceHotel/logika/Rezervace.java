package com.github.pehovorka.rezervaceHotel.logika;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
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
	private String[] tridyPokoju = { "economy", "premium", "exclusive" };
	private Integer[] poctyLuzek = new Integer[10];
	File klientiSoubor = new File(getClass().getResource("/dataRezervaci/klienti.csv").getFile());
	File pokojeSoubor = new File(getClass().getResource("/dataRezervaci/pokoje.csv").getFile());

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
		nactiSoubor(klientiSoubor, "klienti");
		nactiSoubor(pokojeSoubor, "pokoje");

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
					} else {
						System.out.println(radek);
						Klient k = new Klient(cast[0], cast[1], Integer.parseInt(cast[2]));
						seznamKlientu.put(k.getCisloOP(), k);
						radek = ctecka.readLine();
					}
				}
			}
			if (typ.equals("pokoje")) {
				System.out.println("pokoje.csv");
				while (radek != null) {
					String[] cast = radek.split(",");
					if (cast.length != 5) {
						throw new Exception();
					} else {
						System.out.println(radek);
						Pokoj p = new Pokoj(cast[0], cast[1], Integer.parseInt(cast[2]), Integer.parseInt(cast[3]),
								Integer.parseInt(cast[4]));
						seznamPokoju.put(p.getNazev(), p);
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

	public void ulozSoubor(String typ) {
		if (typ.equals("klienti")) {
			File soubor = klientiSoubor;
			try (BufferedWriter zapisovac = new BufferedWriter(new FileWriter(soubor))) {
				for (Integer klientKlic : getKlienti().keySet()) {
					String radek = getKlienti().get(klientKlic).getJmeno() + ","
							+ getKlienti().get(klientKlic).getPrijmeni() + ","
							+ getKlienti().get(klientKlic).getCisloOP();
					zapisovac.write(radek);
					zapisovac.newLine();
				}
				zapisovac.close();
			} catch (Exception e) {
			}
			System.out.println("Ukládám klienty...");
		}
		if (typ.equals("pokoje")) {
			File soubor = pokojeSoubor;
			try (BufferedWriter zapisovac = new BufferedWriter(new FileWriter(soubor))) {
				for (String pokojeKlic : getPokoje().keySet()) {
					String radek = getPokoje().get(pokojeKlic).getNazev() + "," + getPokoje().get(pokojeKlic).getTrida()
							+ "," + getPokoje().get(pokojeKlic).getPocetLuzek() + ","
							+ getPokoje().get(pokojeKlic).getCena() + "," + getPokoje().get(pokojeKlic).getCenaSezona();
					zapisovac.write(radek);
					zapisovac.newLine();
				}
				zapisovac.close();
			} catch (Exception e) {
			}
			System.out.println("Ukládám pokoje...");
		}
	}

}
