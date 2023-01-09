package ru.job4j.lombok;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @NonNull
    @Getter
    @EqualsAndHashCode.Include
    private int id;
    @Getter
    @Setter
    private String name;
}