#
# Makefile
#
# Autor: H.Drachenfels
# Erstellt am: 12.4.2019
#

CC=gcc -g
CFLAGS = -W -Wall -Wvla -std=$(STD) -pedantic
CPPCHECK = cppcheck --enable=all --std=$(STD)
STD=c11

TARGET = stringsort

.PHONY: all clean cppcheck

all: $(TARGET)

clean:
	$(RM) $(TARGET) $(TARGET).o

cppcheck: $(TARGET).c
	$(CPPCHECK) $<

