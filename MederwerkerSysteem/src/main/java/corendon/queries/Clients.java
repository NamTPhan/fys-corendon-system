/*
 * Class clients information
 */
package corendon.queries;

import java.sql.Timestamp;

/**
 *
 * @author Joris Schelfhout - Studentnummer: 500777847
 */
public class Clients {

    //variables
    private int id;
    private String firstName, lastName, email, addres, place, postalCode, phoneNumber, state, country;
    private Timestamp createdAt, updatedAt;

    //constructors

    /**
     *  All parameters contains information from the client
     * @param firstName
     * @param lastName
     * @param email
     * @param addres
     * @param place
     * @param postalCode
     * @param phoneNumber
     * @param state
     * @param country
     * @param createdAt
     * @param updatedAt
     */
    public Clients(String firstName, String lastName, String email, String addres, String place, String postalCode, String phoneNumber, String state, String country, Timestamp createdAt, Timestamp updatedAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addres = addres;
        this.place = place;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.country = country;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Clients(int id, String firstName, String lastName, String email, String addres, String place, String postalCode, String phoneNumber, String state, String country, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addres = addres;
        this.place = place;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.country = country;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return firstname of a client
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName of a client
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return lastname of a client
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName of a client
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return email of a client
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email of a client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return address of a client
     */
    public String getAddres() {
        return addres;
    }

    /**
     *
     * @param addres of a client
     */
    public void setAddres(String addres) {
        this.addres = addres;
    }

    /**
     *
     * @return place of residence of a client
     */
    public String getPlace() {
        return place;
    }

    /**
     *
     * @param place of a client
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     *
     * @return ZIP/postal code of a client
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode of a client
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return phone number of a client
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber of a client
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return state/province of a client
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state of a client
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return country of a client
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country of a client
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return timestamp 
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
       /**
     *
     * @return timestamp 
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }


}
