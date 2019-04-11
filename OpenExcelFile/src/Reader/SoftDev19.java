package Reader;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jxl.read.biff.BiffException;


import javax.swing.JLabel;
import java.util.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Window.Type;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
public class SoftDev19 extends JFrame {
    
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    SoftDev19 frame = new SoftDev19();
		    frame.setVisible(true);
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     * 
     */
    JComboBox cbContinent = new JComboBox();
    JComboBox cbCountry = new JComboBox();
    JComboBox cbCity = new JComboBox();
    JComboBox cbAirport = new JComboBox();
    JRadioButton rdbtnEndDestination = new JRadioButton("End Destination");
    JButton btnAddDstn = new JButton("Add Destination");
    JButton btnRemove = new JButton("Remove All Destinations");
    private final JButton btnCalcDist = new JButton("Calculate Distance");
    String continentSelection;
    public double lat1, lat2, long1, long2;
    String cityChoice;
    String countryChoice;
    String airportChoice;
    String location;
    String[] EuropeCountries = new String[153];
    String[] AfricaCountries = new String[30];
    String[] AsiaCountries = new String[149];
    String[] NorthAmCountries = new String[171];
    String[] OceaniaCountries = new String[12];
    String[] SouthAmCountries = new String[20];
    String[] EuropeCities = new String[153];
    String[] AfricaCities = new String[30];
    String[] AsiaCities = new String[149];
    String[] NorthAmCities = new String[171];
    String[] OceaniaCities = new String[12];
    String[] SouthAmCities = new String[20];
    String[] EuropeAirports = new String[153];
    String[] AfricaAirports = new String[30];
    String[] AsiaAirports = new String[149];
    String[] NorthAmAirports = new String[171];
    String[] OceaniaAirports = new String[12];
    String[] SouthAmAirports = new String[20]; 
    public int[][] distances = new int[8][8];
    int[][] paths = new int[8][5040];
    int[] shortestPath = new int[8];
    double[] lat = new double[8];
    double[] lon = new double[8];
    private JTable table;
    int dstnsAdded = 0;
    public int counter = 0;
    public int shortestDistance = 99999;
    public int currentDistance = 0;
    public int longestDistance = 0;
    public int totalDistance = 0;
    public boolean endDistance = false;
    private JTextField txtDestination1;
    private JTextField txtDestination2;
    private JTextField txtDestination3;
    private JTextField txtDestination4;
    private JTextField txtDestination5;
    private JTextField txtDestination6;
    private JTextField txtDestination7;
    private JTextField txtDestination8;
    private JLabel lblShortestPath;
    private JTextField txtShortestPath;
    private JLabel lblmiles1;
    private JTextField txtPath1;
    private JTextField txtPath2;
    private JTextField txtPath3;
    private JTextField txtPath4;
    private JTextField txtPath5;
    private JTextField txtPath6;
    private JTextField txtPath7;
    private JTextField txtPath8;
    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private JLabel lbl4;
    private JLabel lbl5;
    private JLabel lbl6;
    private JLabel lbl7;
    private JLabel lbl8;
    private JLabel lblTheAveragePath;
    private JTextField txtAveragePath;
    private JLabel lblmiles2;
    private JLabel lblTheLongestPath;
    private JTextField txtLongestPath;
    private JLabel label_2;
    public SoftDev19() {
    	
	ReadExcel test = new ReadExcel();
	test.setInputFile("C:\\Users\\ericw\\Downloads\\airports.xls");
	String[][] airports = test.read();
	distances[0][0] = 0;
	//https://www.codejava.net/java-se/swing/create-custom-gui-for-jcombobox
	//http://www.jtattoo.net/ScreenShots.html
    	setTitle("Aircraft Logistics");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 1551, 1086);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(240, 240, 240));
	contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.scrollbar, SystemColor.controlHighlight, SystemColor.scrollbar, SystemColor.scrollbar));
	setContentPane(contentPane);
	//if(airports[3][2].equals("")) System.out.println("YUPYUP");
	for(int i = 1; i < 30; i++) {
	    if(airports[i][1].equals("Africa")) { //&& !airports[i][2].equals("")) {
		AfricaCountries[i] = airports[i][2];
		AfricaCities[i] = airports[i][3];
		AfricaAirports[i] = airports[i][0];
	    }
	}
	
	for(int i = 30; i < 179; i++) {
	    if(airports[i][1].equals("Asia")) {
		AsiaCountries[i - 30] = airports[i][2];
		AsiaCities[i - 30] = airports[i][3];
		AsiaAirports[i - 30] = airports[i][0];
	    }
	}
	
	for(int i = 179; i < 332; i++) {
	    if(airports[i][1].equals("Europe")) {
		EuropeCountries[i - 179] = airports[i][2];
		EuropeCities[i - 179] = airports[i][3];
		EuropeAirports[i - 179] = airports[i][0];
	    }
	}
	for(int i = 332; i < 503; i++) {
	    if(airports[i][1].equals("North America")) {
		NorthAmCountries[i - 332] = airports[i][2];
		NorthAmCities[i - 332] = airports[i][3];
		NorthAmAirports[i-332] = airports[i][0];
	    }
	}
	for(int i = 503; i < 516; i++) {
	    if(airports[i][1].equals("Oceania")) {
		OceaniaCountries[i - 503] = airports[i][2]; 
		OceaniaCities[i - 503] = airports[i][3];
		OceaniaAirports[i - 503] = airports[i][0];
	    }
	}
	for(int i = 515; i < 535; i++) {
	    if(airports[i][1].equals("South America")) {
		SouthAmCountries[i - 515] = airports[i][2];
		SouthAmCities[i - 515] = airports[i][3];
		SouthAmAirports[i - 515] = airports[i][0];
	    }
	}
	String [] continents = new String[] {"", "Africa", "Asia", "Europe", "North America", "Oceania", "South America"};
	
	//getContentPane().add(cbContinent);
	cbContinent = new JComboBox (continents);
	cbContinent.setFont(new Font("Tahoma", Font.PLAIN, 26));
	cbCountry = new JComboBox(); 
	cbCountry.setFont(new Font("Tahoma", Font.PLAIN, 26));
	cbContinent.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent y) {
		
		cbContinent = (JComboBox) y.getSource();
		continentSelection = (String) cbContinent.getSelectedItem();
		    if(continentSelection.equals("Europe")) {
			cbCountry.removeAllItems();
			cbCountry.addItem(EuropeCountries[0]);
			for(int i = 0; i < EuropeCountries.length; i++) {
			    if(i > 0) {
				if(!EuropeCountries[i].equals(EuropeCountries[i - 1])) {
				    cbCountry.addItem(EuropeCountries[i]);
				}
			    }
			}
		    }
		    if(continentSelection.equals("Asia")) {
			cbCountry.removeAllItems();
			cbCountry.addItem(AsiaCountries[0]);
			for(int i = 0; i < AsiaCountries.length; i++) {
			    if(i > 0) {
				if(!AsiaCountries[i].equals(AsiaCountries[i - 1])) {
				    cbCountry.addItem(AsiaCountries[i]);
				}
			    }
			}
		    }
		    if(continentSelection.equals("Africa")) {
			cbCountry.removeAllItems();
			for(int i = 1; i < AfricaCountries.length; i++) {
			    if(i > 0) {
				if(!AfricaCountries[i].equals(AfricaCountries[i - 1])) {
				    cbCountry.addItem(AfricaCountries[i]);
				}
			    }
			}
		    }
		    if(continentSelection.equals("North America")) {
			cbCountry.removeAllItems();
			cbCountry.addItem(NorthAmCountries[0]);
			for(int i = 0; i < NorthAmCountries.length; i++) {
			    if(i > 0) {
				if(!NorthAmCountries[i].equals(NorthAmCountries[i - 1])) {
				    cbCountry.addItem(NorthAmCountries[i]);
				}
			    }
			}
		    }
		    if(continentSelection.equals("South America")) {
			cbCountry.removeAllItems();
			cbCountry.addItem(SouthAmCountries[0]);
			for(int i = 0; i < SouthAmCountries.length; i++) {
			    if(i > 0) {
				if(!SouthAmCountries[i].equals(SouthAmCountries[i - 1])) {
				    cbCountry.addItem(SouthAmCountries[i]);
				}
			    }
			}
		    }
		    if(continentSelection.equals("Oceania")) {
			cbCountry.removeAllItems();
			cbCountry.addItem(OceaniaCountries[0]);
			for(int i = 0; i < OceaniaCountries.length; i++) {
			    if(i > 0) {
				if(!OceaniaCountries[i].equals(OceaniaCountries[i - 1])) {
				    cbCountry.addItem(OceaniaCountries[i]);
				}
			    } 
			}
		    }
	    }});
	
	cbCountry.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
        	cbCountry = (JComboBox) e.getSource();
        	String countrySelection = (String) cbCountry.getSelectedItem();
        	countryChoice = countrySelection;
        	    cbCity.removeAllItems();
        	    for(int i = 1; i < AfricaCities.length; i++) {
        		if(AfricaCountries[i].equals(countrySelection)) cbCity.addItem(AfricaCities[i]);
        	    }
        	    for(int i = 0; i < AsiaCities.length; i++) {
        		if(AsiaCountries[i].equals(countrySelection)) cbCity.addItem(AsiaCities[i]);
        	    }
        	    for(int i = 0; i < EuropeCities.length; i++) {
        		if(EuropeCountries[i].equals(countrySelection)) cbCity.addItem(EuropeCities[i]);
        	    }
        	    for(int i = 0; i < NorthAmCities.length; i++) {
        		if(NorthAmCountries[i].equals(countrySelection)) cbCity.addItem(NorthAmCities[i]);
        	    }
        	    for(int i = 0; i < OceaniaCities.length; i++) {
        		if(OceaniaCountries[i].equals(countrySelection)) cbCity.addItem(OceaniaCities[i]);
        	    }
        	    for(int i = 0; i < SouthAmCities.length; i++) {
        		if(SouthAmCountries[i].equals(countrySelection)) cbCity.addItem(SouthAmCities[i]);
        	    }
        	
            }});
	cbCity.setFont(new Font("Tahoma", Font.PLAIN, 26));
	cbCity.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
        	cbCity = (JComboBox) e.getSource();
        	String citySelection = (String) cbCity.getSelectedItem();
        	cbAirport.removeAllItems();
        	for(int i = 1; i < AfricaCities.length; i++) {
        	    if(AfricaCities[i].equals(citySelection)) {
        		cbAirport.addItem(AfricaAirports[i]);
        		lat[dstnsAdded] = Double.parseDouble(airports[i][4]);
			lon[dstnsAdded] = Double.parseDouble(airports[i][5]);
        	    }
    	    	}	
    	    	for(int i = 0; i < AsiaCities.length; i++) {
    	    	    if(AsiaCities[i].equals(citySelection)) {
    	    		cbAirport.addItem(AsiaAirports[i]);
    	    		lat[dstnsAdded] = Double.parseDouble(airports[i + 30][4]);
    	    		lon[dstnsAdded] = Double.parseDouble(airports[i + 30][5]);
    	    	    }
    	    	}
    	    	for(int i = 0; i < EuropeCities.length; i++) {
    	    	    if(EuropeCities[i].equals(citySelection)) {
    	    		cbAirport.addItem(EuropeAirports[i]);
    	    		lat[dstnsAdded] = Double.parseDouble(airports[i + 179][4]);
    	    		lon[dstnsAdded] = Double.parseDouble(airports[i + 179][5]);
    	    	    }
    	    	}
    	    	for(int i = 0; i < NorthAmCities.length; i++) {
    	    	    if(NorthAmCities[i].equals(citySelection)) {
    	    		cbAirport.addItem(NorthAmAirports[i]);
    	    		lat[dstnsAdded] = Double.parseDouble(airports[i + 332][4]);
    	    		lon[dstnsAdded] = Double.parseDouble(airports[i + 332][5]);
    	    	    }
    	    	}
    	    	for(int i = 0; i < OceaniaCities.length; i++) {
    	    	    if(OceaniaCities[i].equals(citySelection)) {
    	    		cbAirport.addItem(OceaniaAirports[i]);
    	    		lat[dstnsAdded] = Double.parseDouble(airports[i + 503][4]);
    	    		lon[dstnsAdded] = Double.parseDouble(airports[i + 503][5]);
    	    	    }
    	    	}
    	    	for(int i = 0; i < SouthAmCities.length; i++) {
    	    	    if(SouthAmCities[i].equals(citySelection)) {
    	    		cbAirport.addItem(SouthAmAirports[i]);
    	    		lat[dstnsAdded] = Double.parseDouble(airports[i + 515][4]);
    	    		lon[dstnsAdded] = Double.parseDouble(airports[i + 515][5]);
    	    	    }
    	    	}
    	    	
        	
            }});
	btnAddDstn.setFont(new Font("Tahoma", Font.PLAIN, 40));
	btnAddDstn.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e1) {
		location = (String) cbAirport.getSelectedItem() + " - " + (String) cbCity.getSelectedItem() + ", " + (String) cbCountry.getSelectedItem() + ", " + (String) cbContinent.getSelectedItem();
		dstnsAdded++;
		if(dstnsAdded == 1) {
		    txtDestination1.setText(location);
		    
		}
		else if (dstnsAdded == 2) {
		    txtDestination2.setText(location);
		    
		}
		else if (dstnsAdded == 3) {
		    txtDestination3.setText(location);
		    
		}
		else if (dstnsAdded == 4) {
		    txtDestination4.setText(location);
		    
		}
		else if (dstnsAdded == 5) {
		    txtDestination5.setText(location);
		    
		}
		else if (dstnsAdded == 6) {
		    txtDestination6.setText(location);
		    
		}
		else if (dstnsAdded == 7) {
		    txtDestination7.setText(location);
		    
		}
		else if (dstnsAdded == 8) {
		    txtDestination8.setText(location);
		    
		}
		if(dstnsAdded > 1) {
		    for(int i = 0; i < dstnsAdded - 1; i++) {		
			distances[dstnsAdded - 1][i] = (int) Math.ceil(getDistance(lat[dstnsAdded - 1], lon[dstnsAdded - 1], lat[i], lon[i]));
			distances[i][dstnsAdded - 1] = (int) Math.ceil(getDistance(lat[dstnsAdded - 1], lon[dstnsAdded - 1], lat[i], lon[i]));
			//System.out.println(lat[dstnsAdded - 1] + " " + lon[dstnsAdded - 1] + " " +  lat[i] + " " + lon[i]);
		    }
		}
		/*System.out.println(distances[0][0] + " " + distances[0][1] + " " + distances[0][2] + " " + distances[0][3]);
		System.out.println(distances[1][0] + " " + distances[1][1] + " " + distances[1][2] + " " + distances[1][3]);
		System.out.println(distances[2][0] + " " + distances[2][1] + " " + distances[2][2] + " " + distances[2][3]);
		System.out.println(distances[3][0] + " " + distances[3][1] + " " + distances[3][2] + " " + distances[3][3]);
		System.out.println(dstnsAdded);*/

	    }
	});
	btnRemove.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e1) {
		dstnsAdded = 0;
		totalDistance = 0;
		shortestDistance = 99999;
		currentDistance = 0;
		longestDistance = 0;
		counter = 0;
		txtDestination1.setText("");
		txtDestination2.setText("");
		txtDestination3.setText("");
		txtDestination4.setText("");
		txtDestination5.setText("");
		txtDestination6.setText("");
		txtDestination7.setText("");
		txtDestination8.setText("");
		txtPath1.setText("");
		txtPath2.setText("");
		txtPath3.setText("");
		txtPath4.setText("");
		txtPath5.setText("");
		txtPath6.setText("");
		txtPath7.setText("");
		txtPath8.setText("");
		txtShortestPath.setText("");
		txtAveragePath.setText("");
		txtLongestPath.setText("");
		
	    }
	});
	btnCalcDist.setFont(new Font("Tahoma", Font.PLAIN, 40));
	
	
	btnCalcDist.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (dstnsAdded == 2) {
			permute(java.util.Arrays.asList(0, 1), 0);
		    }
		    else if (dstnsAdded == 3) {
			permute(java.util.Arrays.asList(0, 1, 2), 0);
		    }
		    else if (dstnsAdded == 4) {
			permute(java.util.Arrays.asList(0, 1, 2, 3), 0);
		    }
		    else if (dstnsAdded == 5) {
			permute(java.util.Arrays.asList(0, 1, 2, 3, 4), 0);
		    }
		    else if (dstnsAdded == 6) {
			permute(java.util.Arrays.asList(0, 1, 2, 3, 4, 5), 0);
		    }
		    else if (dstnsAdded == 7) {
			permute(java.util.Arrays.asList(0, 1, 2, 3, 4, 5, 6), 0);
		    }
		    else if (dstnsAdded == 8) {
			permute(java.util.Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7), 0);
		    }
		    txtShortestPath.setText(Integer.toString(shortestDistance));
		    if(dstnsAdded > 0)	txtPath1.setText(getBox(shortestPath[0]).getText());
		    if(dstnsAdded > 1)  txtPath2.setText(getBox(shortestPath[1]).getText());
		    if(dstnsAdded > 2)  txtPath3.setText(getBox(shortestPath[2]).getText());
		    if(dstnsAdded > 3)  txtPath4.setText(getBox(shortestPath[3]).getText());
		    if(dstnsAdded > 4)  txtPath5.setText(getBox(shortestPath[4]).getText());
		    if(dstnsAdded > 5)	txtPath6.setText(getBox(shortestPath[5]).getText());
		    if(dstnsAdded > 6)  txtPath7.setText(getBox(shortestPath[6]).getText());
		    if(dstnsAdded > 7)  txtPath8.setText(getBox(shortestPath[7]).getText());
		    txtLongestPath.setText(Integer.toString(longestDistance));
		    txtAveragePath.setText(Integer.toString(totalDistance / counter));
		    System.out.println("This is the shortest path: " + shortestDistance + " miles.");
		    System.out.println("This is the longest path: " + longestDistance + " miles.");
		    System.out.println("This is the average path length: " + totalDistance / counter + " miles.");
		    System.out.println("There were " + counter + " total paths.");
		}
	});
	
	
	getContentPane().add(cbContinent);
	getContentPane().add(cbCity);
	getContentPane().add(btnAddDstn);
	
	txtDestination1 = new JTextField();
	txtDestination1.setFont(new Font("Tahoma", Font.PLAIN, 26));
	txtDestination1.setBackground(Color.WHITE);
	txtDestination1.setColumns(10);
	
	JLabel lblDestinations = new JLabel("Destinations:");
	lblDestinations.setFont(new Font("Tahoma", Font.PLAIN, 26));
	
	txtDestination2 = new JTextField();
	txtDestination2.setFont(new Font("Tahoma", Font.PLAIN, 26));
	txtDestination2.setColumns(10);
	
	txtDestination3 = new JTextField();
	txtDestination3.setFont(new Font("Tahoma", Font.PLAIN, 26));
	txtDestination3.setColumns(10);
	
	txtDestination4 = new JTextField();
	txtDestination4.setFont(new Font("Tahoma", Font.PLAIN, 26));
	txtDestination4.setColumns(10);
	
	txtDestination5 = new JTextField();
	txtDestination5.setFont(new Font("Tahoma", Font.PLAIN, 26));
	txtDestination5.setColumns(10);
	
	txtDestination6 = new JTextField();
	txtDestination6.setFont(new Font("Tahoma", Font.PLAIN, 26));
	txtDestination6.setColumns(10);
	
	txtDestination7 = new JTextField();
	txtDestination7.setFont(new Font("Tahoma", Font.PLAIN, 26));
	txtDestination7.setColumns(10);
	
	txtDestination8 = new JTextField();
	txtDestination8.setFont(new Font("Tahoma", Font.PLAIN, 26));
	txtDestination8.setColumns(10);
	
	JLabel lblContinent = new JLabel("Continent:");
	lblContinent.setFont(new Font("Tahoma", Font.PLAIN, 26));
	
	JLabel lblCountry = new JLabel("Country:");
	lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 26));
	
	JLabel lblCity = new JLabel("City:");
	lblCity.setFont(new Font("Tahoma", Font.PLAIN, 26));
	
	JLabel lblAirport = new JLabel("Airport:");
	lblAirport.setFont(new Font("Tahoma", Font.PLAIN, 26));
	
	
	btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 40));
	
	
	rdbtnEndDestination.setVerticalAlignment(SwingConstants.TOP);
	rdbtnEndDestination.setToolTipText("When enabled the path must end in the last destination entered");
	rdbtnEndDestination.setForeground(SystemColor.desktop);
	rdbtnEndDestination.setBackground(SystemColor.control);
	rdbtnEndDestination.setFont(new Font("Tahoma", Font.PLAIN, 40));
	
	lblShortestPath = new JLabel("The Shortest Path");
	lblShortestPath.setFont(new Font("Tahoma", Font.PLAIN, 40));
	
	txtShortestPath = new JTextField();
	txtShortestPath.setHorizontalAlignment(SwingConstants.RIGHT);
	txtShortestPath.setFont(new Font("Tahoma", Font.PLAIN, 26));
	txtShortestPath.setColumns(10);
	
	lblmiles1 = new JLabel("miles");
	lblmiles1.setFont(new Font("Tahoma", Font.PLAIN, 40));
	
	txtPath1 = new JTextField();
	txtPath1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtPath1.setColumns(10);
	
	txtPath2 = new JTextField();
	txtPath2.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtPath2.setColumns(10);
	
	txtPath3 = new JTextField();
	txtPath3.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtPath3.setColumns(10);
	
	txtPath4 = new JTextField();
	txtPath4.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtPath4.setColumns(10);
	
	txtPath5 = new JTextField();
	txtPath5.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtPath5.setColumns(10);
	
	txtPath6 = new JTextField();
	txtPath6.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtPath6.setColumns(10);
	
	txtPath7 = new JTextField();
	txtPath7.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtPath7.setColumns(10);
	
	txtPath8 = new JTextField();
	txtPath8.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtPath8.setColumns(10);
	
	lbl1 = new JLabel("1.");
	lbl1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	lbl2 = new JLabel("2.");
	lbl2.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	lbl3 = new JLabel("3.");
	lbl3.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	lbl4 = new JLabel("4.");
	lbl4.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	lbl5 = new JLabel("5.");
	lbl5.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	lbl6 = new JLabel("6.");
	lbl6.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	lbl7 = new JLabel("7.");
	lbl7.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	lbl8 = new JLabel("8.");
	lbl8.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	lblTheAveragePath = new JLabel("The Average Path");
	lblTheAveragePath.setFont(new Font("Tahoma", Font.PLAIN, 40));
	
	txtAveragePath = new JTextField();
	txtAveragePath.setHorizontalAlignment(SwingConstants.RIGHT);
	txtAveragePath.setFont(new Font("Tahoma", Font.PLAIN, 26));
	txtAveragePath.setColumns(10);
	
	lblmiles2 = new JLabel("miles");
	lblmiles2.setFont(new Font("Tahoma", Font.PLAIN, 40));
	
	lblTheLongestPath = new JLabel("The Longest Path");
	lblTheLongestPath.setFont(new Font("Tahoma", Font.PLAIN, 40));
	
	txtLongestPath = new JTextField();
	txtLongestPath.setHorizontalAlignment(SwingConstants.RIGHT);
	txtLongestPath.setFont(new Font("Tahoma", Font.PLAIN, 26));
	txtLongestPath.setColumns(10);
	
	label_2 = new JLabel("miles");
	label_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
	
	GroupLayout gl_contentPane = new GroupLayout(contentPane);
	gl_contentPane.setHorizontalGroup(
		gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblDestinations, GroupLayout.DEFAULT_SIZE, 1611, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(txtDestination8, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE)
									.addComponent(txtDestination7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE)
									.addComponent(txtDestination6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE)
									.addComponent(txtDestination5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE)
									.addComponent(txtDestination3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE)
									.addComponent(txtDestination4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE))
								.addGap(75))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(txtDestination1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE)
									.addComponent(txtDestination2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE))
								.addGap(75)))
						.addContainerGap())
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(cbContinent, 0, 237, Short.MAX_VALUE)
								.addGap(18))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblContinent)
								.addGap(23)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblCountry, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addComponent(cbCountry, Alignment.TRAILING, 0, 363, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblCity, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addComponent(cbCity, 0, 315, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(cbAirport, 0, 591, Short.MAX_VALUE)
								.addGap(85))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblAirport)
								.addContainerGap())))))
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGap(46)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(txtShortestPath, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblmiles1))
							.addComponent(lblShortestPath))
						.addGap(57)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lbl1)
							.addComponent(lbl2, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl3, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)))
					.addComponent(lbl4, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addComponent(lbl5, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addComponent(lbl6, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addComponent(lbl7, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addComponent(lbl8, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
					.addComponent(txtPath1, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
					.addComponent(txtPath2)
					.addComponent(txtPath3)
					.addComponent(txtPath4)
					.addComponent(txtPath5)
					.addComponent(txtPath6)
					.addComponent(txtPath7)
					.addComponent(txtPath8))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addComponent(lblTheAveragePath, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(txtAveragePath, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblmiles2, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
				.addGap(25)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(txtLongestPath, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
					.addComponent(lblTheLongestPath, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(26, Short.MAX_VALUE))
			.addGroup(gl_contentPane.createSequentialGroup()
				.addContainerGap()
				.addComponent(btnAddDstn, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(btnCalcDist, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(rdbtnEndDestination)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	);
	gl_contentPane.setVerticalGroup(
		gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGap(29)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblContinent)
					.addComponent(lblCity)
					.addComponent(lblCountry)
					.addComponent(lblAirport))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
					.addComponent(cbContinent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(cbAirport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(cbCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(cbCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(33)
				.addComponent(lblDestinations, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtDestination1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtDestination2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtDestination3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtDestination4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtDestination5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtDestination6, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtDestination7, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtDestination8, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addGap(106)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblShortestPath)
							.addComponent(txtPath1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl1))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblmiles1)
									.addComponent(txtShortestPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(6)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtPath2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lbl2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtPath3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lbl3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtPath4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lbl4, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtPath5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl5, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtPath6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl6, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtPath7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl7, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtPath8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl8, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTheAveragePath, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTheLongestPath, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGap(29)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtAveragePath, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblmiles2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtLongestPath, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))))
				.addGap(82)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnAddDstn, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
					.addComponent(btnCalcDist, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addComponent(rdbtnEndDestination, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
				.addContainerGap())
	);
	cbAirport.setFont(new Font("Tahoma", Font.PLAIN, 26));
	contentPane.setLayout(gl_contentPane);
	
	
    }
    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
	
	final int R = 3959; // Radius of the earth
	double latDistance, lonDistance;
	if(lat2 < 0) {
	    latDistance = Math.toRadians((lat2) - Math.abs(lat1));
	    lonDistance = Math.toRadians(Math.abs(lon2) - Math.abs(lon1));
	}
	else if(lat1 < 0) {
	    latDistance = Math.toRadians(Math.abs(lat2) - (lat1));
	    lonDistance = Math.toRadians(Math.abs(lon2) - Math.abs(lon1));
	}
	else if(lon2 < 0) {
	    latDistance = Math.toRadians(Math.abs(lat2) - Math.abs(lat1));
	    lonDistance = Math.toRadians((lon2) - Math.abs(lon1));
	}
	else if(lon1 < 0) {
	    latDistance = Math.toRadians(Math.abs(lat2) - Math.abs(lat1));
	    lonDistance = Math.toRadians(Math.abs(lon2) - (lon1));
	}
	else {
	    latDistance = Math.toRadians(Math.abs(lat2) - Math.abs(lat1));
	    lonDistance = Math.toRadians(Math.abs(lon2) - Math.abs(lon1));
	}
	double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
		* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	double distance = R * c; // convert to meters
	distance = Math.pow(distance, 2);
	return Math.sqrt(distance);
    }
    public void permute(java.util.List<Integer> arr, int k){
        for(int i = k; i < arr.size(); i++){
            //System.out.println(arr.get(k));
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if(rdbtnEndDestination.isSelected()) {
            if ((k == arr.size() -1) && (arr.get(0) == 0) && (arr.get(arr.size() - 1) == dstnsAdded - 1)){
        	//System.out.println(java.util.Arrays.toString(arr.toArray()));
        	//paths[k] = arr.stream().mapToInt(Integer::intValue).toArray();
        	calcDistance(arr);
        	counter++;
            }
        }
        else {
            if ((k == arr.size() -1) && (arr.get(0) == 0)){
        	calcDistance(arr);
        	counter++;
            }
        }
    }
    public void calcDistance(java.util.List<Integer> arr) {
	for(int i = 0; i < arr.size() - 1; i++) {
	    currentDistance += distances[arr.get(i)][arr.get(i + 1)];
	    totalDistance += distances[arr.get(i)][arr.get(i + 1)];
	    
	}
	if(currentDistance < shortestDistance) {
	    shortestDistance = currentDistance;
	    for(int i = 0; i < arr.size(); i++) {
		shortestPath[i] = arr.get(i);
		System.out.print(shortestPath[i]);
	    }
	}
	if(currentDistance > longestDistance) {
	    longestDistance = currentDistance;
	}
	
	currentDistance = 0;
    }
    public JTextField getBox(int x) {
	x++;
	if(x == 1) return txtDestination1;
	else if(x == 2) return txtDestination2;
	else if(x == 3) return txtDestination3;
	else if(x == 4) return txtDestination4;
	else if(x == 5) return txtDestination5;
	else if(x == 6) return txtDestination6;
	else if(x == 7) return txtDestination7;
	else if(x == 8) return txtDestination8;
	else return txtDestination8;
    }
}
