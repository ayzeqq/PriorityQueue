import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;
import java.util.PriorityQueue;

class Student{
    int id;
    String name;
    double cgpa;
    
    Student(int id, String name, double cgpa){
        this.id=id;
        this.name=name;
        this.cgpa=cgpa;
    }
    
    int getID(){
        return id;
    }
    
    String getName(){
        return name;
    }
    
    double getCGPA(){
        return cgpa;
    }
}

class Priorities {
    PriorityQueue<Student> students = new PriorityQueue<>(new Comparator<>() {
        @Override
        public int compare(Student s1, Student s2){
            if(s1.getCGPA()==s2.getCGPA() && s1.getName().equals(s2.getName())){
                return s1.getID()-s2.getID();
            }
            else if(s1.getCGPA()==s2.getCGPA()){
                return s1.getName().compareTo(s2.getName());
            }
            else{
                if(s1.getCGPA()>s2.getCGPA()){
                    return -1;
                }
                else{
                    return 1;
                }
            }
        }
    });
    
    List<Student> getStudents(List<String> events){
        for(String str : events){
            if(str.startsWith("ENTER")){
                String[] words = str.split(" ");
                students.add(new Student(Integer.parseInt(words[3]), words[1], Double.parseDouble(words[2])));
            }
            else{
                students.poll();
            }
        }
        List<Student> studList = new ArrayList<>();
        while(!students.isEmpty()){
            studList.add(students.poll());
        }
        return studList;
    }
    
    
}

public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}
/*
12
ENTER John 3.75 50
ENTER Mark 3.8 24
ENTER Shafaet 3.7 35
SERVED
SERVED
ENTER Samiha 3.85 36
SERVED
ENTER Ashley 3.9 42
ENTER Maria 3.6 46
ENTER Anik 3.95 49
ENTER Dan 3.95 50
SERVED
 */