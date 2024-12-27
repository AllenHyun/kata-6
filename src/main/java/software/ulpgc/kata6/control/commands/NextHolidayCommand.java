package software.ulpgc.kata6.control.commands;

import software.ulpgc.kata6.model.WorkingDaysCalendar;
import software.ulpgc.kata6.control.Command;

import java.time.LocalDate;

public class NextHolidayCommand implements Command {
    private final Input input;
    private final Output output;

    public NextHolidayCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {
        LocalDate current = input.start();
        LocalDate nextHoliday = current.plusDays(1);
        while(new WorkingDaysCalendar().iteratorFor(current).hasNext()){
            nextHoliday = nextHoliday.plusDays(1);
            if (isHoliday(nextHoliday)){
                break;
            }
        }
        output.nextHoliday(nextHoliday);
    }

    private boolean isHoliday(LocalDate date){
        return date.getDayOfWeek().getValue() == 7 || date.getDayOfWeek().getValue() == 6;
    }

    public interface Input{
        LocalDate start();
    }

    public interface Output{
        void nextHoliday(LocalDate date);
    }
}
