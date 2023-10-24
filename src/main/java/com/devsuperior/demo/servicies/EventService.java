package com.devsuperior.demo.servicies;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EventService {

    @Autowired
    private EventRepository repository;
    @Autowired
    private CityRepository cityRepository;

    public Page<EventDTO> allEventPage(Pageable pageable){
        Page<Event> page = repository.findAll(pageable);
        return page.map(EventDTO::new);
    }
    public EventDTO newEvent(EventDTO dto){
        Event entity = new Event();
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity.setCity(cityRepository.getReferenceById(dto.getCityId()));
        entity = repository.save(entity);
        return new EventDTO(entity);
    }
}
