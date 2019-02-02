package com.github.domesc.pivot.functions;

import com.github.domesc.pivot.datastructures.PivotNode;

import java.util.function.BiFunction;

public class SumFunction implements BiFunction<PivotNode, Double, Double> {

    @Override
    public Double apply(PivotNode pivotNode, Double aDouble) {
        return pivotNode.getGrandTotal() + aDouble;
    }
}
