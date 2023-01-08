package Test5Q2;

import java.util.Calendar;

public class DateTimeCal {
    int year;
    int month;
    int day;

    //method to set the year
    public void Setyear(int Y) {
        this.year = Y;
    }

    //method to set the month
    public DateTimeCal(int M) {
        this.month = M;
    }

    //method to calculate the first week day of the month based on month and year given
    public int firstWeekdayofMonth(int month, int year) {
        Calendar cal = Calendar.getInstance();
        int date1=0;
            cal.set(year, month-1,1);
            date1 = cal.get(cal.DAY_OF_WEEK)-1;

        return date1;
    }

    //Used for day box title
    public String toString() {
        String s = this.year + "/" + this.month + "/" + this.day;
        return s;
    }

    //method to declare all months
    public String monthyear() {
        String[] Month = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String currentM = Month[this.month - 1];
        return currentM;
    }
}
