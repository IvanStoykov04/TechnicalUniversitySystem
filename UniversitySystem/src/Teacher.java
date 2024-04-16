import java.util.ArrayList;

public class Teacher extends User{

    private String email;

    public Teacher(String userName, String password,String email) {
        super(userName, password);
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public TypeUser getUserType() {
        return TypeUser.Teacher;
    }

    //add student's grade
    public void addGrade(Student student,Grade grade){
        ArrayList<Grade> listGrade=student.getListGrade();
        listGrade.add(grade);
    }


}
