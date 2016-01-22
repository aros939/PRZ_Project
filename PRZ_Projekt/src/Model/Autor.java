package Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Autor {
	
	@Id
	@GeneratedValue
	private long id;
	private String imie;
	private String nazwisko;
	
	@OneToMany(mappedBy = "autor")
	private List<Ksiazka> ksiazki;// = new ArrayList<Ksiazka>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}


	public List<Ksiazka> getKsiazki() {
		return ksiazki;
	}

	public void setKsiazki(List<Ksiazka> ksiazki) {
		this.ksiazki = ksiazki;
	}

	@Override
	public String toString() {
		return imie+" "+nazwisko;
	}
	
	
	
	

}
