import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class SEPmainGUI extends JFrame
{
   private AnalysisFileAdapter adapter;
   private WorkerFileAdapter workeradapter;
   private TrainingFileAdapter trainingAdapter;
   private AnnualPerformanceAdapter annualadapter;
   private WeeklyPlanFileAdapter weeklyPlaAdapter;
   private WorkPlanAdapter workPlanAdapter;
   
   private JTabbedPane tabpane;
   
   private JMenuBar menuBar;
   
   private JMenu fileMenu;
   private JMenu aboutMenu;
   
   private MyButtonListener buttonListener;
   private MyTabListener tabListener;
   
   private JMenuItem exitMenuItem;
   private JMenuItem aboutMenuItem;
   
   private EditAnalysisPanel editAnalysisPanel;
   private ChangeInformationPanel changeInformationPanel;
   private TrainingFileGUI editTrainingPanel;
   private EditAnnualPerformacePrefrence editAnnualPerformacePrefrence;
   private WeeklyPlanPanel weeklyPlanPanel;
   private WorkPlanPanel workPlanPanel;
   
   public SEPmainGUI()
   {
      super ("SEP GUI TEST");
      
      adapter = new AnalysisFileAdapter("analysis.bin");
      workeradapter = new WorkerFileAdapter("workers.bin");
      trainingAdapter = new TrainingFileAdapter("training.bin");
      annualadapter = new AnnualPerformanceAdapter("annuals.bin");
      weeklyPlaAdapter = new WeeklyPlanFileAdapter("weeklyPlan.bin");
      workPlanAdapter = new WorkPlanAdapter("workPlan.bin");
      
      buttonListener = new MyButtonListener();
      tabListener = new MyTabListener();
      
      exitMenuItem = new JMenuItem("Exit");
      exitMenuItem.addActionListener(buttonListener);

      aboutMenuItem = new JMenuItem("About");
      aboutMenuItem.addActionListener(buttonListener);
      

      fileMenu = new JMenu("File");
      aboutMenu = new JMenu("About");
         
      fileMenu.add(exitMenuItem);      
      aboutMenu.add(aboutMenuItem);
      
      menuBar = new JMenuBar();
      
      menuBar.add(fileMenu);
      menuBar.add(aboutMenu);
      
      setJMenuBar(menuBar);
      
      editAnalysisPanel = new EditAnalysisPanel(adapter);
      changeInformationPanel = new ChangeInformationPanel(workeradapter);
      editTrainingPanel = new TrainingFileGUI(adapter, workeradapter, trainingAdapter);
      editAnnualPerformacePrefrence = new EditAnnualPerformacePrefrence(annualadapter,workeradapter);
      weeklyPlanPanel = new WeeklyPlanPanel(weeklyPlaAdapter, adapter);
      workPlanPanel = new WorkPlanPanel(workPlanAdapter, workeradapter,annualadapter, trainingAdapter);
      
      tabpane=new JTabbedPane();

      tabpane.addTab("Work Plan", workPlanPanel);
      tabpane.addTab("Weekly Plan", weeklyPlanPanel);
      tabpane.addTab("Edit Worker", changeInformationPanel);
      tabpane.addTab("Edit Analysis", editAnalysisPanel);
      tabpane.addTab("Edit Training", editTrainingPanel);
      tabpane.addTab("Edit AnnualPrefrence", editAnnualPerformacePrefrence);
      tabpane.addChangeListener(tabListener);
      
      add(tabpane);
      setSize(575, 452);
      setVisible(true);
      setResizable(false);

      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      
   }
   
   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == exitMenuItem)
         {
            int choice = JOptionPane.showConfirmDialog(null,
                  "Really Exit?", "Exit",
                  JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION)
            {
               System.exit(0);
            }
         }

         if (e.getSource() == aboutMenuItem)
         {
            JOptionPane.showMessageDialog(
                        null,
                        "GROUP 3 First group project for SEP 1",
                        
                        "About", JOptionPane.PLAIN_MESSAGE);
         }
      }
      
   }
   
   private class MyTabListener implements ChangeListener
   {
      public void stateChanged(ChangeEvent e)
      {
         if (((JTabbedPane)e.getSource()).getSelectedIndex() == 0)
         {
            weeklyPlanPanel.updateWeeklyPlanTable();
         }
         if (((JTabbedPane)e.getSource()).getSelectedIndex() == 1)
         {
            workPlanPanel.updateWorkPlanTable();
         }
         if(((JTabbedPane)e.getSource()).getSelectedIndex()==2)
         {
            changeInformationPanel.updateWorkerList();
         } 
         if (((JTabbedPane)e.getSource()).getSelectedIndex()==3)
         {
            editAnalysisPanel.updateAnalysisList();
         }
         if (((JTabbedPane)e.getSource()).getSelectedIndex() == 4) {
            editTrainingPanel.updateTrainingList();
            editTrainingPanel.updateAnalysisBox();
            editTrainingPanel.updateWorkerBox();
         }
         if (((JTabbedPane)e.getSource()).getSelectedIndex() == 5)
         {
            editAnnualPerformacePrefrence.updateAnnualPerformanceList();
            editAnnualPerformacePrefrence.updateWorkerBox();
         }
      }
   }
   
   public static void main(String[] args)
   {
      SEPmainGUI sepgui = new SEPmainGUI();
   }
   
   
}
