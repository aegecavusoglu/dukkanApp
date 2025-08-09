package dukkan.dukkanapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String taxNumber;
    private String address;
    private String contactName;
    private String contactPhone;

    public Company() {
    }

    public Company(Long id, String name, String taxNumber, String address, String contactName, String contactPhone) {
        this.id = id;
        this.name = name;
        this.taxNumber = taxNumber;
        this.address = address;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }
}
