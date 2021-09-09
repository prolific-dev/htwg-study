/* Wortweises Einlesen eines deutschen Textes von einer Datei.
 * Ermittlung der Haefigkeiten der Woerter und Ausgabe der  
 * 100 haeufigsten Woerter.
 *
 * Dennis Agostinho da Silva
 * 30.11.2019
 */

package uebung12;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.*;

public class HaeufigkeitsanalyseEinesDeutschenTextes {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Map<String,Integer> haeufigkeit = ermittleHaufigekeiten("beispiel.txt");
		System.out.println("");
		System.out.println("Top 100 Wörter: \n");
		printTop100(haeufigkeit);
	}
	
	public static Map<String,Integer> ermittleHaufigekeiten(String fileName) throws FileNotFoundException, IOException {
		
		LineNumberReader in = new LineNumberReader(new FileReader(fileName));
		String line;
		int i = 1;
		
		Map<String,Integer> haeufigkeit = new TreeMap<>(); 	// enthaelt zu jedem Wort seine Haefigkeit
				
		while ((line = in.readLine()) != null) {
			String[] wf = line.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü]+");
			for (String w: wf) {
				if (w.length() == 0 || w.length() == 1) 
					continue;
				// System.out.println(w);
				if (haeufigkeit.containsKey(w)) {
					i = haeufigkeit.get(w);
					haeufigkeit.put(w, ++i);
					i = 1;
				} else {
					haeufigkeit.put(w,i);
				}
			}
		}
		for (Map.Entry<String,Integer> eintrag : haeufigkeit.entrySet()) {
			System.out.println(eintrag.getKey() +": " + eintrag.getValue());
		}
		return haeufigkeit;
	}
	
	public static void printTop100(Map<String,Integer> h) {
		h.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(100)
				.forEach(System.out::println);
	}
	
}
