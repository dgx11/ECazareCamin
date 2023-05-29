public class Utilizatori {
    private int id;
    private String username;
    private String email;
    private String password;
    private String accountType;

    public Utilizatori(int id, String username, String email, String password, String accountType) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    // Getters and setters for the private fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
