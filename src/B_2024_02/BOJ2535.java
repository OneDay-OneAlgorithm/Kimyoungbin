package B_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2535
{
    static int N;
    static Student[] students;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        students = new Student[N];

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            int country = Integer.parseInt(line_N[0]);
            int student = Integer.parseInt(line_N[1]);
            int grade = Integer.parseInt(line_N[2]);
            students[i] = new Student(country, student, grade);
        }

        Arrays.sort(students);

        // 출력
        for(int i=0; i<2; i++) {
            System.out.println(students[i].country + " " + students[i].student);
        }
        if(students[0].country==students[1].country) { // 처음 두명 나라가 같으면
            for(int i=2; i<N; i++) {
                if(students[1].country!=students[i].country) {
                    System.out.println(students[i].country + " " + students[i].student);
                    return;
                }
            }
        } else { // 처음 두명 나라가 다르면
            System.out.println(students[2].country + " " + students[2].student);
        }
    }


    static class Student implements Comparable<Student> {
        int country;
        int student;
        int grade;

        public Student(int country, int student, int grade) {
            this.country = country;
            this.student = student;
            this.grade = grade;
        }

        @Override
        public int compareTo(Student s) {
            return s.grade - this.grade;
        }
    }
}