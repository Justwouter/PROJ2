package com.logic;

public class NormaleUserFactory extends UserFactory{

    @Override
    public User creatUser() {
        return new User("test1", false, "test1", "test1", "test1");
    }

}
