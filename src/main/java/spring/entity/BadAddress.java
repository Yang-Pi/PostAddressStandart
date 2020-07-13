package spring.entity;

import javax.persistence.*;

@Entity
@Table(name = "badAddresses")
public class BadAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String badName;

    @ManyToOne
    private GoodAddress goodAddress;

    public BadAddress() {
    }

    public BadAddress(String badName, GoodAddress goodAddress) {
        this.badName = badName;
        this.goodAddress = goodAddress;
    }

    public Integer getId() {
        return id;
    }

    public String getBadAddress() {
        return badName;
    }

    public void setBadAddress(String badName) {
        this.badName = badName;
    }

    public GoodAddress getGoodAddress() {
        return goodAddress;
    }

    public void setGoodAddress(GoodAddress goodAddress) {
        this.goodAddress = goodAddress;
    }
}
