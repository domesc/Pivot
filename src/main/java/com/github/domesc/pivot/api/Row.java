package com.github.domesc.pivot.api;

import java.util.List;
import java.util.Map;

public interface Row {

    /**
     * @return the mapping column name value for a certain row
     */
    Map<String, String> getValuesMap();

    /**
     * @param columns the list of columns to filter
     * @return the values corresponding to the chosen columns
     */
    List<String> valuesByColumns(List<String> columns);

}
