Oblig 2

Jacob Midtvik Dietrichson.
StudentNr: 346205.

Forklaring til oppgaver:

Tolker "maks 4 linjer pr oppgave" som maks 4 linjer pr deloppgave

Oppgave 1
Lager metoden antall(), som kun returnerer antall. Lagde så metoden tom(), og brukte en if setning som sier at hvis antall er lik 0, returneres true, ellers returneres false. Lagde så kontruktøren DobbeltlenketListe(T[]a). Brukte requireNonNull-metoden fra klassen objects for å kontrollere at tabellen ikke er null. Bruker så en for løkke for å kjøre gjennom listen. Bruker en if setning som sier at hvis i == null, så skal for-løkken gå videre, slik at den ikke tar med noen null-verdier. Hvis antallet er 0, betyr det at vi er på første node, og den skal da settes til både hode og hale. Hvis ikke blir den nye noden halen og den forrige verdien sin neste, osv. Antallet økes for hver nye node.

Oppgave 2
a) Her valgte jeg å bruke en stringbuilder. Hvis antallet er 0, vil den bare returnere [] (representerer en tom tabell). Hvis det er verdier i tabellen blir det brukt en for-løkke til å gå gjennom tabellen, og legge til hver nodes verdi. Returnerer til slutt stringbuilder sin toString(). På omvendtString, har jeg valgt å bruke akkurat samme metode som i toString, bare at jeg da starter med den bakerste verdien i tabellen.

b)Bruker Objects.requireNonNull for å sjekke at det ikke er en null-verdi. Hvis antallet i eksisterende liste er null, vil den nye verdien da være den første noden, og dermed bli satt til både hode og hale. Hvis ikke vil den nye verdien bli satt som hale, og vil da være den forrige verdien sin neste. For hver gang vi får satt inn en ny verdi vil både antall endringer og antallet (for antall noder i listen) øke.

Oppgave 3
a) Lager metoden finnNode, som skal finne frem en spesifikk node ved hjelp av en valgt indeks. Sjekker først om indeksen ligger på venstre halvdel av listen. Hvis dette er tilfellet starter vi å lete fra starten av listen. Hvis ikke starter vi å lete fra slutten av listen. Dette for å spare tid. Bruker en for løkke for å gå gjennom listen, og stoppe på indeksen som matcher med oppgitt indeks. Hent bruker bare finnNode for å finne frem en spesifikk verdi. oppdater brukes til å endre en nodes verdi ved å oppgi indeksen til noden sammen med den nye verdien. Sjekekr først at Den nye verdien ikke er null. Bruker så finnNode til å finne verdien til noden til oppgitt indeks og seette det som gammelVerdi. Bruker igjen finnNode til å endre verdien til noden på oppgitt indeks til den nye oppgitte verdien. Endringer økes og gammelVerdi returneres.

b)Bruker fratilKontroll for å sjekke at gyldige indekser er oppgitt. Oppretter en ny dobbeltlenketliste, som vi kaller subliste. Bruker en for-løkke til til å hente verdier fra eksisterende liste, og legger inn i sublisten. Tar bare verdier fra og til de oppgitte indeksene. Returnerer sublisten.

Oppgave 4
Lager metoden indeksTil som skal finne og returnere indeksen til en gitt verdi hvis den finnes i listen. Begynner med å lage en if setning som sier at hvis verdi er lik null, så skal den returnere -1. Lager så en for-løkke som går gjennom eksisterende liste og finner indeksen til noden som har den samme verdien som den som er oppgitt. Bruker inneholder for å sjekke om en oppgitt verdi ligger i listen. Bruker indeksTil for å finne indeksen til den oppgitte verdien. hvis indeksTil returnerer -1 betyr det at den ikke finnes i listen.

Oppgave 5
Lager leggInn for å legeg inn en oppgitt verdi i en oppgitt indeks. Sjekker først om indeksen er gyldig. Hvis listen er tom legges den oppgitte verdien inn som både hale- og hode-node. Hvis indeksen er den siste verdien, blir den da lagt til som den siste halen sin neste, og blir satt til å være ny hale. Oppretter høyre- og venstre node som blir satt til hale og hode. Starter med hver av dem fra hver sin ende av listen, og bruker for-løkker for å bevege oss gjennom listen, helt til høyre og venstre står rett ved siden av noden som tilhører oppgitt indeks (på hver sin side). venstre.neste blir satt lik høyre.forrige, som da blir satt til å være den nye noden med oppgitt verdi.

Oppgave 6
T fjern fjerner noden på oppgitt indeks. Sjekker at indeks er gyldig. Hvis indeks er 0, må vi fjerne hodet. Hvis det er flere enn en verdi i listen, blir da den neste noden det nye hodet. Hvis indeks er antall-1, må vi fjerne halen, og sette noden før som ny hale. Hvis indeks ligger på høyre halvdel av listen starter vi på enden av listen og bruker for-løkke for å finne riktig node. Fjerner så den. Hvis indeks ligger på venstre halvdel, starter vi far starten av listen. boolean T fungerer på liknende måte, men fjerner helle rnoden med oppgitt verdi. Hvis det er flere noder med samme verdi, fjernes kun den første. 

Oppgave 8
a) Lager metoden next. Hvis iteratorendringer ikke er lik endringer, så kastes det en ConcurrentModificationException. Sjekker om hasnext metoden ikke returnerer true, noe som betyr at det ikke er flere igjen i listen. Da kastes det en NoSuchElementException. Til slutt settes FjernOk til true, returnerer verdien til denne, og denne settes til denne.neste.

b) Lager metoden Iterator<T> iterator(). Settes til å returnere en ny DobbeltLenketListeiterator.
  
c) Lager metoden DobbeltLenketListeIterator, som tar inn en indeks, og skal sette pekeren denne til den noden som tilhører den oppgitte indeksen. Bruker metoden finnNode() til å finne noden som tilhører oppgitt indeks. Setter så fjernOk til false, og setter iteratorendringer til å være lik endringer.

d) Lager metoden Iterator<T> iterator(int indeks). Denne sjekker først om indeksen er gyldig ved hjelp av metoden indeksKontroll. Den returnerer så bare en ny DobbeltLenketListeIterator.


