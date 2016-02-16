package com.neuralhack.domain;

/**
 * Created by Gonzalo on 2/14/2016.
 */
public enum BusinessStructure {
    SOLE_PROPIETORSHIP(1), LLC(2), CCORP(3);

    private int value;

    BusinessStructure(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }

    public static BusinessStructure getEnum(int value){
        switch (value){
            case 1: return SOLE_PROPIETORSHIP;
            case 2: return  LLC;
            case 3: return CCORP;
            default: throw new RuntimeException("no business structure for number " + value);
        }
    }

}
