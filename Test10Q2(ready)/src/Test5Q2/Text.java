package Test5Q2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class Text {


    static File calendarfile =new File("Calendar.txt");


    String date;
    String content;
    //create the file if not exist, write in the date and the content based on parameter with exception handling
    public Text(String D,String C) throws IOException {
        if(!calendarfile.exists()) {
            calendarfile.createNewFile();
        }
        date=D;
        content=C;
        if(C.equalsIgnoreCase("") || C.equalsIgnoreCase("Empty")) delete();
        else add();//create add into calendartxt
    }
    //method used in daywindow class with exception handling
    public Text(String D) throws IOException {
        if(!calendarfile.exists()) {
            calendarfile.createNewFile();
        }
        date=D;
    }
    //method to return true if the calendar file exist and is ok with exception handling
    public boolean Exist() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(calendarfile)));
        String str;
        while((str = br.readLine()) != null)
        {
            if(str.startsWith(date)) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }
    //method to declare action whether content should write to existing file or new file  with exception handling
    public void add() throws IOException {
        // TODO Auto-generated method stub
        if(Exist()) write("addtoexisting");
        else write("addtonew");
    }
    // method to declare delete action with exception handling
    public void delete() throws IOException{
        write("delete");
    }
    //method to write in the file based on action determined as above  with exception handling
    public void write(String choose) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(calendarfile)));
        String str;
        String S=date;
        ArrayList<String> list = new ArrayList<>();

        //add new content to the calendar file
        if(choose.equalsIgnoreCase("addtonew")) {
            //
            list.add(S);
            list.add(content);
            list.add(S);
        }

        int count=1;

        while((str = br.readLine()) != null)
        {
            // if no date found, write new date in
            if(!str.equalsIgnoreCase(date) && count==1)
                list.add(str);
            // if date found
            else if(str.equalsIgnoreCase(date) && count==1) {
                // if it is existing file, add date and add content
                if(choose.equalsIgnoreCase("addtoexisting")) {
                    list.add(date);
                    list.add(content);
                    count=2;
                }
                // if the action is delete, set counter to 2
                else if(choose.equalsIgnoreCase("delete")) count=2;
            }
            //if date is found again
            else if(str.equalsIgnoreCase(date) && count==2) {
                //if it is existing file, then modify the content
                if(choose.equalsIgnoreCase("addtoexisting")) {
                    list.add(date);

                    count=1;
                }
                // if it is to delete the content
                else if(choose.equalsIgnoreCase("delete")) {
                    count=1;
                }
            }
        }
        br.close();
        rewrite(list);
    }
    //method to rewrite and update the file so that content is intact with exception handling
    private void rewrite(ArrayList<String> list) throws IOException {
        FileOutputStream fos = new FileOutputStream(calendarfile);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        if(list.size() != 0)
            for (String string : list) {
                bw.write(string);
                bw.newLine();
            }
        else if(list.size() == 0) {
            bw.write("");
        }
        bw.flush();
        bw.close();
    }

    //method to get the content on a particular day with exception handling
    public String getcontent() throws IOException {
        //read the calendar file with bufferedreader
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(calendarfile)));
        String s="";
        String str;
        //initialise counter with 1
        int count=1;
        while((str = br.readLine()) != null)
        {
            //if the count is 1, and the string matched the date, set the count to 2
            if(count==1 && str.equalsIgnoreCase(date)) {
                count=2;
            }
            //if the count is 2, and the string matched the date again, then check if there is any content, if no content break it, if have read the content and write to string s
            else if(count==2) {
                if(str.equalsIgnoreCase(date)) {
                    if(s==null || s=="\n") return "";
                    break;
                }
                else
                    s+=str+"\n";
            }
        }
        //close buffered reader
        br.close();
        //return the content
        return s;
    }

}
