package com.infowave.pda.kernel.actions.misc;

import com.infowave.c4p.util.ErrorCode;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 11.03.2008
 * Time: 16:17:58
 * Paccage : com.infowave.pda.kernel.actions.misc
 */
public class Parameter<N,V> {
    private V value;
    private N name;
    private ErrorCode error;

    public Parameter(V v,N n) {
        this.value = v;
        this.name = n;
    }

    public Parameter() {}

    public void setName(N n) {
        this.name = n;
    }

    public void setValue(V v) {
        this.value = v;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }

    public boolean isError() { return error!=null; }

    public ErrorCode getError() { return error; }

    public V getValue() {return value;}
    public N getName() {return name;}
}
