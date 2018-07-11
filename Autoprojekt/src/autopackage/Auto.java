package autopackage;

import com.mysql.jdbc.Blob;


public class Auto {
	
	private int preis = 0;
	private int autoid = 0;
	private String marke = "Marke";
	private int erstzulassung = 0;
	private String modell = "Modell";
	private String stadt = "Stadt";
	private String kraftstoff = "Kraftstoff";
	private String getriebe = "Getriebe";
	private String karosserieform = "Karosserieform";
	private int kilometerstand = 0;
	private int ps = 0;
	private java.sql.Blob foto;

	
	public Auto(int autoid, int preis, String marke, int erstzulassung, String modell, String stadt, String kraftstoff,
			String getriebe, String karosserieform, int kilometerstand, int ps) {
		super();
		this.autoid = autoid;
		this.preis = preis;
		this.marke = marke;
		this.erstzulassung = erstzulassung;
		this.modell = modell;
		this.stadt = stadt;
		this.kraftstoff = kraftstoff;
		this.getriebe = getriebe;
		this.karosserieform = karosserieform;
		this.kilometerstand = kilometerstand;
		this.ps = ps;
	}
 
	public Auto() {
	}

	public java.sql.Blob getFoto() {
		return foto;
	}

	public void setFoto(Blob foto) {
		this.foto = foto;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}

	public int getAutoid() {
		return autoid;
	}

	public void setAutoid(int autoid) {
		this.autoid = autoid;
	}

	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	public int getErstzulassung() {
		return erstzulassung;
	}

	public void setErstezulassung(int erstzulassung) {
		this.erstzulassung = erstzulassung;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String model) {
		this.modell = model;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getKraftstoff() {
		return kraftstoff;
	}

	public void setKraftstoff(String kraftstoff) {
		this.kraftstoff = kraftstoff;
	}

	public String getGetriebe() {
		return getriebe;
	}

	public void setGetriebe(String getriebe) {
		this.getriebe = getriebe;
	}

	public String getKarosserieform() {
		return karosserieform;
	}

	public void setKarosserieform(String karosserieform) {
		this.karosserieform = karosserieform;
	}

	public int getKilometerstand() {
		return kilometerstand;
	}

	public void setKilometerstand(int kilometerstand) {
		this.kilometerstand = kilometerstand;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

}
