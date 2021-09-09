#
# Makefile
#
# Autor: H.Drachenfels
# Erstellt am: 12.10.2018
#

CC=gcc -g -fno-stack-protector
CFLAGS = -W -Wall -std=$(STD) -pedantic
CPPCHECK = cppcheck --enable=all --std=$(STD)
STD=c11

TARGET = aufgabe1

.PHONY: all clean cppcheck

all: $(TARGET)

clean:
	$(RM) $(TARGET) $(TARGET).o

cppcheck: $(TARGET).c
	$(CPPCHECK) $<

