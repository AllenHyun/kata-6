package software.ulpgc.kata6;

public class Main {
    public static void main(String[] args) {
        CommandFactory factory = new CommandFactory();
        new WorkingDatsService(7070, factory).start();
    }
}
