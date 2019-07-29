//Alternate fitness function using value per weight
public class divGenome extends Genome{	
	public divGenome(int i) {
		super(i);
	}
	
	public void calcFitness(Objects list) {
		fitness = list.getValuePer(objectList);
	}
	
}
