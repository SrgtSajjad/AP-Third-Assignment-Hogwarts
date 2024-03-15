import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Account implements AccountManagement {
    private String username;
    private String password;
    private String role;
    private UUID accountID;
    private String fullName;
    private int age;
    private String speciality;
    private boolean signedUp;

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        accountID = UUID.randomUUID();
        signedUp = false;
    }

    public String getRole() {
        return role;
    }

    public UUID getAccountID() {
        return accountID;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public boolean isSignedUp() {
        return signedUp;
    }

    public void setSignedUp(boolean signedUp) {
        this.signedUp = signedUp;
    }

    @Override
    public boolean validatePassword(String enteredPassword) {
        if (Objects.equals(this.password, enteredPassword))
            return true;
        return false;
    }

    @Override
    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }

    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
