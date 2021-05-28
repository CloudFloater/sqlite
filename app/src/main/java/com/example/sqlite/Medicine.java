package com.example.sqlite;

public class Medicine {
    private int id;
    private String name;
    private int age;

    public Medicine(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Medicine(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Medicine() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String inf() {
        return "Medicine{"+
                "id="+id+
                ",name='"+name+"\'"+
                ",age='"+age+"\'"+
                "}";
    }
}


