import java.util.HashMap;

/*  An item property helps determine the amount of power required to 
 *  enchant or empower any given item, and therefore determine the cost
 *  or amount of work required to make or purchase it.
 */
public class Property {
	
	/*  A Property has two attributes: 
	 * 	    - level of magical complexity
	 *      - mechanics (a map of subproperties), which may include
	 *          - effect
	 *          - activation time
	 *          - description
	 *          - formalized category or name
	 */
	public int complexity;
	public HashMap<String, String> mechanics;
}
