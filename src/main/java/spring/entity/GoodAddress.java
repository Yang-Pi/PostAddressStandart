package spring.entity;

import javax.persistence.*;

@Entity
@Table(name = "goodAddresses")
public class GoodAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    public GoodAddress() { }

    public GoodAddress(String name) {
        this.name = name;
    }

    public String getAddress() {
        return name;
    }

    public void setAddress(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
}
