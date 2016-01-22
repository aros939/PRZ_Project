package Console;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Model.Autor;
import Model.Kategoria;
import Model.Role;
import Model.Uzytkownik;
import Model.UzytkownikDane;

import javax.swing.SwingConstants;

public class AddUser extends JPanel {

	private JLabel nazwa;
	private JLabel imie;
	private JLabel nazwisko;
	private JLabel nrTel;
	private JLabel email;
	private JLabel haslo;
	private JTextField tnazwa;
	private JTextField timie;
	private JTextField tnazwisko;
	private JTextField tnrTel;
	private JTextField temail;
	private JPasswordField thaslo;
	private JButton zapisz;
	private JComboBox<Role> rola;

	public AddUser() {

		ImageIcon saveImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\save.png");


		nazwa = new JLabel("Nazwa");
		nazwa.setBounds(46, 34, 56, 14);
		tnazwa = new JTextField();
		tnazwa.setLocation(100, 34);
		tnazwa.setSize(191, 20);
		tnazwa.setColumns(15);
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
		haslo = new JLabel("Has³o");
		haslo.setBounds(50, 254, 52, 14);
		thaslo = new JPasswordField();
		thaslo.setBounds(100, 259, 191, 20);
		thaslo.setColumns(15);
		zapisz = new JButton("Zapisz",saveImage);
		zapisz.setBounds(301, 34, 185, 73);
		setLayout(null);
		
		rola = new JComboBox<Role>();
		rola.setBounds(100, 293, 191, 20);
		rola.setModel(getRoleModel());
		

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
		add(haslo);
		add(thaslo);
		add(zapisz);
		add(rola);
		
		JLabel lRola = new JLabel("rola");
		lRola.setHorizontalAlignment(SwingConstants.RIGHT);
		lRola.setBounds(41, 293, 35, 14);
		add(lRola);
		
		
		zapisz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zapisz();
				JOptionPane.showMessageDialog(null, "Dodano u¿ytkownika!",
						"Zmieniono Dane", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});

	}

	private void zapisz() {

		EntityManager em = Database.getEntityManager();
		Uzytkownik uz = new Uzytkownik();
		UzytkownikDane ud = new UzytkownikDane();
		
		ud.setEmail(getTemail());
		ud.setImie(getTimie());
		uz.setNazwa(getTnazwa());
		ud.setNazwisko(getTnazwisko());
		ud.setNrTel(getTnrTel());
		uz.setHaslo(getThaslo());
		uz.setRola((Role)rola.getSelectedItem());
		uz.setUzytkownikDane(ud);
		
		em.getTransaction().begin();
		em.persist(uz);
		em.persist(ud);
		em.getTransaction().commit();

	}
	
	public DefaultComboBoxModel<Role> getRoleModel() {
		EntityManager em = Database.getEntityManager();
		Query q = em.createQuery("select r from Role r", Role.class);
		DefaultComboBoxModel<Role> model = new DefaultComboBoxModel<Role>();
		List<Role> rol = q.getResultList();
		for (Role role : rol) {
			model.addElement(role);
		}

		return model;

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

	public String getThaslo() {
		return (new String(thaslo.getPassword()));
	}

	public void setThaslo(String email) {
		this.thaslo.setText(email);
	}
}
