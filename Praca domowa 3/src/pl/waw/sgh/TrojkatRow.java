package pl.waw.sgh;

public class TrojkatRow extends Figura {
    public TrojkatRow(double parA) {
        super(parA);
    }

    @Override
    public double policzPole() {

        double pole = (parA*Math.sqrt(3))/4;
        return pole;
    }
}
