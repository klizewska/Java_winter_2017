package pl.waw.sgh;

public class Prostokat extends Figura {
    public Prostokat(double parA, double parB) {
        super(parA, parB);
    }

    @Override
    public double policzPole() {
        return parA*parB;
    }
}
