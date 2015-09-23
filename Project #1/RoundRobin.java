/*Jong Woo Lee
 *CS 431 Operating Systems Project #1 Scheduling Algorithms
 *Due Date: 8/24/15 
 *Purpose of this project is to write a java program that simulates an operating system.
 *The program implements the following CPU scheduling algorithms:
 *1) First-Come_First-Serve (FCFS)
 *2) Shortest-Job-First (SJF)
 *3) Round-Robin with time slice = 2 (RR-2)
 *4) Round-Robin with time slice = 3 (RR-3)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class RoundRobin {
	Queue<Job> queue = new LinkedList<>();
	List<Job> gant = new ArrayList<>();
	
	//Read the contents of the file
	public void readFile(String readfile){
		String name, burst_time;
		try {
			BufferedReader br = new BufferedReader(new FileReader(readfile));
			while(((name = br.readLine()) != null) && ((burst_time = br.readLine()) != null)){
				queue.add(new Job(name, Integer.parseInt(burst_time)));
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//Run the Round Robin Scheduling algorithm and quantum time specified by the user
	public void run(int quantum_time){
		final int q_time = quantum_time;
		int size = queue.size();
		int total = 0;
		double averageTurnAround = 0.0;
		
		while(!queue.isEmpty()){
			Job job = queue.remove();
			String name = job.getName();
			int job_time = job.getBurst_time();
			int time = 0;
			int counter = 0;
					
			System.out.println("The job name is: " + name + " and its burst time is: " + job_time);
			
			while(time < job_time && counter < q_time){
				time++;
				counter++;
				total++;
			}
			if(time == job_time){
				System.out.println(name + " finishes with: " + (job_time - time) + " ms");
				averageTurnAround = averageTurnAround + total;
			}
			else{
				System.out.println("The value still has some running time left and will be placed back into the queue "
						           + "with a burst time of: " + (job_time - time) + " ms");
				job.setBurst_time(job_time - time);
				queue.add(job);
			}
			System.out.println("The total is: " + total + "\n");
			gant.add(new Job(name, time));
		}
		averageTurnAround = averageTurnAround / size;
		System.out.println("The average completion for Round Robin" + quantum_time + " is: " + averageTurnAround);
		
		printGant(gant);
	}
	
	//Prints the elements for the Gant Chart
	public void printGant(List<Job> gant){
		for(int i = 0; i < gant.size(); i++){
			System.out.print("----------");
		}
		System.out.println();
		for(Job j : gant){
			System.out.print("| " + j.getName() + " ");
		}
		System.out.print("|\n");
		for(int i = 0; i < gant.size(); i++){
			System.out.print("----------");
		}
		System.out.print("\n" + "0");
		for(Job j : gant){
			System.out.printf("   %6s", j.getBurst_time());
			//System.out.print("       " + j.getBurst_time());
		}
	}
}
