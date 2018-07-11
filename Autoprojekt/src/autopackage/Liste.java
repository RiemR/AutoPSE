package autopackage;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@SessionScoped
public class Liste implements Serializable {

	private static final long serialVersionUID = 1L;

	private int preis;
	private int autoid;
	private String marke;
	private int erstzulassung;
	private String modell;
	private String stadt;
	private String kraftstoff;
	private String getriebe;
	private String karosserieform;
	private int kilometerstand;
	private int ps;

	public Liste() {
		as = new ArrayList<Auto>();
		idm = new DataModel(as);
	}

	/*--------------------------------------------------------------------------*/

	Auto aktuellesAuto = new Auto(0, 0, "Marke", 0, "Modell", "Stadt", "Kraftstoff", "Getriebe", "Karosserieform", 0,
			0);

	public void setAktuellesAuto(Auto aktuellesAuto) {
		this.aktuellesAuto = aktuellesAuto;
	}

	/**
	 * Die Methode gibt das aktuelle Auto zurÃ¼ck, das dann auf der zweiten Seite
	 * separat angezeigt wird mit den einzelnen Daten zum Auto.
	 * 
	 * @return
	 */
	public Auto getAktuellesAuto() {

		for (int i = 0; i < as.size(); i++) {
			System.out.println(as.get(i).toString());
			if (autoid == as.get(i).getAutoid()) {
				aktuellesAuto = as.get(i);

				break;
			}

		}
		return aktuellesAuto;
	}

	ArrayList<Auto> as = new ArrayList<Auto>();
	private DataModel idm = null;

	/*--------------------------------------------------------------------------*/
	/**
	 * Die Methode search gibt je nach Angabe der Informationen, das passende Auto
	 * zurÃ¼ck aus der Datenbank.
	 * 
	 * @param ae
	 */
	public void search(ActionEvent ae) {
		as.clear();

		final String sql = "Select * from AutoDatenbank where Preis <=? and Marke=?  and Erstzulassung>=? and Modell=? and Stadt=? and Kraftstoff=? and Getriebe=? and Karosserieform=? and Kilometerstand<=? and PS>=?";
		Util util = new Util();
		Connection con = util.getCon();
		try {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, preis);
			pst.setString(2, marke);
			pst.setInt(3, erstzulassung);
			pst.setString(4, modell);
			pst.setString(5, stadt);
			pst.setString(6, kraftstoff);
			pst.setString(7, getriebe);
			pst.setString(8, karosserieform);
			pst.setInt(9, kilometerstand);
			pst.setInt(10, ps);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Auto i = new Auto(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
						rs.getInt(11));
				as.add(i);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void einfügen(ActionEvent ae) {
		as.clear();

		final String sql = "Insert into AutoDatenbank (Preis, Marke,Erstzulassung, Modell, Stadt, Kraftstoff, Getriebe, Karosserieform, Kilometerstand,PS) VALUES (?,?,?,?,?,?,?,?,?,?)";

		Util util = new Util();
		Connection con = util.getCon();
		try {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, preis);
			pst.setString(2, marke);
			pst.setInt(3, erstzulassung);
			pst.setString(4, modell);
			pst.setString(5, stadt);
			pst.setString(6, kraftstoff);
			pst.setString(7, getriebe);
			pst.setString(8, karosserieform);
			pst.setInt(9, kilometerstand);
			pst.setInt(10, ps);

			pst.executeUpdate();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loeschen(ActionEvent ae) {
		long userID;
		String sql = "DELETE FROM AutoDatenbank WHERE AutoId=" + userID;
		if (util != null)
			util.log("delete()...");
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Datensatz nicht entfernt!", "Nicht möglich"));
	}

	public void delete(long userID) {
		as.clear();
		String sql = "DELETE FROM AutoDatenbank WHERE AutoId=" + userID;
		Util util = new Util();

		Connection con = util.getCon();

		try {
			PreparedStatement pst = con.prepareStatement(sql);

			pst = con.prepareStatement(sql);

			pst.executeUpdate();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/*--------------------------------------------------------------------------*/
	Util util = new Util();
	private StreamedContent streamedPic = null;
	final String PIC_TEXT_JA = "Das Bild";
	final String PIC_TEXT_NEIN = "";
	private String picText = PIC_TEXT_NEIN;
	final String CLASSNAME = getClass().getName();

	public String getPicText() {
		return picText;
	}

	/**
	 * Die Methode getStreamedPic() holt das Bild aus der Datenbank und gibt es
	 * zurÃ¼ck.
	 * 
	 * @return
	 */
	public StreamedContent getStreamedPic() {
		getPic();
		return streamedPic;
	}

	public void getPic() {
		System.out.println(CLASSNAME + ".getPic()...");
		String typ = "?";

		Connection con = util.getCon();
		if (con != null) {

			try {
				PreparedStatement pst = con.prepareStatement("SELECT Foto FROM AutoDatenbank WHERE AutoId=?");
				pst.setInt(1, autoid);
				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					InputStream is = rs.getBinaryStream(1);
					streamedPic = new DefaultStreamedContent(is, typ);
					is.close();
					picText = PIC_TEXT_JA;
				} else
					System.err.println("Leeres ResultSet");

				rs.close();
				pst.close();
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else
			System.err.println("Connection null");

	}

	public String foto() {
		return "foto.xhtml";
	}

	public DataModel getAutos() {
		if (as != null) {
			System.out.println(as.size());
		} else
			System.out.println("as ist null");

		return idm;
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

	public void setErstzulassung(int erstzulassung) {
		this.erstzulassung = erstzulassung;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
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
