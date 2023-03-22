package com.mycompany.propertymanagement.dto.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.dto.service.PropertyService;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// we can also use here any of the notation like @Component, @Configuration, @Service or @Repository
// any one can create singleton class but we are prefering @Service beacuse it is a service layer
@Service
public class PropertyServiceImpl implements PropertyService {

    @Value("${pms.dummy:}")
    private String dummy;

    @Value("${spring.datasource.url:}")
    private String dburl;

    @Autowired
    private PropertyRespository propertyRespository;
    @Autowired
    private PropertyConverter propertyConverter;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        pe = propertyRespository.save(pe);

        propertyDTO = propertyConverter.convertEntitytoDTO(pe);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        System.out.println("Inside Service Layer "+dummy);
        System.out.println("Inside Service Layer "+dummy);
        System.out.println("Inside Service Layer "+dburl);
        List<PropertyEntity> listOfProps = (List<PropertyEntity>)propertyRespository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();
        for(PropertyEntity pe : listOfProps) {
            PropertyDTO dto = propertyConverter.convertEntitytoDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        //return type of findById() is Optional and it is easy to check whether any data is present in it so if condition is used
       Optional<PropertyEntity> optEn = propertyRespository.findById(propertyId);
       PropertyDTO dto = null;
       if(optEn.isPresent()) {
           PropertyEntity pe = optEn.get();//data from data base
           //Overriding to database
           pe.setTitle(propertyDTO.getTitle());
           pe.setAddress(propertyDTO.getAddress());
           pe.setOwnerEmail(propertyDTO.getOwnerEmail());
           pe.setOwnerName(propertyDTO.getOwnerName());
           pe.setPrice(propertyDTO.getPrice());
           pe.setDescription(propertyDTO.getDescription());

           dto=propertyConverter.convertEntitytoDTO(pe);//for sending the data to client if not required then eliminate it
           propertyRespository.save(pe);
       }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRespository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEn.isPresent()) {
            PropertyEntity pe = optEn.get();//data from data base
            //Overriding to database
            pe.setDescription(propertyDTO.getDescription());

            dto=propertyConverter.convertEntitytoDTO(pe);//for sending the data to client if not required then eliminate it
            propertyRespository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRespository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEn.isPresent()) {
            PropertyEntity pe = optEn.get();//data from data base
            //Overriding to database
            pe.setPrice(propertyDTO.getPrice());
            dto=propertyConverter.convertEntitytoDTO(pe);//for sending the data to client if not required then eliminate it
            propertyRespository.save(pe);
        }
        return dto;
    }

    @Override
    public void deletePropert(Long propertyId) {
        propertyRespository.deleteById(propertyId);
    }
}
