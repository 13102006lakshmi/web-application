import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
 
class Student {
    int id;
    String name;
    String dept;
 
    Student(int id, String name, String dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
    }
 
    @Override
    public String toString() {
        return id + " - " + name + " - " + dept;
    }
}
 
interface StudentRepository {
    void add(Student s);
    Student getById(int id);
    void update(Student s);
    void delete(int id);
    List<Student> getAll();
}
 
class InMemoryStudentRepository implements StudentRepository {
    private final Map<Integer, Student> store =
            new LinkedHashMap<>();
 
    @Override
    public void add(Student s) {
        store.put(s.id, s);
    }
 
    @Override
    public Student getById(int id) {
        return store.get(id);
    }
 
    @Override
    public void update(Student s) {
        if (store.containsKey(s.id)) {
            store.put(s.id, s);
        }
    }
 
    @Override
    public void delete(int id) {
        store.remove(id);
    }
 
    @Override
    public List<Student> getAll() {
        return new ArrayList<>(store.values());
    }
}
 
public class RepositoryPatternDemo {
    public static void main(String[] args) {
        StudentRepository repo =
                new InMemoryStudentRepository();
 
        // Add students
        repo.add(new Student(1, "Arjun", "CSE"));
        repo.add(new Student(2, "Meena", "IT"));
 
        System.out.println("All students: " + repo.getAll());
 
        // Retrieve a student
        System.out.println("Get student by ID: "
                + repo.getById(2));
 
        // Update a student
        repo.update(new Student(1, "Arjun Kumar", "CSE"));
        System.out.println("After update : "
                + repo.getById(1));
 
        // Delete a student
        repo.delete(2);
        System.out.println("After delete : " + repo.getAll());
    }
}
