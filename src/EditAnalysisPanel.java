import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
   
   private JButton updateButton;
   
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

      updateButton = new JButton("Update");
      updateButton.addActionListener(buttonListener);

      inputPanel.add(analysisLabel);
      inputPanel.add(analysisField);

      inputPanel.add(matrixLabel);
      inputPanel.add(matrixField);

      inputPanel.add(updateButton);
      
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
         if (e.getSource() == updateButton)
         {
            String analysis = analysisField.getText();
            String matrix = matrixField.getText();

            if (matrix.equals(""))
            {
               matrix = "?";
            }

            adapter.changeMatrixName(analysis, matrix);
            updateAnalysisList();
            matrixField.setText("");
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
               //lastNameField.setText(temp.getLastName());
            }
         }
      }
   }
}
