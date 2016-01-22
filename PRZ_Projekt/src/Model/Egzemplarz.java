package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Egzemplarz {

	@Id
	@GeneratedValue
	private long id;
	private int rokWydania;
	
	@OneToOne
	@JoinColumn(name = "statusId")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "ksiazkaId")
	private Ksiazka ksiazka;
	
	@OneToOne
	@JoinColumn(name = "wydawnictwoId")
	private PublishingHouse publishingHouse;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Ksiazka getKsiazka() {
		return ksiazka;
	}

	public void setKsiazka(Ksiazka ksiazka) {
		this.ksiazka = ksiazka;
	}

	public int getRokWydania() {
		return rokWydania;
	}

	public void setRokWydania(int rokWydania) {
		this.rokWydania = rokWydania;
	}

	public PublishingHouse getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(PublishingHouse publishingHouse) {
		this.publishingHouse = publishingHouse;
	}
	
	
	

}
