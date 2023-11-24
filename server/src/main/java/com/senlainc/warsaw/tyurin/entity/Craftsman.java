package com.senlainc.warsaw.tyurin.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "craftsman")
public class Craftsman{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    public Craftsman() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Craftsman craftsman = (Craftsman) o;
        return id == craftsman.id &&
                name.equals(craftsman.name) &&
                surname.equals(craftsman.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(id)
                .append(",")
                .append(name)
                .append(",")
                .append(surname);

        return stringBuilder.toString();
    }
}
