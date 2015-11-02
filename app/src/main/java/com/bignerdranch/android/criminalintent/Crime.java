package com.bignerdranch.android.criminalintent;

import java.util.UUID;

/**
 * Represents an office crime
 */
public class Crime {
    private UUID mId;
    private String mTitle;

    public Crime() {
        // Generate unique identifier
        mId = UUID.randomUUID();
    }

    // Getters and setters - UUID is generated so it doesn't use a setter.
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
