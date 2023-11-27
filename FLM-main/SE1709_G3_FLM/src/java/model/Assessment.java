/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author MSI
 */
public class Assessment {
    private String assessment_id, syllabus_id, category_id, type_id, part , 
            weight, duration, completion_criteria, competencies;

    public Assessment() {
    }

    public Assessment(String assessment_id, String syllabus_id, String category_id, String type_id, String part, String weight, String duration, String completion_criteria, String competencies) {
        this.assessment_id = assessment_id;
        this.syllabus_id = syllabus_id;
        this.category_id = category_id;
        this.type_id = type_id;
        this.part = part;
        this.weight = weight;
        this.duration = duration;
        this.completion_criteria = completion_criteria;
        this.competencies = competencies;
    }

    public String getAssessment_id() {
        return assessment_id;
    }

    public void setAssessment_id(String assessment_id) {
        this.assessment_id = assessment_id;
    }

    public String getSyllabus_id() {
        return syllabus_id;
    }

    public void setSyllabus_id(String syllabus_id) {
        this.syllabus_id = syllabus_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCompletion_criteria() {
        return completion_criteria;
    }

    public void setCompletion_criteria(String completion_criteria) {
        this.completion_criteria = completion_criteria;
    }

    public String getCompetencies() {
        return competencies;
    }

    public void setCompetencies(String competencies) {
        this.competencies = competencies;
    }
    
    
}
