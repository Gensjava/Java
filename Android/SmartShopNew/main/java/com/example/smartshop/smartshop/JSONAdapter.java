package com.example.smartshop.smartshop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gens on 10.04.2015.
 */
public class JSONAdapter extends BaseAdapter {


    private ArrayList<ProductDual> objects;

    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;


    public JSONAdapter(Context context, LayoutInflater inflater, final ArrayList<ProductDual> poducts) {
        mContext = context;
        mInflater = inflater;
        mJsonArray = new JSONArray();

        objects = poducts;

        ///fillData();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check if the view already exists
        // if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(R.layout.product_dual, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
           //// holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.img_thumbnail);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.textView_itemOne);
            holder.authorTextView = (TextView) convertView.findViewById(R.id.textView_itemTwo);

            // hang onto this holder for future recyclage
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }
        // More code after this
        // Get the current book's data in JSON form
        ProductDual jsonObject = (ProductDual) getItem(position);

      // try {
           // String id = jsonObject.getString(Сonstants.TAG_PID);
            String wayImage = jsonObject.getProductOne().getWayImage();
           // String fullImage = Сonstants.url_main_way_image + wayImage;

           /// Log.i("JSONObject_JSONObject","  "+id+"  "+fullImage);
            ImageView imageViewOne = (ImageView) convertView.findViewById(R.id.imageView_itemOne);

            Picasso.with(mContext)
                    .load(wayImage)
                    .into(imageViewOne);

            ImageView imageViewTwo = (ImageView) convertView.findViewById(R.id.imageView_itemTwo);

            Picasso.with(mContext)
                    .load(wayImage)
                    .into(imageViewTwo);
        //} catch (JSONException e) {
        //    e.printStackTrace();
      //  }

        // See if there is a cover ID in the Object
       // if (jsonObject.has("cover_i")) {

            // If so, grab the Cover ID out from the object
           // String imageID = jsonObject.optString("cover_i");

            // Construct the image URL (specific to API)
            //String imageURL = IMAGE_URL_BASE + imageID + "-S.jpg";

            // Use Picasso to load the image
            // Temporarily have a placeholder in case it's slow to load
           // Picasso.with(mContext).load(imageURL).placeholder(R.drawable.ic_launcher).
                  //  into(holder.thumbnailImageView);


        //} else {

            // If there is no cover ID in the object, use a placeholder
//            holder.thumbnailImageView.setImageResource(R.drawable.ic_launcher);
       // }

        // Grab the title and author from the JSON
        String bookTitle = "";
        String authorName = "";

       /// Log.i("mJParser", "" + jsonObject.toString());

       // if (jsonObject.has("title")) {
       //     bookTitle = jsonObject.optString("title");
        //}

       // if (jsonObject.has("author_name")) {
       //     authorName = jsonObject.optJSONArray("author_name").optString(0);
       // }

        // Send these Strings to the TextViews for display
      //  holder.titleTextView.setText(bookTitle);
      //  holder.authorTextView.setText(authorName);

        return convertView;
    }

    // this is used so you only ever have to do
    // inflation and finding by ID once ever per View
    private static class ViewHolder {
        public ImageView thumbnailImageView;
        public TextView titleTextView;
        public TextView authorTextView;
    }

    public void updateData(JSONArray jsonArray) {
        // update the adapter's dataset
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }

}