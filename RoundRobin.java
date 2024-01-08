package SchedulingAlgorithms;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class RoundRobin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of Process = ");
		int np=sc.nextInt();
		int pid[]=new int[np];
		int at[]=new int[np];
		int bt[]=new int[np];
		int remainingTime[] = new int[np];
		System.out.println("Enter the Process id = ");
		for(int i=0;i<np;i++)pid[i]=sc.nextInt();
		System.out.println("Enter the Arrival Time and Burst Time = ");
		for(int i=0;i<np;i++) 
		{
			System.out.println("For Process id "+pid[i]+"= ");
			at[i]=sc.nextInt();
			bt[i]=sc.nextInt();
			remainingTime[i] = bt[i];
		}
		System.out.println("Enter the Time Quantam : ");
		int tq=sc.nextInt();
		roundRobinScheduling(np, pid, at, bt, tq, remainingTime);
	}

    static void roundRobinScheduling(int np, int pid[], int at[], int bt[], int tq, int remainingTime[]) {
        int currentTime = 0;
        Queue<Integer> readyQueue = new ArrayDeque<>();
        int ct[] = new int[np];
        int tat[] = new int[np];
        int wt[] = new int[np];
        int rt[] = new int[np];
        boolean completed[] = new boolean[np];

        while (true) {
            boolean done = true;

            for (int i = 0; i < np; i++) {
                if (at[i] <= currentTime && !completed[i]) {
                    done = false;

                    if (remainingTime[i] > tq) {
                        currentTime += tq;
                        remainingTime[i] -= tq;
                        readyQueue.add(pid[i]);
                    } else {
                        currentTime += remainingTime[i];
                        remainingTime[i] = 0;
                        readyQueue.add(pid[i]);
                        ct[i] = currentTime;
                        tat[i] = ct[i] - at[i];
                        wt[i] = tat[i] - bt[i];
                        rt[i] = wt[i];
                        completed[i] = true;
                    }
                }
            }

            if (done)
                break;

            if (readyQueue.isEmpty())
                currentTime++;
        }
        printGanttChart(np, bt, tq);
        printReadyQueue(readyQueue);
        printAvgTimes(np, tat, wt, rt);
    }

    static void printGanttChart(int np, int bt[], int tq) {
        System.out.println("\nGantt Chart:");
        int currentTime = 0;
        while (true) {
            boolean done = true;

            for (int i = 0; i < np; i++) {
                if (bt[i] > 0) {
                    done = false;

                    if (bt[i] > tq) {
                        System.out.print("| P" + (i + 1) + " | ");
                        currentTime += tq;
                        bt[i] -= tq;
                    } else {
                        System.out.print("| P" + (i + 1) + " | ");
                        currentTime += bt[i];
                        bt[i] = 0;
                    }
                }
            }
            if (done)
                break;
        }
        System.out.println("\n");
    }

    static void printReadyQueue(Queue<Integer> readyQueue) {
        System.out.println("Ready Queue: " + readyQueue);
    }

    static void printAvgTimes(int np, int tat[], int wt[], int rt[]) {
        double tatSum = 0, wtSum = 0, rtSum = 0;

        for (int i = 0; i < np; i++) {
            tatSum += tat[i];
            wtSum += wt[i];
            rtSum += rt[i];
        }

        System.out.println("Average Turnaround Time (TAT): " + (tatSum / np));
        System.out.println("Average Waiting Time (WT): " + (wtSum / np));
        System.out.println("Average Response Time (RT): " + (rtSum / np));
    }
}

