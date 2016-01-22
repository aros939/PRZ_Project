package Console;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Administration extends JPanel {
	
	private JButton dodajKsiazke;
	private JButton dodajAutora;
	private JButton dodajEgzemplarz;
	private JButton dodajKategorie;
	private JButton dodajWydawnictwo;
	
	public Administration(){
		
		ImageIcon bookImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\book.png");
		ImageIcon copyImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\1copy.png");
		ImageIcon authorImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\1author.png");
		ImageIcon categoryImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\1category.png");
		ImageIcon publishingHouseImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\2publishingHouse.png");
		
		dodajKsiazke = new JButton("Dodaj Ksi¹¿kê",bookImage);
		dodajKsiazke.setFont(new Font("Tahoma", Font.PLAIN, 17));
		dodajKsiazke.setBounds(93, 41, 321, 119);
		
		dodajAutora = new JButton("Dodaj Autora",authorImage);
		dodajAutora.setFont(new Font("Tahoma", Font.PLAIN, 17));
		dodajAutora.setBounds(524, 41, 321, 119);
		
		dodajEgzemplarz = new JButton("Dodaj Egzemplarz",copyImage);
		dodajEgzemplarz.setFont(new Font("Tahoma", Font.PLAIN, 17));
		dodajEgzemplarz.setBounds(93, 171, 321, 119);
		
		dodajKategorie = new JButton("Dodaj Kategoriê",categoryImage);
		dodajKategorie.setFont(new Font("Tahoma", Font.PLAIN, 17));
		dodajKategorie.setBounds(524, 171, 321, 119);
		
		dodajWydawnictwo = new JButton("Dodaj Wydawnictwo",publishingHouseImage);
		dodajWydawnictwo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		dodajWydawnictwo.setBounds(294, 301, 321, 119);
		

		setLayout(null);
		
		add(dodajKsiazke);
		add(dodajAutora);
		add(dodajEgzemplarz);
		add(dodajKategorie);
		add(dodajWydawnictwo);
		
		dodajKsiazke.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddBook();
				
			}
		});
		
		dodajAutora.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddAuthor();
				
			}
		});
		
		dodajEgzemplarz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddCopy();
				
			}
		});
		
		dodajKategorie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddCategory();
				
			}
		});
		dodajWydawnictwo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPublishingHouse();
			}
		});
		
		
		
		
		
		
	}

}
