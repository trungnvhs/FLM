/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class Question {
    String question_id,session_id,name,details;

    public Question() {
    }

    public Question(String session_id, String name, String details) {
        this.session_id = session_id;
        this.name = name;
        this.details = details;
    }

    public Question(String question_id, String session_id, String name, String details) {
        this.question_id = question_id;
        this.session_id = session_id;
        this.name = name;
        this.details = details;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
}
