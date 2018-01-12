package pl.waw.sgh;

public class OperNaFig {

    public static void main(String[] args) {
        Prostokat p1 = new Prostokat(24,5);
        System.out.println(p1 + " \npole: " + p1.policzPole());
        //figury f1 = new figury(3,35);
        Kolko k1 = new Kolko(3,0);
        System.out.println("Koło: " + k1 + " " + k1.policzPole());
        Kwadrat kw1 = new Kwadrat(5);
        System.out.println("Kwadrat: " + kw1 + " " + kw1.policzPole());
        Trojkat t1 = new Trojkat(24,6,8);
        System.out.println("Trojkat: " + t1 + " " + t1.policzPole());
        TrojkatRow tr1 = new TrojkatRow(6);
        System.out.println("TrojkatRow: " + tr1 + " " + tr1.policzPole());

        //f1.
        Figura[] tabFig = new Figura[2];
        tabFig[0] = p1;
        tabFig[1] = k1;

//        for (Figura f : tabFig) {
//            System.out.println(f + " " + f.policzPole());
//        }
    }
}
