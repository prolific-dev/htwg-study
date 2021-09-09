#include <stdio.h>  /* fprintf */
#include <string.h> /* strerror */
#include <locale.h>
#include <stdlib.h>

#include <fcntl.h>    /* open, O_RDONLY, O_WRONLY, O_CREAT, O_EXCL */
#include <sys/stat.h> /* mode_t, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH */
#include <unistd.h>   /* read, write */
#include <errno.h>    /* errno */

int main(int argc, char *argv[])
{
    const mode_t mode = S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH; /* rw-r--r-- */
    int in, out; /* Dateideskriptoren */
    struct stat s;
    
    setlocale(LC_MESSAGES, "de_DE.UTF-8");
    
    if (argc != 3)
    {
        fprintf(stderr, "Aufruf: %s Quelle Ziel\n", argv[0]);
        return 1;
    }
    
    errno = 0; /* kein Fehler */
    
    in = open(argv[1], O_RDONLY);
    if (in == -1)
    {
        fprintf(stderr,
                "Quelle %s kann nicht geoeffnet werden (errno %d: %s)\n",
                argv[1], errno, strerror(errno));
        return 1;
    }   

    out = open(argv[2], O_WRONLY | O_CREAT | O_EXCL, mode);
    if (out == -1)
    {
        fprintf(stderr,
                "Ziel %s kann nicht erzeugt werden (errno %d: %s)\n",
                argv[2], errno, strerror(errno));
        return 1;
    }
    
    if (fstat(in,&s) != 0) 
    {
        fprintf(stderr, "Fehler: %s (errno %d: %s)\n",
                argv[1], errno, strerror(errno));
        return -1;
    } else {
        void* a;
        int n;
        a = (void*) malloc(s.st_size);        
        if (a == NULL) 
        {
            fprintf(stderr, "malloc: failed");
            free(a);
            return 1;
        }
        while ((n = read(in,a,s.st_size)) > 0) 
        {
            int m = write(out,a,n);
            if (m == -1)
            {
                fprintf(stderr,
                        "Schreibfehler (errno %d: %s)\n", errno, strerror(errno));
                return 1;
            }
        }
        
        if (n < 0)
        {
            fprintf(stderr,
                    "Lesefehler (errno %d: %s)\n", errno, strerror(errno));
            return 1;
        } 
        free(a);
    }
    
    close(out);
    close(in);
    
    return 0; 
}