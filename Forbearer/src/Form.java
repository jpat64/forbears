import java.util.HashMap;

/*  The form of an object is a general description of the materials required to make it,
 *  as well as the general shape of the item, including its weight, and what other object
 *  it would most closely resemble. 
 */
public class Form {
	
	/*  An object's form has a number of attributes. First, it has an array of Materials
	 *  from which it was hewn. Then, it has a series of values for its mundane properties,
	 *  which are useful for using the object in-game.
	 */
	public HashMap<Material, Double> composition;
	public String shape;
	public String damage;
	public Damage damage_type;
	public String relevant_skill;
	public int weight; // calculated from Material array and shape
	
}
