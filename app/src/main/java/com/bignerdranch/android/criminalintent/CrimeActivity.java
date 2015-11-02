package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

// Support version of an Activity that hosts a Fragment - FragmentActivity
public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
    }


}

/*
Notes

Fragments work on behalf of Activities. A fragment's lifecycle corresponds to an Activity's lifecycle.
The fragment handles the activity's work.
Fragment lifecycle methods are called by the hosting activity, not the OS. The OS knows nothing about the fragments.
Fragments are an activity's intenral business.


To host a UI fragment, an Activity must:
- Define a spot in its layout for the fragment's View
- Manage the lifecycle of the fragment instance

Think of fragments like sub-activities that you can reuse in other activities.


the fragment's lifecycle is directly affected by the host activity's lifecycle.

For example, when the activity is paused, so are all fragments in it,
and when the activity is destroyed, so are all fragments.

However, while an activity is running (it is in the resumed lifecycle state),
you can manipulate each fragment independently, such as add or remove them.

You will add a UI fragment in the hosting Activity's code, but you will still need to make a spot
for the fragment's view in the activity's view hierarchy.


Creating a UI fragment is done the same way you create an activity:
- Make the layout.xml file with widgets
- Create the class (in this case, a fargment) and set its view to the layout that you just made
- Wire up the widgets inflated from the layout.xml inside your code.

 */
