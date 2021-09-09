#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

int main(int argc, char *argv[])
{

    int n;
    char *array;
    int i;
    int m;
    int j;
    char * tmp;
    char * ausgabe;

    srand((unsigned int) time(NULL));

    if (argc != 2)
    {
        printf("Aufruf: c Stringsort Anzahl\n");
        return 1;
    }

    n = atoi(argv[1]);
    if(n < 1)
    {
        printf("Anzahl muss midestens 1 sein\n");
        return 1;
    }
    m = strlen(argv[1]) + 1;
    array = (char *) malloc(n * m * sizeof(char));

    if (array == NULL)
    {
        printf("Speicherreservierung fehlgeschlagen\n");
        return 1;
    }

    for (i = 0; i < n; ++i)
    {
        int r;
        r = rand() % n;
        sprintf(array + i * m, "%d", r);
        printf("%s ", array + i * m);
    }

    tmp = (char *) malloc(m * sizeof(char));
    if (tmp == NULL)
    {
        printf("Speicherreservierung fehlgeschlagen\n");
        return 1;
    }


    for (i = n; i > 1; i--)
    {
        /* groessten Wert nach hinten schieben*/
        for (j = 0; j < i - 1; ++j)
        {
            if (strcmp(array + j * m, array + (j + 1)*m) > 0)
            {
                /*Werte tauschen*/
                strcpy(tmp, array + (j + 1)*m);
                strcpy(array + (j + 1)*m, array + j * m);
                strcpy(array + j * m, tmp);
            }
        }
    }

    printf("\n Sortiertes Feld: \n");

    ausgabe = (char*) malloc (n * m);
    if (ausgabe == NULL)
    {
        printf("Speicherreservierung fehlgeschlagen\n");
        return 1;
    }

    strcpy(ausgabe, array);

    for (i = 0; i < n - 1; ++i)
    {
        if (strcmp(array + i * m, array + (i + 1)*m) == 0)
        {
            strcat(ausgabe, "*");
        }
        else
        {
            strcat(ausgabe, ", ");
            strcat(ausgabe, array + (i + 1)*m);
        }
    }

    printf("%s ", ausgabe);

    free(tmp);
    free(array);
    free(ausgabe);


    return 0;
}