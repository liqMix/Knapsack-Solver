//Programming Assignment #2
//CS441
//Nov 14th, 2018
//Brady Young

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Knapsack {

	static Population pop;
	static Population2 pop2;
	static Objects objectList;
	
	private static Scanner reader = new Scanner(System.in);
	
	public static void main(String[] args) {
		int menu_choice;
		do{
			menu_choice = menu();
			switch(menu_choice) {
				case 1:
					createObjects();
					break;
				case 2:
					createPopulation();
					break;
				case 3:
					runSimulation();
					break;
				case 4:
					print();
					break;
				case 5:
					automated();
					break;
				default:
					break;
			}
		}while(menu_choice != 0);
		System.out.println("Exiting program...");
		return;
	}
	
	private static int menu() {
		System.out.println("--Knapsack Optimizer--");
		System.out.println("--Menu--");
		System.out.println("1. Intialize Object List");
		System.out.println("2. Create Population");
		System.out.println("3. Run Simulation");
		System.out.println("4. Print Best Member");
		System.out.println("5. Run automated test");
		System.out.println("0. Exit");
		return reader.nextInt();
	}
	
	//Creates objects based on input
	private static void createObjects() {
		int size;
		int maxWeight;
		
		System.out.println("Please enter the number of objects: ");
		size = reader.nextInt();
		System.out.println("Please enter the maximum weight: ");
		maxWeight = reader.nextInt();
		
		objectList = new Objects(size, maxWeight);
		return;
	}
	
	private static void createPopulation() {
		int popSize;
		double mutationPct;

		System.out.println("Please enter the population size: ");
		popSize = reader.nextInt();
		System.out.println("Please enter the mutation chance (ex 0.08): ");
		mutationPct = reader.nextDouble();
		
		if(objectList == null) {
			objectList = new Objects();
		}
		
		pop = new Population(popSize, mutationPct, objectList);
		pop2 = new Population2(popSize, mutationPct, objectList);
		return;
	}
	
	private static void runSimulation() {
		int iterations;
		System.out.println("Please enter the number of iterations: ");
		iterations = reader.nextInt();
		
		for(int i = 0; i < iterations; ++i) {
			pop.reproduce();
			pop2.reproduce();
		}
		
		return;
	}
	
	private static void print() {
		if(pop == null)
			System.out.println("No population defined.");
		else
			System.out.println("Value Heuristic:\n" + pop.finish() + "\n\nValue Per Weight Heuristic:\n" + pop2.finish());
		return;
	}
	
	private static void automated() {
		System.out.println("Beginning Automated Tests\n");
		System.out.println("Mutation Chance set at 2%\n50 potential objects, max weight of 250");
		Objects testObj = new Objects(50, 250);
		double mut = 0.02;
		
		//Random Solution/Zero Iteration
		System.out.println("No Iteration");
		System.out.println("------------------\n");
		simulateTest(0, mut, testObj);
		
		//10 Iterations
		System.out.println("10,000 Iterations");
		System.out.println("------------------\n");
		simulateTest(10000, mut, testObj);
		
		//100 Iterations
		System.out.println("100,000 Iterations");
		System.out.println("------------------\n");
		simulateTest(100000, mut, testObj);
		
		//1000 Iterations
		System.out.println("1,000,000 Iterations");
		System.out.println("------------------\n");
		simulateTest(1000000, mut, testObj);
		
		//10000 Iterations
		System.out.println("10,000,000 Iterations");
		System.out.println("------------------\n");
		simulateTest(10000000, mut, testObj);
		
		System.out.println("\nTest complete.");
		return;
	}
	
	
	private static void simulateTest(int iterations, double mut, Objects testObj) {
		Instant start;
		Instant end;
		
		
		//100 members
		System.out.println("Population Size: 100\n");
		System.out.println("Value Heuristic:");
		start = Instant.now();
		pop = new Population(100, mut, testObj);
		test(pop, iterations);
		end = Instant.now();
		System.out.println(Duration.between(start, end) + "\n");
		
		System.out.println("Value Per Weight Heuristic:");
		start = Instant.now();
		pop2 = new Population2(100, mut, testObj);
		test(pop2, iterations);
		end = Instant.now();
		System.out.println(Duration.between(start, end) + "\n");
		
		//1,000 members
		System.out.println("Population Size: 1,000\n");
		System.out.println("Value Heuristic:");
		start = Instant.now();
		pop = new Population(1000, mut, testObj);
		test(pop, iterations);
		end = Instant.now();
		System.out.println(Duration.between(start, end) + "\n");
		
		System.out.println("Value Per Weight Heuristic:");
		start = Instant.now();
		pop2 = new Population2(1000, mut, testObj);
		test(pop2, iterations);
		end = Instant.now();
		System.out.println(Duration.between(start, end) + "\n");
		
		//10,000 members
		System.out.println("Population Size: 10,000\n");
		System.out.println("Value Heuristic:");
		start = Instant.now();
		pop = new Population(10000, mut, testObj);
		test(pop, iterations);
		end = Instant.now();
		System.out.println(Duration.between(start, end) + "\n");
		
		System.out.println("Value Per Weight Heuristic:");
		start = Instant.now();
		pop2 = new Population2(10000, mut, testObj);
		test(pop2, iterations);
		end = Instant.now();
		System.out.println(Duration.between(start, end) + "\n");
		
		//100,000 members
		System.out.println("Population Size: 100,000\n");
		System.out.println("Value Heuristic:");
		start = Instant.now();
		pop = new Population(100000, mut, testObj);
		test(pop, iterations);
		end = Instant.now();
		System.out.println(Duration.between(start, end) + "\n");
		
		System.out.println("Value Per Weight Heuristic:");
		start = Instant.now();
		pop2 = new Population2(100000, mut, testObj);
		test(pop2, iterations);
		end = Instant.now();
		System.out.println(Duration.between(start, end) + "\n");
		
		//1,000,000 members
		System.out.println("Population Size: 1,000,000");
		System.out.println("Value Heuristic:");
		start = Instant.now();
		pop = new Population(1000000, mut, testObj);
		test(pop, iterations);
		end = Instant.now();
		System.out.println(Duration.between(start, end) + "\n");
		
		System.out.println("Value Per Weight Heuristic:");
		start = Instant.now();
		pop2 = new Population2(1000000, mut, testObj);
		test(pop2, iterations);
		end = Instant.now();
		System.out.println(Duration.between(start, end) + "\n");

		return;
	}
	
	private static void test(Population pop, int iterations) {
		int i;
		
		for(i = 0; i < iterations; ++i)
			pop.reproduce();
		System.out.println(pop.finish());

		return;
	}
}
