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
	private String nazev;
	private String trida;
	private Integer pocetLuzek;
	private Integer cena;
	private Integer cenaSezona;


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
	 * @param cenaSezona
	 *            Cena během sezóny.
	 */
	public Pokoj(String nazev, String trida, Integer pocetLuzek, Integer cena, Integer cenaSezona) {
		this.nazev = nazev;
		this.trida = trida;
		this.pocetLuzek = pocetLuzek;
		this.cena = cena;
		this.cenaSezona = cenaSezona;
	}
	
    /**
     *  Metoda vrátí název pokoje.
     *  
     *  @return nazev Název pokoje.
     */
    public String getNazev(){
        return nazev;
    }
    
	/**
	 * Metoda nastaví název pokoje.
	 * 
	 * @param nazev
	 *            Název pokoje. 
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}
    
    /**
     *  Metoda vrátí třídu pokoje.
     *  
     *  @return trida Třída pokoje.
     */
    public String getTrida(){
        return trida;
    }
    
	/**
	 * Metoda nastaví třídu pokoje.
	 * 
	 * @param trida
	 *            Třída pokoje. 
	 */
	public void setTrida(String trida) {
		this.trida = trida;
	}
    
    /**
     *  Metoda vrátí počet lůžek v pokoji.
     *  
     *  @return pocetLuzek Počet lůžek v pokoji.
     */
    public int getPocetLuzek(){
        return pocetLuzek;
    }
    
	/**
	 * Metoda nastaví počet lůžek v pokoji.
	 * 
	 * @param pocetLuzek
	 *            Počet lůžek v pokoji. 
	 */
	public void setPocetLuzek(Integer pocetLuzek) {
		this.pocetLuzek = pocetLuzek;
	}
    
    /**
     *  Metoda vrátí mimosezónní cenu za pokoj.
     *  
     *  @return cena Mimosezónní cena za pokoj.
     */
    public int getCena(){
        return cena;
    }
    
	/**
	 * Metoda nastaví mimosezónní cenu za pokoj.
	 * 
	 * @param cena
	 *            Mimosezónní cena za pokoj. 
	 */
	public void setCena(Integer cena) {
		this.cena = cena;
	}
    
    /**
     *  Metoda vrátí sezónní cenu za pokoj.
     *  
     *  @return cenaSezona Sezónní cena za pokoj.
     */
    public int getCenaSezona(){
        return cenaSezona;
    }
    
	/**
	 * Metoda nastaví sezónní cenu za pokoj.
	 * 
	 * @param cenaSezona
	 *            Sezónní cena za pokoj. 
	 */
	public void setCenaSezona(Integer cenaSezona) {
		this.cenaSezona = cenaSezona;
	}
	
    /**
     * Metoda vrací název pokoje včetně parametrů
     *
     * @return nazevSParametry
     */
    public String getNazevSParametry() {
        String nazevSParametry = getNazev() + " - Třída: " + getTrida() + "; Počet lůžek: " + getPocetLuzek() + "; Cena: " + getCena() + "; Cena v sezóně: " + getCenaSezona();
    	return nazevSParametry;       
    }
	
	@Override
	public String toString() {
		return getNazevSParametry();
	}

}
