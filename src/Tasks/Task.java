package Tasks;


public class Task {

    protected String name;    //Название, кратко описывающее суть задачи
    protected String description;  //Описание, в котором раскрываются детали.
    protected int id;   //УИН задачи
    protected Status status;  //Статус, отображающий её прогресс. ("NEW", "IN_PROGRESS", "DONE")

    public Task(String name, String description){
        this.name = name;
        this.description = description;
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

    @Override
    public  String toString(){
        return  "Task{" +
                "name=" + name +
                ", description=" + description + ", " +
                "id=" + id +
                ", stusus=" + status + '}';
    }
}

