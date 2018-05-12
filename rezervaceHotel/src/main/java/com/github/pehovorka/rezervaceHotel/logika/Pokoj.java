package com.github.pehovorka.rezervaceHotel.logika;

public class Pokoj {
	private String trida;
	private String nazev;
	private int pocetLuzek;
	private int cena;
	private int cenaSezona;
	
    public Pokoj(String nazev, String trida, int pocetLuzek, int cena, int cenaSezona)
    {
        this.nazev = nazev;
        this.trida = trida;
        this.pocetLuzek = pocetLuzek;
        this.cena = cena;
        this.cenaSezona = cenaSezona;
    }

}
