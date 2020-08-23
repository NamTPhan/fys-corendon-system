/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corendon.utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static java.sql.Types.NULL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import corendon.queries.MyJDBC;

/**
 *
 * @author joris
 */
public class ExcelHandler {

    //Methode om excelsheet met bagage op te slaan in de database
    public static void saveToDatabase(File filepath, int importType) throws SQLException, InvalidFormatException, IOException {

        MyJDBC myJDBC = new MyJDBC();

        OPCPackage fs = OPCPackage.open(filepath);
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        XSSFSheet sheet = wb.getSheetAt(0);
        Row row;
        PreparedStatement pstm = null;
        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            row = sheet.getRow(i);

            String type = formatter.formatCellValue(row.getCell(0));
            String brand = formatter.formatCellValue(row.getCell(1));
            String color = formatter.formatCellValue(row.getCell(2));
            String labelNumber = formatter.formatCellValue(row.getCell(3));
            String flightNumber = formatter.formatCellValue(row.getCell(4));
            String destination = formatter.formatCellValue(row.getCell(5));
            String extraInfo = formatter.formatCellValue(row.getCell(6));
            String compensation = formatter.formatCellValue(row.getCell(7));

            //insert luggage in DB
            String sql = "INSERT INTO luggage (type, brand, color, label_number, flight_number, destination, extra_information,"
                    + "compensation, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstm = myJDBC.connection.prepareStatement(sql);

            pstm.setString(1, type);
            pstm.setString(2, brand);
            pstm.setString(3, color);
            pstm.setString(4, labelNumber);
            pstm.setString(5, flightNumber);
            pstm.setString(6, destination);
            pstm.setString(7, extraInfo);
            pstm.setString(8, compensation);
            pstm.setTimestamp(9, getCurrentTimeStamp());
            pstm.setTimestamp(10, getCurrentTimeStamp());

            myJDBC.executePreparedStatementQuery(pstm);

            //Create issue for luggage in DB
            String selectSql = "SELECT id FROM luggage ORDER BY id DESC LIMIT 1;";
            String returnedLastLuggageId = myJDBC.executeStringQuery(selectSql);
            int lastLuggageId = Integer.parseInt(returnedLastLuggageId);

            sql = "INSERT INTO issues (luggage_id, airport_id, status_id, client_id, created_at, updated_at )"
                    + " VALUES(?, ?, ?, ?, ?, ?)";
            pstm = myJDBC.connection.prepareStatement(sql);

            pstm.setInt(1, lastLuggageId + 1);
            pstm.setInt(2, 0);
            pstm.setInt(3, 1);

            //als bagage verloren is dan ook een user importeren uit het excel sheet.
            if (importType == 1) {
                sql = "INSERT INTO clients (first_name, last_name, email, address, place, postal_code, phone_number, state, country, created_at, updated_at)"
                        + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement clientPstm = myJDBC.connection.prepareStatement(sql);

                String firstName = formatter.formatCellValue(row.getCell(8));
                String lastName = formatter.formatCellValue(row.getCell(9));
                String email = formatter.formatCellValue(row.getCell(10));
                String address = formatter.formatCellValue(row.getCell(11));
                String place = formatter.formatCellValue(row.getCell(12));
                String postalCode = formatter.formatCellValue(row.getCell(13));
                String phoneNumber = formatter.formatCellValue(row.getCell(14));
                String state = formatter.formatCellValue(row.getCell(15));
                String country = formatter.formatCellValue(row.getCell(16));

                clientPstm.setString(1, firstName);
                clientPstm.setString(2, lastName);
                clientPstm.setString(3, email);
                clientPstm.setString(4, address);
                clientPstm.setString(5, place);
                clientPstm.setString(6, postalCode);
                clientPstm.setString(7, phoneNumber);
                clientPstm.setString(8, state);
                clientPstm.setString(9, country);
                clientPstm.setTimestamp(10, getCurrentTimeStamp());
                clientPstm.setTimestamp(11, getCurrentTimeStamp());

                myJDBC.executePreparedStatementQuery(clientPstm);

                //get last client Id
                selectSql = "SELECT id FROM clients ORDER BY id DESC LIMIT 1;";
                String returnedLastClientId = myJDBC.executeStringQuery(selectSql);
                int lastClientId = Integer.parseInt(returnedLastClientId);

                pstm.setInt(4, lastClientId);

            } else {
                pstm.setNull(4, NULL);
            }

            pstm.setTimestamp(5, getCurrentTimeStamp());
            pstm.setTimestamp(6, getCurrentTimeStamp());

            myJDBC.executePreparedStatementQuery(pstm);

            System.out.println("Import rows " + i);
        }

        pstm.close();
        System.out.println("Success import excel to mysql table");

        //popup weergeven aan gebruiker
        showPopup();
    }

    //genereer popup
    private static void showPopup() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Luggage import system");
        alert.setHeaderText("Import succeeded!");
        alert.setContentText("The Excelsheet with luggage has been succesfully added to  the system.");

        alert.showAndWait();
    }

    //methode die timestamp teruggeeft om op te slaan in DB
    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }
}
