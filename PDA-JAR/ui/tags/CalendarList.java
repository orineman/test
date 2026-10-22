package com.infowave.pda.ui.tags;

import java.util.TimeZone;
import com.infowave.pda.utils.calendar.Attribute;
import com.infowave.pda.utils.calendar.Tag;
import com.infowave.pda.utils.date.HostTimeAndDate;
import org.apache.commons.lang.time.DateUtils;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 05.03.2008
 * Time: 16:46:13
 * Paccage : com.infowave.pda.ui.tags
 */
public class CalendarList extends TagSupport {
    public int doStartTag() {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String folderID = (String) pageContext.getAttribute("folderID");
        String allday = (String) pageContext.getAttribute("allday");
        String startDate = (String) pageContext.getAttribute("startDate");
        HttpSession session = pageContext.getSession();
        String endDate = (String) pageContext.getAttribute("endDate");
        int startNumber = ((Long) pageContext.getAttribute("position")).intValue();
        CalendarWidget calendar = (CalendarWidget) pageContext.getAttribute("calendar");
        String eventID = (String) pageContext.getAttribute("eventID");
        String subject = (String) pageContext.getAttribute("subject");
        String location = (String) pageContext.getAttribute("location");
        String allmesg = (String) pageContext.getAttribute("allmesg");
        if (startDate!=""&& endDate!="") {
            Date start = HostTimeAndDate.filetimeToDate(Long.parseLong(startDate));
            Date end = HostTimeAndDate.filetimeToDate(Long.parseLong(endDate));
            if (end.getTime()-start.getTime()>24*60*60*1000) {
                 allday="1";
            }
        }
        //For filling Next Prev func
        String temp_start = "";
        if (request.getParameter("start") != null) {
            temp_start = "&amp;start=" + request.getParameter("start");
        }
        String temp_end = "";
        if (request.getParameter("end") != null) {
            temp_end = "&amp;end=" + request.getParameter("end");
        }
        int temp_number = Integer.valueOf((String) pageContext.getAttribute("startNumber")).intValue() +startNumber;
        if (allday.equals("0")) {

        Date start = HostTimeAndDate.filetimeToDate(Long.parseLong(startDate));
        Date end = HostTimeAndDate.filetimeToDate(Long.parseLong(endDate));
        Calendar cal = Calendar.getInstance((Locale) session.getAttribute("org.apache.struts.action.LOCALE"));
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        timeZone.setRawOffset((int)(((Integer) (session.getAttribute("c4p_timezoneOffset"))).intValue()*DateUtils.MILLIS_PER_MINUTE));
        cal.setTimeZone(timeZone);
        cal.setTime(start);
        int start_hour = cal.get(Calendar.HOUR_OF_DAY);
        boolean start_minutes = (cal.get(Calendar.MINUTE)>29);
        cal.setTime(end);
        int end_hour = cal.get(Calendar.HOUR_OF_DAY);
        boolean end_minutes = (cal.get(Calendar.MINUTE)>29);
        if (start.getTime()-end.getTime()>24*60*60*1000) {
             allday="1";
        }
        if (end_minutes == start_minutes && start_hour==end_hour && (end_hour!=23 && end_minutes)) {
            end_minutes = !end_minutes;
            if (!end_minutes)
                end_hour++;
        }

        int start_index = start_hour*2+(start_minutes?1:0);

        int end_index = end_hour*2+(end_minutes?1:0);
        Tag listT = (Tag) pageContext.getAttribute("listT");
        Tag end_tr = (Tag) listT.get(end_index);
        for (int i=start_index;i<end_index+1;i++) {
            Tag tr = (Tag) listT.get(i);
            if (i==start_index) {
                Tag lastTD = new Tag("td","bgcolor=white style=border-left-width:5;border-left-color:blue;color:blue");
                if (((Tag)tr.getLast()).isEmpty()&&(calendar.getBlock_index()[i]-1!=tr.size())) {
                     lastTD = (Tag) tr.getLast();
                }
                Tag event_link = new Tag("a","href=\"/pda/ViewEvent.do?folderID="+folderID+"&amp;go=enter&amp;ID="+eventID+temp_start+temp_end+"&amp;startNumber="+temp_number+"\"");
                event_link.setBody(subject+(location.equals("")?"":" ("+location+")"));
                lastTD.addAttribute(new Attribute("rowspan",String.valueOf(end_index-start_index)));

                lastTD.add(event_link);

                tr.add(lastTD);
                for (int a=start_index+1;a<end_index;a++) {
                    calendar.getBlock_index()[a]++;
                }
                if (tr.size()>calendar.getMax()) calendar.setMax(tr.size());


            }
        }

        } else if (allday.equals("1")) {
            if (!calendar.isFirst_allday()) {
                calendar.setFirst_allday(true);
                Tag h = new Tag("tr");
                Tag head = new Tag("th","align=center");
                head.setBody(allmesg);
                h.add(head);
                Tag alldayList = (Tag) pageContext.getAttribute("alldayList");
                alldayList.add(h);
                pageContext.setAttribute("alldayList",alldayList);
            }
            Tag all_event_tr = new Tag("tr");
            Tag all_event_td = new Tag("td","align=center");
            /*all_event_td.addAttribute(new Attribute("onclick","loadEvent('"+eventID+"')"));*/
            Tag all_event_link =  new Tag("a","href=\"/pda/ViewEvent.do?folderID="+folderID+"&amp;go=enter&amp;ID="+eventID+temp_start+temp_end+"&amp;startNumber="+temp_number+"\"");
            all_event_link.setBody(subject+(location.equals("")?"":" ("+location+")"));
            all_event_td.add(all_event_link);
            all_event_tr.add(all_event_td);
            Tag alldayList = (Tag) pageContext.getAttribute("alldayList");
            alldayList.add(all_event_tr);
            pageContext.setAttribute("alldayList",alldayList);
        }

        return SKIP_BODY;
    }
}
