package com.example.a1474672.fragment2;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class JSONReader {

        private Context mContext;
        private Gson mGson;

        private static final String COMMENTS_FILE = "data.json";

        public JSONReader(Context c) {

            mGson = new GsonBuilder().create();
            mContext = c;
        }

        public shelf readFile() {
            return mGson.fromJson(getStringAsset(COMMENTS_FILE), shelf.class);
        }

    private String getStringAsset(String filename) {
        AssetManager assetManager = mContext.getAssets();
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        try {
            InputStream ins = assetManager.open(filename);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = ins.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
