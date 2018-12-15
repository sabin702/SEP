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
   
   public SEPmainGUI()
   {
      super ("SEP GUI TEST");
      
      adapter = new AnalysisFileAdapter("analysis.bin");
      workeradapter = new WorkerFileAdapter("workers.bin");
      trainingAdapter = new TrainingFileAdapter("training.bin");
      
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
      
      tabpane=new JTabbedPane();

      tabpane.addTab("Edit Worker", changeInformationPanel);
      tabpane.addTab("Edit Analysis", editAnalysisPanel);
      tabpane.addTab("Edit Training", editTrainingPanel);
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
                  "Do you really want to exit the worst program you ever used?", "Exit",
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
                        "This is just a little fail",
                        "About", JOptionPane.PLAIN_MESSAGE);
         }
      }
      
   }
   
   private class MyTabListener implements ChangeListener
   {
      public void stateChanged(ChangeEvent e)
      {
         if(((JTabbedPane)e.getSource()).getSelectedIndex()==0)
         {
            changeInformationPanel.updateWorkerList();
         } 
         if (((JTabbedPane)e.getSource()).getSelectedIndex()==1)
         {
            editAnalysisPanel.updateAnalysisList();
         }
         if (((JTabbedPane)e.getSource()).getSelectedIndex() == 2) {
            editTrainingPanel.updateAnalysisBox();
            editTrainingPanel.updateWorkerBox();
         }
      }
   }
   
   public static void main(String[] args)
   {
      SEPmainGUI sepgui = new SEPmainGUI();
   }
   
   
}
