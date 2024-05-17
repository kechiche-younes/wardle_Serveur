package application;



import javafx.beans.property.SimpleStringProperty;

import javafx.beans.property.StringProperty;



public class StatData {

private final StringProperty nom;

private final StringProperty prenom;



public StatData(String nom, String prenom) {

this.nom = new SimpleStringProperty(nom);

this.prenom = new SimpleStringProperty(prenom);

}



public StringProperty getNomProperty() {

return nom;

}



public String getNom() {

return nom.get();

}



public StringProperty getPrenomProperty() {

return prenom;

}



public String getPrenom() {

return prenom.get();

}

}

