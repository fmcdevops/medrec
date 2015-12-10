package com.bea.medrec.webservices.swing;

import com.bea.medrec.value.Address;
import com.bea.medrec.value.Patient;
import com.bea.medrec.webservices.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.*;

/**
 * <p>Edit Patient Profile JFrame.  </p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class EditProfileFrame extends JFrame {
  private JLabel promptLabel = new JLabel();
  private JLabel WSDLLabel = new JLabel();
  private JLabel firstName_label = new JLabel();
  private JLabel lastName_label = new JLabel();
  private JLabel middleName_label = new JLabel();
  private JLabel gender_label = new JLabel();
  private JLabel dateOfBirth_label = new JLabel();
  private JLabel ssn_label = new JLabel();
  private JLabel phone_label = new JLabel();
  private JLabel email_label = new JLabel();
  private JLabel address_label = new JLabel();

  private JLabel streetName_label = new JLabel("Street Name");
  private JLabel city_label = new JLabel("City");
  private JLabel state_label = new JLabel("State");
  private JLabel zipcode_label = new JLabel("ZipCode");
  private JLabel country_label = new JLabel("Country");


  private JTextField patientIDTextField = new JTextField(11);
  private JButton submitButton = new JButton("Submit");
  private JButton saveButton = new JButton("Save Changes");

  private String wsdl = System.getProperty("wsdl.location");
  private JTextField WSDLTextField = new JTextField();
  private JTextField firstName_textfield = new JTextField(15);
  private JTextField lastName_textfield = new JTextField(15);
  private JTextField middleName_textfield = new JTextField(15);
  private JTextField gender_textfield = new JTextField(6);
  private JTextField dateOfBirth_textfield = new JTextField(9);
  private JTextField ssn_textfield = new JTextField(9);
  private JTextField phone_textfield = new JTextField(10);
  private JTextField email_textfield = new JTextField(20);
  private JTextField streetName1_textfield = new JTextField(20);
  private JTextField streetName2_textfield = new JTextField(20);
  private JTextField city_textfield = new JTextField(15);
  private JTextField state_textfield = new JTextField(3);
  private JTextField zipcode_textfield = new JTextField(10);
  private JTextField country_textfield = new JTextField(15);

  private int width = 675;
  private int height = 450;
  private PatientWS thePatient = null;

  //Construct the frame
  public EditProfileFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      init();
      this.setSize(width, height);
      this.setLocation(150, 150);
      this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
      this.setDefaultLookAndFeelDecorated(true);
      this.setVisible(true);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

 /**
  * <p>Map patient data into the textfields</p>
  */
  private void mapPatientProfile() {
    if (this.thePatient != null) {
      firstName_textfield.setText(this.thePatient.getFirstName());
      lastName_textfield.setText(this.thePatient.getLastName());
      middleName_textfield.setText(this.thePatient.getMiddleName());
      gender_textfield.setText(this.thePatient.getGender());
      if (this.thePatient.getDateOfBirth() != null) {
        dateOfBirth_textfield.setText(Utils.getDisplayDate(this.thePatient.getDateOfBirth().toString()));
      }
      ssn_textfield.setText(this.thePatient.getSsn());
      phone_textfield.setText(this.thePatient.getPhone());
      email_textfield.setText(this.thePatient.getEmail());
      if (this.thePatient.getAddressWS() != null) {
        AddressWS addressWS = this.thePatient.getAddressWS();
        this.streetName1_textfield.setText(addressWS.getStreetName1());
        this.streetName2_textfield.setText(addressWS.getStreetName2());
        this.city_textfield.setText(addressWS.getCity());
        this.state_textfield.setText(addressWS.getState());
        this.zipcode_textfield.setText(addressWS.getZipCode());
        this.country_textfield.setText(addressWS.getCountry());
      }
    }
  }

 /**
  * <p>Address JPanel</p>
  */
  private JPanel constructAddressPanel() {
    JPanel addressPanel = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    addressPanel.setLayout(gridbag);
//    addressPanel.setBackground(Color.orange);
    GridBagConstraints constraints = new GridBagConstraints();
    address_label.setText("Address");
    constraints.gridy = 0;
    constraints.gridx = 0;
    constraints.anchor = constraints.NORTHWEST;
    constraints.insets.bottom=10;
    constraints.insets.right=10;
    gridbag.setConstraints(address_label, constraints);
    constraints.gridy = 1;
    gridbag.setConstraints(this.streetName_label, constraints);
    constraints.gridx = 1;
    constraints.gridwidth = 3;
    gridbag.setConstraints(this.streetName1_textfield, constraints);
    constraints.gridy = 2;
    gridbag.setConstraints(this.streetName2_textfield, constraints);
    constraints.gridwidth = 1;
    constraints.gridy = 3;
    constraints.gridx = 0;
    gridbag.setConstraints(this.city_label, constraints);
    constraints.gridx = 1;
    gridbag.setConstraints(this.city_textfield, constraints);
    constraints.gridy = 4;
    constraints.gridx = 0;
    gridbag.setConstraints(this.state_label, constraints);
    constraints.gridx = 1;
    gridbag.setConstraints(this.state_textfield, constraints);
    constraints.gridy = 5;
    constraints.gridx = 0;
    gridbag.setConstraints(this.zipcode_label, constraints);
    constraints.gridx = 1;
    gridbag.setConstraints(this.zipcode_textfield, constraints);
    constraints.gridy = 6;
    constraints.gridx = 0;
    gridbag.setConstraints(this.country_label, constraints);
    constraints.gridx = 1;
    gridbag.setConstraints(this.country_textfield, constraints);

    addressPanel.add(address_label);
    addressPanel.add(this.streetName_label);
    addressPanel.add(this.city_label);
    addressPanel.add(this.state_label);
    addressPanel.add(this.zipcode_label);
    addressPanel.add(this.country_label);
    addressPanel.add(this.streetName1_textfield);
    addressPanel.add(this.streetName2_textfield);
    addressPanel.add(this.city_textfield);
    addressPanel.add(this.state_textfield);
    addressPanel.add(this.zipcode_textfield);
    addressPanel.add(this.country_textfield);
    return addressPanel;
  }

 /**
  * <p>Left patient panel</p>
  */
  private JPanel constructLeftPatientPanel() {
    JPanel leftPatientPanel = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    leftPatientPanel.setLayout(gridbag);
//    leftPatientPanel.setBackground(Color.pink);
    GridBagConstraints constraints = new GridBagConstraints();

    firstName_label.setText("First Name");
    lastName_label.setText("Last Name");
    middleName_label.setText("Middle Name");
    gender_label.setText("Gender");
    dateOfBirth_label.setText("Date of Birth");
    ssn_label.setText("Social Security");
    phone_label.setText("Phone");
    email_label.setText("Email");
    constraints.anchor = constraints.NORTHWEST;
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.insets.bottom=10;
    constraints.insets.right=10;

    gridbag.setConstraints(firstName_label, constraints);
    constraints.gridy = 1;
    gridbag.setConstraints(lastName_label, constraints);
    constraints.gridy = 2;
    gridbag.setConstraints(middleName_label, constraints);
    constraints.gridy = 3;
    gridbag.setConstraints(gender_label, constraints);
    constraints.gridy = 4;
    gridbag.setConstraints(dateOfBirth_label, constraints);
    constraints.gridy = 5;
    gridbag.setConstraints(ssn_label, constraints);
    constraints.gridy = 6;
    gridbag.setConstraints(phone_label, constraints);
    constraints.gridy = 7;
    gridbag.setConstraints(email_label, constraints);
    constraints.gridx = 1;
    constraints.gridy = 0;
    gridbag.setConstraints(firstName_textfield, constraints);
    constraints.gridy = 1;
    gridbag.setConstraints(lastName_textfield, constraints);
    constraints.gridy = 2;
    gridbag.setConstraints(middleName_textfield, constraints);
    constraints.gridy = 3;
    gridbag.setConstraints(gender_textfield, constraints);
    constraints.gridy = 4;
    gridbag.setConstraints(dateOfBirth_textfield, constraints);
    constraints.gridy = 5;
    ssn_textfield.setToolTipText("Social security number must be 9 digits");
    gridbag.setConstraints(ssn_textfield, constraints);
    constraints.gridy = 6;
    gridbag.setConstraints(phone_textfield, constraints);
    constraints.gridy = 7;
    gridbag.setConstraints(email_textfield, constraints);

    leftPatientPanel.add(firstName_label);
    leftPatientPanel.add(lastName_label);
    leftPatientPanel.add(middleName_label);
    leftPatientPanel.add(gender_label);
    leftPatientPanel.add(dateOfBirth_label);
    leftPatientPanel.add(ssn_label);
    leftPatientPanel.add(phone_label);
    leftPatientPanel.add(email_label);
    leftPatientPanel.add(firstName_textfield);
    leftPatientPanel.add(lastName_textfield);
    leftPatientPanel.add(middleName_textfield);
    leftPatientPanel.add(gender_textfield);
    leftPatientPanel.add(dateOfBirth_textfield);
    leftPatientPanel.add(ssn_textfield);
    leftPatientPanel.add(phone_textfield);
    leftPatientPanel.add(email_textfield);

    return leftPatientPanel;
  }

 /**
  * <p>JPanel containing all the panels.</p>
  */
  private JPanel constructPatientProfile() {
    JPanel patientProfile = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    patientProfile.setLayout(gridbag);
//    patientProfile.setBackground(Color.cyan);
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = constraints.NORTH;
    constraints.gridy = 3;

    JPanel leftPatientPanel = this.constructLeftPatientPanel();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 3;
    gridbag.setConstraints(leftPatientPanel, constraints);

    JPanel addressPanel = this.constructAddressPanel();
    constraints.gridy = 0;
    constraints.gridx = 3;
    constraints.gridwidth = 2;
    gridbag.setConstraints(addressPanel, constraints);
    patientProfile.add(leftPatientPanel);
    patientProfile.add(addressPanel);

    constraints.gridx = 4;
    constraints.gridy = 11;
    constraints.gridwidth = 1;
    constraints.insets.top = 20;
    gridbag.setConstraints(this.saveButton, constraints);
    this.saveButton.setFont(new java.awt.Font("Dialog", 1, 12));
    this.saveButton.setEnabled(false);

    patientProfile.add(this.saveButton);

    this.saveButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        saveButton_actionPerformed(e);
      }
    });

    return patientProfile;
  }

 /**
  * <p>Search panel.  Enter SSN.</p>
  */
  private JPanel constructSearchPanel() {
    JPanel searchPanel = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    searchPanel.setLayout(gridbag);
//    searchPanel.setBackground(Color.green);
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = constraints.WEST;

    JPanel wsdlPanel = new JPanel();
    wsdlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    WSDLTextField.setText(wsdl);
    WSDLTextField.setFont(new java.awt.Font("Dialog", 0, 10));
    WSDLLabel.setFont(new java.awt.Font("Dialog", 0, 10));
    WSDLLabel.setText("wsdl location");
    wsdlPanel.add(this.WSDLLabel);
    wsdlPanel.add(this.WSDLTextField);

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    gridbag.setConstraints(wsdlPanel, constraints);
    searchPanel.add(wsdlPanel);


    constraints.gridwidth = 1;
    JPanel middlePanel = new JPanel();
//    middlePanel.setBackground(Color.green);
    GridBagLayout gridbag2 = new GridBagLayout();
    middlePanel.setLayout(gridbag2);
    promptLabel.setFont(new java.awt.Font("Dialog", 1, 14));
    promptLabel.setText("Enter Patient SSN");
    this.patientIDTextField.setColumns(9);
    this.patientIDTextField.setFont(new java.awt.Font("Dialog", 1, 14));
    constraints.insets.right = 10;
    gridbag2.setConstraints(promptLabel, constraints);
    constraints.gridx = 1;
    gridbag2.setConstraints(patientIDTextField, constraints);
    middlePanel.add(this.promptLabel);
    middlePanel.add(this.patientIDTextField);

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.weighty = 1;
    gridbag.setConstraints(middlePanel, constraints);
    searchPanel.add(middlePanel);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());
  //  bottomPanel.setBackground(Color.red);
    submitButton.setFont(new java.awt.Font("Dialog", 1, 12));
    submitButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        submitButton_actionPerformed(e);
      }
    });
    bottomPanel.add(this.submitButton);
    constraints.gridx = 1;
    gridbag.setConstraints(bottomPanel, constraints);
    searchPanel.add(bottomPanel);
    return searchPanel;
  }

 /**
  * <p>Initialize contentPane</p>
  */
  private void init() {
    this.setTitle("Medical Records Swing Client");
    //this.patientIDTextField
    Container contentPane = getContentPane();
    JPanel medrecPane = new JPanel();
    JPanel searchPanel = this.constructSearchPanel();
    JPanel patientPanel = this.constructPatientProfile();

    GridBagLayout gridbag = new GridBagLayout();
    medrecPane.setLayout(gridbag);

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = constraints.WEST;
    constraints.gridx = 0;
    constraints.gridy = 0;
    gridbag.setConstraints(searchPanel, constraints);
    constraints.gridy = 1;
    constraints.insets.top = 20;
    gridbag.setConstraints(patientPanel, constraints);

    medrecPane.add(searchPanel);
    medrecPane.add(patientPanel);
    contentPane.add(medrecPane);
    this.patientIDTextField.setDocument(new JTextFieldLimit(9));
    firstName_textfield.setDocument(new JTextFieldLimit(60));
    lastName_textfield.setDocument(new JTextFieldLimit(60));
    middleName_textfield.setDocument(new JTextFieldLimit(60));
    gender_textfield.setDocument(new JTextFieldLimit(6));
    dateOfBirth_textfield.setDocument(new JTextFieldLimit(10));
    ssn_textfield.setDocument(new JTextFieldLimit(9));
    phone_textfield.setDocument(new JTextFieldLimit(15));
    email_textfield.setDocument(new JTextFieldLimit(45));
    email_textfield.setEditable(false);
    streetName1_textfield.setDocument(new JTextFieldLimit(60));
    streetName2_textfield.setDocument(new JTextFieldLimit(60));
    city_textfield.setDocument(new JTextFieldLimit(40));
    state_textfield.setDocument(new JTextFieldLimit(40));
    zipcode_textfield.setDocument(new JTextFieldLimit(10));
    country_textfield.setDocument(new JTextFieldLimit(40));

    this.webServicesInit();
  }

 /**
  * <p></p>
  */
  private void webServicesInit() {
    // Setup the global JAXM message factory
    System.setProperty("javax.xml.soap.MessageFactory",
      "weblogic.webservice.core.soap.MessageFactoryImpl");

    // Setup the global JAX-RPC service factory
    System.setProperty( "javax.xml.rpc.ServiceFactory",
      "weblogic.webservice.core.rpc.ServiceFactoryImpl");
  }

  /**
   * <p> Validate Patient ID </p>
   */
  boolean validatePatientID() {
    return (this.patientIDTextField.getText().length() == 9);
  }

 /**
  * <p>Handle submit button</p>
  */
  void submitButton_actionPerformed(ActionEvent e) {
    this.resetLabels();
    this.resetTextFields();
    if (!validatePatientID()) {
      JOptionPane.showMessageDialog(this, "Please enter a 9 digit Patient ID",
          "Entry Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    try {
      MedRecWebServices ws =
          new MedRecWebServices_Impl(this.WSDLTextField.getText());

      MedRecWebServicesPort port  = ws.getMedRecWebServicesPort();
      PatientWS patientWS =
          (PatientWS)port.findPatientBySsn(this.patientIDTextField.getText());
      if (patientWS != null) {
        this.saveButton.setEnabled(true);
        this.thePatient = patientWS;
        this.mapPatientProfile();
        this.patientIDTextField.setText("");
      } else {
        JOptionPane.showMessageDialog(this, "Invalid Patient ID.",
            "Search Error", JOptionPane.ERROR_MESSAGE);
      }
    } catch (weblogic.webservice.tools.wsdlp.WSDLParseException ce) {
      JOptionPane.showMessageDialog(this, "Could not connect to "+wsdl,
          "Connection Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * <p> Reset Fields </p>
   */
  private void resetLabels() {
    this.firstName_label.setForeground(Color.black);
    this.lastName_label.setForeground(Color.black);
    this.middleName_label.setForeground(Color.black);
    this.gender_label.setForeground(Color.black);
    this.dateOfBirth_label.setForeground(Color.black);
    this.ssn_label.setForeground(Color.black);
    this.phone_label.setForeground(Color.black);
    this.email_label.setForeground(Color.black);
    this.streetName_label.setForeground(Color.black);
    this.city_label.setForeground(Color.black);
    this.state_label.setForeground(Color.black);
    this.zipcode_label.setForeground(Color.black);
    this.country_label.setForeground(Color.black);
  }

  /**
   * <p> Reset TextFields </p>
   */
  private void resetTextFields() {
    this.firstName_textfield.setText("");
    this.lastName_textfield.setText("");
    this.middleName_textfield.setText("");
    this.gender_textfield.setText("");
    this.dateOfBirth_textfield.setText("");
    this.ssn_textfield.setText("");
    this.phone_textfield.setText("");
    this.email_textfield.setText("");
    this.streetName1_textfield.setText("");
    this.streetName2_textfield.setText("");
    this.city_textfield.setText("");
    this.state_textfield.setText("");
    this.zipcode_textfield.setText("");
    this.country_textfield.setText("");
  }

  /**
   * <p> Validate fields </p>
   *
   */
  private boolean validateFields() {
    boolean result = true;
    if (this.firstName_textfield.getText().length() == 0) {
      this.firstName_label.setForeground(Color.red);
      result = false;
    }
    if (this.lastName_textfield.getText().length() == 0) {
      this.lastName_label.setForeground(Color.red);
      result = false;
    }
    if (this.gender_textfield.getText().length() == 0) {
      this.gender_label.setForeground(Color.red);
      result = false;
    }
    if (!Utils.isValidDate(this.dateOfBirth_textfield.getText())) {
      this.dateOfBirth_label.setForeground(Color.red);
      result = false;
    }
    if (this.ssn_textfield.getText().length() != 9) {
      this.ssn_label.setForeground(Color.red);
      result = false;
    }
    if (this.phone_textfield.getText().length() == 0) {
      this.phone_label.setForeground(Color.red);
      result = false;
    }
    if (this.email_textfield.getText().length() == 0) {
      this.email_label.setForeground(Color.red);
      result = false;
    }
    if (this.streetName1_textfield.getText().length() == 0) {
      this.streetName_label.setForeground(Color.red);
      result = false;
    }
    if (this.city_textfield.getText().length() == 0) {
      this.city_label.setForeground(Color.red);
      result = false;
    }
    if (this.state_textfield.getText().length() == 0) {
      this.state_label.setForeground(Color.red);
      result = false;
    }
    if (this.zipcode_textfield.getText().length() == 0) {
      this.zipcode_label.setForeground(Color.red);
      result = false;
    }

    return result;
  }


  /**
   * <p>Handle save button</p>
   */
  void saveButton_actionPerformed(ActionEvent e) {
    resetLabels();
    if (!validateFields()) {
      //Print error message.
      JOptionPane.showMessageDialog(this, "The following fields are required or have errors that need to be corrected.",
          "Entry Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    resetLabels();

    try {
      MedRecWebServices ws =
          new MedRecWebServices_Impl(this.WSDLTextField.getText());

      MedRecWebServicesPort port  = ws.getMedRecWebServicesPort();

      Address address = new Address(this.thePatient.getAddressWS().getId(),
                                    this.streetName1_textfield.getText(),
                                    this.streetName2_textfield.getText(),
                                    this.city_textfield.getText(),
                                    this.state_textfield.getText(),
                                    this.zipcode_textfield.getText(),
                                    this.country_textfield.getText());

      Calendar cal = Utils.str2Calendar(this.dateOfBirth_textfield.getText());
      Patient patient = new Patient(this.thePatient.getId(),
                                    this.firstName_textfield.getText(),
                                    this.middleName_textfield.getText(),
                                    this.lastName_textfield.getText(),
                                    cal,
                                    this.gender_textfield.getText(),
                                    this.ssn_textfield.getText(),
                                    this.phone_textfield.getText(),
                                    this.email_textfield.getText(),
                                    address);

      PatientWS patientWS = Utils.toPatientWS(patient);
      port.updatePatient(patientWS);
      this.resetTextFields();
      this.patientIDTextField.requestFocus();
    } catch (weblogic.webservice.tools.wsdlp.WSDLParseException ce) {
      JOptionPane.showMessageDialog(this, "Could not connect to "+wsdl,
          "Connection Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
