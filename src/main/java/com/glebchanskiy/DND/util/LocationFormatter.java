package com.glebchanskiy.DND.util;

import com.glebchanskiy.DND.models.Location;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class LocationFormatter implements Formatter<Location> {
    @Override
    public Location parse(String str_id, Locale locale) throws ParseException {
        Location location = new Location();
        int id = Integer.parseInt(str_id);
        location.setId(id);
        return location;
    }

    @Override
    public String print(Location object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
