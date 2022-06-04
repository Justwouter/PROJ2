package com.logic;

public abstract class UserFactory {
    public static final NormaleUserFactory normaleUserFactory = new NormaleUserFactory();
    public static final AdminUserFactory adminUserFactory = new AdminUserFactory();

    public abstract User creatUser();
    
}
