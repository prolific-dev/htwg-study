// Dennis Agostinho da Silva
// 22.01.2020

package Aufgabe1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashDictionary<K, V> implements Dictionary<K, V> {
    private int primeCapacity;
    private static final int LOAD_FACTOR = 3;
    private LinkedList<Entry<K,V>>[] tab;
    private int size;
    private int numberEntries;

    public HashDictionary(int i) {
        primeCapacity = i;
        size = primeCapacity;
        numberEntries = 0;
        tab = new LinkedList[primeCapacity];
        for (int j = 0; j < primeCapacity; j++) {
            tab[j] = new LinkedList<>();
        }
    }

    private boolean isPrime(int i) {
        if (i <= 2)
            return (i == 2);

        for (int j = 2; j * j <= i ; j++) {
            if (i % j == 0)
                return false;
        }
        return true;
    }

    private int getHash(K key) {
        int adr = key.hashCode();
        if (adr < 0)
            adr = -adr;
        return adr % size;
    }

    @Override
    public V insert(K key, V value) {
        int index = getHash(key);

        if (tab[index] != null && index < size) {
            // Schlüssel bereits vorhanden
            for (Entry<K, V> e : tab[index]) {
                if (key.equals(e.getKey())) {
                    V oldValue = e.getValue();
                    e.setValue(value);
                    return oldValue;
                }
            }
        }

        if (numberEntries != 0 && size != 0) {
            double loadFactor = numberEntries * 1.0 / size;
            // Falls LoadFactor überschritten erstelle neue Tab
            if (loadFactor > LOAD_FACTOR) {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    if (isPrime(2 * size + i)) {
                        int newSize = 2 * size + 1;
                        LinkedList<Entry<K, V>>[] oldTab = tab;
                        tab = new LinkedList[newSize];
                        for (int j = 0; j < newSize; j++) {
                            tab[j] = new LinkedList<>();
                        }

                        numberEntries = 0;
                        int oldSize = size;
                        size = newSize;

                        for (int j = 0; j < oldSize; j++) {
                            for (Entry<K, V> e : oldTab[j]) {
                                int newIndex = getHash(e.getKey());
                                tab[newIndex].add(e);
                                numberEntries++;
                            }
                        }
                        break;
                    }
                }
            }
        }

        // Neue Entry wird hinzugefügt
        tab[index].add(new Entry<>(key, value));
        numberEntries++;
        return null;
    }

    @Override
    public V search(K key) {
        int index = getHash(key);

        if (tab[index] != null && index < size) {
            for (Entry<K, V> e : tab[index]) {
                if (key.equals(e.getKey()))
                    return e.getValue();
            }
        }
            return null;
    }

    @Override
    public V remove(K key) {
        int index = getHash(key);

        if (tab[index] != null && index < size) {
            for (int i = 0; i < tab[index].size(); i++) {
                if (key.equals(tab[index].get(i).getKey())) {
                    V removedValue;
                    removedValue = tab[index].get(i).getValue();
                    tab[index].remove(i);
                    numberEntries--;
                    return removedValue;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            private int arrayCurrent = 0;
            private int listCurrent = 0;

            @Override
            public boolean hasNext() {
                if (!tab[arrayCurrent].isEmpty() && listCurrent < tab[arrayCurrent].size()) {
                    return true;
                }

                for (int i = arrayCurrent + 1; i < size; i++) {
                    if (!tab[i].isEmpty()) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) throw new NoSuchElementException();

                if (listCurrent < tab[arrayCurrent].size()) {
                    return tab[arrayCurrent].get(listCurrent++);
                }

                int i;
                for (i = ++arrayCurrent; i < size; i++) {
                    if (!tab[i].isEmpty()) {
                        break;
                    } else {
                        arrayCurrent++;
                    }
                }

                listCurrent = 1;
                return tab[i].getFirst();
            }
        };
    }
}
