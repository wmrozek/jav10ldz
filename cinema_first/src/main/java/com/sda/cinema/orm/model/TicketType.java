package com.sda.cinema.orm.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_types")
public class TicketType {
    @Id
    private int id;
    @Column(name = "type_name")
    private String typeName;
    @Column(name = "percentage_discount")
    private int percentageDiscount;

    @Override
    public String toString() {
        return "TicketTypes{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", percentageDiscount=" + percentageDiscount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(int percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }
}