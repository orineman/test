package com.infowave.pda.ui.tags;


import com.infowave.pda.utils.calendar.Attribute;
import com.infowave.pda.utils.calendar.Attributes;
import com.infowave.pda.utils.calendar.Tag;
import com.infowave.pda.utils.date.HostTimeAndDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.io.IOException;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 05.03.2008
 * Time: 12:55:45
 * Paccage : com.infowave.pda.ui.tags
 */
public final class CalendarTag extends TagSupport {

    public int doStartTag() {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        int curr_month;
        int curr_day;
        int curr_year;
        HttpSession session = request.getSession();
        //Get calendar in users locale
        //Get calendar in users locale
        Calendar cal = Calendar.getInstance(Locale.US);
        int first = cal.getFirstDayOfWeek();
        DateFormatSymbols dfs = new DateFormatSymbols(Locale.US);
        String[] weekdays = dfs.getWeekdays();
        cal.setTime(new Date());
        //Setting date
        if (request.getParameter("start") != null) {
            curr_month = Integer.parseInt(HostTimeAndDate.getMonthFromFileTime(request.getParameter("start")));
            curr_day = Integer.parseInt(HostTimeAndDate.getDayFromFileTime(request.getParameter("start")));
            curr_year = Integer.parseInt(HostTimeAndDate.getYearFromFileTime(request.getParameter("start")));
            cal.set(Calendar.YEAR, curr_year);
            cal.set(Calendar.MONTH, curr_month - 1);
            cal.set(Calendar.DAY_OF_MONTH, curr_day);
        } else {
            curr_month = cal.get(Calendar.MONTH)+1;
            curr_day = cal.get(Calendar.DAY_OF_MONTH);
            curr_year = cal.get(Calendar.YEAR);
        }

        Tag table = new com.infowave.pda.utils.calendar.Tag("table", "border=1 cellpadding=3 width=\"80%\" cellspacing=1");
        // create header with month declaration and links to next and previous month
        com.infowave.pda.utils.calendar.Tag month_header = new com.infowave.pda.utils.calendar.Tag("tr");

        //left year link

        com.infowave.pda.utils.calendar.Tag left_year_link = new com.infowave.pda.utils.calendar.Tag("td", "align=center");
        Long prev_year_start = HostTimeAndDate.getDateForDMY(String.valueOf(curr_day),
                String.valueOf(curr_month),
                String.valueOf(curr_year - 1));
        Long prev_year_end = HostTimeAndDate.getDateForDMY(
                String.valueOf(curr_day + 1), String.valueOf(curr_month),
                String.valueOf(curr_year - 1));

        com.infowave.pda.utils.calendar.Tag l2 = new com.infowave.pda.utils.calendar.Tag("a", "href=\"/pda/GetEventList.do?folderID=" +
                pageContext.getAttribute("folderID") +
                "&amp;start=" + prev_year_start + "&amp;end=" + prev_year_end + "\"");
        l2.addBody("&lt;&lt;");
        left_year_link.add(l2);
        month_header.add(left_year_link);

        // left link
        com.infowave.pda.utils.calendar.Tag left_link = new com.infowave.pda.utils.calendar.Tag("td", "align=center");
        Long prev_start = HostTimeAndDate.getDateForDMY(String.valueOf(curr_day),
                String.valueOf(curr_month - 1),
                String.valueOf(curr_year));
        Long prev_end = HostTimeAndDate.getDateForDMY(
                String.valueOf(curr_day + 1), String.valueOf(curr_month - 1),
                String.valueOf(curr_year));

        com.infowave.pda.utils.calendar.Tag l1 = new com.infowave.pda.utils.calendar.Tag("a", "href=\"/pda/GetEventList.do?folderID=" +
                pageContext.getAttribute("folderID") +
                "&amp;start=" + prev_start + "&amp;end=" + prev_end + "\"");
        l1.addBody("&lt;");
        left_link.add(l1);
        month_header.add(left_link);

        // center

        com.infowave.pda.utils.calendar.Tag center = new com.infowave.pda.utils.calendar.Tag("td", "colspan=3 align=center");
        String d = DateFormat.getDateInstance(DateFormat.LONG, (Locale) session.getAttribute("org.apache.struts.action.LOCALE")).format(cal.getTime());
        center.addBody(d);
        month_header.add(center);

        
        //right link
        com.infowave.pda.utils.calendar.Tag right_link = new com.infowave.pda.utils.calendar.Tag("td", "align=center");
        Long next_start = HostTimeAndDate.getDateForDMY(String.valueOf(curr_day),
                String.valueOf(curr_month + 1),
                String.valueOf(curr_year));
        Long next_end = HostTimeAndDate.getDateForDMY(
                String.valueOf(curr_day + 1), String.valueOf(curr_month + 1),
                String.valueOf(curr_year));
        com.infowave.pda.utils.calendar.Tag r1 = new com.infowave.pda.utils.calendar.Tag("a", "href=\"/pda/GetEventList.do?folderID=" +
                pageContext.getAttribute("folderID") +
                "&amp;start=" + next_start + "&amp;end=" + next_end + "\"");
        r1.addBody("&gt;");
        right_link.add(r1);
        month_header.add(right_link);

        //right year link
        com.infowave.pda.utils.calendar.Tag right_year_link = new com.infowave.pda.utils.calendar.Tag("td", "align=center");
        Long next_year_start = HostTimeAndDate.getDateForDMY(String.valueOf(curr_day),
                String.valueOf(curr_month),
                String.valueOf(curr_year + 1));
        Long next_year_end = HostTimeAndDate.getDateForDMY(
                String.valueOf(curr_day + 1), String.valueOf(curr_month),
                String.valueOf(curr_year + 1));
        com.infowave.pda.utils.calendar.Tag r2 = new com.infowave.pda.utils.calendar.Tag("a", "href=\"/pda/GetEventList.do?folderID=" +
                pageContext.getAttribute("folderID") +
                "&amp;start=" + next_year_start + "&amp;end=" + next_year_end + "\"");
        r2.addBody("&gt;&gt;");
        right_year_link.add(r2);
        month_header.add(right_year_link);

        table.add(month_header);
        // create seven rows of seven columns each
        com.infowave.pda.utils.calendar.Tag header = new com.infowave.pda.utils.calendar.Tag("tr");
        int NDAYS = 7;
        for (int j = 0; j < NDAYS; j++) {
            com.infowave.pda.utils.calendar.Tag day = new com.infowave.pda.utils.calendar.Tag("td", "align=center");

            day.addBody(weekdays[(first++)].substring(0, 3));

            if (first > NDAYS) {
                first = 1;
            }

            header.add(day);
        }
        table.add(header);

        // fill table with empty cells for days
        int NROWS = cal.getActualMaximum(Calendar.WEEK_OF_MONTH)+2;
        for (int i = 0; i < NROWS - 1; i++) {
            com.infowave.pda.utils.calendar.Tag tr = new com.infowave.pda.utils.calendar.Tag("tr", "align=center");
            for (int j = 0; j < NDAYS; j++) {
                //height 10%
                com.infowave.pda.utils.calendar.Tag cell = new com.infowave.pda.utils.calendar.Tag("td", "align=center valign=top bgcolor=#eeeeee");
                com.infowave.pda.utils.calendar.Tag fonttag = new com.infowave.pda.utils.calendar.Tag("font", "size=" + request.getAttribute("fontSize"));
                cell.add(fonttag);
                cell.setBody("&nbsp;");
                tr.add(cell);
            }
            table.add(tr);
        }
        // fill int days
        int y = 0;
        int month = cal.get(Calendar.MONTH);
        cal.set(Calendar.DAY_OF_MONTH,1);
        int refl = cal.get(Calendar.WEEK_OF_MONTH);
  //TODO: Made invisible items in calendar
/*        {
            cal.set(Calendar.DAY_OF_MONTH, 1);
            int xday = cal.get(Calendar.DAY_OF_WEEK);
            int x = cal.get(Calendar.DAY_OF_MONTH) - xday;
            //fiiling prev month blank cellls
            if (xday > 1) {
                for (int i = 0; i < xday; i++) {
                    cal.set(Calendar.DAY_OF_MONTH, x++);
                    Tag cell =
                            new com.infowave.pda.utils.calendar.Tag("td", "align=center valign=center");
                    Tag fonttag =
                            new com.infowave.pda.utils.calendar.Tag("font", "size=" + request.getAttribute("fontSize"));

                    int start_day = cal.get(Calendar.DAY_OF_MONTH);
                    int start_month = cal.get(Calendar.MONTH);
                    int start_year = cal.get(Calendar.YEAR);
                    Long start = com.infowave.pda.utils.date.HostTimeAndDate.getDateForDMY(String.valueOf(start_day),
                            String.valueOf(start_month),
                            String.valueOf(start_year));
                    Long end = com.infowave.pda.utils.date.HostTimeAndDate.getDateForDMY(
                            String.valueOf(start_day + 1), String.valueOf(start_month),
                            String.valueOf(start_year));

                    Tag link = new com.infowave.pda.utils.calendar.Tag(
                            "a", "href=\"/pda/GetEventList.do?folderID="
                            + pageContext.getAttribute("folderID")
                            + "&amp;start=" + start + "&amp;end=" + end + "\"");

                    link.addBody(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
                    fonttag.add(link);
                    cell.add(fonttag);
                    com.infowave.pda.utils.calendar.Tag TRow = (com.infowave.pda.utils.calendar.Tag) table.get(2);
                    TRow.set(i, cell);
                }
            }
        }
        cal.setTime(new Date());
        if (request.getParameter("start") != null) {
            curr_month = Integer.parseInt(HostTimeAndDate.getMonthFromFileTime(request.getParameter("start"))) - 1;
            curr_day = Integer.parseInt(HostTimeAndDate.getDayFromFileTime(request.getParameter("start")));
            curr_year = Integer.parseInt(HostTimeAndDate.getYearFromFileTime(request.getParameter("start")));
            cal.set(Calendar.YEAR, curr_year);
            cal.set(Calendar.MONTH, curr_month);
            cal.set(Calendar.DAY_OF_MONTH, curr_day);
        } else {
            curr_month = cal.get(Calendar.MONTH);
            curr_day = cal.get(Calendar.DAY_OF_MONTH);
            curr_year = cal.get(Calendar.YEAR);
        }*/

        for (int i = 1; i <= 31; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i);
            if (month != cal.get(Calendar.MONTH))
                break;
            int x = cal.get(Calendar.DAY_OF_WEEK) % NDAYS;
            y = cal.get(Calendar.WEEK_OF_MONTH);
            if (x == 0)
                x = NDAYS;
            x--;
            com.infowave.pda.utils.calendar.Tag cell = new com.infowave.pda.utils.calendar.Tag("td", "align=center valign=center");
            Attributes attrs = cell.getAttributes();
            if (i == curr_day) {
                attrs.add(new Attribute("style", "border-color=\"black\""));
            } else if ((x % 2) != 0) {
                attrs.add(new Attribute("bgcolor", "#a0e0e0"));
            } else {
                attrs.add(new Attribute("bgcolor", "#ccf0f0"));
            }
            com.infowave.pda.utils.calendar.Tag fonttag = new com.infowave.pda.utils.calendar.Tag("font", "size=" + request.getAttribute("fontSize"));

            int start_day = cal.get(Calendar.DAY_OF_MONTH);
            int start_month = cal.get(Calendar.MONTH);
            int start_year = cal.get(Calendar.YEAR);
            Long start = com.infowave.pda.utils.date.HostTimeAndDate.getDateForDMY(String.valueOf(start_day),
                    String.valueOf(start_month+1),
                    String.valueOf(start_year));
            Long end = com.infowave.pda.utils.date.HostTimeAndDate.getDateForDMY(
                    String.valueOf(start_day + 1), String.valueOf(start_month+1),
                    String.valueOf(start_year));

            Tag link;

            if (i != curr_day) {
                link = new com.infowave.pda.utils.calendar.Tag("a", "href=\"/pda/GetEventList.do?folderID=" + pageContext.getAttribute("folderID") + "&amp;start=" + start + "&amp;end=" + end + "\"");
            } else {
                link = new com.infowave.pda.utils.calendar.Tag("b");
            }
            link.addBody(Integer.toString(i));
            fonttag.add(link);
            cell.add(fonttag);
            com.infowave.pda.utils.calendar.Tag TRow = (com.infowave.pda.utils.calendar.Tag) table.get(y + 2 - refl);
            TRow.set(x, cell);
        }
        // remove surplus rows
        for (int i = NROWS; i > y + 1; i--) {
            table.remove(i);
        }

        int hours = 0;
        int minutes = 0;
        //Get calendar in users locale

        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), hours, minutes);
        Date currDate = cal.getTime();
        Tag alldayList = new Tag("table", "width=100% bgcolor=#FFF4BC style=border-bottom-width:1;border-bottom-color:black");
        Tag listT = new Tag("table", "width=100%");
        for (int i = 0; i < 48; i++) {
            Tag tr = new Tag("tr", "style=border-bottom-width:1;border-bottom-color:black");
            if ((i < 14) || (i > 34)) {
                tr.addAttribute(new Attribute("bgcolor", "#FFF4BC"));
            } else {
                tr.addAttribute(new Attribute("bgcolor", "#FFFFD5"));
            }
            Tag td1 = new Tag("td");
            td1.addBody(i / 2 + ":" + (i % 2 == 1 ? "30" : "00"));
            tr.add(td1);
            listT.add(tr);
        }

        pageContext.setAttribute("alldayList", alldayList);
        pageContext.setAttribute("listT", listT);
/*        pageContext.setAttribute("block_index",new int[48]);
        pageContext.setAttribute("max",1);
        pageContext.setAttribute("first_allday",false);*/


        try {
            pageContext.getOut().write(table.toString());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return SKIP_BODY;
    }
}
