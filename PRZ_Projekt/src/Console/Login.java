package Console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Uzytkownik;

public class Login extends JFrame {

	private JLabel lLogin;
	private JLabel lHaslo;
	private JTextField login;
	private JPasswordField haslo;
	private JPanel panel;
	private JButton zaloguj;
	private List<Uzytkownik> uzytkownicy;

	public Login() {
		
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);


		setTitle("Logowanie");
		setSize(299, 155);

		panel = new JPanel();

		lLogin = new JLabel("Login");
		lLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lLogin.setBounds(23, 14, 40, 14);
		lHaslo = new JLabel("Has這");
		lHaslo.setHorizontalAlignment(SwingConstants.RIGHT);
		lHaslo.setBounds(21, 54, 42, 14);

		login = new JTextField();
		login.setBounds(73, 11, 159, 20);

		haslo = new JPasswordField();
		haslo.setBounds(73, 51, 159, 20);

		zaloguj = new JButton("Zaloguj");
		zaloguj.setBounds(73, 82, 159, 20);

		panel.setLayout(null);
		uzytkownicy = getUzytkownicy();

		zaloguj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				zaloguj();
			}
		});

		panel.add(lLogin);
		panel.add(lHaslo);
		panel.add(login);
		panel.add(haslo);
		panel.add(zaloguj);

		getContentPane().add(panel);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private List<Uzytkownik> getUzytkownicy() {

		EntityManager em = Database.getEntityManager();
		Query q = em
				.createQuery("select u from Uzytkownik u", Uzytkownik.class);
		List<Uzytkownik> uz = q.getResultList();
		return uz;

	}

	public void zaloguj() {
		try {
			EntityManager em = Database.getEntityManager();

			Uzytkownik u = new Uzytkownik();
			Query q = em.createQuery(
					"select u from Uzytkownik u where u.nazwa=:nazwa ",
					Uzytkownik.class);
			q.setParameter("nazwa", login.getText());
			u = (Uzytkownik) q.getSingleResult();
			
			String h = u.getHaslo();
			String h1 = new String(haslo.getPassword());
			

			if (h.equals(h1) == true) {
				Database.setLoggedUserId(u.getId());
				new MainWindow(u.getRola().getId());
				dispose();
			}
			else{
				JOptionPane.showMessageDialog(null, "Nieprawid這we has這!",
						"Nieprawid這we has這!", JOptionPane.ERROR_MESSAGE);
			}

		} catch (NoResultException e) {
			JOptionPane.showMessageDialog(null, "Nieprawid這wy login!",
					"Nieprawid這wy login", JOptionPane.ERROR_MESSAGE);
		}

	}

}
