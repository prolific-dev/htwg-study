#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <locale.h>


int main(int argc, char *argv[])
{
    int i;
    struct stat s;
    setlocale(LC_ALL,"");
    
    
    if (argc < 2) {
        fprintf(stderr, "Aufruf: %s Quelle\n", argv[0]);
        return 1;
    }
    
    for (i = 1; i < argc; ++i)
    {
        
        if(stat(argv[i], &s) != 0) 
        {
            perror(argv[i]);
            continue;
        } else {
            printf("Größe: %lu Bytes\tDatei: %s\n", (unsigned long) s.st_size, argv[i]);
        }
    }
    return 0;
}