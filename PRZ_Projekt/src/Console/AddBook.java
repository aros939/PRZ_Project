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

import Model.Autor;
import Model.Kategoria;
import Model.Ksiazka;

public class AddBook extends JFrame {

	private JLabel lTytul;
	private JLabel lISBN;
	private JLabel lKategoria;
	private JLabel lAutor;

	private JTextField tytul;
	private JTextField ISBN;
	private JComboBox<Kategoria> kategoria;
	private JComboBox<Autor> autor;
	private JPanel panel;
	private JButton dodaj;

	public AddBook() {
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);

		
		setTitle("Dodawanie ksi¹¿ek");
		panel = new JPanel();
		setSize(400, 212);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);

		lTytul = new JLabel("Tytu³");
		lTytul.setHorizontalAlignment(SwingConstants.RIGHT);
		lTytul.setBounds(32, 8, 87, 14);
		lISBN = new JLabel("ISBN");
		lISBN.setHorizontalAlignment(SwingConstants.RIGHT);
		lISBN.setBounds(32, 41, 87, 14);
		lKategoria = new JLabel("Kategoria");
		lKategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lKategoria.setBounds(32, 74, 87, 14);
		lAutor = new JLabel("Autor");
		lAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lAutor.setBounds(32, 108, 87, 14);

		tytul = new JTextField();
		tytul.setBounds(129, 5, 159, 20);
		ISBN = new JTextField();
		ISBN.setBounds(129, 38, 159, 20);

		kategoria = new JComboBox<Kategoria>();
		kategoria.setBounds(129, 71, 159, 20);
		kategoria.setModel(getKatModel());

		autor = new JComboBox<Autor>();
		autor.setBounds(129, 102, 159, 20);
		autor.setModel(getAutModel());

		dodaj = new JButton("Dodaj Ksia¿kê");
		dodaj.setBounds(129, 133, 159, 20);
		dodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				zapisz();
				JOptionPane.showMessageDialog(null, "Dodano Ksi¹¿kê!",
						"Dodano Ksia¿kê", JOptionPane.INFORMATION_MESSAGE);
				tytul.setText("");
				ISBN.setText("");

			}
		});

		panel.setLayout(null);

		panel.add(lTytul);
		panel.add(lISBN);
		panel.add(lKategoria);
		panel.add(lAutor);
		panel.add(tytul);
		panel.add(ISBN);
		panel.add(kategoria);
		panel.add(autor);
		panel.add(dodaj);

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public DefaultComboBoxModel<Kategoria> getKatModel() {
		EntityManager em = Database.getEntityManager();
		Query q = em.createQuery("select k from Kategoria k", Kategoria.class);
		DefaultComboBoxModel<Kategoria> model = new DefaultComboBoxModel<Kategoria>();
		List<Kategoria> kat = q.getResultList();
		for (Kategoria kategoria : kat) {
			model.addElement(kategoria);
		}

		return model;

	}

	public DefaultComboBoxModel<Autor> getAutModel() {
		EntityManager em = Database.getEntityManager();
		Query q = em.createQuery("select a from Autor a", Autor.class);
		DefaultComboBoxModel<Autor> model = new DefaultComboBoxModel<Autor>();
		List<Autor> kat = q.getResultList();
		for (Autor autor : kat) {
			model.addElement(autor);
		}

		return model;

	}

	public void zapisz() {
		EntityManager em = Database.getEntityManager();
		Ksiazka k = new Ksiazka();
		k.setTytul(tytul.getText());
		k.setIsbn(ISBN.getText());
		k.setKategoria((Kategoria) kategoria.getSelectedItem());
		k.setAutor((Autor) autor.getSelectedItem());

		em.getTransaction().begin();
		em.persist(k);
		em.getTransaction().commit();

	}

}
