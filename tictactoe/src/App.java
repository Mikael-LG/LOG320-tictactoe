public class App {
    public static void main(String[] args) throws Exception {
        

    }

    // Test Board::evaluate()
    public static void testEvaluate() {
        Board board = new Board();

        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(0, 2), Mark.X);

        System.out.println(board.evaluate(Mark.X)); // Devrait afficher 100
        System.out.println(board.evaluate(Mark.O)); // Devrait afficher -100
        System.out.println(board.isGameOver());     // Devrait afficher true
    }
}
