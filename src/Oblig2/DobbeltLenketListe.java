package Oblig2;

import Oblig2.Liste;

import javax.swing.*;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DobbeltLenketListe<T> implements Liste<T>
{
    private static final class Node<T>   // en indre nodeklasse
    {
        // instansvariabler
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste)  // konstruktør
        {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        protected Node(T verdi)  // konstruktør
        {
            this(verdi, null, null);
        }

    } // Node

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;   // antall endringer i listen

    // Oppgave 3
    private Node<T> finnNode(int indeks)
    {
        Node<T> temp;

        if (indeks < antall/2) {
            temp = hode;
            for (int i = 0; i <= antall/2; i++){
                if (i == indeks) {
                    return temp;
                }
                temp = temp.neste;
            }
        }
        else {
            temp = hale;
            for (int i = antall - 1; i >= antall/2; i --) {
                if (i == indeks) {
                    return temp;
                }
                temp = temp.forrige;
            }
        }
        return temp;
    }

    // konstruktør
    public DobbeltLenketListe()
    {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    // Oppgave 1
    public DobbeltLenketListe(T[] a)
    {
        Objects.requireNonNull(a, "Tabell a er null!");

        for (T i : a) {
            if (i == null) {
                continue;
            }

            if (antall == 0) {
                hale = new Node<>(i, null, null);
                hode = hale;
            }
            else {
                hale.neste = new Node<>(i, hale, null);
                hale = hale.neste;
            }
            antall++;
        }

    }

    //oppgave 3B
    private static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }


    // Oppgave 3B
    public Liste<T> subliste(int fra, int til)
    {
        fratilKontroll(antall, fra, til);

        Liste<T> subListe = new DobbeltLenketListe<>();

        for (int i = fra; i < til; i++) {
            subListe.leggInn(finnNode(i).verdi);
        }
        return subListe;
    }

    @Override
    public int antall()
    {
        return antall;
    }

    @Override
    public boolean tom()
    {
        if (antall == 0) {
            return true;
        }
            return false;
    }

    // Oppgave 2B
    @Override
    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi, "Det er ikke lov med null verdier!");
        if (antall == 0){
            hale = new Node<>(verdi, null, null);
            hode = hale;
        }
        else {
            hale.neste = new Node<>(verdi,hale,null);
            hale = hale.neste;
        }
        endringer++;
        antall++;
        return true;
    }
// oppgave 5
    @Override
    public void leggInn(int indeks, T verdi)
    {
        Objects.requireNonNull(verdi, " Verdien som legges inn kan ikke være null.");

        if (indeks > antall) {
            throw new IndexOutOfBoundsException("Indeksen er for stor. Kan ikke være større enn antallet.");
        }
        if (indeks < 0) {
            throw new IndexOutOfBoundsException("Indeksen er for liten.  Må være lik eller større enn 0");
        }

        if (antall == 0) {
            hale = new Node<>(verdi, null, null);
            hode = hale;
        }
        else if (indeks == antall) {
            hale.neste = new Node<>(verdi, hale, null);
            hale = hale.neste;
        }
        else if (indeks == 0) {
            hode.forrige = new Node<>(verdi, null, hode);
            hode = hode.forrige;
        }
        else {
            Node<T> høyre = hale;
            Node<T> venstre = hode;

            for (int i = antall; i > indeks + 1; i--) {
                høyre = høyre.forrige;
            }

            for (int j = 1; j < indeks; j++) {
                venstre = venstre.neste;
            }
            venstre.neste = høyre.forrige = new Node<>(verdi, venstre, høyre);
        }
        antall++;
        endringer++;
    }
// oppgave 4
    @Override
    public boolean inneholder(T verdi)
    {
        int temp;
        temp = indeksTil(verdi);

        if (temp >= 0) {
            return true;
        }
        else {
            return false;
        }
    }
// oppgave 3
    @Override
    public T hent(int indeks)
    {
        indeksKontroll(indeks, false); //Sjekker indeks
        return finnNode(indeks).verdi;         // Bruker finnNode og returnerer verdien til indeksen.
    }
// oppgave 4
    @Override
    public int indeksTil(T verdi)
    {
        T temp;

        if (verdi== null){
            return -1;
        }

        for (int i = 0; i < antall; i++) {
            temp = hent(i);
            if (temp == verdi){
                return i;
            }
        }
        return -1;
    }
// oppgave 3
    @Override
    public T oppdater(int indeks, T nyverdi)
    {
        indeksKontroll(indeks, false); // Her sjekkes indeksen.
        T gammelVerdi;
        if(nyverdi != null) {
            gammelVerdi = finnNode(indeks).verdi;
            finnNode(indeks).verdi = nyverdi;
            endringer++;

        }
        else {
            throw new NullPointerException(" Kan ikke være null!");
        }
        return gammelVerdi;
    }



// Oppgave 5
    @Override
    public boolean fjern(T verdi)
    {
      if (verdi == null) {
          return false;
      }
      Node<T> temp = hode;
      for (int i = 0; i < antall; i++) {
          if (temp.verdi.equals(verdi)) {
              if (temp.equals(hode)) {
                  if (hode == hale) {
                      hale = null;
                  }
                  else {
                      hode.neste.forrige = null;
                  }
                  hode = hode.neste;
                  temp.neste = null;
                  endringer++;
                  antall--;
                  return true;
              }
              if (temp.equals(hale)) {
                  temp = hale;
                  if (hale == hode) {
                      hode = null;
                  }
                  else {
                      hale.forrige.neste = null;
                  }
                  hale = hale.forrige;
                  temp.forrige = null;
                  endringer++;
                  antall--;
                  return true;
              }
              else {
                  Node<T> høyrePeker;
                  temp = temp.forrige;
                  høyrePeker = temp.neste.neste;
                  høyrePeker.forrige.neste = null;
                  temp.neste = høyrePeker;
                  høyrePeker.forrige = temp;
                  endringer++;
                  antall--;
                  return true;
              }

          }
          else {
              temp = temp.neste;
          }
      }
      return false;
    }


// Oppgave 5
    @Override
    public T fjern(int indeks)
    {
        T verdi;
        Node<T> temp = null;

        if (indeks >= antall){
            throw new IndexOutOfBoundsException("Indeksen er for høy!");
        }
        if (indeks < 0) {
            throw new  IndexOutOfBoundsException("Indeksen er for lav!");
        }

        if (indeks == 0) {
            verdi = hode.verdi;
            temp = hode;

            if (hode == hale) {
                hale = null;
            } else {
                hode.neste.forrige = null;
            }
            hode = hode.neste;
            temp.neste = null;
        }
        else if (indeks == antall - 1) {
            verdi = hale.verdi;
            temp = hale;
            if (hale == hode) {
                hode = null;
            }
            else {
                hale.forrige.neste = null;
            }
            hale = hale.forrige;
            temp.forrige = null;
        }
        else {
            if (indeks > antall/2) {
                Node<T> høyrePeker;
                temp = hale;
                for(int i = antall - 1; i > indeks + 1; i --) {
                    temp = temp.forrige;
                }
                verdi = temp.forrige.verdi;
                høyrePeker = temp.forrige.forrige;
                høyrePeker.neste.forrige = null;
                temp.forrige = høyrePeker;
                høyrePeker.neste = temp;
            }
            else {
                Node<T> venstrePeker;
                temp = hode;
                for (int j = 1; j < indeks; j++) {
                    temp = temp.neste;
                }
                verdi = temp.neste.verdi;
                venstrePeker = temp.neste.neste;
                venstrePeker.forrige.neste = null;
                temp.neste.forrige = null;
                temp.neste = venstrePeker;
                venstrePeker.forrige = temp;
            }
        }
        endringer++;
        antall--;

        return verdi;

    }

    @Override
    public void nullstill()
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }
//Oppgave 2A
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if (antall == 0) {
            return "[]";
        }

        Node<T> temp;
        temp = hode;
        sb.append("[").append(temp.verdi);

        for (int i = 0; i < antall - 1; i++) {
            temp = temp.neste;
            sb.append(", ").append(temp.verdi);
        }

        sb.append("]");

        return sb.toString();
    }

    public String omvendtString() {
        StringBuilder sbOmvendt = new StringBuilder();
        if (antall == 0) {
            return "[]";
        }

        Node<T> temp = hale;
        sbOmvendt.append("[").append(temp.verdi);

        for (int i = antall - 1; i > 0; i--) {
            temp = temp.forrige;
            sbOmvendt.append(", ").append(temp.verdi);
        }
        sbOmvendt.append("]");

        return sbOmvendt.toString();
    }

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public Iterator<T> iterator()
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    public Iterator<T> iterator(int indeks)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator()
        {
            denne = hode;     // denne starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks)
        {
            throw new UnsupportedOperationException("Ikke laget ennå!");
        }

        @Override
        public boolean hasNext()
        {
            return denne != null;  // denne koden skal ikke endres!
        }

        @Override
        public T next()
        {
            throw new UnsupportedOperationException("Ikke laget ennå!");
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Ikke laget ennå!");
        }

    } // DobbeltLenketListeIterator

} // Oblig2test.DobbeltLenketListe



