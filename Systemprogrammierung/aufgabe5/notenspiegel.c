/*
 * notenspiegel.c
 *
 * Liest Fachnamen mit Beurteilung ein und gibt dann einen Notenspiegel aus.
 *
 */

/*  #include-Anweisungen, symbolische Konstante, Typen ... */

#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include "fachnote.h"
#include "liste.h"

int main(void)
{
    fach_note *notenspiegel = NULL;
    fach_note *p;
    fach_note *q;

    /*------------------------------------------- Notenspiegel einlesen */
    fprintf(stderr, "Faecher mit Noten eingeben (Ende mit Strg-D):\n");

    for (;;)
    {
        p = (fach_note *) malloc(sizeof (fach_note));
        if (p == NULL)
        {
            fprintf(stderr, "Zu viele Faecher!\n");
            break;
        }

        if (! einlesen(p))
        {
            fprintf(stderr, "Eingabeende!\n");
            free(p);
            break;
        }

        notenspiegel = einfuegen(notenspiegel, p, &q);
        if (q != NULL)
        {
            fprintf(stderr, "Alte Eingabe ueberschrieben!\n");
            free(q);
        }
    }

    /*------------------------------------------- Notenspiegel ausgeben */
    printf("Notenspiegel:\n");

    schleife(notenspiegel, ausgeben);

    /*------------------------------------------- Notenspiegel loeschen */
    while (notenspiegel != NULL)
    {
        notenspiegel = entfernen(notenspiegel, &p);
        free(p);
    }

    return 0;
}