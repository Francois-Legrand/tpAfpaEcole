package fr.afpa.collection.gestionecole.graph;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fr.afpa.collection.gestionecole.dao.AdresseService;
import fr.afpa.collection.gestionecole.dao.EleveService;
import fr.afpa.collection.gestionecole.dao.SalleService;
import fr.afpa.collection.gestionecole.metier.Adresse;
import fr.afpa.collection.gestionecole.metier.Eleve;
import fr.afpa.collection.gestionecole.metier.Salle;
import fr.francois.ecole.bdd.ConnectionUtils;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphPannel {
	private JFrame frame;
	Object id;
	private String nom;
	private String prenom;
	int numRue;
	String nomRue;
	String ville;
	String code;
	private JComboBox selectSalle;
	private JTextField inputNom;
	private JTextField inputPrenom;
	private JTextField inputCode;
	private JTextField inputLibelle;
	private JTextField inputPays;
	private JTextField inputVille;
	private JTextField inputRue;
	private JTextField inputNumero;
	private JTable table;
	DefaultTableModel model;
	private JTextField inputCodePostal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					GraphPannel window = new GraphPannel();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphPannel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{};
		gridBagLayout.rowWeights = new double[]{};
		
		frame.getContentPane().setLayout(gridBagLayout);
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(-20, 0, 800, 400);
		frame.getContentPane().add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		JPanel pnlCards = new JPanel();
		splitPane.setRightComponent(pnlCards);
		pnlCards.setLayout(new CardLayout(0,0));
		
		CardLayout cardLayout;
		cardLayout = (CardLayout) (pnlCards.getLayout());
		
		JPanel pnlCard1 = new JPanel();
		pnlCard1.setBackground(Color.GRAY);
		pnlCards.add(pnlCard1, "pnlCard1");
		pnlCard1.setLayout(null);
		
		JLabel labelSudents = new JLabel("Create student");
		labelSudents.setBounds(0, 0, 683, 14);
		pnlCard1.add(labelSudents);
		
		JLabel labelNom = new JLabel("Nom");
		labelNom.setBounds(78, 66, 52, 14);
		pnlCard1.add(labelNom);
		
		inputNom = new JTextField();
		inputNom.setBounds(130, 66, 149, 20);
		pnlCard1.add(inputNom);
		inputNom.setColumns(10);
		
		JLabel labelPrenom = new JLabel("Prénom");
		labelPrenom.setBounds(64, 91, 66, 14);
		pnlCard1.add(labelPrenom);
		
		inputPrenom = new JTextField();
		inputPrenom.setBounds(130, 91, 149, 20);
		pnlCard1.add(inputPrenom);
		inputPrenom.setColumns(10);
		
		JLabel labelDateNaissance = new JLabel("Date de naissance");
		labelDateNaissance.setBounds(10, 116, 120, 14);
		pnlCard1.add(labelDateNaissance);
		
		JDateChooser inputDateNaissance = new JDateChooser();
		inputDateNaissance.setBounds(130, 116, 149, 20);
		pnlCard1.add(inputDateNaissance);
		
		JLabel labelAdress = new JLabel("Adresse");
		labelAdress.setBounds(61, 142, 69, 14);
		pnlCard1.add(labelAdress);
		
		AdresseService adresseService = new AdresseService();
		
		ArrayList nameListeAdresse = new ArrayList();
		String adresseName;
		int number = adresseService.findAll().size();
		
		
		//tanter un add item avec resultset
		
		JComboBox comboBoxAdress = new JComboBox();
		for(int i = 0; i < number; i++) {
			adresseName = adresseService.findAll().get(i).getNumRue()+" rue "+adresseService.findAll().get(i).getNomRue()+" "+adresseService.findAll().get(i).getVille();
			int adresseId = adresseService.findAll().get(i).getId();
			comboBoxAdress.addItem(new ComboItem(adresseName, adresseId));
		
		}
		comboBoxAdress.setBounds(130, 141, 149, 22);
		pnlCard1.add(comboBoxAdress);
		
		JLabel lbSalle = new JLabel("Salle");
		lbSalle.setBounds(78, 168, 52, 14);
		pnlCard1.add(lbSalle);
		
		SalleService salleService = new SalleService();
		
		int[] idListe = new int[3];
		String[] codeListe = new String[3];
		int salleId = 0;
		for(int i = 0; i < 3; i++) {
			
			salleId = salleService.findAll().get(i).getId();
			
			String salleCode = salleService.findAll().get(i).getCode();
			codeListe[i] = salleCode;
			idListe[i] = salleId;
			
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 66, 435, 321);
		pnlCard1.add(scrollPane);
		
		JComboBox comboBoxSalle = new JComboBox(codeListe);
		comboBoxSalle.setBounds(130, 168, 149, 20);
		pnlCard1.add(comboBoxSalle);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();
				inputNom.setText(model.getValueAt(rowIndex, 1).toString());
				inputPrenom.setText(model.getValueAt(rowIndex, 2).toString());
				System.out.println(model.getValueAt(rowIndex, 3).toString());
				//inputDateNaissance.setDate((Date) model.getValueAt(rowIndex, 3));
				System.out.println(model.getValueAt(rowIndex, 4));
				comboBoxAdress.setSelectedItem(model.getValueAt(rowIndex, 4));
				comboBoxSalle.setSelectedItem(model.getValueAt(rowIndex, 5));
			}
		});
		model = new DefaultTableModel();
		Object[] column = {"Id", "Nom", "Prénom", "DateNaissance","Adresse", "Salle"};
		final Object[] row = new Object[6];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton boutonValider = new JButton("Add");
		boutonValider.setBounds(190, 199, 89, 23);
		
		EleveService eleveService = new EleveService();
		
		int number2 = eleveService.findAll().size();
		
		if(eleveService.findAll().size() == 0) {
			System.out.println(eleveService.findAll().size());
		}else {
			
			for(int i = 0; i < number2; i++) {
				System.out.println(eleveService.findAll().get(i).getId());
				
				try {
					Connection connection2 = ConnectionUtils.getMyConnection();

					Statement statement2 = connection2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							
							ResultSet.CONCUR_READ_ONLY);
					String sqlSelectSalle = "SELECT salle.code FROM salle INNER JOIN elevesalle ON salleId = salle.id where elevesalle.eleveId = "+eleveService.findAll().get(i).getId()+";";
					ResultSet rs2 = statement2.executeQuery(sqlSelectSalle);
					while (rs2.next()) {
						//salle
						code = rs2.getString(1);
						
					}
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				try {
					Connection connection = ConnectionUtils.getMyConnection();

					Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					
					String sqlSelectAdress = "SELECT adresse.numRue, adresse.nomRue, adresse.ville FROM adresse INNER JOIN eleveadresse ON adresse_id = adresse.id where eleveadresse.eleve_id = "+eleveService.findAll().get(i).getId()+";";
					ResultSet rs = statement.executeQuery(sqlSelectAdress);
					while (rs.next()) {
						
						//adresse
						numRue = rs.getInt(1);
						nomRue = rs.getString(2);
						ville = rs.getString(3);
						
						row[0] = eleveService.findAll().get(i).getId();
						row[1] = eleveService.findAll().get(i).getNom();
						row[2] = eleveService.findAll().get(i).getPrenom();
						row[3] = eleveService.findAll().get(i).getDateNaissance();
						row[4] = numRue+" rue "+nomRue+" "+ville;
						row[5] = code;
						model.addRow(row);
					}
					
					
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		}
		
		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EleveService eleveService = new EleveService();
//				if(row[0] == null) {
//					
//					row[0] = 1;
//				}else {
//					id = Integer.parseInt(row[0].toString())+1;
//					
//				}
				
				try {
					Connection connection = ConnectionUtils.getMyConnection();

					String sqlSelectId = "SELECT auto_increment AS NEXT_ID FROM `information_schema`.`tables` WHERE table_name = \"eleve\" AND table_schema = \"ecole\"";
					
					Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					
					ResultSet rs = statement2.executeQuery(sqlSelectId);
					
					int eleveId = 0;
					while (rs.next()) {
						
					    eleveId = rs.getInt("NEXT_ID");
 
					}
					
					nom = inputNom.getText();
					prenom = inputPrenom.getText();
					
					Date dateNaissance = inputDateNaissance.getDate();
					
					LocalDate localDate = Instant.ofEpochMilli(dateNaissance.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
					
					Object item = comboBoxAdress.getSelectedItem();
					int value = ((ComboItem)item).getValue();
					
					eleveService.create(new Eleve(prenom, nom, localDate, value, comboBoxSalle.getSelectedIndex()+1));
					
					row[0] = eleveId;
					row[1] = nom;
					row[2] = prenom;
					row[3] = localDate.toString();
					row[4] = comboBoxAdress.getSelectedItem();
					row[5] = comboBoxSalle.getSelectedItem();
					model.addRow(row);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		pnlCard1.add(boutonValider);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				System.out.println(rowIndex);
				Eleve deleteEleve = eleveService.findById(Integer.parseInt(table.getModel().getValueAt(rowIndex, 0).toString()));
					deleteEleve.setId(Integer.parseInt(table.getModel().getValueAt(rowIndex, 0).toString()));
					System.out.println(deleteEleve.getId()+" id de leleve");
					
					model.removeRow(rowIndex);
					eleveService.delete(deleteEleve);
					JOptionPane.showMessageDialog(null, "Delete success");
				
			}
		});
		btnNewButton.setBounds(190, 247, 89, 23);
		pnlCard1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object item = comboBoxAdress.getSelectedItem();
				int value = ((ComboItem)item).getValue();
				Date dateNaissance = inputDateNaissance.getDate();
				LocalDate localDate = Instant.ofEpochMilli(dateNaissance.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				int rowIndex = table.getSelectedRow();
				int columnIndex = table.getSelectedColumn();
				Eleve eleve = eleveService.findById(Integer.parseInt(table.getModel().getValueAt(rowIndex, 0).toString()));
				System.out.println(eleve);
				eleve = new Eleve(eleve.getId(),inputPrenom.getText(), inputNom.getText(), localDate, comboBoxSalle.getSelectedIndex()+1,value );
				System.out.println(value+"id de ladresse");
				
//				eleve.setNom(table.getModel().getValueAt(rowIndex, 1).toString());
//				System.out.println(table.getModel().getValueAt(rowIndex, 1).toString());
//				eleve.setPrenom(table.getModel().getValueAt(rowIndex, 2).toString());
//				table.getModel().getValueAt(rowIndex, 3);
//				String dateNaissanceToString = table.getModel().getValueAt(rowIndex, 3).toString();
//				LocalDate localDate = LocalDate.parse(dateNaissanceToString);
				
				
				//eleve.setDateNaissance(localDate);
				
				//table.getModel().getValueAt(rowIndex, 1).toString();
				
				eleveService.update(eleve);
				model.setValueAt(inputNom.getText(), rowIndex, 1);
				model.setValueAt(inputPrenom.getText(), rowIndex, 2);
				Date dateN = inputDateNaissance.getDate();
				LocalDate localDat = Instant.ofEpochMilli(dateN.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				model.setValueAt(localDat, rowIndex, 3);
				model.setValueAt(comboBoxAdress.getSelectedItem(), rowIndex, 4);
				model.setValueAt(comboBoxSalle.getSelectedItem(), rowIndex, 5);
				JOptionPane.showMessageDialog(null, "Update success");
			}
		});
		btnNewButton_1.setBounds(190, 291, 89, 23);
		pnlCard1.add(btnNewButton_1);
		
	
		JPanel pnlCard2 = new JPanel();
		pnlCard2.setBackground(Color.GRAY);
		pnlCards.add(pnlCard2, "pnlCard2");
		GridBagLayout gbl_pnlCard2 = new GridBagLayout();
		gbl_pnlCard2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCard2.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_pnlCard2.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCard2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlCard2.setLayout(gbl_pnlCard2);
		
		JLabel labelSalle = new JLabel("Create salle");
		GridBagConstraints gbc_labelSalle = new GridBagConstraints();
		gbc_labelSalle.insets = new Insets(0, 0, 5, 0);
		gbc_labelSalle.gridwidth = 8;
		gbc_labelSalle.gridx = 0;
		gbc_labelSalle.gridy = 0;
		pnlCard2.add(labelSalle, gbc_labelSalle);
		
		JLabel labelCode = new JLabel("Code");
		GridBagConstraints gbc_labelCode = new GridBagConstraints();
		gbc_labelCode.anchor = GridBagConstraints.EAST;
		gbc_labelCode.insets = new Insets(0, 0, 5, 5);
		gbc_labelCode.gridx = 2;
		gbc_labelCode.gridy = 1;
		pnlCard2.add(labelCode, gbc_labelCode);
		
		inputCode = new JTextField();
		GridBagConstraints gbc_inputCode = new GridBagConstraints();
		gbc_inputCode.insets = new Insets(0, 0, 5, 5);
		gbc_inputCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputCode.gridx = 3;
		gbc_inputCode.gridy = 1;
		pnlCard2.add(inputCode, gbc_inputCode);
		inputCode.setColumns(10);
		
		JLabel labellibelle = new JLabel("Libellé");
		GridBagConstraints gbc_labellibelle = new GridBagConstraints();
		gbc_labellibelle.anchor = GridBagConstraints.EAST;
		gbc_labellibelle.insets = new Insets(0, 0, 5, 5);
		gbc_labellibelle.gridx = 2;
		gbc_labellibelle.gridy = 2;
		pnlCard2.add(labellibelle, gbc_labellibelle);
		
		inputLibelle = new JTextField();
		GridBagConstraints gbc_inputLibelle = new GridBagConstraints();
		gbc_inputLibelle.insets = new Insets(0, 0, 5, 5);
		gbc_inputLibelle.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputLibelle.gridx = 3;
		gbc_inputLibelle.gridy = 2;
		pnlCard2.add(inputLibelle, gbc_inputLibelle);
		inputLibelle.setColumns(10);
		
		JButton boutonValider2 = new JButton("Valider");
		boutonValider2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GridBagConstraints gbc_boutonValider2 = new GridBagConstraints();
		gbc_boutonValider2.fill = GridBagConstraints.HORIZONTAL;
		gbc_boutonValider2.insets = new Insets(0, 0, 0, 5);
		gbc_boutonValider2.gridx = 3;
		gbc_boutonValider2.gridy = 3;
		pnlCard2.add(boutonValider2, gbc_boutonValider2);
		
		JPanel pnlCard2_1 = new JPanel();
		pnlCard2_1.setBackground(Color.GRAY);
		pnlCards.add(pnlCard2_1, "pnlCard2_1");
		GridBagLayout gbl_pnlCard2_1 = new GridBagLayout();
		gbl_pnlCard2_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCard2_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCard2_1.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCard2_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlCard2_1.setLayout(gbl_pnlCard2_1);
		
		JLabel lbsAdresss = new JLabel("Create adress");
		GridBagConstraints gbc_lbsAdresss = new GridBagConstraints();
		gbc_lbsAdresss.gridwidth = 8;
		gbc_lbsAdresss.insets = new Insets(0, 0, 5, 0);
		gbc_lbsAdresss.gridx = 0;
		gbc_lbsAdresss.gridy = 0;
		pnlCard2_1.add(lbsAdresss, gbc_lbsAdresss);
		
		JLabel labelPays = new JLabel("Pays");
		GridBagConstraints gbc_labelPays = new GridBagConstraints();
		gbc_labelPays.anchor = GridBagConstraints.EAST;
		gbc_labelPays.insets = new Insets(0, 0, 5, 5);
		gbc_labelPays.gridx = 2;
		gbc_labelPays.gridy = 1;
		pnlCard2_1.add(labelPays, gbc_labelPays);
		
		inputPays = new JTextField();
		inputPays.setColumns(10);
		GridBagConstraints gbc_inputPays = new GridBagConstraints();
		gbc_inputPays.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputPays.insets = new Insets(0, 0, 5, 5);
		gbc_inputPays.gridx = 3;
		gbc_inputPays.gridy = 1;
		pnlCard2_1.add(inputPays, gbc_inputPays);
		
		JLabel labelVille = new JLabel("Ville");
		GridBagConstraints gbc_labelVille = new GridBagConstraints();
		gbc_labelVille.anchor = GridBagConstraints.EAST;
		gbc_labelVille.insets = new Insets(0, 0, 5, 5);
		gbc_labelVille.gridx = 2;
		gbc_labelVille.gridy = 2;
		pnlCard2_1.add(labelVille, gbc_labelVille);
		
		inputVille = new JTextField();
		inputVille.setColumns(10);
		GridBagConstraints gbc_inputVille = new GridBagConstraints();
		gbc_inputVille.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputVille.insets = new Insets(0, 0, 5, 5);
		gbc_inputVille.gridx = 3;
		gbc_inputVille.gridy = 2;
		pnlCard2_1.add(inputVille, gbc_inputVille);
		
		JButton buttonValider3 = new JButton("Valider");
		buttonValider3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdresseService adresseService = new AdresseService();
				int numero = Integer.parseInt(inputNumero.getText());
				String rue = inputRue.getText();
				int codePostal = Integer.parseInt(inputCodePostal.getText());
				String ville = inputVille.getText();
				String pays = inputPays.getText();
				
				adresseService.create(new Adresse( numero, rue, codePostal, ville, pays));
				
			}
		});
		
		JLabel lblCodePostal = new JLabel("Code Postal");
		GridBagConstraints gbc_lblCodePostal = new GridBagConstraints();
		gbc_lblCodePostal.anchor = GridBagConstraints.EAST;
		gbc_lblCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodePostal.gridx = 2;
		gbc_lblCodePostal.gridy = 3;
		pnlCard2_1.add(lblCodePostal, gbc_lblCodePostal);
		
		inputCodePostal = new JTextField();
		GridBagConstraints gbc_inputCodePostal = new GridBagConstraints();
		gbc_inputCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_inputCodePostal.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputCodePostal.gridx = 3;
		gbc_inputCodePostal.gridy = 3;
		pnlCard2_1.add(inputCodePostal, gbc_inputCodePostal);
		inputCodePostal.setColumns(10);
		
		JLabel labelRue = new JLabel("Rue");
		GridBagConstraints gbc_labelRue = new GridBagConstraints();
		gbc_labelRue.anchor = GridBagConstraints.EAST;
		gbc_labelRue.insets = new Insets(0, 0, 5, 5);
		gbc_labelRue.gridx = 2;
		gbc_labelRue.gridy = 4;
		pnlCard2_1.add(labelRue, gbc_labelRue);
		
		inputRue = new JTextField();
		GridBagConstraints gbc_inputRue = new GridBagConstraints();
		gbc_inputRue.insets = new Insets(0, 0, 5, 5);
		gbc_inputRue.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputRue.gridx = 3;
		gbc_inputRue.gridy = 4;
		pnlCard2_1.add(inputRue, gbc_inputRue);
		inputRue.setColumns(10);
		
		JLabel labelNumero = new JLabel("Numéro");
		GridBagConstraints gbc_labelNumero = new GridBagConstraints();
		gbc_labelNumero.anchor = GridBagConstraints.EAST;
		gbc_labelNumero.insets = new Insets(0, 0, 5, 5);
		gbc_labelNumero.gridx = 2;
		gbc_labelNumero.gridy = 5;
		pnlCard2_1.add(labelNumero, gbc_labelNumero);
		
		inputNumero = new JTextField();
		GridBagConstraints gbc_inputNumero = new GridBagConstraints();
		gbc_inputNumero.insets = new Insets(0, 0, 5, 5);
		gbc_inputNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNumero.gridx = 3;
		gbc_inputNumero.gridy = 5;
		pnlCard2_1.add(inputNumero, gbc_inputNumero);
		inputNumero.setColumns(10);
		GridBagConstraints gbc_buttonValider3 = new GridBagConstraints();
		gbc_buttonValider3.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonValider3.insets = new Insets(0, 0, 0, 5);
		gbc_buttonValider3.gridx = 3;
		gbc_buttonValider3.gridy = 6;
		pnlCard2_1.add(buttonValider3, gbc_buttonValider3);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("create student");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlCards, "pnlCard1");
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("create room");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlCards, "pnlCard2");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		frame.getContentPane().setLayout(null);
		
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("create adress");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlCards, "pnlCard2_1");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Students list");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlCards, "pnlCard2_1_1");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		frame.getContentPane().setLayout(null);
	}

	private ComboBoxModel ArraysToString(String[] liste) {
		
		return null;
	}
}
