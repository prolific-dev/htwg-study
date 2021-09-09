// IntList.java
package beispielprogramme.staticnested;

import java.util.NoSuchElementException;


public final class IntList {

    private Element head = null; // leere Liste

    /**
     * F&uuml;gt eine Zahl am Listenanfang ein.
     * @param n die einzuf&uuml;gende Zahl
     * @return die Liste
     */
    public IntList insert(int n) {
        this.head = new Element(this.head, n);
        return this;
    }

    /**
     * Element speichert eine einzelne Zahl als Teil einer Liste.
     * Beipiel f&uuml;r eine statisch eingebettete Klasse.
     */
    private static final class Element {
        private final Element next;
        private final int n;

        private Element(Element e, int n) {
            this.next = e;
            this.n = n;
        }
    }

    /**
     * Iterator speichert den aktuellen Zustand einer Listeniteration.
     * Beipiel f&uuml;r eine innere Klasse,
     * hier mit einer statisch eingebetteten Klasse nachgebildet.
     */
    public static final class Iterator {
        private final IntList list; // ersetzt IntList.this
        private Element current;

        /**
         * Baut einen Iterator f&uuml;r eine Liste.
         * @param list die Liste, &uuml;ber die iteriert werden soll
         */
        public Iterator(IntList list) {
            this.list = list;
            this.current = this.list.head;
        }

        /**
         * pr&uuml;t, ob das Listenende erreicht ist.
         * @return true, wenn das Listenende erreicht ist, sonst false.
         */
        public boolean hasNext() {
            return this.current != null;
        }

        /**
         * liefert die aktuelle Zahl und iteriert zum n&auml;chsten Jahr.
         * Aufruf am Listenende liefert NoSuchElementException.
         * @return die aktuelle Zahl
         */
        public int next() {
            if (this.current == null) {
                throw new NoSuchElementException();
            }

            Element e = this.current;
            this.current = this.current.next;
            return e.n;
        }
    }
}

