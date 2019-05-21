//package com.example.ndsp.DataBase;
//
//import android.annotation.SuppressLint;
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.mounts.kyaw.ndsp.pojo.OrderBook;
//
//import java.util.ArrayList;
//
//public class OrderItemDatabaseManagement {
//
//    private Context context;
//    private SQLiteDatabase database;
//
//    //constructor
//    public OrderItemDatabaseManagement(Context context) {
//        this.context = context;
//    }
//    //database Open
//    public void dbOpen() {
//        OrderListHelper orderListHelper = new OrderListHelper(context);
//        database = orderListHelper.getWritableDatabase();
//    }
//
//    //database Close
//    public void dbClose() {
//        database.close();
//    }
//
//    //adding OrderBook
//    public void addOrderItem(OrderBook orderBook){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(OrderListHelper.BOOK_ID,orderBook.getBook_id());
//        contentValues.put(OrderListHelper.BOOK_NAME,orderBook.getBook_name());
//        contentValues.put(OrderListHelper.BOOK_QTY,orderBook.getBook_qty());
//        contentValues.put(OrderListHelper.BOOK_PRICE,orderBook.getBook_price());
//
//        database.insert(OrderListHelper.TB_NAME,null,contentValues);
//
//    }
//    //getting All Data
//    public ArrayList<OrderBook> getAllItems() {
//
//        ArrayList<OrderBook> orderBooks = new ArrayList<>();
//        String selectQuery = "SELECT  * FROM " + OrderListHelper.TB_NAME;
//
//        @SuppressLint("Recycle") Cursor cursor = database.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//
//            do {
//
//                OrderBook orderBook = new OrderBook();
//                orderBook.setBook_id(cursor.getString(1));
//                orderBook.setBook_name(cursor.getString(2));
//                orderBook.setBook_price(cursor.getString(3));
//                orderBook.setBook_qty(cursor.getString(4));
//                orderBooks.add(orderBook);
//            } while (cursor.moveToNext());
//        }
//        return orderBooks;
//    }
//
//    //update Count
//    public void updateQuantity(String newQty){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(OrderListHelper.BOOK_QTY,newQty);
//
//        database.update(OrderListHelper.TB_NAME,contentValues, OrderListHelper.BOOK_QTY + " = '" + newQty + "'",null);
//    }
//
//    //delete row
//    public void deleteOrderItem(String bookId){
//        database.delete(OrderListHelper.TB_NAME, OrderListHelper.BOOK_ID + " = '" + bookId + "'",null);
//    }
//
//    //delete all items
//    public void deleteAllItems(){
//        database.execSQL("delete from "+ OrderListHelper.TB_NAME);
//    }
//
//
//    // Getting items Count
//    public int getItemsCount() {
//
//        String countQuery = "SELECT  * FROM " + OrderListHelper.TB_NAME;
//
//        Cursor cursor = database.rawQuery(countQuery, null);
//        int cnt = cursor.getCount();
//        cursor.close();
//        return cnt;
//    }
//
//
//
//    //database table
//    private class OrderListHelper extends SQLiteOpenHelper {
//
//        static final String DB_NAME = "Order_List";
//        static final int DB_VERSION = 1;
//        static final String TB_NAME = "order_list_table";
//
//        static final String ITEM_ID = "order_item_id";
//        static final String BOOK_ID = "book_id";
//        static final String BOOK_NAME = "book_name";
//        static final String BOOK_QTY = "quantity";
//        static final String BOOK_PRICE = "price";
//
//        private static final String SQL_CREATE_ENTRIES =
//                "CREATE TABLE " + TB_NAME + " (" +
//                        ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        BOOK_ID + " VARCHAR(225), " +
//                        BOOK_NAME + " VARCHAR(225), " +
//                        BOOK_PRICE + " VARCHAR(225), " +
//                        BOOK_QTY + " VARCHAR(225));";
//
//
//        private OrderListHelper(Context context) {super(context, DB_NAME, null, DB_VERSION);}
//
//        @Override
//
//        public void onCreate(SQLiteDatabase db) {db.execSQL(SQL_CREATE_ENTRIES);}
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
//            onCreate(db);
//        }
//    }
//
//}
