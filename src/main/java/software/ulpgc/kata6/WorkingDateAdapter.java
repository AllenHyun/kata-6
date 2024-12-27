package software.ulpgc.kata6;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

public class WorkingDateAdapter {
    public static WorkingDateCommand.Input inputOf(HttpServletRequest req){
        return  new WorkingDateCommand.Input() {
            @Override
            public LocalDate start() {
                return LocalDate.parse(req.getParameter("start"));
            }

            @Override
            public int workingDays() {
                return Integer.parseInt(req.getParameter("days"));
            }
        };
    }

    public static WorkingDateCommand.Output outputOf(HttpServletResponse res){
        return date -> {
            try {
                res.getWriter().write(date.toString());
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        };
    }
}
