#!/bin/sh
if [ $# -eq 0 ]
then
    echo "Aufruf: $0 AUFGABE"
    echo "AUFGABE ist Einstieg oder Aufgabe1 oder Aufgabe2 usw."
    exit 1
fi
firefox \
http://www-home.htwg-konstanz.de/~drachen/sypr/sypr.html \
http://www-home.htwg-konstanz.de/~drachen/sypr/${1%/}/${1%/}.html \
http://man7.org/linux/man-pages/index.html \
http://en.cppreference.com/w/c \
http://pubs.opengroup.org/onlinepubs/9699919799/toc.htm \
> /dev/null 2>&1 &
