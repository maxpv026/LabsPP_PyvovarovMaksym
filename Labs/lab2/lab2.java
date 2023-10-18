import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private int id;
    private String surname;
    private String name;
    private String pName; // по-батькові
    private String date_of_birth;
    private String adress;
    private String phone_numb;
    private String faculty;
    private int course;
    private String group;

    public Student(int id, String surname, String name, String p_name, String date_of_birth, String adress, String phone_numb, String faculty, int course, String group) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.pName = p_name;
        this.date_of_birth = date_of_birth;
        this.adress = adress;
        this.phone_numb = phone_numb;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    // Геттери та сеттери

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getGroup() {
        return group;
    }

    // Інші геттери

    @Override
    public String toString() {
        return "Student ID: " + id +
                ", Surname: " + surname +
                ", First Name: " + name +
                ", Patronymic Name: " + pName +
                ", Date of Birth: " + date_of_birth +
                ", Address: " + adress +
                ", Phone: " + phone_numb +
                ", Faculty: " + faculty +
                ", Course: " + course +
                ", Group: " + group;
    }
}

public class lab2_pp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Створюємо масив студентів
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Boyko", "Petro", "A.", "2000-03-15", "Shevchenka 34", "123-456-7890", "Engineering", 2, "KN303"));
        students.add(new Student(2, "Shevchenko", "Ivan", "V.", "1999-07-21", "Promyslova 21", "987-654-3210", "Science", 3, "OI24"));
        students.add(new Student(3, "Melnyk", "Maryna", "O.", "2001-05-10", "Podatkova 54", "555-123-4567", "Engineering", 1, "IT102"));
        students.add(new Student(4, "Klymenko", "Stanyslav", "D.", "2000-11-30", "Zolota 104", "333-999-8888", "Science", 2, "S201"));
        students.add(new Student(5, "Moroz", "Dmytro", "E.", "1999-09-05", "Kontraktova 32", "111-222-3333", "Arts", 4, "SH401"));

        System.out.println("Please enter the value of the faculty: ");
        String desiredFaculty = scan.nextLine();
        System.out.println("Please enter the year: ");
        int desiredYear = scan.nextInt();

        // Вивід студентів заданого факультету
        System.out.println("Список студентів факультету " + desiredFaculty + ":");
        for (Student student : students) {
            if (student.getFaculty().equals(desiredFaculty)) {
                System.out.println(student);
            }
        }

        // Вивід студентів, які народилися після заданого року
        System.out.println("\nСписок студентів, які народилися після року " + desiredYear + ":");
        for (Student student : students) {
            int birthYear = Integer.parseInt(student.getDate_of_birth().split("-")[0]);
            if (birthYear > desiredYear) {
                System.out.println(student);
            }
        }

        scan.nextLine(); // Очистити буфер введення

        System.out.println("Please enter a group value to search for: ");
        String desiredGroup = scan.nextLine();

        // Вивід студентів з заданої групи
        System.out.println("\nСписок студентів навчальної групи " + desiredGroup + ":");
        for (Student student : students) {
            if (student.getGroup().equals(desiredGroup)) {
                System.out.println(student);
            }
        }
    }
}