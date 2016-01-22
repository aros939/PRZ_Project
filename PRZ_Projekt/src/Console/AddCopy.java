package Console;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Egzemplarz;
import Model.Ksiazka;
import Model.PublishingHouse;
import Model.Status;

public class AddCopy extends JFrame {

	private JPanel panel;
	private JLabel lKsiazka;
	private JLabel lStatus;
	private JLabel lRokWydania;
	private JComboBox<Ksiazka> ksiazka;
	private JComboBox<Status> status;
	private JComboBox<PublishingHouse> publishingHouse;
	private JTextField rokWydania;
	private JButton dodaj;
	private JLabel lPublishingHouse;

	public AddCopy() {
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);

		
		setTitle("Dodawanie egzemplarza");

		panel = new JPanel();
		setSize(400, 214);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);
		
		lKsiazka = new JLabel("Ksi¹¿ka");
		lKsiazka.setHorizontalAlignment(SwingConstants.RIGHT);
		lKsiazka.setBounds(10, 11, 84, 14);
		lStatus = new JLabel("Status");
		lStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lStatus.setBounds(10, 49, 84, 14);
		lRokWydania = new JLabel("Rok Wydania");
		lRokWydania.setHorizontalAlignment(SwingConstants.RIGHT);
		lRokWydania.setBounds(10, 83, 84, 14);
	
		
		rokWydania = new JTextField();
		rokWydania.setBounds(104, 80, 217, 20);

		ksiazka = new JComboBox<Ksiazka>();
		ksiazka.setBounds(104, 8, 217, 20);
		ksiazka.setModel(getKsModel());
		
		publishingHouse = new JComboBox<PublishingHouse>();
		publishingHouse.setBounds(104, 111, 217, 20);
		publishingHouse.setModel(getPhModel());

		status = new JComboBox<Status>();
		status.setBounds(104, 46, 217, 20);
		status.setModel(getStModel());
		panel.setLayout(null);
		
		dodaj = new JButton("Dodaj");
		dodaj.setBounds(104, 142, 217, 20);
		

		dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dodaj();
				JOptionPane.showMessageDialog(null, "Dodano Egzemplarz!",
						"Dodano Egzemplarz", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		
		panel.add(lKsiazka);
		panel.add(lStatus);
		panel.add(lRokWydania);
		panel.add(ksiazka);
		panel.add(status);
		panel.add(rokWydania);
		panel.add(dodaj);
		panel.add(publishingHouse);
		
		lPublishingHouse = new JLabel("Wydawnictwo");
		lPublishingHouse.setHorizontalAlignment(SwingConstants.RIGHT);
		lPublishingHouse.setBounds(10, 114, 84, 14);
		panel.add(lPublishingHouse);
		

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

	}
	
	public DefaultComboBoxModel<Ksiazka> getKsModel() {
		EntityManager em = Database.getEntityManager();
		Query q = em.createQuery("select k from Ksiazka k", Ksiazka.class);
		DefaultComboBoxModel<Ksiazka> model = new DefaultComboBoxModel<Ksiazka>();
		List<Ksiazka> ks = q.getResultList();
		for (Ksiazka ksiazka : ks) {
			model.addElement(ksiazka);
		}

		return model;

	}
	
	public DefaultComboBoxModel<Status> getStModel() {
		EntityManager em = Database.getEntityManager();
		Query q = em.createQuery("select s from Status s", Status.class);
		DefaultComboBoxModel<Status> model = new DefaultComboBoxModel<Status>();
		List<Status> ks = q.getResultList();
		for (Status status : ks) {
			model.addElement(status);
		}
		
		return model;
	}
	
	public void dodaj(){
		EntityManager em = Database.getEntityManager();
		Egzemplarz egz = new Egzemplarz();
		egz.setKsiazka((Ksiazka)ksiazka.getSelectedItem());
		egz.setStatus((Status)status.getSelectedItem());
		egz.setPublishingHouse((PublishingHouse)publishingHouse.getSelectedItem());
		egz.setRokWydania(Integer.parseInt(rokWydania.getText()));
		
		em.getTransaction().begin();
		em.persist(egz);
		em.getTransaction().commit();
		
	}
	
	public DefaultComboBoxModel<PublishingHouse> getPhModel() {
		EntityManager em = Database.getEntityManager();
		Query q = em.createQuery("select p from PublishingHouse p", PublishingHouse.class);
		DefaultComboBoxModel<PublishingHouse> model = new DefaultComboBoxModel<PublishingHouse>();
		List<PublishingHouse> ks = q.getResultList();
		for (PublishingHouse publishingHouse : ks) {
			model.addElement(publishingHouse);
		}
		
		return model;
	}
	
	
	
	
	
	
	
}
