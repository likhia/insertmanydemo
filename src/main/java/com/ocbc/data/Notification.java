package com.ocbc.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Notification")
public class Notification {

    @Id
    private String id;

    private String message;
    private String imageURI;

    public Notification(String message, String imageURI, int index) {
        this.message = message;
        this.imageURI = imageURI;
        this.id = "Notification" + index;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getImageURI() {
        return imageURI;
    }
    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    } 
    
}
