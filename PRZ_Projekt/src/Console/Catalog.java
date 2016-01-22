package Console;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import Model.Egzemplarz;
import Model.Kategoria;
import Model.Ksiazka;
import Model.Status;
import Model.Uzytkownik;
import Model.Wypozyczenie;

public class Catalog extends JPanel {

	private String[] kolumny = { "id", "tytu³", "isbn", "autor", "kategoria" };
	private String[] kolumny1 = { "id", "rok Wydania", "status", "wydawnictwo"};

	public Catalog() {

		setLayout(new BorderLayout());

		JTree drzewo = new JTree(setNodes());
		JScrollPane sp = new JScrollPane(drzewo);
		JPanel panelWew = new JPanel(new BorderLayout());

		JPanel wyszukiwanie = new JPanel(new FlowLayout());
		JLabel tyt1 = new JLabel("Tytu³:");
		JTextField tyt = new JTextField();
		JLabel imA = new JLabel("Imie autora:");
		JTextField imieA = new JTextField();
		JLabel nA = new JLabel("Nazwisko autora:");
		JTextField nazwA = new JTextField();
		JButton szukaj = new JButton("Wyszukaj");
		tyt.setColumns(10);
		imieA.setColumns(10);
		nazwA.setColumns(10);

		

		JButton rezerwuj = new JButton("Zarezerwuj");

		JTable tabela = new JTable();
		JScrollPane spt = new JScrollPane(tabela);
		spt.setPreferredSize(new Dimension(713, 200));
		spt.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Ksi¹¿ki",
				TitledBorder.LEFT, TitledBorder.TOP));
		JTable tabela2 = new JTable();
		JScrollPane spt2 = new JScrollPane(tabela2);
		spt2.setPreferredSize(new Dimension(713, 200));
		spt2.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Dostêpne Egzemplarze",
				TitledBorder.LEFT, TitledBorder.TOP));
		

		setDataModel(getAllRows(), tabela);

		add(sp, BorderLayout.WEST);
		add(panelWew, BorderLayout.CENTER);
		wyszukiwanie.add(tyt1);
		wyszukiwanie.add(tyt);
		wyszukiwanie.add(imA);
		wyszukiwanie.add(imieA);
		wyszukiwanie.add(nA);
		wyszukiwanie.add(nazwA);
		wyszukiwanie.add(szukaj);
		panelWew.add(wyszukiwanie, BorderLayout.NORTH);
		panelWew.add(spt, BorderLayout.CENTER);
		panelWew.add(spt2, BorderLayout.SOUTH);
		add(rezerwuj, BorderLayout.EAST);

		szukaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				setDataModel(
						wyszukajKsiazke(tyt.getText(), imieA.getText(),
								nazwA.getText()), tabela);

			}
		});

		drzewo.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				EntityManager em = Database.getEntityManager();
				if(drzewo.getSelectionPath().getLastPathComponent().toString().equals("Kategorie")){
					Query q1 = em.createQuery("select k from Ksiazka k",Ksiazka.class);
					setDataModel(q1.getResultList(),tabela);
				}
				else{
					Query q = em.createQuery("select k from Ksiazka k where k.kategoria.nazwa=:kat",Ksiazka.class);
					q.setParameter("kat", drzewo.getSelectionPath().getLastPathComponent().toString());
					setDataModel(q.getResultList(), tabela);
				}
			}
		});

		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int i = tabela.getSelectedRow();
				long ID = (long) tabela.getModel().getValueAt(i, 0);
				EntityManager em = Database.getEntityManager();
				Ksiazka k = em.find(Ksiazka.class, ID);
				em.refresh(k);
				List<Egzemplarz> egz = k.getEgzemplarze();
				setDataModel2(egz, tabela2);

			}

		});

		rezerwuj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tabela.getSelectedRow() < -1)
					JOptionPane.showMessageDialog(null, "Wybierz ksi¹¿kê!",
							"Wybierz Ksia¿kê!", JOptionPane.ERROR_MESSAGE);
				else if (tabela2.getSelectedRow() < 0)
					JOptionPane.showMessageDialog(null, "Wybierz egzemplarz!",
							"Wybierz egzemplarz", JOptionPane.ERROR_MESSAGE);
				else {
					EntityManager em = Database.getEntityManager();
					Status status = em.find(Status.class, 2L);
					Uzytkownik zalogowanyUzyt = em.find(Uzytkownik.class, Database.getLoggedUserId());
					Egzemplarz egz = em.find(Egzemplarz.class, (long) tabela2
							.getValueAt(tabela2.getSelectedRow(), 0));
					egz.setStatus(status);
					Wypozyczenie wyp = new Wypozyczenie();
					wyp.setDataWyp(new Date());
					wyp.setUzytkownik(zalogowanyUzyt);
					wyp.setEgzemplarz(egz);
					em.getTransaction().begin();
					em.persist(wyp);
					em.getTransaction().commit();

					JOptionPane.showMessageDialog(null,
							"Zarezerwowa³eœ ksi¹¿kê!", "Rezerwacja",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

	}

	private DefaultMutableTreeNode setNodes() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Kategorie");
		EntityManager em = Database.getEntityManager();

		Query q = em.createQuery("select k from Kategoria k", Kategoria.class);
		List<Kategoria> kat = q.getResultList();
		for (Kategoria kategoria : kat) {
			root.add(new DefaultMutableTreeNode(kategoria.getNazwa()));
		}
		return root;
	}

	public List<Ksiazka> getAllRows() {
		List<Ksiazka> lista = new ArrayList<Ksiazka>();

		EntityManager em = Database.getEntityManager();
		Query q = em.createQuery("select k from Ksiazka k", Ksiazka.class);
		lista = q.getResultList();

		return lista;
	}

	public void setDataModel(List<Ksiazka> lista, JTable tabela) {
		Object[] rowData = new Object[5];
		DefaultTableModel mod = new DefaultTableModel();
		mod.setColumnIdentifiers(kolumny);

		for (int i = 0; i < lista.size(); i++) {

			rowData[0] = lista.get(i).getId();
			rowData[1] = lista.get(i).getTytul();
			rowData[2] = lista.get(i).getIsbn();
			rowData[3] = lista.get(i).getAutor().getImie() + " "
					+ lista.get(i).getAutor().getNazwisko();
			rowData[4] = lista.get(i).getKategoria().getNazwa();

			mod.addRow(rowData);
		}

		tabela.setModel(mod);
	}

	public List<Ksiazka> wyszukajKsiazke(String tytul, String imieA,
			String nazwA) {

		List<Ksiazka> lista = new ArrayList<Ksiazka>();
		EntityManager em = Database.getEntityManager();
		Query q = em
				.createQuery(
						"select k from Ksiazka k where k.tytul like :tyt and k.autor.imie like :im and k.autor.nazwisko like :nazw",
						Ksiazka.class);
		q.setParameter("tyt", "%" + tytul + "%");
		q.setParameter("im", "%" + imieA + "%");
		q.setParameter("nazw", "%" + nazwA + "%");
		lista = q.getResultList();

		return lista;
	}

	public void setDataModel2(List<Egzemplarz> egz, JTable tab) {

		Object[] rowData = new Object[4];
		DefaultTableModel mod = new DefaultTableModel();
		mod.setColumnIdentifiers(kolumny1);

		for (int i = 0; i < egz.size(); i++) {
			if (egz.get(i).getStatus().getId() == 1L) {
				rowData[0] = egz.get(i).getId();
				rowData[1] = egz.get(i).getRokWydania();
				rowData[2] = egz.get(i).getStatus().getNazwa();
				rowData[3] = egz.get(i).getPublishingHouse().getName();

				mod.addRow(rowData);
			}
		}

		tab.setModel(mod);

	}

}
