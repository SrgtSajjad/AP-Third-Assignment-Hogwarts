public class SignUpRequest {
    String username;
    String password;
    String role;

    public SignUpRequest(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
