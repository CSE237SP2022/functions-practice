package functions;

import support.cse131.ArgsProcessor;

public class Nim {
	
	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int numberOfSticks = -1;
		while(numberOfSticks < 0) {
			numberOfSticks = ap.nextInt("How many sticks would you like to start with?");
		}
		boolean humansTurn = true;
		int round = 0;
		
		while(numberOfSticks > 0) {
			int sticksTaken = -1;
			System.out.print("Round " + round + ": " + numberOfSticks + " at start ");
			if(humansTurn) {
				while (sticksTaken < 0 || sticksTaken > numberOfSticks || sticksTaken > 2) {
					sticksTaken = ap.nextInt("How many sticks would you like to take?");
				}
				System.out.print("human took " + sticksTaken);

			} else {
				while (sticksTaken < 0 || sticksTaken > numberOfSticks) {
					sticksTaken = (int)(Math.random() * 2 + 1);
				}
				System.out.print("computer took " + sticksTaken);
			}
			System.out.println(", so " + (numberOfSticks - sticksTaken) + " sticks remain.");
			humansTurn = !humansTurn;
			numberOfSticks -= sticksTaken;
			round++;
		}
		if(humansTurn) {
			System.out.println("The computer wins!");
		} else {
			System.out.println("You win!");

		}
	}
}
