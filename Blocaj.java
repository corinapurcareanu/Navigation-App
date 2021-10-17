/** 
 * Clasa ce mosteneste clasa Ambuteiaj si reprezinta
 * un tip particular de ambuteiaj
 */
public class Blocaj extends Ambuteiaj {
	Blocaj urm;
	/** 
	 * Constructor pentru un obiect de tip Blocaj
	 * ce seteaza punctul de pornire, punctul destinatie,
	 * costul suplimentar pe acea strada si seteaza
	 * campul urm ca fiind null;
	 * @param start punctul de pornire
	 * @param end punctul destinatie
	 * @param cost costul suplimentar adaugat pe strada
	 */
	Blocaj(int start, int end, int cost){
		super(start, end, cost);
		this.urm = null;
	}
}
