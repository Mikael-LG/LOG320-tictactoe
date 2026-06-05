import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Board board = new Board();

        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(1, 0), Mark.O);
        board.play(new Move(1, 1), Mark.O);

        CPUPlayer player = new CPUPlayer(Mark.X);

        ArrayList<Move> minMaxMoves =
            player.getNextMoveMinMax(board);

        System.out.println("Minimax :");

        for (Move move : minMaxMoves) {
            System.out.println(
                "(" + move.getRow() + ", " + move.getCol() + ")"
            );
        }

        System.out.println(
            "Nombre de noeuds Minimax : "
            + player.getNumOfExploredNodes()
        );

        ArrayList<Move> alphaBetaMoves =
            player.getNextMoveAB(board);

        System.out.println("Alpha-Beta :");

        for (Move move : alphaBetaMoves) {
            System.out.println(
                "(" + move.getRow() + ", " + move.getCol() + ")"
            );
        }

        System.out.println(
            "Nombre de noeuds Alpha-Beta : "
            + player.getNumOfExploredNodes()
        );
    }
}