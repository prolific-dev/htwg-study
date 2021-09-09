#!/bin/sh
for s in notenspiegel.c liste.c fachnote.c ; do
    compile_command="gcc -static -c $s"
    echo $compile_command
    eval $compile_command
    if [ $? -ne 0 ] ; then
        echo build failed
        exit 1
        fi
    done
    lib="ar -crv libaufgabe5.a fachnote.o liste.o"
    echo $lib
    eval $lib
    if [ $? -ne 0 ] ; then
        echo build failed
        exit 1
        fi
    link="gcc -o static notenspiegel.o libaufgabe5.a"
    echo $link
    eval $link
        if [ $? -ne 0 ] ; then
        echo build failed
        exit 1
        fi    
   echo build sucessful