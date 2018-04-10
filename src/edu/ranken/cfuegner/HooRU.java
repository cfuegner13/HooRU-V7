package edu.ranken.cfuegner;

import javax.swing.*;

public class HooRU
{ // START public class HooRU

    // Global Constants
        // Blank/Empty Input Error Messages
        private static final String ERROR_BLANK_NAME      = "Blank Input, Name field cannot be left blank!";
        private static final String ERROR_BLANK_YEAR      = "Blank Input, Year field cannot be left blank!";
        private static final String ERROR_BLANK_MONTH     = "Blank Input, Month field cannot be left blank!";
        private static final String ERROR_BLANK_DAY       = "Blank Input, Day field cannot be left blank!";
        // OOR (Out of Range) Input Error Messages
        private static final String ERROR_OOR_YEAR        = "Input invalid, Please enter a year between 1900 and 2018. (EX: 1956)";
        private static final String ERROR_OOR_MONTH       = "Input invalid, Please enter a month between 1 - 12. (EX: 10)";
        private static final String ERROR_OOR_DAY         = "Input invalid, Please enter a day between 1 - "; // Intelligent day range knowing, if the month has 28 - 31 days.
        // Non-Numerical Input Error Messages
        private static final String ERROR_INPUT_YEAR      = "Input invalid, Year input must be numbers only! (EX: 1956)";
        private static final String ERROR_INPUT_MONTH     = "Input invalid, Month input must be numbers only! (EX: 10)";
        private static final String ERROR_INPUT_DAY       = "Input invalid, Day input must be numbers only! (EX: 5)";
        // Class constants
        private static final int MINMONTH = 1;
        private static final int MAXMONTH = 12;
        private static final int MINDAY = 1;
        private static final int MAXDAY28 = 28;
        private static final int MAXDAY29 = 29;
        private static final int MAXDAY30 = 30;
        private static final int MAXDAY31 = 31;
        private static final int MINYEAR = 1900;
        private static final int MAXYEAR = 2018;

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

    // Main Driver
    public static void main(String[] args)
    { // START main(String[] args)

        // Local variables (TO: Main)


        // Do/While Loop
        do
            {
                userName = getUserName();
                userMonth = inputBirthMonth();
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

    } // END main(String[] args)

    // Get User Name
    public static String getUserName()
    {
        // Local variables (TO: getUserName)
        String inputStr = "";
        String localUserName = "";
        boolean validInput = false;

        while (validInput == false)
        {
            // Get user input in String format
            inputStr = JOptionPane.showInputDialog("Please enter your name: ");

            // Check for blank input
            if (inputStr.equals(""))
            {
                System.out.println(ERROR_BLANK_NAME);
                validInput = false;
            }
            else
            {
                localUserName = inputStr;
                validInput = true;
            }
        }

        // return string
        return localUserName;
    }


    // Input birth month with loop for invalid entry
    public static int inputBirthMonth()
    {
        // Local variables (TO: getUserMonth)
        String inputStr = "";
        int localUserMonth = 0;
        boolean validInput = false;

        while (validInput == false)
        {
            // Get user input in String format
            inputStr = JOptionPane.showInputDialog("Please enter your birth month number (between " +
                    MINMONTH + " - " + MAXMONTH + "): ");

            // Check for blank input
            if (inputStr.equals(""))
            {
                System.out.println(ERROR_BLANK_MONTH);
                validInput = false;
            }
            else
            {
                localUserMonth = Integer.parseInt(inputStr);

                // Check for out-of-range input
                if ((localUserMonth < MINMONTH) || (localUserMonth > MAXMONTH))
                {
                    System.out.println(ERROR_OOR_MONTH);
                    validInput = false;
                }
                // If program reaches here, user entered a valid birth month with no errors
                else
                {

                    validInput = true;
                }

            }
        }

        // Return int
        return localUserMonth;

    }

    // Find monthName based on number entered by user
    public static String findMonthName()
    {
        switch (userMonth)
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
        userYear = Integer.parseInt(inputStr);

        while ((userYear < MINYEAR) || (userYear > MAXYEAR))
        {
            inputStr = JOptionPane.showInputDialog(ERROR_BLANK_YEAR + "Please enter your birth year (between " +
                    MINYEAR + " - " + MAXYEAR + "): ");
            userYear = Integer.parseInt(inputStr);
        }

        return Math.abs(userYear);
    }

    // Calculate if user inputted year is a leap year
    public static boolean leapYearCalculation()
    {
        boolean ly = ((userYear % 4 == 0) && (userYear % 100 != 0) || (userYear % 400 == 0));

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
        if (userMonth == 2)
        {
            String inputStr = JOptionPane.showInputDialog("Please enter your birth day (between " +
                    MINDAY + " - " + MAXDAY28 + "): ");
            userDay  = Integer.parseInt(inputStr);

            while ((userDay  < MINDAY) || (userDay  > MAXDAY28)) {
                inputStr = JOptionPane.showInputDialog(ERROR_BLANK_YEAR + "Please enter your birth day (between " +
                        MINDAY + " - " + MAXDAY28 + "): ");
                userDay  = Integer.parseInt(inputStr);
            }

            if (isLeapYear)
            {
                inputStr = JOptionPane.showInputDialog("Please enter your birth day (between " +
                        MINDAY + " - " + MAXDAY29 + "): ");
                userDay  = Integer.parseInt(inputStr);

                while ((userDay  < MINDAY) || (userDay  > MAXDAY29)) {
                    inputStr = JOptionPane.showInputDialog(ERROR_BLANK_YEAR + "Please enter your birth day (between " +
                            MINDAY + " - " + MAXDAY29 + "): ");
                    userDay  = Integer.parseInt(inputStr);
                }
            }
        }

        else if (userMonth == 4 || userMonth == 6 || userMonth == 9 || userMonth == 11)
        {
            String inputStr = JOptionPane.showInputDialog("Please enter your birth day (between " +
                    MINDAY + " - " + MAXDAY30 + "): ");
            userDay  = Integer.parseInt(inputStr);

            while ((userDay  < MINDAY) || (userDay  > MAXDAY30)) {
                inputStr = JOptionPane.showInputDialog(ERROR_BLANK_YEAR + "Please enter your birth day (between " +
                        MINDAY + " - " + MAXDAY30 + "): ");
                userDay  = Integer.parseInt(inputStr);
            }

            return Math.abs(userDay );
        }

        else    // (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        {
            String inputStr = JOptionPane.showInputDialog("Please enter your birth day (between " +
                    MINDAY + " - " + MAXDAY31 + "): ");
            userDay  = Integer.parseInt(inputStr);

            while ((userDay < MINDAY) || (userDay  > MAXDAY31))
            {
                inputStr = JOptionPane.showInputDialog(ERROR_BLANK_YEAR + "Please enter your birth day (between " +
                        MINDAY + " - " + MAXDAY31 + "): ");
                userDay  = Integer.parseInt(inputStr);
            }
        }

        return Math.abs(userDay );
    }

    // Find monthName based on number entered by user
    public static String findAstrologicalSign() {
        switch (userYear) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
            default:
                monthName = "?";
                break;
        }

        return monthName;
    }

    public static void displayBirthDay()
    {
        String outputStr = "";
        outputStr += monthName + " " + userDay  + ", " + userYear + "\n";
        outputStr += userMonth + "/" + userDay  + "/" + userYear;

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








} // END public class HooRU
