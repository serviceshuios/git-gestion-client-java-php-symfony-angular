package com.noelmace.gestionclient.spring.gui.frames;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;

import com.noelmace.gestionclient.spring.business.services.ClientService;
import com.noelmace.gestionclient.spring.business.services.OrderingService;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JMenuItem;
import javax.swing.JFormattedTextField;

public class MainFrame {
	
	private ClientService clientService;
	
	private OrderingService orderingService;

	public JFrame frame;
	private JTextField nomClientTextField;
	private JTextField prenomClientTextField;

	private JLabel statusLabel = new JLabel("");
	private JPanel mainPanel = new JPanel();
	
	private CardLayout mainCardLayout = new CardLayout(0, 0);
	private JTextField refTextField;

	private JFormattedTextField idClientTextField;

	private JFormattedTextField priceTextField;

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnAjouterClient = new JMenu("Clients");
		menuBar.add(mnAjouterClient);
		
		JMenuItem mntmAjouterClient = new JMenuItem("Créer un client");
		mntmAjouterClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainCardLayout.show(mainPanel, "ajoutClient");
			}
		});
		mnAjouterClient.add(mntmAjouterClient);
		
		JMenu mnCommandes = new JMenu("Commandes");
		menuBar.add(mnCommandes);
		
		JMenuItem mntmCrerUneCommande = new JMenuItem("Créer une commande");
		mntmCrerUneCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCardLayout.show(mainPanel, "ajoutCmd");
			}
		});
		mnCommandes.add(mntmCrerUneCommande);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(mainCardLayout);
		
		JPanel ajoutClientPanel = new JPanel();
		mainPanel.add(ajoutClientPanel, "ajoutClient");
		ajoutClientPanel.setLayout(null);
		
		nomClientTextField = new JTextField();
		nomClientTextField.setBounds(178, 52, 114, 19);
		ajoutClientPanel.add(nomClientTextField);
		nomClientTextField.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(110, 54, 70, 15);
		ajoutClientPanel.add(lblNom);
		
		prenomClientTextField = new JTextField();
		prenomClientTextField.setBounds(178, 75, 114, 19);
		ajoutClientPanel.add(prenomClientTextField);
		prenomClientTextField.setColumns(10);
		
		JLabel lblPrnom = new JLabel("Prénom");
		lblPrnom.setBounds(90, 77, 70, 15);
		ajoutClientPanel.add(lblPrnom);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					clientService.create(nomClientTextField.getText(), prenomClientTextField.getText());
				} catch (Exception e) {
					statusLabel.setText("Création du client impossible : "+e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnAjouter.setBounds(147, 154, 117, 25);
		ajoutClientPanel.add(btnAjouter);
		
		JPanel ajoutCmdPanel = new JPanel();
		mainPanel.add(ajoutCmdPanel, "ajoutCmd");
		ajoutCmdPanel.setLayout(null);
		
		refTextField = new JTextField();
		refTextField.setBounds(165, 51, 114, 19);
		ajoutCmdPanel.add(refTextField);
		refTextField.setColumns(10);
		
		JLabel lblRef = new JLabel("Ref :");
		lblRef.setBounds(87, 53, 70, 15);
		ajoutCmdPanel.add(lblRef);
		
		//NumberFormat idFormat = NumberFormat.getIntegerInstance();
		idClientTextField = new JFormattedTextField(new Long(1));
		idClientTextField.setBounds(165, 113, 114, 19);
		ajoutCmdPanel.add(idClientTextField);
		
		//DecimalFormat numberFormat = new DecimalFormat();
		//numberFormat.setNegativePrefix(null);
		priceTextField = new JFormattedTextField(new Double(0));
		priceTextField.setBounds(165, 82, 114, 19);
		ajoutCmdPanel.add(priceTextField);
		
		JLabel lblPrix = new JLabel("Prix :");
		lblPrix.setBounds(87, 84, 70, 15);
		ajoutCmdPanel.add(lblPrix);
		
		JLabel lblIdClient = new JLabel("id Client :");
		lblIdClient.setBounds(87, 115, 70, 15);
		ajoutCmdPanel.add(lblIdClient);
		
		JButton btnAjouter_1 = new JButton("Ajouter");
		btnAjouter_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					orderingService.makeOrder(
							((Number)idClientTextField.getValue()).longValue(),
							refTextField.getText(), ((Number)priceTextField.getValue()).doubleValue()
							);
				} catch (SQLException e) {
					statusLabel.setText("Création du client impossible : "+e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnAjouter_1.setBounds(125, 170, 117, 25);
		ajoutCmdPanel.add(btnAjouter_1);
		
		JPanel statusPanel = new JPanel();
		frame.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		
		statusPanel.add(statusLabel);
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setOrderingService(OrderingService orderingService) {
		this.orderingService = orderingService;
	}
}
