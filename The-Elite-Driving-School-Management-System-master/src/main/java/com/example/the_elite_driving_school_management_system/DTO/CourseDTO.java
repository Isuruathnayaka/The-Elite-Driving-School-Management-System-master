package com.example.the_elite_driving_school_management_system.DTO;

public class CourseDTO {
    private String id;
    private String name;
    private String duration;
    private Double fee;
    private String description;

    public CourseDTO(String id, String name, String duration, Double fee, String description) {
      this.id = id;
      this.name = name;
      this.duration = duration;
      this.fee = fee;
      this.description = description;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
