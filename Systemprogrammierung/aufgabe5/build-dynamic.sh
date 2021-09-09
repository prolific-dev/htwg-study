#!/bin/sh
for s in notenspiegel.c liste.c fachnote.c ; do
    compile_command="gcc -c $s"
    echo $compile_command
    eval $compile_command
    if [ $? -ne 0 ] ; then
        echo build failed
        exit 1
        fi
    done  
    lib="gcc -shared -fPIC -o libaufgabe5.so fachnote.c liste.c"
    echo $lib
    eval $lib
    if [ $? -ne 0 ] ; then
        echo build failed
        exit 1
        fi
    link="gcc -o dynamisch notenspiegel.o libaufgabe5.a"
    echo $link
    eval $link
        if [ $? -ne 0 ] ; then
        echo build failed
        exit 1
        fi
   # LD_LIBRARY_PATH=. ./notenspiegel < aufgabe4-in.txt > out.txt             
    echo build sucessful