/** 
 * Clasa ce mosteneste clasa Ambuteiaj si reprezinta
 * un tip particular de ambuteiaj
 */
public class Trafic extends Ambuteiaj{
	Trafic urm;
	/** 
	 * Constructor pentru un obiect de tip Trafic
	 * ce seteaza punctul de pornire, punctul destinatie,
	 * costul suplimentar pe acea strada si seteaza
	 * campul urm ca fiind null;
	 * @param start punctul de pornire
	 * @param end punctul destinatie
	 * @param cost costul suplimentar adaugat pe strada
	 */
	Trafic(int start, int end, int cost){
		super(start, end, cost);
		this.urm = null;
	}
}
