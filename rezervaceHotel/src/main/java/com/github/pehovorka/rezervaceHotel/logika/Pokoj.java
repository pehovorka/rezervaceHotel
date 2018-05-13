package com.github.pehovorka.rezervaceHotel.logika;

/**
 *  Třída Pokoj
 * 
 *  Třída popisuje pokoje a jejich vlastnosti.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
public class Pokoj {
	private String trida;
	private String nazev;
	private int pocetLuzek;
	private int cena;
	private int cenaSezona;

	/**
	 * Vytváří pokoj.
	 * 
	 * @param nazev
	 *            Název pokoje (např. 105A)
	 * @param trida
	 *            Třída, kategorie pokoje (např. dvoulůžkový (double)).
	 * @param pocetLuzek
	 *            Počet lůžek v pokoji.
	 * @param cena
	 *            Bežná cena mimo sezónu.
	 * @param y
	 *            Cena během sezóny.
	 */
	public Pokoj(String nazev, String trida, int pocetLuzek, int cena, int cenaSezona) {
		this.nazev = nazev;
		this.trida = trida;
		this.pocetLuzek = pocetLuzek;
		this.cena = cena;
		this.cenaSezona = cenaSezona;
	}

}
