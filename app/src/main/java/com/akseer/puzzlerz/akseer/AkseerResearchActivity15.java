package com.akseer.puzzlerz.akseer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AkseerResearchActivity15 extends AppCompatActivity {





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.News:
                    startActivity(new Intent(AkseerResearchActivity15.this, NewsActivity4.class));
                    finish();
                    return true;
                case R.id.Market:

                    startActivity(new Intent(AkseerResearchActivity15.this, MarketsActivity6.class));
                    finish();
                    return true;

                case R.id.Akseer:

                    return true;

                case R.id.Broker:

                    startActivity(new Intent(AkseerResearchActivity15.this, BrokerActivity19.class));
                    finish();
                    return true;
            }
            return false;
        }

    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Disclaimer:
                startActivity(new Intent(AkseerResearchActivity15.this, DisclaimerActivity16.class));
                return true;
         /*   case R.id.PerformanceReviews:
                startActivity(new Intent(AkseerResearchActivity15.this, PR18Activity.class));
                return true;
            case R.id.additiondeletion:
                startActivity(new Intent(AkseerResearchActivity15.this, AdditionDeletion17.class));
                return true;*/
            case R.id.logout:
                startActivity(new Intent(AkseerResearchActivity15.this, LoginActivity1.class));
                finishAffinity();
                finish();

                return true;
            case R.id.Share:
//                startActivity(new Intent(NewsInsideActivity5.this, AdditionDeletion17.class));
                return true;
            case R.id.aboutus:
//                startActivity(new Intent(NewsInsideActivity5.this, AdditionDeletion17.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akseer_research15);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setSelectedItemId(R.id.Akseer);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    public void clickInsight(View view) {
        startActivity(new Intent(AkseerResearchActivity15.this, AkseerActivity7.class));
    }

    public void clickResearchCoverage(View view) {
        startActivity(new Intent(AkseerResearchActivity15.this, AkseerActivity7.class));}


    public void clickStockRating(View view) {
        startActivity(new Intent(AkseerResearchActivity15.this, StockRating10.class));
    }

    public void clickHCF(View view) {
        startActivity(new Intent(AkseerResearchActivity15.this, HCP9.class));
    }
}
