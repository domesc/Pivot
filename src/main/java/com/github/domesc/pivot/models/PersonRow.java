package com.github.domesc.pivot.models;

import com.github.domesc.pivot.api.Row;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonRow implements Row {
    private final String nation;

    private final String eyes;

    private final String hair;

    private final int count;

    private final Map<String, String> valuesMap;

    public PersonRow(String nation, String eyes, String hair, int count) {
        this.nation = nation;
        this.eyes = eyes;
        this.hair = hair;
        this.count = count;
        this.valuesMap = buildValuesMap();
    }

    public String getNation() {
        return nation;
    }

    public String getEyes() {
        return eyes;
    }

    public String getHair() {
        return hair;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "PersonRow{" +
                "nation='" + nation + '\'' +
                ", eyes='" + eyes + '\'' +
                ", hair='" + hair + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public Map<String, String> getValuesMap() {
        return this.valuesMap;
    }

    @Override
    public List<String> valuesByColumns(List<String> columns) {
        ArrayList<String> result = new ArrayList<>();
        for(String c : columns) {
            result.add(valuesMap.get(c));
        }
        return result;
    }

    private Map<String, String> buildValuesMap() {
        HashMap<String, String> columnValueMap = new HashMap<>();
        String countStr = String.valueOf(this.getCount());
        columnValueMap.put("nation", this.getNation());
        columnValueMap.put("eyes", this.getEyes());
        columnValueMap.put("hair", this.getHair());
        columnValueMap.put("count", countStr);
        return columnValueMap;
    }

}
