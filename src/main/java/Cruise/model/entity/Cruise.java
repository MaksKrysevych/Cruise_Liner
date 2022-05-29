package Cruise.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Cruise implements Serializable {
    String name;
    String regions;
    String liner;
    Date start_day;
    Date finish_day;
    String from_port;
    String to_port;
    int days;
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_day() {
        return start_day;
    }

    public void setStart_day(Date start_day) {
        this.start_day = start_day;
    }

    public Date getFinish_day() {
        return finish_day;
    }

    public void setFinish_day(Date finish_day) {
        this.finish_day = finish_day;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int nights) {
        this.days = nights;
    }

    public String getFrom_port() {
        return from_port;
    }

    public void setFrom_port(String from_port) {
        this.from_port = from_port;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    public String getTo_port() {
        return to_port;
    }

    public void setTo_port(String to_port) {
        this.to_port = to_port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLiner() {
        return liner;
    }

    public void setLiner(String liner) {
        this.liner = liner;
    }
}

