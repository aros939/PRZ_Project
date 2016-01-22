package Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Ksiazka {
	
	@Id
	@GeneratedValue
	private long id;
	private String tytul;
	private String isbn;
	
	@OneToOne
	@JoinColumn(name = "kategoriaId")
	private Kategoria kategoria;
	
	@ManyToOne()
	@JoinColumn(name = "autorId")
	private Autor autor;
	
	@OneToMany(mappedBy = "ksiazka")
	private List<Egzemplarz> egzemplarze;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytu³) {
		this.tytul = tytu³;
	}


	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Kategoria getKategoria() {
		return kategoria;
	}

	public void setKategoria(Kategoria kategoria) {
		this.kategoria = kategoria;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Egzemplarz> getEgzemplarze() {
		return egzemplarze;
	}

	public void setEgzemplarze(List<Egzemplarz> egzemplarze) {
		this.egzemplarze = egzemplarze;
	}

	@Override
	public String toString() {
		return tytul;
	}
	
	
	
	
	
	

}
