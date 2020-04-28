package com.remobjects.dataabstract.swing;

import java.awt.EventQueue;
import com.remobjects.dataabstract.LogInDialog;
import com.remobjects.dataabstract.DataModule;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ToDoApp {

	private JFrame frame;
	private JTextField textFieldFilter;
	private JTable table;
	private boolean isLoggedIn;
	JButton btnLoadData = new JButton("Load Data");
	private DataModule dataModule;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToDoApp window = new ToDoApp();
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
	public ToDoApp() {
		initialize();
		this.initComponents();
		this.initListeners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//JButton btnLoadData = new JButton("Load Data");
		btnLoadData.setBounds(10, 10, 85, 21);
		frame.getContentPane().add(btnLoadData);
		
		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.setBounds(95, 10, 85, 21);
		frame.getContentPane().add(btnAddTask);
		
		JButton btnEditTask = new JButton("Edit Task");
		btnEditTask.setBounds(180, 10, 85, 21);
		frame.getContentPane().add(btnEditTask);
		
		JButton btnDeleteTask = new JButton("Delete Task");
		btnDeleteTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeleteTask.setBounds(261, 10, 85, 21);
		frame.getContentPane().add(btnDeleteTask);
		
		JButton btnApplyUpdates = new JButton("Apply Updates");
		btnApplyUpdates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApplyUpdates.setBounds(341, 10, 85, 21);
		frame.getContentPane().add(btnApplyUpdates);
		
		textFieldFilter = new JTextField();
		textFieldFilter.setBounds(10, 41, 336, 19);
		frame.getContentPane().add(textFieldFilter);
		textFieldFilter.setColumns(10);
		
		JButton btnApplyFilter = new JButton("Apply Filter");
		btnApplyFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApplyFilter.setBounds(341, 40, 85, 21);
		frame.getContentPane().add(btnApplyFilter);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 416, 177);
		frame.getContentPane().add(scrollPane);
		
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		}
	private void initListeners() {
		this.btnLoadData.addActionListener(actionEvent -> 
		{
		    if (!this.isLoggedIn) {
		        LogInDialog loginDialog = new LogInDialog();
		        loginDialog.setVisible(true);
		        
		        if (!loginDialog.getLoginCancelled()) {
		            if (this.dataModule.login(loginDialog.getUsername(), loginDialog.getPassword())) {
		                this.isLoggedIn = true;
		            } else {
		                JOptionPane.showMessageDialog(loginDialog, this, "Error Logging in, please try again", 0);
		                return;
		            }
		        } else {
		            // Login Canceled
		            return;
		        }
		    }

		    this.dataModule.loadData();
		});
		
	}
	private void initComponents() {
	    this.dataModule = new DataModule();
	    //this.table.setModel(this.dataModule.getTasksDataTableModel());
	}
	
}
