package com.github.domesc.pivot;

import com.github.domesc.pivot.datastructures.Pivot;
import com.github.domesc.pivot.datastructures.PivotNode;
import com.github.domesc.pivot.functions.AverageFunction;
import com.github.domesc.pivot.functions.SumFunction;
import com.github.domesc.pivot.models.PersonRow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class AggregatorTest {

    private Aggregator aggregator;
    private ArrayList<PersonRow> rows;

    @Before
    public void setup() {
        aggregator = new Aggregator();
        rows = new ArrayList<>();
        rows.add(new PersonRow("Germany", "green", "black", 10));
        rows.add(new PersonRow("Germany", "yellow", "blonde", 20));
        rows.add(new PersonRow("Germany", "brown", "black", 5));
        rows.add(new PersonRow("Germany", "green", "blonde", 10));
    }

    @Test
    public void buildPivotSumByNation() {
        Pivot pivot = aggregator.buildPivot(rows, Arrays.asList("nation"), "count", new SumFunction());
        Assert.assertEquals(45.0, pivot.getRoot().getChildren().get("Germany").getGrandTotal(), 0);
        Assert.assertEquals(1, pivot.getRoot().getChildren().size());
        Assert.assertEquals(0, pivot.getRoot().getChildren().get("Germany").getChildren().size());
        Assert.assertEquals(4, pivot.getRoot().getChildren().get("Germany").getCount());
    }

    @Test
    public void buildPivotSumByNationEyes() {
        Pivot pivot = aggregator.buildPivot(rows, Arrays.asList("nation", "eyes"), "count", new SumFunction());
        Assert.assertEquals(45.0, pivot.getRoot().getChildren().get("Germany").getGrandTotal(), 0);
        Assert.assertEquals(4, pivot.getRoot().getChildren().get("Germany").getCount());

        PivotNode greenEyesNode = pivot.getRoot().getChildren().get("Germany").getChildren().get("green");
        Assert.assertEquals(20.0, greenEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(2, greenEyesNode.getCount());
        Assert.assertEquals(0, greenEyesNode.getChildren().size());

        PivotNode yellowEyesNode = pivot.getRoot().getChildren().get("Germany").getChildren().get("yellow");
        Assert.assertEquals(20.0, yellowEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(1, yellowEyesNode.getCount());
        Assert.assertEquals(0, yellowEyesNode.getChildren().size());


        PivotNode brownEyesNode = pivot.getRoot().getChildren().get("Germany").getChildren().get("brown");
        Assert.assertEquals(5.0, brownEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(1, brownEyesNode.getCount());
        Assert.assertEquals(0, brownEyesNode.getChildren().size());
    }

    @Test
    public void buildPivotSumByEyesNation() {
        Pivot pivot = aggregator.buildPivot(rows, Arrays.asList("eyes", "nation"), "count", new SumFunction());

        PivotNode brownEyesNode = pivot.getRoot().getChildren().get("brown");
        Assert.assertEquals(5.0, brownEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(1, brownEyesNode.getCount());
        Assert.assertEquals(1, brownEyesNode.getChildren().size());

        PivotNode greenEyesNode = pivot.getRoot().getChildren().get("green");
        Assert.assertEquals(20.0, greenEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(2, greenEyesNode.getCount());
        Assert.assertEquals(1, greenEyesNode.getChildren().size());

        PivotNode yellowEyesNode = pivot.getRoot().getChildren().get("yellow");
        Assert.assertEquals(20.0, yellowEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(1, yellowEyesNode.getCount());
        Assert.assertEquals(1, yellowEyesNode.getChildren().size());

        PivotNode brownEyesGermanyNode = pivot.getRoot().getChildren().get("brown").getChildren().get("Germany");
        Assert.assertEquals(5.0, brownEyesGermanyNode.getGrandTotal(), 0);
        Assert.assertEquals(1, brownEyesGermanyNode.getCount());
        Assert.assertEquals(0, brownEyesGermanyNode.getChildren().size());

        PivotNode greenEyesGermanyNode = pivot.getRoot().getChildren().get("green").getChildren().get("Germany");
        Assert.assertEquals(20.0, greenEyesGermanyNode.getGrandTotal(), 0);
        Assert.assertEquals(2, greenEyesGermanyNode.getCount());
        Assert.assertEquals(0, greenEyesGermanyNode.getChildren().size());

        PivotNode yellowEyesGermanyNode = pivot.getRoot().getChildren().get("yellow").getChildren().get("Germany");
        Assert.assertEquals(20.0, yellowEyesGermanyNode.getGrandTotal(), 0);
        Assert.assertEquals(1, yellowEyesGermanyNode.getCount());
        Assert.assertEquals(0, yellowEyesGermanyNode.getChildren().size());

    }

    @Test
    public void buildPivotAverageByNation() {
        Pivot pivot = aggregator.buildPivot(rows, Arrays.asList("nation"), "count", new AverageFunction());
        Assert.assertEquals(11.25, pivot.getRoot().getChildren().get("Germany").getGrandTotal(), 0);
        Assert.assertEquals(1, pivot.getRoot().getChildren().size());
        Assert.assertEquals(4, pivot.getRoot().getChildren().get("Germany").getCount());
        Assert.assertEquals(0, pivot.getRoot().getChildren().get("Germany").getChildren().size());
    }

    @Test
    public void buildPivotAverageByNationEyes() {
        Pivot pivot = aggregator.buildPivot(rows, Arrays.asList("nation", "eyes"), "count", new AverageFunction());
        Assert.assertEquals(11.25, pivot.getRoot().getChildren().get("Germany").getGrandTotal(), 0);
        Assert.assertEquals(4, pivot.getRoot().getChildren().get("Germany").getCount());

        PivotNode greenEyesNode = pivot.getRoot().getChildren().get("Germany").getChildren().get("green");
        Assert.assertEquals(10.0, greenEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(2, greenEyesNode.getCount());
        Assert.assertEquals(0, greenEyesNode.getChildren().size());

        PivotNode yellowEyesNode = pivot.getRoot().getChildren().get("Germany").getChildren().get("yellow");
        Assert.assertEquals(20.0, yellowEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(1, yellowEyesNode.getCount());
        Assert.assertEquals(0, yellowEyesNode.getChildren().size());


        PivotNode brownEyesNode = pivot.getRoot().getChildren().get("Germany").getChildren().get("brown");
        Assert.assertEquals(5.0, brownEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(1, brownEyesNode.getCount());
        Assert.assertEquals(0, brownEyesNode.getChildren().size());
    }

    @Test
    public void buildPivotAverageByEyesNation() {
        Pivot pivot = aggregator.buildPivot(rows, Arrays.asList("eyes", "nation"), "count", new AverageFunction());

        PivotNode brownEyesNode = pivot.getRoot().getChildren().get("brown");
        Assert.assertEquals(5.0, brownEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(1, brownEyesNode.getCount());
        Assert.assertEquals(1, brownEyesNode.getChildren().size());

        PivotNode greenEyesNode = pivot.getRoot().getChildren().get("green");
        Assert.assertEquals(10.0, greenEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(2, greenEyesNode.getCount());
        Assert.assertEquals(1, greenEyesNode.getChildren().size());

        PivotNode yellowEyesNode = pivot.getRoot().getChildren().get("yellow");
        Assert.assertEquals(20.0, yellowEyesNode.getGrandTotal(), 0);
        Assert.assertEquals(1, yellowEyesNode.getCount());
        Assert.assertEquals(1, yellowEyesNode.getChildren().size());

        PivotNode brownEyesGermanyNode = pivot.getRoot().getChildren().get("brown").getChildren().get("Germany");
        Assert.assertEquals(5.0, brownEyesGermanyNode.getGrandTotal(), 0);
        Assert.assertEquals(1, brownEyesGermanyNode.getCount());
        Assert.assertEquals(0, brownEyesGermanyNode.getChildren().size());

        PivotNode greenEyesGermanyNode = pivot.getRoot().getChildren().get("green").getChildren().get("Germany");
        Assert.assertEquals(10.0, greenEyesGermanyNode.getGrandTotal(), 0);
        Assert.assertEquals(2, greenEyesGermanyNode.getCount());
        Assert.assertEquals(0, greenEyesGermanyNode.getChildren().size());

        PivotNode yellowEyesGermanyNode = pivot.getRoot().getChildren().get("yellow").getChildren().get("Germany");
        Assert.assertEquals(20.0, yellowEyesGermanyNode.getGrandTotal(), 0);
        Assert.assertEquals(1, yellowEyesGermanyNode.getCount());
        Assert.assertEquals(0, yellowEyesGermanyNode.getChildren().size());

    }
}
