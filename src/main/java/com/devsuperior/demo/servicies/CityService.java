package com.devsuperior.demo.servicies;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    private CityRepository repository;

    public List<CityDTO> allCities(){
        List<City> list = repository.findAll();
        return list.stream().map(CityDTO::new).collect(Collectors.toList());
    }
    public CityDTO newCities(CityDTO cityDTO){
        City entity = new City();
        entity.setName(cityDTO.getName());
        entity = repository.save(entity);
        return new CityDTO(entity);
    }
}
