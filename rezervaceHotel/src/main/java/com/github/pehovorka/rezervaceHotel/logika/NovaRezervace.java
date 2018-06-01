package com.github.pehovorka.rezervaceHotel.logika;
import java.time.LocalDate;
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

	private LocalDate datumZacatek;
	private LocalDate datumKonec;
	private Pokoj pokoj;
	private Klient klient;
	private int id;
	private int cenaZaRezervaci;
	private int sezonaOdMesic = 6;
	private int sezonaDoMesic = 9;
	
	//private Hotel hotel;

	/**
	 * Konstruktor vytváří jednotlivé seznamy (klienti, pokoje a vztahy mezi nimi).
	 */
	//public NovaRezervace(Integer id,Date datumZacatek, Date datumKonec, Pokoj pokoj, Klient klient) {
	public NovaRezervace(Integer id, LocalDate datumZacatek, LocalDate datumKonec, Pokoj pokoj, Klient klient) {
		this.datumZacatek = datumZacatek;
		this.datumKonec = datumKonec;
		this.pokoj = pokoj;
		this.klient = klient;
		this.id = id;
		//System.out.println(cenaZaRezervaci);
		//LocalDate d = datumZacatek;
		/*while (!d.isAfter(datumKonec.minusDays(1))) {
			if(d.getMonthValue() <= sezonaDoMesic && d.getMonthValue() >= sezonaOdMesic)
			{cenaZaRezervaci = cenaZaRezervaci + pokoj.getCenaSezona();}
			else {cenaZaRezervaci = cenaZaRezervaci + pokoj.getCena();}
			 d = d.plusDays(1);
			}
		System.out.println(cenaZaRezervaci);*/

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
	    public LocalDate getDatumKonec() {
	        return datumKonec;
	    }
	    
	    /**
	    * Setter pro nastavení data konce rezervace
	    * 
	    * @param Date datum konce rezervace
	    */
	    public void setDatumKonec(LocalDate datumKonec) {
	        this.datumKonec = datumKonec;
	    }
	    
	    /**
	    * Getter pro získání zacatku rezervace
	    * 
	    * @return vrací den zacatku
	    */
	    public LocalDate getDatumZacatek() {
	        return datumZacatek;
	    }
	    
	    /**
	    * Setter pro nastavení data zacatku rezervace
	    * 
	    * @param LocalDate zacatek
	    */
	    public void setDatumZacatek(LocalDate datumZacatek) {
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
	    * Getter pro získání ceny rezervace
	    * 
	    * @return cenaZaRezervaci vrací cenu za rezervaci
	    */
	    public Integer getCenaZaRezervaci() {
	        return cenaZaRezervaci;
	    }
	    
	    /**
	    * Kontroluje, zda je možné přidat novou rezervaci.
	    * Pokud je daný pokoj v požadovaném termínu již zarezervován, vrací false.
	    * Používá se při vytváření nových rezervací a úpravě stávajících.
	    * 
	    * @param LocalDate pozadavekOd - požadovaný začátek termínu nové rezervace
	    * @param LocalDate pozadavekDo - požadovaný konec termínu nové rezervace
	    * @param Pokoj pozadovanyPokoj - požadovaný pokoj pro novou rezervaci
	    * 
	    * @return volny vrací, zda je požadvaný pokoj v požadovaném termínu volný
	    */
	    public Boolean jeTerminVolny(LocalDate pozadavekOd, LocalDate pozadavekDo, Pokoj pozadovanyPokoj) {
	    	Boolean volny = false;
	    	//if () {}
	    	//else {
	    	//volny = true;
	    	//}
	        return volny;
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
	        String rezervaceSParametry = getIdRezervace()+", Od: "+getDatumZacatek().getDayOfMonth()+"."+getDatumZacatek().getMonthValue()+". "+getDatumZacatek().getYear()+", Do: "+getDatumKonec().getDayOfMonth()+"."+getDatumKonec().getMonthValue()+". "+getDatumKonec().getYear()+", Pokoj: "+getPokoj().getNazev()+", Klient: "+getKlient();
	    	return rezervaceSParametry;       
	    }
		
	    @Override
		public String toString() {
			return getRezervace();
		}

	

	

}
