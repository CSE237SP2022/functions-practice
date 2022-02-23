package functions;

import support.cse131.ArgsProcessor;

public class Nim {
	
	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int numberOfSticks = getPositiveInt(ap);
		boolean humansTurn = true;
		int round = 0;
		
		while(numberOfSticks > 0) {
			int sticksTaken = -1;
			if(humansTurn) {
				sticksTaken = getHumanMove(ap, numberOfSticks, sticksTaken);
			} else {
				sticksTaken = getComputerMove(numberOfSticks, sticksTaken);
			}
			printRound(round, numberOfSticks, sticksTaken, humansTurn);
			
			humansTurn = !humansTurn;
			numberOfSticks -= sticksTaken;
			round++;
		}
		printWinner(humansTurn);
	}

	private static void printWinner(boolean humansTurn) {
		if(humansTurn) {
			System.out.println("The computer wins!");
		} else {
			System.out.println("You win!");
		}
	}
	
	private static void printRound(int round, int numberOfSticks, int sticksTaken, boolean humansTurn) {
		System.out.print("Round " + round + ": " + numberOfSticks + " at start ");
		
		if(humansTurn) {
			System.out.print("human took " + sticksTaken);
		} else {
			System.out.print("computer took " + sticksTaken);
		}

		System.out.println(", so " + (numberOfSticks - sticksTaken) + " sticks remain.");

	}

	private static int getComputerMove(int numberOfSticks, int sticksTaken) {
		while (isInvalidMove(numberOfSticks, sticksTaken)) {
			sticksTaken = (int)(Math.random() * 2 + 1);
		}
		return sticksTaken;
	}

	private static boolean isInvalidMove(int numberOfSticks, int sticksTaken) {
		return sticksTaken < 0 || sticksTaken > numberOfSticks || sticksTaken > 2;
	}

	private static int getHumanMove(ArgsProcessor ap, int numberOfSticks, int sticksTaken) {
		while (isInvalidMove(numberOfSticks, sticksTaken)) {
			sticksTaken = ap.nextInt("How many sticks would you like to take?");
		}
		return sticksTaken;
	}

	private static int getPositiveInt(ArgsProcessor ap) {
		int numberOfSticks = -1;
		while(numberOfSticks < 0) {
			numberOfSticks = ap.nextInt("How many sticks would you like to start with?");
		}
		return numberOfSticks;
	}
}
