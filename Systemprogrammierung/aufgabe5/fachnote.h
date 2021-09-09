/* fachnote.h*/

#ifndef AUFGABE5_FACHNOTE_H
#define AUFGABE5_FACHNOTE_H

#define FELDGROESSE 20
enum pruefungsArten {unbenotet = 1, benotet};

typedef struct fach_note
{
    char fachname[FELDGROESSE];
    struct fach_note *next;
    struct fach_note *previous;
    enum pruefungsArten art;
    union u
    {
        unsigned char note;
        char beurteilung;
    } u;
} fach_note;

void ausgeben(fach_note const *);
int einlesen(fach_note *);


#endif //AUFGABE5_FACHNOTE_H
