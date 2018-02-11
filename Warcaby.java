package pl.waw.sgh;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;



public class Warcaby extends JFrame implements MouseListener
{
    /*
    Gra opiera się o obliczenia dokonywane na tablicy plansza.
    3- pionek szary
    2- pionek czerwony
    1- puste pole czarne
    0- pole białe
    */
    private int[][]plansza={
            {0,2,0,2,0,2,0,2},
            {2,0,2,0,2,0,2,0},
            {0,2,0,2,0,2,0,2},
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {3,0,3,0,3,0,3,0},
            {0,3,0,3,0,3,0,3},
            {3,0,3,0,3,0,3,0}};
    /*
    Tablica plansza_podswietl odpowiada za podpowiedz dozwolonych ruchow pionka dla gracza
     */
    private int[][] plansza_podswietl ={
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}};

    private int kolumna;
    private int wiersz;
    private boolean podswietl_prawo1 =false;
    private boolean podswietl_lewo1 =false;
    private boolean podswietl_prawo2 =false;
    private boolean podswietl_lewo2 =false;
    private int bicie_wiersz;
    private int bicie_kolumna;
    private int zmiana_gracza =2;


    private  Warcaby()
    {
        setSize(500, 500);
        addMouseListener(this);
    }

    public static void main(String[] args)
    {
        Warcaby warcaby= new Warcaby();
        warcaby.setVisible(true);
        warcaby.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

/*
Metoda paint odpowiedzialna jest za stworzenie szachownicy z pionkami
W celu poprawy jakości wyswietlanej grafiki zastsowany został ANTIALIASING
W tej metodzie wybierane są kolory dla poszczególnych przypadków w tym wybranie pionka którym chcemy się poruszać skutkuje
zmiana jego koloru na przypisany w metodzie. Zmiana koloru również nastąpi dla pól na które pionek może wskoczyć.
 */
    public void paint(Graphics g)
    {
        Image img= createImage(getSize().width, getSize().height);
        Graphics2D g2= (Graphics2D)img.getGraphics();
        ((Graphics2D) g2).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for(int i=0;i<8;i++)
        {
            for(int j=0; j<8;j++)
            {
                switch(plansza[i][j])
                {
                    case 0:
                        g2.setColor(Color.white);
                        g2.fillRect(j+50+j*50, i+50+i*50, 50, 50);
                        break;
                    case 1:
                        g2.setColor(Color.black);

                        if(plansza_podswietl[i][j]==2)
                            g2.setColor(Color.pink);
                        if(plansza_podswietl[i][j]==3)
                            g2.setColor(Color.blue);


                        g2.fillRect(j+50+j*50, i+50+i*50, 50, 50);
                        break;
                    case 2:
                        g2.setColor(Color.black);
                        g2.fillRect(j+50+j*50, i+50+i*50, 50, 50);
                        g2.setColor(Color.red);

                        if(plansza_podswietl[i][j]==1)
                            g2.setColor(Color.pink);
                            g2.fillOval(j+53+j*50, i+53+i*50, 44, 44);

                        break;
                    case 3:
                        g2.setColor(Color.black);
                        g2.fillRect(j+50+j*50, i+50+i*50, 50, 50);
                        g2.setColor(Color.GRAY);

                        if(plansza_podswietl[i][j]==4)
                            g2.setColor(Color.blue);
                            g2.fillOval(j+53+j*50, i+53+i*50, 44, 44);

                        break;
                }
            }
        }
        g.drawImage(img, 0, 0, this);
    }


/*
Metoda Wybrane_pole zwraca pole kóre zostało wybrane za pomocą kliknięcia myszą.
 */
    private void Wybrane_pole(int x, int y)
    {
        if(x>50&&x<450&&y>50&&y<450)
        {
            wiersz= y/50-1;
            kolumna=x/50-1;
        }
    }
/*
Metoda Ruch gracz1 opisuje działania które wykonuje gracz 1
ruchy są oparte o działania na tablicy plansza.
 */
    private void Ruch_gracz1(int wiersz, int kolumna)
    {
        if(plansza[wiersz][kolumna]==2)
        {
            for(int wiersz_podswietlenie=0;wiersz_podswietlenie<8;wiersz_podswietlenie++)
            {
                for(int kolumna_podswietlenie=0; kolumna_podswietlenie<8;kolumna_podswietlenie++)
                {
                    plansza_podswietl[wiersz_podswietlenie][kolumna_podswietlenie]=0;
                }
            }
            plansza_podswietl[wiersz][kolumna]=1;
            //lewo
            if(wiersz+1<=7&&kolumna-1>=0)
            {
                podswietl_lewo1 =true;
                if(plansza[wiersz+1][kolumna-1]==1)
                {
                    plansza_podswietl[wiersz+1][kolumna-1]=2;
                }
                else
                {
                    plansza_podswietl[wiersz+1][kolumna-1]=0;
                    podswietl_lewo1 =false;
                }
                if(wiersz+2<=7&&kolumna-2>=0)
                {
                    podswietl_lewo2 =true;
                    if(plansza[wiersz+2][kolumna-2]==1&&plansza[wiersz+1][kolumna-1]==3)
                    {
                        bicie_wiersz =wiersz+1;
                        bicie_kolumna =kolumna-1;
                        plansza_podswietl[wiersz+2][kolumna-2]=2;
                    }
                    else
                    {
                        podswietl_lewo2 =false;
                        plansza_podswietl[wiersz+2][kolumna-2]=0;
                    }
                }
                else
                {
                    podswietl_lewo2 =false;
                }
            }
            else
            {
                podswietl_lewo1 =false;
            }
            //prawo
            if(wiersz+1<=7&&kolumna+1<=7)
            {
                podswietl_prawo1 =true;
                if(plansza[wiersz+1][kolumna+1]==1)
                {
                    plansza_podswietl[wiersz+1][kolumna+1]=2;
                }
                else
                {
                    plansza_podswietl[wiersz+1][kolumna+1]=0;
                    podswietl_prawo1 =false;
                }
                if(wiersz+2<=7&&kolumna+2<=7)
                {
                    podswietl_prawo2 =true;
                    if(plansza[wiersz+2][kolumna+2]==1&&plansza[wiersz+1][kolumna+1]==3)
                    {
                        bicie_wiersz =wiersz+1;
                        bicie_kolumna =kolumna+1;
                        plansza_podswietl[wiersz+2][kolumna+2]=2;
                    }
                    else
                    {
                        podswietl_prawo2 =false;
                        plansza_podswietl[wiersz+2][kolumna+2]=0;
                    }
                }
                else
                {
                    podswietl_prawo2 =false;
                }
            }
            else
                podswietl_prawo1 =false;
        }
        else if(plansza_podswietl[wiersz][kolumna]==2)
        {
            for(int wiersz_podswietl=0;wiersz_podswietl<8;wiersz_podswietl++)
            {
                for(int kolumna_podswietl=0; kolumna_podswietl<8;kolumna_podswietl++)
                {
                    if(plansza_podswietl[wiersz_podswietl][kolumna_podswietl]==1)
                    {
                        plansza[wiersz_podswietl][kolumna_podswietl]=1;
                        plansza[wiersz][kolumna]=2;
                        plansza_podswietl[wiersz_podswietl][kolumna_podswietl]=0;
                        zmiana_gracza =3;
                        if(wiersz_podswietl+2==wiersz)
                        {
                            if(kolumna+2==kolumna_podswietl)
                            {
                                //lewo
                                plansza[wiersz_podswietl+1][kolumna_podswietl-1]=1;
                            }
                            else if(kolumna-2==kolumna_podswietl)
                            {
                                //prawo
                                plansza[wiersz_podswietl+1][kolumna_podswietl+1]=1;
                            }
                        }
                    }
                    plansza_podswietl[wiersz_podswietl][kolumna_podswietl]=0;
                }
            }
        }

    }

    /*
   Metoda Ruch gracz2 opisuje działania które wykonuje gracz 2
   ruchy są oparte o działania na tablicy plansza.
    */
    private void Ruch_gracz2(int wiersz, int kolumna)
    {
        if(plansza[wiersz][kolumna]==3)
        {
            for(int wiersz_podswietl=0;wiersz_podswietl<8;wiersz_podswietl++)
            {
                for(int kolumna_podswietl=0; kolumna_podswietl<8;kolumna_podswietl++)
                {
                    plansza_podswietl[wiersz_podswietl][kolumna_podswietl]=0;
                }
            }
            plansza_podswietl[wiersz][kolumna]=4;
            //lewo
            if(wiersz-1>=0&&kolumna-1>=0)
            {
                podswietl_lewo1 =true;

                if(plansza[wiersz-1][kolumna-1]==1)
                {
                    plansza_podswietl[wiersz-1][kolumna-1]=3;
                }
                else
                {
                    plansza_podswietl[wiersz-1][kolumna-1]=0;
                    podswietl_lewo1 =false;
                }
                if(wiersz-2<=7&&kolumna-2>=0)
                {
                    podswietl_lewo2 =true;

                    if(plansza[wiersz-2][kolumna-2]==1&&plansza[wiersz-1][kolumna-1]==2)
                    {
                        bicie_wiersz =wiersz-1;
                        bicie_kolumna =kolumna-1;
                        plansza_podswietl[wiersz-2][kolumna-2]=3;
                    }
                    else
                    {
                        podswietl_lewo2 =false;
                        plansza_podswietl[wiersz-2][kolumna-2]=0;
                    }
                }
                else
                {
                    podswietl_lewo2 =false;
                }
            }
            else
            {
                podswietl_lewo1 =false;
            }
            //prawo
            if(wiersz-1>=0&&kolumna+1<=7)
            {
                podswietl_prawo1 =true;

                if(plansza[wiersz-1][kolumna+1]==1)
                {
                    plansza_podswietl[wiersz-1][kolumna+1]=3;
                }
                else
                {
                    plansza_podswietl[wiersz-1][kolumna+1]=0;
                    podswietl_prawo1 =false;
                }
                if(wiersz-2>=0&&kolumna+2<=7)
                {
                    podswietl_prawo2 =true;

                    if(plansza[wiersz-2][kolumna+2]==1&&plansza[wiersz-1][kolumna+1]==2)
                    {
                        bicie_wiersz =wiersz-1;
                        bicie_kolumna =kolumna-1;
                        plansza_podswietl[wiersz-2][kolumna+2]=3;
                    }
                    else
                    {
                        podswietl_prawo2 =false;
                        plansza_podswietl[wiersz-2][kolumna+2]=0;
                    }
                }
                else
                {
                    podswietl_prawo2 =false;
                }
            }
            else
                podswietl_prawo1 =false;
        }
        else if(plansza_podswietl[wiersz][kolumna]==3)
        {
            for(int wiersz_podswietl=0;wiersz_podswietl<8;wiersz_podswietl++)
            {
                for(int kolumna_podswietl=0; kolumna_podswietl<8;kolumna_podswietl++)
                {

                    if(plansza_podswietl[wiersz_podswietl][kolumna_podswietl]==4)
                    {
                        plansza[wiersz_podswietl][kolumna_podswietl]=1;
                        plansza[wiersz][kolumna]=3;
                        plansza_podswietl[wiersz_podswietl][kolumna_podswietl]=0;
                        zmiana_gracza =2;
                        if(wiersz_podswietl-2==wiersz)
                        {
                            if(kolumna+2==kolumna_podswietl)
                            {
                                //lewo
                                plansza[wiersz_podswietl-1][kolumna_podswietl-1]=1;
                            }
                            else if(kolumna-2==kolumna_podswietl)
                            {
                                //prawo
                                plansza[wiersz_podswietl-1][kolumna_podswietl+1]=1;
                            }
                        }
                    }
                    plansza_podswietl[wiersz_podswietl][kolumna_podswietl]=0;

                }
            }
        }

    }

/*
 Obsługa interfejsu myszy. po kliknieciu pozycji myszy jest przekzywana do metody Wybrane_pole która zwraca wartość wiersza oraz kolumny które zostały wybrane.
 Te wartości są przekazywane do metod Ruch_gracz1 oraz Ruch_gracz2 w zależności którego gracza jest kolej.
 Gre zaczyna gracz 1 wybierane jest to za pomocą przypisanej na stałe wartości zmiennej zmiana_gracza.
 */
    @Override
    public void mouseClicked(MouseEvent klikniecie)
    {

        Wybrane_pole(klikniecie.getX(), klikniecie.getY());
        if(zmiana_gracza ==2)
            Ruch_gracz1(wiersz, kolumna);
               else if(zmiana_gracza ==3)
            Ruch_gracz2(wiersz, kolumna);
        repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}