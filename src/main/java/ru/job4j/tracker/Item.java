package ru.job4j.tracker;

import java.text.SimpleDateFormat;
import javax.persistence.*;

import lombok.*;
import ru.job4j.toone.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Item {
    private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat(
            "dd-MMMM-EEEE-yyyy HH:mm:ss");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    private LocalDateTime created = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> participates;
}