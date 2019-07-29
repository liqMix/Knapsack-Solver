import java.util.PriorityQueue;
import java.util.Random;

//Uses divGenomes
public class Population2 extends Population {
	
	public Population2(int popSize, double mut, Objects objectList) {
		rand = new Random();
		theObjects = objectList;
		populationSize = popSize;
		pop = new divGenome[popSize];
		weakestMembers = new PriorityQueue<Genome>(populationSize, new FitnessComparator());
		mutationPct = mut;
		randomizeGenomes();
	}
	
	protected void randomizeGenomes() {
		for(int i = 0; i < populationSize; ++i) {
			pop[i] = new divGenome(theObjects.num);
			pop[i].randomize(theObjects);
			weakestMembers.add(pop[i]);
		}
	}
	
}
