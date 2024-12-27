package software.ulpgc.kata6;

import software.ulpgc.kata6.control.CommandFactory;
import software.ulpgc.kata6.view.WorkingDatsService;

public class Main {
    public static void main(String[] args) {
        CommandFactory factory = new CommandFactory();
        new WorkingDatsService(7070, factory).start();
    }
}
