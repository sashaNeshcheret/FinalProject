package ua.simpleproject;

public class MainClass {


   // public static void main(String[] args) throws SQLException {
        //try {
            //connection = ConnectionPool.getConnection();
        /*} catch (SQLException e) {
            System.out.println("Failed connection");
            e.printStackTrace();
            return;
        }
        Date curentTime = new Date();
        System.out.println(curentTime);
        DAOFactory daoFactory = DAOFactory.getDaoFactory();
        DAOStock daoStock = daoFactory.getDaoStock();
        DAOProduct daoProduct = daoFactory.getDaoProduct();
*/
/*        List<CurrentCheque> list = new ArrayList<>();
        try(Connection connection = ConnectionWithOutPool.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cash_register.current_check WHERE current_check.id > 0");
            int id=0, productId=0, userId=0;
            BigDecimal count;
            BigDecimal resultPrice;
            while (resultSet.next()){
                id = resultSet.getInt(1);
                System.out.print("id = " + id);
                productId = resultSet.getInt(2);
                System.out.print(", product_id = " + productId);
                userId = resultSet.getInt(3);
                System.out.print(", user_id = " + userId);
                count = resultSet.getBigDecimal(4);
                System.out.print(", count = " + count);
                resultPrice = resultSet.getBigDecimal(5);
                System.out.print(", result price = " + resultPrice);
                System.out.println();
                CurrentCheque currentCheque = new CurrentCheque(id, productId, userId, count, resultPrice);
                System.out.println("kkkkkkkkkkkkkk" + currentCheque.getProductID());
                System.out.println("kkkkkkkkkkkkkk" + currentCheque.getCount());
                System.out.println("ffkkkkkkkkkk" + currentCheque.getResultPrice());
                list.add(currentCheque);

            }
        } catch (SQLException sqlEx) {
            System.out.println("Mistake in getting opened list");
            sqlEx.printStackTrace();
        }
        List<CurrentCheque> listCheck = list;

        for(CurrentCheque q: listCheck){
            System.out.println("zzzzzzzzz" + q.getProductID());
            System.out.println("zzzzzzzzz" + q.getCount());
            System.out.println("zzzzzzzzz" + q.getResultPrice());
        }

        List<ProductCurrentCheck>  listProductsInCheck = new ArrayList<>();


        for(CurrentCheque currentCheque : listCheck){
            Product product1 = new Product();
            ResultSet resultSet = null;

            try(Connection connection = ConnectionWithOutPool.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cash_register.products WHERE id = ?");
                preparedStatement.setInt(1, currentCheque.getProductID());
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    product1.setId(resultSet.getInt(1));
                    product1.setCodeProduct(resultSet.getInt(2));
                    product1.setName(resultSet.getString(3));
                    product1.setCountable(resultSet.getBoolean(4));
                    product1.setPriceForOne(resultSet.getBigDecimal(5));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Product product = product1;
            ProductCurrentCheck productCurrentCheck = new ProductCurrentCheck();
            productCurrentCheck.setNameProduct(product.getName());
            System.out.println(" current check - "+ productCurrentCheck.getNameProduct());
            productCurrentCheck.setCodeProduct(product.getCodeProduct());
            productCurrentCheck.setNumberOfProduct(currentCheque.getCount());
            productCurrentCheck.setPriceForOne(product.getPriceForOne());
            productCurrentCheck.setResultPrice(currentCheque.getResultPrice());

            System.out.println("qqqqqqqqq" + productCurrentCheck.getNameProduct());
            System.out.println("qqqqqqqqq" + productCurrentCheck.getNumberOfProduct());
            System.out.println("qqqqqqqqq" + productCurrentCheck.getResultPrice());

            listProductsInCheck.add(productCurrentCheck);

        }
        for(ProductCurrentCheck q: listProductsInCheck){
            System.out.println("listProductsInCheck" + q.getNameProduct());
            System.out.println("listProductsInCheck" + q.getNumberOfProduct());
            System.out.println("listProductsInCheck" + q.getResultPrice());
        }
*/
        /*try {
            productCurrentCheck = AddProductLogic.addProduct(name, codeStr, numberStr);
        } catch (MyException e) {
            e.printStackTrace();
        }
        System.out.println(productCurrentCheck.getCodeProduct());
        System.out.println(productCurrentCheck.getNameProduct());
        System.out.println(productCurrentCheck.getNumberOfProduct());
        System.out.println(productCurrentCheck.getPriceForOne());
        System.out.println(productCurrentCheck.getResultPrice());
  */      //Stock stock = new Stock(26, 200);
        //daoStock.add(connection, stock);
        //Product product = new Product(1, 2003, "яблука", false);//
        //daoProduct.add(connection, product);
            //daoStock.addByCode(connection, product, 3000);
            //daoStock.addByCode(connection, 2009, 1000);
                        //daoStock.addByName(connection, product, 150);
                        //daoStock.addByName(connection, "груша", 444);
         /*   stock = new Stock(2009, 150);
            daoStock.add(connection, stock);
            stock = new Stock(2001, 60);
            daoStock.add(connection, stock);
            stock = new Stock(2004, 150);
            daoStock.add(connection, stock);
            daoStock.show(connection);

         DAOUsers daoUsers = DAOFactory.getDaoFactory().getDaoUsers();
         User user = daoUsers.getFromUsersByLogin(connection, "user");
        System.out.println(user.getPassword());
*/
        //DAOCurrentCheque daoCurrentCheck = daoFactory.getDaoCurrentCheque();
        //DAOUsers daoUsers = daoFactory.getDaoUsers();
        //daoUsers.addToUsers(connection, "kacup", "Valja", "zzz", 2);
        //daoUsers.deleteFromUsers(connection, "zzz");

        /*daoCurrentCheck.openCheque(connection);
        daoCurrentCheck.addToCurrentCheckByCode(connection,2001,1, 3);
        daoCurrentCheck.addToCurrentCheckByCode(connection,2002,1, 4);
        daoCurrentCheck.addToCurrentCheckByCode(connection,2003,1, 1);
        daoCurrentCheck.closeCheque(connection, 1);*/

    /*    try{
            Thread.sleep(1000*10);
        }catch(Exception ex){

        }
        CurrentCheque currentCheck = new CurrentCheque
                (1, 1,4, 100, 400);
        daoCurrentCheck.add(connection, currentCheck);
        CurrentCheque currentCheck1 = new CurrentCheque
                (1, 2,1, 50, 50);
        daoCurrentCheck.add(connection, currentCheck1);
        CurrentCheque currentCheck2 = new CurrentCheque
                (1, 3,4, 20, 80);
        daoCurrentCheck.add(connection, currentCheck2);
        CurrentCheque currentCheck3 = new CurrentCheque
                (1, 2,1, 50, 50);
        daoCurrentCheck.add(connection, currentCheck3);
        daoCurrentCheck.show(connection);
        daoCurrentCheck.closeCheque(connection);
*/
  //  }
}
