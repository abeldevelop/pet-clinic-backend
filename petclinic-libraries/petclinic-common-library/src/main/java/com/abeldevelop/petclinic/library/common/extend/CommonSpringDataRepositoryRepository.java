package com.abeldevelop.petclinic.library.common.extend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommonSpringDataRepositoryRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
