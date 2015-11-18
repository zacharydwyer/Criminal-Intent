package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

// Support version of an Activity that hosts a Fragment - FragmentActivity
public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

    @Override
    protected Fragment createFragment() {
        // return new CrimeFragment(); -- We no longer are just creating any old fragment, CrimeFragment needs info before it is created.

        // Retrieve the CrimeID from the Intent that started me
        UUID crimeID = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        // Return a new CrimeFragment that was created using its newInstance method
        return CrimeFragment.newInstance(crimeID);
    }

    public static Intent newIntent(Context packageContext, UUID crimeID) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        return intent;
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



A FragmentActivity has a FragmentManager. It is responsible for 1) managing your fragments and
 2) adding their Views to the activity's View hierarchy. So Fragments are just another form of a View,
 but they


 The fragment manager is responsible for calling the lifecycle methods of the fragments in its list,
 just like the Android OS's ActivityManager is responsible for calling the lifecycle methods of the
 activities. It calls its lifecycle methods in accordance to the lifecycle methods that are called on
 the activity that contains the fragment.

 */
