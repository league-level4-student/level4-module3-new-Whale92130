package _02_Rainbow_Zombie_Conga_Line;

import java.util.ArrayList;

import _00_Intro_to_Linked_Lists.LinkedList;
import _00_Intro_to_Linked_Lists.Node;

public class RainbowZombieCongaLine {

	/*
	 * You are hosting a rainbow zombie conga dance party! Zombies are not very
	 * smart(maybe that's why they crave brains) and need your help to direct them
	 * through the choreography.
	 * 
	 * Each method is a dance move you will need to implement.
	 * 
	 * When you think you've gotten your zombies trained well enough you can start
	 * the party by running the main method in RainbowZombieDanceParty and typing
	 * how many rounds you want in the console to see if they followed your
	 * choreography correctly.
	 * 
	 * Note: The party will always start with a rainbow brains and every 5 rounds
	 * the head and tail of the dance line will be removed.
	 */

	private LinkedList<Zombie> congaLine;
	private ZombieHatColor[] zombieHats;

	public RainbowZombieCongaLine() {

		congaLine = new LinkedList<Zombie>();
		zombieHats = ZombieHatColor.values();

	}

	// Make the passed in zombie the first Zombie in the conga line!
	public void engine(Zombie dancer) {
		congaLine.add(dancer);
	}

	// Make the passed in zombie the last Zombie in the conga line!
	public void caboose(Zombie dancer) {
		
		LinkedList<Zombie> tempLine = congaLine;
		for (int i = 0; i < congaLine.size(); i++) {
			congaLine.remove(i);
		}
		congaLine.add(dancer);
		for (int i = 0; i < tempLine.size(); i++) {
			congaLine.add(tempLine.getHead().getValue());
			tempLine.remove(0);
		}
	}

	// Place the zombie at the designated position in the conga line!
	public void jumpInTheLine(Zombie dancer, int position) {
		int posCount = 0;
		LinkedList<Zombie> tempLine = congaLine;
		for (int i = 0; i < congaLine.size(); i++) {
			congaLine.remove(i);
		}
		for (int i = 0; i < tempLine.size(); i++) {
			congaLine.add(tempLine.getHead().getValue());
			tempLine.remove(0);
			if (posCount == position) {
				congaLine.add(dancer);
			}
			posCount++;
		}

	}

	/*
	 * Remove all zombies with the same hat color as the passed in zombie from the
	 * conga line!
	 */
	public void everyoneOut(Zombie dancer) {
		ZombieHatColor color = dancer.getZombieHatColor();
		LinkedList<Zombie> tempLine = congaLine;
		for (int i = 0; i < congaLine.size(); i++) {
			congaLine.remove(0);
		}
		for (int i = 0; i < tempLine.size(); i++) {
			if (color == tempLine.getHead().getValue().getZombieHatColor()) {
				tempLine.remove(0);
			} else {
				congaLine.add(tempLine.getHead().getValue());
				tempLine.remove(0);
			}
		}

	}

	/*
	 * Remove the first zombie with the same hat color as the passed in zombie from
	 * the conga line!
	 */
	public void youAreDone(Zombie dancer) {
		ZombieHatColor color = dancer.getZombieHatColor();
		LinkedList<Zombie> tempLine = congaLine;
		for (int i = 0; i < congaLine.size(); i++) {
			congaLine.remove(0);
		}
		for (int i = 0; i < tempLine.size(); i++) {
			if (color == tempLine.getHead().getValue().getZombieHatColor()) {
				tempLine.remove(0);
				color = null;
			} else {
				congaLine.add(tempLine.getHead().getValue());
				tempLine.remove(0);
			}
		}
	}

	/*
	 * Make two more zombies with the same hat color as the passed in zombie and add
	 * one to the front, one to the end and one in the middle.
	 */
	public void brains(Zombie dancer) {
		LinkedList<Zombie> tempLine = congaLine;
		ZombieHatColor color = dancer.getZombieHatColor();
		Node<Zombie> zoomby = congaLine.getHead();
		int howManyZombies = 0;
		ArrayList<Zombie> chosenZombies = new ArrayList<Zombie>();
		for (int i = 0; i < congaLine.size(); i++) {
			if (zoomby.getValue().getZombieHatColor().equals(color)) {
				howManyZombies++;
				chosenZombies.add(zoomby.getValue());
			}
			zoomby = zoomby.getNext();
		}
		zoomby = congaLine.getHead();
		for (int i = 0; i < congaLine.size(); i++) {
			congaLine.remove(i);
		}
		for (int i = 0; i < tempLine.size(); i++) {
			if (howManyZombies == 2) {
				if (i == 0) {
					congaLine.add(chosenZombies.get(0));
				}
				if (i == tempLine.size()-1) {
					congaLine.add(chosenZombies.get(1));
				}
			}
			if (howManyZombies == 3) {
				if (i == 0) {
					congaLine.add(chosenZombies.get(0));
				}
				if (i == tempLine.size()-1) {
					congaLine.add(chosenZombies.get(1));
				}
				if (i == (tempLine.size()-1)/2) {
					congaLine.add(chosenZombies.get(2));
				}
			}
			congaLine.add(tempLine.getHead().getValue());
			tempLine.remove(0);
		}

	}

	/*
	 * Add the passed in zombie to the front and then add one zombie of each hat
	 * color to the end of the line.
	 */
	public void rainbowBrains(Zombie dancer) {
		LinkedList<Zombie> tempLine = congaLine;
		for (int i = 0; i < congaLine.size(); i++) {
			congaLine.remove(i);
		}
		congaLine.add(dancer);
		for (int i = 0; i < tempLine.size(); i++) {
			congaLine.add(tempLine.getHead().getValue());
			tempLine.remove(0);
		}
		ZombieHatColor[] hatColors = ZombieHatColor.values();
		for(int i = 0; i < hatColors.length; i++) {
			congaLine.add(new Zombie(hatColors[i]));
		}
	}

	public LinkedList<Zombie> getCongaLine() {
		return congaLine;
	}
}
