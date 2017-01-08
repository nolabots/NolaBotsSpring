package org.usfirst.frc.team5953.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Data")
public class Data {

    @Id
    @Column
    private long id;

    @Column
    private String name;

    public Data() {
        // left intentionally blank for JPA
    }

    public Data(int id, String name) {
    	this.id = id;
    	this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }


}
