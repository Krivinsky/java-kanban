import java.util.ArrayList;

public class Task {
    String name;    //Название, кратко описывающее суть задачи
    String description;  //Описание, в котором раскрываются детали.
    int ID;   //УИН задачи
    String status;  //Статус, отображающий её прогресс. ("NEW", "IN_PROGRESS", "DONE")

    Task(String name, String description){
        this.name = name;
        this.description = description;
//               this.ID = id;
//        this.status = status;
    }

}

