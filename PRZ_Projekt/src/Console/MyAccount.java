package Console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Uzytkownik;
import Model.UzytkownikDane;

public class MyAccount extends JPanel {

	private JLabel nazwa;
	private JLabel imie;
	private JLabel nazwisko;
	private JLabel nrTel;
	private JLabel email;
	private JTextField tnazwa;
	private JTextField timie;
	private JTextField tnazwisko;
	private JTextField tnrTel;
	private JTextField temail;
	private JButton zmienHaslo;
	private JButton zapisz;

	public MyAccount() {
		ImageIcon saveImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\save.png");
		ImageIcon passwordImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\password.png");

		

		nazwa = new JLabel("Nazwa");
		nazwa.setBounds(46, 34, 56, 14);
		tnazwa = new JTextField();
		tnazwa.setLocation(100, 34);
		tnazwa.setSize(191, 20);
		tnazwa.setColumns(15);
		tnazwa.setEditable(false);
		imie = new JLabel("Imie");
		imie.setBounds(58, 78, 44, 14);
		timie = new JTextField();
		timie.setBounds(100, 79, 191, 20);
		timie.setColumns(15);
		nazwisko = new JLabel("Nazwisko");
		nazwisko.setBounds(34, 122, 68, 14);
		tnazwisko = new JTextField();
		tnazwisko.setBounds(100, 124, 191, 20);
		tnazwisko.setColumns(15);
		nrTel = new JLabel("Nr telefonu");
		nrTel.setBounds(22, 166, 80, 14);
		tnrTel = new JTextField();
		tnrTel.setBounds(100, 169, 191, 20);
		tnrTel.setColumns(15);
		email = new JLabel("E-mail");
		email.setBounds(50, 210, 52, 14);
		temail = new JTextField();
		temail.setBounds(100, 214, 191, 20);
		temail.setColumns(15);
		zapisz = new JButton("Zapisz",saveImage);
		zapisz.setBounds(301, 34, 185, 73);
		zmienHaslo = new JButton("Zmieñ Has³o",passwordImage);
		zmienHaslo.setBounds(301, 160, 185, 73);

		setLayout(null);

		add(nazwa);
		add(tnazwa);
		add(imie);
		add(timie);
		add(nazwisko);
		add(tnazwisko);
		add(nrTel);
		add(tnrTel);
		add(email);
		add(temail);
		add(zmienHaslo);
		add(zapisz);
		setValues();
		System.out.println(Database.getLoggedUserId());
		zmienHaslo.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new EditPassword(Database.getLoggedUserId());
			System.out.println(Database.getLoggedUserId());
		}
	});
	

		
		
		zapisz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zapisz();
				//System.out.println(tnrTel.getText().replaceAll("[^0-9]", ""));
				JOptionPane.showMessageDialog(null, "Zmieniono Dane!",
						"Zmieniono Dane", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});

	}

	private void setValues() {
		EntityManager em = Database.getEntityManager();

		Uzytkownik uz = em.find(Uzytkownik.class, Database.getLoggedUserId());

		setName(uz.getNazwa());
		setTimie(uz.getUzytkownikDane().getImie());
		setTnazwa(uz.getNazwa());
		setTnazwisko(uz.getUzytkownikDane().getNazwisko());
		setTnrTel(uz.getUzytkownikDane().getNrTel());
		setTemail(uz.getUzytkownikDane().getEmail());

	}

	private void zapisz() {

		EntityManager em = Database.getEntityManager();
		Uzytkownik uz = em.find(Uzytkownik.class, Database.getLoggedUserId());
		UzytkownikDane ud = uz.getUzytkownikDane();
		em.getTransaction().begin();
		uz.getUzytkownikDane().setImie(getTimie());
		uz.getUzytkownikDane().setNazwisko(getTnazwisko());
		uz.getUzytkownikDane().setNrTel(getTnrTel());
		uz.getUzytkownikDane().setEmail(getTemail());
		em.getTransaction().commit();

	}

	public String getTnazwa() {
		return tnazwa.getText();
	}

	public void setTnazwa(String nazwa) {
		this.tnazwa.setText(nazwa);
	}

	public String getTimie() {
		return timie.getText();
	}

	public void setTimie(String imie) {
		this.timie.setText(imie);
	}

	public String getTnazwisko() {
		return tnazwisko.getText();
	}

	public void setTnazwisko(String nazwisko) {
		this.tnazwisko.setText(nazwisko);
		;
	}

	public String getTnrTel() {
		return tnrTel.getText();
	}

	public void setTnrTel(String nrTel) {
		this.tnrTel.setText(nrTel);
	}

	public String getTemail() {
		return temail.getText();
	}

	public void setTemail(String email) {
		this.temail.setText(email);
	}

}
