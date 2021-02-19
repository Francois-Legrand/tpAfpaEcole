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
import java.awt.ComponentOrientation;
import java.awt.Cursor;

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
	private JTable tableStudent;
	DefaultTableModel model;
	DefaultTableModel model2;
	DefaultTableModel model3;
	private JTextField inputCodePostal;
	private JTable tableSalle;
	private JTable tableAdress;

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
		frame.setBounds(100, 100, 1400, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{};
		gridBagLayout.rowWeights = new double[]{};
		
		frame.getContentPane().setLayout(gridBagLayout);
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(-20, 0, 1400, 900);
		frame.getContentPane().add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		JPanel pnlCards = new JPanel();
		
		splitPane.setRightComponent(pnlCards);
		pnlCards.setLayout(new CardLayout(0,0));
		
		CardLayout cardLayout;
		cardLayout = (CardLayout) (pnlCards.getLayout());
		
		
		GradientPanel gradien = new GradientPanel();
		
		pnlCards.add(gradien, "gradien");
		gradien.setLayout(null);
		
		JLabel labelSudents = new JLabel("CREATION D'UN ETUDIANT");
		labelSudents.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 18));
		labelSudents.setHorizontalAlignment(SwingConstants.CENTER);
		labelSudents.setBounds(0, 23, 1383, 32);
		gradien.add(labelSudents);
		
		inputNom = new JTextField();
		inputNom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inputNom.setBackground(new Color(255, 255, 255));
		inputNom.setBounds(180, 130, 289, 50);
		gradien.add(inputNom);
		inputNom.setColumns(10);
		
		JLabel labelNom = new JLabel("Nom");
		labelNom.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNom.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		labelNom.setBounds(0, 130, 170, 50);
		gradien.add(labelNom);
		
		inputPrenom = new JTextField();
		inputPrenom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inputPrenom.setBounds(180, 222, 289, 50);
		gradien.add(inputPrenom);
		inputPrenom.setColumns(10);
		
		JLabel labelDateNaissance = new JLabel("Date de naissance");
		labelDateNaissance.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDateNaissance.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		labelDateNaissance.setBounds(0, 307, 170, 50);
		gradien.add(labelDateNaissance);
		
		JDateChooser inputDateNaissance = new JDateChooser();
		inputDateNaissance.setBounds(180, 308, 289, 50);
		gradien.add(inputDateNaissance);
		
		JLabel labelAdress = new JLabel("Adresse");
		labelAdress.setHorizontalAlignment(SwingConstants.RIGHT);
		labelAdress.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		labelAdress.setBounds(0, 386, 170, 50);
		gradien.add(labelAdress);
		
		AdresseService adresseService = new AdresseService();
		
		String adresseName;
		int number = adresseService.findAll().size();

		JComboBox comboBoxAdress = new JComboBox();
		comboBoxAdress.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		comboBoxAdress.addItem(new ComboItem("Selectioner votre adresse", 0));
		for(int i = 0; i < number; i++) {
			adresseName = adresseService.findAll().get(i).getNumRue()+" "+adresseService.findAll().get(i).getNomRue()+" "+adresseService.findAll().get(i).getVille();
			int adresseId = adresseService.findAll().get(i).getId();
			ComboItem ok = new ComboItem(adresseName, adresseId); 
			
			comboBoxAdress.addItem(ok);
		
		}
		comboBoxAdress.setBounds(180, 386, 289, 50);
		gradien.add(comboBoxAdress);
		
		JLabel lbSalle = new JLabel("Salle");
		lbSalle.setHorizontalAlignment(SwingConstants.RIGHT);
		lbSalle.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lbSalle.setBounds(0, 463, 167, 50);
		gradien.add(lbSalle);
		
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
		comboBoxSalle.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		comboBoxSalle.setBounds(180, 465, 289, 50);
		gradien.add(comboBoxSalle);
		
		tableStudent = new JTable();
		tableStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tableStudent.getSelectedRow();
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
		scrollPane.setBounds(535, 130, 827, 690);
		gradien.add(scrollPane);
		model = new DefaultTableModel();
		Object[] column = {"Id", "Nom", "Prénom", "DateNaissance","Adresse", "Salle"};
		final Object[] row = new Object[6];
		model.setColumnIdentifiers(column);
		tableStudent.setModel(model);
		scrollPane.setViewportView(tableStudent);
		
		JButton btnAddStudent = new JButton("");
		btnAddStudent.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\user_add.png"));
		btnAddStudent.setBounds(311, 774, 46, 46);
		
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
		
		btnAddStudent.addActionListener(new ActionListener() {
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
		gradien.add(btnAddStudent);
		JButton btnRemoveStudent = new JButton("");
		btnRemoveStudent.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\user_delete.png"));
		btnRemoveStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = tableStudent.getSelectedRow();
				System.out.println(rowIndex);
				Eleve deleteEleve = eleveService.findById(Integer.parseInt(tableStudent.getModel().getValueAt(rowIndex, 0).toString()));
					deleteEleve.setId(Integer.parseInt(tableStudent.getModel().getValueAt(rowIndex, 0).toString()));
					System.out.println(deleteEleve.getId()+" id de leleve");
					
					model.removeRow(rowIndex);
					eleveService.delete(deleteEleve);
					JOptionPane.showMessageDialog(null, "Delete success");
				
			}
		});
		btnRemoveStudent.setBounds(423, 774, 46, 46);
		gradien.add(btnRemoveStudent);
		
		JButton btnUpdateStudent = new JButton("");
		btnUpdateStudent.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\document_edit.png"));
		btnUpdateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object item = comboBoxAdress.getSelectedItem();
				int value = ((ComboItem)item).getValue();
				Date dateNaissance = inputDateNaissance.getDate();
				LocalDate localDate = Instant.ofEpochMilli(dateNaissance.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				int rowIndex = tableStudent.getSelectedRow();
				int columnIndex = tableStudent.getSelectedColumn();
				Eleve eleve = eleveService.findById(Integer.parseInt(tableStudent.getModel().getValueAt(rowIndex, 0).toString()));
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
		btnUpdateStudent.setBounds(367, 774, 46, 46);
		gradien.add(btnUpdateStudent);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(68, 761, 401, 2);
		gradien.add(separator);
		
		JLabel labelPrenom_1 = new JLabel("Prénom");
		labelPrenom_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPrenom_1.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		labelPrenom_1.setBounds(0, 222, 170, 50);
		gradien.add(labelPrenom_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(220, 20, 60));
		separator_2.setBounds(0, 77, 1383, 19);
		gradien.add(separator_2);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBackground(new Color(220, 20, 60));
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setBounds(499, 78, 26, 767);
		gradien.add(separator_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Liste des élèves");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(499, 77, 884, 42);
		gradien.add(lblNewLabel_3);
		
		GradientPanel gradient2 = new GradientPanel();
		
		gradient2.setBackground(new Color(0, 153, 255));
		
		pnlCards.add(gradient2, "gradient2");
		gradient2.setLayout(null);
		
		JLabel labelCode = new JLabel("Code");
		labelCode.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		labelCode.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCode.setBounds(0, 130, 170, 50);
		gradient2.add(labelCode);
		
		inputCode = new JTextField();
		inputCode.setBounds(180, 130, 289, 50);
		gradient2.add(inputCode);
		inputCode.setColumns(10);
		
		JLabel labellibelle = new JLabel("Libellé");
		labellibelle.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		labellibelle.setHorizontalAlignment(SwingConstants.RIGHT);
		labellibelle.setBounds(0, 222, 170, 50);
		gradient2.add(labellibelle);
		
		inputLibelle = new JTextField();
		inputLibelle.setBounds(180, 222, 289, 50);
		gradient2.add(inputLibelle);
		inputLibelle.setColumns(10);
		
		JButton boutonValider_1 = new JButton("");
		boutonValider_1.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\document_add.png"));
		boutonValider_1.setBounds(311, 774, 46, 46);
		gradient2.add(boutonValider_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\document_edit.png"));
		btnNewButton_1_1.setBounds(367, 774, 46, 46);
		gradient2.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\document_delete.png"));
		btnNewButton_2.setBounds(423, 774, 46, 46);
		gradient2.add(btnNewButton_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(68, 761, 404, 2);
		gradient2.add(separator_1);
		
		JSeparator separator_2_1_1 = new JSeparator();
		separator_2_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1_1.setBackground(new Color(220, 20, 60));
		separator_2_1_1.setBounds(499, 78, 26, 767);
		gradient2.add(separator_2_1_1);
		
		JSeparator separator_2_2 = new JSeparator();
		separator_2_2.setBackground(new Color(220, 20, 60));
		separator_2_2.setBounds(0, 77, 1383, 19);
		gradient2.add(separator_2_2);
		
		tableSalle = new JTable();
		tableSalle.setBounds(0, 0, 1, 1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setSize(827, 690);
		scrollPane_1.setLocation(535, 130);
		scrollPane.setBounds(535, 130, 827, 690);
		gradient2.add(scrollPane_1);
		model2 = new DefaultTableModel();
		Object[] column1 = {"Id", "Code", "Libellé"};
		final Object[] row1 = new Object[3];
		model2.setColumnIdentifiers(column1);
		tableSalle.setModel(model2);
		scrollPane_1.setViewportView(tableSalle);
		
		JLabel labelSalle_1 = new JLabel("CREATION DE SALLE");
		labelSalle_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelSalle_1.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 18));
		labelSalle_1.setBounds(0, 21, 1383, 32);
		gradient2.add(labelSalle_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Liste des salles");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 18));
		lblNewLabel_3_2.setBounds(499, 78, 884, 42);
		gradient2.add(lblNewLabel_3_2);
		
		GradientPanel gradient3 = new GradientPanel();
		
		gradient3.setBackground(new Color(0, 153, 255));
		pnlCards.add(gradient3, "gradient3");
		gradient3.setLayout(null);
		
		JLabel lbsAdresss = new JLabel("CREATION D'ADRESSE");
		lbsAdresss.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 18));
		lbsAdresss.setBounds(0, 23, 1383, 32);
		lbsAdresss.setHorizontalAlignment(SwingConstants.CENTER);
		gradient3.add(lbsAdresss);
		
		JLabel labelPays = new JLabel("Pays");
		labelPays.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		labelPays.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPays.setBounds(0, 130, 170, 50);
		gradient3.add(labelPays);
		
		inputPays = new JTextField();
		inputPays.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		inputPays.setBounds(180, 130, 289, 50);
		inputPays.setColumns(10);
		gradient3.add(inputPays);
		
		JLabel labelVille = new JLabel("Ville");
		labelVille.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		labelVille.setHorizontalAlignment(SwingConstants.RIGHT);
		labelVille.setBounds(0, 222, 170, 50);
		gradient3.add(labelVille);
		
		inputVille = new JTextField();
		inputVille.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		inputVille.setBounds(180, 222, 189, 50);
		inputVille.setColumns(10);
		gradient3.add(inputVille);
		
		JLabel lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblCodePostal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodePostal.setBounds(0, 308, 170, 50);
		gradient3.add(lblCodePostal);
		
		inputCodePostal = new JTextField();
		inputCodePostal.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		inputCodePostal.setBounds(180, 308, 289, 50);
		gradient3.add(inputCodePostal);
		inputCodePostal.setColumns(10);
		
		JLabel labelRue = new JLabel("Rue");
		labelRue.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		labelRue.setHorizontalAlignment(SwingConstants.RIGHT);
		labelRue.setBounds(0, 386, 170, 50);
		gradient3.add(labelRue);
		
		inputRue = new JTextField();
		inputRue.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		inputRue.setBounds(180, 386, 289, 50);
		gradient3.add(inputRue);
		inputRue.setColumns(10);
		
		JLabel labelNumero = new JLabel("Numéro");
		labelNumero.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		labelNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNumero.setBounds(0, 465, 170, 50);
		gradient3.add(labelNumero);
		
		inputNumero = new JTextField();
		inputNumero.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		inputNumero.setBounds(180, 465, 289, 50);
		gradient3.add(inputNumero);
		inputNumero.setColumns(10);
		
		JSeparator separator_2_1_1_1 = new JSeparator();
		separator_2_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1_1_1.setBackground(new Color(220, 20, 60));
		separator_2_1_1_1.setBounds(499, 78, 26, 767);
		gradient3.add(separator_2_1_1_1);
		
		JSeparator separator_2_2_1 = new JSeparator();
		separator_2_2_1.setBackground(new Color(220, 20, 60));
		separator_2_2_1.setBounds(0, 77, 1383, 19);
		gradient3.add(separator_2_2_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(68, 759, 404, 2);
		gradient3.add(separator_1_1);
		
		JButton btnUpdateAdress = new JButton("");
		btnUpdateAdress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int rowIndex = tableAdress.getSelectedRow();
				
				Adresse adresse = adresseService.findById(Integer.parseInt(tableAdress.getModel().getValueAt(rowIndex, 0).toString()));
				System.out.println(adresse+" drans btnupdate graph");
				System.out.println(adresse.getId());
				
				adresse.setNomRue(inputRue.getText());
				adresse.setVille(inputVille.getText());
				adresse.setPays(inputPays.getText());
				adresse.setNumRue(Integer.parseInt(inputNumero.getText()));
				adresse.setCodePostale(Integer.parseInt(inputCodePostal.getText()));
				
				model3.setValueAt(inputNumero.getText(), rowIndex, 1);
				model3.setValueAt(inputRue.getText(), rowIndex, 2);
				model3.setValueAt(inputVille.getText(), rowIndex, 3);
				model3.setValueAt(inputCodePostal.getText(), rowIndex, 4);
				model3.setValueAt(inputPays.getText(), rowIndex, 5);
				
				adresseService.update(adresse);
				JOptionPane.showMessageDialog(null, "Update success");
			}
		});
		btnUpdateAdress.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\document_edit.png"));
		btnUpdateAdress.setBounds(367, 774, 46, 46);
		gradient3.add(btnUpdateAdress);
		
		JButton btnRemoveAdress = new JButton("");
		btnRemoveAdress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tableAdress.getSelectedRow();
				System.out.println(rowIndex);
				Adresse deleteAdresse = adresseService.findById(Integer.parseInt(tableAdress.getModel().getValueAt(rowIndex, 0).toString()));
					deleteAdresse.setId(Integer.parseInt(tableAdress.getModel().getValueAt(rowIndex, 0).toString()));
					System.out.println(deleteAdresse.getId()+" id de ladresse");
					model3.removeRow(rowIndex);
					adresseService.delete(deleteAdresse);
					JOptionPane.showMessageDialog(null, "Delete success");
			}
		});
		btnRemoveAdress.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\document_delete.png"));
		btnRemoveAdress.setBounds(423, 774, 46, 46);
		
		gradient3.add(btnRemoveAdress);
		
		tableAdress = new JTable();
		tableAdress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tableAdress.getSelectedRow();
				
				inputNumero.setText(model3.getValueAt(rowIndex, 1).toString());
				inputRue.setText(model3.getValueAt(rowIndex, 2).toString());
				inputVille.setText(model3.getValueAt(rowIndex, 3).toString());
				inputCodePostal.setText(model3.getValueAt(rowIndex, 4).toString());
				inputPays.setText(model3.getValueAt(rowIndex, 5).toString());
			}
		});
		tableAdress.setBounds(0, 0, 1, 1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setSize(827, 690);
		scrollPane_1_1.setLocation(535, 130);
		scrollPane.setBounds(535, 130, 827, 690);
		
		
		gradient3.add(scrollPane_1_1);
		model3 = new DefaultTableModel();
		Object[] column2 = {"Id", "Numéro de rue", "Nom de rue", "Ville", "Code postal", "Pays"};
		final Object[] row2 = new Object[6];
		model3.setColumnIdentifiers(column2);
		tableAdress.setModel(model3);
		scrollPane_1_1.setViewportView(tableAdress);
		
		
		if(adresseService.findAll().size() == 0) {
			System.out.println(adresseService.findAll().size());
		}else {
			
			for(int i = 0; i < number; i++) {
				System.out.println(eleveService.findAll().get(i).getId());
				
						row[0] = adresseService.findAll().get(i).getId();
						row[1] = adresseService.findAll().get(i).getNumRue();
						row[2] = adresseService.findAll().get(i).getNomRue();
						row[3] = adresseService.findAll().get(i).getVille();
						row[4] = adresseService.findAll().get(i).getCodePostale();
						row[5] = adresseService.findAll().get(i).getPays();
						model3.addRow(row);
					}
			}

		JButton btnAddAdress = new JButton("");
		btnAddAdress.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\document_add.png"));
		btnAddAdress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection connection = ConnectionUtils.getMyConnection();

					String sqlSelectId = "SELECT auto_increment AS NEXT_ID FROM `information_schema`.`tables` WHERE table_name = \"adresse\" AND table_schema = \"ecole\"";
					
					Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					
					ResultSet rs = statement2.executeQuery(sqlSelectId);
					
					int adresseId = 0;
					while (rs.next()) {
						
					    adresseId = rs.getInt("NEXT_ID");
					
					}
					AdresseService adresseService = new AdresseService();
					int numero = Integer.parseInt(inputNumero.getText());
					String rue = inputRue.getText();
					int codePostal = Integer.parseInt(inputCodePostal.getText());
					String ville = inputVille.getText();
					String pays = inputPays.getText();
					adresseService.create(new Adresse( numero, rue, codePostal, ville, pays));
					
					row2[0] = adresseId;
					row2[1] = numero;
					row2[2] = rue;
					row2[3] = ville;
					row2[4] = codePostal;
					row2[5] = pays;
					
					model3.addRow(row2);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAddAdress.setBounds(311, 774, 46, 46);
		gradient3.add(btnAddAdress);
		
		JLabel lblNewLabel_3_1 = new JLabel("Liste d'adresses");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(499, 78, 884, 42);
		gradient3.add(lblNewLabel_3_1);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setToolTipText("Ajouter un évève");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCards, "gradien");
			}
		});
		mnNewMenu.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\user_add.png"));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("");
		mnNewMenu_1.setToolTipText("Ajouter une salle");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCards, "gradient2");
			}
		});
		mnNewMenu_1.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\door.png"));
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("");
		mnNewMenu_2.setToolTipText("Ajouter une adresse");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCards, "gradient3");
			}
		});
		mnNewMenu_2.setIcon(new ImageIcon("C:\\Users\\yyper\\Desktop\\ico\\adress-book.png"));
		menuBar.add(mnNewMenu_2);
		frame.getContentPane().setLayout(null);
		
	}

	private ComboBoxModel ArraysToString(String[] liste) {
		
		return null;
	}
}
