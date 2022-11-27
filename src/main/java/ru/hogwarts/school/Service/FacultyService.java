package ru.hogwarts.school.Service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long nextId = 0;

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++nextId);
        faculties.put(nextId, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> matchingFacultiesByColor(String color) {
        return faculties.entrySet().stream()
                .filter(faculties -> faculties.getValue().getColor().equals(color))
                .collect(Collectors.toMap(faculties -> faculties.getKey(), faculties -> faculties.getValue())).values();
    }
}
