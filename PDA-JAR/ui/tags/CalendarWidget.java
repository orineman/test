package com.infowave.pda.ui.tags;

import com.infowave.pda.utils.calendar.Attribute;
import com.infowave.pda.utils.calendar.Attributes;
import com.infowave.pda.utils.calendar.Tag;
import com.infowave.pda.utils.date.HostTimeAndDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 03.03.2008
 * Time: 14:54:04
 * Paccage : com.infowave.pda.ui.tags
 */
public class CalendarWidget {
    private HttpServletRequest request;

    private int max = 1;
    public int getMax() { return max; }
    public void setMax(int max) { this.max = max; }
    private boolean first_allday = false;
    private int[] block_index = new int[48];

    public boolean isFirst_allday() {
        return first_allday;
    }

    public void setFirst_allday(boolean first_allday) {
        this.first_allday = first_allday;
    }

    public int[] getBlock_index() {
        return block_index;
    }

    public void setBlock_index(int[] block_index) {
        this.block_index = block_index;
    }

}
