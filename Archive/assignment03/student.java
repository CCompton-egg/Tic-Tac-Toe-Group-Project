public class Student implements Comparable<Student> {
    private String id;
    private String name;
    private double gpa;
    private String major;

    public Student(String id, String name, double gpa, String major) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public int compareTo(Student s) {
        return this.id.compareTo(s.id);
    }

    @Override
    public String toString() {
        return "Student[id:" + id + ", name:" + name + ", gpa:" + gpa + ", major:" + major + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

