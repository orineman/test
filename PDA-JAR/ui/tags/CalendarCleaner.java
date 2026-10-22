package com.infowave.pda.ui.tags;

import com.infowave.pda.utils.calendar.Tag;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 05.03.2008
 * Time: 17:28:49
 * Paccage : com.infowave.pda.ui.tags
 */
public class CalendarCleaner extends TagSupport {
    public int doStartTag() {
        Tag listT = (Tag) pageContext.getAttribute("listT");
        CalendarWidget cal = (CalendarWidget) pageContext.getAttribute("calendar");
        int[] bl = cal.getBlock_index();
        int max = cal.getMax();
        for (int i = 0; i < 48; i++) {
            Tag tr = (Tag) listT.get(i);
            int size = tr.size() + bl[i];

            if (max > size) {
                Tag td = new Tag("td", "colspan=" + (max - size + 1));
                tr.add(td);
            }
        }
        return SKIP_BODY;
    }
}
