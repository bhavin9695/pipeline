package com.vertexcover.model;

import java.util.Objects;

public class User {

    private String userName;
    private String name;
    private String dept;
    private int retryAttemptCount = 0;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getRetryAttemptCount() {
        return retryAttemptCount;
    }

    public void setRetryAttemptCount(int retryAttemptCount) {
        this.retryAttemptCount = retryAttemptCount;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(name, user.name) &&
                Objects.equals(dept, user.dept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, name, dept);
    }
}
