#include <stdio.h>
#include <limits.h>
#include <float.h>

int main()
{
    char c = 'c';
    signed char sc = -1;
    unsigned char us = 0xff;
    short s = 1;
    unsigned short su = 2;
    int i = 3;
    unsigned int iu = 4;
    long int li = 5;
    long long int lli = 6;
    unsigned long int uli = 7;
    unsigned long long int ulli = 8;
    float f = 3.10;
    double d = 3.11;
    long double ld = 3.12;
    const char* str = "Hallo";

    printf(" Adresse: %p\t | Typ: %s\t\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %s%c\t\n", (void* )&c, "char", "c", sizeof(char), "", c);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %i\t\n", (void *)&sc, "signed char", "sc", sizeof(signed char), sc);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %u\t\n", (void *)&us, "unsigned char", "uc", sizeof(unsigned char), us);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %i\t\n", (void *)&s, "short", "s", sizeof(short), s);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %u\t\n", (void *)&su, "unsigned short", "su", sizeof(unsigned short), su);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %i\t\n", (void *)&i, "int", "i", sizeof(int), i);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %u\t\n", (void *)&iu, "unsigned int", "iu", sizeof(unsigned int), iu);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %ld\t\n", (void *)&li, "long int", "li", sizeof(long long), li);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %lld\t\n", (void *)&lli, "long long int", "lli", sizeof(long long), lli);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %lu\t\n", (void *)&uli, "unsigned long int", "uli", sizeof(unsigned long), uli);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %llu\t\n", (void *)&ulli, "unsigned long long int", "ulli", sizeof(unsigned long long), ulli);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %g\t\n", (void *)&f, "float", "f", sizeof(float), f);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %g\t\n", (void *)&d, "double", "d", sizeof(double), d);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %Lg\t\n", (void *)&ld, "long double", "ld", sizeof(long double), ld);
    printf("\n");
    printf(" Adresse: %p\t | Typ: %s\t\t\t | Name: %s\t | Platzbedarf: %lu\t | Wert: %s\t\n", (void *)&str, "char*", "s", sizeof(char*), str);

    printf("\nDatenmodell: ");

    if (sizeof(int) == 4 && sizeof(long int) == 4 && sizeof(char*) == 4)
        printf("ILP32");
    else if (sizeof(long int) == 8 && sizeof(char*) == 8 && sizeof(int) == 4)
        printf("ILP64");
    else if (sizeof(int) == 4  && sizeof(long int) == 4 && sizeof(long long int) == 8 && sizeof(char) == 8)
        printf("LLP64-Datenmodell");
    else
        printf("unknown");

    printf("\n");
    return 0;
}



