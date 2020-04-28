package com.remobjects.dataabstract;

import com.remobjects.dataabstract.data.DataTable;
import com.remobjects.dataabstract.swing.DataTableModel;
//import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;
import com.remobjects.dataabstract.*;
import java.net.*;
public class DataModule {
	
	//The RemoteDataAdapter handles the actual interactions with the server
	private RemoteDataAdapter dataAdapter;
	//The DataTable class represent the data that has been retrieved from the server
	private DataTable tasksTable;
	private DataTable prioritiesTable;
	//The DataTableModel class is a concrete implementation of Java's Swing class javax.swing.table.AbstractTableModel.
	//It acts as the bridge between an instance of Java's JTable class and the DataTable class.
	private DataTableModel tasksTableModel;
	private DataTableModel prioritiesTableModel;
	
	public DataModule() {
		initComponents();
		initTables();
	}
	
	//The argument to create is the URI of an instance of Relativity Server. In this case to an instance running on the local machine.
	public void initComponents() {
		URI uri = null;
		try {
			uri = new URI("http://localhost:7100/bin");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dataAdapter = RemoteDataAdapter.create(uri);
	}
	//Here we instantiate two DataTable classes, passing in the name of the table they will match to on the server.
	//We then create a DataTableModel object which takes a DataTable as an argument. 
	//Then finally we add a listener for the TableChangedEvent event, which is sent when the data or structure of the table is changed.
	public void initTables() {
		this.tasksTable = new DataTable("Tasks");
		this.tasksTableModel = new DataTableModel(this.tasksTable);
		this.tasksTable.addTableDataChangedListener(tasksTableModel);
		
		this.prioritiesTable = new DataTable("Priorities");
		this.prioritiesTableModel = new DataTableModel(this.prioritiesTable);
		this.prioritiesTable.addTableDataChangedListener(prioritiesTableModel);
		
	}
	
	//add a helper method which returns the DataTableModel for the Tasks table, we need this to pass to the JTable
	public DataTableModel getTasksDataTableModel() {
		return this.tasksTableModel;
		
	}
	//create the login method which takes two String arguments which are uses to create a new String that is passed to the RemoteDataAdapter.
	public boolean login(String username, String password) {
	    String loginString = String.format("User=%s;Password=%s;Domain=%s;Schema=%s", 
	            username, password, "ToDoList", "Tasks");
	    System.out.println("loginString: " + loginString);
	    return this.dataAdapter.login(loginString);
	}
	//
	public void loadData() {
	    DataTable[] tables = {this.tasksTable, this.prioritiesTable};
	    
	    this.tasksTable.clear();
	    this.tasksTable.getColumns().clear();
	    
	   this.dataAdapter.fill(tables);
//	    this.dataAdapter.fillAsync(tables,requestInfo, new FillRequestTask.Callback() {
//	        @Override
//	        public void completed(FillRequestTask aTask, Object aState) {
//	            if (aTask.isCancelled() || aTask.isFailed()) {  
//	                System.out.printf("An error occured: %s\n", aTask.getFailureCause().getMessage());
//	            } else {
//	                System.out.println("success");
//	            }
//	        }
//	    }).execute();
	}
}
	
