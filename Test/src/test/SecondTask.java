package test;

import java.util.PriorityQueue;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/*You are given a list of cities. 
 * Each direct connection between two cities has its transportation cost (an integer bigger than 0). 
 * The goal is to find the paths of minimum cost between pairs of cities. 
 * Assume that the cost of each path (which is the sum of costs of all direct connections belongning to this path) is at most 200000. 
 * The name of a city is a string containing characters a,...,z and is at most 10 characters long.2) 
 * */

//implementing a help class Vertex - node of our network
class Vertex implements Comparable<Vertex> {
	public String name;// name of the city
	public Edge[] adjacencies;// "roads" to other cities
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;// previous node

	public Vertex(String argName) {
		name = argName;
	}

	public Vertex() {
		name = null;
	}

	public String toString() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

}

// help class Edge - road between two cities
class Edge {
	public final Vertex target;// the "finish" city
	public final double weight;// how long this road is

	public Edge(Vertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}

	@Override
	public String toString() {
		return "Edge [target=" + target + ", weight=" + weight + "]";
	}

}

// class for calculating the shortest path using dijkstra algorithm
public class SecondTask {

	// building our path
	public static void computePaths(Vertex source) {
		source.minDistance = 0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);
		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();
			// Visit each edge exiting u
			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}

	public static int findByName(String n, ArrayList<Vertex> arrayVertex) {

		for (int i = 0; i < arrayVertex.size(); i++) {
			if (arrayVertex.get(i).name.equals(n)) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		// parse our file with the task
		String fileName = "D:\\Input.txt";
		int numberOfTests;
		int numberOfCities;
		ArrayList<Vertex> arrayVertex = new ArrayList<Vertex>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			numberOfTests = Integer.parseInt(line);// getting number of tests
			for (int testIndex = 0; testIndex < numberOfTests; testIndex++) {
				line = br.readLine();
				numberOfCities = Integer.parseInt(line);// getting number of
														// cities
				// implementing array of Vertex to build a network
				for (int i = 0; i < numberOfCities; i++) {
					arrayVertex.add(new Vertex());
				}
				// parsing information about "roads"
				for (int i = 0; i < numberOfCities; i++) {
					String cityName = br.readLine();
					arrayVertex.get(i).setName(cityName);
					line = br.readLine();
					int numberOfConnections = Integer.parseInt(line);
					Edge[] ed = new Edge[numberOfConnections];
					for (int j = 0; j < numberOfConnections; j++) {
						line = br.readLine();
						String[] con = line.split(" ");
						int ind = Integer.parseInt(con[0]);
						int cost = Integer.parseInt(con[1]);
						Edge edge = new Edge(arrayVertex.get(ind - 1), cost);
						ed[j] = edge;
					}
					arrayVertex.get(i).adjacencies = ed;
				}
				// parsing information about cities - between them we want to
				// find the shortest path
				line = br.readLine();
				int index = Integer.parseInt(line);
				for (int i = 0; i < index; i++) {
					line = br.readLine();
					String[] cities = line.split(" ");
					int foundIndexStart = findByName(cities[0], arrayVertex);
					int foundIndexFinish = findByName(cities[1], arrayVertex);
					if ((foundIndexStart != -1) && (foundIndexFinish != -1)) {
						Vertex start = arrayVertex.get(foundIndexStart);
						Vertex finish = arrayVertex.get(foundIndexFinish);
						// calculating the path
						computePaths(start);
						System.out.println("Distance from " + start + " to " + finish + ": "
								+ (int) arrayVertex.get(3).minDistance);
					} else {
						System.out.println("No such city");
					}
				}
				br.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
