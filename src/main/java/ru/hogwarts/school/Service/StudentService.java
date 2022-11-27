package ru.hogwarts.school.Service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long nextId = 0;

    public Student addStudent(Student student) {
        student.setId(++nextId);
        students.put(nextId, student);
        return student;
    }

    public Student findStudent(long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    public Collection<Student> coincidencesStudentsByAge(int age) {
        return students.entrySet().stream()
                .filter(students -> students.getValue().getAge() == age)
                .collect(Collectors.toMap(students -> students.getKey(), students -> students.getValue())).values();
    }
}
