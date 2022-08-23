package tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    protected String name;    //Название, кратко описывающее суть задачи
    protected String description;  //Описание, в котором раскрываются детали
    protected int id;   //УИН задачи
    protected Status status;  //Статус, отображающий её прогресс. ("NEW", "IN_PROGRESS", "DONE")
    protected Type type;
    protected LocalDateTime startTime;
    protected long duration;
    protected LocalDateTime endTime;
    protected User user;

    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy|HH:mm");

    public Task(int id, String name, String description, Type type, Status status, LocalDateTime startTime, long duration) { //для тестов
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.status = status;
        this.startTime = startTime;
        this.duration = duration;
       }

    public Task(String name, String description, Type type, LocalDateTime startTime, long duration) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.startTime = startTime;
        this.duration = duration;
    }
    public Task(String name, String description, Type type){
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public  String toString(){
        return  id + "," + type.toString() + "," + name + "," + status.toString()
                + "," + description +  ","
                + (startTime.format(outputFormatter)) + ","  + duration + "," + (getEndTime().format(outputFormatter)) ;
    }
    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return startTime.plus(Duration.ofMinutes(duration));
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}

