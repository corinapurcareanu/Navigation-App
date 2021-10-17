/** 
 * Clasa pentru obiecte de tip Ambuteiaj ce contine 
 * informatii despre punctul de pornire si punctul 
 * destinatie al strazii unde are loc ambuteiajul,
 *  si costul suplimentar cauzat de ambuteiajul respectiv
 */
public abstract class Ambuteiaj{
	private int punct_start;
	private int punct_end;
	private int cost;
	/**
	 * Constructor pentru un element de tip Ambuteiaj, ce seteaza
	 * punctul de pornire, punctul destinatie si costul suplimentar
	 * cauzat de ambuteiaj
	 * @param start punctul de pornire
	 * @param end punctul destinatie
	 * @param cost costul suplimentar adaugat pe strada
	 */
	protected Ambuteiaj(int start, int end, int cost){
		this.punct_start = start;
		this.punct_end = end;
		this.cost = cost;
	}
	/** 
	 * Getter pentru punctul de pornire 
	 * @return punctul de pornire
	 */
	public int getStart(){
		return punct_start;
	}
	/** 
	 * Getter pentru punctul destinatie 
	 * @return punctul destinatie
	 */
	public int getEnd(){
		return punct_end;
	}
	/** 
	 * Getter pentru cost 
	 * @return cost
	 */
	public int getCost(){
		return cost;
	}
}
