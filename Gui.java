import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Gui extends JFrame{ //inherit from jframe
    private JTextField namefield;
    private JLabel name;
    private JTextField statusfield;
    private JLabel status;
    private JButton submit;
    private JButton update;
    private JButton search;
    private FlowLayout layout;
    private Container container;
    String content = "";
    String line = "";
    String updatedLine = "";
    int startingIndex;
    int endingIndex;

    public Gui(){
        super("depressionator 3000");
        layout = new FlowLayout(); //arranges gui objects from left to right 
        container = getContentPane();
        setLayout(layout);

        File dbase = new File("C:\\Users\\quntu\\Desktop\\coding\\database.txt");

        name = new JLabel("Name: ");
        namefield = new JTextField(20);
        add(name);
        add(namefield);

        status = new JLabel("Status:");
        statusfield = new JTextField(20);
        add(status);
        add(statusfield);

        submit = new JButton("Submit");
        add(submit);
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){ 
                try{
                    Integer invalid = 0;
                    Scanner scan = new Scanner(dbase);
                    FileWriter database = new FileWriter("C:\\Users\\quntu\\Desktop\\coding\\database.txt", true);
                    while(scan.hasNext()){
                        if(scan.next().equals(namefield.getText())){
                            statusfield.setText("person already in system");
                            invalid++;
                            break;
                        }
                    }

                    if(invalid == 0){
                        database.write(namefield.getText() + " " + statusfield.getText() + "\n");
                    }

                    database.close();
                    scan.close();
                }catch(IOException ie){
                    statusfield.setText("Error: " + ie);
                }  
            }
        });

        update = new JButton("Update");
        add(update);
        update.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                try{
                    Scanner scan = new Scanner(dbase);
                    BufferedWriter database = new BufferedWriter(new FileWriter("C:\\Users\\quntu\\Desktop\\coding\\database.txt"));

                    while(scan.hasNext()){
                        content = content + scan.nextLine() + "\n";
                    }
                    
                    System.out.println(content);
                    System.out.println();
                    if(content.contains(namefield.getText())){
                        startingIndex = content.indexOf(namefield.getText());
                        endingIndex = content.indexOf("\n", startingIndex);
                        line = content.substring(startingIndex, endingIndex);
                        updatedLine = namefield.getText() + " " + statusfield.getText();
                        content = content.replaceAll(line, updatedLine);
                    }
                    System.out.println(content);
                    database.write(content);
                    database.close();
                    scan.close();
                    line = "";
                    content = "";

                }catch(Exception e){
                    statusfield.setText("error");
                    System.out.println(e);
                }
            }
        });

        search = new JButton("Search");
        add(search);
        search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                try{
                    Scanner scan = new Scanner(dbase);
                    while(scan.hasNext()){
                        if(scan.next().equals(namefield.getText())){
                            statusfield.setText(scan.next());
                        }
                    }
                    scan.close();
                }catch(Exception e){
                    statusfield.setText("Error");
                    System.out.println(e);
                }
            }
        });
    }

}