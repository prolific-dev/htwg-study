// Dennis Agostinho da Silva
// 17.11.2019

package uebung11;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TelefonBuch {

    private TreeMap<String,String> telBuch = new TreeMap<String,String>();

    public boolean insert(String name, String zusatz, String telNr) {
        if (!telBuch.containsKey(name + "" + zusatz)) {
            telBuch.put(name + "" + zusatz, telNr);
            return true;
        }
        return false;
    }

    public boolean remove(String name, String zusatz) {
        if (telBuch.containsKey(name + "" + zusatz)) {
            telBuch.remove(name + "" + zusatz);
            return true;
        }
        return false;
    }

    public String exactSearch(String name, String zusatz) {
        String telNr;
        if ((telNr = telBuch.get(name + "" + zusatz)) != null) {
            return telNr;
        }
        return null;
    }

    public List<String> prefixSearch(String s) {
        List<String> list = new LinkedList<>();
        if (s.length() <= 0) {
        	for (Entry<String,String> eintrag : telBuch.entrySet())
                list.add(eintrag.getKey() + " " + eintrag.getValue());
            return list;
        }
        String tmp = new String();
        if (s.length() > 1) {
            for (int i = 0; i < s.length() - 1; i++) {
                tmp += s.charAt(i);
            }
        }
        char a = (char) (s.charAt(s.length() - 1) + 1);
        tmp += a;
        for (Entry<String,String> eintrag : telBuch.subMap(s,true,tmp,false).entrySet()) {
            list.add(eintrag.getKey() + " " + eintrag.getValue());
        }
        if (list.size() == 0) {
        	return null;
        }
        return list;
    }

    public void read(File f) {
        LineNumberReader in = null;
        try {
            telBuch.clear();
            in = new LineNumberReader(new FileReader(f));
            String line;
            while ((line = in.readLine()) != null) {
                String[] sf = line.split(" ");
                if (sf.length == 2) {
                    insert(sf[0], "", sf[1]); // leerer Zusatz
                } else if (sf.length == 3) {
                    insert(sf[0], sf[1], sf[2]);
                }
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(TelefonBuch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save(File f) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(f);
            for (Entry<String, String> eintrag : telBuch.entrySet()) {
                String s = eintrag.getKey().replace('#', ' ') + " " + eintrag.getValue();
                out.println(s);
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(TelefonBuch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void print(List<String> strList) {
        for (String s : strList)
            System.out.println(s);
    }

    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        
        TelefonBuch telBuch = new TelefonBuch();
        telBuch.read(new File("test.txt"));

        System.out.println(telBuch.exactSearch("Oliver",""));
        System.out.println();

        print(telBuch.prefixSearch("Ha"));
        System.out.println();
        
        print(telBuch.prefixSearch(""));
        System.out.println();

        telBuch.insert("Oliver","1","33245");
        telBuch.insert("Oliver","2","23423");
        telBuch.insert("Oliver","3","87655");
        telBuch.remove("Oliver","2");

        print(telBuch.prefixSearch("Ol"));
        System.out.println();
       
        telBuch.save(new File("test.txt"));
    }
}

