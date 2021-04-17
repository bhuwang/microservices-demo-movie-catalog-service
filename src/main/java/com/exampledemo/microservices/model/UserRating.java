/**
 * 
 */
package com.exampledemo.microservices.model;

import java.util.List;

/**
 * @author bhuwangautam
 *
 */
public class UserRating {

    private List<Rating> userRatings;

    public List<Rating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<Rating> userRatings) {
        this.userRatings = userRatings;
    }

}
