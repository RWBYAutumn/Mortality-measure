package dm.v1;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SetUpGUI {
    
    public static ArrayList<JCheckBox> perfectBoxArray = new ArrayList<>();
    private static GUI mainPanel;
    private static ArrayList<JCheckBox> BoxArray = new ArrayList<>();

    
    //Method called when creating new GUI to display either 'countries' , 'years' or 'causes of death' 
    
    public static void SetUpGUI(ArrayList<String> stringArray, String Name, HashMap hashMap, Boolean IsBarChart) {
        perfectBoxArray.clear();                                //Arrays are cleared at the begginning as they're used again on each call
        BoxArray.clear();

        adapt(stringArray);
        mainPanel = new GUI(Name, hashMap, IsBarChart);  //Creating an object of the GUI class
       
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.yellow);
        JFrame frame = new JFrame(Name);
        
        frame.setSize(800, 800);
       
         
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);

        //frame.pack();
        frame.setLocationByPlatform(true);

        for (int i = 0; i < perfectBoxArray.size(); i++) {
            mainPanel.addCheckBox(perfectBoxArray.get(i));

        }

        if (IsBarChart.equals(false)) {
            JLabel Label = null;
            if (Name.equals("Countries")) {
                Label = new JLabel("Only select a single country", JLabel.LEFT);
            }
            if (Name.equals("Causes")) {
                Label = new JLabel("Only select a single cause", JLabel.LEFT);
            }

            if (Name.equals("Years")) {
                Label = new JLabel("Select multiple years ", JLabel.LEFT);
            }

            Label.setVerticalAlignment(JLabel.TOP);
            Label.setFont(new Font("Verdana", Font.BOLD, 20));
            Label.setPreferredSize(new Dimension(250, 100));
            Label.setForeground(new Color(250, 0, 0));

            mainPanel.add(Label);

        }
       

        frame.setVisible(true);
    }
    
    
    //Method called after search bar has been used

    public static void SetUPGUI(String Name, String text, HashMap hashMap, Boolean IsBarChart) {

        mainPanel = new GUI(Name, hashMap, IsBarChart);
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.yellow);
       
        
        JFrame frame = new JFrame(Name);
        frame.setSize(600, 600);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);

        //frame.pack();
        frame.setLocationByPlatform(true);

        refresh(text);
        frame.setVisible(true);
    }

    
    //Used to limit the search results displayed 
    //Compares what was previously entered in the search bar to values in the list
    //If search bar is left blank original list with all values is called 
    //If not a
    public static void refresh(String text) {

        if (text == "") {
            for (int i = 0; i < perfectBoxArray.size(); i++) {
                mainPanel.addCheckBox(perfectBoxArray.get(i));
            }
        } else {
            BoxArray.clear();

            for (int i = 0; i < perfectBoxArray.size(); i++) {
                if (perfectBoxArray.get(i).getText().contains(text)) {
                    // System.out.println("Contains");
                    BoxArray.add(perfectBoxArray.get(i));//Leaving in countries that do not contain 
                }

            }
            for (int i = 0; i < BoxArray.size(); i++) {
                mainPanel.addCheckBox(BoxArray.get(i));
            }

        }

    }

    
    // Method converts the string array passed in to an array of JCheckBox's which can then be appended to the GUI
    public static void adapt(ArrayList<String> Carray) {
        for (int i = 0; i < Carray.size(); i++) {

           
            JCheckBox box = new JCheckBox(Carray.get(i));
            perfectBoxArray.add(box);
        }

    }

}
