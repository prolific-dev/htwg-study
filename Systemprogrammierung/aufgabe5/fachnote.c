/* fachnote.c */

#include <stdio.h>
#include <string.h>
#include "fachnote.h"

int einlesen(fach_note *p)
{

    int note;
    int eingelesen;
    char* helps;

    eingelesen = scanf("%20s%d", p->fachname, &note);
    if (eingelesen == 2)
    {
        p->art = benotet;
        p->u.note = note;
    }
    else if (eingelesen == 1)
    {
        p->art = unbenotet;
        scanf("%c", &p->u.beurteilung);
    }
    else
    {
        return 0;
    }

    for (helps = p->fachname; (helps = strchr(helps, '_')) != 0; helps++)
    {
        *helps = ' ';
    }

    return eingelesen;
}

void ausgeben(fach_note const *notenspiegel)
{
    printf("%-20s\t", notenspiegel->fachname);

    if(notenspiegel->art == 2)
    {
        int vk = notenspiegel->u.note / 10;
        int nk = notenspiegel->u.note - (10 * vk);

        if ((vk < 4 && (nk == 0 || nk == 3 || nk == 7)) || ((vk == 4 || vk == 5) && nk == 0) )
        {
            printf("%d,%d", vk, nk);
        }
        else
        {
            printf("Fehler: %d", notenspiegel->u.note);
        }

    }
    else if (notenspiegel->art == 1)
    {
        char x = notenspiegel->u.beurteilung;
        if (x == 'B')
        {
            printf("bestanden");
        }
        else if (x == 'N')
        {
            printf("nicht bestanden");
        }
        else
        {
            printf("Fehler: %c", x);
        }

    }
    printf("\n");
}
