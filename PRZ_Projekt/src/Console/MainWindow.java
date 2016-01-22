package Console;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainWindow extends JFrame {

	public MainWindow(long rolaId) {

		setSize(900, 510);
		setTitle("Biblioteka");

		if (rolaId == 1 || rolaId == 2 || rolaId == 3) {

		}

		JTabbedPane tabbedPane = new JTabbedPane();
		add(tabbedPane);

		Catalog katalog;
		Reservation rezerwacje;
		MyAccount mojeKonto;
		Administration zarzadzanie;
		AddUser dodajUzytkownika;
		EditUser edytujUzytkownika;
		Lend lend;

		if (rolaId == 1 || rolaId == 2 || rolaId == 3) {
			katalog = new Catalog();
			rezerwacje = new Reservation();
			mojeKonto = new MyAccount();
			
			tabbedPane.addTab("Katalog", katalog);
			tabbedPane.addTab("Rezerwacje i Wypo퓓czenia", rezerwacje);
			tabbedPane.addTab("Moje konto", mojeKonto);	
		}
		if(rolaId == 2 || rolaId == 3){
			zarzadzanie = new Administration();
			dodajUzytkownika = new AddUser();
			lend = new Lend();
			
			tabbedPane.addTab("Wydanie", lend);
			tabbedPane.addTab("Zarz퉐zanie", zarzadzanie);
			tabbedPane.addTab("Dodaj u퓓tkownika", dodajUzytkownika);
		}
		if(rolaId == 3){
			edytujUzytkownika = new EditUser();
			tabbedPane.addTab("Edytuj u퓓tkownika", edytujUzytkownika);
			
		}

		

		


		setResizable(false);
		setLocationRelativeTo(null);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
