package backend.findAGymBro.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    @ManyToOne
    private Member member;
    @OneToMany
    private List<Exercise> exercises;

    public Workout() {
        this.exercises = new ArrayList<>();
    }

    public Workout(LocalDate date, Member member) {
        this.date = date;
        this.member = member;
        this.exercises = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Member getMember() {
        return member;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMember(Member member) {
        this.member = member;
    }



    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", date=" + date +
                ", member=" + member +
                ", actualExercises=" + exercises +
                '}';
    }
}
