import java.util.UUID;

public class Account implements AccountManagement {
    private String username;
    private String password;
    private UUID accountID;

    @Override
    public boolean validatePassword(String enteredPassword) {
        return false;
    }

    @Override
    public void changeUsername(String newUsername) {
    }

    @Override
    public void changePassword(String newPassword) {
    }
}
