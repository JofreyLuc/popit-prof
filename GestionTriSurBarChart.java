package com.anychart.sample.charts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Cartesian;
import com.anychart.anychart.CoreAxesLinear;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.EnumsAnchor;
import com.anychart.anychart.HoverMode;
import com.anychart.anychart.LabelsOverlapMode;
import com.anychart.anychart.Mapping;
import com.anychart.anychart.Orientation;
import com.anychart.anychart.ScaleStackMode;
import com.anychart.anychart.SeriesBar;
import com.anychart.anychart.Set;
import com.anychart.anychart.Sort;
import com.anychart.anychart.TooltipDisplayMode;
import com.anychart.anychart.TooltipPositionMode;
import com.anychart.anychart.ValueDataEntry;
import com.anychart.sample.ChartSide;
import com.anychart.sample.R;
import com.anychart.sample.SortedDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/*
*
*
*   Ce code se trouve dans une autre application android, la logique est néanmoins la même. 
*   cf. présentations
*
*/


public class BarChartActivity extends AppCompatActivity {

    private List<DataEntry> seriesData = new ArrayList<>();
    private Cartesian barChart;
    private AnyChartView anyChartView;

    public final static String VALUE1_KEY = "value";
    public final static String VALUE2_KEY = "value2";

    private ChartSide sortedChartSide = null;
    private SortedDirection sortedDirection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_common);

        final Button leftButton = findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                //reverseChart();

                boolean wasSorted = sortValues(seriesData, ChartSide.LEFT, SortedDirection.CRESCENT);

                if (wasSorted)
                    generateBarChartWithValues(seriesData);
            }
        });

        final Button rightButton = findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                //reverseChart();

                boolean wasSorted = sortValues(seriesData, ChartSide.RIGHT, SortedDirection.DESCENDING);

                if (wasSorted)
                    generateBarChartWithValues(seriesData);
            }
        });

        // generate values
        this.seriesData = generateData();
        generateBarChartWithValues(this.seriesData);
    }

    private List<DataEntry> generateData(){
        List<DataEntry> seriesData = new ArrayList<>();

        seriesData.add(new CustomDataEntry("Nail polish", 5376, -229));
        seriesData.add(new CustomDataEntry("Eyebrow pencil", 10987, -932));
        seriesData.add(new CustomDataEntry("Rouge", 7624, -5221));
        seriesData.add(new CustomDataEntry("Lipstick", 8814, -256));
        seriesData.add(new CustomDataEntry("Eyeshadows", 8998, -308));
        seriesData.add(new CustomDataEntry("Eyeliner", 9321, -432));
        seriesData.add(new CustomDataEntry("Foundation", 8342, -701));
        seriesData.add(new CustomDataEntry("Lip gloss", 6998, -908));
        seriesData.add(new CustomDataEntry("Mascara", 9261, -712));
        seriesData.add(new CustomDataEntry("Shampoo", 5376, -9229));
        seriesData.add(new CustomDataEntry("Hair conditioner", 10987, -13932));
        seriesData.add(new CustomDataEntry("Body lotion", 7624, -10221));
        seriesData.add(new CustomDataEntry("Shower gel", 8814, -12256));
        seriesData.add(new CustomDataEntry("Soap", 8998, -5308));
        seriesData.add(new CustomDataEntry("Eye fresher", 9321, -432));
        seriesData.add(new CustomDataEntry("Deodorant", 8342, -11701));
        seriesData.add(new CustomDataEntry("Hand cream", 7598, -5808));
        seriesData.add(new CustomDataEntry("Foot cream", 6098, -3987));
        seriesData.add(new CustomDataEntry("Night cream", 6998, -847));
        seriesData.add(new CustomDataEntry("Day cream", 5304, -4008));
        seriesData.add(new CustomDataEntry("Vanila cream", 9261, -712));

        return seriesData;
    }

    private void generateBarChartWithValues(List<DataEntry> seriesData) {

        anyChartView = findViewById(R.id.any_chart_view);

        barChart = AnyChart.bar();

        barChart.setAnimation(true);

        barChart.setPadding(10d, 20d, 5d, 20d);

        barChart.getYScale().setStackMode(ScaleStackMode.VALUE);

        barChart.getYAxis().getLabels().setFormat(
                "function() {\n" +
                        "    return Math.abs(this.value).toLocaleString();\n" +
                        "  }");

        barChart.getYAxis(0d).setTitle("Revenue in Dollars");

        barChart.getXAxis(0d).setOverlapMode(LabelsOverlapMode.ALLOW_OVERLAP);

        CoreAxesLinear xAxis1 = barChart.getXAxis(1d);
        xAxis1.setEnabled(true);
        xAxis1.setOrientation(Orientation.RIGHT);
        xAxis1.setOverlapMode(LabelsOverlapMode.ALLOW_OVERLAP);

        barChart.setTitle("Cosmetic Sales by Gender");

        barChart.getInteractivity().setHoverMode(HoverMode.BY_X);

        barChart.getTooltip()
                .setTitle(false)
                .setSeparator(false)
                .setDisplayMode(TooltipDisplayMode.SEPARATED)
                .setPositionMode(TooltipPositionMode.POINT)
                .setUseHtml(true)
                .setFontSize(12d)
                .setOffsetX(5d)
                .setOffsetY(0d)
                .setFormat(
                        "function() {\n" +
                                "      return '<span style=\"color: #D9D9D9\">$</span>' + Math.abs(this.value).toLocaleString();\n" +
                                "    }");


        Set set = new Set(seriesData); // order is somehow preserved
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");

        SeriesBar series1 = barChart.bar(series1Data);
        series1.setName("Females")
                .setColor("HotPink");
        series1.getTooltip()
                .setPosition("right")
                .setAnchor(EnumsAnchor.LEFT_CENTER);

        SeriesBar series2 = barChart.bar(series2Data);
        series2.setName("Males");
        series2.getTooltip()
                .setPosition("left")
                .setAnchor(EnumsAnchor.RIGHT_CENTER);

        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setInverted(true);
        barChart.getLegend().setFontSize(13d);
        barChart.getLegend().setPadding(0d, 0d, 20d, 0d);

        anyChartView.setChart(barChart);

    }

    private void reverseChart() {
        sortValues(this.seriesData, ChartSide.LEFT, SortedDirection.CRESCENT);
        generateBarChartWithValues(this.seriesData);
    }

    /**
     *
     * @param seriesData
     * @param sideToSort
     * @param sortedDirection
     * @return false if it already sorted and true if it had to be sorted
     */
    private boolean sortValues(List<DataEntry> seriesData, final ChartSide sideToSort, SortedDirection sortedDirection) {

        if (sortedDirection == this.sortedDirection && sideToSort == this.sortedChartSide)
            return false;


        Log.i("AZER", "Still alive?");
        // tri du plus petit au plus grand
        Collections.sort(seriesData, new Comparator<DataEntry>() {
            @Override
            public int compare(DataEntry dE1, DataEntry dE2) {

                CustomDataEntry customDataEntry1 = (CustomDataEntry) dE1;
                CustomDataEntry customDataEntry2 = (CustomDataEntry) dE2;

                String key = getValueKeyFromGraph(sideToSort);

                String value1 = (String)((CustomDataEntry) dE1).getValue(key);
                String value2 = (String)((CustomDataEntry) dE2).getValue(key);

                return compareTo(Integer.parseInt(value1), Integer.parseInt(value2));
            }
        });

        // si décroissant alors il faut inverser la liste
        if (sortedDirection == SortedDirection.DESCENDING){
            Collections.reverse(seriesData);
        }

        this.sortedChartSide = sideToSort;
        this.sortedDirection = sortedDirection;

        return true;
    }

    private String getValueKeyFromGraph(ChartSide sideToSort) {

        if (sideToSort == ChartSide.RIGHT)
            return VALUE1_KEY;
        else
            return VALUE2_KEY;
    }


    /**
     * CompareTo but uses absolute values because graph
     * @param val1
     * @param val2
     * @return if val1 > val2 then 0
     */
    private int compareTo(int val1, int val2) {
        if (Math.abs(val1) > Math.abs(val2))
            return +1;
        else if (Math.abs(val1) < Math.abs(val2))
            return -1;
        else
            return 0;
    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }
    }

}