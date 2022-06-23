package co.com.sofka.domain.program.command;


import co.com.sofka.generic.Command;

import java.util.List;

public class AddCourseCommand  extends Command {
    private String programId;
    private  String courseId;
    private String name;
    private List<String> categories;

    public AddCourseCommand(){

    }
    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
