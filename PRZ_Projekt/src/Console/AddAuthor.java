package Console;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Autor;

public class AddAuthor extends JFrame {

	private JLabel lImie;
	private JLabel lNazwisko;
	private JTextField imie;
	private JTextField nazwisko;
	private JPanel panel;
	private JButton dodaj;

	public AddAuthor() {
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);

		
		setTitle("Dodawanie autorów");

		panel = new JPanel();
		setSize(400, 135);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);

		lImie = new JLabel("Imie");
		lImie.setHorizontalAlignment(SwingConstants.RIGHT);
		lImie.setBounds(32, 8, 87, 14);
		lNazwisko = new JLabel("Nazwisko");
		lNazwisko.setHorizontalAlignment(SwingConstants.RIGHT);
		lNazwisko.setBounds(32, 41, 87, 14);

		imie = new JTextField();
		imie.setBounds(129, 5, 159, 20);
		nazwisko = new JTextField();
		nazwisko.setBounds(129, 38, 159, 20);

		dodaj = new JButton("Dodaj Autora");
		dodaj.setBounds(129, 69, 159, 20);

		dodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dodaj();
				JOptionPane.showMessageDialog(null, "Dodano Autora!",
						"Dodano Autora", JOptionPane.INFORMATION_MESSAGE);
				imie.setText("");
				nazwisko.setText("");

			}
		});

		panel.setLayout(null);

		panel.add(lImie);
		panel.add(lNazwisko);
		panel.add(imie);
		panel.add(nazwisko);
		panel.add(dodaj);

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public void dodaj() {
		EntityManager em = Database.getEntityManager();

		Autor a = new Autor();
		a.setImie(imie.getText());
		a.setNazwisko(nazwisko.getText());

		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
	}

}
