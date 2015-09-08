package com.actsat.alpal.actsatprep;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.viewpagerindicator.TitlePageIndicator;

public class swipeable extends AppCompatActivity {

    private FragmentAdapter mAdapter;
    private TitlePageIndicator titleindicator;
    private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeable);
        pager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new FragmentAdapter(this, pager);

        titleindicator = (TitlePageIndicator) findViewById(R.id.titles);
        titleindicator.setViewPager(pager);
        titleindicator.setOnTouchListener(null);
        titleindicator.setOnClickListener(null);
        titleindicator.setOnPageChangeListener(mAdapter);

        mAdapter.addFragment(InfoFragment.newInstance("Information"));
        mAdapter.addFragment(LocationFragment.newInstance("Location"));
        mAdapter.addFragment(RequestMapFragment.newInstance(new GoogleMapOptions(), "Destination"));
        mAdapter.addFragment(ReviewFragment.newInstance("Review"));

        initializePageListeners(pager, prevButton, nextButton);
        pager.setOffscreenPageLimit(mAdapter.getCount() - 1);
    }


    private void initializePageListeners(final ViewPager pager, final Button prevButton, final Button nextButton) {
        nextButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                int pageNum = pager.getCurrentItem();
                switch (pageNum) {
                    case 0:
                        // Getting info
                        if (v.isEnabled() && validationCheck()) {
                            String[] info = ((InfoFragment) mAdapter.getItem(0)).getInfo();
                            walkRequest.setName(info[0]);
                            walkRequest.setUTEID(info[1]);
                            walkRequest.setPhoneNumber(info[2].replaceAll("[\\D]", ""));
                            walkRequest.setEmail(info[3]);
                            pager.setCurrentItem(1, true);
                            v.setEnabled(false);
                            prevButton.setEnabled(true);
                        }
                        break;
                    case 1:
                        // Getting location
                        LocationFragment locFrag = ((LocationFragment) mAdapter.getItem(1));
                        if(v.isEnabled() && locFrag.mDone) {
                            double[] coordinates = locFrag.getCoordinates();

                            walkRequest.setStartLocation(coordinates[0], coordinates[1]);
                            pager.setCurrentItem(2, true);
                            showHelpToast();
                            v.setEnabled(false);
                            prevButton.setEnabled(true);
                        } else {
                            Toast.makeText(getBaseContext(), "Please select a start address first", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 2:
                        // Getting destination
                        RequestMapFragment destFrag = ((RequestMapFragment) mAdapter.getItem(2));
                        if (destFrag.mPointPicked) {
                            double[] coordinates2 = destFrag.getCoordinates();
                            walkRequest.setEndLocation(coordinates2[0], coordinates2[1]);
                            ReviewFragment commentFrag = ((ReviewFragment) mAdapter.getItem(3));
                            commentFrag.populateFields(walkRequest);
                            pager.setCurrentItem(3, true);
                            ((Button) v).setText("Submit");
                            v.setEnabled(true);
                            prevButton.setEnabled(true);

                        } else {
                            Toast.makeText(getBaseContext(), "Please select a point", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3:
                        // Getting comments
                        ReviewFragment commentFrag = ((ReviewFragment) mAdapter.getItem(3));
                        walkRequest.setMessage(commentFrag.getComments());
                        mParseHandler.saveWalkInfo(walkRequest);
                        finish();

                    default:
                        break;
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int pageNum = pager.getCurrentItem();
                pager.setCurrentItem(pageNum - 1, true);
                switch (pageNum) {
                    case 1:
                        // Getting location
                        v.setEnabled(false);
                        nextButton.setEnabled(true);
                        break;
                    case 2:
                        // Getting destination
                        v.setEnabled(true);
                        nextButton.setEnabled(true);
                        break;
                    case 3:
                        // Getting comments
                        nextButton.setText("Next");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_swipeable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
