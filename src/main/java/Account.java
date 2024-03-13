import java.util.UUID;

public class Account implements AccountManagement {
    private String username;
    private String password;
    private String role;
    private boolean approved;
    private UUID accountID;

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        accountID = UUID.randomUUID();
    }

    public String getRole() {
        return role;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }


    public UUID getAccountID() {
        return accountID;
    }

    @Override
    public boolean validatePassword(String enteredPassword) {
        if (this.password == enteredPassword)
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
