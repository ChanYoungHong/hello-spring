package prac;

import java.util.ArrayList;
import java.util.Collections;

public class Score implements Comparable<Score> {

    int english;
    int math;

    public static void main(String[] args) {

        ArrayList<Score> A = new ArrayList<>();

        A.add(new Score(80, 100));
        A.add(new Score(100, 100));
        A.add(new Score(70, 80));
        A.add(new Score(100, 90));

        Collections.sort(A);

        for (int i = 0; i < A.size(); i++) {

            System.out.println(A.get(i).toString());
        }
    }

    @Override
    public int compareTo(Score hi) {

        if(this.english != hi.english) {
            return hi.english - this.english;
        }
        return hi.math - this.math;
    }

    public Score(int english, int math) {
        super();
        this.english = english;
        this.math = math;
    }

    @Override
    public String toString() {
        return "Score{" +
            "english=" + english +
            ", math=" + math +
            '}';
    }

}
