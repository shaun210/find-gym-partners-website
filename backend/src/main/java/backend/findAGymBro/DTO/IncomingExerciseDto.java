package backend.findAGymBro.DTO;

public class IncomingExerciseDto {
    private int reps;
    private int weight;

    public IncomingExerciseDto(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
