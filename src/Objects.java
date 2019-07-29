import java.util.Random;

//Holds the objects that can be in the knapsack
public class Objects {
	public int num;
	private int[][] objectList;
	private int maxWeight;
	private Random rand;
	
	public Objects() {
		//Use object list as defined in assignment description
		num = 6;
		maxWeight = 7;
		/*objectList = new int[4][3];
		objectList[0] = new int[]{45, 3, 3/45};
		objectList[1] = new int[]{40, 5, 5/40};
		objectList[2] = new int[]{50, 8, 8/50};
		objectList[3] = new int[]{90, 10,10/90};*/
		objectList = new int[num][];
		objectList[0] = new int[]{1, 10, 1/10};
		objectList[1] = new int[]{4, 28, 4/28};
		objectList[2] = new int[]{3, 24, 3/24};
		objectList[3] = new int[]{3, 32, 3/32};
		objectList[4] = new int[]{2, 24, 2/24};
		objectList[5] = new int[]{7, 38, 7/38};
	}
	
	//Creates a randomized object list based on a defined number of objects and maximum weight allowed
	public Objects(int num, int maxWeight) {
		this.num = num;
		this.maxWeight = maxWeight;
		int weight, value;	
		rand = new Random();
		
		objectList = new int[num][3];
		for(int i = 0; i < num; ++i)
			objectList[i] = new int[] {weight = (rand.nextInt(maxWeight/4)+1), value = (rand.nextInt(100)+1), value/weight};
	}
	
	public int getValue(boolean[] list) {
		int total_v = 0;
		int total_w = 0;
		
		for(int i = 0; i < num; ++i) {
			if(list[i]) {
				total_w += objectList[i][0];
				total_v += objectList[i][1];
			}
		}
		
		if(total_w > maxWeight)
			return 0;
		
		return total_v;
		
	}
	
	public int getWeight(boolean[] list) {
		int total = 0;
		
		for(int i = 0; i < num; ++i) {
			if(list[i])
				total += objectList[i][0];
		}
		
		return total;
	}
	
	public int getValuePer(boolean[] list) {
		int total = 0;
		int total_w = 0;
		
		for(int i = 0; i < num; ++i) {
			if(list[i]) {
				total_w += objectList[i][0];
				total += objectList[i][2];
			}
		}
		
		if(total_w > maxWeight)
			return 0;
		return total;
	}
}
