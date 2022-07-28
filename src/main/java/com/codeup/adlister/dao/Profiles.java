package com.codeup.adlister.dao;

import com.codeup.adlister.models.Profile;

import java.util.List;

public interface Profiles {

    List<Profile> all();

    Long insert(Profile profile);

}
