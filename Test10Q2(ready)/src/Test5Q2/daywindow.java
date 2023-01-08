package Test5Q2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class daywindow extends Frame{
    int year;
    int month;
    int day;
    TextArea textarea=new TextArea();
    JButton save=new JButton();
    JButton delete=new JButton();
    Label daytitle=new Label();


    //method to save text in the text area  with exception handling
    public void Save() throws IOException {
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                String str=textarea.getText();
                String date=year+""+month+""+day;
                try {
                    new Text(date,str);
                    JOptionPane.showMessageDialog(null, "Save Successful!\n Please reopen the app to update the task list window");
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }

        });

    }

    //method to delete text from the text area
    public void Delete() {
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the task?", "Confirmation to delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    textarea.setText("Empty");
                    String date=year+""+month+""+day;
                    try {
                        new Text(date).delete();
                        JOptionPane.showMessageDialog(null, "Delete Successful!\n Please reopen the app to update the task list window");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                }else if (result == JOptionPane.NO_OPTION){
                }
            }

        });
    }

    //method to obtain the text from the date  with exception handling
    public void Text() throws IOException {
        String date=year+""+month+""+day;
        Text t=new Text(date);
        String s=t.getcontent();
        if(s.equalsIgnoreCase("") || s.equalsIgnoreCase("\n")|| s.equalsIgnoreCase(" "))
            textarea.setText("Empty");
        else textarea.setText(s);
    }


    //day window screen layout
    public void daywindowscreen() {
        save.setText("Save task");
        delete.setText("Delete task");
        textarea.setText("Empty");
        textarea.setBounds(20,80,260,260);
        delete.setBounds(140,355,110,25);
        save.setBounds(30,355,90,25);
        daytitle.setText("Task text");
        daytitle.setBounds(20,55,60,20);

    }

    //method to initialize the datwindow class
    private void init() throws IOException {
        this.setLayout(null);
        daywindowscreen();
        Save();
        Delete();
        Text();
        this.add(daytitle);
        this.add(textarea);
        this.add(save);
        this.add(delete);
        this.setTitle(year+"/"+month+"/"+day);
        this.setLocation(800, 50);
        this.setSize(300,400);
        this.setVisible(true);
        this.setResizable(false);
    }
    //method to call the init  with exception handling
    public daywindow(int Y,int M,int D) {

        this.year=Y;
        this.month=M;
        this.day=D;
        try {
            init();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //add window listener for closing the daywindow
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeaction(e);
            }
        });
    }
    //method to close the day window
    public void closeaction(WindowEvent e) {
        this.setVisible(false);
    }
}
