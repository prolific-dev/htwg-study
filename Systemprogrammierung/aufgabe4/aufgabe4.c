/*
 * aufgabe4.c
 *
 * Liest Fachnamen mit Beurteilung ein und gibt dann einen Notenspiegel aus.
 *
 */

/*  #include-Anweisungen, symbolische Konstante, Typen ... */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stddef.h>

#define FELDGROESSE 20

enum pruefungsArten {unbenotet = 1,benotet = 2};

typedef struct fach_note {
    int art;	
    char fachname[FELDGROESSE];
    struct fach_note *next;
    struct fach_note *previous;
    union u {
        unsigned char note;
        char beurteilung;
    } u;
    
} fach_note;

void ausgeben(fach_note const *);
void schleife(fach_note *, void (*) (fach_note const *));
fach_note *entfernen(fach_note *, fach_note **);
fach_note *einfuegen(fach_note *, fach_note *, fach_note **);
int einlesen(fach_note *);

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

/* Funktionsimplementierungen */

int einlesen(fach_note *p) {
    
    int note;
    int eingelesen;
    char* helps;
    
    eingelesen = scanf("%20s%d", p->fachname, &note);
    if (eingelesen == benotet) {
        p->art = benotet;
        p->u.note = note;
    } else if (eingelesen == unbenotet) {
        p->art = unbenotet;
        scanf("%c", &p->u.beurteilung);
    } else {
        return 0;
    }
    
    for (helps = p->fachname; (helps = strchr(helps, '_')) != 0; helps++) {
        *helps = ' ';
    }
    
    return eingelesen;
}

void ausgeben(fach_note const *notenspiegel) 
{
    printf("%-20s\t", notenspiegel->fachname);
    
    if(notenspiegel->art == benotet) {
        int vk = notenspiegel->u.note / 10;
        int nk = notenspiegel->u.note - (10 * vk);
        
        if ((vk < 4 && (nk == 0 || nk == 3 || nk == 7)) || ((vk == 4 || vk == 5) && nk == 0) ) {
            printf("%d,%d", vk, nk);	
        } else {
            printf("Fehler: %d", notenspiegel->u.note);
        }
        
    } else if (notenspiegel->art == unbenotet) {
        char x = notenspiegel->u.beurteilung;
        if (x == 'B') {
            printf("bestanden");
        } else if (x == 'N') {
            printf("nicht bestanden");
        } else {
            printf("Fehler: %c", x);
        }
        
    }
    printf("\n");
}

fach_note *einfuegen(fach_note *Notenspiegel, fach_note *p, fach_note **q) {
    
    fach_note *node;
    p->next = Notenspiegel;
    p->previous = NULL;
    
    *q = NULL;
    
    if (Notenspiegel != NULL) {
        Notenspiegel->previous = p;
    }
    
    for(node = Notenspiegel;
        node != NULL && (node->art != p->art || 
        strcmp(node->fachname, p->fachname) != 0); node = node->next);
        { }
        if(node != NULL) {
            node->previous->next = node->next;
            if(node->next != NULL) {
                node->next->previous = node->previous;
            }
            node->previous = NULL;
            node->next = NULL;
        }
    *q = node;
    return p;
}

void schleife(fach_note *notenspiegel, void (*func) (fach_note const *)) {
    
    while (notenspiegel != NULL) {
        func(notenspiegel);
        notenspiegel = notenspiegel->next;
    }
    
}

fach_note *entfernen(fach_note *Notenspiegel, fach_note **q) {
    
    *q = Notenspiegel;
    
    Notenspiegel = Notenspiegel->next;
    
    if (Notenspiegel != NULL) {
        Notenspiegel->previous = NULL;
    }
    return Notenspiegel;
}
