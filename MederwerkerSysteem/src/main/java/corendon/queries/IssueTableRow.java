/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corendon.queries;


/**
 *
 * @author joris
 */
public class IssueTableRow {
    public String id, type, color, brand, label_number, reference_number;
    public int client_id;

    public String getId() {
        return id;
    }
    
    public String getType()
    {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public String getLabel_number() {
        return label_number;
    }

    public String getReference_number() {
        return reference_number;
    }
    
    public String getIssue_type() {
        if (client_id > 0) {
            return "Lost";
        }
        return "Found";
    }
    
    public IssueTableRow(String id, String type, String color, String brand, String label_number, String reference_number) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.brand = brand;
        this.label_number = label_number;
        this.reference_number = reference_number;
    }
    
    public IssueTableRow(String id, String type, String color, String brand, String label_number, String reference_number, int client_id) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.brand = brand;
        this.label_number = label_number;
        this.reference_number = reference_number;
        this.client_id = client_id;
    }
}
