package br.com.bicmsystems.clinical.domain.appointment.validations.scheduling;

import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentRequest;
import br.com.bicmsystems.clinical.infra.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidatorOpeningHourClinic implements ValidatorAppointmentScheduling {

    public void validate(AppointmentRequest data) {

        var dateTime = data.dateTime();
        var isSunday = dateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isBeforeOpeningHour = dateTime.getHour() < 7;
        var isAfterClosingHour = dateTime.getHour() > 18;

        if(isSunday || isBeforeOpeningHour || isAfterClosingHour) {
            throw new ValidationException("Appointment outside clinic opening hours");
        }

    }

}
