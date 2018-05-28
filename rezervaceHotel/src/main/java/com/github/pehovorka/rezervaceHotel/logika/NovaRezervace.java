package com.github.pehovorka.rezervaceHotel.logika;
import java.util.Date;

import java.util.Observable;

/**
 * Třída Nova Rezervace
 * 
 * Hlavní třída rezervačního systému. Tato třída se stará o udržování seznamu
 * klientů, pokojů a rezervací mezi nimi.
 *
 * @author Petr Hovorka, Aleksandr Kadesnikov
 * @version Alpha 1
 */
public class NovaRezervace extends Observable {

	private Date datumZacatek;
	private Date datumKonec;
	private Pokoj pokoj;
	private Klient klient;
	private int id;

	/**
	 * Konstruktor vytváří jednotlivé seznamy (klienti, pokoje a vztahy mezi nimi).
	 */
	public NovaRezervace(Integer id,Date datumZacatek, Date datumKonec, Pokoj pokoj, Klient klient) {
		this.datumKonec = datumKonec;
		this.datumZacatek = datumZacatek;
		this.pokoj = pokoj;
		this.klient = klient;
		this.id = id;

	}
	/**
	    * Getter pro vyvolání pokoje
	    * 
	    * @return vrací pokoj
	    */
	    public Pokoj getPokoj() {
	        return pokoj;
	    }
	    
	    /**
	    * Setter pro nastavení pokoje
	    * 
	    * @param	pokoj 
	    */
	    public void setPokoj(Pokoj pokoj) {
	        this.pokoj = pokoj;
	    }
	        
	    /**
	    * Getter pro získání data konce rezervace
	    * 
	    * @return	vrací datumKonec
	    */
	    public Date getDatumKonec() {
	        return datumKonec;
	    }
	    
	    /**
	    * Setter pro nastavení data konce rezervace
	    * 
	    * @param Date datum konce rezervace
	    */
	    public void setDatumKonec(Date datumKonec) {
	        this.datumKonec = datumKonec;
	    }
	    
	    /**
	    * Getter pro získání zacatku rezervace
	    * 
	    * @return vrací den zacatku
	    */
	    public Date getDatumZacatek() {
	        return datumZacatek;
	    }
	    
	    /**
	    * Setter pro nastavení data zacatku rezervace
	    * 
	    * @param date zacatek
	    */
	    public void setDatumZacatek(Date datumZacatek) {
	        this.datumZacatek = datumZacatek;
	    }
	    
	    /**
	    * Getter pro získání klienta přiděleného k rezervaci
	    * 
	    * @return vrací klienta
	    */
	    public Klient getKlient() {
	        return klient;
	    }
	    
	    /**
	    * Getter pro nastavení jména
	    * 
	    * @param String jmeno - jméno typu string
	    */
	    public void setKlient(Klient klient) {
	        this.klient = klient;
	    }
	    
	    /**
		    * Getter pro získání id
		    * 
		    * @return vrací id
		    */
		    public Integer getIdRezervace() {
		        return id;
		    }
		    
		    /**
		    * Getter pro nastavení id
		    * 
		    * @param nastavuje id
		    */
		    public void setIdRezervace(Integer id) {
		        this.id = id;
		    }
	    
	    /**
	     * Metoda vrací rezervaci včetně parametrů
	     *
	     * @return nazevSParametry
	     */
	    public String getRezervace() {
	        String rezervaceSParametry = getIdRezervace() + ", datum začátku- " + getDatumZacatek() + ", datum konce- "  + getDatumKonec() + ", pokoj: " + getPokoj() + " pro: " + getKlient(); // klient.getCeleJmeno();
	    	return rezervaceSParametry;       
	    }
		
	    @Override
		public String toString() {
			return getRezervace();
		}

	

	

}
