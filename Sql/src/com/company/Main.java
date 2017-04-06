package com.company;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static final String HOST="10.0.11.49";



    public static void main(String[] args) {
        //Connection connection=null;

        Map<Integer,Customer> listOrders=new HashMap();
        Map<Integer,Customer> listCustomers=new HashMap();
        float max=0;
        Customer customerKey=null;

        try (Connection connection=DB.getConnection()){
            PreparedStatement statement=connection.prepareStatement(
                    "SELECT CUSTOMER_ID, COMPANY_NAME FROM CUSTOMERS"
            );
            try(ResultSet resultSet=statement.executeQuery()){
                while (resultSet.next()){
                    listCustomers.put(resultSet.getInt(1), new Customer(resultSet.getString(2)));
                }
            }
            statement=connection.prepareStatement(
                    "SELECT ORDER_ID,CUSTOMER_ID FROM ORDERS "
            );
            try(ResultSet resultSet=statement.executeQuery()) {
                while (resultSet.next()) {
                    listOrders.put(resultSet.getInt(1),listCustomers.get(resultSet.getInt(2))); // הזמנה אחת מתוך ההזמנות של לקוח
                }
            }
            statement=connection.prepareStatement(
                    "SELECT ORDER_ID,UNIT_PRICE*QUANTITY*(1-DISCOUNT) FROM ORDER_DETAILS"
            );
            try(ResultSet resultSet=statement.executeQuery()){
                while (resultSet.next()){
                    Customer customer=listOrders.get(resultSet.getInt(1));
                    if (resultSet.getFloat(2)>customer.getTotalOrderSum())
                        customer.setTotalOrderSum(resultSet.getInt(2));
                    if (resultSet.getFloat(2)>max){
                        max=resultSet.getFloat(2);
                        customerKey=customer;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(customerKey.getCompanyName());
    }
}


/*
class Sapir implements Closeable{
    @Override
    public void close() throws IOException {
        System.out.println("in close of Sapir");
    }
}*/
