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
    private static String zodiacSign = "";
    private static String zodiacElement = "";
    private static String zodiacPartners = "";
    private static String zodiacCharacteristics = "";
    private static int currentAge = 0;
    private static String monthName = "";
    private static boolean isLeapYear = false;
    private static String astrologicalStrengths = "";
    private static String astrologicalWeaknesses = "";
    private static String astrologicalLikes = "";
    private static String astrologicalDislikes = "";
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
                    astrologicalStrengths = "Intelligent, energetic, humane, and honest";
                    astrologicalWeaknesses = "Distant, lost, detached, alone, outcast";
                    astrologicalLikes = "Fun company, rich and interesting anecdotes, exciting experiences, strange events";
                    astrologicalDislikes = "Intimate relationships, peace of mind, boring activities, responsibilities";
                }
                else
                {
                    astrologicalSign = "Capricorn";
                    astrologicalStrengths = "Ambition, discipline, patience";
                    astrologicalWeaknesses = "Cold, stubborn, Pessimistic";
                    astrologicalLikes = "Successful career, trustworthiness, stable relationships";
                    astrologicalDislikes = "Change, advice, easy routes";
                }
                break;
            case 2: // February
                if (userDay > 18)
                {
                    astrologicalSign = "Pisces";
                    astrologicalStrengths = "Imaginative, sensitive, selfless";
                    astrologicalWeaknesses = "Escapist, unreliable, weak-willed";
                    astrologicalLikes = "Sharing happiness with others, making friends";
                    astrologicalDislikes = "Negative situations, being punctual, easily manipulated";
                }
                else
                {
                    astrologicalSign = "Aquarius";
                    astrologicalStrengths = "Intelligent, energetic, humane, and honest";
                    astrologicalWeaknesses = "Distant, lost, detached, alone, outcast";
                    astrologicalLikes = "Fun company, rich and interesting anecdotes, exciting experiences, strange events";
                    astrologicalDislikes = "Intimate relationships, peace of mind, boring activities, responsibilities";

                }
                break;
            case 3: // March
                if (userDay > 20)
                {
                    astrologicalSign = "Aries";
                    astrologicalStrengths = "Passionate, positive, courageous";
                    astrologicalWeaknesses = "Undisciplined, stubborn, arrogant";
                    astrologicalLikes = "Advancing their work and relationships, to take on problems, help others in need";
                    astrologicalDislikes = "giving up without a fight, mean opinions from others";
                }
                else
                {
                    astrologicalSign = "Pisces";
                    astrologicalStrengths = "Imaginative, sensitive, selfless";
                    astrologicalWeaknesses = "Escapist, unreliable, weak-willed";
                    astrologicalLikes = "Sharing happiness with others, making friends";
                    astrologicalDislikes = "Negative situations, being punctual, easily manipulated";
                }
                break;
            case 4: // April
                if (userDay > 19)
                {
                    astrologicalSign = "Taurus";
                    astrologicalStrengths = "Practical, dependable, patient";
                    astrologicalWeaknesses = "Materialistic, lazy, possessive";
                    astrologicalLikes = "Helping others resolve challenges, not giving up on tough situations";
                    astrologicalDislikes = "Giving away material items, loss, change";
                }
                else
                {
                    astrologicalSign = "Aries";
                    astrologicalStrengths = "Passionate, positive, courageous";
                    astrologicalWeaknesses = "Undisciplined, stubborn, arrogant";
                    astrologicalLikes = "Advancing their work and relationships, to take on problems, help others in need";
                    astrologicalDislikes = "giving up without a fight, mean opinions from others";
                }
                break;
            case 5: // May
                if (userDay > 20)
                {
                    astrologicalSign = "Gemini";
                    astrologicalStrengths = "Intellectual, humorous, enthusiastic";
                    astrologicalWeaknesses = "Inconsistent,impatient, anxious";
                    astrologicalLikes = "Mental stimulation, new ideas and experiences";
                    astrologicalDislikes = "Multitasking, staying in one place, uninterested in others problems";
                }
                else
                {
                    astrologicalSign = "Taurus";
                    astrologicalStrengths = "Practical, dependable, patient";
                    astrologicalWeaknesses = "Materialistic, lazy, possessive";
                    astrologicalLikes = "Helping others resolve challenges, not giving up on tough situations";
                    astrologicalDislikes = "Giving away material items, loss, change";
                }
                break;
            case 6: // June
                if (userDay > 20)
                {
                    astrologicalSign = "Cancer";
                    astrologicalStrengths = "Loving, compassionate, generous";
                    astrologicalWeaknesses = "Oversensitive, clingy, moody";
                    astrologicalLikes = "Listening to others, giving all to those they love";
                    astrologicalDislikes = "Being attached to the past, looking towards the future";
                }
                else
                {
                    astrologicalSign = "Gemini";
                    astrologicalStrengths = "Intellectual, humorous, enthusiastic";
                    astrologicalWeaknesses = "Inconsistent,impatient, anxious";
                    astrologicalLikes = "Mental stimulation, new ideas and experiences";
                    astrologicalDislikes = "Multitasking, staying in one place, uninterested in others problems";
                }
                break;
            case 7: // July
                if (userDay > 22)
                {
                    astrologicalSign = "Leo";
                    astrologicalStrengths = "Warm, loyal, energetic";
                    astrologicalWeaknesses = "Dominating, arrogant, impatient";
                    astrologicalLikes = "Cherishes the people they love, teaching others";
                    astrologicalDislikes = "Listening to others points of view, waiting for others";
                }
                else
                {
                    astrologicalSign = "Cancer";
                    astrologicalStrengths = "Loving, compassionate, generous";
                    astrologicalWeaknesses = "Oversensitive, clingy, moody";
                    astrologicalLikes = "Listening to others, giving all to those they love";
                    astrologicalDislikes = "Being attached to the past, looking towards the future";
                }
                break;
            case 8: // August
                if (userDay > 22)
                {
                    astrologicalSign = "Virgo";
                    astrologicalStrengths = "Intelligent, practical, clean";
                    astrologicalWeaknesses = "Modest, obsessive, critical";
                    astrologicalLikes = "Analyzing information, fixing things that are broken";
                    astrologicalDislikes = "Not getting respect, change if it is already working fine";
                }
                else
                {
                    astrologicalSign = "Leo";
                    astrologicalStrengths = "Warm, loyal, energetic";
                    astrologicalWeaknesses = "Dominating, arrogant, impatient";
                    astrologicalLikes = "Cherishes the people they love, teaching others";
                    astrologicalDislikes = "Listening to others points of view, waiting for others";
                }
                break;
            case 9: // September
                if (userDay > 22)
                {
                    astrologicalSign = "Libra";
                    astrologicalStrengths = "Charming, balanced, diplomatic";
                    astrologicalWeaknesses = "Detached, indecisive, lazy";
                    astrologicalLikes = "Love and approval, saying the right thing at the right time";
                    astrologicalDislikes = "Having compassion for others, being judged or dealing with big egos";
                }
                else
                {
                    astrologicalSign = "Virgo";
                    astrologicalStrengths = "Intelligent, practical, clean";
                    astrologicalWeaknesses = "Modest, obsessive, critical";
                    astrologicalLikes = "Analyzing information, fixing things that are broken";
                    astrologicalDislikes = "Not getting respect, change if it is already working fine";
                }
                break;
            case 10:    // October
                if (userDay > 22)
                {
                    astrologicalSign = "Scorpio";
                    astrologicalStrengths = "Focused, intuitive, deep";
                    astrologicalWeaknesses = "Manipulative, jealous, resentful";
                    astrologicalLikes = "Getting obsessed with completing a goal, being prepared for the worst";
                    astrologicalDislikes = "Being betrayed, forgiving people";
                }
                else
                {
                    astrologicalSign = "Libra";
                    astrologicalStrengths = "Charming, balanced, diplomatic";
                    astrologicalWeaknesses = "Detached, indecisive, lazy";
                    astrologicalLikes = "Love and approval, saying the right thing at the right time";
                    astrologicalDislikes = "Having compassion for others, being judged or dealing with big egos";
                }
                break;
            case 11:    // November
                if (userDay > 21)
                {
                    astrologicalSign = "Sagittarius";
                    astrologicalStrengths = "Generous, optimistic, wise";
                    astrologicalWeaknesses = "Unrealistic, inconsistent, tactless";
                    astrologicalLikes = "Giving things to others, helping others, being open";
                    astrologicalDislikes = "Seeing the world as it truly is, lying to others";
                }
                else
                {
                    astrologicalSign = "Scorpio";
                    astrologicalStrengths = "Focused, intuitive, deep";
                    astrologicalWeaknesses = "Manipulative, jealous, resentful";
                    astrologicalLikes = "Getting obsessed with completing a goal, being prepared for the worst";
                    astrologicalDislikes = "Being betrayed, forgiving people";
                }
                break;
            case 12:    // December
                if (userDay > 21)
                {
                    astrologicalSign = "Capricorn";
                    astrologicalStrengths = "Ambition, discipline, patience";
                    astrologicalWeaknesses = "Cold, stubborn, Pesimistic";
                    astrologicalLikes = "Successful career, trustworthiness, stable relationships";
                    astrologicalDislikes = "Change, advice, easy routes";
                }
                else
                {
                    astrologicalSign = "Sagittarius";
                    astrologicalStrengths = "Generous, optimistic, wise";
                    astrologicalWeaknesses = "Unrealistic, inconsistent, tactless";
                    astrologicalLikes = "Giving things to others, helping others, being open";
                    astrologicalDislikes = "Seeing the world as it truly is, lying to others";
                }
                break;
            default:
                astrologicalSign = "?";
                break;
        }

        return astrologicalSign;
    }

    // Determine Chinese zodiac sign
    public static String findZodiacSign()
    {
        switch (userYear) {
            // Rat
            case 1924:
            case 1936:
            case 1948:
            case 1960:
            case 1972:
            case 1984:
            case 1996:
            case 2008:
            case 2020:
            case 2032:
                zodiacSign = "Rat";
                break;
            // Ox
            case 1925:
            case 1937:
            case 1949:
            case 1961:
            case 1973:
            case 1985:
            case 1997:
            case 2009:
            case 2021:
            case 2033:
                zodiacSign = "Ox";
                break;
            // Tiger
            case 1926:
            case 1938:
            case 1950:
            case 1962:
            case 1974:
            case 1986:
            case 1998:
            case 2010:
            case 2022:
            case 2034:
                zodiacSign = "Tiger";
                break;
            // Rabbit
            case 1927:
            case 1939:
            case 1951:
            case 1963:
            case 1975:
            case 1987:
            case 1999:
            case 2011:
            case 2023:
            case 2035:
                zodiacSign = "Rabbit";
                break;
            // Dragon
            case 1916:
            case 1928:
            case 1940:
            case 1952:
            case 1964:
            case 1976:
            case 1988:
            case 2000:
            case 2012:
            case 2024:
                zodiacSign = "Dragon";
                break;
            // Snake
            case 1917:
            case 1929:
            case 1941:
            case 1953:
            case 1965:
            case 1977:
            case 1989:
            case 2001:
            case 2013:
            case 2025:
                zodiacSign = "Snake";
                break;
            // Horse
            case 1918:
            case 1930:
            case 1942:
            case 1954:
            case 1966:
            case 1978:
            case 1990:
            case 2002:
            case 2014:
            case 2026:
                zodiacSign = "Horse";
                break;
            // Sheep
            case 1919:
            case 1931:
            case 1943:
            case 1955:
            case 1967:
            case 1979:
            case 1991:
            case 2003:
            case 2015:
            case 2027:
                zodiacSign = "Sheep";
                break;
            // Monkey
            case 1920:
            case 1932:
            case 1944:
            case 1956:
            case 1968:
            case 1980:
            case 1992:
            case 2004:
            case 2016:
            case 2028:
                zodiacSign = "Monkey";
                break;
            // Rooster
            case 1921:
            case 1933:
            case 1945:
            case 1957:
            case 1969:
            case 1981:
            case 1993:
            case 2005:
            case 2017:
            case 2029:
                zodiacSign = "Rooster";
                break;
            // Dog
            case 1922:
            case 1934:
            case 1946:
            case 1958:
            case 1970:
            case 1982:
            case 1994:
            case 2006:
            case 2018:
            case 2030:
                zodiacSign = "Dog";
                break;
            // Pig (Boar)
            case 1923:
            case 1935:
            case 1947:
            case 1959:
            case 1971:
            case 1983:
            case 1995:
            case 2007:
            case 2019:
            case 2031:
                zodiacSign = "Pig (Boar)";
                break;
            default:
                zodiacSign = "?";
                break;
        }

        return zodiacSign;
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
