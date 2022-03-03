/**
 * Name: Quan Tran
 * ID: A16191839
 * Email: qutran@ucsd.edu
 * Sources used: Zybook, Lecture Slides
 * 
 * This file implement the determineWinner method to take the move choises 
 * from the user and the computer to determined who win for the game. It also 
 * contain main which contain execution code to start the program. 
 */

import java.util.Scanner;

/**
 * This class represent the information and execution code for the entire game.
 * More specifically by comparing input choices and provide winner information 
 * to RPSAbstract. 
 */

public class RPS extends RPSAbstract {
    
    /**
     * Define a list of string for other methods
     * @param possibleMoves - all the possible moves allowed by the game
     * @param playerMoves - deined by game control constants
     * @param cpuMoves - deined by game control constants
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove)
    {
        if (!isValidMove(playerMove) || !isValidMove(cpuMove)){
            System.out.println(INVALID_INPUT);
            return RPSAbstract.INVALID_INPUT_OUTCOME;
        }
        for (int idx = 0; idx < possibleMoves.length; idx++) 
        {
            if (playerMove.equals(possibleMoves[idx])){
                int i = idx + 1;
                if (i > possibleMoves.length - 1){
                    i = 0;
                }
                
                int e = idx - 1;
                if (e < 0){
                    e = possibleMoves.length - 1;
                }

                if (cpuMove.equals(possibleMoves[i])){
                    return RPSAbstract.PLAYER_WIN_OUTCOME;
                }
        
                else if (cpuMove.equals(possibleMoves[e])){
                    return RPSAbstract.CPU_WIN_OUTCOME;

                }

                else {
                    return RPSAbstract.TIE_OUTCOME;
                }
            }
        }
        return RPSAbstract.INVALID_INPUT_OUTCOME;
    }


    /**
     * Main method that reads user input, generates cpu move, and plays game
     * 
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game
        String playerMove = new String("");
        String cpuMove = new String("");

        while (!playerMove.equals("q")){
            System.out.println(PROMPT_MOVE);
            playerMove = in.nextLine();
            if (playerMove.equals("q")) {
                game.end();
                break;
            }
            
            else{
                cpuMove = game.genCPUMove();
                if (game.determineWinner(playerMove, cpuMove) != 
                    RPSAbstract.INVALID_INPUT_OUTCOME) {
                    game.play(playerMove, cpuMove);
                }
            }
        }    
        in.close();
    }
}
