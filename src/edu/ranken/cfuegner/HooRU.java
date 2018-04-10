package edu.ranken.cfuegner;

public class HooRU
{ // START public class HooRU

    // Global Constants
    private static String ERROR_BLANK_NAME  = "Blank Input, Name field cannot be left blank!";
    private static String ERROR_BLANK_YEAR  = "Blank Input, Year field cannot be left blank!";
    private static String ERROR_BLANK_MONTH  = "Blank Input, Year field cannot be left blank!";
    private static String ERROR_YEAR        = "Input invalid, Please enter a year between 1900 and 2018.";
    private static String ERROR_MONTH       = "Input invalid, Please enter a month between 1 - 12.";
    private static String ERROR_DAY         = "Input invalid, Please enter a day between 1 - ";



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
    private static int birthMonth = 0;

} // END public class HooRU
