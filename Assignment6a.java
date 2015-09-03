/*
 * @author :  Vaishakhi Kulkarni
 * Net Id: vpk140230
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

import Graph;

public class Assignment6a {

	// Override the compare function in priority queue to compare on
	// Graph.vertex
	static class VertexPriority implements Comparator<Graph.Vertex> {
		@Override
		public int compare(Graph.Vertex one, Graph.Vertex two) {
			return one.degree - two.degree;
		}
	}

	// Topological Sort having LinkedList as output
	public static LinkedList<Graph.Vertex> TopologicalOrder1(Graph g) {

		// LinkedList which have final output
		LinkedList<Graph.Vertex> topologicalOrder = new LinkedList<Graph.Vertex>();

		// Initialize the vertex with degree value
		for (Graph.Vertex u : g) {
			for (Graph.Edge e : u.Adj) {
				Graph.Vertex v = e.otherEnd(u);
				v.degree++;
			}
		}

		VertexPriority pqr = new VertexPriority();
		// Create Priority queue
		PriorityQueue<Graph.Vertex> queue = new PriorityQueue<Graph.Vertex>(7,
				pqr);
		int vertex = 0;

		for (Graph.Vertex u : g) {
			if (u.degree == 0) // all nodes having in degree 0
				queue.offer(u);
			vertex++;
		}

		int count = 0;
		// Execute untill queue is empty
		while (!queue.isEmpty()) {
			Graph.Vertex u = queue.poll();
			count++;
			topologicalOrder.offer(u);

			// If edge is found then the in degree of vertex v is reduced
			for (Graph.Edge e : u.Adj) {
				Graph.Vertex v = e.otherEnd(u);
				v.degree--;
				if (v.degree == 0)
					queue.offer(v);
			}

		}

		// To detect cycle
		if (count != vertex)
			System.out.println("Given graph is not a DAG");

		return topologicalOrder;
	}

	// Topological Sort having stack as output
	public static Stack<Graph.Vertex> TopologicalOrder2(Graph g) {
		// DFS traversal
		Stack<Graph.Vertex> stack = DFS_Top(g);
		// Return stack
		return stack;
	}

	public static Stack<Graph.Vertex> DFS_Top(Graph g) {
		// Initialize the graph
		for (Graph.Vertex u : g) {
			u.seen = false;
			u.active = false;
		}
		// Create Stack s
		Stack<Graph.Vertex> s = new Stack<Graph.Vertex>();
		// Call DFS_Visit
		for (Graph.Vertex u : g) {
			if (!u.seen)
				DFS_Visit(u, s);
		}
		return s;
	}

	public static void DFS_Visit(Graph.Vertex u, Stack<Graph.Vertex> s) {
		// Set the vertex u with some value
		u.seen = true;
		u.active = true;

		for (Graph.Edge e : u.Adj) {
			Graph.Vertex v = e.otherEnd(u);
			if (!v.seen) {
				DFS_Visit(v, s);
			} else if (v.active) { // To detect cycle
				System.out.println("Not a DAG");
			}
		}
		s.push(u);
		u.active = false;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// Take input from Scanner
		Scanner in = new Scanner(new File(args[0]));
		// Read the graph
		Graph g = Graph.readGraph(in);
		// Print the graph
		g.printGraph();
		// To calculate running time complexity
		long startTime = System.currentTimeMillis();
		System.out.println("Topological Order using LinkedList:(Algorithm 1)");
		// Topological Sort having LinkedList output
		LinkedList<Graph.Vertex> topologicalOrder = TopologicalOrder1(g);
		System.out.println(" " + topologicalOrder);
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		// Total Time complexity
		System.out.println("Time is:" + elapsedTime);

		long startTimes = System.currentTimeMillis();
		System.out.println("Topological Order using Stack:(Algorithm 2)");
		// Topological Sort having Stack as output
		Stack<Graph.Vertex> topologicalOrderStack = TopologicalOrder2(g);
		// Convert from stack to array
		Object[] stack = topologicalOrderStack.toArray();
		// Print in reverse order
		for (int i = stack.length - 1; i >= 0; i--)
			System.out.print(" " + stack[i] + " ");
		System.out.println();
		long endTimes = System.currentTimeMillis();
		long elapsedTimes = endTimes - startTimes;
		// Total time taken
		System.out.println("Time is:" + elapsedTimes);
	}

}
