package Console;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import Model.Egzemplarz;
import Model.Status;
import Model.Uzytkownik;
import Model.Wypozyczenie;

import javax.swing.SwingConstants;

public class Lend extends JPanel {
	
	private String[] kolumnyRez = { "id egzemplarza", "tytu³","autor", "rok wydania" ,"ISBN","wydawnictwo", "u¿ytkownik"};

	
	public Lend(){
		
		ImageIcon refreshImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\refresh.png");
		ImageIcon lendImage = new ImageIcon("C:\\Users\\Arekk\\workspace\\PRZ_Projekt\\Image\\1stamp.png");
		


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
		odswiez.setBounds(0, 0, 150, 50);
		odswiez.setVerticalAlignment(SwingConstants.TOP);
		JButton lend = new JButton("Wypo¿ycz",lendImage);
		lend.setBounds(160, 0, 150, 50);
		lend.setVerticalAlignment(SwingConstants.TOP);
		JPanel panelWew = new JPanel();
		panelWew.setLayout(null);
		panelWew.add(odswiez);
		panelWew.add(lend);

		add(spt, BorderLayout.NORTH);
		add(spt2, BorderLayout.SOUTH);
		add(panelWew, BorderLayout.CENTER);
		
		//add(odswiez,BorderLayout.WEST);
		//add(lend,BorderLayout.EAST);
		
		
		setDataModelRez(getWypoz(), tabela);
		setDataModelWyp(getWypoz(), tabela2);
		
		
		odswiez.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setDataModelRez(getWypoz(), tabela);
				setDataModelWyp(getWypoz(), tabela2);	
			}
		});
		
		lend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changeStatus(tabela, tabela2);
				setDataModelRez(getWypoz(), tabela);
				setDataModelWyp(getWypoz(), tabela2);
			}
		});
	}

	public List<Wypozyczenie> getWypoz() {
		List<Wypozyczenie> rez;

		EntityManager em = Database.getEntityManager();
		Query q = em.createQuery("select w from Wypozyczenie w",Wypozyczenie.class);
		List<Wypozyczenie> wyp = q.getResultList();
		//em.refresh(wyp);
		
		return wyp;
	}

	public void setDataModelRez(List<Wypozyczenie> lista, JTable tabela) {
		Object[] rowData = new Object[7];
		DefaultTableModel mod = new DefaultTableModel();
		mod.setColumnIdentifiers(kolumnyRez);

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getEgzemplarz().getStatus().getId() == 2) {
				rowData[0] = lista.get(i).getEgzemplarz().getId();
				rowData[1] = lista.get(i).getEgzemplarz().getKsiazka().getTytul();
				rowData[2] = lista.get(i).getEgzemplarz().getKsiazka().getAutor().getImie() +" "+ lista.get(i).getEgzemplarz().getKsiazka().getAutor().getNazwisko();
				rowData[3] = lista.get(i).getEgzemplarz().getRokWydania();
				rowData[4] = lista.get(i).getEgzemplarz().getKsiazka().getIsbn();
				rowData[5] = lista.get(i).getEgzemplarz().getPublishingHouse().getName();
				rowData[6] = lista.get(i).getUzytkownik().getUzytkownikDane().getImie()+" "+ lista.get(i).getUzytkownik().getUzytkownikDane().getNazwisko();

				mod.addRow(rowData);
			}
		}

		tabela.setModel(mod);
		tabela.revalidate();
	}
	
	public void setDataModelWyp(List<Wypozyczenie> lista, JTable tabela) {
		Object[] rowData = new Object[7];
		DefaultTableModel mod = new DefaultTableModel();
		mod.setColumnIdentifiers(kolumnyRez);

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getEgzemplarz().getStatus().getId() == 3) {
				rowData[0] = lista.get(i).getEgzemplarz().getId();
				rowData[1] = lista.get(i).getEgzemplarz().getKsiazka().getTytul();
				rowData[2] = lista.get(i).getEgzemplarz().getKsiazka().getAutor().getImie() +" "+ lista.get(i).getEgzemplarz().getKsiazka().getAutor().getNazwisko();
				rowData[3] = lista.get(i).getEgzemplarz().getRokWydania();
				rowData[4] = lista.get(i).getEgzemplarz().getKsiazka().getIsbn();
				rowData[5] = lista.get(i).getEgzemplarz().getPublishingHouse().getName();
				rowData[6] = lista.get(i).getUzytkownik().getUzytkownikDane().getImie()+" "+ lista.get(i).getUzytkownik().getUzytkownikDane().getNazwisko();

				mod.addRow(rowData);
			}
		}

		tabela.setModel(mod);
		tabela.revalidate();
	}
	
	public void changeStatus(JTable tabela, JTable tabela2){
		int i = tabela.getSelectedRow();
		long ID = (long) tabela.getModel().getValueAt(i, 0);
		EntityManager em = Database.getEntityManager();
		Egzemplarz egz = em.find(Egzemplarz.class, ID);
		Status stat = em.find(Status.class,3L);
		
		em.getTransaction().begin();
		egz.setStatus(stat);
		em.getTransaction().commit();
	}
}