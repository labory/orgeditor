package com.carcasser.orgeditor.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Organization.
 */
@Entity
@Table(name = "organization")
public class Organization implements IsSerializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Size(min = 2, max = 20)
    private String address;

    @NotNull
    @Size(min = 2, max = 20)
    private String description;

    public Organization() {
    }

    public Organization(Long id, String name, String address, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public Organization getOrganization() {
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Organization{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
