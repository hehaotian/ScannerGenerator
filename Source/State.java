package Source;

import java.util.*;

public class State implements Comparable<State> {
	
	static int stateNumCounter = 0;
	int stateNum;
	boolean isFinal;
	
	private HashMap<Character,State> charEdges = new HashMap<Character,State>(256);
	private ArrayList<State> epsEdges = new ArrayList<State>();
	
	public HashMap<Character, State> getCharEdges() {
		return charEdges;
	}

	public ArrayList<State> getEpsEdges() {
		return epsEdges;
	}
	
	/**
	 * Add epsilon edge from this state to next
	 * @param next
	 */
	public void addepsilonEdge(State next) {
		epsEdges.add(next);
	}
	
	/**
	 * Add single edge between this state and next by given char
	 * @param c
	 * @param next
	 */
	public void addCharEdge(char c, State next) {
		charEdges.put(c, next);
	}
	
	/**
	 * Adds multiple character edges from this state to next for each character in given HashSet
	 * @param chars
	 * @param next
	 */
	public void addSetCharEdges(HashSet<Character> chars, State next) {
		for (Iterator<Character> iterator = chars.iterator(); iterator.hasNext();) {
			addCharEdge(iterator.next(), next);
		}
	}
	public State(int statenum) {
		stateNum = statenum;
	}
	public State() {
		stateNum = stateNumCounter++;
	}
	
	@Override
	public String toString() {
		ArrayList<String> connectedStates = new ArrayList<String>();
		ArrayList<String> connectedStatesEps = new ArrayList<String>();
		for ( State state : charEdges.values() ) connectedStates.add("S"+state.stateNum);
		for ( State state : epsEdges ) connectedStatesEps.add("S"+state.stateNum);
		return "<S"+stateNum+" "+(isFinal ? "FINAL" : "") + (charEdges.isEmpty() ? "" : ", charEdges =" + connectedStates) 
				+ (epsEdges.isEmpty() ? "" : ", epsEdges=" + connectedStatesEps) + ">";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
//		System.out.println(this + " equals "+obj);
		if (obj.getClass() == this.getClass()) return equals((State)obj);
		else return super.equals(obj);
	}
	
	public boolean equals(State other) {
//		System.out.println("Equals sSTATE "+other);
		return other.stateNum == stateNum;
	}
	@Override
	public int compareTo(State other) {
//		System.out.println("Compare State: "+other);
		return other.stateNum - stateNum;
	}
	
	@Override
	public int hashCode() {
		return stateNum;
	}

	public static void resetNumCounter() {
		// TODO Auto-generated method stub
		stateNumCounter = 0;
	}
	
}