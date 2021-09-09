// Dennis Agostinho da Silva
// 23.01.2020

package Aufgabe3.ScotlandYard;

import Aufgabe2.*;
import java.io.FileNotFoundException;
import Aufgabe3.SYSimulation.sim.SYSimulation;
import java.awt.Color;
import java.io.IOException;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * Kürzeste Wege im Scotland-Yard Spielplan mit A* und Dijkstra.
 */
public class ScotlandYard {

	/**
	 * Fabrikmethode zur Erzeugung eines gerichteten Graphens für den Scotland-Yard-Spielplan.
	 * <p>
	 * Liest die Verbindungsdaten von der Datei ScotlandYard_Kanten.txt.
	 * Für die Verbindungen werden folgende Gewichte angenommen:
	 * U-Bahn = 5, Taxi = 2 und Bus = 3.
	 * Falls Knotenverbindungen unterschiedliche Beförderungsmittel gestatten,
	 * wird das billigste Beförderungsmittel gewählt. 
	 * Bei einer Vebindung von u nach v wird in den gerichteten Graph sowohl 
	 * eine Kante von u nach v als auch von v nach u eingetragen.
	 * @return Gerichteter und Gewichteter Graph für Scotland-Yard.
	 * @throws FileNotFoundException
	 */
	public static DirectedGraph<Integer> getGraph() throws FileNotFoundException {

		DirectedGraph<Integer> sy_graph = new AdjacencyListDirectedGraph<>();
		Scanner in = new Scanner(new File("src/Aufgabe3/ScotlandYard/ScotlandYard_Kanten.txt"));

		// ...
		while (in.hasNextLine()) {
			if (!in.hasNextLine()) {
				break;
			}
			int v = in.nextInt(); // Integer.parseInt(in.next());
			int w = in.nextInt();
			double weight = 0;

			switch (in.next()) {
				case "UBahn" : weight = 5; break;
				case "Taxi" : weight = 2; break;
				case "Bus" : weight = 3; break;
				default:
					System.out.println("Fehler");
			}

			if (sy_graph.containsEdge(v, w)) {
				double currentWeight = sy_graph.getWeight(v, w);
				if (currentWeight < weight) {
					weight = currentWeight;
				}
			}

			if (!sy_graph.containsVertex(v)) {
				sy_graph.addVertex(v);
			}
			if (!sy_graph.containsVertex(w)) {
				sy_graph.addVertex(w);
			}

			sy_graph.addEdge(v, w, weight);
			sy_graph.addEdge(w, v, weight);
		}
		
		// Test, ob alle Kanten eingelesen wurden: 
		System.out.println("Number of Vertices:       " + sy_graph.getNumberOfVertexes());	// 199
		System.out.println("Number of directed Edges: " + sy_graph.getNumberOfEdges());	  	// 862
		double wSum = 0.0;
		for (Integer v : sy_graph.getVertexSet())
			for (Integer w : sy_graph.getSuccessorVertexSet(v))
				wSum += sy_graph.getWeight(v,w);
		System.out.println("Sum of all Weights:       " + wSum);	// 1972.0
		
		return sy_graph;
	}


	/**
	 * Fabrikmethode zur Erzeugung einer Heuristik für die Schätzung
	 * der Distanz zweier Knoten im Scotland-Yard-Spielplan.
	 * Die Heuristik wird für A* benötigt.
	 * <p>
	 * Liest die (x,y)-Koordinaten (Pixelkoordinaten) aller Knoten von der Datei
	 * ScotlandYard_Knoten.txt in eine Map ein.
	 * Die zurückgelieferte Heuristik-Funktion estimatedCost
	 * berechnet einen skalierten Euklidischen Abstand.
	 * @return Heuristik für Scotland-Yard.
	 * @throws FileNotFoundException
	 */
	public static Heuristic<Integer> getHeuristic() throws FileNotFoundException {
		return new ScotlandYardHeuristic();
	}

	/**
	 * Scotland-Yard Anwendung.
	 * @param args wird nicht verewendet.
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		DirectedGraph<Integer> syGraph = getGraph();
		
		//Heuristic<Integer> syHeuristic = null; // Dijkstra
		Heuristic<Integer> syHeuristic = getHeuristic(); // A*

		ShortestPath<Integer> sySp = new ShortestPath<Integer>(syGraph,syHeuristic);

		sySp.searchShortestPath(65,157);
		System.out.println("Distance = " + sySp.getDistance()); // 9.0

		sySp.searchShortestPath(1,175);
		System.out.println("Distance = " + sySp.getDistance()); // 25.0

		sySp.searchShortestPath(1,173);
		System.out.println("Distance = " + sySp.getDistance()); // 22.0


		SYSimulation sim;
		try {
			sim = new SYSimulation();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		sySp.setSimulator(sim);
		sim.startSequence("Shortest path from 1 to 173");

		//sySp.searchShortestPath(65,157); // 9.0
		//sySp.searchShortestPath(1,175); //25.0
		
		sySp.searchShortestPath(1,173); //22.0
		// bei Heuristik-Faktor von 1/10 wird nicht der optimale Pfad produziert.
		// bei 1/30 funktioniert es.

		System.out.println("Distance = " + sySp.getDistance());
		List<Integer> sp = sySp.getShortestPath();

		int a = -1;
		for (int b : sp) {
			if (a != -1)
			sim.drive(a, b, Color.RED.darker());
			sim.visitStation(b);
			a = b;
		}

        sim.stopSequence();


    }

}

class ScotlandYardHeuristic implements Heuristic<Integer> {
	private Map<Integer,Point> coord = new HashMap<>(); // Ordnet jedem Knoten seine Koordinaten zu

	private static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public ScotlandYardHeuristic() throws FileNotFoundException {
		// ...
		Scanner in = new Scanner(new File("src/Aufgabe3/ScotlandYard/ScotlandYard_Knoten.txt"));

		// fill in coordinates
		while (in.hasNextLine()) {
			if (!in.hasNext()) {
				break;
			}

			int knoten = in.nextInt();
			int x = in.nextInt();
			int y = in.nextInt();

			coord.put(knoten, new Point(x, y));
		}

		System.out.println("KONSTRUKTOR");
	}

	public double estimatedCost(Integer u, Integer v) {
		// ...
		// get coordinates
		Point vp = coord.get(u);
		Point wp = coord.get(v);
		// WURZEL((x1 - x2)² + (y1 - y2)²)
		double h = Math.sqrt((vp.x - wp.x) * (vp.x - wp.x) + (vp.y - wp.y) * (vp.y - wp.y)) / 30;
		return h;
	}
}

