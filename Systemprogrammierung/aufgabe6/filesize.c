#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <locale.h>


int main(int argc, char *argv[])
{
    int i;
    struct stat s;
    setlocale(LC_ALL,"");
    
    printf("Groesse von %d Datei(en) wird ermittelt:\n" argc - 1);
    for (i = 1; i <= argc -1; ++i)
    {
        if(stat(argv[i], &s) < 0)
        {
            perror("stat");
            continue;
        }
        
        printf("%s: \t%ld Byte\n", argv[i], s.st_size);
    }

    if(argc <= 1)
    {
        char buf[128];
        int n = 0;
        while(read(STDIN_FILENO, buf, sizeof(buf) != EOF))
        {
            n++;
        }
        printf("Byte von der Standarteingabe: %d \n", n);
    }
    return 0;
}
