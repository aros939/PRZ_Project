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
import Model.PublishingHouse;

public class AddPublishingHouse extends JFrame{
	
	private JPanel panel;
	private JLabel lwydawnistwo;
	private JTextField wydawnictwo;
	private JButton dodaj;
	
	
	public AddPublishingHouse(){
		
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);

		
		setTitle("Dodawanie kategorii");

		
		panel = new JPanel();
		setSize(445, 83);
		
		lwydawnistwo = new JLabel("Nazwa Wydawnictwa");
		lwydawnistwo.setHorizontalAlignment(SwingConstants.RIGHT);
		lwydawnistwo.setBounds(5, 11, 137, 20);
		
		wydawnictwo = new JTextField();
		wydawnictwo.setBounds(152, 11, 120, 20);
		
		dodaj = new JButton("Dodaj");
		dodaj.setBounds(282, 11, 120, 20);
		
		dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dodaj();
				JOptionPane.showMessageDialog(null, "Dodano Wydawnictwo!",
						"Dodano Wydawnictwo", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		panel.setLayout(null);
		panel.add(lwydawnistwo);
		panel.add(wydawnictwo);
		panel.add(dodaj);
		
		getContentPane().add(panel);
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	public void dodaj(){
		EntityManager em = Database.getEntityManager();
		PublishingHouse wyd = new PublishingHouse();
		wyd.setName(wydawnictwo.getText());
		
		em.getTransaction().begin();
		em.persist(wyd);
		em.getTransaction().commit();
		
	}


}
