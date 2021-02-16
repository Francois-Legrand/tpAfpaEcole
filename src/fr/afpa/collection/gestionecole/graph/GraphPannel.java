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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.mysql.cj.x.protobuf.Mysqlx.Ok;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import javax.swing.JToolBar;

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
		pnlCard1.setBackground(new Color(51, 153, 255));
		pnlCards.add(pnlCard1, "pnlCard1");
		pnlCard1.setLayout(null);
		
		JLabel labelSudents = new JLabel("Create student");
		labelSudents.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 18));
		labelSudents.setHorizontalAlignment(SwingConstants.CENTER);
		labelSudents.setBounds(0, 23, 783, 14);
		pnlCard1.add(labelSudents);
		
		inputNom = new JTextField();
		inputNom.setBackground(new Color(255, 255, 255));
		inputNom.setBounds(130, 66, 149, 20);
		pnlCard1.add(inputNom);
		inputNom.setColumns(10);
		
		JLabel labelNom = new JLabel("Nom");
		labelNom.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNom.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		labelNom.setBounds(54, 66, 66, 14);
		pnlCard1.add(labelNom);
		
		inputPrenom = new JTextField();
		inputPrenom.setBounds(130, 91, 149, 20);
		pnlCard1.add(inputPrenom);
		inputPrenom.setColumns(10);
		
		JLabel labelDateNaissance = new JLabel("Date de naissance");
		labelDateNaissance.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDateNaissance.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		labelDateNaissance.setBounds(0, 115, 120, 14);
		pnlCard1.add(labelDateNaissance);
		
		JDateChooser inputDateNaissance = new JDateChooser();
		inputDateNaissance.setBounds(130, 116, 149, 20);
		pnlCard1.add(inputDateNaissance);
		
		JLabel labelAdress = new JLabel("Adresse");
		labelAdress.setHorizontalAlignment(SwingConstants.RIGHT);
		labelAdress.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		labelAdress.setBounds(51, 141, 69, 14);
		pnlCard1.add(labelAdress);
		
		AdresseService adresseService = new AdresseService();
		
		String adresseName;
		int number = adresseService.findAll().size();

		JComboBox comboBoxAdress = new JComboBox();
		comboBoxAdress.addItem(new ComboItem("Select your adress", 0));
		for(int i = 0; i < number; i++) {
			adresseName = adresseService.findAll().get(i).getNumRue()+" "+adresseService.findAll().get(i).getNomRue()+" "+adresseService.findAll().get(i).getVille();
			int adresseId = adresseService.findAll().get(i).getId();
			ComboItem ok = new ComboItem(adresseName, adresseId); 
			
			comboBoxAdress.addItem(ok);
		
		}
		comboBoxAdress.setBounds(130, 141, 149, 22);
		pnlCard1.add(comboBoxAdress);
		
		JLabel lbSalle = new JLabel("Salle");
		lbSalle.setHorizontalAlignment(SwingConstants.RIGHT);
		lbSalle.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		lbSalle.setBounds(68, 167, 52, 14);
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
				
				String string = model.getValueAt(rowIndex, 3).toString();
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				try {
					Date date = format.parse(string);
					System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010
					inputDateNaissance.setDate(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				comboBoxSalle.setSelectedItem(model.getValueAt(rowIndex, 5));
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 66, 473, 321);
		pnlCard1.add(scrollPane);
		model = new DefaultTableModel();
		Object[] column = {"Id", "Nom", "Prénom", "DateNaissance","Adresse", "Salle"};
		final Object[] row = new Object[6];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton boutonValider = new JButton("");
		boutonValider.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\user_add.png"));
		boutonValider.setBounds(121, 341, 46, 46);
		
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
						row[4] = numRue+" "+nomRue+" "+ville;
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
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		pnlCard1.add(boutonValider);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\user_delete.png"));
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
		btnNewButton.setBounds(233, 341, 46, 46);
		pnlCard1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\document_edit.png"));
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
		btnNewButton_1.setBounds(177, 341, 46, 46);
		pnlCard1.add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(47, 328, 238, 2);
		pnlCard1.add(separator);
		
		JLabel labelPrenom_1 = new JLabel("Prénom");
		labelPrenom_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPrenom_1.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		labelPrenom_1.setBounds(54, 91, 66, 14);
		pnlCard1.add(labelPrenom_1);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(-36, 0, 819, 20);
		pnlCard1.add(toolBar);
		
		JLabel lblNewLabel_2 = new JLabel("");
		toolBar.add(lblNewLabel_2);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCards, "pnlCard2_1");
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\printer.png"));
		
		JLabel lblNewLabel_1 = new JLabel("");
		toolBar.add(lblNewLabel_1);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCards, "pnlCard2");
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\home.png"));
		lblNewLabel_1.setDisplayedMnemonic('2');
		
		JLabel lblNewLabel = new JLabel("");
		toolBar.add(lblNewLabel);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCards, "pnlCard1");
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\user_white.png"));
		
		JPanel pnlCard2 = new JPanel();
		pnlCard2.setBackground(new Color(0, 153, 255));
		pnlCards.add(pnlCard2, "pnlCard2");
		pnlCard2.setLayout(null);
		
		JLabel labelSalle = new JLabel("Create salle");
		labelSalle.setHorizontalAlignment(SwingConstants.CENTER);
		labelSalle.setBounds(0, 0, 795, 14);
		pnlCard2.add(labelSalle);
		
		JLabel labelCode = new JLabel("Code");
		labelCode.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCode.setBounds(30, 87, 79, 14);
		pnlCard2.add(labelCode);
		
		inputCode = new JTextField();
		inputCode.setBounds(114, 84, 164, 20);
		pnlCard2.add(inputCode);
		inputCode.setColumns(10);
		
		JLabel labellibelle = new JLabel("Libellé");
		labellibelle.setHorizontalAlignment(SwingConstants.RIGHT);
		labellibelle.setBounds(30, 112, 79, 14);
		pnlCard2.add(labellibelle);
		
		inputLibelle = new JTextField();
		inputLibelle.setBounds(114, 109, 164, 20);
		pnlCard2.add(inputLibelle);
		inputLibelle.setColumns(10);
		
		JButton boutonValider_1 = new JButton("");
		boutonValider_1.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\add.png"));
		boutonValider_1.setBounds(120, 341, 46, 46);
		pnlCard2.add(boutonValider_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\document_edit.png"));
		btnNewButton_1_1.setBounds(176, 341, 46, 46);
		pnlCard2.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\close.png"));
		btnNewButton_2.setBounds(232, 341, 46, 46);
		pnlCard2.add(btnNewButton_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(46, 328, 238, 2);
		pnlCard2.add(separator_1);
		
		JPanel pnlCard2_1 = new JPanel();
		pnlCard2_1.setBackground(new Color(0, 153, 255));
		pnlCards.add(pnlCard2_1, "pnlCard2_1");
		pnlCard2_1.setLayout(null);
		
		JLabel lbsAdresss = new JLabel("Create adress");
		lbsAdresss.setBounds(0, 0, 783, 14);
		lbsAdresss.setHorizontalAlignment(SwingConstants.CENTER);
		pnlCard2_1.add(lbsAdresss);
		
		JLabel labelPays = new JLabel("Pays");
		labelPays.setBounds(251, 22, 23, 14);
		pnlCard2_1.add(labelPays);
		
		inputPays = new JTextField();
		inputPays.setBounds(279, 19, 252, 20);
		inputPays.setColumns(10);
		pnlCard2_1.add(inputPays);
		
		JLabel labelVille = new JLabel("Ville");
		labelVille.setBounds(253, 47, 21, 14);
		pnlCard2_1.add(labelVille);
		
		inputVille = new JTextField();
		inputVille.setBounds(279, 44, 252, 20);
		inputVille.setColumns(10);
		pnlCard2_1.add(inputVille);
		
		JButton buttonValider3 = new JButton("Valider");
		buttonValider3.setBounds(279, 144, 252, 23);
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
		lblCodePostal.setBounds(216, 72, 58, 14);
		pnlCard2_1.add(lblCodePostal);
		
		inputCodePostal = new JTextField();
		inputCodePostal.setBounds(279, 69, 252, 20);
		pnlCard2_1.add(inputCodePostal);
		inputCodePostal.setColumns(10);
		
		JLabel labelRue = new JLabel("Rue");
		labelRue.setBounds(255, 97, 19, 14);
		pnlCard2_1.add(labelRue);
		
		inputRue = new JTextField();
		inputRue.setBounds(279, 94, 252, 20);
		pnlCard2_1.add(inputRue);
		inputRue.setColumns(10);
		
		JLabel labelNumero = new JLabel("Numéro");
		labelNumero.setBounds(235, 122, 39, 14);
		pnlCard2_1.add(labelNumero);
		
		inputNumero = new JTextField();
		inputNumero.setBounds(279, 119, 252, 20);
		pnlCard2_1.add(inputNumero);
		inputNumero.setColumns(10);
		pnlCard2_1.add(buttonValider3);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
	}

	private ComboBoxModel ArraysToString(String[] liste) {
		
		return null;
	}
}
