package _01_Spies_On_A_Train;

import java.util.ArrayList;
import java.util.HashMap;

import _00_Intro_to_Linked_Lists.LinkedList;
import _00_Intro_to_Linked_Lists.Node;

public class SpiesOnATrain {

	/*
	 * A spy has made off with important intel from your intelligence agency! You
	 * know the spy is somewhere on this train(LinkedList). Your job is to find the
	 * suspect that matches the description given to you by the list of clues(the
	 * array).
	 * 
	 * Walk through the train, questioning each of the passengers about what they
	 * have seen and return the name of the most likely suspect.
	 * 
	 * The results are randomly generated each time so you should have a general
	 * case solution that carefully compares the clues to each passenger's
	 * testimony. Remember to use String methods to break up the passengers'
	 * statements.
	 */
	String findIntel(LinkedList<TrainCar> train, String[] clues) {
		train.print();
		print(" ");
		for (int i = 0; i < clues.length; i++) {
			print(clues[i]);
		}
		print(" ");

		String[] allResponses = new String[train.size()];
		Node<TrainCar> car = train.getHead();
		for (int i = 0; i < train.size(); i++) {
			allResponses[i] = car.getValue().questionPassenger();
			car = car.getNext();
		}
		for (int i = 0; i < allResponses.length; i++) {
			int sawIndex = allResponses[i].indexOf("I saw");
			StringBuilder build = new StringBuilder();
			build.append(allResponses[i]);
			build.replace(0, sawIndex + 6, "");
			allResponses[i] = build.toString();

		}

		for (int i = 0; i < allResponses.length; i++) {
			String[] words = allResponses[i].split(" ");
			String suspect = "joe";
			suspect = words[0];
			StringBuilder build = new StringBuilder();
			build.append(allResponses[i]);
			int susIndex = allResponses[i].indexOf(suspect);
			build.delete(0, susIndex);
			allResponses[i] = build.toString();
			for (int o = 0; o < clues.length; o++) {
				if (allResponses[i].equals(clues[o])) {
					print(suspect);
					return suspect;
					//I think clue string and allResponse string do not match
				}
			}
			// weturnALI
		}
		return "Ali";
	}

	<T> void print(T i) {
		System.out.println(i);
	}

}
