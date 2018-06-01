package com.github.pehovorka.rezervaceHotel.logika;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

import com.github.pehovorka.rezervaceHotel.logika.Klient;
import com.github.pehovorka.rezervaceHotel.logika.NovaRezervace;


/**
 * Třída Rezervace
 * 
 * Hlavní třída rezervačního systému. Tato třída se stará o udržování seznamu
 * klientů, pokojů a rezervací mezi nimi.
 *
 * @author Petr Hovorka, Aleksandr Kadesnikov
 * @version Alpha 1
 */
public class Hotel extends Observable {
	private Map<Integer, Klient> seznamKlientu;
	private Map<String, Pokoj> seznamPokoju;
	private Map<Integer, NovaRezervace> seznamRezervaci;
	private boolean rezimSpravce = false;
	private String[] tridyPokoju = { "economy", "premium", "exclusive" };
	private Integer[] poctyLuzek = new Integer[10];
	String klientiSoubor = "./hotelData/klienti.csv";
	String pokojeSoubor = "./hotelData/pokoje.csv";
	String rezervaceSoubor = "./hotelData/rezervace.csv";
	String klientiResources = getClass().getResource("/dataRezervaci/klienti.csv").getFile();
	String pokojeResources = getClass().getResource("/dataRezervaci/pokoje.csv").getFile();
	String rezervaceResources = getClass().getResource("/dataRezervaci/rezervace.csv").getFile();
	InputStream klientiStream = getClass().getResourceAsStream("/dataRezervaci/klienti.csv");
	InputStream pokojeStream = getClass().getResourceAsStream("/dataRezervaci/pokoje.csv");
	InputStream rezervaceStream = getClass().getResourceAsStream("/dataRezervaci/rezervace.csv");

	/**
	 * Konstruktor vytváří jednotlivé seznamy (klienti, pokoje a vztahy mezi nimi).
	 */
	public Hotel() {
		seznamKlientu = new HashMap<>();
		seznamPokoju = new HashMap<>();
		seznamRezervaci = new HashMap<>();
		poctyLuzek[0] = 1;
		for (int i = 0; i < 10; i++) {
			poctyLuzek[i] = i + 1;
		}
		nactiSoubor(klientiSoubor, "klienti");
		nactiSoubor(pokojeSoubor, "pokoje");
		nactiSoubor(rezervaceSoubor, "rezervace");

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
	 * Metoda vrací klienta.
	 * 
	 * @return urcity klienta.
	 */
	public Klient getKlient(Integer jmeno) {
		return seznamKlientu.get(jmeno);
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
	 * Metoda vrací pokoj.
	 * 
	 * @return urcity pokoj.
	 */
	public Pokoj getPokoj(String nazev) {
		return seznamPokoju.get(nazev);
	}

	
	/**
	 * Metoda vkládá rezervace do mapy rezervaci.
	 * 
	 * @param r
	 *            Vkladana rezervace.
	 */
	public void vlozRezervaci(NovaRezervace r) {
		seznamRezervaci.put(r.getIdRezervace(), r);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Metoda odebere rezervace z mapy rezervaci.
	 * 
	 * @param r
	 *            Odebrana rezervace.
	 */
	public void odeberRezervaci(NovaRezervace r) {
		seznamRezervaci.remove(r.getIdRezervace(), r);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Metoda vrací mapu všech rezervací.
	 * 
	 * @return seznamRezervaci Mapa všech rezervací.
	 */
	public Map<Integer, NovaRezervace> getSeznamRezervaci() {
		return seznamRezervaci;
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
	
	public NovaRezervace getNazevRezervace(Integer nazev) {
		return seznamRezervaci.get(nazev);
	}
	

	/**
	 * Metoda pro zkopírování souborů.
	 * 
	 * @param soubor1
	 *            Adresa zdrojového souboru
	 * @param soubor2
	 *            Adresa cílového souboru
	 * @param stream
	 *            InputStream zdrojového souboru
	 */
	private void zkopirujSoubor(String soubor1, String soubor2, InputStream stream) {

		/* Zdrojový soubor, který bude zkopírován */
		File zdrojovySoubor = new File(soubor1);

		/* Cílový soubor */
		File cilovySoubor = new File(soubor2);

		/* Pokud soubor neexistuje, tak ho vytvoř */
		if (!zdrojovySoubor.exists()) {
			try {
				cilovySoubor.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		InputStream vstup = stream;
		OutputStream vystup = null;

		try {

			/* FileOutputStream pro zápis streamů */
			vystup = new FileOutputStream(cilovySoubor);

			byte[] buf = new byte[1024];
			int bytesRead;

			while ((bytesRead = vstup.read(buf)) > 0) {
				vystup.write(buf, 0, bytesRead);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {

				if (null != vstup) {
					vstup.close();
				}

				if (null != vystup) {
					vystup.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void nactiSoubor(String soubor, String typ) {
		File slozka = new File("hotelData");
		if (!slozka.exists()) {
			System.out.println("Vytvářím složku: " + slozka.getName());
			boolean vysledek = false;

			try {
				slozka.mkdir();
				vysledek = true;
			} catch (SecurityException se) {
				System.out.println("Nelze vytvořit složku, nemáte nedostatečné oprávnění.");
			}
			if (vysledek) {
				System.out.println("Složka vytvořena");
			}
			try {
				zkopirujSoubor(klientiResources, klientiSoubor, klientiStream);
				zkopirujSoubor(pokojeResources, pokojeSoubor, pokojeStream);
				zkopirujSoubor(rezervaceResources, rezervaceSoubor, rezervaceStream);
				System.out.println("Kopíruji soubory");
			} catch (Exception e) {
				System.out.println("Nelze vytvořit soubory");
			}

		}

		File souborSoubor = new File(soubor);
		if (!souborSoubor.exists()) {
			zkopirujSoubor(klientiResources, klientiSoubor, klientiStream);
			zkopirujSoubor(pokojeResources, pokojeSoubor, pokojeStream);
			zkopirujSoubor(rezervaceResources, rezervaceSoubor, rezervaceStream);
			System.out.println("Kopíruji soubory");
		}
		try (BufferedReader ctecka = new BufferedReader(new FileReader(soubor))) {
			String radek = ctecka.readLine();
			if (typ.equals("rezervace")) {
				System.out.println("rezervace.csv");
				while (radek != null) {
					String[] cast = radek.split(",");
					if (cast.length != 9) {
						throw new Exception();
					} else {
						
						String pokoj = cast[7];
						Integer klient = Integer.parseInt(cast[8]);
						System.out.println(radek);
						int den = Integer.parseInt(cast[1]);
						int mesic = Integer.parseInt(cast[2]);
						int rok = Integer.parseInt(cast[3]);
						int den2 = Integer.parseInt(cast[4]);
						int mesic2 = Integer.parseInt(cast[5]);
						int rok2 = Integer.parseInt(cast[6]);
						
						LocalDate dateZ = LocalDate.of(rok, mesic, den);
						LocalDate dateK = LocalDate.of(rok2, mesic2, den2);
						
						Pokoj p = getPokoj(pokoj);
						Klient k = getKlient(klient);
						
						//NovaRezervace r = new NovaRezervace(Integer.parseInt(cast[0]), dateZ, dateK, p, k);
						NovaRezervace r = new NovaRezervace(Integer.parseInt(cast[0]), dateZ, dateK, p, k);
						seznamRezervaci.put(r.getIdRezervace(), r);
						radek = ctecka.readLine();
					}

				}
			}
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
			
			ctecka.close();
		}

		catch (FileNotFoundException e) {
			System.out.println("Soubor nenalezen");

		} catch (IOException e) {
			System.out.println("Chyba vstupu");
		} catch (Exception e) {
			System.out.println("Chyba při načítání řádku, neplatný počet parametrů");
		}
	}

	public void ulozSoubor(String typ) throws FileNotFoundException {
		if (typ.equals("klienti")) {
			FileOutputStream klientiOutStream = new FileOutputStream(klientiSoubor);
			try (BufferedWriter zapisovac = new BufferedWriter(new OutputStreamWriter(klientiOutStream))) {
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
			FileOutputStream pokojeOutStream = new FileOutputStream(pokojeSoubor);
			try (BufferedWriter zapisovac = new BufferedWriter(new OutputStreamWriter(pokojeOutStream))) {
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
		
		if (typ.equals("rezervace")) {
			FileOutputStream rezervaceOutStream = new FileOutputStream(rezervaceSoubor);
			try (BufferedWriter zapisovac = new BufferedWriter(new OutputStreamWriter(rezervaceOutStream))) {
				for (Integer rezervaceKlic : getSeznamRezervaci().keySet()) {
					String radek = getSeznamRezervaci().get(rezervaceKlic).getIdRezervace() + "," 
				+ getSeznamRezervaci().get(rezervaceKlic).getDatumZacatek().getDayOfMonth() + "," + 
				getSeznamRezervaci().get(rezervaceKlic).getDatumZacatek().getMonth().getValue() + "," +	
				getSeznamRezervaci().get(rezervaceKlic).getDatumZacatek().getYear() + "," +		
				getSeznamRezervaci().get(rezervaceKlic).getDatumKonec().getDayOfMonth() + "," + 
				getSeznamRezervaci().get(rezervaceKlic).getDatumKonec().getMonth().getValue() + "," +	
				getSeznamRezervaci().get(rezervaceKlic).getDatumKonec().getYear() + "," 		
				+ getSeznamRezervaci().get(rezervaceKlic).getPokoj().getNazev() + "," + getSeznamRezervaci().get(rezervaceKlic).getKlient().getCisloOP();
					zapisovac.write(radek);
					zapisovac.newLine();
					
				}
				zapisovac.close();
			} catch (Exception e) {
			}
			System.out.println("Ukládám rezervace...");
		}
	}

}
