package com.logic;

public class AdminUserFactory extends UserFactory{

    @Override
    public User creatUser() {
        return new Admin("test2", "test2", "test2", "test2");
    }

}
