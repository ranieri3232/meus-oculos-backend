package dev.rlucassmd.meusOculosApi.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_ubs")
public class Ubs implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int esf;

    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User managedBy;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Ubs() {
    }

    public Ubs(Long id, String name, int esf, User managedBy, Address address) {
        this.id = id;
        this.name = name;
        this.esf = esf;
        this.managedBy = managedBy;
        this.address = address;
    }

    public User getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(User managedBy) {
        this.managedBy = managedBy;
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

    public int getEsf() {
        return esf;
    }

    public void setEsf(int esf) {
        this.esf = esf;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubs ubs = (Ubs) o;
        return Objects.equals(id, ubs.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
