/* 			TADAS SEMIONOVAS
 * 			DNET2 
 * 			DOCTOR PROJECT PROGRAM
 * 
 * 			ID: 1
 * 			PASSWORD: 111
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.*;

public class Surgery implements ActionListener {

	@SuppressWarnings("unused")
	private static int surgeryId = 1;
	@SuppressWarnings("unused")
	private static String surgeryAdd = "Main Street, Kinsale";
	private ArrayList<Doctor> doctorList = new ArrayList<>();

	private JFrame loginFrame;
	private JPanel totalGUIPanel, listPatientsPanel, searchPatientPanel,
			updatePatientPanel, addNewPatientPanel;
	private JTextField searchPatientTF, updateNameTF, updatePhoneNoTA,
			medicineTF, procedureTF, visitDayTF, visitMonthTF, visitYearTF;
	private JLabel searchLabel1, searchLabel2, searchLabel3, searchLabel4, l1,
			l2, l3, l4;
	private JTextArea patientsHistoryReport;
	private JButton updatePatientButton;
	private JPanel loginPanel;
	private JTextField idText;
	private JPasswordField passwordText;
	private JButton b1;
	private JButton b2;
	private JTextArea updateAddressTA;
	private JButton listPatientsButton;
	private JButton searchPatientButton;
	private JButton addNewPatientButton;
	private JButton searchButton;
	private int tempPatientId, tempDoctorId;
	private JButton addNewHistoryButton;
	private JPanel addNewHistoryPanel;
	private JTextArea descriptionTA;
	private JButton addHistoryButton;
	private JTextField newNameTF;
	private JTextArea newAddressTA;
	private JTextField newPhoneNoTA;
	private JTextField newDayTF;
	private JTextField newMonthTF;
	private JTextField newYearTF;
	private JButton newDoctorB;
	private JPanel newDoctorPanel;
	private JTextField newDoctorsName;
	private JTextField newDoctorsPassword;
	private JButton submitNewDoctorB;
	private JButton logoutButton;
	private JFrame mainFrame;
	private JTextArea docListTA;
	private String backupDate;
	private JFrame fcf;
	private JMenuItem bItem;
	private JMenuItem rItem;
	private JMenuBar menuBar;
	private JMenu barMenu;
	@SuppressWarnings("rawtypes")
	private JList patientList;
	private JMenu barMenuReports;
	private JMenuItem patientsItem;
	private JMenuItem visitsItem;
	private JFrame visitFrame;
	private JFrame patientsFrame;
	private JTextArea visitReport;
	private JTextArea patientsReport;
	private DBConnection dbc;

	public void addDoctor(Doctor d) {

		int id = 1;
		for (Doctor p : doctorList) {
			if (p.getDoctorId() >= id) {
				id = p.getDoctorId() + 1;
			}

		}
		d.setDoctorId(id);
		doctorList.add(d);

	}

	public Surgery()

	{
		//dbc = DBConnection.getInstance();
		//doctorList = dbc.getDoctors();
		Doctor temp = new Doctor(1,"hi","111");
		addDoctor(temp);
		// load();
		openLoginLayout();

	}

	public Doctor getDoctor(int x) {
		Doctor tempDoctor = null;
		for (Doctor d : doctorList) {
			if (d.getDoctorId() == x) {
				tempDoctor = d;
				break;
			}
		}
		return tempDoctor;
	}

	public void openLoginLayout() {
		loginFrame = new JFrame();
		loginFrame.setSize(300, 400);
		loginFrame.setLocation(500, 300);
		loginFrame.setLayout(null);
		loginFrame.setResizable(false);

		menuBar = new JMenuBar();
		loginFrame.setJMenuBar(menuBar);
		barMenu = new JMenu("Backup/Restore");
		bItem = new JMenuItem("Backup System");
		bItem.addActionListener(this);
		rItem = new JMenuItem("Restore System");
		rItem.addActionListener(this);
		barMenu.add(bItem);
		barMenu.add(rItem);
		menuBar.add(barMenu);

		loginFrame.setJMenuBar(menuBar);
		barMenuReports = new JMenu("Reports");
		patientsItem = new JMenuItem("Show Patients Report");
		patientsItem.addActionListener(this);
		visitsItem = new JMenuItem("Show Visits Report");
		visitsItem.addActionListener(this);
		barMenuReports.add(patientsItem);
		barMenuReports.add(visitsItem);
		menuBar.add(barMenuReports);

		JPanel totalLoginPanel = new JPanel();
		totalLoginPanel.setSize(300, 500);
		totalLoginPanel.setLocation(0, 0);
		totalLoginPanel.setLayout(null);
		loginPanel = new JPanel();
		loginPanel.setLayout(new GridBagLayout());
		loginPanel.setSize(300, 150);
		loginPanel.setLocation(0, 0);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		l1 = new JLabel("Doctors ID");
		loginPanel.add(l1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		idText = new JTextField(10);
		loginPanel.add(idText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		l2 = new JLabel("Password");
		loginPanel.add(l2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		passwordText = new JPasswordField(10);
		loginPanel.add(passwordText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		b1 = new JButton();
		b1.setText("Login");
		loginPanel.add(b1, gbc);
		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				verifyAndLogin();
			}
		});
		b2 = new JButton();
		b2.setText("Cancel");
		gbc.gridx = 1;
		gbc.gridy = 2;
		loginPanel.add(b2, gbc);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		JPanel doctorsListPanel = new JPanel();
		doctorsListPanel.setSize(250, 500);
		doctorsListPanel.setLocation(30, 150);

		docListTA = new JTextArea(8, 20);
		updateDoctorsList();
		docListTA.setEditable(false);
		docListTA.setText(listDoctors());
		JScrollPane sp = new JScrollPane(docListTA);
		sp.setSize(250, 200);
		doctorsListPanel.add(sp);
		totalLoginPanel.add(doctorsListPanel);
		totalLoginPanel.add(loginPanel);
		loginFrame.add(totalLoginPanel);
		loginFrame.setVisible(true);

		// create report frames in the background
		createVisitFrame();
		createPatientsFrame();

	}

	public void createVisitFrame() {
		visitFrame = new JFrame();
		visitFrame.setLayout(new GridLayout(1, 1));
		visitFrame.setLocation(400, 200);
		visitFrame.setSize(800, 500);

		JPanel mainPanel = new JPanel(new GridLayout(1, 1));
		visitReport = new JTextArea("Visits");

		mainPanel.add(visitReport);
		visitFrame.add(mainPanel);
	}

	public void createPatientsFrame() {
		patientsFrame = new JFrame();
		patientsFrame.setLayout(new GridLayout(1, 1));
		patientsFrame.setLocation(400, 200);
		patientsFrame.setSize(700, 500);

		JPanel mainPanel = new JPanel(new GridLayout(1, 1));
		patientsReport = new JTextArea("Patients");
		JScrollPane jsp = new JScrollPane(patientsReport);
		mainPanel.add(jsp);
		patientsFrame.add(mainPanel);
	}

	@SuppressWarnings("deprecation")
	public void verifyAndLogin() {
		Doctor doc = null;
		boolean found = false;
		if (isNumeric(idText.getText())) {
			for (Doctor o : doctorList) {
				if (o.getDoctorId() == Integer.parseInt(idText.getText())) {

					found = true;
					doc = o;
				}
			}
		} else
			JOptionPane.showMessageDialog(null, "Invalid ID");

		if (found) {
			if (passwordText.getText().compareTo(doc.getDoctorPassword()) == 0) {
				tempDoctorId = doc.getDoctorId();
				openDoctorsFrame();
				loginFrame.setVisible(false);
			} else
				JOptionPane.showMessageDialog(null, "Invalid ID or Password");
		} else
			JOptionPane.showMessageDialog(null, "Invalid ID or Password");

	}

	public void openDoctorsFrame() {

		totalGUIPanel = new JPanel();
		totalGUIPanel.setLayout(null);

		mainFrame = new JFrame();
		mainFrame.setSize(700, 500);
		mainFrame.setLocation(200, 200);
		mainFrame.setTitle("Logged in as "
				+ getDoctor(tempDoctorId).getDoctorName());
		mainFrame.setResizable(false);

		// logout panel
		JPanel logoutPanel = new JPanel();
		logoutPanel.setSize(80, 35);
		logoutPanel.setLocation(0, 0);

		logoutButton = new JButton("Logout");
		logoutPanel.add(logoutButton);
		logoutButton.addActionListener(this);
		totalGUIPanel.add(logoutPanel);

		// doctors ID and Name panel
		JPanel topDoctorsDetailsPanel = new JPanel();
		topDoctorsDetailsPanel.setSize(300, 35);
		topDoctorsDetailsPanel.setLocation(200, 0);

		JLabel l1 = new JLabel("ID:  " + getDoctor(tempDoctorId).getDoctorId()
				+ "   /  Name: " + getDoctor(tempDoctorId).getDoctorName());
		topDoctorsDetailsPanel.add(l1);

		// JScrollPane searchedHistoryScroll = new
		// JScrollPane(searchedPatientHistoryList);
		// searchedPatientHistoryPanel.add(searchedHistoryScroll);
		// searchPanel.add(searchedPatientHistoryPanel);

		totalGUIPanel.add(topDoctorsDetailsPanel);

		// top buttons panel
		JPanel topButtonsPanel = new JPanel();
		topButtonsPanel.setSize(700, 35);
		topButtonsPanel.setLocation(0, 35);
		topButtonsPanel.setBackground(Color.blue);
		topButtonsPanel.setLayout(new GridLayout(1, 4));

		// buttons for buttons panel
		listPatientsButton = new JButton("List Patients");
		searchPatientButton = new JButton("search Patient");
		addNewPatientButton = new JButton("Add New Patient");

		listPatientsButton.addActionListener(this);
		searchPatientButton.addActionListener(this);
		addNewPatientButton.addActionListener(this);

		topButtonsPanel.add(listPatientsButton);
		topButtonsPanel.add(searchPatientButton);
		topButtonsPanel.add(addNewPatientButton);
		totalGUIPanel.add(topButtonsPanel);

		JPanel newDoctoBPanel = new JPanel(new GridLayout(1, 1));
		newDoctoBPanel.setLocation(560, 5);
		newDoctoBPanel.setSize(130, 25);
		newDoctorB = new JButton("Add New Doctor");
		newDoctoBPanel.add(newDoctorB);
		newDoctorB.addActionListener(this);
		totalGUIPanel.add(newDoctoBPanel);

		// backup and restore menu
		mainFrame.setJMenuBar(menuBar);

		// List of patients Panel
		createListPatientsPanel();
		// search For Patient Panel
		createSearchForPatientPanel();
		// update patient panel
		createUpdatePatientPanel();
		// add new patient panel
		createAddNewPatientPanel();
		// create add new history
		createAddNewHistoryPanel();
		// creaete add new dctor panel
		createNewDoctorPanel();
		// ///////////////////////////////////////////////////////////////////////////////////////////////////

		updateListPatientsPanel();

		totalGUIPanel.setOpaque(true);
		mainFrame.add(totalGUIPanel);
		mainFrame.setVisible(true);
		listPatientsButton.doClick();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createListPatientsPanel() { // creating list patients panel

		listPatientsPanel = new JPanel();
		listPatientsPanel.setSize(700, 430);
		listPatientsPanel.setLocation(0, 70);
		listPatientsPanel.setLayout(new GridLayout(1, 1));
		// textArea
		patientList = new JList(getDoctor(tempDoctorId).getPList().toArray());
		patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patientList.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Patient p = (Patient) patientList.getSelectedValue();
					tempPatientId = p.getPId();
					updateSearchPatientPanel();
					searchPatientPanel.setVisible(true);
					listPatientsPanel.setVisible(false);
				}

			}
		});

		JScrollPane sp1 = new JScrollPane(patientList);
		listPatientsPanel.add(sp1);
		totalGUIPanel.add(listPatientsPanel);
		listPatientsPanel.setVisible(false);
	}

	public void createSearchForPatientPanel() { // creating search for patient
												// panel

		searchPatientPanel = new JPanel();
		searchPatientPanel.setSize(700, 430);
		searchPatientPanel.setLocation(0, 70);
		searchPatientPanel.setLayout(null);

		JPanel topSearchPanel = new JPanel(new FlowLayout());
		topSearchPanel.setSize(500, 30);
		topSearchPanel.setLocation(100, 0);
		topSearchPanel.add(new JLabel("Enter Patiets Name/ID:"));
		searchPatientTF = new JTextField(20);
		topSearchPanel.add(searchPatientTF);
		searchButton = new JButton("Find Patient");

		searchButton.addActionListener(this);

		topSearchPanel.add(searchButton);
		searchPatientPanel.add(topSearchPanel);

		// searched patients details labels
		JPanel searchedPatientDetailsPanel = new JPanel(new GridLayout(4, 2));
		searchedPatientDetailsPanel.setLocation(40, 60);
		searchedPatientDetailsPanel.setSize(250, 200);
		searchedPatientDetailsPanel.setBackground(Color.WHITE);

		l1 = new JLabel("Name:");
		searchLabel1 = new JLabel("");
		l2 = new JLabel("Address:");
		searchLabel2 = new JLabel("");
		l3 = new JLabel("Phone Number:");
		searchLabel3 = new JLabel("");
		l4 = new JLabel("DOB:");
		searchLabel4 = new JLabel("");

		searchedPatientDetailsPanel.add(l1);
		searchedPatientDetailsPanel.add(searchLabel1);
		searchedPatientDetailsPanel.add(l2);
		searchedPatientDetailsPanel.add(searchLabel2);
		searchedPatientDetailsPanel.add(l3);
		searchedPatientDetailsPanel.add(searchLabel3);
		searchedPatientDetailsPanel.add(l4);
		searchedPatientDetailsPanel.add(searchLabel4);

		searchPatientPanel.add(searchedPatientDetailsPanel);

		// update button
		JPanel updateButtonPanel = new JPanel();
		updateButtonPanel.setSize(200, 40);
		updateButtonPanel.setLocation(40, 300);

		updatePatientPanel = new JPanel();
		updatePatientButton = new JButton("Upadate Current Patient");
		updatePatientButton.setVisible(false);

		updatePatientButton.addActionListener(this);
		updateButtonPanel.add(updatePatientButton);
		searchPatientPanel.add(updateButtonPanel);

		JPanel updateAddNewHistoryButtonPanel = new JPanel();
		updateAddNewHistoryButtonPanel.setSize(200, 40);
		updateAddNewHistoryButtonPanel.setLocation(40, 350);

		addNewHistoryButton = new JButton("Add New History");
		addNewHistoryButton.addActionListener(this);
		addNewHistoryButton.setVisible(false);

		updateAddNewHistoryButtonPanel.add(addNewHistoryButton);
		searchPatientPanel.add(updateAddNewHistoryButtonPanel);

		// searched patients history text area
		JPanel patientsHistoryPanel = new JPanel();
		patientsHistoryPanel.setSize(350, 300);
		patientsHistoryPanel.setLocation(330, 50);

		patientsHistoryReport = new JTextArea(17, 30);
		JScrollPane sp2 = new JScrollPane(patientsHistoryReport);
		patientsHistoryPanel.add(sp2);
		patientsHistoryReport.setEditable(false);
		patientsHistoryReport.setLineWrap(true);
		patientsHistoryReport.setWrapStyleWord(true);
		patientsHistoryReport.setWrapStyleWord(true);

		searchPatientPanel.add(patientsHistoryPanel);

		totalGUIPanel.add(searchPatientPanel);
	}

	public void createUpdatePatientPanel() {

		updatePatientPanel.setSize(700, 430);
		updatePatientPanel.setLocation(0, 70);
		updatePatientPanel.setLayout(null);
		updatePatientPanel.setBackground(Color.darkGray);
		updateNameTF = new JTextField();
		updateAddressTA = new JTextArea();
		updatePhoneNoTA = new JTextField();

		JPanel updateDetailsPanel = new JPanel();
		updateDetailsPanel.setSize(300, 200);
		updateDetailsPanel.setLocation(200, 50);
		updateDetailsPanel.setLayout(new GridLayout(4, 2));

		updateDetailsPanel.add(new JLabel("Name:"));
		updateDetailsPanel.add(updateNameTF);

		updateDetailsPanel.add(new JLabel("Address:"));
		updateAddressTA.setBorder(BorderFactory.createBevelBorder(1));
		updateDetailsPanel.add(updateAddressTA);

		updateDetailsPanel.add(new JLabel("Phone no:"));
		updateDetailsPanel.add(updatePhoneNoTA);

		updateDetailsPanel.add(new JLabel(""));
		JButton updatePatientB = new JButton("Update");
		updatePatientB.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				validateAndUpdatePatientsDetails();
			}
		});

		updateDetailsPanel.add(updatePatientB);
		updatePatientPanel.add(updateDetailsPanel);
		updatePatientPanel.setVisible(false);
		totalGUIPanel.add(updatePatientPanel);

	}

	public void createAddNewPatientPanel() {
		addNewPatientPanel = new JPanel();
		addNewPatientPanel.setSize(700, 430);
		addNewPatientPanel.setLocation(0, 70);
		addNewPatientPanel.setLayout(null);

		newNameTF = new JTextField(25);
		newAddressTA = new JTextArea(5, 25);
		newPhoneNoTA = new JTextField(25);

		JPanel newPatientDetailsPanel = new JPanel();
		newPatientDetailsPanel.setSize(400, 300);
		newPatientDetailsPanel.setLocation(170, 50);
		newPatientDetailsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(10, 7, 7, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		newPatientDetailsPanel.add(new JLabel("Name:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		newPatientDetailsPanel.add(newNameTF, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		newPatientDetailsPanel.add(new JLabel("DOB:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		JPanel DOBPanel = new JPanel(new GridLayout(1, 3));
		newDayTF = new JTextField("DD");
		newMonthTF = new JTextField("MM");
		newYearTF = new JTextField("YYYY");
		DOBPanel.add(newDayTF);
		DOBPanel.add(newMonthTF);
		DOBPanel.add(newYearTF);
		newPatientDetailsPanel.add(DOBPanel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		newPatientDetailsPanel.add(new JLabel("Address:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		updateAddressTA.setBorder(BorderFactory.createBevelBorder(1));
		newPatientDetailsPanel.add(new JScrollPane(newAddressTA), gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		newPatientDetailsPanel.add(new JLabel("Phone no:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		newPatientDetailsPanel.add(newPhoneNoTA, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JButton addNewPatientB = new JButton("Add Patient");
		addNewPatientB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validateUpdateAndAddNewPatient();
			}
		});

		newPatientDetailsPanel.add(addNewPatientB, gbc);
		addNewPatientPanel.add(newPatientDetailsPanel);

		totalGUIPanel.add(addNewPatientPanel);
	}

	public void createAddNewHistoryPanel() {
		addNewHistoryPanel = new JPanel();
		addNewHistoryPanel.setSize(700, 430);
		addNewHistoryPanel.setLocation(0, 70);
		addNewHistoryPanel.setLayout(null);

		JPanel historyLabelsPanel = new JPanel(new GridLayout(4, 1));
		historyLabelsPanel.setLocation(20, 20);
		historyLabelsPanel.setSize(100, 200);
		historyLabelsPanel.add(new JLabel("Visit Date"));
		historyLabelsPanel.add(new JLabel("Medicine"));
		historyLabelsPanel.add(new JLabel("Procedure"));
		historyLabelsPanel.add(new JLabel("Description"));

		addNewHistoryPanel.add(historyLabelsPanel);
		JPanel visitDatePanel = new JPanel(new GridLayout(1, 3));
		visitDatePanel.setLocation(120, 20);
		visitDatePanel.setSize(200, 50);
		visitDayTF = new JTextField("DD");
		visitMonthTF = new JTextField("MM");
		visitYearTF = new JTextField("YYYY");
		visitDatePanel.add(visitDayTF);
		visitDatePanel.add(visitMonthTF);
		visitDatePanel.add(visitYearTF);

		addNewHistoryPanel.add(visitDatePanel);

		JPanel medicineAndProcedurePanel = new JPanel(new GridLayout(2, 1));
		medicineAndProcedurePanel.setLocation(120, 70);
		medicineAndProcedurePanel.setSize(550, 100);

		medicineTF = new JTextField();
		medicineAndProcedurePanel.add(medicineTF);
		procedureTF = new JTextField();
		medicineAndProcedurePanel.add(procedureTF);

		addNewHistoryPanel.add(medicineAndProcedurePanel);

		JPanel descriptionPanel = new JPanel(new GridLayout(1, 1));
		descriptionPanel.setLocation(120, 170);
		descriptionPanel.setSize(550, 200);

		descriptionTA = new JTextArea();
		descriptionTA.setBorder(BorderFactory.createBevelBorder(1));
		descriptionPanel.add(descriptionTA);

		addNewHistoryPanel.add(descriptionPanel);

		JPanel addHistoryButtonPannel = new JPanel();
		addHistoryButtonPannel.setLocation(400, 20);
		addHistoryButtonPannel.setSize(200, 30);

		addHistoryButton = new JButton("Add History");
		addHistoryButton.addActionListener(this);
		addHistoryButton.setSize(200, 30);
		addHistoryButtonPannel.add(addHistoryButton);

		addNewHistoryPanel.add(addHistoryButtonPannel);

		addNewHistoryPanel.setVisible(false);
		totalGUIPanel.add(addNewHistoryPanel);
	}

	public void createNewDoctorPanel() {
		newDoctorPanel = new JPanel();
		newDoctorPanel.setSize(700, 430);
		newDoctorPanel.setLocation(0, 70);
		newDoctorPanel.setLayout(null);

		JPanel innerPanel = new JPanel(new GridLayout(3, 2));
		innerPanel.setSize(250, 100);
		innerPanel.setLocation(220, 50);
		innerPanel.add(new JLabel("Enter Name"));
		newDoctorsName = new JTextField();
		innerPanel.add(newDoctorsName);
		innerPanel.add(new JLabel("Choose Password"));
		newDoctorsPassword = new JTextField();
		innerPanel.add(newDoctorsPassword);
		innerPanel.add(new JLabel(""));

		submitNewDoctorB = new JButton("Submit");
		submitNewDoctorB.addActionListener(this);

		innerPanel.add(submitNewDoctorB);

		newDoctorPanel.add(innerPanel);
		totalGUIPanel.add(newDoctorPanel);
		newDoctorPanel.setVisible(false);
	}

	@SuppressWarnings("unchecked")
	public void updateListPatientsPanel() {
		patientList.setListData(getDoctor(tempDoctorId).getPList().toArray());
	}

	@SuppressWarnings("deprecation")
	public void updateSearchPatientPanel() {
		searchLabel1.setText(getDoctor(tempDoctorId).getPatient(tempPatientId)
				.getPName());
		searchLabel2.setText(getDoctor(tempDoctorId).getPatient(tempPatientId)
				.getPAddress());
		searchLabel3
				.setText(""
						+ getDoctor(tempDoctorId).getPatient(tempPatientId)
								.getPPhone());
		searchLabel4.setText(getDoctor(tempDoctorId).getPatient(tempPatientId)
				.getPDOB().getDate()
				+ "/"
				+ getDoctor(tempDoctorId).getPatient(tempPatientId).getPDOB()
						.getMonth()
				+ "/"
				+ getDoctor(tempDoctorId).getPatient(tempPatientId).getPDOB()
						.getYear());

		updatePatientButton.setVisible(true);
		addNewHistoryButton.setVisible(true);
		patientsHistoryReport.setVisible(true);
		patientsHistoryReport.setText(getDoctor(tempDoctorId).getPatient(
				tempPatientId).getPatientHistoryReport());
		System.out.println(getDoctor(tempDoctorId).getPatient(tempPatientId)
				.getPatientHistoryReport());

	}

	public void updateUpdatePatientPanel() {
		// details panel
		// update Patient Panel
		updateNameTF.setText(getDoctor(tempDoctorId).getPatient(tempPatientId)
				.getPName());
		updateAddressTA.setText(getDoctor(tempDoctorId).getPatient(
				tempPatientId).getPAddress());
		updatePhoneNoTA
				.setText(""
						+ getDoctor(tempDoctorId).getPatient(tempPatientId)
								.getPPhone());
	}

	public void validateAndUpdatePatientsDetails() {
		boolean valid = false;
		if (updateNameTF.getText().compareTo("") == 0
				|| updatePhoneNoTA.getText().compareTo("") == 0
				|| updateAddressTA.getText().compareTo("") == 0) {
			JOptionPane.showMessageDialog(null,
					"Please do not leave any blank spaces!!");

		} else {
			if (!isAlphabetic(updateNameTF.getText())
					|| !isNumeric(updatePhoneNoTA.getText())) {
				JOptionPane
						.showMessageDialog(null,
								"Name should be alphabetic!!\nPhone number should be alphabetic!!");
			} else
				valid = true;
		}

		if (valid == true) {
			updatePatientPanel.setVisible(false);
			
		      String sql = "UPDATE patient "
		      		+ "SET pAddress = '" + updateAddressTA.getText()
		      		+ "', pName = '" + updateNameTF.getText()
		      		+ "', pPhone =  " + updatePhoneNoTA.getText()
		      		+ " WHERE pId = "+tempPatientId+";";
		      dbc.insert(sql);
		      updateDoctorsArrayList();
			JOptionPane.showMessageDialog(null, "Updated Successful");
			searchLabel1.setText("");
			searchLabel2.setText("");
			searchLabel3.setText("");
			searchLabel4.setText("");
			searchPatientTF.setText("");
			updateNameTF.setText("");
			updateAddressTA.setText("");
			updatePhoneNoTA.setText("");
			patientsHistoryReport.setVisible(false);
			updatePatientButton.setVisible(false);
		}

	}

	public void validateAndAddNewHistory() {
		if (validDate(visitDayTF.getText(), visitMonthTF.getText(),
				visitYearTF.getText())
				&& !medicineTF.getText().isEmpty()
				&& !descriptionTA.getText().isEmpty()
				&& !procedureTF.getText().isEmpty()) {
			
			
			String month = "" +visitMonthTF.getText();
			if(month.length() == 1){
				month="0" + month;
			}
			String day = ""+ visitDayTF.getText();
			if(day.length() == 1){
				day="0" + day;
			}
			String sql = "INSERT INTO patientHistory VALUES ("+ (dbc.getMaxPatientHistoryId()+1) 
					+", " +  tempPatientId
					+", " +  tempDoctorId
					+", '"+ visitYearTF.getText() + "-" + month + "-" + day
					+"', '"+ descriptionTA.getText()
					+"', '"+ medicineTF.getText()
					+"', '" + procedureTF.getText()
					+"')";
			System.out.print(sql);
			dbc.insert(sql);
			updateDoctorsArrayList();

			visitDayTF.setText("DD");
			visitMonthTF.setText("MM");
			visitYearTF.setText("YYYY");
			descriptionTA.setText("");
			medicineTF.setText("");
			procedureTF.setText("");
			searchLabel1.setText("");
			searchLabel2.setText("");
			searchLabel3.setText("");
			searchLabel4.setText("");
			searchPatientTF.setText("");
			addNewHistoryPanel.setVisible(false);
			JOptionPane.showMessageDialog(null, "Success!!!");
		} else
			JOptionPane
					.showMessageDialog(null, "Please enter Data Correctly!!");
	}

	@SuppressWarnings({ "deprecation" })
	public void validateUpdateAndAddNewPatient() {
		boolean valid = false;
		if (newNameTF.getText().compareTo("") == 0
				|| newPhoneNoTA.getText().compareTo("") == 0
				|| newAddressTA.getText().compareTo("") == 0) {
			JOptionPane.showMessageDialog(null,
					"Please do not leave any blank spaces!!");

		} else {
			if (!isAlphabetic(newNameTF.getText())
					|| !isNumeric(newPhoneNoTA.getText())) {
				JOptionPane
						.showMessageDialog(null,
								"Name should be alphabetic!!\nPhone number should be alphabetic!!");

			} else {
				if (validDate(newDayTF.getText(), newMonthTF.getText(),
						newYearTF.getText())) {
					valid = true;
				}
			}

		}

		if (valid == true) {

			String month = "" +newMonthTF.getText();
			if(month.length() == 1){
				month="0" + month;
			}
			String day = ""+ newDayTF.getText();
			if(day.length() == 1){
				day="0" + day;
			}
			String sql = "INSERT INTO patient VALUES ("+ (dbc.getMaxPatientId()+1) 
					+", " + tempDoctorId 
					+", '"+ newNameTF.getText() 
					+"', '"+ newAddressTA.getText()
					+"', " + Integer.parseInt(newPhoneNoTA.getText()) 
					+", '"+ newYearTF.getText() + "-" + month + "-" + day
					+"')";
			System.out.print(sql);
			dbc.insert(sql);
			updateDoctorsArrayList();

			JOptionPane.showMessageDialog(null, "Updated Successful");
			newNameTF.setText("");
			newAddressTA.setText("");
			newPhoneNoTA.setText("");
			newDayTF.setText("");
			newMonthTF.setText("");
			newYearTF.setText("");
			listPatientsButton.doClick();
		}
	}

	public void validateAndAddNewDoctor() {

		if (newDoctorsName.getText().compareTo("") != 0
				&& newDoctorsPassword.getText().compareTo("") != 0) {
			if (isAlphabetic(newDoctorsName.getText())) {
				String sql = "INSERT INTO doctor VALUES ("
						+ (dbc.getMaxDoctorId() + 1) + ", '"
						+ newDoctorsName.getText() + "', '"
						+ newDoctorsPassword.getText() + "')";
				dbc.insert(sql);
				updateDoctorsArrayList();
				JOptionPane.showMessageDialog(null, "Succeess!");

				newDoctorsName.setText("");
				newDoctorsPassword.setText("");
				listPatientsButton.doClick();
				newDoctorPanel.setVisible(false);
				updateDoctorsList();
			} else
				JOptionPane
						.showMessageDialog(null, "name should be alphabetic");

		} else
			JOptionPane.showMessageDialog(null, "Do not leave empty spaces");

	}

	public void updateDoctorsList() {
		docListTA.setText(listDoctors());
	}

	public String listDoctors() {
		String s = "";
		for (Doctor d : doctorList) {
			s += "ID:" + d.getDoctorId() + "\t" + d.getDoctorName() + "\n";
		}
		return s;
	}

	public static void main(String[] args) {
		new Surgery();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == listPatientsButton) {
			updatePatientPanel.setVisible(false);
			addNewPatientPanel.setVisible(false);
			listPatientsPanel.setVisible(true);
			searchPatientPanel.setVisible(false);
			addNewHistoryPanel.setVisible(false);
			newDoctorPanel.setVisible(false);

			updateListPatientsPanel();
		}
		if (e.getSource() == searchPatientButton) {
			updatePatientPanel.setVisible(false);
			addNewPatientPanel.setVisible(false);
			listPatientsPanel.setVisible(false);
			searchPatientPanel.setVisible(true);
			addNewHistoryPanel.setVisible(false);
			newDoctorPanel.setVisible(false);

		}
		if (e.getSource() == addNewPatientButton) {
			updatePatientPanel.setVisible(false);
			addNewPatientPanel.setVisible(true);
			listPatientsPanel.setVisible(false);
			searchPatientPanel.setVisible(false);
			addNewHistoryPanel.setVisible(false);
			newDoctorPanel.setVisible(false);

		}
		if (e.getSource() == updatePatientButton) {
			updatePatientPanel.setVisible(true);
			addNewPatientPanel.setVisible(false);
			listPatientsPanel.setVisible(false);
			searchPatientPanel.setVisible(false);
			addNewHistoryPanel.setVisible(false);
			newDoctorPanel.setVisible(false);

			updateUpdatePatientPanel();
		}
		if (e.getSource() == searchButton) {
			if (getDoctor(tempDoctorId).searchForPatientName(
					searchPatientTF.getText()) != 0) {

				tempPatientId = getDoctor(tempDoctorId).getPatient(
						searchPatientTF.getText()).getPId();
				updateSearchPatientPanel();
			} else if (isNumeric(searchPatientTF.getText())) {
				if (getDoctor(tempDoctorId).searchForPatientId(
						Integer.parseInt(searchPatientTF.getText())) != 0) {

					tempPatientId = getDoctor(tempDoctorId).getPatient(
							Integer.parseInt(searchPatientTF.getText()))
							.getPId();
					updateSearchPatientPanel();
				} else
					JOptionPane.showMessageDialog(null, "Patient not found");
			} else
				JOptionPane.showMessageDialog(null, "Patient not found");

		}
		if (e.getSource() == addNewHistoryButton) {
			updatePatientPanel.setVisible(false);
			addNewPatientPanel.setVisible(false);
			listPatientsPanel.setVisible(false);
			searchPatientPanel.setVisible(false);
			addNewHistoryPanel.setVisible(true);		
			newDoctorPanel.setVisible(false);

		}
		if (e.getSource() == addHistoryButton) {
			validateAndAddNewHistory();
		}
		if (e.getSource() == newDoctorB) {
			updatePatientPanel.setVisible(false);
			addNewPatientPanel.setVisible(false);
			listPatientsPanel.setVisible(false);
			searchPatientPanel.setVisible(false);
			addNewHistoryPanel.setVisible(false);
			newDoctorPanel.setVisible(true);

		}
		if (e.getSource() == submitNewDoctorB) {

			validateAndAddNewDoctor();
		}
		if (e.getSource() == logoutButton) {

			// save();
			mainFrame.dispose();
			loginFrame.setVisible(true);
			idText.setText("");
			passwordText.setText("");
		}

		if (e.getSource() == bItem) {
			int valid = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to back up system?", "Backup",
					JOptionPane.YES_NO_OPTION);
			if (valid == 0) {
				// backup();
			} else
				JOptionPane.showMessageDialog(null, "Backup was cancelled");
		}
		if (e.getSource() == rItem) {
			fcf = new JFrame("System  restore file");
			fcf.setSize(500, 300);
			JFileChooser fc = new JFileChooser();
			fcf.add(fc);
			fcf.setVisible(true);
			int returnVal = fc.showOpenDialog(fcf);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// This is where a real application would open the file.
				fcf.setVisible(false);

				JOptionPane.showMessageDialog(null, "File was loaded");

				if (fc.getSelectedFile().getName().compareTo("") != 0) {
					// restore(fc.getSelectedFile().getName());
				}
				updateDoctorsList();
			} else {
				fcf.setVisible(false);
				JOptionPane.showMessageDialog(null, "cancelled");

			}

		}
		if (e.getSource() == visitsItem) {

			visitFrame.setVisible(true);
			ArrayList<PatientHistory> phl = new ArrayList<>();
			String s = "";

			for (Doctor d : doctorList) {
				for (Patient p : d.getPatients()) {
					for (PatientHistory h : p.getPatientHistories()) {
						phl.add(h);
					}
				}
			}

			Collections.sort(phl, new Comparator<PatientHistory>() {
				public int compare(PatientHistory result1,
						PatientHistory result2) {
					return result2.getVisitDate().compareTo(
							result1.getVisitDate());
				}
			});
			for (PatientHistory h : phl) {
				s += "Visit Date: "
						+ h.getVisitDate().getDate()
						+ "/"
						+ h.getVisitDate().getMonth()
						+ "/"
						+ h.getVisitDate().getYear()
						+ "\t"
						+ "ID: "
						+ h.getPatientId()
						+ "\t"
						+ getDoctor(h.getDocorId())
								.getPatient(h.getPatientId()).getPName()
						+ "\tSeen By "
						+ getDoctor(h.getDocorId()).getDoctorName() + "\n";
			}

			visitReport.setText(s);
		}
		if (e.getSource() == patientsItem) {

			patientsFrame.setVisible(true);
			String s = "";
			ArrayList<Patient> pl = new ArrayList<>();
			
			for (Doctor d : doctorList) {
				for(Patient p : d.getPatients()){
					pl.add(p);
				}
			}
			Collections.sort(pl, new Comparator<Patient>() {
				public int compare(Patient p1,
						Patient p2) {
					return p2.getPName().compareTo(
							p1.getPName());
				}
			});
			for(Patient p: pl){
				s+=p.toString()+"\n" + p.getPatientHistoryReport();
			}
			patientsReport.setText(s);

		}
	}

	// public void save() {
	// FileOutputStream fos = null;
	// ObjectOutputStream out = null;
	// try {
	// fos = new FileOutputStream("file");
	// out = new ObjectOutputStream(fos);
	// out.writeObject(doctorList);
	//
	// out.close();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	//
	// @SuppressWarnings("unchecked")
	// public void load() {
	// FileInputStream fis = null;
	// ObjectInputStream in = null;
	// try {
	// fis = new FileInputStream("file");
	// in = new ObjectInputStream(fis);
	// doctorList = (ArrayList<Doctor>) in.readObject();
	// in.close();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	//
	// }
	//
	// public void backup() {
	// FileOutputStream fos = null;
	// ObjectOutputStream out = null;
	// Date date = new Date();
	// SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
	//
	// backupDate = sdf.format(date);
	// try {
	// fos = new FileOutputStream(backupDate);
	// out = new ObjectOutputStream(fos);
	// out.writeObject(doctorList);
	// out.close();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// JOptionPane.showMessageDialog(null,
	// "System was successfully backed up to a file " + backupDate);
	// }
	//
	// @SuppressWarnings("unchecked")
	// public void restore(String fileName) {
	// FileInputStream fis = null;
	// ObjectInputStream in = null;
	// try {
	// fis = new FileInputStream(fileName);
	// in = new ObjectInputStream(fis);
	// doctorList = (ArrayList<Doctor>) in.readObject();
	// in.close();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	//
	// }

	public static boolean isNumeric(String str) {
		try {
			@SuppressWarnings("unused")
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isAlphabetic(String s) {
		boolean valid = true;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2'
					|| s.charAt(i) == '3' || s.charAt(i) == '4'
					|| s.charAt(i) == '5' || s.charAt(i) == '6'
					|| s.charAt(i) == '7' || s.charAt(i) == '8'
					|| s.charAt(i) == '9') {
				valid = false;
				break;
			}
		}

		return valid;
	}

	public static boolean validDate(String d, String m, String y) {
		boolean valid = false;
		if (isNumeric(d) && isNumeric(m) && isNumeric(y)) {
			valid = true;
		} else {
			JOptionPane.showMessageDialog(null, "Date Must Be Numeric");
			valid = false;
		}
		if (valid) {
			if ((d.length() == 1 || d.length() == 2)
					&& Integer.parseInt(d) <= 31 && Integer.parseInt(d) >= 1) {
				valid = true;
			} else {
				JOptionPane.showMessageDialog(null,
						"Please enter day correctly");
				valid = false;
			}

		}
		if (valid) {
			if ((m.length() == 1 || m.length() == 2)
					&& Integer.parseInt(m) <= 12 && Integer.parseInt(m) >= 1) {
				valid = true;
			} else {
				JOptionPane.showMessageDialog(null,
						"Please enter month correctly");
				valid = false;
			}
		}
		if (valid) {
			if (y.length() == 4 && Integer.parseInt(y) <= 2050
					&& Integer.parseInt(y) >= 1900) {
				valid = true;
			} else {
				JOptionPane.showMessageDialog(null,
						"Please enter year correctly");
				valid = false;
			}

		}
		return valid;
	}

	public void updateDoctorsArrayList() {
		doctorList = dbc.getDoctors();
	}
}
