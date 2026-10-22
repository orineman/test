package com.infowave.pda.ui.tags;

import com.infowave.pda.kernel.objects.Attachment;

import java.util.ArrayList;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 03.03.2008
 * Time: 18:40:32
 * Paccage : com.infowave.pda.ui.tags
 */
public class AttachLinks {
    public static ArrayList isContain(ArrayList array,Attachment at) {
        if (!array.contains(at)) {
            array.add(at);
        }
        return array;
    }

    public static void addAttach(ArrayList array, Attachment at) {
        if (!array.contains(at)) {
            array.add(at);
        }
    }

    public static Attachment newAttachment() {return new Attachment(); }
}
