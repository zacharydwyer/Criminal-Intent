package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

// Support version of an Activity that hosts a Fragment - FragmentActivity
public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);

        // Get a handle to the fragment manager of this activity
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Try to get a fragment within the fragmentManager that has the same id of fragment_container

        // Remember, fragments are identified using the container they are in. If you add multiple fragments,
        //   you would create multiple separate containers for each fragment, each with a unique ID.
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        // If there wasn't a fragment there

        //   A fragment could be there already - onCreate is called whenever the activity is re-created
        //      but the FragmentManager automatically saves out its list of fragments. When the activity
        //      is re-created, the new FragmentManager retrieves the list and re-creates the listed
        //      fragments to make everything as it was before.
        if (fragment == null) {

            // Create a brand new CrimeFragment
            fragment = new CrimeFragment();

            // Begin a transaction with the fragment manager
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)         // Add the new fragment in the specified container with the container view ID
                    .commit();                                      // Commit the change
        }

        // The FragmentManager identifies its fragments by the resource ID of its containers.

        // There are two reasons why you pass in the resource ID of the fragment container:
        // 1) Tells where the Fragment should appear
        // 2) Is used as a unique identifier for the fragment in the FragmentManager's list.

        // Notice
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
