package com.glebchanskiy.DND.models;

//import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "plant")
@Getter
@Setter
@ToString
public class Plant {
    @Id     
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
//    @NotEmpty(message = "Это поле не должно быть пустым")
    private String name;
    @Column(name = "rareness")
//    @NotEmpty(message = "Это поле не должно быть пустым")
    private String rareness;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    @JoinTable(
            name = "plant_location",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private List<Location> locations;

    @Column(name = "difficult")
//    @NotEmpty(message = "Это поле не должно быть пустым")
    private int difficult;

    @Column(name = "feature")
//    @NotEmpty(message = "Это поле не должно быть пустым")
    private String feature;

    @Column(name = "description")
//    @NotEmpty(message = "Это поле не должно быть пустым")
    private String description;

    public Plant() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return difficult == plant.difficult && Objects.equals(name, plant.name) && Objects.equals(rareness, plant.rareness) && Objects.equals(feature, plant.feature) && Objects.equals(description, plant.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rareness, difficult, feature, description);
    }
}
