import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;
    private Mark cpu;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
        this.cpu = cpu;
        this.numExploredNodes = 0;
    }

    // Ne pas changer cette méthode
    public int getNumOfExploredNodes(){
        return numExploredNodes;
    }

    private Mark getOpponent(Mark player) {
        if (player == Mark.X) {
            return Mark.O;
        }
        return Mark.X;
    }

    private int minimax(Board board, Mark currentPlayer) {
        numExploredNodes++;

        if (board.isGameOver()) {
            return board.evaluate(cpu);
        }

        if (currentPlayer == cpu) {
            int bestScore = Integer.MIN_VALUE;

            for (Move move : board.getPossibleMoves()) {
                board.play(move, currentPlayer);

                int score = minimax(
                    board,
                    getOpponent(currentPlayer)
                );

                board.undo(move);

                bestScore = Math.max(bestScore, score);
            }

            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (Move move : board.getPossibleMoves()) {
                board.play(move, currentPlayer);

                int score = minimax(
                    board,
                    getOpponent(currentPlayer)
                );

                board.undo(move);

                bestScore = Math.min(bestScore, score);
            }

            return bestScore;
        }
    }

    private int alphaBeta(Board board, Mark currentPlayer, int alpha, int beta) {
        numExploredNodes++;

        if (board.isGameOver()) {
            return board.evaluate(cpu);
        }

        if (currentPlayer == cpu) {
            int bestScore = Integer.MIN_VALUE;

            for (Move move : board.getPossibleMoves()) {
                board.play(move, currentPlayer);

                int score = alphaBeta(
                    board,
                    getOpponent(currentPlayer),
                    alpha,
                    beta
                );

                board.undo(move);

                bestScore = Math.max(bestScore, score);
                alpha = Math.max(alpha, bestScore);

                if (alpha >= beta) {
                    break;
                }
            }

            return bestScore;
        }
        else {
            int bestScore = Integer.MAX_VALUE;

            for (Move move : board.getPossibleMoves()) {
                board.play(move, currentPlayer);

                int score = alphaBeta(
                    board,
                    getOpponent(currentPlayer),
                    alpha,
                    beta
                );

                board.undo(move);

                bestScore = Math.min(bestScore, score);
                beta = Math.min(beta, bestScore);

                if (alpha >= beta) {
                    break;
                }
            }

        return bestScore;
    }
}

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
     public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        numExploredNodes = 0;

        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        for (Move move : board.getPossibleMoves()) {
            board.play(move, cpu);

            int score = minimax(
                board,
                getOpponent(cpu)
            );

            board.undo(move);

            if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            }
            else if (score == bestScore) {
                bestMoves.add(move);
            }
        }

        return bestMoves;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
     public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;

        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        for (Move move : board.getPossibleMoves()) {
            board.play(move, cpu);

            int score = alphaBeta(
                board,
                getOpponent(cpu),
                Integer.MIN_VALUE,
                Integer.MAX_VALUE
            );

            board.undo(move);

            if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            }
            else if (score == bestScore) {
                bestMoves.add(move);
            }
        }

        return bestMoves;
    }

}
