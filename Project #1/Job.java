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

import java.util.Comparator;


public class Job {
  private String name;
  private int burst_time;
  
  public Job(){
  }
  
  public Job(String name, int burst_time){
	  this.name = name;
	  this.burst_time = burst_time;
  }
  
  public int getBurst_time() {
	  return burst_time;
  }

  public void setBurst_time(int burst_time) {
	  this.burst_time = burst_time;
  }

  public String getName() {
	  return name;
  }
  
  public static class compare implements Comparator<Job>{
	@Override
	public int compare(Job job_one, Job job_two) {
		// TODO Auto-generated method stub
		return job_one.getBurst_time() - job_two.getBurst_time();
	}
	  
  }
}
