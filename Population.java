import java.util.PriorityQueue;
import java.util.Random;

//Holds an array of genomes
public class Population {
	protected Genome[] pop;
	protected PriorityQueue<Genome> weakestMembers;
	protected Random rand;
	public int populationSize;
	protected double mutationPct;
	protected Objects theObjects;
	
	public Population() {
		this(100, 0.05, new Objects());
	}
	
	public Population(int popSize, double mut, Objects objectList) {
		rand = new Random();
		theObjects = objectList;
		populationSize = popSize;
		pop = new Genome[popSize];
		weakestMembers = new PriorityQueue<Genome>(populationSize, new FitnessComparator());
		mutationPct = mut;
		randomizeGenomes();
	}
	
	protected void randomizeGenomes() {
		for(int i = 0; i < populationSize; ++i) {
			pop[i] = new Genome(theObjects.num);
			pop[i].randomize(theObjects);
			weakestMembers.add(pop[i]);
		}
	}
	
	//Returns the top of the priority queue, which is the genome with
	//the lowest fitness
	private Genome youAreTheWeakestLink() {
		//goodbye
		return weakestMembers.poll();
	}
	
	//Produces two children from random parents
	public void reproduce() {
		Genome parentOne, parentTwo, childOne, childTwo;
		int first = rand.nextInt(populationSize);
		int second = 0;
		int splice = 0;
		
		do {
			second = rand.nextInt(populationSize);
		}while(second == first);
		
		parentOne = pop[first];
		parentTwo = pop[second];
		
		//Children are made from the bodies of the weak
		childOne = youAreTheWeakestLink();
		childTwo = youAreTheWeakestLink();
		
		//Splice & Crossover
		splice = rand.nextInt(parentOne.length-1) + 1;
		for(int i = 0; i < splice; ++i) {
			childOne.objectList[i] = parentOne.objectList[i];
			childTwo.objectList[i] = parentTwo.objectList[i];
		}
		
		for(int i = splice; i < parentOne.length; ++i) {
			childOne.objectList[i] = parentTwo.objectList[i];
			childTwo.objectList[i] = parentOne.objectList[i];
		}
		
		
		//Mutate
		if((1 / (rand.nextInt(100)+1)) < mutationPct)
			childOne.mutate();
		if((1 / (rand.nextInt(100)+1)) < mutationPct)
			childTwo.mutate();
		
		//Calculate their resulting fitness and add the children back into the queue
		childOne.calcFitness(theObjects);
		childTwo.calcFitness(theObjects);
		weakestMembers.add(childOne);
		weakestMembers.add(childTwo);
	}
	
	
	public String finish() {
		Genome temp = null;
		Genome best = null;
		PriorityQueue<Genome> tempQueue = new PriorityQueue<Genome>(weakestMembers);
		
		do {
			best = temp;
			temp = tempQueue.poll();
		}while(temp != null);
		
		if(best == null)
			return "Empty Population";
		
		return best.output(theObjects) + "Weight: " + theObjects.getWeight(best.objectList);
	}
}
