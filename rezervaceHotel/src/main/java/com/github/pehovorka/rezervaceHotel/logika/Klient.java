package com.github.pehovorka.rezervaceHotel.logika;


/**
 *  Třída Klient
 * 
 *  V této třídě se vytvářejí jednotliví klienti.
 *
 *@author     Petr Hovorka, Aleksandr Kadesnikov
 *@version    Alpha 1
 */
public class Klient {
	private String jmeno;
	private String prijmeni;
	private int cisloOP;
	
	/**
	 * Vytváří klienta.
	 * 
	 * @param jmeno
	 *            Křestní jméno klienta (např. Josef)
	 * @param prijmeni
	 *            Příjmení klienta (např. Novák).
	 * @param cisloOP
	 *            Číslo občanského průkazu klienta.
	 */
	public Klient(String jmeno, String prijmeni, Integer cisloOP )
    {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.cisloOP = cisloOP;
    }
    
    /**
     *  Metoda vrátí křestní jméno klienta.
     *  
     *  @return jmeno Jméno klienta.
     */
    public String getJmeno(){
        return jmeno;
    }
    
    /**
     *  Metoda vrátí příjmení klienta.
     *  
     *  @return prijmeni Příjmení klienta.
     */
    public String getPrijmeni(){
        return prijmeni;
    }
    
    /**
     *  Metoda vrátí číslo občanského průkazu klienta.
     *  
     *  @return cisloOP Číslo občanského průkazu klienta.
     */
    public int getCisloOP(){
        return cisloOP;
    }
    
	/**
	 * Metoda nastaví jméno klientovi.
	 * 
	 * @param jmeno
	 *            Křestní jméno klienta 
	 */
	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}
	
	/**
	 * Metoda nastaví příjmení klientovi.
	 * 
	 * @param prijmeni
	 *            Příjmení klienta 
	 */
	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}
	
	/**
	 * Metoda nastaví klientovi číslo občanského průkazu.
	 * 
	 * @param cisloOP
	 *            Číslo občanského průkazu klienta 
	 */
	public void setCisloOP(Integer cisloOP) {
		this.cisloOP = cisloOP;
	}
	
    /**
     * Metoda vrací celé jméno klienta včetně čísla OP
     *
     * @return celeJmeno
     */
    public String getCeleJmeno() {
        String celeJmeno = getJmeno() + " " + getPrijmeni() + " (OP: " + getCisloOP()+")";
    	return celeJmeno;       
    }
	
	@Override
	public String toString() {
		return getCeleJmeno();
	}

}
