package com.bignerdranch.android.criminalintent;

    import android.os.Bundle;
    import android.support.annotation.Nullable;
    import android.support.v4.app.Fragment;
    import android.text.Editable;
    import android.text.TextWatcher;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.EditText;

// A controller that interacts with model and view objects. Like you would with a traditional Activity.
public class CrimeFragment extends Fragment {

    // Each CrimeFragment has an associated crime.
    private Crime mCrime;

    // Widgets inside of this fragment
    private EditText mCrimeTitleField;

    // This method is called by the parent activity's FragmentManager
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This fragment was just created. Create a new corresponding Crime object to go with it
        mCrime = new Crime();

    }

    // Nullable means that this method is permitted to return null. This is because fragments can
    //   lack a UI.

    // onCreateView is a method that expects a view that is created from the fragment xml layout.
    // It is called by the hosting activity - it needs this view so it can be used to draw it in the Activity.
    // It is public as opposed to protected because it needs to be called by the hosting activity.
    // This is also where you wire up your widgets. This is because your widgets aren't even available in onCreate() -
    //   they become available after the layout is inflated to become a view.

    // LayoutInflater is provided for you so you can inflate your layout
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Create the fragment view. Needs the root element (container) and whether to attach this view to the activity.
        View fragmentView = inflater.inflate(R.layout.crime_fragment, container, false);

        // Add handle to widgets - just like in Activity.onCreate() but now you call View.findViewById.
        //   In Activity.findViewById() it's a convenience method that calls View.findViewById behind the scenes.
        //   The Fragment class doesn't have a correponding convenience method, so you have to call the real thing.
        mCrimeTitleField = (EditText) fragmentView.findViewById(R.id.crime_title_field);


        // Add listeners
        mCrimeTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Set the title of the Crime object to whatever the user has typed in
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Doing nothing with this
            }
        });



        return fragmentView;
    }
}

// You don't inflate  the fragment's view in onCreate().

/*
onCreate()
The system calls this when creating the fragment. Within your implementation,
you should initialize essential components of the fragment that you want to retain
when the fragment is paused or stopped, then resumed.

onCreateView()
The system calls this when it's time for the fragment to draw its user interface for the first time.
To draw a UI for your fragment, you must return a View from this method that is
the root of your fragment's layout.
You can return null if the fragment does not provide a UI.

onPause()
The system calls this method as the first indication that the user is
leaving the fragment (though it does not always mean the fragment is being destroyed).

This is usually where you should commit any changes that should be persisted beyond the current user session
(because the user might not come back).



Fragments cannot put their own views on the screen.


 */
