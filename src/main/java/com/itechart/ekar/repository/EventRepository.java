package com.itechart.ekar.repository;

import com.itechart.ekar.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EventRepository<T extends Event> extends CrudRepository<T, Long> {

}
