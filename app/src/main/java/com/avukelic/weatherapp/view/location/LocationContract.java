package com.avukelic.weatherapp.view.location;

import com.avukelic.weatherapp.model.LocationWrapper;

import java.util.List;

/**
 * Created by avukelic on 02-Oct-18.
 */
public interface LocationContract {

    interface View {

    }

    interface Presenter {
        void setView(LocationContract.View view);
    }
}
