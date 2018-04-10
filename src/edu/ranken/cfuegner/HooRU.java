package edu.ranken.cfuegner;

public class HooRU
{ // START public class HooRU

    // Declare and initialize GLOBAL (CLASS) program constants
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
        // MIN/MAX Ranges of variables
        private static final int MIN_YEAR   = 1900;
        private static final int MAX_YEAR   = 2018;
        private static final int MIN_MONTH  = 1;
        private static final int MAX_MONTH  = 12;
        private static final int MIN_DAY    = 1;
        private static final int MAX_DAY28  = 28;
        private static final int MAX_DAY29  = 29;
        private static final int MAX_DAY30  = 30;
        private static final int MAX_DAY31  = 31;

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









} // END public class HooRU
