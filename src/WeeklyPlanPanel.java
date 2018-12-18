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
   
   private JComboBox<String> combo;
   
   /**
    * Constructor initializing the GUI components
    * @param adapter StudentFileAdapter object used for retrieving and storing student information
    */
   public WeeklyPlanPanel(WeeklyPlanFileAdapter adapter, AnalysisFileAdapter analysisFileAdapter)
   {
      combo = new JComboBox<String>();
      
      String[] values = {"0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4"};
      for(int j = 0;j< values.length;j++) {
         combo.addItem(values[j]);
      }
      
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
      
      weeklyPlanTable = new JTable(dtm);
      weeklyPlanTable.setEnabled(true);
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
      saveButton.addActionListener(buttonListener);
      
      
      add(getButton);
      add(saveButton);
   }
   
   public void saveWeeklyPlanTable()
   {
      for (int i = 0; i < weeklyPlanTable.getModel().getRowCount(); i++)
{
   
  String mName = (String)weeklyPlanTable.getModel().getValueAt(i, 0);
  String wType = (String)weeklyPlanTable.getModel().getValueAt(i, 1);
  String aName = (String)weeklyPlanTable.getModel().getValueAt(i, 2);
  String moNumber =  (String)weeklyPlanTable.getModel().getValueAt(i, 3);
  String tuNumber = (String)weeklyPlanTable.getModel().getValueAt(i, 4);
  String weNumber =  (String)weeklyPlanTable.getModel().getValueAt(i, 5);
  String thNumber = (String)weeklyPlanTable.getModel().getValueAt(i, 6);
  String frNumber =  (String)weeklyPlanTable.getModel().getValueAt(i, 7);
  String saNumber =  (String)weeklyPlanTable.getModel().getValueAt(i, 8);

System.out.println(mName + " " + wType + " " + aName+ " " + moNumber + " " + tuNumber + " " + weNumber + " " + thNumber + " " + frNumber + " " + saNumber);

double[] emp = new double[6];

emp[0] = Double.parseDouble(moNumber);
emp[1] = Double.parseDouble(tuNumber);
emp[2] = Double.parseDouble(weNumber);
emp[3] = Double.parseDouble(thNumber);
emp[4] = Double.parseDouble(frNumber);
emp[5] = Double.parseDouble(saNumber);


adapter.addWeeklyPlan(i,new WeeklyPlan(new Analysis(aName,mName),wType,emp));

}
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
      
      TableColumn[] columns = new TableColumn[6];
      String[] values = {"0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4"};
      for (int i = 0; i < 6; i++)
      {
        columns[i] = weeklyPlanTable.getColumnModel().getColumn(i+3);
         columns[i].setCellEditor(new MyComboBoxEditor(values));
      }

      for (int i = 0; i < weeklyPlanTable.getModel().getRowCount(); i++)
      {
         String[] weeklyNumber = new String[6];
         
         
         for(int n=0; n<6;n++) {
            if (weeklyPlans.get(i) != null)
               weeklyNumber[n] = String.valueOf(weeklyPlans.get(i).getWeeklyEmployee(n));
            else 
               weeklyNumber[n] = "0";
         }
         
         
         for(int m=0; m<6;m++) {
               weeklyPlanTable.getModel().setValueAt(weeklyNumber[m], i, m+3);
         }
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
         if (e.getSource() == getButton)
         {
            updateWeeklyPlanTable();
         }
         
         else if (e.getSource() == saveButton)
         {
           saveWeeklyPlanTable();
         }
      }
   }
   
   private class MyComboBoxRender extends JComboBox implements TableCellRenderer{
      public MyComboBoxRender(String[] items) {
         super(items);
         super.setSelectedItem("0");
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
         super(combo);
      }
   }
}
