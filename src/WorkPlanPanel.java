import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class WorkPlanPanel extends JPanel
{
   private WorkPlanAdapter adapter;
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
   public WorkPlanPanel(WorkPlanAdapter adapter)
   {
      this.adapter = adapter;
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
            
            String tip = null;
            java.awt.Point point = e.getPoint();
            int rowIndex = rowAtPoint(point);
            int colIndex = columnAtPoint(point);
            //int realColumnIndex = convertColumnIndexToModel(colIndex);
            tip = workPlans.get(rowIndex).getWorker().getName() + " " + workPlans.get(rowIndex).getAnnualPerformance().getComment();
            return tip;
         }
      };
      workPlanTable.setEnabled(false);
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
      
      
      add(getButton);
      
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
   
   /**
    * Updates the allStudentsTable JTable with information from the students file
    */
   public void updateWorkPlanTable()
   {
      WorkPlanList workPlans = adapter.getAllWorkPlans();
      Object[][] data = new Object[workPlans.size()][7];
      
      for(int i = 0; i<workPlans.size();i++)
      {
         data[i][0] = workPlans.get(i).getWorker().getInitials();
         data[i][1] = workPlans.get(i).getWorker().getName();
         
         for (int j=0;j<workPlans.get(i).getTrainings().getSize();j++) {
            data[i][j+2] = workPlans.get(i).getTrainings().get(j).getAnalysis() + " (" 
         + workPlans.get(i).getTrainings().get(j).getAnalysis() + ")";
         }
         
      }
      dtm = new DefaultTableModel(data, columnNames);
      workPlanTable.setModel(dtm);
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
      }
   }
   
   private class ButtonRender extends JButton implements TableCellRenderer{

      public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column)
      {
         
         return null;
      }
      
   }
}
