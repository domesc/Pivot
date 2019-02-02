package com.github.domesc.pivot.datastructures;

import java.util.Map;
import java.util.TreeMap;

public class PivotNode {
    private double grandTotal;
    private long count;
    private Map<String, PivotNode> children;

    public PivotNode(double grandTotal, long count) {
        this.grandTotal = grandTotal;
        this.count = count;
        this.children = new TreeMap<>();
    }


    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Map<String, PivotNode> getChildren() {
        return children;
    }

    public long getCount() {
        return count;
    }

    public void incrementCount(long toAdd) {
        this.count += toAdd;
    }
}
