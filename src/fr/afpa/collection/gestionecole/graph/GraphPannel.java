package fr.afpa.collection.gestionecole.graph;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import fr.afpa.collection.gestionecole.dao.EleveService;
import fr.afpa.collection.gestionecole.metier.Eleve;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GraphPannel {
	private JFrame frame;
	private JFrame frame2;
	private JTextField inputNom;
	private String nom;
	private String prenom;
	private JTextField inputPrenom;
	private JTextField dateNaissance;
	private JLabel lblNewLabel_3;
	private JTextField adresse;
	private JLabel lblNewLabel_4;
	private JComboBox selectSalle;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_5;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;

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
		frame.setBounds(100, 100, 449, 317);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		lblNewLabel_5 = new JLabel("Création d'élève");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.gridwidth = 3;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 0;
		frame.getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("Nom");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		inputNom = new JTextField();
		
		GridBagConstraints gbc_inputNom = new GridBagConstraints();
		gbc_inputNom.gridwidth = 2;
		gbc_inputNom.insets = new Insets(0, 0, 5, 0);
		gbc_inputNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNom.gridx = 1;
		gbc_inputNom.gridy = 1;
		frame.getContentPane().add(inputNom, gbc_inputNom);
		inputNom.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pénom");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		inputPrenom = new JTextField();
		
		GridBagConstraints gbc_inputPrenom = new GridBagConstraints();
		gbc_inputPrenom.gridwidth = 2;
		gbc_inputPrenom.insets = new Insets(0, 0, 5, 0);
		gbc_inputPrenom.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputPrenom.gridx = 1;
		gbc_inputPrenom.gridy = 2;
		frame.getContentPane().add(inputPrenom, gbc_inputPrenom);
		inputPrenom.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Date de naissance");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		dateNaissance = new JTextField();
		GridBagConstraints gbc_dateNaissance = new GridBagConstraints();
		gbc_dateNaissance.gridwidth = 2;
		gbc_dateNaissance.insets = new Insets(0, 0, 5, 0);
		gbc_dateNaissance.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateNaissance.gridx = 1;
		gbc_dateNaissance.gridy = 3;
		frame.getContentPane().add(dateNaissance, gbc_dateNaissance);
		dateNaissance.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Adresse");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		frame.getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		adresse = new JTextField();
		GridBagConstraints gbc_adresse = new GridBagConstraints();
		gbc_adresse.gridwidth = 2;
		gbc_adresse.insets = new Insets(0, 0, 5, 0);
		gbc_adresse.fill = GridBagConstraints.HORIZONTAL;
		gbc_adresse.gridx = 1;
		gbc_adresse.gridy = 4;
		frame.getContentPane().add(adresse, gbc_adresse);
		adresse.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Salle");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 5;
		frame.getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		selectSalle = new JComboBox();
		GridBagConstraints gbc_selectSalle = new GridBagConstraints();
		gbc_selectSalle.gridwidth = 2;
		gbc_selectSalle.insets = new Insets(0, 0, 5, 0);
		gbc_selectSalle.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectSalle.gridx = 1;
		gbc_selectSalle.gridy = 5;
		frame.getContentPane().add(selectSalle, gbc_selectSalle);
		
		btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 7;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton_1 = new JButton("Valider");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				nom = inputNom.getText();
				
				prenom = inputPrenom.getText();
				
				LocalDate dateNaissance = LocalDate.of(1998, 01, 22);
				
				EleveService eleveService = new EleveService();
				
				eleveService.create(new Eleve(prenom, nom, dateNaissance));
				
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 7;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Création de salle");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
	}

}
