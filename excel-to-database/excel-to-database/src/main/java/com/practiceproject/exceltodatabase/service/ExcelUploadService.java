package com.practiceproject.exceltodatabase.service;

import com.practiceproject.exceltodatabase.domain.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {

    //contains business logic

    //validate excel is in excel format
    public static boolean isValidExcelFormat(MultipartFile file){

        /*
        MultipartFile is an interface in Spring Framework used to handle file uploads in web applications. It represents an uploaded file received in a multipart request, typically sent via HTML forms with <input type="file"> fields.

        When a file is uploaded via a form in a Spring MVC application, Spring automatically binds the uploaded file to a MultipartFile object. This object provides methods to retrieve information about the file such as its name, content type, size, and to access its contents as an InputStream.
         */


        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        /*
        Object is a class and serves as the root of the class hierarchy in Java.
        Objects is a utility class containing static methods for working with objects, particularly for handling null values and performing common operations such as equality checks.

         */

        /*
        for an Excel file, it might return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" for XLSX files or "application/vnd.ms-excel" for older XLS files.
         */
    }

    public static List<Customer> getCustomerDataFromExcel(InputStream inputStream){
        /*
        In Java, when you need to read data from a source such as a file, network connection, or in-memory buffer, you can use an InputStream to facilitate the reading process. InputStream provides a set of methods for reading bytes of data from the source.
         */
        List<Customer> customers = new ArrayList<>();

        /*
        XSSFWorkbook is a class from the Apache POI library, specifically from the poi-ooxml module. It is used for working with Excel files in the Office Open XML (OOXML) format, which is the default format used by Microsoft Excel 2007 and later versions.

In Java Spring Boot applications (or any Java application), you can use XSSFWorkbook to create, read, modify, and write Excel files (.xlsx format).
         */


        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("customers");
            //customer is name of sheet

            int rowIndex = 0;
            //iterate through rows in the sheet
            for(Row row : sheet){
                //In Apache POI, the Row class represents a row in an Excel worksheet. It is part of the
                if(rowIndex == 0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Customer customer = new Customer();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 ->  customer.setCustomerId((int)cell.getNumericCellValue());
                        case 1 ->  customer.setFirstName(cell.getStringCellValue());
                        case 2 ->  customer.setLastName(cell.getStringCellValue());
                        case 3 ->  customer.setCountry(cell.getStringCellValue());
                        case 4 ->  customer.setContact((int)cell.getNumericCellValue());
                        default -> {
                            //keep it empty as of now.
                        }
                    }
                    cellIndex++;
                }
                customers.add(customer);


            }
        } catch (IOException e) {
            e.getStackTrace();
        }

        return customers;

    }
}
