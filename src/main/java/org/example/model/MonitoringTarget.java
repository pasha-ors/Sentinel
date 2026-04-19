package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name="targets")
public class MonitoringTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;

    public MonitoringTarget() {}

    public MonitoringTarget(String name, String url){
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getUrl(){
        return url;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setUrl(String url){
        this.url = url;
    }

}
