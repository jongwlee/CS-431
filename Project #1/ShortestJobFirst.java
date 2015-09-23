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
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class ShortestJobFirst {
	int MAX_SIZE = 30;
	Queue<Job> queue = new PriorityQueue<>(MAX_SIZE, new Job.compare());
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
		
		//Shortest Job First
		public void sjf(){
			int size = queue.size();
			double averageTurnAround = 0.0;
			int total_time = 0;
			
			while(!queue.isEmpty()){
				Job job = queue.remove();
				String name = job.getName();
				int remaining_job_time = job.getBurst_time();
				int job_time = 0;
				
				System.out.println("The job name is: " + name + " and its burst time is: " + remaining_job_time);
				
				while(job_time < remaining_job_time){
					job_time++;
					total_time++;
				}
				gant.add(new Job(name, total_time));
				System.out.println(name + " finishes with: " + (remaining_job_time - job_time) + " ms");
				System.out.println("The total is: " + total_time + "\n");
				averageTurnAround = averageTurnAround + total_time;
		}
			averageTurnAround = averageTurnAround / size;
			System.out.println("The average completion for Shortest Job First is: " + averageTurnAround);
			
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
