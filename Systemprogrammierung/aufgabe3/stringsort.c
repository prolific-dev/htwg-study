#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

int main(int argc, char *argv[])
{
    int n;
    char** a;
    int i;
    int j;
    int m;
    char* tmp;
    char* ausgabe;
    srand((unsigned int) time(NULL));


    if (argc != 2)
    {
        printf("Aufruf: Stringsort Anzahl\n");
        return 1;
    }

    n = atoi(argv[1]);
    m = strlen(argv[1]) + 1;
    a = (char**) malloc(n * sizeof(char*));

    if (a == NULL)
    {
        printf("Speicherreservierung fehlgeschlagen!\n");
        return 1;
    }

    for (i = 0; i < n; i++)
    {
        int r;
        r = rand() % n;
        a[i] = (char*) malloc(m);
        if (a[i] == NULL)
        {
            printf("Speicherreservierung fehlgeschlagen!\n");
            return 1;
        }
        sprintf(a[i], "%d", r);
    }

    for (i = n; i > 1; i--)
    {
        for (j = 0; j < i - 1; ++j)
        {
            if (strcmp(a[j], a[j + 1]) > 0)
            {
                tmp = a[j + 1];
                a[j + 1] = a[j];
                a[j] = tmp;
            }
        }
    }

    ausgabe = (char*) malloc(n * m);
    if (ausgabe == NULL)
    {
        printf("Speicherreservierung fehlgeschlagen!\n");
        return 1;
    }
    printf("Sortiertes Feld: \n");
    strcpy(ausgabe, a[0]);
    for (i = 1; i < n; ++i)
    {
        if ((strcmp(a[i], a[i - 1]) == 0))
        {
            strcat(ausgabe, "*");
        }
        else
        {
            strcat(ausgabe, ",");
            strcat(ausgabe, a[i]);
        }
    }
    printf("%s ", ausgabe);
    for (i = 0; i < n; i++)
    {
        free(a[i]);
    }
    free(ausgabe);
    free(a);

    return 0;
}
