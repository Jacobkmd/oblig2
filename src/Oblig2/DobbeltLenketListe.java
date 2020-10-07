package Oblig2;

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
                if (i == i) {
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

    // Oppgave 3B
    public Liste<T> subliste(int fra, int til)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
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

    @Override
    public void leggInn(int indeks, T verdi)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public boolean inneholder(T verdi)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }
// oppgave 3
    @Override
    public T hent(int indeks)
    {
        indeksKontroll(indeks, false); //Sjekker indeks
        return finnNode(indeks).verdi;         // Bruker finnNode og returnerer verdien til indeksen.
    }

    @Override
    public int indeksTil(T verdi)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
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

    @Override
    public boolean fjern(T verdi)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public T fjern(int indeks)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
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

} // Oblig2.DobbeltLenketListe

