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
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Autor;
import Model.Kategoria;
import Model.Ksiazka;
import Model.Uzytkownik;

public class EditPassword extends JFrame {

	private JLabel OldPassword;
	private JLabel NewPassword1;
	private JLabel NewPassword2;

	private JPasswordField OldPasswordField;
	private JPasswordField NewPassword1Field;
	private JPasswordField NewPassword2Field;
	private JPanel panel;
	private JButton save;
	private long id;
	
	public EditPassword(long id) {
		this.id =id;
		
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);

		
		setTitle("Edycja has³a");
		panel = new JPanel();
		setSize(400, 166);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);

		OldPassword = new JLabel("Stare has³o");
		OldPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		OldPassword.setBounds(32, 8, 119, 14);
		NewPassword1 = new JLabel("Nowe has³o");
		NewPassword1.setHorizontalAlignment(SwingConstants.RIGHT);
		NewPassword1.setBounds(32, 41, 119, 14);
		NewPassword2 = new JLabel("Powtórz nowe has³o");
		NewPassword2.setHorizontalAlignment(SwingConstants.RIGHT);
		NewPassword2.setBounds(10, 74, 141, 14);

		OldPasswordField = new JPasswordField();
		OldPasswordField.setBounds(161, 5, 159, 20);
		NewPassword1Field = new JPasswordField();
		NewPassword1Field.setBounds(161, 38, 159, 20);
		NewPassword2Field = new JPasswordField();
		NewPassword2Field.setBounds(161, 71, 159, 20);


		save = new JButton("Zapisz has³o");
		save.setBounds(129, 102, 159, 20);

		setLocationRelativeTo(null);

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				zapisz();

			}
		});

		panel.setLayout(null);

		panel.add(OldPassword);
		panel.add(NewPassword1);
		panel.add(NewPassword2);
		panel.add(OldPasswordField);
		panel.add(NewPassword1Field);
		panel.add(NewPassword2Field);
		panel.add(save);

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public void zapisz() {
		EntityManager em = Database.getEntityManager();
		Uzytkownik u = em.find(Uzytkownik.class, id);
		String oldPassword = new String(OldPasswordField.getPassword());
		String newPassword1 = new String(NewPassword1Field.getPassword());
		String newPassword2 = new String(NewPassword2Field.getPassword());

		if (oldPassword.equals(u.getHaslo())) {

			if (newPassword1.equals(newPassword2)) {

				u.setHaslo(new String(NewPassword1Field.getPassword()));
				em.getTransaction().begin();
				em.persist(u);
				em.getTransaction().commit();
				JOptionPane.showMessageDialog(null, "Ustawiono nowe has³o",
								"Ustawiono nowe has³o",
								JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null,
						"Wpisane has³a nie s¹ takie same", "Ró¿ne has³a",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nie poprawne stare has³o",
					"B³êdne has³o", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
