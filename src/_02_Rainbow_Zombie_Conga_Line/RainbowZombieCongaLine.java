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
	// not working
	public void engine(Zombie dancer) {
		Node<Zombie> newNode = new Node<>(dancer);

		// Add the dancer to the front of the conga line
		if (congaLine.getHead() == null) {
			congaLine.setHead(newNode);
			congaLine.setTail(newNode);
		} else {
			newNode.setNext(congaLine.getHead());
			congaLine.getHead().setPrev(newNode);
			congaLine.setHead(newNode);
		}
	}

	// Make the passed in zombie the last Zombie in the conga line!
	// working
	public void caboose(Zombie dancer) {
		congaLine.add(dancer);
	}

	// Place the zombie at the designated position in the conga line!
	// working
	public void jumpInTheLine(Zombie dancer, int position) {
		Node<Zombie> newNode = new Node<>(dancer);

		// If the conga line is empty or the position is 0, add to the front
		if (congaLine.getHead() == null || position == 0) {
			newNode.setNext(congaLine.getHead());
			if (congaLine.getHead() != null) {
				congaLine.getHead().setPrev(newNode);
			}
			congaLine.setHead(newNode);
		} else {
			int currentPosition = 0;
			Node<Zombie> current = congaLine.getHead();

			// Traverse to the specified position
			while (currentPosition < position - 1 && current.getNext() != null) {
				current = current.getNext();
				currentPosition++;
			}

			// Insert the new node at the specified position
			newNode.setNext(current.getNext());
			if (current.getNext() != null) {
				current.getNext().setPrev(newNode);
			}
			current.setNext(newNode);
			newNode.setPrev(current);
		}
	}

	/*
	 * Remove all zombies with the same hat color as the passed in zombie from the
	 * conga line!
	 */
	// not sure
	public void everyoneOut(Zombie dancer) {
		ZombieHatColor color = dancer.getZombieHatColor();
		Node<Zombie> current = congaLine.getHead();
		Node<Zombie> prev = null;

		// Iterate through the conga line
		while (current != null) {
			if (current.getValue().getZombieHatColor() == color) {
				// Remove the current node
				if (prev == null) {
					// If the matched node is the head, update the head
					congaLine.setHead(current.getNext());
					if (current.getNext() != null) {
						current.getNext().setPrev(null);
					}
				} else {
					// If the matched node is not the head, update the links
					prev.setNext(current.getNext());
					if (current.getNext() != null) {
						current.getNext().setPrev(prev);
					}
				}

				// Move to the next node
				current = current.getNext();
			} else {
				// Move to the next node
				prev = current;
				current = current.getNext();
			}
		}
	}

	/*
	 * Remove the first zombie with the same hat color as the passed in zombie from
	 * the conga line!
	 */
	// working WWWWWWW :)
	public void youAreDone(Zombie dancer) {
		ZombieHatColor color = dancer.getZombieHatColor();
		Node<Zombie> node = congaLine.getHead();
		for (int i = 0; i < congaLine.size(); i++) {
			if (node.getValue().getZombieHatColor().equals(color)) {
				congaLine.remove(i);
				break;
			} else {
				node = node.getNext();
			}
		}
	}

	/*
	 * Make two more zombies with the same hat color as the passed in zombie and add
	 * one to the front, one to the end and one in the middle.
	 */
	public void brains(Zombie dancer) {
	    ZombieHatColor color = dancer.getZombieHatColor();
	    Node<Zombie> current = congaLine.getHead();
	    Node<Zombie> prev = null;

	    // Count the number of zombies with the same hat color
	    int count = 0;
	    while (current != null) {
	        if (current.getValue().getZombieHatColor() == color) {
	            count++;
	        }
	        current = current.getNext();
	    }

	    // Reset current to the head for traversal
	    current = congaLine.getHead();

	    // Create a list of chosen zombies with the same hat color
	    ArrayList<Zombie> chosenZombies = new ArrayList<>();
	    while (current != null) {
	        if (current.getValue().getZombieHatColor() == color) {
	            chosenZombies.add(current.getValue());
	        }
	        current = current.getNext();
	    }

	    // Remove all zombies with the same hat color from the conga line
	    everyoneOut(dancer);

	    // Add chosen zombies back to the conga line
	    current = congaLine.getHead();
	    prev = null;
	    int index = 0;
	    while (current != null && index < chosenZombies.size()) {
	        if (current.getValue().getZombieHatColor() != color) {
	            // Add a chosen zombie in the middle
	            Zombie chosenZombie = chosenZombies.get(index);
	            Node<Zombie> newNode = new Node<>(chosenZombie);
	            if (prev == null) {
	                // Add to the front
	                newNode.setNext(current);
	                current.setPrev(newNode);
	                congaLine.setHead(newNode);
	            } else {
	                // Add to the middle
	                newNode.setPrev(prev);
	                newNode.setNext(current);
	                current.setPrev(newNode);
	                prev.setNext(newNode);
	            }
	            index++;
	        }

	        prev = current;
	        current = current.getNext();
	    }

	    // Add one to the end
	    while (current != null && current.getNext() != null) {
	        current = current.getNext();
	    }

	    if (current == null) {
	        // If the conga line is empty, set the head and tail to the new zombie
	        congaLine.setHead(new Node<>(new Zombie(color)));
	        congaLine.setTail(congaLine.getHead());
	    } else {
	        // Add to the end
	        Node<Zombie> newNode = new Node<>(new Zombie(color));
	        current.setNext(newNode);
	        newNode.setPrev(current);
	        congaLine.setTail(newNode);
	    }
	}

	/*
	 * Add the passed in zombie to the front and then add one zombie of each hat
	 * color to the end of the line.
	 */
	// works
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
		for (int i = 0; i < hatColors.length; i++) {
			congaLine.add(new Zombie(hatColors[i]));
		}
	}

	public LinkedList<Zombie> getCongaLine() {
		return congaLine;
	}

	<T> void print(T thing) {
		System.out.println(thing);
	}

}
