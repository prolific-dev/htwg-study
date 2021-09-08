#!/bin/sh
command="java -jar ~/merkur/Drachenfels/prog/Klara.jar -t -v $* | tee trace.txt"
echo $command
eval $command
