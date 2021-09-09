#
# Makefile
#
# Autor: H.Drachenfels
# Erstellt am: 31.1.2019
#

CC=gcc -g
CFLAGS = -g -W -Wall -Wvla -std=$(STD) -pedantic
CPPCHECK = cppcheck --enable=all --std=$(STD)
STD=c11

TARGET = bubblesort

.PHONY: all clean cppcheck

all: $(TARGET)

clean:
	$(RM) $(TARGET) $(TARGET).o

cppcheck: $(TARGET).c
	$(CPPCHECK) $<

