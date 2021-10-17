/**
 *  Clasa pentru obiecte de tip Harta, ce contine informatii despre 
 * numarul maxim de noduri, graful stocat sub forma de lista de adiacenta
 * pentru strazi, si listele pentru ambuteiajele ce apar pe harta
 */
public class Harta{
	private int max_noduri;
	private Strada[] strazi;
	private Ambuteiaj[] ambuteiaje = new Ambuteiaj[3];
	/**
	 * Constructor pentru harta, ce initializeaza cu null
	 * toate elementele din vectorul pentru strazi dar si
	 * din cel pentru ambuteiaje
	 * @param max numarul maxim de noduri de pe harta
	 */
	Harta(int max){
		this.max_noduri = max;
		strazi = new Strada[max_noduri];
		for(int i = 0; i < max; i++){
			strazi[i] = null;
		}
		for(int j = 0; j < 3; j++){
			ambuteiaje[j] = null;
		}
	}
	/**
	 * Metoda ce adauga strazile, stocandu-le intr-un graf reprezentat
	 * prin lista de adiacenta
	 * @param start punctul din care incepe strada
	 * @param end punctul unde se termina strada
	 * @param cost costul de a circula pe strada respectiva
	 * @param size limita maxima de gabarit pe strada respectiva
	 */
	public void addStreet(int start, int end, int cost, int size){
		if(strazi[start] == null)
			strazi[start] = new Strada(end, cost, size);
		else{
			Strada aux = strazi[start];
			while(aux.urm != null)
				aux = aux.urm;
			aux.urm = new Strada(end, cost, size);
		}
	}
	/** 
	 * Metoda ce adauga resctrictiile in vectorul pentru restrictii
	 * @param type tipul restrictiei
	 * @param start punctul de pornire
	 * @param end punctul destinatie
	 * @param cost costul suplimentar adaugat pe strada respectiva 
	 */
	public void addRestriction(String type, int start, int end, int cost){
		int i;
		/* Lista pentru restrictiile de tip accident se afla pe pozitia 0 in vector */
		if(type.equals("accident")){
			i = 0;
			if(ambuteiaje[i] == null)
				ambuteiaje[i] = new Accident(start, end, cost);
			else{
				Accident aux = (Accident)(ambuteiaje[i]);
				while(aux.urm != null)
					aux = aux.urm;
				aux.urm = new Accident(start, end, cost);					
			}
		}
		/* Lista pentru restrictiile de tip trafic se afla pe pozitia 1 in vector */
		if(type.equals("trafic")){
			i = 1;
			if(ambuteiaje[i] == null)
				ambuteiaje[i] = new Trafic(start, end, cost);
			else {
				Trafic aux = (Trafic)(ambuteiaje[i]);
				while(aux.urm != null)
					aux = aux.urm;
				aux.urm = new Trafic(start, end, cost);					
			}
		}
		/* Lista pentru restrictiile de tip blocaj se afla pe pozitia 2 in vector */
		if(type.equals("blocaj")){
			i = 2;
			if(ambuteiaje[i] == null)
				ambuteiaje[i] = new Blocaj(start, end, cost);
			else{
				Blocaj aux = (Blocaj)(ambuteiaje[i]);
				while(aux.urm != null)
					aux = aux.urm;
				aux.urm = new Blocaj(start, end, cost);					
			}
			
		}		
	}
	/**
	 * Metoda ce verifica daca intr-un vector se gaseste valoarea 0, 
	 * ce returneaza true daca s-a gasit si false altfel
	 * @param vector vectorul in care se cauta valoarea 0
	 * @param max numarul maxim de elemente din vector
	 * @return true daca se gaseste, false altfel
	 */
	public boolean find0(int[] vector, int max){
		for(int i = 0; i < max; i++){
			if(vector[i] == 0)
				return true;
		}
		return false;
	}
	
	/**
	 * Metoda ce adauga cost suplimentar pe o strada daca se
	 * gaseste un ambuteiaj pe aceasta
	 * @param start punctul de pornire
	 * @param end punctul destinatie
	 * @return costul suplimentar pe strada respectiva
	 */
	public int costSuplimentar(int start, int end){
		/* Costul suplimentar are initial valoarea 0 */
		int cost = 0;
		/* Daca exista accidente in lista pentru accidente, 
		 * se verifica daca exista accidente pe strada respectiva
		 * si se adauga costul la costul ce urmeaza sa fie returnat
		 */
		if(ambuteiaje[0] != null){
			Accident aux = (Accident)(ambuteiaje[0]);
			while(aux != null){
				if(aux.getStart() == start && aux.getEnd() == end){
					cost += aux.getCost();
				}
				aux = aux.urm;
			}
		}
		/* Daca exista trafic in lista pentru trafic, se verifica
		 * daca exista accidente pe strada respectiva si se adauga 
		 * costul la costul ce urmeaza sa fie returnat
		 */
		if(ambuteiaje[1] != null){
			Trafic aux = (Trafic)(ambuteiaje[1]);
			while(aux != null){
				if(aux.getStart() == start && aux.getEnd() == end){
					cost += aux.getCost();
				}
				aux = aux.urm;
			}
		}
		/* Daca exista blocaje in lista pentru blocaje, se verifica
		 * daca exista accidente pe strada respectiva si se adauga 
		 * costul la costul ce urmeaza sa fie returnat
		 */
		if(ambuteiaje[2] != null){
			Blocaj aux = (Blocaj)(ambuteiaje[2]);
			while(aux != null) {
				if(aux.getStart() == start && aux.getEnd() == end){
					cost += aux.getCost();
				}
				aux = aux.urm;
			}
		}
		return cost;
	}
	
	/**
	 *  Metoda ce gaseste cel mai scurt drum dintre doua puncte, folosind algoritmul Dijkstra
	 *  @param v vehiculul condus
	 *  @param start punctul de unde incepe sa conduca vehiculul
	 *  @param end punctul unde trebuie sa ajunga vehiculul
	 *  @param costuri vectorul de costuri minime
	 *  @param vizitat vectorul in care se marcheaza daca un nod a fost vizitat sau nu
	 *  @return ultimul nod vizitat
	 */
	public Nod Dijkstra(Vehicul v, int start, int end, int[] costuri, int[] vizitat){
		int gabarit_vehicul = v.getGabarit();
		Nod nod_pornire = new Nod(start, 0);
		costuri[start] = 0;
		/* Se initializeaza coada de prioritati cu nodul ce contine punctul de pornire 
		 * si costul 0
		 */
		PriorityQueue coada_prioritati = new PriorityQueue(nod_pornire);
		/* Se adauga si restul nodurilor in coada, formate din celalalte puncte existente
		 * si costul punctului respectiv, care este initial infinit
		 */
		for(int i = 0; i < max_noduri; i++){
			if(i != start){
				Nod nod = new Nod(i, costuri[i]);
				coada_prioritati.AdaugaDupaPrioritate(nod);
			}									
		}
		/* Cat timp exista puncte nevizitate */
		while(find0(vizitat, max_noduri) == true){
			/* Daca coada nu este goala, se elimina primul nod */
			if(coada_prioritati.inceput() != null){
				Nod nod_curent = coada_prioritati.Elimina();
				/* Daca punctul nodului curent este punctul destinatie, se opreste */
				if(nod_curent.getPunct() == end) {
					return nod_curent;
				}
				int punct = nod_curent.getPunct();
				/* Se marcheaza punctul ca fiind vizitat */
				vizitat[punct] = 1;
				Strada aux = strazi[punct];
				/* Se parcurge lista de strazi care au ca punct de pornire punctul nodului curent */
				while(aux != null){
					/* Daca limita de gabarit pe strada respectiva este mai mare 
					 * sau egala cu cea a vehicului condus, acesta poate intra pe strada
					 */
					if(aux.getLimita() >= gabarit_vehicul){	
						int destinatie = aux.getDestinatie();
						/* Daca punctul destinatie al strazii respective nu a fost vizitat */
						if(vizitat[destinatie] == 0){
							int cost_suplimentar = costSuplimentar(punct, aux.getDestinatie());
							/* Costul nou este format din costul suplimentar, costul pe care il avea
							 * punctul de pornire in vectorul de costuri si costul de pe acea strada
							 * inmultit cu costul vehiculului
							 */
							int cost_nou = cost_suplimentar + costuri[punct] + v.getCost() * aux.getCost();
							/* Daca costul nou este mai mic sau egal cu costul pe care il avea deja punctul
							 * destinatie, se actualizeaza costul, se adauga nodul format din noul cost si 
							 * punctul destinatie in coada, iar nodul curent devine parintele nodului nou
							 */
							if(cost_nou < costuri[destinatie]){
								costuri[destinatie] = cost_nou;
								Nod nod_nou = new Nod(destinatie, cost_nou, nod_curent);
								coada_prioritati.AdaugaDupaPrioritate(nod_nou);
							}
						}
					}
					aux = aux.urm;
				}
				System.out.println();
			}
			else {
				break;
			}
		}
		return null;
	}
	
	/**
	 * Metoda ce returneaza un anumit tip de vehicul, in functie
	 * de ce este specificat atunci cand se da comanda drive
	 * @param vehicle vehiculul ce urmeaza sa fie initializat cu un anumit tip
	 * @return vehiculul ce a fost initializat
	 */
	public Vehicul tipVehicul(String vehicle){
		Vehicul vehicul;
		if(vehicle.equals("b"))
			vehicul = new Bicicleta();
		else if(vehicle.equals("m"))
			vehicul = new Motocicleta();
		else if(vehicle.equals("a"))
			vehicul = new Autoturism();
		else
			vehicul = new Camion();
		return vehicul;
	}
	
	/**
	 *  Metoda ce executa comanda drive si returneaza punctele prin care trece
	 * vehiculul, precum si costul total al drumului
	 * @param vehicle vehiculul ce este condus
	 * @param start punctul de pornire
	 * @param end punctul destinatie
	 * @return string-ul ce contine punctele prin care trece vehiculul si costul minim
	 */
	public String drive(String vehicle, int start, int end){	
		int[] costuri = new int[max_noduri], vizitat = new int[max_noduri];
		Vehicul vehicul = tipVehicul(vehicle);	
		int i;
		String output = "";
		/* Initial, vectorul pentru costuri contine infinit pe toate pozitiile,
		 * iar vectorul pentru vizitat contine 0, ceea ce inseamna ca punctul 
		 * respectiv nu a fost vizitat
		 */
		for(i = 0; i < max_noduri; i++){
			costuri[i] = Integer.MAX_VALUE;
			vizitat[i] = 0;
		}
		Nod nod = Dijkstra(vehicul, start, end, costuri, vizitat);
		/* Daca metoda Dijkstra a returnat null, inseamna ca vehiculul nu a putut
		 * ajunge de la punctul de pornire la punctul destinatie
		 */
		if(nod == null){
			output = "P" + String.valueOf(start) + " P" + String.valueOf(end) +" null";
		}
		/* Altfel, se parcurg parintii nodului destinatie pana se ajunge sa nu mai aiba
		 * stramosi, si sunt adaugate punctele la output-ul final, la inceputul string-ului
		 */
		else{
			while(nod.getParinte() != null){
				output = " P"+ String.valueOf(nod.getPunct()) + output;
				nod = nod.getParinte();
				
			}
			/* Daca ultimul punct la care se ajunge nu este cel de pornire, inseamna ca vehiculul
			 * nu a putut conduce pe acel drum, iar costul drumului este null
			 */
			if(nod.getPunct() != start){
				output = "P" + String.valueOf(start) + " P" + String.valueOf(end) +" null";
			}
			/* Altfel, se adauga costul total la string-ul output */
			else{
				output = "P"+ String.valueOf(nod.getPunct()) + output;
				output += " " + String.valueOf(costuri[end]);
			}
		}
		return output;
	}
}
