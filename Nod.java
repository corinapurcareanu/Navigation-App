/**
 * Clasa pentru obiecte de tip Nod ce contine informatii despre
 * punctul destinatie, costul dintre punctul de pornire si cel 
 * destinatie, nodul urmator si nodul parinte al nodului curent
 */
public class Nod{
	private int punct;
	private int cost;
	public Nod urm, parinte;
	/**
	 * Constructor pentru obiecte de tip Nod, ce
	 * seteaza o valoare pentru punct, pentru cost si 
	 * steaza campurile urm si parinte ca fiind null
	 * @param punct punctul destinatie
	 * @param cost costul dintre punctul de pornire si punctul destinatie
	 */
	public Nod(int punct, int cost){
		this.punct = punct;
		this.cost = cost;
		this.urm = null;
		this.parinte = null;
	}
	/**
	 * Constructor pentru obiecte de tip Nod, ce
	 * seteaza o valoare pentru punct, pentru cost, 
	 * steaza parintele nodului, iar campul urm ca
	 * fiind null
	 * @param punct punctul destinatie
	 * @param cost costul dintre punctul de pornire si punctul destinatie
	 * @param parinte parintele nodului curent
	 */
	public Nod(int punct, int cost, Nod parinte){
		this.punct = punct;
		this.cost = cost;
		this.urm = null;
		this.parinte = parinte;
	}
	/**
	 * Getter pentru punct
	 * @return punctul destinatie
	 */
	public int getPunct(){
		return punct;
	}
	/**
	 * Getter pentru cost 
	 * @return costul dintre punctul de pornire si punctul destinatie
	 */
	public int getCost(){
		return cost;
	}
	/** 
	 * Getter pentru nodul urmator 
	 * @return nodul urmator
	 */
	public Nod getUrm(){
		return urm;
	}
	/** 
	 * Getter pentru nodul parinte 
	 * @return nodul parinte
	 */
	public Nod getParinte(){
		return parinte;
	}
}
