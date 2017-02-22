/**
 * Created by Ivan on 18.02.2017.
 */
public class Story {
    double a1;
    double a2;
    String type;
    double result;

    public Story(double a1, double a2, String type, double result) {
        this.a1 = a1;
        this.a2 = a2;
        this.type = type;
        this.result = result;
    }

    @Override
    public String toString() {
        return "" + a1 + type + a2 + " = " + result;
    }
}
