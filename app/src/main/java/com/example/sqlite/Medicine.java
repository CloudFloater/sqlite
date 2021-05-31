package com.example.sqlite;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Medicine {
    private int id;
    private String name;
    private String family_name;
    private String introduction;
    private String method;
    private Date deadline;

    public Medicine(int id, String name,String family_name, String introduction,String method,String dateStr) {
        this.id = id;
        this.name = name;
        this.family_name = family_name;
        this.introduction = introduction;
        this.method = method;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //使用SimpleDateFormat的parse()方法生成Date
            Date date = new Date(sf.parse(dateStr).getTime());
            deadline = date;
            //打印Date

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Medicine(String name,String family_name, String introduction,String method,String dateStr) {
        this.id = id;
        this.name = name;
        this.family_name = family_name;
        this.introduction = introduction;
        this.method = method;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //使用SimpleDateFormat的parse()方法生成Date
            Date date = new Date(sf.parse(dateStr).getTime());
            deadline = date;
            //打印Date

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Medicine(String name, String family_name) {
        this.name = name;
        this.family_name = family_name;
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

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getDeadline() { return deadline.toString(); }

    public void setDeadline(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //使用SimpleDateFormat的parse()方法生成Date
            Date date = new Date(sf.parse(dateStr).getTime());
            deadline = date;
            //打印Date

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getIntroduction() { return introduction; }

    public void setIntroduction(String introduction) { this.introduction = introduction; }

    public String getMethod() { return method; }

    public void setMethod(String method) { this.method = method; }

    public String inf() {
        return "Medicine{"+
                "id="+id+
                ",name='"+name+"\'"+
                ",服药人 '"+family_name+"\'"+
                ",保质期 '"+deadline+"\'"+
                ",药品介绍 '"+introduction+"\'"+
                ",服药方法'"+method+"\'"+
                "}";
    }
}


