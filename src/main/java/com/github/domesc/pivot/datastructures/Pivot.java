package com.github.domesc.pivot.datastructures;

import java.util.List;
import java.util.function.BiFunction;

public class Pivot {
    private final PivotNode root;

    public Pivot() {
        this.root = new PivotNode(0, 0);
    }

    public PivotNode getRoot() {
        return root;
    }

    /**
     * @param row list of the row values
     * @param index where to start
     * @param aggregateValue the value to be aggregated in the right node
     * @param aggregateFun the aggregation function
     */
    public void searchAndUpdateTotal(
            List<String> row,
            int index,
            double aggregateValue,
            BiFunction<PivotNode, Double, Double> aggregateFun
    ) {
        searchAndUpdateTotal(root, row, index, aggregateValue, aggregateFun);
    }

    private void searchAndUpdateTotal(
            PivotNode root,
            List<String> row,
            int index,
            double toAdd,
            BiFunction<PivotNode, Double, Double> aggregateFun
    ) {
        if(root == null || index >= row.size()) return;
        String keyColumn = row.get(index);
        if(root.getChildren().containsKey(keyColumn)){
            PivotNode n = root.getChildren().get(keyColumn);
            n.incrementCount(1);
            n.setGrandTotal(aggregateFun.apply(n, toAdd));
            searchAndUpdateTotal(n, row, index + 1, toAdd, aggregateFun);
        } else {
            PivotNode newNode = new PivotNode(toAdd, 1);
            root.getChildren().put(keyColumn, newNode);
            searchAndUpdateTotal(newNode, row, index + 1, toAdd, aggregateFun);
        }

    }

    /**
     * Nicely print the pivot
     * FIXME tabulation is not perfect
     */
    public void print() {
        printRecursive(root, 0);
    }

    private void printRecursive(PivotNode root, int level) {
        if(root == null) return;
        if(root.getChildren().size() == 0) {
            System.out.println(root.getGrandTotal());
            for(int i = 0;i <= level;i++) {
                System.out.print("\t");
            }
        }
        root.getChildren().forEach((key, value) -> {
            int currentLevel = 0;
            System.out.print(key + "\t");
            printRecursive(value, currentLevel + 1);
            if (value.getChildren().size() > 1) {
                System.out.println(key + " total\t" + value.getGrandTotal());
            }
        });
    }


}
