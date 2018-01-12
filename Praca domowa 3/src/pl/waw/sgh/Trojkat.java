package pl.waw.sgh;

public class Trojkat extends Figura {
    public Trojkat(double parA, double parB, double parC) {
        super(parA, parB, parC);
    }

    @Override
    public double policzPole() {


           double o = parA + parB + parC;
           double wynik = Math.sqrt(o * (o - parA) * (o - parB) * (o - parC));
           return wynik;


    }
}
