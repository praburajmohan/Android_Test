package com.cts.androidtest.util;

import android.text.TextUtils;

import com.cts.androidtest.model.FactsBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParserUtil {
    public static String title = "";

    private static final String DATA_NOT_AVAILABLE = "No data available";
    private static final String IMAGE_URL = "http://3.bp.blogspot.com/__mokxbTmuJM/RnWuJ6cE9cI/AAAAAAAAATw/6z3m3w9JDiU/s400/019843_31.jpg";

    /**
     * JsonParserUtil class used to Make factsList from JSONObject
     */
    public static ArrayList<FactsBean> jsonParsing(String resultJSON){
        ArrayList<FactsBean> factsList = null;

        try {
            JSONObject jObject = new JSONObject(resultJSON);
            title = jObject.getString("title");
            JSONArray jsonArray = jObject.getJSONArray("rows");
            factsList = new ArrayList();
            FactsBean factsBean;

            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jo_inside = jsonArray.getJSONObject(index);
                factsBean = new FactsBean();

                String listTitle = jo_inside.getString("title");
                String description = jo_inside.getString("description");
                String imageURL = jo_inside.getString("imageHref");

                if (!TextUtils.isEmpty(listTitle) && !listTitle.equalsIgnoreCase("null")) {
                    factsBean.setTitle(listTitle);
                }else{
                    factsBean.setTitle(DATA_NOT_AVAILABLE);
                }

                if (!TextUtils.isEmpty(description) && !description.equalsIgnoreCase("null")) {
                    factsBean.setDescription(description);
                }else{
                    factsBean.setDescription(DATA_NOT_AVAILABLE);
                }

                if (!TextUtils.isEmpty(imageURL) && !imageURL.equalsIgnoreCase("null")) {
                    factsBean.setImageURL(imageURL);
                }else{
                    factsBean.setImageURL(IMAGE_URL);
                }

                factsList.add(factsBean);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return factsList;
    }
}
