package com.example.betterDays.Entities;
//
//
import java.time.LocalDateTime;
//
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    String text;

    LocalDateTime start;

    LocalDateTime end1 ;

    String color;


    @OneToOne
    Patient patient;
    @ManyToOne
    private DoctorEntity doctor;



//
    public Long getId() {
        return id;
    }
//
    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd1() {
        return end1;
    }

    public void setEnd1(LocalDateTime end) {
        this.end1 = end;

    }


    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }
}