package pl.waw.sgh;

public class Kwadrat extends Figura {
    public Kwadrat(double parA) {
        super(parA);
    }

    @Override
    public double policzPole() { return Math.pow(parA,2);
    }
}

