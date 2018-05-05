package com.opensource.app.persistance;

import java.util.List;

import com.opensource.app.models.Weight;

public interface WeightsPersistance{
    List<Weight> getWeightsForUserId();

    List<Weight> getWeightsForUserIdInSpecificDay();

    Weight getLatestWeightForUserId();

    Weight addNewWeight();

    void deleteWeight();
}