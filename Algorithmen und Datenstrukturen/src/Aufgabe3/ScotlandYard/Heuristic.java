// Dennis Agostinho da Silva
// 23.01.2020

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Aufgabe3.ScotlandYard;

/**
 * @param <V> Knotentyp.
 */
public interface Heuristic<V> {
	/**
	 * Schätzt die Kosten (Distanz) von u nach v ab.
	 * @param u
	 * @param v
	 * @return Geschätzte Kosten.
	 */
	double estimatedCost(V u, V v);
}
