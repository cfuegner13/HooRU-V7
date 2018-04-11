package edu.ranken.cfuegner;

import javax.swing.*;
import java.time.LocalDate;

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
    private static final String ERROR_INPUT_NAME      = "Input invalid, Name input must be alphanumeric characters only! (EX: Colin)";
    private static final String ERROR_INPUT_YEAR      = "Input invalid, Year input must be numbers only! (EX: 1956)";
    private static final String ERROR_INPUT_MONTH     = "Input invalid, Month input must be numbers only! (EX: 10)";
    private static final String ERROR_INPUT_DAY       = "Input invalid, Day input must be numbers only! (EX: 5)";
    // Validity Messages
    private static final String NAME_INPUT_VALID  = "Name input was valid, Thank You!";
    private static final String YEAR_INPUT_VALID  = "Year input was valid, Thank You!";
    private static final String MONTH_INPUT_VALID = "Month input was valid, Thank You!";
    private static final String DAY_INPUT_VALID   = "Day input was valid, Thank You!";
    // MIN/MAX Range Constants
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
    private static int monthMaxDays = 0;
    private static String astrologicalSign = "";
    private static String dayOfWeek = "";
    private static String zodiacInformation = "";
    private static int currentAge = 0;
    private static String monthName = "";
    private static boolean isLeapYear = false;
    // Loop Control
    private static boolean again = false;
    private static boolean lcv1 = false;

    //******************************************************************

    // Main Driver
    public static void main(String[] args)
    { // START main(String[] args)

        // Local variables (TO: Main)


        // Do/While Loop
        do
        {
            userName = getUserName();
            userYear = inputBirthYear();
            isLeapYear = leapYearCalculation();
            userMonth = inputBirthMonth();
            findMonthName();
            monthMaxDays = calculateMaxDaysInMonth();
            userDay = inputBirthDay();
            dayOfWeek = calculateDayOfWeek();
            findAstrologicalSign();
            // dayOfTheWeekCalculation();
            displayBirthDay();
            again = inputRunProgram();
        }
        while (again);

        System.exit(0);

    } // END main(String[] args)

    //******************************************************************

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
                JOptionPane.showMessageDialog(null, ERROR_BLANK_NAME);
                System.out.println(ERROR_BLANK_NAME);
                validInput = false;
            }
            // Check for non-alphanumeric input
            else if (inputStr.matches("^[A-Za-z]+$"))
            {
                System.out.println(NAME_INPUT_VALID);
                localUserName = inputStr;
                validInput = true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, ERROR_INPUT_NAME);
                System.out.println(ERROR_INPUT_NAME);
                validInput = false;
            }
        }

        // return string
        return localUserName;
    }

    //******************************************************************

    // Input birth year and validate
    public static int inputBirthYear()
    { // START inputBirthYear()

        // Local variables (TO: getUserYear)
        String inputStr = "";
        int localUserYear = 0;
        boolean validInput = false;

        while (validInput == false)
        {
            inputStr = JOptionPane.showInputDialog("Please enter your birth year in the format #### \n (Between " +
                    MINYEAR + " - " + MAXYEAR + ") \n Your birth year: ");

            // Check for blank input
            if (inputStr.equals(""))
            {
                JOptionPane.showMessageDialog(null, ERROR_INPUT_YEAR);
                System.out.println(ERROR_BLANK_YEAR);
                validInput = false;
            }

            // Check for non-numeric input
            else if (inputStr.matches("^[0-9]+$"))
            {
                localUserYear = Integer.parseInt(inputStr);

                // Check for out-of-range input
                if ((localUserYear < MINYEAR) || (localUserYear > MAXYEAR))
                {
                    JOptionPane.showMessageDialog(null, ERROR_OOR_YEAR);
                    System.out.println(ERROR_OOR_YEAR);
                    validInput = false;
                }

                // If program reaches here, user entered a valid birth year with no errors
                else
                {
                    System.out.println(YEAR_INPUT_VALID);
                    validInput = true;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, ERROR_INPUT_YEAR);
                System.out.println(ERROR_INPUT_YEAR);
                validInput = false;
            }

        }

        // Return the localuseryear in absolute value
        return Math.abs(localUserYear);

    } // END inputBirthYear()

    //******************************************************************

    public static boolean leapYearCalculation()
    { // START leapYearCalculation(int uy)

        // Local variables (TO: leapYearCalculation)
        boolean localIsLeapYear = false;
        int passedUserYear = userYear;

        // Leap year calculation
        if ((passedUserYear % 4 == 0) && (passedUserYear % 100 != 0) || (passedUserYear % 400 == 0))
        {
            localIsLeapYear = true;
        }
        else
        {
            localIsLeapYear = false;
        }

        // Display user awareness information
        if (localIsLeapYear == true)
        {
            System.out.println("\nYour birth year IS a leap year.");
        }
        else if (localIsLeapYear == false)
        {
            System.out.println("\nYour birth year IS NOT a leap year.");
        }

        // Return boolean
        return localIsLeapYear;

    } // END leapYearCalculation(int uy)

    //******************************************************************

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
                JOptionPane.showMessageDialog(null, ERROR_BLANK_MONTH);
                System.out.println(ERROR_BLANK_MONTH);
                validInput = false;
            }

            // Check for non-numeric input
            else if (inputStr.matches("^[0-9]+$"))
            {
                localUserMonth = Integer.parseInt(inputStr);

                // Check for out-of-range input
                if ((localUserMonth < MINMONTH) || (localUserMonth > MAXMONTH))
                {
                    JOptionPane.showMessageDialog(null, ERROR_OOR_MONTH);
                    System.out.println(ERROR_OOR_MONTH);
                    validInput = false;
                }
                // If program reaches here, user entered a valid birth month with no errors
                else
                {

                    validInput = true;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, ERROR_INPUT_MONTH);
                System.out.println(ERROR_INPUT_MONTH);
                validInput = false;
            }
        }

        // Return int
        return localUserMonth;

    }

    //******************************************************************

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

    //******************************************************************

    // Find the max and min days in each month
    public static int calculateMaxDaysInMonth()
    { // START calculateMaxDaysInMonth()

        // Local variables (TO: calculateMaxDaysInMonth)
        int passedUserMonth = userMonth;
        boolean passedIsLeapYear = isLeapYear;
        int numOfDaysMax = 0;

        // Calculate max days in month
        switch (passedUserMonth)
        {
            case 1:
                numOfDaysMax = 31;
                break;
            case 2:
                if (passedIsLeapYear == true)
                {
                    numOfDaysMax = 29;
                }
                else
                {
                    numOfDaysMax = 28;
                }
                break;
            case 3:
                numOfDaysMax = 31;
                break;
            case 4:
                numOfDaysMax = 30;
                break;
            case 5:
                numOfDaysMax = 31;
                break;
            case 6:
                numOfDaysMax = 30;
                break;
            case 7:
                numOfDaysMax = 31;
                break;
            case 8:
                numOfDaysMax = 31;
                break;
            case 9:
                numOfDaysMax = 30;
                break;
            case 10:
                numOfDaysMax = 31;
                break;
            case 11:
                numOfDaysMax = 30;
                break;
            case 12:
                numOfDaysMax = 31;
                break;
            default:
                break;
        }

        // Display user awareness information
        System.out.println("\nYour birth month's max day is: " + numOfDaysMax);

        // Return int
        return numOfDaysMax;

    } // END calculateMaxDaysInMonth()

    //******************************************************************

    // Input birth day with loop for invalid entry
    public static int inputBirthDay()
    {
        // Local variables (TO: getUserDay)
        String inputStr = "";
        int localUserDay = 0;
        boolean validInput = false;


        while (validInput == false)
        {
            inputStr = JOptionPane.showInputDialog("Please enter your birth day in the format ##, \n " +
                    "(between " + MINDAY + " - " + monthMaxDays + ") \n Your birth day: ");

            // Check for blank input
            if (inputStr.equals(""))
            {
                JOptionPane.showMessageDialog(null, ERROR_BLANK_DAY);
                System.out.println(ERROR_BLANK_DAY);
                validInput = false;
            }

            // Check for non-numeric input
            else if (!inputStr.matches("^[0-9]+$"))
            {
                JOptionPane.showMessageDialog(null, ERROR_INPUT_DAY);
                System.out.println(ERROR_INPUT_DAY);
                validInput = false;
            }
            else
            {
                // Convert user input to int format
                localUserDay = Integer.parseInt(inputStr);

                // Check for out-of-range input
                if ((localUserDay < MINDAY) || (localUserDay > monthMaxDays))
                {
                    JOptionPane.showMessageDialog(null, ERROR_OOR_DAY);
                    System.out.println(ERROR_OOR_DAY);
                    validInput = false;
                }

                // If program reaches here, user entered a valid birth DAY with no errors
                else
                {
                    System.out.println(DAY_INPUT_VALID);
                    validInput = true;
                }
            }
        }

        // Return int in absolute value format
        return Math.abs(localUserDay);
    }

    //******************************************************************

    // Calculate the day of the week (BONUS)
    public static String calculateDayOfWeek()
    { // START calculateDayOfWeek()

        // Local variables (TO: calculateDayOfWeek)
        String localDayOfWeek = "";

        // Calculate day of week
        localDayOfWeek = LocalDate.of(userYear, userMonth, userDay).getDayOfWeek().toString();

        // Display user awareness information
        System.out.println("\nYour birth DAY falls on a: " + localDayOfWeek);

        // Return String
        return localDayOfWeek;

    } // END calculateDayOfWeek()

    //******************************************************************


    // Find Astrological Sign
    public static String findAstrologicalSign() {
        switch (userMonth) {
            case 1: // January
                if (userDay > 19)
                {
                    astrologicalSign = "Aquarius";
                }
                else
                {
                    astrologicalSign = "Capricorn";
                }
                break;
            case 2: // February
                if (userDay > 18)
                {
                    astrologicalSign = "Pisces";
                }
                else
                {
                    astrologicalSign = "Aquarius";
                }
                break;
            case 3: // March
                if (userDay > 20)
                {
                    astrologicalSign = "Aries";
                }
                else
                {
                    astrologicalSign = "Pisces";
                }
                break;
            case 4: // April
                if (userDay > 19)
                {
                    astrologicalSign = "Taurus";
                }
                else
                {
                    astrologicalSign = "Aries";
                }
                break;
            case 5: // May
                if (userDay > 20)
                {
                    astrologicalSign = "Gemini";
                }
                else
                {
                    astrologicalSign = "Taurus";
                }
                break;
            case 6: // June
                if (userDay > 20)
                {
                    astrologicalSign = "Cancer";
                }
                else
                {
                    astrologicalSign = "Gemini";
                }
                break;
            case 7: // July
                if (userDay > 22)
                {
                    astrologicalSign = "Leo";
                }
                else
                {
                    astrologicalSign = "Cancer";
                }
                break;
            case 8: // August
                if (userDay > 22)
                {
                    astrologicalSign = "Virgo";
                }
                else
                {
                    astrologicalSign = "Leo";
                }
                break;
            case 9: // September
                if (userDay > 22)
                {
                    astrologicalSign = "Libra";
                }
                else
                {
                    astrologicalSign = "Virgo";
                }
                break;
            case 10:    // October
                if (userDay > 22)
                {
                    astrologicalSign = "Scorpio";
                }
                else
                {
                    astrologicalSign = "Libra";
                }
                break;
            case 11:    // November
                if (userDay > 21)
                {
                    astrologicalSign = "Sagittarius";
                }
                else
                {
                    astrologicalSign = "Scorpio";
                }
                break;
            case 12:    // December
                if (userDay > 21)
                {
                    astrologicalSign = "Capricorn";
                }
                else
                {
                    astrologicalSign = "Sagittarius";
                }
                break;
            default:
                astrologicalSign = "?";
                break;
        }

        return astrologicalSign;
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
    //hi








} // END public class HooRU
