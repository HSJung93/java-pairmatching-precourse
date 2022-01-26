package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Crew {
    private Course course;
    private String name;
    private static List<Crew> level1Crew = new ArrayList<>();
    private static List<Crew> level2Crew = new ArrayList<>();
    private static List<Crew> level4Crew = new ArrayList<>();

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Crew crew = (Crew) o;
        return Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public static List<String> getCrewNames(List<Crew> crewList) {
        return crewList.stream().map(Crew::getName).collect(Collectors.toList());
    }

    public void saveCrew(List<Crew> crews, Level level) {
        if (level == Level.LEVEL1) {
            level1Crew.addAll(crews);
        }
    }


}
