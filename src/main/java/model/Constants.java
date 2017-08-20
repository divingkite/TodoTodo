package model;

import java.util.HashMap;
import java.util.Map;

public enum Constants {
    UNASSIGNED (0),
    ASSIGNED (1),
    COMPLETED (2),
    DELETED (3);

    private int value;

    private Constants(int val){
        value = val;
    }

    private static Map<Integer, Constants> map = new HashMap<Integer, Constants>();

    static {
        for( Constants cons : Constants.values() ){
            map.put(cons.value ,cons);
        }
    }

    public static Constants valueOf(int val){
        return map.get(val);
    }

    public static int getValue(Constants cons){
        return cons.value;
    }
    public boolean equals(String s){
        if( (this.value == 0 && s.equals("UNASSIGNED")) || (this.value == 1 && s.equals("ASSIGNED")) || (this.value == 2 && s.equals("COMPLETED")) )
            return true;
        return false;
    }
}