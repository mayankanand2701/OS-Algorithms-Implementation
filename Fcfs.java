package SchedulingAlgorithms;
import java.util.Scanner;
public class Fcfs {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of Process = ");
		int np=sc.nextInt();
		int pid[]=new int[np];
		int at[]=new int[np];
		int bt[]=new int[np];
		System.out.println("Enter the Process id = ");
		for(int i=0;i<np;i++)pid[i]=sc.nextInt();
		System.out.println("Enter the Arrival Time and Burst Time = ");
		for(int i=0;i<np;i++) 
		{
			System.out.println("For Process id "+pid[i]+"= ");
			at[i]=sc.nextInt();
			bt[i]=sc.nextInt();
		}
		sort(pid,at,bt,np);
		int gc[]=new int[np+1];
		gc[0]=at[0];
		for(int i=1;i<np+1;i++)gc[i]=gc[i-1]+bt[i-1];
		System.out.println("--------------------------------------------");
		System.out.println("Gantt Chart :  ");
		for(int i=0;i<np+1;i++) System.out.print(gc[i]+" ");
		System.out.println();
		System.out.println("--------------------------------------------");
		// to calculate completion time
		int ct[]=new int[np];
		for(int i=0;i<np;i++)ct[i]=gc[i+1];
		// to calculate turn around time
		int tatsum=0;
		int tat[]=new int[np];
		for(int i=0;i<np;i++)
		{
			tat[i]=ct[i]-at[i];
			tatsum=tatsum+tat[i];
		}
		// to calculate waiting time
		int wtsum=0;
		int wt[]=new int[np];
		for(int i=0;i<np;i++)
		{
			wt[i]=tat[i]-bt[i];
			wtsum=wtsum+wt[i];
		}
		System.out.println("Average Turn Around Time(TAT) = "+(tatsum/(np*1.0)));
		System.out.println("Average Waiting Time(WT) = "+(wtsum/(np*1.0)));
		System.out.println("Average Response Time (RT) = "+(wtsum/(np*1.0)));
	    System.out.println("--------------------------------------------");

	}
	static void sort(int pid[],int at[],int bt[],int np)
	{
	    for(int i=0; i < np; i++)
	    {  
            for(int j=1; j < (np-i); j++)
            {       
            	if(at[j-1]>at[j])
            	{  
                    int temp1 = at[j-1];  
                    at[j-1] = at[j];  
                    at[j] = temp1;  
                    // Arranging the Process Id according to AT
                    int temp2 = pid[j-1];  
                    pid[j-1] = pid[j];  
                    pid[j] = temp2;
                    // Arranging the Burst Time according to BT
                    int temp3 = bt[j-1];  
                    bt[j-1] = bt[j];  
                    bt[j] = temp3;
                }         
            }  
	    }
	    System.out.println("--------------------------------------------");
	    System.out.println("Process ID     Arrival Time     Burst Time");
	    for(int i=0;i<np;i++) System.out.println(pid[i]+"		"+at[i]+"		"+bt[i]);
	}
}
