package com.cedrox.goals.entity;

import java.util.Date;

/**
 * Created by edelarosaraymun on 1/7/16.
 */
public class Goal {
    private int id;
    private String description;
    private int status;
    private String creationDate;
    private String finalDate;
    private String expectedDate;

    public Goal(int id, String description, int status,String creationDate,String expectedDate){
        this.id = id;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
        this.expectedDate = expectedDate;
    }
    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }
    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }



}
