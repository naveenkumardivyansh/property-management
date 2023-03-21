package com.mycompany.propertymanagement.repository;

import com.mycompany.propertymanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRespository extends CrudRepository<PropertyEntity,Long> {

}
