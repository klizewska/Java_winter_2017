package pl.waw.sgh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Main {






  //  static final String PLIK = "h:" + File.separator + "plik.txt";
    //static final String PLIK = "h:" + File.separator + "plik.txt";
    static final String PLIK = "src/GOOG.csv";

    public static void main(String[] args) throws FileNotFoundException {
        //throws FileNotFoundException {
        System.out.println(PLIK);
        File file = new File(PLIK);
        if (file.exists())
            System.out.println("Plik istnieje!!");
        //file.
        String cwd = System.getProperty("user.dir");
        System.out.println(cwd);
        PrintWriter pw = new PrintWriter(new File("src/GOOG2.csv"));
        StringBuilder sb = new StringBuilder();
        try {
            int l = 0;

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                l++;
                int i = 0;
                float open = 0;
                float close = 0;
                float change = 0;


                String linia = scanner.nextLine();
                System.out.println(linia);
                String[] elementy = linia.split(",");




                    for (String el : elementy) {
                        System.out.println("\t" + el);
                        i++;
                        sb.append(el + ",");
                        if (l ==1 && i==7) {
                            sb.append("Change");
                        }


                        if (l != 1) {
                            if (i == 2) {
                                open = Float.parseFloat(el);
                            }

                            if (i == 5) {
                                close = Float.parseFloat(el);
                            }

                            if (i == 7) {
                                change = (close - open) / open;
                                el = Float.toString(change);

                                sb.append(el);
                            }

                        }

                    }
                sb.append('\n');
            }


        }

        catch (FileNotFoundException el) {
            System.out.println("Problem z wczytaniem pliku...");
        }
        pw.write(sb.toString());
        pw.close();

       /* PrintWriter pw = new PrintWriter(new File("src/test.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("id");
        sb.append(',');
        sb.append("Name");
        sb.append('\n');

        sb.append("1");
        sb.append(',');
        sb.append("Prashant Ghimire");
        sb.append('\n');

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");*/
        if (file.isDirectory()) {
            //file.l


        }


    }
}