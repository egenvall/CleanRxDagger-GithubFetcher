package com.egenvall.githubmvp.domain;

import rx.Observable;

/**
 * Created by kWall on 2016-06-28.
 */

public interface Usecase<T> {
    Observable<T> execute();
}
