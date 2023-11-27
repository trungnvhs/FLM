/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author quan
 */
public class Syllabus {

    private String syllabus_id, name, no_of_credit, is_active, is_approved,
            degree_level, decision_id, designer_id, subject_id, subject_name;
    private Subject subject;
    private Decision decision;
    private User designer;
    private ArrayList<Pre_requisite> pre;
    private String scoringScale;
    private String minAvgMarkToPass;
    private String note;
    private String createBy;
    private String time_allocation;
    private String stu_task;
    private String tool;

    public Syllabus() {
    }

    public Syllabus(String syllabus_id, String name, String decision_id, Subject subject) {
        this.syllabus_id = syllabus_id;
        this.name = name;
        this.decision_id = decision_id;
        this.subject = subject;
    }

    public Syllabus(String syllabus_id, String name, String no_of_credit, String is_active, String is_approved, String degree_level, String decision_id, String subject_id, User designer) {
        this.syllabus_id = syllabus_id;
        this.name = name;
        this.no_of_credit = no_of_credit;
        this.is_active = is_active;
        this.is_approved = is_approved;
        this.degree_level = degree_level;
        this.decision_id = decision_id;
        this.subject_id = subject_id;
        this.designer = designer;
    }
    
    public Syllabus(String syllabus_id, String name, String no_of_credit, String is_active, String is_approved, String degree_level, String decision_id, String designer_id, String subject_id, String scoringScale, String minAvgMarkToPass, String note, String createBy, String time_allocation, String stu_task, String tool) {
        this.syllabus_id = syllabus_id;
        this.name = name;
        this.no_of_credit = no_of_credit;
        this.is_active = is_active;
        this.is_approved = is_approved;
        this.degree_level = degree_level;
        this.decision_id = decision_id;
        this.designer_id = designer_id;
        this.subject_id = subject_id;
        this.scoringScale = scoringScale;
        this.minAvgMarkToPass = minAvgMarkToPass;
        this.note = note;
        this.createBy = createBy;
        this.time_allocation = time_allocation;
        this.stu_task = stu_task;
        this.tool = tool;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public Syllabus(String syllabus_id, String name, String no_of_credit, String is_active, String is_approved, String degree_level, String decision_id, String designer_id, String subject_id) {
        this.syllabus_id = syllabus_id;
        this.name = name;
        this.no_of_credit = no_of_credit;
        this.is_active = is_active;
        this.is_approved = is_approved;
        this.degree_level = degree_level;
        this.decision_id = decision_id;
        this.designer_id = designer_id;
        this.subject_id = subject_id;

    }

    public Syllabus(String syllabus_id, String name, Subject subject, Decision decision, ArrayList<Pre_requisite> pre) {
        this.syllabus_id = syllabus_id;
        this.name = name;
        this.subject = subject;
        this.decision = decision;
        this.pre = pre;
    }

    public Syllabus(String syllabus_id, String name) {
        this.syllabus_id = syllabus_id;
        this.name = name;
    }

    public String getSyllabus_id() {
        return syllabus_id;
    }

    public void setSyllabus_id(String syllabus_id) {
        this.syllabus_id = syllabus_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo_of_credit() {
        return no_of_credit;
    }

    public void setNo_of_credit(String no_of_credit) {
        this.no_of_credit = no_of_credit;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(String is_approved) {
        this.is_approved = is_approved;
    }

    public String getDegree_level() {
        return degree_level;
    }

    public void setDegree_level(String degree_level) {
        this.degree_level = degree_level;
    }

    public String getDecision_id() {
        return decision_id;
    }

    public void setDecision_id(String decision_id) {
        this.decision_id = decision_id;
    }

    public String getDesigner_id() {
        return designer_id;
    }

    public void setDesigner_id(String designer_id) {
        this.designer_id = designer_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public ArrayList<Pre_requisite> getPre() {
        return pre;
    }

    public void setPre(ArrayList<Pre_requisite> pre) {
        this.pre = pre;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public User getDesigner() {
        return designer;
    }

    public void setDesigner(User designer) {
        this.designer = designer;
    }
    public String getScoringScale() {
        return scoringScale;
    }

    public void setScoringScale(String scoringScale) {
        this.scoringScale = scoringScale;
    }

    public String getMinAvgMarkToPass() {
        return minAvgMarkToPass;
    }

    public void setMinAvgMarkToPass(String minAvgMarkToPass) {
        this.minAvgMarkToPass = minAvgMarkToPass;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getTime_allocation() {
        return time_allocation;
    }

    public void setTime_allocation(String time_allocation) {
        this.time_allocation = time_allocation;
    }

    public String getStu_task() {
        return stu_task;
    }

    public void setStu_task(String stu_task) {
        this.stu_task = stu_task;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }
}
