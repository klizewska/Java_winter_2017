package pl.waw.sgh;

public abstract class Figura {

    protected double parA;
    protected double parB;
    protected double parC;

    public Figura(double parA, double parB, double parC) { ustawParam3(parA, parB, parC);
    }

    public void ustawParam3(double parA, double parB, double parC) {
        this.parA = parA;
        this.parB = parB;
        this.parC = parC;
    }
    public Figura(double parA, double parB) { ustawParam2(parA, parB);
    }
    public void ustawParam2(double parA, double parB) {
        this.parA = parA;
        this.parB = parB;
    }
    public Figura(double parA) { ustawParam1(parA);
    }
    public void ustawParam1(double parA) {
        this.parA = parA;
    }

    public abstract double policzPole();

    @Override
    public String toString() {
        return super.toString() +
                " figury o parametrach A=" + parA +
                " i B=" + parB +
                " i C=" + parC;
    }
}
