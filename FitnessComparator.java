import java.util.Comparator;

public class FitnessComparator implements Comparator<Genome>{
    public int compare(Genome n1, Genome n2) { 
        if (n1.fitness > n2.fitness) 
            return 1; 
        else if (n1.fitness < n2.fitness) 
            return -1; 
                        return 0; 
        } 
}