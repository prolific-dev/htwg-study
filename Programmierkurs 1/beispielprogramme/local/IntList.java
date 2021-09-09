// IntList.java
package beispielprogramme.local;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class IntList implements Iterable<Integer> {

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

    @Override
    public Iterator<Integer> iterator() {
        // Beispiel fuer eine anonyme lokale Klasse
        return new Iterator<Integer>() {
            private Element current = IntList.this.head;

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public Integer next() {
                if (this.current == null) {
                    throw new NoSuchElementException();
                }

                Element e = this.current;
                this.current = this.current.next;
                return Integer.valueOf(e.n);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

