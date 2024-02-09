import java.time.LocalDate;

public class Task {
    private String taskName;
    private LocalDate dueDate;
    private boolean completed;

    public Task(String taskName, LocalDate dueDate, boolean completed) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    protected String getTaskName() {
        return taskName;
    }

    protected LocalDate getDueDate() {
        return dueDate;
    }

    protected boolean isCompleted() {
        return completed;
    }

    protected void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
