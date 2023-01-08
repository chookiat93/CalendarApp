package Test5Q2;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.*;
import javax.swing.*;



public class Q2 extends Frame{
    JButton tasklistshow=new JButton();
    Label MonthYear=new Label();
    Label []WeekLabel=new Label[7];
    Label tasklisttitle=new Label();
    JButton[]day=new JButton[42];
    JTable TaskJTable;
    static JButton nextMonth=new JButton();
    static JButton lastMonth=new JButton();
    //current month
    int currentmonth;
    //current year
    int currentyear;
    int []leapyear= {31,29,31,30,31,30,31,31,30,31,30,31};
    int []nonleapyear= {31,28,31,30,31,30,31,31,30,31,30,31};
    final String columnNames[] = { "Date", "Task",};

    //method to generate rowdata for the TaskJTable with exception handling
    public String[][] rowdata3() throws IOException {

        //calculate the length of Date_Time.DateListTestDate2()
        int fal = CalendarFile.DateListDate().length;
        //set the row number for the TaskJTable later
        int rownum =0;
        if (fal>3){
            rownum = fal/3;
        }

        //create 1st array string for column 1
        String[] array1dDate = new String[rownum];

        for (int i=0;i<rownum;i++){
            array1dDate[i] = CalendarFile.DateListDate()[i*3];

        }

        //create 2nd array string for column 2
        String[] array1dTask = new String[rownum];


        //method to merge two array to 2d array
        for (int i=0;i<rownum;i++){
            array1dTask[i] = CalendarFile.DateListTask()[i+(i*2+1)];
        }
        String[][] arrayBox = new String[fal/3][2];
        for (int i=0;i < rownum;i++){
            arrayBox[i][0] = array1dDate[i];
            arrayBox[i][1] = array1dTask[i];
        }

        return arrayBox;

    }
    //boolean to determine
    boolean TasklistShowing = true;


    //layout for the center part
    public void CenterLayout() {
        currentmonth=Calendar.getInstance().get(Calendar.MONTH)+1;
        currentyear=Calendar.getInstance().get(Calendar.YEAR);
        Daylayout();
        Day(currentmonth,currentyear);
        daybuttonaction();

    }

    //add action listener when any date is clicked
    public void daybuttonaction() {
        //for the total 42 days in the window screen
        for(int i=0;i<42;i++) {
            final int j=i;
            day[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int year=currentyear;
                    int month=currentmonth;
                    String s=day[j].getText();
                    int day=Integer.parseInt(s);
                    new daywindow(year,month,day);
                }
            });
        }
    }

    //layout of each day
    public void Daylayout() {
        int t=0;
        for(int i=0;i<6;i++) {
            int j = 0;

            //layout for sunday
            for(j=0;j<1;j++){
                day[t]=new JButton();
                day[t].setBackground(Color.pink);
                day[t].setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.black));
                day[t].setBounds(20+j*60,110+i*40,60,40);
                day[t].setFont(new Font(Font.DIALOG,Font.BOLD,13));
                this.add(day[t]);
                t++;
            }
            //layout for week days
            for(j=1;j<6;j++) {
                day[t]=new JButton();
                day[t].setBackground(Color.lightGray);
                day[t].setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.black));
                day[t].setBounds(20+j*60,110+i*40,60,40);
                day[t].setFont(new Font(Font.DIALOG,Font.BOLD,13));
                this.add(day[t]);
                t++;
            }
            //weekday for saturday
            for(j=6;j<7;j++) {
                day[t]=new JButton();
                day[t].setBackground(Color.pink);
                day[t].setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.black));
                day[t].setBounds(20+j*60,110+i*40,60,40);
                day[t].setFont(new Font(Font.DIALOG,Font.BOLD,13));
                this.add(day[t]);
                t++;
            }

        }
    }
    public void Day(int month,int year) {

        DateTimeCal T=new DateTimeCal(month);
        T.Setyear(year);
        int firstwd;
        firstwd = T.firstWeekdayofMonth(month,year);

        //determine month andyear format
        int MonthYear;
        if (year%4==0){
            MonthYear = leapyear[(month+11)%12];
        }else {
            MonthYear = nonleapyear[(month+11)%12];


        }

        //last month first line
        int lastmonthday;
        if (year%4==0){
            lastmonthday = leapyear[(month+10)%12];
        }else {
            lastmonthday =nonleapyear[(month+10)%12];
        }

            for(int i=firstwd;i>=0;i--) {
                if(i!=firstwd) {
                    String s=""+lastmonthday--;
                    day[i].setText(s);
                    day[i].setEnabled(false);
                }
            }


        //start the month with day below
        int dayofmonth=1;
        for(int i=firstwd;i<(firstwd+MonthYear);i++) {
            String s=""+dayofmonth++;
            day[i].setText(s);
            day[i].setEnabled(true);
        }
        //plot next month with day below
        int t=1;
        for(int i=firstwd+MonthYear;i<42;i++) {
            String s=""+t++;
            day[i].setText(s);
            day[i].setEnabled(false);
        }
    }

    // Show the current Month and Year
    public void monthyear() {
        String s= new DateTimeCal(Calendar.getInstance().get(Calendar.MONTH)+1).monthyear();
        s+=" "+Calendar.getInstance().get(Calendar.YEAR);
        MonthYear.setText(s);
    }

    // update based on month and year
    public void monthyear(int month,int year) {
        String s= new DateTimeCal(month).monthyear();
        s+=" "+year;
        MonthYear.setText(s);
    }
    // S M T weekday label
    public void weeklabel() {
        String[] S= {"S","M","T","W","T","F","S"};
        for(int i=0;i<7;i++) {
            WeekLabel[i]=new Label(S[i]);
            WeekLabel[i].setBounds(40+i*60,80,60,30);
            WeekLabel[i].setFont(new Font(Font.DIALOG,Font.BOLD,13));
        }
    }

    //layout for the top part
    public void TopLayout() {

        //back button with action listener for function to go previous month
        lastMonth.setText("<");
        lastMonth.setFont(new Font(Font.DIALOG,Font.ROMAN_BASELINE,13));
        lastMonth.setBounds(20, 35, 50, 50);
        lastMonth.setBackground(Color.white);
        lastMonth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(currentmonth-1==0) {
                    currentmonth=12;
                    currentyear--;
                    Day(currentmonth,currentyear);
                    monthyear(currentmonth,currentyear);
                }
                else {
                    Day(--currentmonth,currentyear);
                    monthyear(currentmonth,currentyear);
                }
            }

        });
        this.add(lastMonth);

        //next button with action listener for function to go previous month
        nextMonth.setText(">");
        nextMonth.setFont(new Font(Font.DIALOG,Font.PLAIN,13));
        nextMonth.setBounds(390, 35, 50, 50);
        nextMonth.setBackground(Color.white);
        nextMonth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(currentmonth+1==13) {
                    currentmonth=1;
                    currentyear++;
                    Day(currentmonth,currentyear);
                    monthyear(currentmonth,currentyear);
                }
                else {
                    Day(++currentmonth,currentyear);
                    monthyear(currentmonth,currentyear);
                }
            }

        });
        this.add(nextMonth);

        //Month Year display in between the back and next button
        monthyear();
        MonthYear.setBounds(170,25,200,60);
        MonthYear.setFont(new Font(Font.DIALOG,Font.ROMAN_BASELINE,20));
        this.add(MonthYear);

    }

    //method for  list window action listener
    public void TaskList() {
        tasklistshow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    tasklistaction(e);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }

    //method for tasklist action listener  with exception handling
    public void tasklistaction(ActionEvent e) throws IOException {

        if(TasklistShowing){
            tasklistshow.setText("Hide task list");
            TasklistShowing = false;

            this.setSize(900,450);

        }else {
            tasklistshow.setText("Show task list");
            TasklistShowing = true;
            this.setSize(500,450);

        }


    }

    //layout for bottom and etc with exception handling
    public void BottomLayout() throws IOException {
        TaskList();
        tasklisttitle.setText("Tasks are shown below");
        tasklisttitle.setBounds(660, 30, 150, 30);
        TaskJTable = new JTable(rowdata3(),columnNames);
        JScrollPane TaskTablescrollPane = new JScrollPane(TaskJTable);
        TaskJTable.setBounds(610, 60, 270, 300);
        TaskJTable.setBackground(Color.white);
        TaskJTable.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.pink));
        TaskTablescrollPane.setBounds(610, 60, 270, 301);
        TaskTablescrollPane.setBackground(Color.white);
        this.add(TaskTablescrollPane);
        tasklistshow.setText("Show task list");
        tasklistshow.setBounds(150,380,180,40);
        this.add(tasklisttitle);
        this.add(tasklistshow);


    }


    //method to initialize the GUI with exception handling
    private void init() throws IOException {
        this.setLayout(null);
        weeklabel();
        TopLayout();
        CenterLayout();
        BottomLayout();

        this.setBackground(Color.white);
        this.setLocation(300, 50);
        for(int i=0;i<7;i++) {
            this.add(WeekLabel[i]);
        }
    }
    //method to call init with exception handling
    public Q2() throws IOException {
        try {
            init();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    //main arg to call CalendarApp
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Q2 CalendarApp=new Q2();
        CalendarApp.setTitle("Calendar Task App");
        CalendarApp.setSize(600,450);
        CalendarApp.setResizable(false);
        CalendarApp.setVisible(true);
    }



}
