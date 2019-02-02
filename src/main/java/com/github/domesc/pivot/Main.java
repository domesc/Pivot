package com.github.domesc.pivot;

import com.github.domesc.pivot.functions.AverageFunction;
import com.github.domesc.pivot.models.PersonRow;

import java.util.ArrayList;
import java.util.Arrays;

public class Main
{
    public static void main( String[] args )
    {

        Aggregator pivot = new Aggregator();
        ArrayList<PersonRow> rows = new ArrayList<>();
        rows.add(new PersonRow("France", "green", "black", 10));
        rows.add(new PersonRow("France", "green", "blonde", 20));
        rows.add(new PersonRow("France", "green", "blonde", 5));
        rows.add(new PersonRow("France", "blue", "black", 5));
        rows.add(new PersonRow("France", "blue", "black", 10));
        rows.add(new PersonRow("Germany", "green", "black", 10));
        rows.add(new PersonRow("Germany", "yellow", "blonde", 20));
        rows.add(new PersonRow("Germany", "brown", "black", 5));
        rows.add(new PersonRow("Germany", "green", "blonde", 10));


        pivot.buildPivot(rows, Arrays.asList("nation", "eyes", "hair"), "count", new AverageFunction()).print();
    }
}
