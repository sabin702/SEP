import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class WeeklyPlanPanel extends JPanel
{
   private WeeklyPlanFileAdapter adapter;
   private AnalysisFileAdapter analysisAdapter;
   private MyButtonListener buttonListener;
  
   private JTable weeklyPlanTable;
   private String[] columnNames;
  // private String[] commentsRow;
   private MyDate date;
   private DefaultTableModel dtm;
   private JScrollPane weeklyPlanScrollPane;
   
   private JButton getButton;
   private JButton saveButton;
  
   private JPanel datePanel;
   private JLabel currentDate;
   
   /**
    * Constructor initializing the GUI components
    * @param adapter StudentFileAdapter object used for retrieving and storing student information
    */
   public WeeklyPlanPanel(WeeklyPlanFileAdapter adapter, AnalysisFileAdapter analysisFileAdapter)
   {
      this.adapter = adapter;
      this.analysisAdapter = analysisFileAdapter;
      buttonListener = new MyButtonListener();
      
      date = new MyDate();
            
      columnNames = new String[9];
      columnNames[0] = "Matrix";
      columnNames[1] = "Week size";
      columnNames[2] = "Analysis";
      columnNames[3] = "No. Persons";
      columnNames[4] = "No. Persons";
      columnNames[5] = "No. Persons";
      columnNames[6] = "No. Persons";
      columnNames[7] = "No. Persons";
      columnNames[8] = "No. Persons";
      
      
      dtm = new DefaultTableModel(columnNames, 0);
      
      weeklyPlanTable = new JTable(dtm) {
         //Implement Table cell tool tips
         public String getToolTipText(MouseEvent e) {
            
            WeeklyPlanList weeklyPlans = adapter.getAllWeeklyPlans();
            
            String tip = null;
            java.awt.Point point = e.getPoint();
            int rowIndex = rowAtPoint(point);
            int colIndex = columnAtPoint(point);
            //int realColumnIndex = convertColumnIndexToModel(colIndex);
            tip = weeklyPlans.get(rowIndex).getAnalysis().getAnalysisType() + " belongs to: " + weeklyPlans.get(rowIndex).getAnalysis().getMatrix();
            return tip;
         }
      };
      weeklyPlanTable.setEnabled(false);
      weeklyPlanTable.getTableHeader().setReorderingAllowed(false);
      weeklyPlanTable.getTableHeader().setResizingAllowed(false);
      weeklyPlanTable.setPreferredScrollableViewportSize(new Dimension(700, weeklyPlanTable.getRowHeight()*18));
      
      weeklyPlanScrollPane = new JScrollPane(weeklyPlanTable);
      weeklyPlanScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      
      currentDate = new JLabel("Year: " + date.getYear() + " Week: " + date.getCurrentWeek());
      add(currentDate);
      
      add(weeklyPlanScrollPane);

      getButton = new JButton("Display Weekly Plan");
      getButton.addActionListener(buttonListener);
      
      saveButton = new JButton("Save");
      //saveButton.addActionListener(buttonListener);
      
      add(getButton);
     // add(saveButton);
   }
   
   /**
    * Enables or disables editing of the allStudentsTable.
    * @param bool if true then the table will be editable, if false then it will not
    */
   public void changeEditableState(boolean bool)
   {
     weeklyPlanTable.setEnabled(bool);
     weeklyPlanTable.clearSelection();
   }
   
   /**
    * Updates the allStudentsTable JTable with information from the students file
    */
   public void updateWeeklyPlanTable()
   {
      WeeklyPlanList weeklyPlans = adapter.getAllWeeklyPlans();
      AnalysisList analyses = analysisAdapter.getAllAnalysis();
      
      Object[][] data = new Object[analyses.size()*3][9];
      
      int tableSize = analyses.size();
      
      for(int i = 0; i<analyses.size();i++)
      {
         data[i][0] = analyses.get(i).getMatrix();
         data[i][1] = "S";
         data[i][2] = analyses.get(i).getAnalysisType();
      }
      
      while(tableSize<analyses.size()*2) {
         for(int i = 0; i<analyses.size();i++)
         {  
         data[tableSize][0] = analyses.get(i).getMatrix();
         data[tableSize][1] = "L";
         data[tableSize][2] = analyses.get(i).getAnalysisType();
         tableSize++;
         }
      }
      
      while(tableSize<analyses.size()*3) {
      for(int i = 0; i<analyses.size();i++)
      {
         data[tableSize][0] = analyses.get(i).getMatrix();
         data[tableSize][1] = "All";
         data[tableSize][2] = analyses.get(i).getAnalysisType();
         tableSize++;
         }
      }
      dtm = new DefaultTableModel(data, columnNames);
      
      weeklyPlanTable.setModel(dtm);
 String[] values = {"0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4"};
      TableColumn[] columns = new TableColumn[6];
      for (int i = 0; i < columns.length; i++)
      {
         columns[i] = weeklyPlanTable.getColumnModel().getColumn(i+3);
         columns[i].setCellEditor(new MyComboBoxEditor(values));
         columns[i].setCellRenderer(new MyComboBoxRender(values));
      }
      //TableColumn col = weeklyPlanTable.getColumnModel().getColumn(3);
      //col.setCellEditor(new MyComboBoxEditor(values));
      //col.setCellRenderer(new MyComboBoxRender(values));
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
         if (e.getSource() == getButton)
         {
            updateWeeklyPlanTable();
         }
         
        /* else if (e.getSource() == saveButton)
         {
            updateWeeklyPlanTable();
         }*/
      }
   }
   
   private class MyComboBoxRender extends JComboBox implements TableCellRenderer{
      public MyComboBoxRender(String[] items) {
         super(items);
      }
      
      public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column)
      {
         if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
         }
         else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
         }
         setSelectedItem(value);
         return this;
      }
   }
   
   private class ButtonRender extends JButton implements TableCellRenderer{

      public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column)
      {
         
         return null;
      }
      
   }   
   
   class MyComboBoxEditor extends DefaultCellEditor {
      public MyComboBoxEditor(String[] items) {
         super(new JComboBox<String>(items));
      }
   }
}
