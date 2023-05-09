/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigmacoder.deepthoughttask.service;

import com.sigmacoder.deepthoughttask.model.Event;
import com.sigmacoder.deepthoughttask.repository.EventRepository;
import com.sigmacoder.deepthoughttask.util.ImageUtility;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sigmacoder
 */


@Service
public class EventService {
    
    @Autowired
    private EventRepository repo;
    
    public Integer saveEvent(String name, MultipartFile imageData, String tagline, LocalDateTime schedule, String description, String moderator, String category, String subCategory, Integer rigorRank) throws Exception {
        
        Event event = repo.save(Event.builder().name(name).tagline(tagline).schedule(schedule).description(description).moderator(moderator).category(category).subCategory(subCategory).rigorRank(rigorRank).imageName(imageData.getOriginalFilename()).imageType(imageData.getContentType()).imageData(ImageUtility.compressImage(imageData.getBytes())).build());
        Integer uid = event.getUid();
        return uid;
    }
    
    public Event updateEvent(Integer uid, String name, String tagline, String description, LocalDateTime schedule, MultipartFile imageData, String moderator, String category, String subCategory, Integer rigorRank) throws Exception {
        
        Optional<Event> op = repo.findById(uid);
        Event ev = op.get();
        ev.setName(name);
        ev.setTagline(tagline);
        ev.setSchedule(schedule);
        ev.setDescription(description);
        ev.setModerator(moderator);
        ev.setCategory(category);
        ev.setSubCategory(subCategory);
        ev.setRigorRank(rigorRank);
        ev.setImageName(imageData.getOriginalFilename());
        ev.setImageType(imageData.getContentType());
        ev.setImageData(ImageUtility.compressImage(imageData.getBytes()));
        repo.save(ev);
        return ev;
    }
    
    public Event getEventById(Integer uid) {
        Optional<Event> op = repo.findById(uid);
        Event event = op.get();
        return event;
    }
    
    public List<Event> getPaginateEvents(String type, Integer limit, Integer page) {
        
        Pageable pgbl = PageRequest.of(page, limit);
        List<Event> eventList = repo.findAllByType(type, pgbl);
        return eventList;
    }
    
    public Event deleteEvent(Integer uid) {
        
        Optional<Event> op = repo.findById(uid);
        Event event = op.get();
        repo.delete(event);
        return event;
    }
    
    public byte[] getImageBytes(String img) {
        Optional<Event> op = repo.findByName(img);
        byte[] image = ImageUtility.decompressImage(op.get().getImageData());
        return image;
    }
}
