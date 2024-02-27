package com.mh.gaimadministrative.models.entities;

import com.mh.gaimadministrative.models.enums.Offices;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "administrators")
public class Administrator implements Serializable {

    @Serial
    private static final long serialVersionUID = 1787179138672080591L;

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String document;

    @Column(length = 128, nullable = false)
    private String fullName;

    @Enumerated(EnumType.ORDINAL)
    private Offices office;

    protected Administrator() {
    }

    public Administrator(String document, String fullName, Offices office) {
        this.document = document;
        this.fullName = fullName;
        this.office = office;
    }

    public Administrator(UUID id, String document, String fullName, Offices office) {
        this.id = id;
        this.document = document;
        this.fullName = fullName;
        this.office = office;
    }

    public UUID getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public String getFullName() {
        return fullName;
    }

    public Offices getOffice() {
        return office;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(document, that.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, document);
    }

}
