package com.example.zul_mizini.bangunpc1;

import android.app.Activity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdManager {
    // Static fields are shared between all instances.
    private static InterstitialAd interstitialAd;

    private static boolean isInterAdsShowed = false;
    private Activity activity;
    private String AD_UNIT_ID;

    AdManager(Activity activity, String AD_UNIT_ID) {

        this.activity = activity;
        this.AD_UNIT_ID = AD_UNIT_ID;
        createAd();
    }

    void createAd() {
        // Create an ad.
        interstitialAd = new InterstitialAd(activity);
        interstitialAd.setAdUnitId(AD_UNIT_ID);

        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //.addTestDevice(TEST_DEVICE_ID)
                .build();

        // Load the interstitial ad.
        interstitialAd.loadAd(adRequest);
    }

    static InterstitialAd getAd() {
        if (interstitialAd != null && interstitialAd.isLoaded() && !isInterAdsShowed) {
            isInterAdsShowed = true;
            return interstitialAd;
        } else return null;
    }
}