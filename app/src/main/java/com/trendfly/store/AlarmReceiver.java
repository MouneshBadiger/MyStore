package com.trendfly.store;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AlarmReceiver extends BroadcastReceiver {
String phoneNumbers=null;
static int cnt=0;
    @Override
    public void onReceive(Context arg0, Intent arg1) {

        // For our recurring task, we'll just display a message
        Bundle bundle = arg1.getExtras();
        String pincodes = bundle.getString("pincodes");
        phoneNumbers=bundle.getString("phoneNumbers");



        if(pincodes!=null && !pincodes.isEmpty()){
            String[] pins=pincodes.split(",");
            for(String pin:pins){
                Toast.makeText(arg0, (++cnt)+":Checking slots on cowin website for:"+pin, Toast.LENGTH_SHORT).show();
                CowinSlotAsync1 cowin=new CowinSlotAsync1();
                cowin.execute(pin,arg0);
            }
        }



    }
    class CowinSlotAsync1 extends AsyncTask<Object, Void, Boolean> {
        public String alertMessage=null;
        @Override
        protected Boolean doInBackground(Object... objects) {
            try {
                String pincode=objects[0].toString();
                Context context= (Context) objects[1];
                int pin=Integer.parseInt(pincode);
                checkAvailabilityByPin(pin,context);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(this.alertMessage!=null && !this.alertMessage.isEmpty()){
                SmsManager smsManager = SmsManager.getDefault();
                String alertMsg=this.alertMessage;

                if(phoneNumbers!=null && !phoneNumbers.isEmpty()){
                    String[] phoneNs=phoneNumbers.split(",");
                    for(String phone:phoneNs){
                        smsManager.sendTextMessage(phone, null,this.alertMessage, null, null);
                        System.out.println("Sent sms successfully"+this.alertMessage);
                        //Toast.makeText(context, "Sent sms successfully to:"+phone, Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }

        public void checkAvailabilityByPin(int pincode, Context context) throws IOException, JSONException {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String today= formatter.format(date);
            //today="08-06-2021";
            System.out.println(today);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+pincode+"&date="+today)
                    .method("GET", null)
                    .addHeader("Accept-Language", "hi_IN")
                    .addHeader("Accept", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            String respJson=response.body().string();
            JSONObject jSONObject=new JSONObject(respJson);
            JSONArray jSONArray=jSONObject.getJSONArray("sessions");
            int count=0;
            for(int i=0;i<jSONArray.length();i++) {
                JSONObject session=jSONArray.getJSONObject(i);
                int ageLimit=session.getInt("min_age_limit");
                if(ageLimit==18) {
                    if(session.getInt("available_capacity_dose1")>0) {
                        System.out.println((++count)+" =========================================");
                        StringBuilder str=new StringBuilder("");
                        str.append("Slot available for "+ageLimit+"+ "+session.getString("vaccine"));
                        str.append(" vaccine at "+session.getString("name")+" ("+pincode+")");
                        str.append(" Dose 1 available:"+session.getInt("available_capacity_dose1"));
                        //str.append("\nSession ID: "+session.getString("session_id"));
                        //str.append("\nVaccine name:"+session.getString("vaccine"));
                        //str.append("\nSlots:"+session.getJSONArray("slots").toString());
                        //str.append("\nDose 1 available:"+session.getInt("available_capacity_dose1"));
                        //str.append("\nDose 2 available:"+session.getInt("available_capacity_dose1"));
                        this.alertMessage=str.toString();
                        //Toast.makeText(context, "SLOT FOUND:: "+ this.alertMessage, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            if(count==0) {
                //Toast.makeText(context, ":::NO SLOTS AVAILABLE TO BOOK::::", Toast.LENGTH_SHORT).show();
                System.out.println(":::NO SLOTS AVAILABLE TO BOOK::::");
            }

        }
    }

}

