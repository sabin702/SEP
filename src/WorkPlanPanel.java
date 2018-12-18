import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;

public class WorkPlanPanel extends JPanel
{
   private WorkPlanAdapter adapter;
   private WorkerFileAdapter workerAdapter;
   private TrainingFileAdapter trainingAdapter;
   private AnnualPerformanceAdapter annualAdapter;
   private MyButtonListener buttonListener;
  
   private JTable workPlanTable;
   private String[] columnNames;
  // private String[] commentsRow;
   private MyDate date;
   private DefaultTableModel dtm;
   private JScrollPane workPlanScrollPane;
   
   private JButton getButton;
   private JButton saveButton;
  
   private JPanel datePanel;
   private JLabel currentDate;
   
   /**
    * Constructor initializing the GUI components
    * @param adapter StudentFileAdapter object used for retrieving and storing student information
    */
   public WorkPlanPanel(WorkPlanAdapter adapter, WorkerFileAdapter workerAdapter, AnnualPerformanceAdapter annualAdapter, TrainingFileAdapter trainingAdapter)
   {
      this.adapter = adapter;
      this.workerAdapter = workerAdapter;
      this.annualAdapter = annualAdapter;
      this.trainingAdapter = trainingAdapter;
      buttonListener = new MyButtonListener();
      
      date = new MyDate();
            
      columnNames = new String[7];
      columnNames[0] = "Initials";
      columnNames[1] = "Date";
      columnNames[2] = date.toString();
      columnNames[3] = date.nextDate().toString();
      columnNames[4] = date.nextDate().toString();
      columnNames[5] = date.nextDate().toString();
      columnNames[6] = date.nextDate().toString();
      
      
      dtm = new DefaultTableModel(columnNames, 0);
      
      workPlanTable = new JTable(dtm) {
         //Implement Table cell tool tips
         public String getToolTipText(MouseEvent e) {
            
            WorkPlanList workPlans = adapter.getAllWorkPlans();
            AnnualPerformanceList annuals = annualAdapter.getAllAnnualPerformance();
            
            String tip = null;
            java.awt.Point point = e.getPoint();
            int rowIndex = rowAtPoint(point);
            int colIndex = columnAtPoint(point);
          
            String initials = (String)workPlanTable.getModel().getValueAt(rowIndex, 0);
            String name = (String)workPlanTable.getModel().getValueAt(rowIndex, 1);
            String comment = annuals.getComment(initials, name);
            
            tip = name + " prefers to: " + comment;
            return tip;
         }
      };
      workPlanTable.setEnabled(true);
      workPlanTable.getTableHeader().setReorderingAllowed(false);
      workPlanTable.getTableHeader().setResizingAllowed(false);
      workPlanTable.setPreferredScrollableViewportSize(new Dimension(700, workPlanTable.getRowHeight()*18));
      
      workPlanScrollPane = new JScrollPane(workPlanTable);
      workPlanScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      
      currentDate = new JLabel("Year: " + date.getYear() + " Week: " + date.getCurrentWeek());
      add(currentDate);
      
      add(workPlanScrollPane);

      getButton = new JButton("Update Work Plan");
      getButton.addActionListener(buttonListener);
      
      saveButton = new JButton("Save");
      saveButton.addActionListener(buttonListener);
      
      
      add(getButton);
      add(saveButton);
      
   }
   
   /**
    * Enables or disables editing of the allStudentsTable.
    * @param bool if true then the table will be editable, if false then it will not
    */
   public void changeEditableState(boolean bool)
   {
     workPlanTable.setEnabled(bool);
     workPlanTable.clearSelection();
   }
   
   
   public void saveWorkPlanTable()
   {
      for (int i = 0; i < workPlanTable.getModel().getRowCount(); i++)
      {
   
         String initials = (String)workPlanTable.getModel().getValueAt(i, 0);
         String name = (String)workPlanTable.getModel().getValueAt(i, 1);
         String analysis1 =  (String)workPlanTable.getModel().getValueAt(i, 2);
         String analysis2 = (String)workPlanTable.getModel().getValueAt(i, 3);
         String analysis3 =  (String)workPlanTable.getModel().getValueAt(i, 4);
         String analysis4 = (String)workPlanTable.getModel().getValueAt(i, 5);
         String analysis5 =  (String)workPlanTable.getModel().getValueAt(i, 6);

         System.out.println(initials + " " + name + " " + analysis1 + " " + analysis2 + " " + analysis3 + " " + analysis4 + " " + analysis5);

         String[] analyses = new String[5];
         analyses[0] = analysis1;
         analyses[1] = analysis2;
         analyses[2] = analysis3;
         analyses[3] = analysis4;
         analyses[4] = analysis5;

         adapter.addWorkPlan(i,new WorkPlan(initials, name, analyses));

      }
   }
   
   /**
    * Updates the allStudentsTable JTable with information from the students file
    */
   public void updateWorkPlanTable()
   {
      WorkPlanList workPlans = adapter.getAllWorkPlans();
      WorkersList workers = workerAdapter.getAllWorkers();
      TrainingList trainings = trainingAdapter.getAllTrainings();
      Object[][] data = new Object[workers.size()][7];
      
      for(int i = 0; i<workers.size();i++)
      {
         data[i][0] =   workers.get(i).getInitials();
         data[i][1] = workers.get(i).getName();
      }
      dtm = new DefaultTableModel(data, columnNames);
      
      workPlanTable.setModel(dtm);
      
      String[] analyses = new String[trainings.getSize()];
      for (int i = 0; i < analyses.length; i++)
      {
         analyses[i] = trainings.get(i).getAnalysis().getAnalysisType() 
               + " (" + trainings.get(i).getAnalysis().getMatrix()
               + ")";
      }
      
      TableColumn[] columns = new TableColumn[5];
      for (int i = 0; i < 5; i++)
      {
         columns[i] = workPlanTable.getColumnModel().getColumn(i+2);
         columns[i].setCellEditor(new MyComboBoxEditor(analyses));
      }
      
      for (int i= 0; i< workPlanTable.getModel().getRowCount();i++) {
         String[] analysesList = new String[5];
         
         for(int n=0;n<5;n++) {
            if(workPlans.get(i) != null)
               analysesList[n] = (String)workPlans.get(i).getAnalysis(n);
            else
               analysesList[n] = "No analysis";
         }
         
         for(int m=0; m<5;m++) {
            workPlanTable.getModel().setValueAt(analysesList[m], i, m+2);
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
            updateWorkPlanTable();
         }
         if (e.getSource() == saveButton)
         {
            
           saveWorkPlanTable();
         }
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
