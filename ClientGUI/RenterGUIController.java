package ClientGUI;
// TEST TO SEE WHERE i AM at
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



/**
 * Provides data fields and methods to create a Java data-type, representing a GUI controller in a java application.
 *
 * @author Y. Attia
 * @author H. Muhamad
 * @author Y. Khalil
 * @version 1.0
 * @since April 4th, 2019
 */

public class RenterGUIController {
	private RenterGUI mainGui;
	private PrintWriter out;
	public String searchContent;


	
	/**
	 * Creates a gui controller object , which assigns a gui and a print writer to its fields, and creates action listeners to the buttons of the gui.
	 * @param aGUI gui to be assigned to the gui in the controller.
	 * @param out print writer to write into the socket.
	 */
	public RenterGUIController(RenterGUI aGUI, PrintWriter out){
		this.out = out;
		this.mainGui = aGUI;
		searchContent = new String("SEARCH,");
		mainGui.addRentPropertyListener(new RentPropertyListener());
		mainGui.addSearchPropertyByTypeListener(new SearchPropertyByTypeListener());
		mainGui.addSearchByBedRoomNoListener(new SearchByBedRoomNoListener());
		mainGui.addSearchByBathRoomNoListener(new SearchByBathRoomNoListener());
		mainGui.addSearchByFurnishedStateListener(new SearchByFurnishedStateListener());
		mainGui.addsearchByCityQuadrantListener(new SearchByCityQuadrantListener());
		mainGui.addExitListener(new ExitListener());
		mainGui.addSearchPropertyListener(new SearchPropertiesListener() );
		mainGui.addSendEmailsListener(new SendEmail());


		out.println(" ");
	}
	public static void main(String[] args) {

		
	}
	/**
	 * Provides data fields and methods to create a Java data-type, representing a listener for a button in a java application that is an inner class of the GUIController class.
	 *
	 * @author Y. Attia
	 * @author H. Muhamad
	 * @author Y. Khalil
	 * @version 1.0
	 * @since April 4th, 2019
	 */
	class RentPropertyListener implements ActionListener{
		/**
		 * Creates a text field that takes input from user and prints it out to the socket with a unique indicator.
		 */
		@Override
		public void actionPerformed(ActionEvent e1) {
			Object[] message = {"Property selected: ",mainGui.getTextArea().getSelectedText()};
			int option = JOptionPane.showConfirmDialog(null, message,"Property to rent",JOptionPane.OK_CANCEL_OPTION);
			
			if(option == JOptionPane.OK_OPTION)
			{
				out.println("RENT");
			}	
	}
}
	
	
	class SendEmail implements ActionListener{
		/**
		 * Creates a text field that takes input from user and prints it out to the socket with a unique indicator.
		 */
		@Override
		public void actionPerformed(ActionEvent e1) {
			 JPanel middlePanel = new JPanel ();
			JFrame frame = new JFrame("POST PROPERTY");
	       
			
	        JTextField propertyNumber = new JTextField(10);
	        JTextArea Email = new JTextArea(5,20);
	        JScrollPane scroll = new JScrollPane(Email);
	        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

			middlePanel.add(scroll);	
			frame.add(middlePanel);
			Object[] message =  {"propertyNum:", propertyNumber, "Email:", Email, scroll};
			int option = JOptionPane.showConfirmDialog(null, message,"Property to rent",JOptionPane.OK_CANCEL_OPTION);
			
			if(option == JOptionPane.OK_OPTION)
			{
				out.println("EMAIL;"+propertyNumber.getText()+";"+Email.getText());
			}	
	}
}
	
	
	
	
	 String PropertyType="*,";

	class SearchPropertyByTypeListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			if(((JSpinner)e.getSource()).getValue().toString() == "none") {
				PropertyType= "*,";
			}else
				PropertyType= ((JSpinner)e.getSource()).getValue().toString() + ",";
		}
	}
	static String BedRoomNom="*,";

	class SearchByBedRoomNoListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			if(((JSpinner)e.getSource()).getValue().toString() == "0") {
				BedRoomNom= "*,";
			}else
				BedRoomNom= ((JSpinner)e.getSource()).getValue().toString() + ",";

	}
	}
	static String BathRoomNom="*,";
	class SearchByBathRoomNoListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			if(((JSpinner)e.getSource()).getValue().toString() == "0") {
				BathRoomNom= "*,";
			}else
			BathRoomNom= ((JSpinner)e.getSource()).getValue().toString() + ",";
			}	
		}
	
	static String FurnishedState="*,";

	class SearchByFurnishedStateListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			if(((JSpinner)e.getSource()).getValue().toString() == "none") {
				FurnishedState= "*,";
			}else
				
				FurnishedState= ((JSpinner)e.getSource()).getValue().toString() + ",";
			}
		}
	static String CityQuad="*,";

	class SearchByCityQuadrantListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			if(((JSpinner)e.getSource()).getValue().toString() == "none") {
				CityQuad= "*";
			}
			else
				CityQuad= ((JSpinner)e.getSource()).getValue().toString();
		}
	}
	
	
	
	
	class SearchPropertiesListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			out.println("SEARCH,"+PropertyType+BedRoomNom+BathRoomNom+FurnishedState+CityQuad);
			searchContent = "";
		}
		
	}

	class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			out.println("QUIT");
			System.exit(0);
	}
	}
	/**
	 * prints out a message on a joption pane window.
	 * @param message
	 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	/**
	 * printing out the string passed to the text area of the gui.
	 * @param text
	 */
	public void printOnTextArea(String text) {
		mainGui.clear();
		mainGui.getTextArea().setText(text);
		
	}
	
	//setting text area to visible.
	public void setVisible() {
		this.mainGui.getFrame().setVisible(true);
		
	}
	}
