package com.mycompany.propertymanagement.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.dto.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Value("${pms.dummy:}")
    private String dummy;

    @Value("${spring.datasource.url:}")
    private String dburl;
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
        propertyDTO = propertyService.saveProperty(propertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);

//        System.out.println(propertyDTO);
        return responseEntity;
    }
    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        System.out.println(dummy);
        System.out.println(dburl);
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(propertyList,HttpStatus.OK);
        return responseEntity;
    }
    @PutMapping("/properties/{propertyId}")
        public ResponseEntity<PropertyDTO> updateProperty (@RequestBody PropertyDTO propertyDTO, @PathVariable Long
        propertyId){
            propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
            ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
            return responseEntity;
    }
    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId) {
        propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;

    }
    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId) {
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, propertyId);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }
    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId) {
        propertyService.deletePropert(propertyId);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return responseEntity;

    }
}
