package software.ulpgc.kata6;

import io.javalin.Javalin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WorkingDatsService {
    private final  int port;
    private final CommandFactory factory;
    private Javalin app;

    public WorkingDatsService(int port, CommandFactory factory) {
        this.port = port;
        this.factory = factory;
        factory.register("working-days", workingDaysBuilder());
        factory.register("working-date", workingDateBuilder());
        factory.register("next-holiday", nextHolidayBuilder());
    }

    public void start(){
        app = Javalin.create()
                .get("/working-days", ctx -> execute("working-days", ctx.req(), ctx.res()))
                .get("/working-date", ctx -> execute("working-date", ctx.req(), ctx.res()))
                .get("/next-holiday", ctx -> execute("next-holiday", ctx.req(), ctx.res()))
                .start(port);
    }

    private void execute(String command, HttpServletRequest req, HttpServletResponse res){
        factory.with(req, res).build(command).execute();
    }

    public void stop(){
        app.stop();
    }

    private static CommandFactory.Builder workingDaysBuilder(){
        return ((req, res) -> new WorkingDaysCommand(WorkingDaysAdapter.inputOf(req), WorkingDaysAdapter.outputOf(res)));
    }

    private static CommandFactory.Builder workingDateBuilder(){
        return ((req, res) -> new WorkingDateCommand(WorkingDateAdapter.inputOf(req), WorkingDateAdapter.outputOf(res)));
    }

    private static CommandFactory.Builder nextHolidayBuilder(){
        return ((req, res) -> new NextHolidayCommand(NextHolidayAdapter.inputOf(req), NextHolidayAdapter.outputOf(res)));
    }
}
