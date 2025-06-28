import java.util.*;

class Student {
    String name;
    int distanceFromStop;

    public Student(String name, int distanceFromStop) {
        this.name = name;
        this.distanceFromStop = distanceFromStop;
    }
}

class BusStop {
    String name;
    int capacity;
    List<Student> assignedStudents;

    public BusStop(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.assignedStudents = new ArrayList<>();
    }

    public boolean assign(Student student) {
        if (assignedStudents.size() < capacity) {
            assignedStudents.add(student);
            return true;
        }
        return false;
    }
}

public class BusStopAllocator {
    public static void main(String[] args) {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop("Stop A", 2));
        busStops.add(new BusStop("Stop B", 2));
        busStops.add(new BusStop("Stop C", 2));

        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 3));
        students.add(new Student("Bob", 1));
        students.add(new Student("Charlie", 2));
        students.add(new Student("David", 5));
        students.add(new Student("Eva", 4));
        students.add(new Student("Frank", 1));

        // Sort students by distance (smallest first)
        students.sort(Comparator.comparingInt(s -> s.distanceFromStop));

        for (Student student : students) {
            boolean assigned = false;
            for (BusStop stop : busStops) {
                if (stop.assign(student)) {
                    assigned = true;
                    break;
                }
            }
            if (!assigned) {
                System.out.println("❌ Could not assign " + student.name + " to any bus stop.");
            }
        }

        System.out.println("\n✅ Final Bus Stop Assignments:");
        for (BusStop stop : busStops) {
            System.out.println(stop.name + ":");
            for (Student s : stop.assignedStudents) {
                System.out.println("  - " + s.name + " (Distance: " + s.distanceFromStop + ")");
            }
        }
    }
}
