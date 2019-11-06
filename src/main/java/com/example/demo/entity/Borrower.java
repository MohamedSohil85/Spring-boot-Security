package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Borrower implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @OneToMany
    private List<Book>bookList;

    public Borrower() {
    }

    public Borrower(Long id,String name, String address) {
        this.id=id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return Objects.equals(getId(), borrower.getId()) &&
                Objects.equals(getName(), borrower.getName()) &&
                Objects.equals(getAddress(), borrower.getAddress()) &&
                Objects.equals(bookList, borrower.bookList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), bookList);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Borrower{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');

        sb.append('}');
        return sb.toString();
    }
}
