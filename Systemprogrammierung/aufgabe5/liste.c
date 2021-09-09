/* liste.c */

#include <string.h>
#include "liste.h"

fach_note *einfuegen(fach_note *notenspiegel, fach_note *p, fach_note **q)
{

    fach_note *node;
    p->next = notenspiegel;
    p->previous = NULL;

    if (notenspiegel != NULL)
    {
        notenspiegel->previous = p;
    }

    for (node = notenspiegel;
         node != NULL &&
         (node->art != p->art || strcmp(node->fachname, p->fachname) != 0);
         node = node->next);
    { }
    if (node != NULL)
    {
        node->previous->next = node->next;
        if (node->next != NULL)
        {
            node->next->previous = node->previous;
        }
        node->previous = NULL;
        node->next = NULL;
    }
    *q = node;
    return p;
}

void schleife(fach_note *notenspiegel, void (*func) (fach_note const *))
{
    while (notenspiegel != NULL)
    {
        func(notenspiegel);
        notenspiegel = notenspiegel->next;
    }

}

fach_note *entfernen(fach_note *notenspiegel, fach_note **q)
{

    *q = notenspiegel;

    notenspiegel = notenspiegel->next;

    if (notenspiegel != NULL)
    {
        notenspiegel->previous = NULL;
    }
    return notenspiegel;
}

