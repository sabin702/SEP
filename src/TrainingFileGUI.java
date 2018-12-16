import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;

public class TrainingFileGUI extends JPanel
{
   private TrainingFileAdapter trainingAdapter;
   private AnalysisFileAdapter analysisAdapter;
   private WorkerFileAdapter workerAdapter;
   private MyListSelectionListener listListener;

   private JPanel buttonsPanel;
   private JPanel inputPanel;
   private JPanel trainingPanel;
   private JPanel analysisPanel;
   private JPanel workerPanel;
   private JPanel workerBoxPanel;
   private JPanel numberPanel;
   private JPanel initialsPanel;
   private JPanel matrixPanel;
   private JPanel analysisBoxPanel;
   private JPanel boxPanel;
   private JPanel mainPanel;

   private JButton addButton;
   private JButton deleteButton;
   private JButton editButton;
   private JButton saveButton;
   private MyButtonListener buttonListener;

   private JComboBox<Analysis> analysisBox;
   private JComboBox<Worker> workerBox;
   private JTextField trainingStatusField;
   private JTextField workerField;
   private JTextField analysisField;
   private JTextField initialsField;
   private JTextField numberField;
   private JTextField matrixField;

   private DefaultListModel<Training> listModel;
   private JList<Training> trainingList;
   private JScrollPane allTrainingScrollPane;
   private JPanel list;

   private JLabel trainingLabel;
   private JLabel analysisBoxLabel;
   private JLabel workerBoxLabel;
   private JLabel workerLabel;
   private JLabel analysisLabel;
   private JLabel initialsLabel;
   private JLabel numberLabel;
   private JLabel matrixLabel;

   public TrainingFileGUI(AnalysisFileAdapter analysisAdapter,
         WorkerFileAdapter workerAdapter, TrainingFileAdapter trainingAdapter)
   {
      // super("Training File Adapter GUI 69.420");
      // trainingAdapter = new TrainingFileAdapter("training.bin");
      // analysisAdapter = new AnalysisFileAdapter("analysis.bin");
      // workerAdapter = new WorkerFileAdapter("worker.bin");

      this.analysisAdapter = analysisAdapter;
      this.workerAdapter = workerAdapter;
      this.trainingAdapter = trainingAdapter;

      buttonListener = new MyButtonListener();

      buttonsPanel = new JPanel();
      trainingPanel = new JPanel();
      analysisPanel = new JPanel();
      mainPanel = new JPanel();
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

      inputPanel = new JPanel();
      inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
      workerPanel = new JPanel();
      workerBoxPanel = new JPanel();
      analysisBoxPanel = new JPanel();
      boxPanel = new JPanel();
      numberPanel = new JPanel();
      initialsPanel = new JPanel();
      matrixPanel = new JPanel();

      addButton = new JButton("Add");
      deleteButton = new JButton("Delete");
      editButton = new JButton("Edit");
      saveButton = new JButton("Save");
      addButton.addActionListener(buttonListener);
      deleteButton.addActionListener(buttonListener);
      editButton.addActionListener(buttonListener);
      saveButton.addActionListener(buttonListener);

      trainingLabel = new JLabel("Training:");
      trainingStatusField = new JTextField(15);
      trainingStatusField.setEditable(true);

      analysisLabel = new JLabel("Analysis:");
      analysisField = new JTextField(15);
      analysisField.setEditable(false);

      workerLabel = new JLabel("Worker: ");
      workerField = new JTextField(15);
      workerField.setEditable(false);

      analysisBoxLabel = new JLabel("Analysis");
      analysisBox = new JComboBox<Analysis>();
      analysisBox.addActionListener(buttonListener);

      workerBoxLabel = new JLabel("Worker");
      workerBox = new JComboBox<Worker>();
      workerBox.addActionListener(buttonListener);

      initialsLabel = new JLabel("Initials:   ");
      initialsField = new JTextField(15);
      initialsField.setEditable(false);

      numberLabel = new JLabel("Number:");
      numberField = new JTextField(15);
      numberField.setEditable(false);

      matrixLabel = new JLabel("Matrix:   ");
      matrixField = new JTextField(15);
      matrixField.setEditable(false);

      listModel = new DefaultListModel<Training>();
      listListener = new MyListSelectionListener();
      trainingList = new JList<Training>(listModel);
      trainingList.addListSelectionListener(listListener);
      trainingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      trainingList.setVisibleRowCount(5);
      allTrainingScrollPane = new JScrollPane(trainingList);
      allTrainingScrollPane.setHorizontalScrollBarPolicy(
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      list = new JPanel();
      list.add(allTrainingScrollPane);

      list.setPreferredSize(new Dimension(300, 100));

      buttonsPanel.add(addButton);
      buttonsPanel.add(deleteButton);
      buttonsPanel.add(editButton);
      buttonsPanel.add(saveButton);
      trainingPanel.add(trainingLabel);
      trainingPanel.add(trainingStatusField);
      workerBoxPanel.add(workerBoxLabel);
      workerBoxPanel.add(workerBox);
      analysisBoxPanel.add(analysisBoxLabel);
      analysisBoxPanel.add(analysisBox);
      analysisPanel.add(analysisLabel);
      analysisPanel.add(analysisField);
      workerPanel.add(workerLabel);
      workerPanel.add(workerField);
      numberPanel.add(numberLabel);
      numberPanel.add(numberField);
      initialsPanel.add(initialsLabel);
      initialsPanel.add(initialsField);
      matrixPanel.add(matrixLabel);
      matrixPanel.add(matrixField);
      inputPanel.add(matrixPanel);
      inputPanel.add(analysisPanel);
      inputPanel.add(initialsPanel);
      inputPanel.add(workerPanel);
      inputPanel.add(numberPanel);
      inputPanel.add(trainingPanel);

      boxPanel.add(analysisBoxPanel);
      boxPanel.add(workerBoxPanel);

      mainPanel.add(inputPanel);
      mainPanel.add(buttonsPanel);
      mainPanel.add(boxPanel);
      mainPanel.add(list);

      add(mainPanel);
      setSize(575, 452);
      setVisible(true);
      // setResizable(false);

      // setDefaultCloseOperation(EXIT_ON_CLOSE);
      // setLocationRelativeTo(null);

   }

   public void updateAnalysisBox()
   {
      int currentIndex = analysisBox.getSelectedIndex();

      analysisBox.removeAllItems();

      AnalysisList analysis = analysisAdapter.getAllAnalysis();
      for (int i = 0; i < analysis.size(); i++)
      {
         analysisBox.addItem(analysis.get(i));
      }

      if (currentIndex == -1 && analysisBox.getItemCount() > 0)
      {
         analysisBox.setSelectedIndex(0);
      }
      else
      {
         analysisBox.setSelectedIndex(currentIndex);
      }
   }

   public void updateWorkerBox()
   {
      int currentIndex = workerBox.getSelectedIndex();

      workerBox.removeAllItems();

      WorkersList worker = workerAdapter.getAllWorkers();
      for (int i = 0; i < worker.size(); i++)
      {
         workerBox.addItem(worker.get(i));
      }

      if (currentIndex == -1 && workerBox.getItemCount() > 0)
      {
         workerBox.setSelectedIndex(0);
      }
      else
      {
         workerBox.setSelectedIndex(currentIndex);
      }
   }

   public void updateTrainingList()
   {
      int currentIndex = trainingList.getSelectedIndex();

      listModel.clear();

      TrainingList trainings = trainingAdapter.getAllTrainings();
      for (int i = 0; i < trainings.getSize(); i++)
      {
         listModel.addElement(trainings.getTraining(i));
      }

      if (currentIndex == -1 && listModel.size() > 0)
      {
         trainingList.setSelectedIndex(0);
      }
      else
      {
         trainingList.setSelectedIndex(currentIndex);
      }
   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == workerBox)
         {
            try
            {
            Worker worker = (Worker) workerBox.getSelectedItem();
            workerField.setText(worker.getName());
            initialsField.setText(worker.getInitials());
            numberField.setText(worker.getNumber());
            trainingStatusField.setEditable(true);
            }
            catch (NullPointerException ex)
            {
               System.out.print("");
            }
         }
         if (e.getSource() == analysisBox)
         {
            try {
            Analysis analysis = (Analysis) analysisBox.getSelectedItem();
            analysisField.setText(analysis.getAnalysisType());
            matrixField.setText(analysis.getMatrix());
            trainingStatusField.setEditable(true);
            }
            catch (NullPointerException ex)
            {
               System.out.print("");
            }
         }
         if (e.getSource() == addButton)
         {
            String training = trainingStatusField.getText();
            String matrix = matrixField.getText();
            String analysis = analysisField.getText();
            String initials = initialsField.getText();
            String name = workerField.getText();
            String number = numberField.getText();

            Analysis analy = new Analysis(analysis, matrix);
            Worker work = new Worker(name, number, initials);

            trainingAdapter.addTrainings(training, analy, work);
            updateTrainingList();
         }
         if (e.getSource() == deleteButton)
         {

            String matrix = matrixField.getText();
            String analysis = analysisField.getText();
            String initials = initialsField.getText();
            String name = workerField.getText();
            String number = numberField.getText();
            String training = trainingStatusField.getText();

            Analysis anal = new Analysis(analysis, matrix);
            Worker work = new Worker(name, number, initials);

            trainingAdapter.deleteTraining(training, anal, work);

            matrixField.setText("");
            analysisField.setText("");
            initialsField.setText("");
            workerField.setText("");
            numberField.setText("");
            trainingStatusField.setText("");

            updateTrainingList();

         }
         if (e.getSource() == editButton)
         {
            trainingStatusField.setEditable(true);
            workerBox.setEnabled(false);
            analysisBox.setEnabled(false);
         }
         if (e.getSource() == saveButton)
         {
            workerBox.setEnabled(true);
            analysisBox.setEnabled(true);
            trainingStatusField.setEditable(false);
            
            TrainingList trainings = trainingAdapter.getAllTrainings();
            
            String matrix = matrixField.getText();
            String analysis = analysisField.getText();
            String initials = initialsField.getText();
            String name = workerField.getText();
            String number = numberField.getText();
            String training = trainingStatusField.getText();

            Analysis analy = new Analysis(analysis, matrix);
            Worker work = new Worker(name, number, initials);
            
            for (int i=0;i<trainings.getSize();i++) 
            {
               if(trainings.get(i).getWorker().equals(work) 
                     && trainings.get(i).getAnalysis().equals(analy))
               {
                  trainings.removeIndex(i);
                  trainingAdapter.saveTraining(trainings);
                  break;
                  
               }
            }
            
            trainings.addTraining(training, analy, work);

            
            trainingAdapter.saveTraining(trainings);
            

            matrixField.setText("");
            analysisField.setText("");
            initialsField.setText("");
            workerField.setText("");
            numberField.setText("");
            trainingStatusField.setText("");
            updateTrainingList();

         }
      }
   }

   private class MyListSelectionListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         if (e.getSource() == trainingList)
         {
            if (trainingList.getSelectedValue() instanceof Training)
            {
               Training training = (Training) trainingList.getSelectedValue();
               workerField.setText(training.getWorker().getName());
               initialsField.setText(training.getWorker().getInitials());
               numberField.setText(training.getWorker().getNumber());
               analysisField.setText(training.getAnalysis().getAnalysisType());
               matrixField.setText(training.getAnalysis().getMatrix());
               trainingStatusField.setText(training.getTrainingStatus());
               trainingStatusField.setEditable(false);
            }
         }
      }
   }
}
