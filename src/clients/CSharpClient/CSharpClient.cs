using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;

namespace CSharpClient
{
	/// <summary>
	/// Summary description for WindowsClient.
	/// </summary>
	public class WindowsClient : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Label wsdlLabel;
		private System.Windows.Forms.TextBox wsdlTextBox;
		private System.Windows.Forms.Label SSNEntryLabel;
		private System.Windows.Forms.TextBox SSNEntryTextBox;
		private System.Windows.Forms.Button submitButton;
		private System.Windows.Forms.Label firstNameLabel;
		private System.Windows.Forms.Label lastNameLabel;
		private System.Windows.Forms.Label genderLabel;
		private System.Windows.Forms.Label middleNameLabel;
		private System.Windows.Forms.Label DateOfBirthLabel;
		private System.Windows.Forms.Label socialSecurityLabel;
		private System.Windows.Forms.Label phoneLabel;
		private System.Windows.Forms.Label emailLabel;
		private System.Windows.Forms.Label addressLabel;
		private System.Windows.Forms.Label streetNameLabel;
		private System.Windows.Forms.TextBox streetName1TextBox;
		private System.Windows.Forms.TextBox streetName2TextBox;
		private System.Windows.Forms.Label cityLabel;
		private System.Windows.Forms.TextBox cityTextBox;
		private System.Windows.Forms.TextBox stateTextBox;
		private System.Windows.Forms.Label stateLabel;
		private System.Windows.Forms.Label zipCodeLabel;
		private System.Windows.Forms.TextBox firstNameTextBox;
		private System.Windows.Forms.TextBox lastNameTextBox;
		private System.Windows.Forms.TextBox middleNameTextBox;
		private System.Windows.Forms.TextBox genderTextBox;
		private System.Windows.Forms.TextBox dateOfBirthTextBox;
		private System.Windows.Forms.TextBox socialSecurityTextBox;
		private System.Windows.Forms.TextBox phoneTextBox;
		private System.Windows.Forms.TextBox emailTextBox;
		private System.Windows.Forms.TextBox zipCodeTextBox;
		private System.Windows.Forms.TextBox countryTextBox;
		private System.Windows.Forms.Label countryLabel;
		private System.Windows.Forms.Button saveChangesButton;

		private localhost.PatientWS thePatient = null;		
		
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			Application.Run(new WindowsClient());
		}

		public WindowsClient()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if(components != null)
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.wsdlLabel = new System.Windows.Forms.Label();
			this.wsdlTextBox = new System.Windows.Forms.TextBox();
			this.SSNEntryLabel = new System.Windows.Forms.Label();
			this.SSNEntryTextBox = new System.Windows.Forms.TextBox();
			this.submitButton = new System.Windows.Forms.Button();
			this.firstNameLabel = new System.Windows.Forms.Label();
			this.lastNameLabel = new System.Windows.Forms.Label();
			this.genderLabel = new System.Windows.Forms.Label();
			this.middleNameLabel = new System.Windows.Forms.Label();
			this.DateOfBirthLabel = new System.Windows.Forms.Label();
			this.socialSecurityLabel = new System.Windows.Forms.Label();
			this.phoneLabel = new System.Windows.Forms.Label();
			this.emailLabel = new System.Windows.Forms.Label();
			this.firstNameTextBox = new System.Windows.Forms.TextBox();
			this.lastNameTextBox = new System.Windows.Forms.TextBox();
			this.middleNameTextBox = new System.Windows.Forms.TextBox();
			this.genderTextBox = new System.Windows.Forms.TextBox();
			this.dateOfBirthTextBox = new System.Windows.Forms.TextBox();
			this.socialSecurityTextBox = new System.Windows.Forms.TextBox();
			this.phoneTextBox = new System.Windows.Forms.TextBox();
			this.emailTextBox = new System.Windows.Forms.TextBox();
			this.addressLabel = new System.Windows.Forms.Label();
			this.streetNameLabel = new System.Windows.Forms.Label();
			this.streetName1TextBox = new System.Windows.Forms.TextBox();
			this.streetName2TextBox = new System.Windows.Forms.TextBox();
			this.cityLabel = new System.Windows.Forms.Label();
			this.cityTextBox = new System.Windows.Forms.TextBox();
			this.stateTextBox = new System.Windows.Forms.TextBox();
			this.stateLabel = new System.Windows.Forms.Label();
			this.zipCodeTextBox = new System.Windows.Forms.TextBox();
			this.zipCodeLabel = new System.Windows.Forms.Label();
			this.countryTextBox = new System.Windows.Forms.TextBox();
			this.countryLabel = new System.Windows.Forms.Label();
			this.saveChangesButton = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// wsdlLabel
			// 
			this.wsdlLabel.Location = new System.Drawing.Point(32, 16);
			this.wsdlLabel.Name = "wsdlLabel";
			this.wsdlLabel.Size = new System.Drawing.Size(80, 16);
			this.wsdlLabel.TabIndex = 0;
			this.wsdlLabel.Text = "wsdl location";
			this.wsdlLabel.Click += new System.EventHandler(this.label1_Click);
			// 
			// wsdlTextBox
			// 
			this.wsdlTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.wsdlTextBox.Location = new System.Drawing.Point(120, 16);
			this.wsdlTextBox.Name = "wsdlTextBox";
			this.wsdlTextBox.Size = new System.Drawing.Size(336, 20);
			this.wsdlTextBox.TabIndex = 200;
			this.wsdlTextBox.TabStop = false;
			this.wsdlTextBox.Text = "http://localhost:7001/ws_medrec/MedRecWebServices?WSDL";
			this.wsdlTextBox.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
			// 
			// SSNEntryLabel
			// 
			this.SSNEntryLabel.Font = new System.Drawing.Font("Arial", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.SSNEntryLabel.Location = new System.Drawing.Point(32, 56);
			this.SSNEntryLabel.Name = "SSNEntryLabel";
			this.SSNEntryLabel.Size = new System.Drawing.Size(152, 23);
			this.SSNEntryLabel.TabIndex = 2;
			this.SSNEntryLabel.Text = "Enter Patient SSN";
			this.SSNEntryLabel.Click += new System.EventHandler(this.SSNEntryLabel_Click);
			// 
			// SSNEntryTextBox
			// 
			this.SSNEntryTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.SSNEntryTextBox.Location = new System.Drawing.Point(200, 53);
			this.SSNEntryTextBox.MaxLength = 9;
			this.SSNEntryTextBox.Name = "SSNEntryTextBox";
			this.SSNEntryTextBox.Size = new System.Drawing.Size(120, 29);
			this.SSNEntryTextBox.TabIndex = 1;
			this.SSNEntryTextBox.Text = "";
			// 
			// submitButton
			// 
			this.submitButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.submitButton.Location = new System.Drawing.Point(376, 55);
			this.submitButton.Name = "submitButton";
			this.submitButton.Size = new System.Drawing.Size(80, 24);
			this.submitButton.TabIndex = 4;
			this.submitButton.Text = "Submit";
			this.submitButton.Click += new System.EventHandler(this.submitButton_Click);
			// 
			// firstNameLabel
			// 
			this.firstNameLabel.Location = new System.Drawing.Point(32, 112);
			this.firstNameLabel.Name = "firstNameLabel";
			this.firstNameLabel.Size = new System.Drawing.Size(80, 16);
			this.firstNameLabel.TabIndex = 5;
			this.firstNameLabel.Text = "First Name";
			// 
			// lastNameLabel
			// 
			this.lastNameLabel.Location = new System.Drawing.Point(32, 140);
			this.lastNameLabel.Name = "lastNameLabel";
			this.lastNameLabel.Size = new System.Drawing.Size(80, 16);
			this.lastNameLabel.TabIndex = 6;
			this.lastNameLabel.Text = "Last Name";
			// 
			// genderLabel
			// 
			this.genderLabel.Location = new System.Drawing.Point(32, 196);
			this.genderLabel.Name = "genderLabel";
			this.genderLabel.Size = new System.Drawing.Size(80, 16);
			this.genderLabel.TabIndex = 8;
			this.genderLabel.Text = "Gender";
			// 
			// middleNameLabel
			// 
			this.middleNameLabel.Location = new System.Drawing.Point(32, 168);
			this.middleNameLabel.Name = "middleNameLabel";
			this.middleNameLabel.Size = new System.Drawing.Size(80, 16);
			this.middleNameLabel.TabIndex = 7;
			this.middleNameLabel.Text = "Middle Name";
			// 
			// DateOfBirthLabel
			// 
			this.DateOfBirthLabel.Location = new System.Drawing.Point(32, 224);
			this.DateOfBirthLabel.Name = "DateOfBirthLabel";
			this.DateOfBirthLabel.Size = new System.Drawing.Size(80, 16);
			this.DateOfBirthLabel.TabIndex = 9;
			this.DateOfBirthLabel.Text = "Date of Birth";
			// 
			// socialSecurityLabel
			// 
			this.socialSecurityLabel.Location = new System.Drawing.Point(32, 252);
			this.socialSecurityLabel.Name = "socialSecurityLabel";
			this.socialSecurityLabel.Size = new System.Drawing.Size(80, 16);
			this.socialSecurityLabel.TabIndex = 10;
			this.socialSecurityLabel.Text = "Social Security";
			// 
			// phoneLabel
			// 
			this.phoneLabel.Location = new System.Drawing.Point(32, 280);
			this.phoneLabel.Name = "phoneLabel";
			this.phoneLabel.Size = new System.Drawing.Size(80, 16);
			this.phoneLabel.TabIndex = 11;
			this.phoneLabel.Text = "Phone";
			// 
			// emailLabel
			// 
			this.emailLabel.Location = new System.Drawing.Point(32, 308);
			this.emailLabel.Name = "emailLabel";
			this.emailLabel.Size = new System.Drawing.Size(80, 16);
			this.emailLabel.TabIndex = 12;
			this.emailLabel.Text = "Email";
			// 
			// firstNameTextBox
			// 
			this.firstNameTextBox.Location = new System.Drawing.Point(120, 110);
			this.firstNameTextBox.MaxLength = 40;
			this.firstNameTextBox.Name = "firstNameTextBox";
			this.firstNameTextBox.Size = new System.Drawing.Size(120, 20);
			this.firstNameTextBox.TabIndex = 13;
			this.firstNameTextBox.Text = "";
			this.firstNameTextBox.TextChanged += new System.EventHandler(this.firstNameTextBox_TextChanged);
			// 
			// lastNameTextBox
			// 
			this.lastNameTextBox.Location = new System.Drawing.Point(120, 138);
			this.lastNameTextBox.MaxLength = 40;
			this.lastNameTextBox.Name = "lastNameTextBox";
			this.lastNameTextBox.Size = new System.Drawing.Size(120, 20);
			this.lastNameTextBox.TabIndex = 14;
			this.lastNameTextBox.Text = "";
			this.lastNameTextBox.TextChanged += new System.EventHandler(this.lastNameTextBox_TextChanged);
			// 
			// middleNameTextBox
			// 
			this.middleNameTextBox.Location = new System.Drawing.Point(120, 166);
			this.middleNameTextBox.MaxLength = 40;
			this.middleNameTextBox.Name = "middleNameTextBox";
			this.middleNameTextBox.Size = new System.Drawing.Size(120, 20);
			this.middleNameTextBox.TabIndex = 15;
			this.middleNameTextBox.Text = "";
			this.middleNameTextBox.TextChanged += new System.EventHandler(this.middleNameTextBox_TextChanged);
			// 
			// genderTextBox
			// 
			this.genderTextBox.Location = new System.Drawing.Point(120, 194);
			this.genderTextBox.MaxLength = 6;
			this.genderTextBox.Name = "genderTextBox";
			this.genderTextBox.Size = new System.Drawing.Size(48, 20);
			this.genderTextBox.TabIndex = 16;
			this.genderTextBox.Text = "";
			this.genderTextBox.TextChanged += new System.EventHandler(this.genderTextBox_TextChanged);
			// 
			// dateOfBirthTextBox
			// 
			this.dateOfBirthTextBox.Location = new System.Drawing.Point(120, 222);
			this.dateOfBirthTextBox.MaxLength = 10;
			this.dateOfBirthTextBox.Name = "dateOfBirthTextBox";
			this.dateOfBirthTextBox.Size = new System.Drawing.Size(88, 20);
			this.dateOfBirthTextBox.TabIndex = 17;
			this.dateOfBirthTextBox.Text = "";
			this.dateOfBirthTextBox.TextChanged += new System.EventHandler(this.dateOfBirthTextBox_TextChanged);
			// 
			// socialSecurityTextBox
			// 
			this.socialSecurityTextBox.Location = new System.Drawing.Point(120, 250);
			this.socialSecurityTextBox.MaxLength = 9;
			this.socialSecurityTextBox.Name = "socialSecurityTextBox";
			this.socialSecurityTextBox.Size = new System.Drawing.Size(88, 20);
			this.socialSecurityTextBox.TabIndex = 18;
			this.socialSecurityTextBox.Text = "";
			this.socialSecurityTextBox.TextChanged += new System.EventHandler(this.socialSecurityTextBox_TextChanged);
			// 
			// phoneTextBox
			// 
			this.phoneTextBox.Location = new System.Drawing.Point(120, 278);
			this.phoneTextBox.MaxLength = 12;
			this.phoneTextBox.Name = "phoneTextBox";
			this.phoneTextBox.TabIndex = 19;
			this.phoneTextBox.Text = "";
			this.phoneTextBox.TextChanged += new System.EventHandler(this.phoneTextBox_TextChanged);
			// 
			// emailTextBox
			// 
			this.emailTextBox.Location = new System.Drawing.Point(120, 306);
			this.emailTextBox.MaxLength = 60;
			this.emailTextBox.Name = "emailTextBox";
			this.emailTextBox.ReadOnly = true;
			this.emailTextBox.Size = new System.Drawing.Size(176, 20);
			this.emailTextBox.TabIndex = 20;
			this.emailTextBox.Text = "";
			this.emailTextBox.TextChanged += new System.EventHandler(this.emailTextBox_TextChanged);
			// 
			// addressLabel
			// 
			this.addressLabel.Location = new System.Drawing.Point(320, 112);
			this.addressLabel.Name = "addressLabel";
			this.addressLabel.Size = new System.Drawing.Size(72, 16);
			this.addressLabel.TabIndex = 21;
			this.addressLabel.Text = "Address";
			// 
			// streetNameLabel
			// 
			this.streetNameLabel.Location = new System.Drawing.Point(320, 140);
			this.streetNameLabel.Name = "streetNameLabel";
			this.streetNameLabel.Size = new System.Drawing.Size(72, 16);
			this.streetNameLabel.TabIndex = 22;
			this.streetNameLabel.Text = "Street Name";
			// 
			// streetName1TextBox
			// 
			this.streetName1TextBox.Location = new System.Drawing.Point(400, 138);
			this.streetName1TextBox.MaxLength = 45;
			this.streetName1TextBox.Name = "streetName1TextBox";
			this.streetName1TextBox.Size = new System.Drawing.Size(152, 20);
			this.streetName1TextBox.TabIndex = 23;
			this.streetName1TextBox.Text = "";
			// 
			// streetName2TextBox
			// 
			this.streetName2TextBox.Location = new System.Drawing.Point(400, 166);
			this.streetName2TextBox.MaxLength = 45;
			this.streetName2TextBox.Name = "streetName2TextBox";
			this.streetName2TextBox.Size = new System.Drawing.Size(152, 20);
			this.streetName2TextBox.TabIndex = 24;
			this.streetName2TextBox.Text = "";
			// 
			// cityLabel
			// 
			this.cityLabel.Location = new System.Drawing.Point(320, 196);
			this.cityLabel.Name = "cityLabel";
			this.cityLabel.Size = new System.Drawing.Size(72, 16);
			this.cityLabel.TabIndex = 25;
			this.cityLabel.Text = "City";
			// 
			// cityTextBox
			// 
			this.cityTextBox.Location = new System.Drawing.Point(400, 194);
			this.cityTextBox.MaxLength = 45;
			this.cityTextBox.Name = "cityTextBox";
			this.cityTextBox.Size = new System.Drawing.Size(136, 20);
			this.cityTextBox.TabIndex = 26;
			this.cityTextBox.Text = "";
			// 
			// stateTextBox
			// 
			this.stateTextBox.Location = new System.Drawing.Point(400, 222);
			this.stateTextBox.MaxLength = 2;
			this.stateTextBox.Name = "stateTextBox";
			this.stateTextBox.Size = new System.Drawing.Size(32, 20);
			this.stateTextBox.TabIndex = 28;
			this.stateTextBox.Text = "";
			// 
			// stateLabel
			// 
			this.stateLabel.Location = new System.Drawing.Point(320, 224);
			this.stateLabel.Name = "stateLabel";
			this.stateLabel.Size = new System.Drawing.Size(72, 16);
			this.stateLabel.TabIndex = 27;
			this.stateLabel.Text = "State";
			// 
			// zipCodeTextBox
			// 
			this.zipCodeTextBox.Location = new System.Drawing.Point(400, 250);
			this.zipCodeTextBox.MaxLength = 9;
			this.zipCodeTextBox.Name = "zipCodeTextBox";
			this.zipCodeTextBox.Size = new System.Drawing.Size(64, 20);
			this.zipCodeTextBox.TabIndex = 30;
			this.zipCodeTextBox.Text = "";
			// 
			// zipCodeLabel
			// 
			this.zipCodeLabel.Location = new System.Drawing.Point(320, 252);
			this.zipCodeLabel.Name = "zipCodeLabel";
			this.zipCodeLabel.Size = new System.Drawing.Size(72, 16);
			this.zipCodeLabel.TabIndex = 29;
			this.zipCodeLabel.Text = "ZipCode";
			// 
			// countryTextBox
			// 
			this.countryTextBox.Location = new System.Drawing.Point(400, 278);
			this.countryTextBox.MaxLength = 30;
			this.countryTextBox.Name = "countryTextBox";
			this.countryTextBox.Size = new System.Drawing.Size(136, 20);
			this.countryTextBox.TabIndex = 32;
			this.countryTextBox.Text = "";
			// 
			// countryLabel
			// 
			this.countryLabel.Location = new System.Drawing.Point(320, 280);
			this.countryLabel.Name = "countryLabel";
			this.countryLabel.Size = new System.Drawing.Size(72, 16);
			this.countryLabel.TabIndex = 31;
			this.countryLabel.Text = "Country";
			// 
			// saveChangesButton
			// 
			this.saveChangesButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.saveChangesButton.Location = new System.Drawing.Point(400, 344);
			this.saveChangesButton.Name = "saveChangesButton";
			this.saveChangesButton.Size = new System.Drawing.Size(104, 24);
			this.saveChangesButton.TabIndex = 33;
			this.saveChangesButton.Text = "Save Changes";
			this.saveChangesButton.Click += new System.EventHandler(this.saveChangesButton_Click);
			// 
			// WindowsClient
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(592, 416);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.saveChangesButton,
																		  this.countryTextBox,
																		  this.countryLabel,
																		  this.zipCodeTextBox,
																		  this.zipCodeLabel,
																		  this.stateTextBox,
																		  this.stateLabel,
																		  this.cityTextBox,
																		  this.cityLabel,
																		  this.streetName2TextBox,
																		  this.streetName1TextBox,
																		  this.streetNameLabel,
																		  this.addressLabel,
																		  this.emailTextBox,
																		  this.phoneTextBox,
																		  this.socialSecurityTextBox,
																		  this.dateOfBirthTextBox,
																		  this.genderTextBox,
																		  this.middleNameTextBox,
																		  this.lastNameTextBox,
																		  this.firstNameTextBox,
																		  this.emailLabel,
																		  this.phoneLabel,
																		  this.socialSecurityLabel,
																		  this.DateOfBirthLabel,
																		  this.genderLabel,
																		  this.middleNameLabel,
																		  this.lastNameLabel,
																		  this.firstNameLabel,
																		  this.submitButton,
																		  this.SSNEntryTextBox,
																		  this.SSNEntryLabel,
																		  this.wsdlTextBox,
																		  this.wsdlLabel});
			this.Name = "WindowsClient";
			this.Text = "WindowsClient";
			this.Load += new System.EventHandler(this.WindowsClient_Load);
			this.ResumeLayout(false);

		}
		#endregion

		private void label1_Click(object sender, System.EventArgs e)
		{
		
		}

		private void textBox1_TextChanged(object sender, System.EventArgs e)
		{
		
		}

		private void WindowsClient_Load(object sender, System.EventArgs e)
		{

			}

		private void SSNEntryLabel_Click(object sender, System.EventArgs e)
		{
		
		}

		private void socialSecurityTextBox_TextChanged(object sender, System.EventArgs e)
		{
		
		}

		private void dateOfBirthTextBox_TextChanged(object sender, System.EventArgs e)
		{
		
		}

		private void lastNameTextBox_TextChanged(object sender, System.EventArgs e)
		{
		
		}

		private void genderTextBox_TextChanged(object sender, System.EventArgs e)
		{
		
		}

		private void middleNameTextBox_TextChanged(object sender, System.EventArgs e)
		{
		
		}

		private void emailTextBox_TextChanged(object sender, System.EventArgs e)
		{
		
		}

		private void phoneTextBox_TextChanged(object sender, System.EventArgs e)
		{
		
		}

		private void firstNameTextBox_TextChanged(object sender, System.EventArgs e)
		{
		
		}

		private void mapPatientProfile() 
		{
			this.firstNameTextBox.Text = this.thePatient.firstName;
			this.lastNameTextBox.Text = this.thePatient.lastName;
			this.middleNameTextBox.Text = this.thePatient.middleName;
			this.genderTextBox.Text = this.thePatient.gender;
			this.dateOfBirthTextBox.Text = this.thePatient.dateOfBirth;
			this.socialSecurityTextBox.Text = this.thePatient.ssn;
			this.phoneTextBox.Text = this.thePatient.phone;
			this.emailTextBox.Text = this.thePatient.email;
			localhost.AddressWS address = this.thePatient.addressWS;
			this.streetName1TextBox.Text = address.streetName1;
			this.streetName2TextBox.Text = address.streetName2;
			this.cityTextBox.Text = address.city;
			this.stateTextBox.Text = address.state;
			this.zipCodeTextBox.Text = address.zipCode;
			this.countryTextBox.Text = address.country;
		}

		private bool validateSSNEntry() 
		{
			return (this.SSNEntryTextBox.Text.Length == 9); 
		}

		private void submitButton_Click(object sender, System.EventArgs e)
		{
			this.resetLabels();
			this.resetTextBoxes();
			if (!validateSSNEntry()) 
			{
				MessageBox.Show("Please enter a 9 digit Patient ID", 
					"Entry Error", 
					System.Windows.Forms.MessageBoxButtons.OK, 
					System.Windows.Forms.MessageBoxIcon.Error);
				return;
			}

			try 
			{
				localhost.MedRecWebServices proxy = new localhost.MedRecWebServices();
				proxy.Url = this.wsdlTextBox.Text;

				localhost.PatientWS patient = proxy.findPatientBySsn(this.SSNEntryTextBox.Text);

				if (patient != null) 
				{
					this.thePatient = patient;
					this.mapPatientProfile();
					this.SSNEntryTextBox.ResetText();
				}
				else 
				{
					MessageBox.Show("Invalid Patient ID",  
						"Search Error", 
						System.Windows.Forms.MessageBoxButtons.OK, 
						System.Windows.Forms.MessageBoxIcon.Error);
				}
			} catch (Exception ex) {
				MessageBox.Show(ex.ToString(),  
					"Connection Error", 
					System.Windows.Forms.MessageBoxButtons.OK, 
					System.Windows.Forms.MessageBoxIcon.Error);
			}
		}

		private void resetLabels() 
		{
			this.firstNameLabel.ForeColor = Color.Black;			
			this.lastNameLabel.ForeColor = Color.Black;			
			this.genderLabel.ForeColor = Color.Black;			
			this.DateOfBirthLabel.ForeColor = Color.Black;
			this.socialSecurityLabel.ForeColor = Color.Black;
			this.emailLabel.ForeColor = Color.Black;
			this.phoneLabel.ForeColor = Color.Black;
			this.streetNameLabel.ForeColor = Color.Black;
			this.cityLabel.ForeColor = Color.Black;
			this.stateLabel.ForeColor = Color.Black;
			this.zipCodeLabel.ForeColor = Color.Black;
			this.countryLabel.ForeColor = Color.Black;
		}

		private void resetTextBoxes() 
		{
			this.firstNameTextBox.ResetText();
			this.lastNameTextBox.ResetText();
			this.middleNameTextBox.ResetText();
			this.genderTextBox.ResetText();
			this.dateOfBirthTextBox.ResetText();
			this.socialSecurityTextBox.ResetText();
			this.emailTextBox.ResetText();
			this.phoneTextBox.ResetText();
			this.streetName1TextBox.ResetText();
			this.streetName2TextBox.ResetText();
			this.cityTextBox.ResetText();
			this.stateTextBox.ResetText();
			this.zipCodeTextBox.ResetText();
			this.countryTextBox.ResetText();
		}
	
		private bool validateFields() {
			bool result = true;
			if (this.firstNameTextBox.Text.Length == 0) 
			{
				this.firstNameLabel.ForeColor = Color.Red;
				result=false;
			}
			if (this.lastNameTextBox.Text.Length == 0) 
			{
				this.lastNameLabel.ForeColor = Color.Red;
				result=false;
			}
			if (this.genderTextBox.Text.Length == 0) 
			{
				this.genderLabel.ForeColor = Color.Red;
				result=false;
			}
			if (this.emailTextBox.Text.Length == 0) 
			{
				this.emailLabel.ForeColor = Color.Red;
				result=false;
			}
			if (this.phoneTextBox.Text.Length == 0) 
			{
				this.phoneLabel.ForeColor = Color.Red;
				result=false;
			}
			if (this.socialSecurityTextBox.Text.Length == 0) 
			{
				this.socialSecurityLabel.ForeColor = Color.Red;
				result=false;
			}
			if (this.streetName1TextBox.Text.Length == 0) 
			{
				this.streetNameLabel.ForeColor = Color.Red;
				result=false;
			}
			if (this.cityTextBox.Text.Length == 0) 
			{
				this.cityLabel.ForeColor = Color.Red;
				result=false;
			}
			if (this.stateTextBox.Text.Length == 0) 
			{
				this.stateLabel.ForeColor = Color.Red;
				result=false;
			}
			if (this.zipCodeTextBox.Text.Length == 0) 
			{
				this.zipCodeLabel.ForeColor = Color.Red;
				result=false;
			}
			if (this.countryTextBox.Text.Length == 0) 
			{
				this.countryLabel.ForeColor = Color.Red;
				result=false;
			}
			return result;
		}

		private bool validateDate(string date) 
		{
			try 
			{
				DateTime aDT = new DateTime();
				DateTime.Parse(date);
			} 
			catch (Exception e) 
			{
				this.DateOfBirthLabel.ForeColor = Color.Red;
				return false;
			}
			return true;
		}


		private void saveChangesButton_Click(object sender, System.EventArgs e) {
			this.resetLabels();

			if (!this.validateFields()) 
			{
				MessageBox.Show("Please fill in required fields.",  
					"Entry Error", 
					System.Windows.Forms.MessageBoxButtons.OK, 
					System.Windows.Forms.MessageBoxIcon.Error);
				return;
			}
			if (!this.validateDate(this.dateOfBirthTextBox.Text)) 
			{
				MessageBox.Show("Please use the following date format:  mm/dd/yyyy",  
					"Entry Error", 
					System.Windows.Forms.MessageBoxButtons.OK, 
					System.Windows.Forms.MessageBoxIcon.Error);
				return;
			}
			this.resetLabels();
	
			try {
				localhost.MedRecWebServices proxy = new localhost.MedRecWebServices();
				proxy.Url = this.wsdlTextBox.Text;

				localhost.AddressWS addressWS = new localhost.AddressWS();
				addressWS.streetName1 = this.streetName1TextBox.Text;
				addressWS.streetName2 = this.streetName2TextBox.Text;
				addressWS.city = this.cityTextBox.Text;
				addressWS.state = this.stateTextBox.Text;
				addressWS.zipCode = this.zipCodeTextBox.Text;
				addressWS.country = this.countryTextBox.Text;
				
				localhost.PatientWS patientWS = new localhost.PatientWS();
				patientWS.id = this.thePatient.id;
				patientWS.addressWS = addressWS;
				patientWS.firstName = this.firstNameTextBox.Text;
				patientWS.lastName = this.lastNameTextBox.Text;
				patientWS.middleName = this.middleNameTextBox.Text;
				patientWS.dateOfBirth = this.dateOfBirthTextBox.Text;
				patientWS.gender = this.genderTextBox.Text;
				patientWS.phone = this.phoneTextBox.Text;
				patientWS.email = this.emailTextBox.Text;
				patientWS.ssn = this.socialSecurityTextBox.Text;

				proxy.updatePatient(patientWS);
				this.resetTextBoxes();
				this.SSNEntryTextBox.Focus();
			} catch (Exception ex) {
				MessageBox.Show(ex.ToString(),  
					"Connection Error", 
					System.Windows.Forms.MessageBoxButtons.OK, 
					System.Windows.Forms.MessageBoxIcon.Error);
			}
		}
	}
}
