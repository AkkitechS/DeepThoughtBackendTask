/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigmacoder.deepthoughttask.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sigmacoder
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    
    @Id
    @GeneratedValue
    private int uid;
    private String type;
    private String name;
    private String tagline;
    
    private LocalDateTime schedule;
    
    private String description;
    
    private String imageName;
    private String imageType;
    
    @Lob
    private byte[] imageData;
    
    private String moderator;
    private String category;
    private String subCategory;
    private int rigorRank;
    private String attendees;
}
