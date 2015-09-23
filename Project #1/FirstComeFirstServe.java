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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class FirstComeFirstServe {
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
	
	//First Come First Serve
	public void fcfs(){
		double averageTurnAround = 0.0;
		int total = 0;
		int size = queue.size();
		
		while(!queue.isEmpty()){
			Job job = queue.remove();
			String name = job.getName();
			int job_time = job.getBurst_time();
			int time = 0;
			
			System.out.println("The job name is: " + name + " and its burst time is: " + job_time);
			
			while(time < job_time){
				time++;
				total++;
			}
			
			System.out.println(name + " finishes with: " + (job_time - time) + " ms");
			System.out.println("The total is: " + total + "\n");
			averageTurnAround = averageTurnAround + total;
			gant.add(new Job(name, total));
		}
		
		averageTurnAround = averageTurnAround / size;
		System.out.println("The average completion for First Come First Serve is: " + averageTurnAround);
		printGant(gant);
	}
	
	
	//Prints the elements for the Gant Chart
		public void printGant(List<Job> gant){
			for(int i = 0; i < gant.size(); i++){
				System.out.print("---------");
			}
			System.out.println();
			for(Job j : gant){
				System.out.print("| " + j.getName() + " ");
			}
			System.out.print("|\n");
			for(int i = 0; i < gant.size(); i++){
				System.out.print("---------");
			}
			System.out.print("\n" + "0");
			for(Job j : gant){
				System.out.printf("   %6s", j.getBurst_time());
				//System.out.print("       " + j.getBurst_time());
			}
		}
}
