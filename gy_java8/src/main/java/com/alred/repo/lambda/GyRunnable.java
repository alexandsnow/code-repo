package com.alred.repo.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GyRunnable {
    public static void main(String[] args) {
        UserDto userDto = new UserDto();
        userDto.setName("gaoyang");
        userDto.setAge("20");
        GyFunctionalInterface gyFunctionalInterface = (u) -> {
            System.out.println(u.getName());
            System.out.println(u.toString());
        };

        GyFunctionalInterface gyFunctionalInterface1 = UserDto::showUserInfo;
        GyFunctionalInterface gyFunctionalInterface2 = System.out::println;
        GyFunctionalInterface gyFunctionalInterface3 = userDto::myTest;
        GyFunctionalInterface gyFunctionalInterface4 = userDto::showInfo;
        gyFunctionalInterface.show(userDto);
        gyFunctionalInterface1.show(userDto);
        gyFunctionalInterface2.show(userDto);
        gyFunctionalInterface3.show(userDto);
        gyFunctionalInterface4.show(userDto);
    }
}

@FunctionalInterface
interface GyFunctionalInterface<T extends UserDto,v> {

    default void test() {
        System.out.println("test");
    }

    void show(T dto);
}

class UserDto {
    String name;
    String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static void showUserInfo(UserDto userDto) {
        System.out.println("showUserInfo method : " + userDto.toString());
    }

    public void showInfo(UserDto userDto){
        System.out.println("non-static method call: "+userDto.toString());
    }

    public void myTest(UserDto userDto){
        System.out.println("myTest");
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

