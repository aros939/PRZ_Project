package Console;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Model.Uzytkownik;
import Model.Wypozyczenie;

public class Reservation extends JPanel {

	private String[] kolumnyRez = { "id egzemplarza", "tytu³","autor", "rok wydania" ,"ISBN", "wydawnictwo"};

	public Reservation() {
		ImageIcon refreshImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\refresh.png");


		setLayout(new BorderLayout());

		JTable tabela = new JTable();
		JScrollPane spt = new JScrollPane(tabela);
		spt.setPreferredSize(new Dimension(713, 200));
		spt.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Rezerwacje",
				TitledBorder.LEFT, TitledBorder.TOP));
		JTable tabela2 = new JTable();
		JScrollPane spt2 = new JScrollPane(tabela2);
		spt2.setPreferredSize(new Dimension(713, 200));
		spt2.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Wypozyczenia",
				TitledBorder.LEFT, TitledBorder.TOP));
		
		JButton odswiez = new JButton("Odœwie¿",refreshImage);

		add(spt, BorderLayout.NORTH);
		add(spt2, BorderLayout.SOUTH);
		add(odswiez, BorderLayout.WEST);
		
		
		setDataModelRez(getWypoz(), tabela);
		setDataModelWyp(getWypoz(), tabela2);
		
		
		odswiez.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setDataModelRez(getWypoz(), tabela);
				setDataModelWyp(getWypoz(), tabela2);	
			}
		});
	}

	public List<Wypozyczenie> getWypoz() {
		List<Wypozyczenie> rez;

		EntityManager em = Database.getEntityManager();
		Uzytkownik uzyt = em.find(Uzytkownik.class, Database.getLoggedUserId());
		em.refresh(uzyt);
		rez = uzyt.getWypozyczenia();
		
		return rez;
	}

	public void setDataModelRez(List<Wypozyczenie> lista, JTable tabela) {
		Object[] rowData = new Object[6];
		DefaultTableModel mod = new DefaultTableModel();
		mod.setColumnIdentifiers(kolumnyRez);

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getEgzemplarz().getStatus().getId() == 2) {
				rowData[0] = lista.get(i).getEgzemplarz().getId();
				rowData[1] = lista.get(i).getEgzemplarz().getKsiazka().getTytul();
				rowData[2] = lista.get(i).getEgzemplarz().getKsiazka().getAutor().getImie() +" "+ lista.get(i).getEgzemplarz().getKsiazka().getAutor().getNazwisko();
				rowData[3] = lista.get(i).getEgzemplarz().getRokWydania();
				rowData[4] = lista.get(i).getEgzemplarz().getKsiazka().getIsbn();
				rowData[4] = lista.get(i).getEgzemplarz().getPublishingHouse().getName();

				mod.addRow(rowData);
			}
		}

		tabela.setModel(mod);
		tabela.repaint();
	}
	
	public void setDataModelWyp(List<Wypozyczenie> lista, JTable tabela) {
		Object[] rowData = new Object[6];
		DefaultTableModel mod = new DefaultTableModel();
		mod.setColumnIdentifiers(kolumnyRez);

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getEgzemplarz().getStatus().getId() == 3) {
				rowData[0] = lista.get(i).getEgzemplarz().getId();
				rowData[1] = lista.get(i).getEgzemplarz().getKsiazka().getTytul();
				rowData[2] = lista.get(i).getEgzemplarz().getKsiazka().getAutor().getImie() +" "+ lista.get(i).getEgzemplarz().getKsiazka().getAutor().getNazwisko();
				rowData[3] = lista.get(i).getEgzemplarz().getRokWydania();
				rowData[4] = lista.get(i).getEgzemplarz().getKsiazka().getIsbn();
				rowData[4] = lista.get(i).getEgzemplarz().getPublishingHouse().getName();

				mod.addRow(rowData);
			}
		}

		tabela.setModel(mod);
		tabela.repaint();
	}

}
