package zac.vince.jl.patou.popitprof;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Circular;
import com.anychart.anychart.CircularGauge;
import com.anychart.anychart.EnumsAnchor;
import com.anychart.anychart.Fill;
import com.anychart.anychart.GaugePointersBar;
import com.anychart.anychart.SingleValueDataSet;
import com.anychart.anychart.SolidFill;
import com.anychart.anychart.Stroke;
import com.anychart.anychart.TextHAlign;
import com.anychart.anychart.TextVAlign;

import java.util.ArrayList;
import java.util.List;


public class CircularGaugeFragment extends Fragment {

    // Le graph
    CircularGauge circularGauge;
    AnyChartView anyChartView;

    // Deux listes, la premieres contient les notes, la deuxieme les noms
    List<String> data = new ArrayList();
    List<String> name = new ArrayList();

    // Couleur de chaque row
    List<String> colors = new ArrayList();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_circular_gauge, container, false);

        circularGauge = AnyChart.circular();
        SingleValueDataSet datagauge = new SingleValueDataSet(data.toArray());
        anyChartView = v.findViewById(R.id.any_chart_view);

        // Pour les couleurs ->
        colors.add("#64b5f6");
        colors.add("#1976d2");
        colors.add("#ef6c00");
        colors.add("#ffd54f");
        colors.add("#455a64");


        addEleve("Jesus", 94);

        return v;
    }

    private void addEleve(String nom, int score)
    {
        int size = data.size();
        if(size<5) {
            data.add("" + score);
            name.add(nom);

            circularGauge = AnyChart.circular();
            circularGauge.setData(new SingleValueDataSet(data.toArray()));
            circularGauge.setFill("#fff")
                    .setStroke((Stroke) null, null, null, null, null)
                    .setPadding(0d, 0d, 0d, 0d)
                    .setMargin(100d, 100d, 100d, 100d);
            circularGauge.setStartAngle(0d);
            circularGauge.setSweepAngle(270d);

            Circular xAxis = circularGauge.getAxis()
                    .setRadius(100d)
                    .setWidth(1d)
                    .setFill((Fill) null);
            xAxis.getScale()
                    .setMinimum(0d)
                    .setMaximum(100d);
            xAxis.setTicks("{ interval: 1 }")
                    .setMinorTicks("{ interval: 1 }");
            xAxis.getLabels().setEnabled(false);
            xAxis.getTicks().setEnabled(false);
            xAxis.getMinorTicks().setEnabled(false);


            for (int i = 0; i <= size; i++) {
                circularGauge.getLabel(i)
                        .setText(name.get(i)+", <span style=\"\">" + data.get(i) + "%</span>")
                        .setUseHtml(true)
                        .setHAlign(TextHAlign.CENTER)
                        .setVAlign(TextVAlign.MIDDLE);
                circularGauge.getLabel(i)
                        .setAnchor(EnumsAnchor.RIGHT_CENTER)
                        .setPadding(0d, 10d, 0d, 0d)
                        .setHeight(17d / 2d + "%")
                        .setOffsetY(100 - 20 * i + "%")
                        .setOffsetX(0d);
                GaugePointersBar bar0 = circularGauge.getBar(i);
                bar0.setDataIndex(i);
                bar0.setRadius(100 - 20 * i);
                bar0.setWidth(17d);
                bar0.setFill(new SolidFill(colors.get(i), 1d));
                bar0.setStroke((Stroke) null, null, null, null, null);
                bar0.setZIndex(5d);
                GaugePointersBar bar100 = circularGauge.getBar(100 + i);
                bar100.setDataIndex(5d);
                bar100.setRadius(100 - 20 * i);
                bar100.setWidth(17d);
                bar100.setFill(new SolidFill("#F5F4F4", 1d));
                bar100.setStroke("#e5e4e4", 1d, null, null, null);
                bar100.setZIndex(4d);
            }


            circularGauge.setMargin(50d, 50d, 50d, 50d);
            circularGauge.getTitle()
                    .setText("Comparaison des élèves")
                    .setUseHtml(true);
            circularGauge.getTitle().setEnabled(true);
            circularGauge.getTitle().setHAlign(TextHAlign.CENTER);
            circularGauge.getTitle()
                    .setPadding(0d, 0d, 0d, 0d)
                    .setMargin(0d, 0d, 20d, 0d);

            anyChartView.setChart(circularGauge);
        }
    }

}
