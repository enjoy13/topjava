package ru.javawebinar.topjava.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NamedQueries(value = {
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m  WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Meal.GET, query = "SELECT m FROM Meal m JOIN FETCH m.user WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Meal.GET_ALL, query = "SELECT m FROM Meal m JOIN FETCH m.user WHERE  m.user.id=:userId ORDER BY m.dateTime DESC "),
        @NamedQuery(name = Meal.GET_BETWEEN, query = "SELECT m FROM Meal m JOIN FETCH m.user WHERE  m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC "),
//        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.dateTime=:dateTime, m.description=:description, m.calories=: calories WHERE m.user.id=:userId AND m.id=:id")
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"date_time", "user_id"}, name = "meals_unique_user_datetime_idx")})
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Meal extends AbstractBaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String GET = "Meal.get";
    public static final String GET_ALL = "Meal.getAll";
    public static final String GET_BETWEEN = "Meal.getBetween";
//        public static final String UPDATE="Meal.update";

    @Column(name = "date_Time", nullable = false, unique = true)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;

    @Column(name = "calories", nullable = false)
    @NotNull
    @Range(min = 1, max = 10000)
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }
}
