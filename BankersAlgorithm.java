import java.util.ArrayList;
import java.util.Scanner;
public class BankersAlgorithm {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of Process = ");
		int np=sc.nextInt();
		System.out.println("Enter the number of Resources = ");
		int nr=sc.nextInt();
		int tr[]=new int[nr];
		System.out.println("Enter the Total Existing Resources : ");
		for(int i=0;i<nr;i++)
		{
			System.out.println("For Resource "+(i+1)+" is : ");
			tr[i]=sc.nextInt();
		}
		int allocation[][]=new int[np][nr];
		System.out.println("Enter the Already Allocated Resources for each Process and Each Resource  to Terminate its Execution: ");
		for(int i=0;i<np;i++)
		{
			System.out.println("For Process "+(i+1)+" :-- ");
			for(int j=0;j<nr;j++)
			{
				System.out.println("For Resource "+(j+1)+" = ");
				allocation[i][j]=sc.nextInt();
			}
		}
		int max[][]=new int[np][nr];
		System.out.println("Enter the Maximum Resources Needed for Each Process and Each Resource : ");
		for(int i=0;i<np;i++)
		{
			System.out.println("For Process "+(i+1)+" :-- ");
			for(int j=0;j<nr;j++)
			{
				System.out.println("For Resource "+(j+1)+" = ");
				max[i][j]=sc.nextInt();
			}
		}
		// to calcualate the sum of all allocated resources till know
		int tallot[]=new int[nr];
		for(int i=0;i<np;i++)
		{
			for(int j=0;j<nr;j++) tallot[j]=tallot[j]+allocation[i][j];
		}
		int cavail[]=new int[nr];
		for(int i=0;i<nr;i++)cavail[i]=tr[i]-tallot[i];
		
		// to calculate the remaining need of allocation
		int ralloc[][]=new int[np][nr];
		for(int i=0;i<np;i++)
		{
			for(int j=0;j<nr;j++) ralloc[i][j]=max[i][j]-allocation[i][j];
		}
		// Objective a
		System.out.println("--------------------------------------");
		System.out.println("Need Matrix :- ");
		for(int i=0;i<nr;i++)System.out.print(" R"+(i+1)+" ");
		System.out.println();
		for(int i=0;i<np;i++)
		{
			for(int j=0;j<nr;j++) System.out.print("  "+ralloc[i][j]+" ");
			System.out.println();
		}
		
		// Objective b
		System.out.println("--------------------------------------");
		// in the condition is meet we need to free its resources and add to cavail and erase that 
		 boolean[] completed = new boolean[np];
	        ArrayList<Integer> safeSequence = new ArrayList<>();
	        int count = 0;

	        while (count < np) {
	            boolean found = false;

	            for (int i = 0; i < np; i++) {
	                if (!completed[i]) {
	                    int j;
	                    for (j = 0; j < nr; j++) {
	                        if (ralloc[i][j] > cavail[j]) {
	                            break;
	                        }
	                    }

	                    if (j == nr) {
	                        for (int k = 0; k < nr; k++) {
	                            cavail[k] += allocation[i][k];
	                        }
	                        safeSequence.add(i + 1);
	                        completed[i] = true;
	                        found = true;
	                        count++;
	                    }
	                }
	            }

	            if (!found)  break; // System is in an unsafe state
	        }
	        if (count == np) {
	            System.out.println("Safe : No Deadlock");
	            System.out.print("Safe Sequence: ");
	            for (int i = 0; i < safeSequence.size() - 1; i++) {
	                System.out.print(safeSequence.get(i) + " -> ");
	            }
	            System.out.print(safeSequence.get(safeSequence.size() - 1));
	        } else {
	            System.out.println("Unsafe : Deadlock Present");
	        }
	        System.out.println();
	        System.out.println("--------------------------------------");
	        // Objective c
	        // Previously :
	        // Safe : No Deadlock
	        //  Safe Sequence: 1 -> 4 -> 5 -> 2 -> 3
	        // If i change the location of P3 R2 to 1 then the output is :
	        // Unsafe : Deadlock Present
	  }
}
