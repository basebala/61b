package ngordnet.ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for (Map.Entry<Integer, Double> i: ts.entrySet()) {
            if (i.getKey() >= startYear && i.getKey() <= endYear) {
                this.put(i.getKey(), i.getValue());
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        List<Integer> yearsReturn = new ArrayList<>();
        for (Map.Entry<Integer, Double> i: this.entrySet()) {
            yearsReturn.add(i.getKey());
        }
        return yearsReturn;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> valReturn = new ArrayList<>();
        for (Map.Entry<Integer, Double> i: this.entrySet()) {
            valReturn.add(i.getValue());
        }
        return valReturn;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries myRet = new TimeSeries();
        for (Map.Entry<Integer, Double> i: this.entrySet()) {
            if (ts.containsKey(i.getKey())) {
                myRet.put(i.getKey(), i.getValue() + ts.get(i.getKey()));
            } else {
                myRet.put(i.getKey(), i.getValue());
            }
        }
        for (Map.Entry<Integer, Double> i: ts.entrySet()) {
            if (!this.containsKey(i.getKey())) {
                myRet.put(i.getKey(), i.getValue());
            }
        }
        return myRet;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries myRet = new TimeSeries();
        for (Map.Entry<Integer, Double> i: this.entrySet()) {
            if (!ts.containsKey(i.getKey())) {
                throw new IllegalArgumentException();
            } else {
                myRet.put(i.getKey(), i.getValue() / ts.get(i.getKey()));
            }
        }
        return myRet;
    }
}
