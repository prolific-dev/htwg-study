#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <time.h>

int main(int argc, char* argv[])
{
    srand((unsigned int)time(NULL));

    int arraysize;
    printf("Anzahl zu sortierender Werte eingeben: ");
    scanf("%d", &arraysize);
    int* a = malloc(arraysize * sizeof(int));
    if (a == NULL)
    {
        printf("Speicherresservierung fehlgeschlagen!\n");
        return 1;
    }
    printf("%d ganze Zahlen eingeben: ", arraysize);

    for (int i = 0; i < arraysize; i++)
    {
        int check = scanf("%d", &a[i]);
        if (check == EOF || check < 0)
        {
            a[i] = rand();
        }
    }
    for (int i = arraysize; i > 1; i--)
    {
        for (int j = 0; j < i - 1; ++j)
        {
            if (a[j] > a[j + 1])
            {
                int tmp = a[j + 1];
                a[j + 1] = a[j];
                a[j] = tmp;
            }
        }
    }
    for (int i = 0; i < arraysize; ++i)
    {
        printf("%d\n", a[i]);
    }
    return 0;
}
