/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ThinkPad P50
 */
public class Clo_Plo {
    private int clo,plo,syllabusId;

    public Clo_Plo() {
    }

    public Clo_Plo(int clo, int plo, int syllabusId) {
        this.clo = clo;
        this.plo = plo;
        this.syllabusId = syllabusId;
    }

    public int getClo() {
        return clo;
    }

    public void setClo(int clo) {
        this.clo = clo;
    }

    public int getPlo() {
        return plo;
    }

    public void setPlo(int plo) {
        this.plo = plo;
    }

    public int getSyllabusId() {
        return syllabusId;
    }

    public void setSyllabusId(int syllabusId) {
        this.syllabusId = syllabusId;
    }
    
}
