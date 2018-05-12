package com.github.pehovorka.rezervaceHotel.logika;

public class Klient {
	private String jmeno;
	private String prijmeni;
	private int cisloOP;
	
    public Klient(String jmeno, String prijmeni, Integer cisloOP )
    {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.cisloOP = cisloOP;
    }
    
    public String getJmeno(){
        return jmeno;
    }
    
    public String getPrijmeni(){
        return prijmeni;
    }
    
    public int getCisloOP(){
        return cisloOP;
    }
    
	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}
	
	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}
	
	public void setCisloOP(Integer cisloOP) {
		this.cisloOP = cisloOP;
	}

}
