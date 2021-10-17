/**
 * Clasa pentru obiecte de tip Strada ce contine informatii despre
 * punctul destinatie, costul dintre punctul de pornire si cel 
 * destinatie, limita de gabarit de pe strada si strada urmatoare
 */
public class Strada{
	private int punct_destinatie;
	private int cost;
	private int limita_gabarit;
	Strada urm;
	/**
	 * Constructor pentru strada, ce seteaza punctul destinatie,
	 * costul dintre punctul de pornire si cel destinatie, limita de
	 * gabarit de pe strada respectiva si seteaza campul urm ca fiind null
	 * @param destinatie punctul destinatie
	 * @param cost costul de a circula pe strada respectiva
	 * @param limita limita maxima de gabarit pe strada
	 */
	Strada(int destinatie, int cost, int limita){
		this.punct_destinatie = destinatie;
		this.cost = cost;
		this.limita_gabarit = limita;
		this.urm = null;
	}
	/**
	 * Getter pentru destinatie 
	 * @return punctul destinatie
	 */
	public int getDestinatie(){
		return punct_destinatie;
	}
	/** 
	 * Getter pentru cost 
	 * @return costul de a circula pe strada
	 */
	public int getCost(){
		return cost;
	}
	/** 
	 * Getter pentru limita de gabarit 
	 * @return limita maxima de gabarit
	 */
	public int getLimita(){
		return limita_gabarit;
	}
}
