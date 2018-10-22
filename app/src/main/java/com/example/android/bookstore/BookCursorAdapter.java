package com.example.android.bookstore;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.android.bookstore.data.BookContract.BookEntry;

public class BookCursorAdapter extends CursorAdapter {
    private final CatalogActivity catalogActivity;

    public BookCursorAdapter(CatalogActivity context, Cursor c) {
        super(context, c, 0 /* flags */);
        this.catalogActivity = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        Button sellButton = (Button) view.findViewById(R.id.book_sell_button);

        int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANTITY);

        final int id = cursor.getInt(idColumnIndex);
        String bookName = "Book title: " + cursor.getString(nameColumnIndex);
        float price = cursor.getFloat(priceColumnIndex);
        String bookPrice = String.format("%s %.02f", "Price: ",price);
        final int quantity = cursor.getInt(quantityColumnIndex);
        String bookQuantity = "Quantity: " + String.valueOf(quantity);

        nameTextView.setText(bookName);
        priceTextView.setText(bookPrice);
        quantityTextView.setText(bookQuantity);

        sellButton.setOnClickListener(new View.OnClickListener() {
            int updatedQuantity = quantity - 1;

            @Override
            public void onClick(View v) {
                catalogActivity.clickOnSale(id, updatedQuantity);
            }
        });
    }
}
