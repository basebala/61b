package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {

    private NGramMap myMap;

    public HistoryHandler(NGramMap map) {
        myMap = map;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        for (String word : words) {
            lts.add(myMap.weightHistory(word, startYear, endYear));
        }
        XYChart chart = Plotter.generateTimeSeriesChart(words, lts);
        String s = Plotter.encodeChartAsString(chart);
        return s;
    }
}
