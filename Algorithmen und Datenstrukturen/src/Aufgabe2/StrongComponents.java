// Dennis Agostinho da Silva
// 23.01.2020

package Aufgabe2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Klasse für Bestimmung aller strengen Komponenten.
 * Kosaraju-Sharir Algorithmus.
 * @param <V> Knotentyp.
 */
public class StrongComponents<V> {
	// comp speichert fuer jede Komponente die zughörigen Knoten.
    // Die Komponenten sind numeriert: 0, 1, 2, ...
    // Fuer Beispielgraph in Aufgabenblatt 2, Abb3:
    // Component 0: 5, 6, 7,
    // Component 1: 8,
    // Component 2: 1, 2, 3,
    // Component 3: 4,

	private final Map<Integer,Set<V>> comp = new TreeMap<>();
	
	/**
	 * Ermittelt alle strengen Komponenten mit
	 * dem Kosaraju-Sharir Algorithmus.
	 * @param g gerichteter Graph.
	 */
	public StrongComponents(DirectedGraph<V> g) {
		// ...
		// Tiefensuche mit Graph g
		DepthFirstOrder<V> dfo = new DepthFirstOrder<>(g);
		dfo.visitDFAllNodes();

		// Postorder-Reihenfolge
		List<V> postorder;
		postorder = dfo.postOrder();

		// Postorder-Reihenfolge invertiert
		List<V> postorderInverted = new LinkedList<>();
		for (int i = postorder.size() - 1; i >= 0; i--) {
			postorderInverted.add(postorder.get(i));
		}

		// Invertierter Graph
		DirectedGraph<V> gInverted;
		gInverted = g.invert();

		// Tiefensuche mit invertiertem Graphen
		DepthFirstOrder<V> dfoInverted = new DepthFirstOrder<>(gInverted);

		Set<V> besucht = new TreeSet<>();
		Set<V> testSet = new TreeSet<>();
		Set<V> testSet2 = new TreeSet<>();

		int tmp = 0;
		for (V v : postorderInverted) {
			besucht = dfoInverted.visitDFforStrongComponents(v, gInverted, besucht);
			for (V x : besucht) {
				if (!testSet2.contains(x)) {
					testSet.add(x);
				}
			}

			testSet2.addAll(besucht);

			if (testSet.size() != 0) {
				comp.put(tmp, testSet);
				tmp++;
			}
			testSet = new TreeSet<>();
		}
	}

	/**
	 * 
	 * @return Anzahl der strengen Komponeneten.
	 */
	public int numberOfComp() {
		return comp.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < comp.size(); i++) {
			sb.append("Component " + i + ": ");
			sb.append(comp.get(i));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Liest einen gerichteten Graphen von einer Datei ein. 
	 * @param fn Dateiname.
	 * @return gerichteter Graph.
	 * @throws FileNotFoundException
	 */
	public static DirectedGraph<Integer> readDirectedGraph(File fn) throws FileNotFoundException {
		DirectedGraph<Integer> g = new AdjacencyListDirectedGraph<>();
		Scanner sc = new Scanner(fn);
		sc.nextLine();
        sc.nextLine();
		while (sc.hasNextInt()) {
			int v = sc.nextInt();
			int w = sc.nextInt();
			g.addEdge(v, w);
		}
		return g;
	}
	
	private static void test1() {
		DirectedGraph<Integer> g = new AdjacencyListDirectedGraph<>();
		g.addEdge(1,2);
		g.addEdge(1,3);
		g.addEdge(2,1);
		g.addEdge(2,3);
		g.addEdge(3,1);
		
		g.addEdge(1,4);
		g.addEdge(5,4);
		
		g.addEdge(5,7);
		g.addEdge(6,5);
		g.addEdge(7,6);
		
		g.addEdge(7,8);
		g.addEdge(8,2);
		
		StrongComponents<Integer> sc = new StrongComponents<>(g);
		
		System.out.println(sc.numberOfComp());  // 4
		
		System.out.println(sc);
			// Component 0: 5, 6, 7, 
        	// Component 1: 8, 
            // Component 2: 1, 2, 3, 
            // Component 3: 4, 
	}
	
	private static void test2() throws FileNotFoundException {
		DirectedGraph<Integer> g = readDirectedGraph(new File("C:\\Users\\HP\\AlgoDat\\AlgoDat_20\\src\\Aufgabe2\\mediumDG.txt"));
		System.out.println(g.getNumberOfVertexes());
		System.out.println(g.getNumberOfEdges());
		System.out.println(g);
		
		System.out.println("");
		
		StrongComponents<Integer> sc = new StrongComponents<>(g);
		System.out.println(sc.numberOfComp());  // 10
		System.out.println(sc);
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		test1();
		test2();
	}
}
