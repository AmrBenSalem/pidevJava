/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.entities;

/**
 *
 * @author oussema
 */
public class Documents {
    private int id;
    private String path;
    private String libelle;
    private String date;
    private String typeDocument;
    private Double size;
    private String language;
    private int id_user;
    private Matiere matiere;
    private String image;
    private int flag;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Documents() {
    }

    public Documents(int id, String path, String libelle, String date, String typeDocument, Double size, String language, Matiere matiere, String image, int flag) {
        this.id = id;
        this.path = path;
        this.libelle = libelle;
        this.date = date;
        this.typeDocument = typeDocument;
        this.size = size;
        this.language = language;
        this.matiere = matiere;
        this.image = image;
        this.flag = flag;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Documents other = (Documents) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
   
    
}
