package Console;

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

import Model.Kategoria;

public class AddCategory extends JFrame{
	
	
	private JLabel lKategoria;
	private JTextField kategoria;
	private JPanel panel;
	private JButton dodaj;
	
	
	public AddCategory(){
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);

		
		setTitle("Dodawanie kategorii");

		
		panel = new JPanel();
		setSize(410, 83);
		
		lKategoria = new JLabel("Nazwa Kategorii");
		lKategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lKategoria.setBounds(5, 11, 100, 20);
		
		kategoria = new JTextField();
		kategoria.setBounds(110, 11, 120, 20);
		
		dodaj = new JButton("Dodaj");
		dodaj.setBounds(240, 11, 120, 20);
		
		dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dodaj();
				JOptionPane.showMessageDialog(null, "Dodano Kategoriê!",
						"Dodano Kategoriê", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		panel.setLayout(null);
		panel.add(lKategoria);
		panel.add(kategoria);
		panel.add(dodaj);
		
		getContentPane().add(panel);
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

	}
	
	public void dodaj(){
		EntityManager em = Database.getEntityManager();
		Kategoria kat = new Kategoria();
		kat.setNazwa(kategoria.getText());
		
		em.getTransaction().begin();
		em.persist(kat);
		em.getTransaction().commit();
		
	}

}
