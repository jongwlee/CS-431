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

public class Main {
	public static void main(String args[]){
		FirstComeFirstServe fcfs = new FirstComeFirstServe();
		ShortestJobFirst sjf = new ShortestJobFirst();
		RoundRobin rr = new RoundRobin();
		if(args.length < 1){
			System.out.println("There needs to be at least one argument");
		}
		
		System.out.println("Shortest Job First\n");
		sjf.readFile(args[0]);
		sjf.sjf();
		
		System.out.println("\n\nFirst Come First Serve\n");
		fcfs.readFile(args[0]);
		fcfs.fcfs();
		
		System.out.println("\n\nRound Robin with Time Quantum = 2\n");
		rr.readFile(args[0]);
		rr.run(2);
		
		System.out.println("\n\nRound Robin with Time Quantum = 3\n");
		rr.readFile(args[0]);
		rr.run(3);
	}
}
