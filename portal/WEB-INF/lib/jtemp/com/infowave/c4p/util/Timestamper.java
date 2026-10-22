package com.infowave.c4p.util;

import java.util.Date;


/**
 * This class is responsible for generating unique Ids.
 */
public class Timestamper
{
    public static String logDelta( Date start, Date finish )
    {
	long delta = finish.getTime() - start.getTime();
	return "delta time = " +delta+ " milliseconds";
    }

}
