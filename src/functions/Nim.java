package functions;

import support.cse131.ArgsProcessor;

public class Nim {
	
	
	private int numberOfSticks;
	private boolean humansTurn;
	private int round;
	private ArgsProcessor ap;
	
	public Nim(ArgsProcessor ap) {
		this.ap = ap;
		this.numberOfSticks = -1;
		this.humansTurn = true;
		this.round = 0;
	}
	
	public static void main(String[] args) {	
		ArgsProcessor ap = new ArgsProcessor(args);
		Nim gameOfNim = new Nim(ap);
		gameOfNim.play();
	}
	
	public void play() {
		this.numberOfSticks = getPositiveInt();
		while(numberOfSticks > 0) {
			int sticksTaken = -1;
			if(humansTurn) {
				sticksTaken = getHumanMove();
			} else {
				sticksTaken = getComputerMove();
			}
			printRound(sticksTaken);
			
			endTurn(sticksTaken);
		}
		printWinner();
	}

	private void endTurn(int sticksTaken) {
		humansTurn = !humansTurn;
		numberOfSticks -= sticksTaken;
		round++;
	}

	private void printWinner() {
		if(humansTurn) {
			System.out.println("The computer wins!");
		} else {
			System.out.println("You win!");
		}
	}
	
	private void printRound(int sticksTaken) {
		System.out.print("Round " + round + ": " + numberOfSticks + " at start ");
		
		if(humansTurn) {
			System.out.print("human took " + sticksTaken);
		} else {
			System.out.print("computer took " + sticksTaken);
		}

		System.out.println(", so " + (numberOfSticks - sticksTaken) + " sticks remain.");

	}

	private int getComputerMove() {
		int sticksTaken = -1;
		while (isInvalidMove(sticksTaken)) {
			sticksTaken = (int)(Math.random() * 2 + 1);
		}
		return sticksTaken;
	}

	private boolean isInvalidMove(int sticksTaken) {
		return sticksTaken < 0 || sticksTaken > numberOfSticks || sticksTaken > 2;
	}

	private int getHumanMove() {
		int sticksTaken = -1;
		while (isInvalidMove(sticksTaken)) {
			sticksTaken = ap.nextInt("How many sticks would you like to take?");
		}
		return sticksTaken;
	}

	private int getPositiveInt() {
		int numberOfSticks = -1;
		while(numberOfSticks < 0) {
			numberOfSticks = ap.nextInt("How many sticks would you like to start with?");
		}
		return numberOfSticks;
	}
}
