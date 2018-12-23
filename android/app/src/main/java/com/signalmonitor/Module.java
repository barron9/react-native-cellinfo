package com.signalmonitor;

import android.content.Context;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import android.telephony.CellSignalStrength;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.SignalStrength;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Module extends ReactContextBaseJavaModule {

  private static final String DURATION_SHORT_KEY = "SHORT";
  private static final String DURATION_LONG_KEY = "LONG";
  TelephonyManager telManager = ( TelephonyManager )getReactApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

  public Module(ReactApplicationContext reactContext) {
    super(reactContext);

  }

    @Override
  public String getName() {
    return "ModuleEX";
  }

   @ReactMethod
  public void show(Promise promise) {

     List<NeighboringCellInfo>  neighCell = telManager.getNeighboringCellInfo();
     List<CellInfo> list=null;
     List<CellInfo> cellInfoList = telManager.getAllCellInfo();
   // Toast.makeText(getReactApplicationContext(), message, duration).show();
    String sonuc ="";
       WritableMap map = Arguments.createMap();
     for (final CellInfo cellInfo : cellInfoList) {

       // turn your data into Entry objects

       if (cellInfo.isRegistered()) {
         CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
         CellSignalStrength cellSignalStrengthGsm = ((CellInfoGsm) cellInfo).getCellSignalStrength();
         Integer asd= cellSignalStrengthGsm.getDbm();
sonuc += cellSignalStrengthGsm.getDbm() + " ";
       }else{
         CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
         CellSignalStrength cellSignalStrengthGsm = ((CellInfoGsm) cellInfo).getCellSignalStrength();
         Integer asd= cellSignalStrengthGsm.getDbm();

         sonuc += cellSignalStrengthGsm.getDbm() + " ";

       }
     }
       map.putString("data", sonuc);

       promise.resolve(map);

  }
    @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
    constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
    return constants;
  }


    @ReactMethod
    public void loadVideo(Promise promise) {
        try {
            Boolean videoLoaded = true; // could be any data type listed under https://facebook.github.io/react-native/docs/native-modules-android.html#argument-types
            if (videoLoaded) {
                promise.resolve(videoLoaded);
            }
        } catch (Exception e) {
            promise.reject("asd", e);
        }
    }
}