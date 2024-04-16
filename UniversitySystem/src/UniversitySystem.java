import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class UniversitySystem {

private ArrayList<User> listUser;
private PrintStream printToClient;
private Scanner inputFromClient;


public UniversitySystem(){
    this.listUser=new ArrayList<>();
}

    public ArrayList<User> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }


    //login form
    public void logIn(Socket clintSocket) throws IOException {
        printToClient = new PrintStream(clintSocket.getOutputStream());
        inputFromClient = new Scanner(clintSocket.getInputStream());
        //Created test user to test our system works correctly
        Admin admin=new Admin("admin","123456");
        listUser.add(admin);
        printToClient.println("Enter userName:  ");
        String userName=inputFromClient.next();
        printToClient.println("Enter password: ");
        String password=inputFromClient.next();
        for (User user : listUser) {
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                printToClient.println("Log is Successfully");
                switch (user.getUserType()) {
                    case Admin:
                        Admin administrator= (Admin) user;
                        adminMenu(administrator);
                        break;
                    case Teacher:
                        Teacher teacher= (Teacher) user;
                        teacherMenu(teacher);
                        break;
                    case Student:
                        Student student= (Student) user;
                        studentMenu(student);
                        break;
                }
            }else{
                printToClient.println("Incorrect Username or Password");
            }
        }
    }



    //create admin method
    public boolean createAdmin(){
    printToClient.println("Enter userName: ");
    String username=inputFromClient.next();
    printToClient.println("Enter password: ");
    String password=inputFromClient.next();
    Admin admin=new Admin(username,password);
    listUser.add(admin);
    return true;
}


//create student method
public boolean createStudent(){
    printToClient.println("Enter userName");
    String username=inputFromClient.next();
    printToClient.println("Enter password: ");
    String password=inputFromClient.next();
    printToClient.println("Enter facultyNumber: ");
    String facultyNumber=inputFromClient.next();
    printToClient.println("Enter egn: ");
    String egn=inputFromClient.next();
    if(validStudent(facultyNumber,egn)) {
        Student student = new Student(username, password, facultyNumber, egn);
        listUser.add(student);
        return true;
    }
    return false;
}

//create teacher method
public boolean createTeacher(){
    printToClient.println("Enter username: ");
    String userName=inputFromClient.next();
    printToClient.println("Enter password: ");
    String password=inputFromClient.next();
    printToClient.println("Enter email: ");
    String email=inputFromClient.next();
    if(validTeacher(email,password)) {
        Teacher teacher = new Teacher(userName, password, email);
        listUser.add(teacher);
        return true;
    }
    return false;
}


//admin's menu
public void adminMenu(Admin admin){
    printToClient.println("You can create a new user profile");
    printToClient.println("1-Admin\n2-Teacher\n3-Student\n");
    printToClient.println("Enter your choice: ");
    int choiceUser=inputFromClient.nextInt();
    switch (choiceUser){
        case 1:
            if (createAdmin()){
                System.out.println("The user is created successfully");
            }else{
                printToClient.println("The user in not created");
            }
            break;
        case 2:
            if (createTeacher()){
                System.out.println("The user is created successfully");
            }else{
                printToClient.println("The user in not created");
            }
            break;
        case 3:
            if (createStudent()){
                System.out.println("The user is created successfully");
            }else{
                printToClient.println("The user in not created");
            }
            break;
    }
}


//validate teachers by their email and password
public boolean validTeacher(String email,String password){
    String regexEmail="[a-z]+@tu-sofia\\\\.bg";
    String regexPassword="\\\\S{5,}";
    if(!email.matches(regexEmail)){
        printToClient.println("Incorrect email!");
        return  false;
    }
    if (!password.matches(regexPassword)){
        printToClient.println("Incorrect password!");
        return  false;
    }
    return true;
}



//validate students by their faculty number and egn
public boolean validStudent(String facultyNumber,String egn){
    String regexFacultyNumber="[1-9]{9}";
    String regexEgn="[0-9]{10}";
    if(!facultyNumber.matches(regexFacultyNumber)){
        printToClient.println("Incorrect faculty number");
        return false;
    }
    if(!egn.matches(regexEgn)){
        printToClient.println("Incorrect egn");
        return false;
    }
    return true;
    }



    //find a student by faculty number
public Student findStudentByFaclultyNumber(String facultyNumber){
    for(User user:listUser){
        if(user.getUserType()==TypeUser.Student){
            Student student=(Student) user;
            if(facultyNumber.equals(student.getFacultyNumber())){
                return student;
            }
        }
    }
return null;
}



//adding grade
public boolean addGrade(Teacher teacher){
printToClient.println("Enter subject: ");
String subject=inputFromClient.next();
printToClient.println("Enter semester: ");
int semester=inputFromClient.nextInt();
printToClient.println("Enter rating: ");
double rating=inputFromClient.nextDouble();
printToClient.println("Enter student's faculty number ");
String facultyNumber=inputFromClient.next();
Student student=findStudentByFaclultyNumber(facultyNumber);
if(student==null){
    printToClient.println("This student doesn't exist");
    return false;
}else {
    Grade grade = new Grade(subject, semester, rating);
    teacher.addGrade(student, grade);
    return true;
}
}


//teacher's menu
public void teacherMenu(Teacher teacher){
    printToClient.println("Welcome "+teacher.getUserName());
    printToClient.println("Enter rating....");
    if(addGrade(teacher)){
        printToClient.println("The rating is successfully added");
    }else{
        printToClient.println("The rating is not added");
    }
}


//sorting student's grades by semester and subject's name
public String getStudentRating(Student student){
    printToClient.println("Your grades are:");
    ArrayList<Grade> studentGrades = student.getListGrade();
    studentGrades.sort((gradeOne, gradeTwo) -> {
        if (gradeOne.getSemester() == gradeTwo.getSemester()) {
            return gradeOne.getSubject().compareTo(gradeTwo.getSubject());
        } else {
            return gradeOne.getSemester() - gradeTwo.getSemester();
        }
    });
    StringBuilder grades = new StringBuilder();
    for (Grade grade : studentGrades) {
        grades.append(grade.getSubject()).append(" ").append(grade.getRating()).append(" ").append(grade.getSemester()).append("\n");
    }
    return grades.toString();
}


//student menu
public void studentMenu(Student student){
    printToClient.println("Welcome "+student.getUserName());
    String grades=getStudentRating(student);
    printToClient.println(grades);
    printToClient.println("Thank you and goodbye + " + student.getUserName() + "!");
}



}
