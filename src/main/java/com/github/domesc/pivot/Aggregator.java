package com.github.domesc.pivot;

import com.github.domesc.pivot.api.Row;
import com.github.domesc.pivot.datastructures.Pivot;
import com.github.domesc.pivot.datastructures.PivotNode;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Aggregator {

    public <T extends Row> Pivot buildPivot(
            List<T> rows,
            List<String> aggregationFields,
            String aggregatedColumn,
            BiFunction<PivotNode, Double, Double> aggregateFun
    ) {
        Pivot pivot = new Pivot();
        PivotNode root = pivot.getRoot();
        rows.forEach(
                r -> {
                    Map<String, String> valuesMap = r.getValuesMap();
                    pivot.searchAndUpdateTotal(
                        r.valuesByColumns(aggregationFields),
                        0,
                        Long.parseLong(valuesMap.get(aggregatedColumn)),
                        aggregateFun
                    );
                }
        );

        return pivot;
    }
}
