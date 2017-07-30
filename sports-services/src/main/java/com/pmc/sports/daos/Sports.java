package com.pmc.sports.daos;

import javax.persistence.*;

@Entity
@Table(name = "sports", catalog = "sportsdb")
public class Sports {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "sports_name")
    private String sportsName;
    @Column(name = "is_active", nullable = false, columnDefinition = "tinyint(1)")
    private boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSportsName() {
        return sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
