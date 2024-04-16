public class Admin extends User{

    public Admin(String userName, String password) {
        super(userName, password);
    }

    @Override
    public TypeUser getUserType() {
        return TypeUser.Admin;
    }
}
