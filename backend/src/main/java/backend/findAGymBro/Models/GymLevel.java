package backend.findAGymBro.Models;

public enum GymLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED;

    @Override
    public String toString() {
        String name = name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
