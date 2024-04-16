import java.util.ArrayList;

public class Student extends User{

    private String facultyNumber;
    private String egn;
    private ArrayList<Grade> listGrade;


    public Student(String userName, String password,String facultyNumber,String egn) {
        super(userName, password);
        this.facultyNumber=facultyNumber;
        this.egn=egn;
        this.listGrade=new ArrayList<Grade>();
    }


    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public ArrayList<Grade> getListGrade() {
        return listGrade;
    }

    public void setListGrade(ArrayList<Grade> listGrade) {
        this.listGrade = listGrade;
    }

    @Override
    public TypeUser getUserType() {
        return TypeUser.Student;
    }



}
