/** 
 * Clasa pentru obiecte Vehicul ce contine informatii despre
 * tipul vehiculului condus, gabaritul si costul acestuia
 */
public abstract class Vehicul{
	private String type;
	private int gabarit;
	private int cost;
	
	/** 
	 * Constructor pentru un obiect de tip Vehicul, ce 
	 * seteaza tipul vehiculului, gabaritul si costul acestuia 
	 * @param type tipul vehiculului
	 * @param gabarit gabaritul vehiculului
	 * @param cost costul vehiculului
	 */
	protected Vehicul(String type, int gabarit, int cost){
		this.type = type;
		this.gabarit = gabarit;
		this.cost = cost;
	}
	/**
	 * Getter pentru gabarit 
	 * @return gabarit
	 */
	public int getGabarit(){
		return gabarit;
	}
	/** 
	 * Getter pentru cost
	 * @return cost 
	 */
	public int getCost(){
		return cost;
	}
	/** 
	 * Getter pentru tipul vehiculului 
	 * @return type
	 */
	public String getType(){
		return type;
	}
}
