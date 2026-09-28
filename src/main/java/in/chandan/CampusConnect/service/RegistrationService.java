package in.chandan.CampusConnect.service;

import ch.qos.logback.core.boolex.EventEvaluatorBase;
import in.chandan.CampusConnect.controller.RegistrationController;
import in.chandan.CampusConnect.dto.RegistrationReqDto;
import in.chandan.CampusConnect.entity.Event;
import in.chandan.CampusConnect.entity.Registration;
import in.chandan.CampusConnect.repository.EventRepository;
import in.chandan.CampusConnect.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private EventRepository eventRepository;

    public RegistrationService(RegistrationRepository registrationRepository,
                               EventRepository eventRepository){
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
    }
    public String register(RegistrationReqDto req){

        Registration reg = new Registration();

        Event event = eventRepository.findByName(req.getEvent_name()).orElse(null);

        reg.setEvent(event);
        reg.setUid(reg.getUid());
        registrationRepository.save(reg);
        return "Registeration completed for event" + reg.getEvent().getName();
    }
}
