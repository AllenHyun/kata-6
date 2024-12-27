package software.ulpgc.kata6.view.adapters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import software.ulpgc.kata6.control.commands.WorkingDaysCommand;

import java.io.IOException;
import java.time.LocalDate;

public class WorkingDaysAdapter {
    public static WorkingDaysCommand.Input inputOf(HttpServletRequest req){
        return new WorkingDaysCommand.Input() {
            @Override
            public LocalDate start() {
                return LocalDate.parse(req.getParameter("start"));
            }

            @Override
            public LocalDate end() {
                return LocalDate.parse(req.getParameter("end"));
            }
        };
    }

    public static WorkingDaysCommand.Output outputOf(HttpServletResponse res){
        return days -> {
            try {
                res.getWriter().write(days);
                res.setStatus(200);
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        };
    }
}
