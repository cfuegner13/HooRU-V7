package edu.ranken.cfuegner;

import javax.swing.*;

public class HooRU
{ // START public class HooRU

    // Global Constants
        // Blank/Empty Input Error Messages
        private static String ERROR_BLANK_NAME      = "Blank Input, Name field cannot be left blank!";
        private static String ERROR_BLANK_YEAR      = "Blank Input, Year field cannot be left blank!";
        private static String ERROR_BLANK_MONTH     = "Blank Input, Month field cannot be left blank!";
        private static String ERROR_BLANK_DAY       = "Blank Input, Day field cannot be left blank!";
        // OOR (Out of Range) Input Error Messages
        private static String ERROR_OOR_YEAR        = "Input invalid, Please enter a year between 1900 and 2018. (EX: 1956)";
        private static String ERROR_OOR_MONTH       = "Input invalid, Please enter a month between 1 - 12. (EX: 10)";
        private static String ERROR_OOR_DAY         = "Input invalid, Please enter a day between 1 - "; // Intelligent day range knowing, if the month has 28 - 31 days.
        // Non-Numerical Input Error Messages
        private static String ERROR_INPUT_YEAR      = "Input invalid, Year input must be numbers only! (EX: 1956)";
        private static String ERROR_INPUT_MONTH     = "Input invalid, Month input must be numbers only! (EX: 10)";
        private static String ERROR_INPUT_DAY       = "Input invalid, Day input must be numbers only! (EX: 5)";

    // Declare and initialize GLOBAL (CLASS) program variables
        // User Info
        private static String userName = "";
        private static int userDay = 0;
        private static int userMonth = 0;
        private static int userYear = 0;
        // Other Info
        private static String astrologicalSign = "";
        private static String zodiacInformation = "";
        private static int currentAge = 0;
        private static String monthName = "";
        private static boolean isLeapYear = false;
        // Loop Control
        private static boolean again = false;
        private static boolean lcv1 = false;

    // Input birth month with loop for invalid entry
    public static int inputBirthMonth()
    {
        String inputStr = JOptionPane.showInputDialog("Please enter your birth month number (between " +
                MINMONTH + " - " + MAXMONTH + "): ");
        month = Integer.parseInt(inputStr);

        while ((month < MINMONTH) || (month > MAXMONTH))
        {
            inputStr = JOptionPane.showInputDialog(ERRORMESSAGE + "Please enter your birth month number (between " +
                    MINMONTH + " - " + MAXMONTH + "): ");
            month = Integer.parseInt(inputStr);
        }

        return month;
    }

    // Find monthName based on number entered by user
    public static String findMonthName()
    {
        switch (month)
        {
            case 1: monthName = "January";
                break;
            case 2: monthName = "February";
                break;
            case 3: monthName = "March";
                break;
            case 4: monthName = "April";
                break;
            case 5: monthName = "May";
                break;
            case 6: monthName = "June";
                break;
            case 7: monthName = "July";
                break;
            case 8: monthName = "August";
                break;
            case 9: monthName = "September";
                break;
            case 10: monthName = "October";
                break;
            case 11: monthName = "November";
                break;
            case 12: monthName = "December";
                break;
            default: monthName = "?";
                break;
        }

        return monthName;
    }

    // Input birth year with loop for invalid entry
    public static int inputBirthYear()
    {
        String inputStr = JOptionPane.showInputDialog("Please enter your birth year (between " +
                MINYEAR + " - " + MAXYEAR + "): ");
        year = Integer.parseInt(inputStr);

        while ((year < MINYEAR) || (year > MAXYEAR))
        {
            inputStr = JOptionPane.showInputDialog(ERRORMESSAGE + "Please enter your birth year (between " +
                    MINYEAR + " - " + MAXYEAR + "): ");
            year = Integer.parseInt(inputStr);
        }

        return Math.abs(year);
    }

    // Calculate if user inputted year is a leap year
    public static boolean leapYearCalculation()
    {
        boolean ly = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));

        if (ly)
        {
            isLeapYear = true;
        }
        else
        {
            isLeapYear = false;
        }

        return isLeapYear;
    }

    // Input birth day with loop for invalid entry
    public static int inputBirthDay()
    {
        if (month == 2)
        {
            String inputStr = JOptionPane.showInputDialog("Please enter your birth day (between " +
                    MINDAY + " - " + MAXDAY28 + "): ");
            day = Integer.parseInt(inputStr);

            while ((day < MINDAY) || (day > MAXDAY28)) {
                inputStr = JOptionPane.showInputDialog(ERRORMESSAGE + "Please enter your birth day (between " +
                        MINDAY + " - " + MAXDAY28 + "): ");
                day = Integer.parseInt(inputStr);
            }

            if (isLeapYear)
            {
                inputStr = JOptionPane.showInputDialog("Please enter your birth day (between " +
                        MINDAY + " - " + MAXDAY29 + "): ");
                day = Integer.parseInt(inputStr);

                while ((day < MINDAY) || (day > MAXDAY29)) {
                    inputStr = JOptionPane.showInputDialog(ERRORMESSAGE + "Please enter your birth day (between " +
                            MINDAY + " - " + MAXDAY29 + "): ");
                    day = Integer.parseInt(inputStr);
                }
            }
        }

        else if (month == 4 || month == 6 || month == 9 || month == 11)
        {
            String inputStr = JOptionPane.showInputDialog("Please enter your birth day (between " +
                    MINDAY + " - " + MAXDAY30 + "): ");
            day = Integer.parseInt(inputStr);

            while ((day < MINDAY) || (day > MAXDAY30)) {
                inputStr = JOptionPane.showInputDialog(ERRORMESSAGE + "Please enter your birth day (between " +
                        MINDAY + " - " + MAXDAY30 + "): ");
                day = Integer.parseInt(inputStr);
            }

            return Math.abs(day);
        }

        else    // (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        {
            String inputStr = JOptionPane.showInputDialog("Please enter your birth day (between " +
                    MINDAY + " - " + MAXDAY31 + "): ");
            day = Integer.parseInt(inputStr);

            while ((day < MINDAY) || (day > MAXDAY31))
            {
                inputStr = JOptionPane.showInputDialog(ERRORMESSAGE + "Please enter your birth day (between " +
                        MINDAY + " - " + MAXDAY31 + "): ");
                day = Integer.parseInt(inputStr);
            }
        }

        return Math.abs(day);
    }

    public static void displayBirthDay()
    {
        String outputStr = "";
        outputStr += monthName + " " + day + ", " + year + "\n";
        outputStr += month + "/" + day + "/" + year;

        JOptionPane.showMessageDialog(null, outputStr);
    }

    // Ask user if they would like to run the program again
    public static boolean inputRunProgram()
    {
        char firstChar = ' ';

        String inputStr = JOptionPane.showInputDialog("Would you like to run the program again? (Y/N): ");
        inputStr = inputStr.toUpperCase();

        firstChar = inputStr.charAt(0);

        if (firstChar != 'Y')
        {
            return false;
        }

        return true;
    }

    // Program Main
    public static void main(String[] args)
    {
        do
        {
            inputBirthMonth();
            findMonthName();
            inputBirthYear();
            leapYearCalculation();
            inputBirthDay();
            // dayOfTheWeekCalculation();
            displayBirthDay();
            again = inputRunProgram();
        }
        while (again);

        System.exit(0);







} // END public class HooRU
