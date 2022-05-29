package Cruise.model.entity;

import java.io.Serializable;

public class User implements Serializable {
    String login;
    String password;
    Role role;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    int account;
    int notification;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getNotification() {
        return notification;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }

    public enum Role {
        ADMIN, USER;

        public boolean isUser() {
            return this == USER;
        }
        public boolean isAdmin() {
            return this == ADMIN;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}

