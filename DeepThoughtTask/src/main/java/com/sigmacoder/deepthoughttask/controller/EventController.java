/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigmacoder.deepthoughttask.controller;

import com.sigmacoder.deepthoughttask.model.Event;
import com.sigmacoder.deepthoughttask.service.EventService;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.ws.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sigmacoder
 */

@RequestMapping("api/v3/app/")
@RestController
public class EventController {
    
    @Autowired
    private EventService service;
    
    @RequestMapping(path = {"events"})
    public Integer saveEvent(@RequestParam String name, @RequestParam MultipartFile imageData, @RequestParam String tagline, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime schedule, @RequestParam String description, @RequestParam String moderator, @RequestParam String category, @RequestParam String subCategory, @RequestParam Integer rigorRank) throws Exception {
    
        Integer event = service.saveEvent(name, imageData, tagline, schedule, description, moderator, category, subCategory, rigorRank);
        return event;
    }
    
    @RequestMapping(path = {"events"}, method = RequestMethod.GET, params = {"uid"})
    public Event getEvent(@RequestParam Integer uid) {
        Event event = service.getEventById(uid);
        return event;
    }
    
    @RequestMapping(path = {"events"}, method = RequestMethod.GET, params = {"type", "limit", "page"})
    public List<Event> getEventsByPage(@RequestParam String type, @RequestParam Integer limit, @RequestParam Integer page) {
        
        List<Event> eventList = service.getPaginateEvents(type, limit, page);
        return eventList;
    }
    
    @RequestMapping(path = {"events/id"})
    public Event updateEvent(@RequestParam Integer uid, @RequestParam String name, @RequestParam String tagline, @RequestParam String description, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime schedule, @RequestParam MultipartFile imageData, @RequestParam String moderator, @RequestParam String category, @RequestParam String subCategory, @RequestParam Integer rigorRank) throws Exception {
        
        Event event = service.updateEvent(uid, name, tagline, description, schedule, imageData, moderator, category, subCategory, rigorRank);
        return event;
    }
    
    @RequestMapping(path = {"events/{uid}"})
    public Event deleteEvent(@PathVariable Integer uid) throws Exception {
        
        Event event = service.deleteEvent(uid);
        return event;
    }
    
}
