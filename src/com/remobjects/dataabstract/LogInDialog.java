package com.remobjects.dataabstract;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
public class LogInDialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField ; 
	private JPasswordField passwordField;
	//variable which we will check to see if the user dismissed the dialog with the OK or Cancel buttons
	private boolean logInCancelled = true;
	//variable that we'll use to keep track of whether the user is logged in or not
	private boolean isLoggedIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LogInDialog dialog = new LogInDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LogInDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField("User name");
		textField.setBounds(139, 65, 96, 19);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField("Password");
		passwordField.setBounds(139, 106, 96, 19);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				//add ActionEventListeners to the OK and Cancel buttons so we can detect when the button has been selected
				okButton.addActionListener(actionEvent -> {
				    this.dispose();
				    logInCancelled = false;
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				//user press the Cancel button, we simply dispose of the dialog
				cancelButton.addActionListener(actionEvent -> {
				    this.dispose();
				});
			}
		}
		setModal(true);

	}
	//helper method to return the value of loginCancelled
	public boolean getLoginCancelled() {
		return this.logInCancelled;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		System.out.println("username: " + textField.getText().trim());
		return textField.getText().trim();
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return new String(passwordField.getPassword());
	}

		
	}
	

	
