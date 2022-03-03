/**
 * Name: Quan Tran
 * ID: A16191839
 * Email: qutran@ucsd.edu
 * Sources used: Lecture Slides, Zybooks 
 * 
 * 
 * This file contain the foundation of the game by implementing many 
 * methods that provide the game functions. It also defined many static 
 * variables to use for the many of the methods. 
 */

import java.util.Random;

/**
 * This class defined static variables for the game. It also validate if move
 * choice made by the user is valid or not. The class also implement the game 
 * using determineWinner to keep tracks of all the move, win, lost, and ties. 
 * It also implement the end of the game. 
 */
public abstract class RPSAbstract implements RPSInterface {
    public static final int SEED = 12;
    Random rand = new Random(SEED);
    
    // The moves allowed in this version of RPS
    public String [] possibleMoves;
    
    // The number of games, player wins, CPU wins and ties
    public int numGames;
    public int numPlayerWins;
    public int numCPUWins;
    public int numTies;
    
    // The complete history of the moves
    String[] playerMoves;
    String[] cpuMoves;

    // The default moves.  Use for the basic implementation of the game.
    public static final String[] DEFAULT_MOVES = {"scissors", "paper", "rock"};

    // Messages for the game.  USE THESE!
    public static final String INVALID_INPUT =
    "That is not a valid move. Please try again.";
    public static final String PLAYER_WIN = "You win.";
    public static final String CPU_WIN = "I win.";
    public static final String TIE = "It's a tie.";
    public static final String CPU_MOVE = "I chose %s. ";
    public static final String THANKS = 
        "Thanks for playing!\nOur most recent games were: ";
    public static final String PROMPT_MOVE = 
        "Let's play! What's your move? (Type the move or q to quit)";
    
    public static final String OVERALL_STATS =
        "Our overall stats are:\n" + 
        "I won: %.2f%%\nYou won: %.2f%%\nWe tied: %.2f%%\n";
    public static final String CPU_PLAYER_MOVES = "Me: %s, You: %s\n";

    // Game outcome constants.  USE THESE!
    public static final int CPU_WIN_OUTCOME = 2;
    public static final int PLAYER_WIN_OUTCOME = 1;
    public static final int TIE_OUTCOME = 0;
    public static final int INVALID_INPUT_OUTCOME = -1;

    // Other game control constants.  Use as appropriate.
    public static final int MAX_GAMES = 100;
    public static final int MIN_POSSIBLE_MOVES = 3;
    public static final int NUM_ROUNDS_TO_DISPLAY = 10;
    public static final int PERCENTAGE_RESIZE = 100;
    public static final String QUIT = "q";

     /**
     * Determine if the move is valid
     * @param move The move
     * @return true if the move is valid, false otherwise
     */
    public boolean isValidMove(String move) {
        if (move == null){
            return false;
        }
        for (int i = 0; i < possibleMoves.length; i++) {
            if (move.equals(possibleMoves[i])){
                return true;
            }
        }
        return false;  
    }
    
     /**
     * Play one game of RPS.
     * Also adds appropriately to the number of games, wins and ties played.
     * and records the most recent moves.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     */
    public void play(String playerMove, String cpuMove) {

       // Use determineWinner to determine who won
       // Record the moves made
       // Add one to the appropriate statistics

       if (determineWinner(playerMove, cpuMove) == CPU_WIN_OUTCOME) {
           System.out.printf(CPU_MOVE + CPU_WIN, cpuMove);
           System.out.println();
           playerMoves[numGames] = playerMove;
           cpuMoves[numGames] = cpuMove;
           this.numCPUWins += 1;
           this.numGames += 1;
        }
        
        else if (determineWinner(playerMove, cpuMove) == PLAYER_WIN_OUTCOME) {
            System.out.printf(CPU_MOVE + PLAYER_WIN, cpuMove);
            System.out.println();
            playerMoves[numGames] = playerMove;
            cpuMoves[numGames] = cpuMove;
            this.numPlayerWins += 1;
            this.numGames += 1;
        }

        else if (determineWinner(playerMove, cpuMove) == TIE_OUTCOME) {
            System.out.printf(CPU_MOVE + TIE, cpuMove);
            System.out.println();
            playerMoves[numGames] = playerMove;
            cpuMoves[numGames] = cpuMove;
            this.numTies += 1;
            this.numGames += 1;
        }

        else {
            System.out.println(INVALID_INPUT);
        }
    }  

    // NOTE: You do not need to modify the methods below

    /**
     * Generates next cpu move
     * @return String representing the move, depending on random int
     */
    public String genCPUMove() {
        // Generate new random number
        int num = rand.nextInt(possibleMoves.length);
        // Get move based on random number
        return possibleMoves[num];
    }

    /**
     * Print out the end game stats: moves played and win percentages
     */
    public void end() {
        float cpuWinPercent = (float)numCPUWins/
            (float)numGames * PERCENTAGE_RESIZE;
        float playerWinPercent = (float)numPlayerWins/
            (float)numGames * PERCENTAGE_RESIZE;
        float tiedPercent = (float)numTies/
            (float)numGames * PERCENTAGE_RESIZE;

        System.out.println(THANKS);

        // Get which index to print to
        int printTo = (numGames < NUM_ROUNDS_TO_DISPLAY) 
        ? 0 : numGames - NUM_ROUNDS_TO_DISPLAY;
            
        // Print system and user moves
        for (int i = numGames - 1 ; i >= printTo; i--) {
            System.out.printf(CPU_PLAYER_MOVES, this.cpuMoves[i],
                this.playerMoves[i]);   
        }

        System.out.printf(OVERALL_STATS, cpuWinPercent, playerWinPercent,
            tiedPercent);
    }
}
