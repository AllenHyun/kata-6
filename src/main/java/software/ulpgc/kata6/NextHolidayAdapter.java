package software.ulpgc.kata6;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

public class NextHolidayAdapter {
    public static NextHolidayCommand.Input inputOf(HttpServletRequest req){
        return new NextHolidayCommand.Input() {
            @Override
            public LocalDate start() {
                return LocalDate.parse(req.getParameter("start"));
            }
        };
    }

    public static NextHolidayCommand.Output outputOf(HttpServletResponse res){
        return date -> {
            try {
                res.setContentType("application/json");
                res.getWriter().write("{\"nextHoliday\": \"" + date.toString() + "\"}");
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        };
    }
}
