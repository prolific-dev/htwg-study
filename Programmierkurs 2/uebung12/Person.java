// Dennis Agostinho da Silva
// 30.11.2019

package uebung12;

import java.time.LocalDate;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import java.util.function.*;

public class Person {

    private String vorname;
    private String nachname;
    private LocalDate geburtsdatum;


    public Person(String v,String n,LocalDate gd) {
        this.vorname = v;
        this.nachname = n;
        this.geburtsdatum = gd;
    }

    void setVorname(String v) {
        this.vorname = v;
    }

    void setNachname(String n) {
        this.nachname = n;
    }

    void setGeburtsdatum(LocalDate gd) {
        this.geburtsdatum = gd;
    }

    String getVorname() {
        return vorname;
    }

    String getNachname() {
        return nachname;
    }

    LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    @Override
    public String toString() {
        return vorname + " " + nachname + " " + geburtsdatum;
    }
    
    public static void main1(String[] args) {
        List<Person> personList = new LinkedList<>();
        LocalDate jahr = LocalDate.now().minusYears(18);

        personList.add(new Person("Test3","Drei",LocalDate.of(2000,11,5)));
        personList.add(new Person("Test1","Eins",LocalDate.of(1950,6,7)));
        personList.add(new Person("Test2","Zwei",LocalDate.of(1900,12,4)));
 
        
        Predicate<Person> istVolljährig = x -> x.getGeburtsdatum().isBefore(jahr);
        System.out.println(forAll(personList,istVolljährig));

        Comparator<Person> cmp = (o1, o2) -> o2.getGeburtsdatum().compareTo(o1.getGeburtsdatum());

        Collections.sort(personList, cmp);
        for (Person p : personList) {
            System.out.println(p);
        }
        System.out.println("");
        
        Collections.sort(personList, cmp.reversed());
        for (Person p : personList) {
            System.out.println(p);
        }
        System.out.println("");
        
        
        personList.stream()
                .filter(istVolljährig.negate())
                .sorted(cmp)
                .forEach(System.out::println);
        System.out.println("");

    	personList.stream()
            .peek(x -> x.setNachname(x.getNachname().toUpperCase()))
            .sorted(cmp)
            .forEach(x -> System.out.println(x));

    }



    static boolean forAll(List<Person> personList, Predicate pre) {
        for (Person p : personList) {
            if (!pre.test(p)) return false;
        }
        return true;
    }


}

