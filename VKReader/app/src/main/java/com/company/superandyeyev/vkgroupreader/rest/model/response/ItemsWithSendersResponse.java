package com.company.superandyeyev.vkgroupreader.rest.model.response;

import com.company.superandyeyev.vkgroupreader.model.Group;
import com.company.superandyeyev.vkgroupreader.model.Owner;
import com.company.superandyeyev.vkgroupreader.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DIMON on 02.10.2017.
 */

public class ItemsWithSendersResponse<T> extends BaseItemResponse<T> {
    public List<Profile> profiles = new ArrayList<>();
    public List<Group> groups = new ArrayList<>();

    public List<Profile> getProfiles() {
        return profiles;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Owner> getAllSenders() {
        List<Owner> all = new ArrayList<>();
        all.addAll(getProfiles());
        all.addAll(getGroups());
        return all;
    }

    public Owner getSender(int id) {
        for (Owner owner : getAllSenders()) {
            if (owner.getId() == Math.abs(id)) {
                return owner;
            }
        }
        return null;
    }
}
