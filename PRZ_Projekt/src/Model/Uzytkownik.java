package Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Uzytkownik {

	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String nazwa;
	private String haslo;
	
	@OneToOne
	@JoinColumn(name = "daneId")
	private UzytkownikDane uzytkownikDane;

	
	@OneToOne
	@JoinColumn(name = "rolaId")
	private Role rola;

	@OneToMany(mappedBy = "uzytkownik")
	private List<Wypozyczenie> wypozyczenia;

	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNazwa() {
		return nazwa;
	}



	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}



	public String getHaslo() {
		return haslo;
	}



	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}



	public UzytkownikDane getUzytkownikDane() {
		return uzytkownikDane;
	}



	public void setUzytkownikDane(UzytkownikDane uzytkownikDane) {
		this.uzytkownikDane = uzytkownikDane;
	}



	public Role getRola() {
		return rola;
	}



	public void setRola(Role rola) {
		this.rola = rola;
	}



	public List<Wypozyczenie> getWypozyczenia() {
		return wypozyczenia;
	}



	public void setWypozyczenia(List<Wypozyczenie> wypozyczenia) {
		this.wypozyczenia = wypozyczenia;
	}



	@Override
	public String toString() {
		return nazwa;
	}

	

}
