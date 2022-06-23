package co.com.sofka.domain.program.command;


import co.com.sofka.generic.Command;

public class CreateProgramCommand extends Command {
    private String programId;
    private String name;

    public CreateProgramCommand(){

    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
