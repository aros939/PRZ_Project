package Console;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Model.Autor;
import Model.Egzemplarz;
import Model.Kategoria;
import Model.Ksiazka;
import Model.Status;
import Model.Uzytkownik;
import Model.Wypozyczenie;

public class MainTest {
	
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		
		Status s1 = new Status();
		s1.setNazwa("dostepna");
		Status s2 = new Status();
		s2.setNazwa("zarezerwowana");
		Status s3 = new Status();
		s3.setNazwa("wypozyczona");
		
		
		
		//Punkt 1
		Autor a = new Autor();
		a.setImie("Jan");
		a.setNazwisko("Kowalski");
		Kategoria kat = new Kategoria();
		kat.setNazwa("Biografie");
		Kategoria kat1 = new Kategoria();
		kat1.setNazwa("Sportowe");
		
		//Punkt2
		Ksiazka k = new Ksiazka();
		k.setTytul("Ksiazka 1");
		k.setIsbn("235dg34df");
		k.setKategoria(kat);
		k.setAutor(a);
		Ksiazka k1 = new Ksiazka();
		k1.setTytul("Spalony");
		k1.setIsbn("1234-21423f3-22");
		k1.setAutor(a);
		k1.setKategoria(kat1);
		
		
		Egzemplarz egz = new Egzemplarz();
		egz.setStatus(s1);
		egz.setKsiazka(k1);
		egz.setRokWydania(2012);
		Egzemplarz egz1 = new Egzemplarz();
		egz1.setStatus(s1);
		egz1.setKsiazka(k1);
		egz1.setRokWydania(2012);
		
		
		
		/*Uzytkownik u1 = new Uzytkownik();
		u1.setNazwa("aros");
		u1.setImie("Arkadiusz");
		u1.setNazwisko("Pszczola");
		u1.setHaslo("123456");*/
		
		Date data = new Date();
		Wypozyczenie wyp = new Wypozyczenie();
		wyp.setDataWyp(data);
		System.out.println(data);
		
		
		
		
		//Punkt 4
		em.getTransaction().begin();
		//em.persist(u1);
		em.persist(kat);
		em.persist(k);
		em.persist(kat1);
		em.persist(k1);
		em.persist(a);
		em.persist(egz);
		em.persist(egz1);
		em.persist(wyp);
		em.persist(s1);
		em.persist(s2);
		em.persist(s3);
		em.getTransaction().commit();
		/*
		Autor aut = em.find(Autor.class, 1L);
		
		System.out.println(aut.getNazwisko()+"\n");
		List<Ksiazka> lista = aut.getKsiazki();
		System.out.println(lista.size() +"a");
		for (Ksiazka ksiazka : lista)
			System.out.println(ksiazka.getTytul());
		
		
		System.out.println("1-------------------");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ksiazka> criteriaQuery = cb.createQuery(Ksiazka.class);
		Root<Ksiazka> rks = criteriaQuery.from(Ksiazka.class);
		
		criteriaQuery.select(rks).where(cb.equal(rks.get("tytul"), "Kowal"));
		TypedQuery<Ksiazka> tqks = em.createQuery(criteriaQuery);
		List<Ksiazka> l = tqks.getResultList();
		for (Ksiazka ksiazka : l) {
			System.out.println(ksiazka.getTytul());
			
		}
		System.out.println("2-------------------");
		
		
		Query q = em.createQuery("select k from Ksiazka k where k.kategoria.nazwa=:kat",Ksiazka.class);
		q.setParameter("kat","Biografie");
		List<Ksiazka> kkk = q.getResultList();
		for (Ksiazka ksiazka : kkk) {
			System.out.println(ksiazka.getTytul());
		}
		
		System.out.println("3-------------------");
		
		Ksiazka kk = em.find(Ksiazka.class, 11L);
		//em.refresh(kk);
		List<Egzemplarz> listEgz = kk.getEgzemplarze();
		for (Egzemplarz egzemplarz : listEgz) {
			System.out.println(egzemplarz.getId() + " " + egzemplarz.getStatus());
		}
		
		
		*/
		
		em.close();
		entityManagerFactory.close();
		
	}
	
}
