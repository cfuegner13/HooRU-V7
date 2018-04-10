package edu.ranken.cfuegner;

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



    private static final int MINMONTH = 1;
    private static final int MAXMONTH = 12;
    private static final int MINDAY = 1;
    private static final int MAXDAY28 = 28;
    private static final int MAXDAY29 = 29;
    private static final int MAXDAY30 = 30;
    private static final int MAXDAY31 = 31;
    private static final int MINYEAR = 1900;
    private static final int MAXYEAR = 2018;


    // Declare and Initialize Global Variables
    private static int day = 0;
    private static int month = 0;
    private static int year = 0;
    private static String currentAge;
    private static String name;
    private static String astrologicalSign;
    private static String zodiacInformation;
    private static String monthName;
    private static boolean isLeapYear = false;
    private static boolean again = true;

    //








} // END public class HooRU
