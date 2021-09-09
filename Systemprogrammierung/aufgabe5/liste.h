/* liste.h */

#ifndef AUFGABE5_LISTE_H
#define AUFGABE5_LISTE_H

#include "fachnote.h"

void schleife(fach_note *, void (*) (fach_note const *));
fach_note *entfernen(fach_note *, fach_note **);
fach_note *einfuegen(fach_note *, fach_note *, fach_note **);

#endif //AUFGABE5_LISTE_H
