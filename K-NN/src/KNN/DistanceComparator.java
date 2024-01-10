package KNN;

import java.util.Comparator;


public class DistanceComparator implements Comparator<Distances>{


    @Override
    public int compare(Distances a, Distances b) {
        return a.distance<b.distance?-1 : a.distance==b.distance?0:1;
    }

}
