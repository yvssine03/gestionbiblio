package com.library;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService studentService;
    private StudentDAO studentDAO;

    @BeforeEach
    void setUp() {
        studentDAO = new StudentDAO();
        studentService = new StudentService();
        studentDAO.deleteAllStudents();
    }

    @Test
    void testAddStudent() {
        studentService.addStudent(1, "Alice", "alice@example.com");
        assertEquals(1, studentDAO.getAllStudents().size());
        assertEquals("Alice", studentDAO.getStudentById(1).get().getName());
    }

    @Test
    void testUpdateStudent() {
        studentService.addStudent(1, "Alice", "alice@example.com");
        studentService.updateStudent(1, "Alice Smith", "alice.smith@example.com");
        assertEquals("Alice Smith", studentDAO.getStudentById(1).get().getName());
    }

    @Test
    void testDeleteStudent() {
        studentService.addStudent(1, "Alice", "alice@example.com");
        studentService.deleteStudent(1);
        assertTrue(studentDAO.getStudentById(1).isEmpty());
    }

    @Test
    void testGetAllStudents() {
        studentService.addStudent(1, "Alice", "alice@example.com");
        studentService.addStudent(2, "Bob", "bob@example.com");
        assertEquals(2, studentDAO.getAllStudents().size());
    }
}
