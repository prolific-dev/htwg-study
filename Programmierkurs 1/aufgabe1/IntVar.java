// IntVar.java

package aufgabe1;

import java.util.Scanner;

/**
 * IntVar zeigt den Umgang mit Variablen vom Typ int.
 * &Uuml;bungsaufgabe 1 zur Programmiertechnik 1.
 * Dennis Agostinho da Silva
 * 23.10.2018
 */
public final class IntVar {
    private IntVar() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(final String[] args) {

        final int max = Integer.MAX_VALUE;
        final int min = Integer.MIN_VALUE;

        System.out.printf("Zwei ganze Zahlen zwischen %d und %d eingeben: %n",
             max, min);


        int zahl1 = EINGABE.nextInt();
        int zahl2 = EINGABE.nextInt();


        System.out.printf("%d%n ist oktal %o%n und hexadezimal %x%n",
            zahl1, zahl1, zahl1);
        System.out.printf("%d%n ist oktal %o%n und hexadezimal %x%n",
            zahl2, zahl2, zahl2);



        int summe = zahl1 + zahl2;
        int minus = zahl1 - zahl2;
        int multi = zahl1 * zahl2;
        int divis = zahl1 / zahl2;
        int modul = zahl1 % zahl2;
        boolean bool1 = false;
        boolean bool2 = true;

        System.out.println(zahl1 + " + " + zahl2 + " ist " + summe);
        System.out.println(zahl1 + " - " + zahl2 + " ist " + minus);
        System.out.println(zahl1 + " * " + zahl2 + " ist " + multi);
        System.out.println(zahl1 + " / " + zahl2 + " ist " + divis);
        System.out.println(zahl1 + " % " + zahl2 + " ist " + modul);



        if (zahl1 == zahl2) {
            System.out.println(zahl1 + " == " + zahl2 + " ist " + bool2);
        } else {
            System.out.println(zahl1 + " == " + zahl2 + " ist " + bool1);
        }


        if (zahl1 != zahl2) {
            System.out.println(zahl1 + " != " + zahl2 + " ist " + bool2);
        } else {
            System.out.println(zahl1 + " != " + zahl2 + " ist " + bool1);
        }


        if (zahl1 < zahl2) {
            System.out.println(zahl1 + " < " + zahl2 + " ist " + bool2);
        } else {
            System.out.println(zahl1 + " < " + zahl2 + " ist " + bool1);
        }


        if (zahl1 <= zahl2) {
            System.out.println(zahl1 + " <= " + zahl2 + " ist " + bool2);
        } else {
            System.out.println(zahl1 + " <= " + zahl2 + " ist " + bool1);
        }


        if (zahl1 > zahl2) {
            System.out.println(zahl1 + " > " + zahl2 + " ist " + bool2);
        } else {
            System.out.println(zahl1 + " > " + zahl2 + " ist " + bool1);
        }


        if (zahl1 >= zahl2) {
            System.out.println(zahl1 + " >= " + zahl2 + " ist " + bool2);
        } else {
            System.out.println(zahl1 + " >= " + zahl2 + " ist " + bool1);
        }


    }
}

