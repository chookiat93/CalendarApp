
package Test5Q2;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CalendarFile {

    static File calendarfile = new File("Calendar.txt");

    //method to scan the calendar file and store them as string array for Task Jtable usage later with exception handling
    public static String[] DateListDate() throws IOException {
        Scanner scnr = new Scanner(new FileReader(calendarfile));
        String str;
        ArrayList<String> list = new ArrayList();

        while (scnr.hasNext()) {
            str = scnr.next();
            list.add(str);
        }
        scnr.close();
        String[] L = list.toArray(new String[0]);

        return L;
    }

    //method to scan the calendar file and store them as string array for Task Jtable usage later with exception handling
    public static String[] DateListTask() throws IOException {
        Scanner scnr = new Scanner(new FileReader(
                calendarfile));
        String str;
        ArrayList<String> list = new ArrayList();
        while (scnr.hasNext()) {
            str = scnr.next();
            list.add(str);
        }
        scnr.close();
        String[] L = list.toArray(new String[0]);

        return L;
    }



}
