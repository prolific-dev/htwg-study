#!/bin/sh
for s in notenspiegel.c liste.c fachnote.c
    do
    compile_command="gcc -c $s"
    echo $compile_command
    eval $compile_command
    if [ $? -ne 0 ] ; then
        echo build failed
        exit 1
        fi
    done
    link_command="gcc -o notenspiegel notenspiegel.o liste.o fachnote.o"
    echo $link_command
    eval $link_command
    if [ $? -ne 0 ] ; then
        echo build failed
        exit 1
    fi
    echo build sucessful




