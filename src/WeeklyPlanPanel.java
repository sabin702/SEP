import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class WeeklyPlanPanel extends JFrame
{
   private WeeklyPlanFileAdapter adapter;
   private MyButtonListener buttonListener;
  
   private JTable weeklyPlanTable;
   private String[] columnNames;
  // private String[] commentsRow;
   private MyDate date;
   private DefaultTableModel dtm;
   private JScrollPane weeklyPlanScrollPane;
   
   private JButton getButton;
   private JButton editButton;
  
   private JPanel datePanel;
   private JLabel currentDate;
   
   /**
    * Constructor initializing the GUI components
    * @param adapter StudentFileAdapter object used for retrieving and storing student information
    */
   public WeeklyPlanPanel(WeeklyPlanFileAdapter adapter)
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

      getButton = new JButton("Get Students");
      getButton.addActionListener(buttonListener);
      
      editButton = new JButton("Edit");
      
      
      add(getButton);
      
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
      Object[][] data = new Object[weeklyPlans.size()][7];
      
      for(int i = 0; i<weeklyPlans.size();i++)
      {
         data[i][0] = weeklyPlans.get(i).getAnalysis().getAnalysisType();
         data[i][1] = weeklyPlans.get(i).getWeekSize();
         data[i][2] = weeklyPlans.get(i).getAnalysis().getMatrix();
         data[i][3] = weeklyPlans.get(i).getNumberOfEmployees(i);
         data[i][4] = weeklyPlans.get(i).getNumberOfEmployees(i);
         data[i][5] = weeklyPlans.get(i).getNumberOfEmployees(i);
         data[i][6] = weeklyPlans.get(i).getNumberOfEmployees(i);
         data[i][7] = weeklyPlans.get(i).getNumberOfEmployees(i);
         data[i][8] = weeklyPlans.get(i).getNumberOfEmployees(i);
         
      }
      dtm = new DefaultTableModel(data, columnNames);
      weeklyPlanTable.setModel(dtm);
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
