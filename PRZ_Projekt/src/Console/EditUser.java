package Console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Role;
import Model.Uzytkownik;
import Model.UzytkownikDane;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class EditUser extends JPanel {

	private JLabel nazwa;
	private JLabel imie;
	private JLabel nazwisko;
	private JLabel nrTel;
	private JLabel email;
	private JLabel rola;
	private JComboBox<Uzytkownik> tnazwa;
	private JTextField timie;
	private JTextField tnazwisko;
	private JTextField tnrTel;
	private JTextField temail;
	private JButton zapisz;
	private JButton zmienHaslo;
	private JComboBox<Role> tRole;

	public EditUser() {

		ImageIcon saveImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\save.png");
		ImageIcon passwordImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\password.png");

		nazwa = new JLabel("Nazwa");
		nazwa.setHorizontalAlignment(SwingConstants.RIGHT);
		nazwa.setBounds(34, 28, 56, 14);
		imie = new JLabel("Imie");
		imie.setHorizontalAlignment(SwingConstants.RIGHT);
		imie.setBounds(50, 73, 40, 14);
		timie = new JTextField();
		timie.setBounds(100, 70, 191, 20);
		timie.setColumns(15);
		nazwisko = new JLabel("Nazwisko");
		nazwisko.setHorizontalAlignment(SwingConstants.RIGHT);
		nazwisko.setBounds(34, 118, 56, 14);
		tnazwisko = new JTextField();
		tnazwisko.setBounds(100, 115, 191, 20);
		tnazwisko.setColumns(15);
		nrTel = new JLabel("Nr Telefonu");
		nrTel.setHorizontalAlignment(SwingConstants.RIGHT);
		nrTel.setBounds(22, 163, 68, 14);
		tnrTel = new JTextField();
		tnrTel.setBounds(100, 160, 191, 20);
		tnrTel.setColumns(15);
		email = new JLabel("e-mail");
		email.setHorizontalAlignment(SwingConstants.RIGHT);
		email.setBounds(50, 210, 40, 14);
		temail = new JTextField();
		temail.setBounds(100, 205, 191, 20);
		temail.setColumns(15);
		zapisz = new JButton("Zapisz",saveImage);
		zapisz.setBounds(301, 25, 185, 73);
		zmienHaslo = new JButton("Zmieñ Has³o",passwordImage);
		zmienHaslo.setBounds(301, 115, 185, 73);
		
		tnazwa = new JComboBox<Uzytkownik>();
		tnazwa.setBounds(100, 25, 191, 20);
		tnazwa.setModel(getUzModel());
		
		tRole = new JComboBox<Role>();
		tRole.setBounds(100, 250, 191, 20);
		tRole.setModel(getRoleModel());
		
		rola = new JLabel("Rola");
		rola.setHorizontalAlignment(SwingConstants.RIGHT);
		rola.setBounds(50, 250, 40, 20);

		setLayout(null);

		add(nazwa);
		add(imie);
		add(timie);
		add(nazwisko);
		add(tnazwisko);
		add(nrTel);
		add(tnrTel);
		add(email);
		add(temail);
		add(zapisz);
		add(zmienHaslo);
		add(tnazwa);
		add(tRole);
		add(rola);
		
		zmienHaslo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Uzytkownik u = (Uzytkownik) tnazwa.getSelectedItem();
				new EditPassword(u.getId());
			}
		});
		
		
		tnazwa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Uzytkownik uz = (Uzytkownik) tnazwa.getSelectedItem();
				
				setName(uz.getNazwa());
				setTimie(uz.getUzytkownikDane().getImie());
				setTnazwisko(uz.getUzytkownikDane().getNazwisko());
				setTnrTel(uz.getUzytkownikDane().getNrTel());
				setTemail(uz.getUzytkownikDane().getEmail());
				
			}
		});
		
		
		zapisz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zapisz();
				JOptionPane.showMessageDialog(null, "Edytowano u¿ytkownika!",
						"Zmieniono Dane", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});

	}

	private void zapisz() {

		EntityManager em = Database.getEntityManager();
		Uzytkownik uz = (Uzytkownik) tnazwa.getSelectedItem();
		UzytkownikDane ud = uz.getUzytkownikDane();
		
		em.getTransaction().begin();
		ud.setEmail(getTemail());
		ud.setImie(getTimie());
		ud.setNazwisko(getTnazwisko());
		ud.setNrTel(getTnrTel());
		em.getTransaction().commit();

	}
	
	public DefaultComboBoxModel<Uzytkownik> getUzModel() {
		EntityManager em = Database.getEntityManager();
		Query q = em.createQuery("select k from Uzytkownik k", Uzytkownik.class);
		DefaultComboBoxModel<Uzytkownik> model = new DefaultComboBoxModel<Uzytkownik>();
		List<Uzytkownik> uz = q.getResultList();
		for (Uzytkownik uzytkownik : uz) {
			model.addElement(uzytkownik);
		}

		return model;

	}
	
	public DefaultComboBoxModel<Role> getRoleModel() {
		EntityManager em = Database.getEntityManager();
		Query q = em.createQuery("select r from Role r", Role.class);
		DefaultComboBoxModel<Role> model = new DefaultComboBoxModel<Role>();
		List<Role> rl = q.getResultList();
		for (Role role : rl) {
			model.addElement(role);
		}

		return model;

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
