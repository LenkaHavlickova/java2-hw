package net.sevecek.videoboss.entity;

import java.io.*;

public class Customer implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private boolean deleted;

    private int version;

    //------------------------------------------------------------------------

    @Deprecated
    protected Customer() {
    }


    public Customer(Long id, int version) {
        this.id = id;
        this.version = version;
    }


    public Customer(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }


    public Customer(Long id, String firstName, String lastName, String address, boolean deleted, int version) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.deleted = deleted;
        this.version = version;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long customerId) {
        this.id = customerId;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public boolean isDeleted() {
        return deleted;
    }


    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    public int getVersion() {
        return version;
    }


    public void setVersion(int version) {
        this.version = version;
    }

    //------------------------------------------------------------------------

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (this.getId() == null) return false;
        if (!this.testInstanceOf(other)) return false;
        Customer otherEntity = (Customer) other;
        if (!otherEntity.testInstanceOf(this)) return false;

        return getId().equals(otherEntity.getId());
    }


    protected boolean testInstanceOf(Object other) {
        return other instanceof Customer;
    }


    @Override
    public int hashCode() {
        if (id == null) return 0;
        return id.hashCode();
    }


    @Override
    public String toString() {
        return "Customer[" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", deleted=" + deleted +
                ", version=" + version +
                ']';
    }

}
