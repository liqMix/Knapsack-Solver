import java.util.Random;

public class Genome {
	public boolean[] objectList;
	public int length;
	public int fitness;
	private Random rand;
	
	public Genome(int i) {
		length = i;
		objectList = new boolean[length];
		fitness = 0;
	}

	public Genome(boolean[] list) {
		length = list.length;
		for(int i = 0; i < length; ++i)
			objectList[i] = list[i];
	}
	
	public void randomize(Objects list) {
		rand = new Random();
		
		for(int i = 0; i < 1000; ++i)
			flip(rand.nextInt(objectList.length));
		
		calcFitness(list);
		return;
	}
	
	public String output(Objects objList) {
		String out = new String();
		out += "Value: " + objList.getValue(this.objectList) + "\n(";
		
		for(int i = 0; i < length-1; ++i) {
			if(objectList[i])
				out+= "1 ";
			else
				out+= "0 ";
		}
		if(objectList[length-1])
			out+= "1)";
		else
			out+= "0)";
		
		return out + "\n";
	}
	
	private void flip(int index) {
		if(objectList[index])
			objectList[index] = false;
		
		else
			objectList[index] = true;
	}
	
	public void calcFitness(Objects list) {
		fitness = list.getValue(objectList);
	}
	
	public void mutate() {
		rand = new Random();
		flip(rand.nextInt(length));
		return;
	}
}
