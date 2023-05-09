/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigmacoder.deepthoughttask.repository;

import com.sigmacoder.deepthoughttask.model.Event;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sigmacoder
 */


@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    
    Optional<Event> findByName(String imageName);
    
    List<Event> findAllByType(String type, Pageable pageable);
}
