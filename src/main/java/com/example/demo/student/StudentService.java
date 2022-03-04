package com.example.demo.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail =
                studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);

    }
    public void deleteStudent(Long studentId) {
       boolean exists = studentRepository.existsById(studentId);
       if (!exists) {
           throw new IllegalStateException(
                   "student with id " + studentId + " doesn't exist"
           );
       }
       studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent (Long studentId,
                               String studentName,
                                String email)
    {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "student with id " + studentId + " doesn't exist"
        ));
        if (studentName!=null && studentName.length()> 0 &&
            !Objects.equals(student.getName(), studentName))  {
            student.setName(studentName);
        }
        if (email!=null && email.length()> 0 &&
                !Objects.equals(student.getEmail(), email))  {
            student.setEmail(email);
        }
    }
}
