package ru.job4j.lombok;

import lombok.*;

import java.util.List;

@Builder
@ToString
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Permission {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private List<String> rules;
}
