import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * A GUI panel that allows for displaying,adding and modifying workers
 * @author Kresimir Bosnjak, Lucian Rafa
 * @version 1.0
 */
public class ChangeInformationPanel extends JPanel
{
   private WorkerFileAdapter adapter;
   private MyButtonListener buttonListener;
   private MyListSelectionListener listListener;
   
   private JPanel inputPanel;
   
   private JLabel nameLabel;
   private JLabel numberLabel;
   private JLabel idLabel;

   private JTextField nameField;
   private JTextField numberField;
   private JTextField idField;
   
   private JButton updateButton;
   private JButton editButton;
   private JButton clearButton;
   private JButton addButton;
   private JButton deleteButton;
   
   private JPanel comboPanel;
   private JList<Worker> workerList;
   private DefaultListModel<Worker> listModel;
   private JScrollPane allWorkersScrollPane;

   /**
    * Constructor initializing the GUI components
    * @param adapter WorkeFileAdapter object used for retrieving and storing worker information
    */
   public ChangeInformationPanel(WorkerFileAdapter adapter)
   {
      this.adapter = adapter;
      buttonListener = new MyButtonListener();
      listListener = new MyListSelectionListener();
      
      inputPanel = new JPanel();

      nameLabel = new JLabel("Name:    ");
      nameField = new JTextField(15);
      nameField.setEditable(false);

      numberLabel = new JLabel("Number: ");
      numberField = new JTextField(15);
      numberField.setEditable(false);

      idLabel = new JLabel("Initials:    ");
      idField = new JTextField(15);
      idField.setEditable(false);

      updateButton = new JButton("Save");
      updateButton.addActionListener(buttonListener);
      updateButton.setEnabled(false);
      
      editButton = new JButton("Edit");
      editButton.addActionListener(buttonListener);

      clearButton = new JButton("Clear");
      clearButton.addActionListener(buttonListener);
      
      addButton = new JButton("Add");
      addButton.addActionListener(buttonListener);
      addButton.setEnabled(false);
      
      deleteButton = new JButton("Delete");
      deleteButton.addActionListener(buttonListener);
      
      inputPanel.add(nameLabel);
      inputPanel.add(nameField);

      inputPanel.add(numberLabel);
      inputPanel.add(numberField);

      inputPanel.add(idLabel);
      inputPanel.add(idField);

      inputPanel.add(updateButton);
      inputPanel.add(editButton);
      inputPanel.add(clearButton);
      inputPanel.add(addButton);
      inputPanel.add(deleteButton);
      
      add(inputPanel);
      inputPanel.setPreferredSize(new Dimension(280, 300));

      comboPanel = new JPanel();
            
      listModel = new DefaultListModel<Worker>();
      workerList = new JList<Worker>(listModel);
      workerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      workerList.addListSelectionListener(listListener);
      workerList.setVisibleRowCount(12); 
      
      allWorkersScrollPane = new JScrollPane(workerList);
      allWorkersScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
      comboPanel.add(allWorkersScrollPane);
            
      add(comboPanel);
      comboPanel.setPreferredSize(new Dimension(200, 300));
   }
   
   
   /**
    * Updates the WorkerList JList with information from the worker file  
    */
  public void updateWorkerList()
   {
      int currentIndex = workerList.getSelectedIndex();
      
      listModel.clear();
      
      WorkersList workers = adapter.getAllWorkers();
      for(int i = 0; i<workers.size(); i++)
      {
         listModel.addElement(workers.get(i));
      }

      if(currentIndex==-1 && listModel.size()>0)
      {
         workerList.setSelectedIndex(0);
      }
      else
      {
         workerList.setSelectedIndex(currentIndex);
      }
      
      if (workerList.getSelectedIndex()<0 || workerList.getSelectedIndex()>workers.size())
      {
         deleteButton.setEnabled(false);
      }
      else
         deleteButton.setEnabled(true);
   }

   /*
    * Inner action listener class 
    * @author Kresimir Bosnjak
    * @version 1.0
    */
   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource()== editButton)
         {
            nameField.setEditable(true);
            idField.setEditable(true); 
            
            updateButton.setEnabled(true);
         }
         if (e.getSource() == updateButton)
         {
            
            String name = nameField.getText();
            String number= numberField.getText();
            String id= idField.getText();
            boolean emptyFields=false;
            
            if (name.equals("") || number.equals("") || id.equals(""))
            {
               emptyFields=true;
            }

            if (emptyFields==true)
            {
               {
               JOptionPane.showMessageDialog(
                     null,
                     "One or more fields is empty!",
                     "Erorr", JOptionPane.ERROR_MESSAGE);
               }
            }
            else {
            adapter.changeInformation(name, number, id);
            updateWorkerList();
            
            nameField.setText("");
            numberField.setText("");
            idField.setText("");
            numberField.setEditable(false);
            nameField.setEditable(false);
            idField.setEditable(false); 
            updateButton.setEnabled(false);
            }
         }
         if (e.getSource() == clearButton)
         {
            nameField.setText("");
            numberField.setText("");
            idField.setText("");
            numberField.setEditable(true);
            nameField.setEditable(true);
            idField.setEditable(true); 
            addButton.setEnabled(true);
         }
         if (e.getSource() == addButton)
         {
            String name = nameField.getText();
            String number= numberField.getText();
            String id= idField.getText();
            
            WorkersList workers=adapter.getAllWorkers();
            boolean sameWorker=false;
            boolean emptyField=false;
            
            for (int i=0;i<workers.size();i++)
            {
               if (workers.get(i).getName().equals(name) &&
                     workers.get(i).getInitials().equals(id) &&
                     workers.get(i).getNumber().equals(number))
               {
                  sameWorker = true;
                  break;
               }     
            }
            
            if (name.equals("") || number.equals("") || id.equals(""))
               emptyField=true;
            
            
            
            if (sameWorker==false && emptyField==false)
            {
            adapter.addWorker(name, number, id);
            updateWorkerList();
            numberField.setEditable(false);
            nameField.setEditable(false);
            idField.setEditable(false);
            addButton.setEnabled(false);
            }
            else if (sameWorker==true)
            {
               JOptionPane.showMessageDialog(
                     null,
                     "Worker already exists!",
                     "Erorr", JOptionPane.ERROR_MESSAGE);
               addButton.setEnabled(false);
            }
            else if(emptyField==true)
               {
               JOptionPane.showMessageDialog(
                     null,
                     "One or more fields is empty!",
                     "Erorr", JOptionPane.ERROR_MESSAGE);
               }
            addButton.setEnabled(false);
            }
         
         if (e.getSource()== deleteButton)
         {
            numberField.setEditable(true);
            nameField.setEditable(true);
            idField.setEditable(true); 
            
            String name = nameField.getText();
            String number= numberField.getText();
            String id= idField.getText();
            
            adapter.deleteWorker(name, number, id);
                     
            updateWorkerList();
            
            nameField.setText("");
            numberField.setText("");
            idField.setText("");
            numberField.setEditable(false);
            nameField.setEditable(false);
            idField.setEditable(false);
            
            updateWorkerList();
         }
      }
   }
   /*
    * Inner action listener class 
    * @author Kresimir Bosnjak
    * @version 1.0
    */
   private class MyListSelectionListener implements ListSelectionListener 
   {
      public void valueChanged(ListSelectionEvent e) 
      {
         if (e.getSource() == workerList)
         {
            if (workerList.getSelectedValue() instanceof Worker)
            {
               Worker temp = (Worker)workerList.getSelectedValue();
               nameField.setText(temp.getName());
               numberField.setText(temp.getNumber());
               idField.setText(temp.getInitials());
            }
         }
      }
   }
}
