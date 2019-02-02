package com.github.domesc.pivot.functions;

import com.github.domesc.pivot.datastructures.PivotNode;

import java.util.function.BiFunction;

public class AverageFunction implements BiFunction<PivotNode, Double, Double> {

    @Override
    public Double apply(PivotNode pivotNode, Double aDouble) {
        if(pivotNode.getCount() != 0) {
            return ((pivotNode.getCount() - 1) * pivotNode.getGrandTotal() + aDouble)/pivotNode.getCount();
        } else {
            return pivotNode.getGrandTotal() + aDouble;
        }
    }
}
