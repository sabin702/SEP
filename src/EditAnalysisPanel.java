import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Savepoint;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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



public class EditAnalysisPanel extends JPanel
{
   private AnalysisFileAdapter adapter;
   private MyButtonListener buttonListener;
   private MyListSelectionListener listListener;
   
   private JPanel inputPanel;
   
   private JLabel analysisLabel;
   private JLabel matrixLabel;

   private JTextField analysisField;
   private JTextField matrixField;
   
   private JButton clearButton;
   private JButton addButton;
   private JButton deleteButton;
   
   private JPanel comboPanel;
   private JList<Analysis> analysisList;
   private DefaultListModel<Analysis> listModel;
   private JScrollPane allAnalysisScrollPane;
   
   
   /**
    * Constructor initializing the GUI components
    * @param adapter AnalysisFileAdapter object used for retrieving and storing analysis information
    */
   public EditAnalysisPanel(AnalysisFileAdapter adapter)
   {
      this.adapter = adapter;
      buttonListener = new MyButtonListener();
      listListener = new MyListSelectionListener();
      
      inputPanel = new JPanel();

      analysisLabel = new JLabel("Analysis:    ");
      analysisField = new JTextField(15);
      analysisField.setEditable(false);

      matrixLabel = new JLabel("Matrix:        ");
      matrixField = new JTextField(15);
      matrixField.setEditable(false);

      clearButton = new JButton("Clear");
      clearButton.addActionListener(buttonListener);
      
      addButton = new JButton("Add");
      addButton.addActionListener(buttonListener);
      addButton.setEnabled(false);
      
      deleteButton = new JButton("Delete");
      deleteButton.addActionListener(buttonListener);

      inputPanel.add(analysisLabel);
      inputPanel.add(analysisField);

      inputPanel.add(matrixLabel);
      inputPanel.add(matrixField);

      inputPanel.add(clearButton);
      inputPanel.add(addButton);
      inputPanel.add(deleteButton);
      
      add(inputPanel);
      inputPanel.setPreferredSize(new Dimension(280, 300));

      comboPanel = new JPanel();
            
      listModel = new DefaultListModel<Analysis>();
      analysisList = new JList<Analysis>(listModel);
      analysisList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      analysisList.addListSelectionListener(listListener);
      analysisList.setVisibleRowCount(12); 
      
      allAnalysisScrollPane = new JScrollPane(analysisList);
      allAnalysisScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
      comboPanel.add(allAnalysisScrollPane);
            
      add(comboPanel);
      comboPanel.setPreferredSize(new Dimension(260, 300));
   }
   
   
   /**
    * Updates the analysisList JList with information from the analyses file  
    */
  public void updateAnalysisList()
   {
      int currentIndex = analysisList.getSelectedIndex();
      
      listModel.clear();
      
      AnalysisList analysises = adapter.getAllAnalysis();
      for(int i = 0; i<analysises.size(); i++)
      {
         listModel.addElement(analysises.get(i));
      }

      if(currentIndex==-1 && listModel.size()>0)
      {
         analysisList.setSelectedIndex(0);
      }
      else
      {
         analysisList.setSelectedIndex(currentIndex);
      }
      if (analysisList.getSelectedIndex()<0 || analysisList.getSelectedIndex()>analysises.size())
      {
         deleteButton.setEnabled(false);
      }
      else
      {
         deleteButton.setEnabled(true);
      }
   }

   /*
    * Inner action listener class 
    * @author Sabin Sirbu
    * @version 1.0
    */
   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == clearButton)
         {
            analysisField.setText("");
            matrixField.setText("");
            analysisField.setEditable(true);
            matrixField.setEditable(true);
            addButton.setEnabled(true);
         }
         if (e.getSource() == addButton)
         {
            String analysis=analysisField.getText();
            String matrix=matrixField.getText();
            
            AnalysisList analysisList=adapter.getAllAnalysis();
            boolean sameAnal=false;
            boolean emptyField=false;
            
            for (int i=0;i<analysisList.size();i++)
            {
               if (analysis.equals(analysisList.get(i).getAnalysisType()) && matrix.equals(analysisList.get(i).getMatrix()))
               {
                  sameAnal = true;
                  break;
               }     
            }
            
            if (analysis.equals("") || matrix.equals(""))
               emptyField=true;
            
            
            
            if (sameAnal==false && emptyField==false)
            {
               adapter.addAnalysis(analysis, matrix);
               
               updateAnalysisList();
               
               analysisField.setEditable(false);
               matrixField.setEditable(false);
               addButton.setEnabled(false);
            }
            else if (sameAnal==true)
            {
               JOptionPane.showMessageDialog(
                     null,
                     "Analysis with matrix already exists!",
                     "Erorr", JOptionPane.ERROR_MESSAGE);
            }
            else if(emptyField==true)
               {
               JOptionPane.showMessageDialog(
                     null,
                     "One or more fields is empty!",
                     "Erorr", JOptionPane.ERROR_MESSAGE);
               }
            }
         if (e.getSource()== deleteButton)
         {
            analysisField.setEditable(true);
            matrixField.setEditable(true); 
            
            String analysis = analysisField.getText();
            String matrix= matrixField.getText();
            
            adapter.deleteAnalysis(analysis, matrix);
                     
            updateAnalysisList();
            
            analysisField.setText("");
            matrixField.setText("");
            analysisField.setEditable(false);
            matrixField.setEditable(false);
            
            updateAnalysisList();
         }
      }
   }
   
   private class MyListSelectionListener implements ListSelectionListener 
   {
      public void valueChanged(ListSelectionEvent e) 
      {
         if (e.getSource() == analysisList)
         {
            if (analysisList.getSelectedValue() instanceof Analysis)
            {
               Analysis temp = (Analysis)analysisList.getSelectedValue();
               analysisField.setText(temp.getAnalysisType());
               matrixField.setText(temp.getMatrix());
            }
         }
      }
   }
}
