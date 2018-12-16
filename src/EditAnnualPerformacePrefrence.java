import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Savepoint;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * A GUI panel containing components for changing a students country.
 * @author Allan Henriksen
 * @version 2.0
 */
public class EditAnnualPerformacePrefrence extends JPanel
{
   private AnnualPerformanceAdapter adapter;
   private WorkerFileAdapter workeradapter;
   private MyButtonListener buttonListener;
   private MyListSelectionListener listListener;
   
   private JPanel inputPanel;
   
   private JLabel nameLabel;
   private JLabel numberLabel;
   private JLabel idLabel;
   private JLabel prefrenceLabel;

   private JTextField nameField;
   private JTextField numberField;
   private JTextField idField;
   private JTextField prefrenceField;
   
   private JButton updateButton;
   
   private JPanel comboPanel;
   private JList<AnnualPerformance> annualPerformanceList;
   private DefaultListModel<AnnualPerformance> listModel;
   private JScrollPane allAnnualPerformanceScrollPane;

   /**
    * Constructor initializing the GUI components
    * @param adapter StudentFileAdapter object used for retrieving and storing student information
    */
   public EditAnnualPerformacePrefrence (AnnualPerformanceAdapter adapter)
   {
      this.adapter=adapter;
      workeradapter = new WorkerFileAdapter("workers.bin");
      buttonListener = new MyButtonListener();
      listListener = new MyListSelectionListener();
      
      inputPanel = new JPanel();

      nameLabel = new JLabel("Name:    ");
      nameField = new JTextField(15);
      nameField.setEditable(false);

      numberLabel = new JLabel("Number: ");
      numberField = new JTextField(15);
      numberField.setEditable(false);

      idLabel = new JLabel("Initials:   ");
      idField = new JTextField(15);
      idField.setEditable(false);
      
      prefrenceLabel = new JLabel("Prefrence:  ");
      prefrenceField = new JTextField(15);
      

      updateButton = new JButton("Update");
      updateButton.addActionListener(buttonListener);
          
      inputPanel.add(nameLabel);
      inputPanel.add(nameField);

      inputPanel.add(numberLabel);
      inputPanel.add(numberField);

      inputPanel.add(idLabel);
      inputPanel.add(idField);
      
      inputPanel.add(prefrenceLabel);
      inputPanel.add(prefrenceField);

      inputPanel.add(updateButton);
      
      add(inputPanel);
      inputPanel.setPreferredSize(new Dimension(280, 300));

      comboPanel = new JPanel();
            
      listModel = new DefaultListModel<AnnualPerformance>();
      annualPerformanceList = new JList<AnnualPerformance>(listModel);
      annualPerformanceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      annualPerformanceList.addListSelectionListener(listListener);
      annualPerformanceList.setVisibleRowCount(12); 
      
      allAnnualPerformanceScrollPane = new JScrollPane(annualPerformanceList);
      allAnnualPerformanceScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
      comboPanel.add(allAnnualPerformanceScrollPane);
            
      add(comboPanel);
      comboPanel.setPreferredSize(new Dimension(200, 300));
   }
   
   
   /**
    * Updates the studentList JList with information from the students file  
    */
  public void updateAnnualPerformanceList()
   {
      int currentIndex = annualPerformanceList.getSelectedIndex();
      
      listModel.clear();
      
      AnnualPerformanceList annuals= new AnnualPerformanceList();
      WorkersList workers = workeradapter.getAllWorkers();
      
      for (int i=0;i<workers.size();i++)
      {
         annuals.addAnnualPerformance(new AnnualPerformance("f",workers.get(i)));

      }

      for(int i = 0; i<annuals.getSize(); i++)
      {
         listModel.addElement(annuals.getAnnualPerformance(i));
      }

      if(currentIndex==-1 && listModel.size()>0)
      {
         annualPerformanceList.setSelectedIndex(0);
      }
      else
      {
         annualPerformanceList.setSelectedIndex(currentIndex);
      }
      
   }
  
  public void updateAnnualPerformanceListUpdated()
  {
     int currentIndex = annualPerformanceList.getSelectedIndex();
     
     listModel.clear();
     
     AnnualPerformanceList annuals= new AnnualPerformanceList();

     for(int i = 0; i<annuals.getSize(); i++)
     {
        listModel.addElement(annuals.getAnnualPerformance(i));
     }

     if(currentIndex==-1 && listModel.size()>0)
     {
        annualPerformanceList.setSelectedIndex(0);
     }
     else
     {
        annualPerformanceList.setSelectedIndex(currentIndex);
     }
  }

   /*
    * Inner action listener class 
    * @author Allan Henriksen
    * @version 3.0
    */
   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == updateButton)
         {
            String name = nameField.getText();
            String number= numberField.getText();
            String id= idField.getText();
            String str= prefrenceField.getText();
            
            adapter.changeAnnualPerformanceComment(str, name, number, id);
            
            updateAnnualPerformanceListUpdated();
         }
      }
   }
   
   private class MyListSelectionListener implements ListSelectionListener 
   {
      public void valueChanged(ListSelectionEvent e) 
      {
         if (e.getSource() == annualPerformanceList)
         {
            if (annualPerformanceList.getSelectedValue() instanceof AnnualPerformance)
            {
               AnnualPerformance temp = (AnnualPerformance)annualPerformanceList.getSelectedValue();
               nameField.setText(temp.getWorker().getName());
               numberField.setText(temp.getWorker().getNumber());
               idField.setText(temp.getWorker().getInitials());
               prefrenceField.setText(temp.getComment());
            }
         }
      }
   }
}