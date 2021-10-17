/**
 * @author Purcareanu Corina 321 CB

 */
import java.io.*; 
import java.util.Scanner; 

public class Main{
	/** Metoda ce citeste din fisier si returneaza output-ul
	 * rezultat in urma tuturor comenzilor drive
	 * @param file fisierul din care citeste datele
	 * @param scan scannerul folosit pentru a citi datele
	 * @return string-ul ce contine output-ul final ce va fi afisat
	 */
	public static String Citire(File file, Scanner scan){
		String output_final = "";
		/* Se citeste prima linie */
	    String prima_linie = scan.nextLine();
	    String[] valori = prima_linie.split(" ");
	    /* Prima valoare de pe linie este numarul de muchii,
	     * adica numarul de strazi pe care le contine harta
	     */
	    int nr_muchii = Integer.valueOf(valori[0]);
	    /* A doua valoare reprezinta numarul de noduri */
	    int nr_noduri = Integer.valueOf(valori[1]);
	    Harta harta = new Harta(nr_noduri);
	    for(int i = 0; i < nr_muchii; i++) {
	    	String linie =	scan.nextLine();
	    	String[] elemente_linie = linie.split(" ");
	    	/* Se elimina primul caracter din noduri, pentru a ramane
	    	 * doar valoarea numerica
	    	 */
	    	elemente_linie[0] = elemente_linie[0].substring(1);
	    	elemente_linie[1] = elemente_linie[1].substring(1);
	    	int nod_sursa = Integer.valueOf(elemente_linie[0]);
	    	int nod_destinatie = Integer.valueOf(elemente_linie[1]);
	    	int cost = Integer.valueOf(elemente_linie[2]);
	    	int	limita_gabarit = Integer.valueOf(elemente_linie[3]);
	    	harta.addStreet(nod_sursa, nod_destinatie, cost, limita_gabarit);
	    }
	    /* Se citesc liniile ramase in fisier */
	    while (scan.hasNextLine()) {
	    	String linie =	scan.nextLine() ;
	    	String[] elemente_linie = linie.split(" ");
	    	/* Daca primul element de pe linie nu este drive, atunci
	    	se adauga la restrictii */
	    	if(elemente_linie[0].equals("drive") == false) {
		    	elemente_linie[1] = elemente_linie[1].substring(1);
		    	elemente_linie[2] = elemente_linie[2].substring(1);
		    	int start = Integer.valueOf(elemente_linie[1]);
		    	int end = Integer.valueOf(elemente_linie[2]);
		    	int cost = Integer.valueOf(elemente_linie[3]);
	    		harta.addRestriction(elemente_linie[0], start, end, cost);
	    	}
	    	/* Altfel, se executa metoda drive */
	    	else {
	    		String type = elemente_linie[1];
	    		elemente_linie[2] = elemente_linie[2].substring(1);
		    	elemente_linie[3] = elemente_linie[3].substring(1);
		    	int start = Integer.valueOf(elemente_linie[2]);
		    	int end = Integer.valueOf(elemente_linie[3]);
		    	String output_drive = harta.drive(type, start, end);
		    	output_final += output_drive + "\n";
	    	}
	    }
	   return output_final;
	}
	/**
	 * @param args parametrul metodei main
	 * @throws FileNotFoundException Exceptia ce apare daca nu este gasit fisierul
	 */
	public static void main(String[] args) throws FileNotFoundException{
		try {
			File file =  new File("map.in"); 
			FileWriter writer = new FileWriter("map.out");
			Scanner scan = new Scanner(file);
			/* Se citeste din fisierul "map.in" */
			String output = Citire(file, scan);
		 	scan.close();
		 	/* Scrie rezultatul in fisierul "map.out" */
		 	writer.write(output);
		 	writer.close();
		}
		catch(FileNotFoundException e){
			
		}
		catch(IOException e){
			
		}
	}
}
