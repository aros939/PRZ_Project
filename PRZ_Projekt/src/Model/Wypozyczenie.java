package Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Wypozyczenie {
	
	@Id
	@GeneratedValue
	private long id;
	
	
	@OneToOne
	@JoinColumn(name = "egzemplarzId")
	private Egzemplarz egzemplarz;
	
	@ManyToOne
	@JoinColumn(name = "uzytkownikId")
	private Uzytkownik uzytkownik;
	
	@Temporal(TemporalType.DATE)
	private Date dataRez;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Egzemplarz getEgzemplarz() {
		return egzemplarz;
	}

	public void setEgzemplarz(Egzemplarz egzemplarz) {
		this.egzemplarz = egzemplarz;
	}

	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	public Date getDataWyp() {
		return dataRez;
	}

	public void setDataWyp(Date dataWyp) {
		this.dataRez = dataWyp;
	}
	
	
	
	

}
