/** 
 * Clasa ce implementeaza o coada de prioritate, 
 * care contine metode de eliminare din coada si de
 * a adauga un nou element de tip Nod in coada in
 * functie de prioritate
 */
public class PriorityQueue{
	private Nod inceput;
	private Nod sfarsit;
	/**
	 * Constructor pentru coada de prioritati 
	 * @param punct_start nodul cu care este initializata coada
	 */
	PriorityQueue(Nod punct_start){
		this.inceput = punct_start;
		this.sfarsit = punct_start;
	}
	/**
	 * Getter pentru primul nod
	 * @return primul nod din coada 
	 */
	public Nod inceput(){
		return inceput;
	}
	/**
	 * Metoda ce elimina un nod din coada si returneaza nodul eliminat 
	 * @return nodul eliminat din coada
	 */
	public Nod Elimina(){
		Nod eliminat = inceput;
		inceput = inceput.urm;
		if(inceput == null){
			sfarsit = null;
		}
		return eliminat;
	}
	/**
	 *  Metoda ce adauga in coada de prioritati 
	 *  @param nou nodul ce va fi adaugat in coada
	 */
	public void AdaugaDupaPrioritate(Nod nou){
		/* Daca coada e goala, nodul se adauga in coada ca fiind primul element */
		if(inceput == null && sfarsit == null){
			inceput = nou;
			sfarsit = nou;
		}
		/* Altfel, se adauga in functie de prioritate, care este data de costul nodului */
		else{
			Nod p, ant;
			for(p = inceput, ant = null; p != null; ant = p, p = p.urm){
				/* Daca exista deja un nod cu acelasi punct, este eliminat din coada */
				if(p.getPunct() == nou.getPunct()){
					if(ant != null){
						p = p.urm;
						ant.urm = p;
					}
					else{
						inceput = inceput.urm;
					}
					break;
				}
			}
			/* Daca nodul ce urmeaza sa fie adaugat are cel mai mic cost,
			 * este inserat la inceput
			 */
			if(nou.getCost() <= inceput.getCost()){
				nou.urm = inceput;
				inceput = nou;
			}
			/* Altfel, se adauga pe pozitia corespunzatoare */
			else{
				Nod aux = inceput;
				/* Se parcurg elementele atata timp cat costul nodului este mai 
				 * mare sau egal decat costul lor
				 */
				while(aux.urm != null && nou.getCost() >= aux.urm.getCost()){
					aux = aux.urm;
				}
				/* Se insereaza nodul */
				nou.urm = aux.urm;
				aux.urm= nou;
				/* Se actualizeaza sfarsitul cozii */
				if(aux.urm.urm == null){
					sfarsit = aux.urm;
				}
			}
		}
	}
}
