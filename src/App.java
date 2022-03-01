public class App {
    public static void main(String[] args) throws Exception {
        // new Frame();
        Game game = new Game();
        int lines = game.countLines();
        System.out.println(lines);
    }
}
