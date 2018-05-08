package com.android.mvp_frame.data.repository;

import com.android.mvp_frame.data.api.GankIoService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class GankIoRepository {

    private GankIoService mService;

    @Inject
    public GankIoRepository(GankIoService commonService) {
        this.mService = commonService;
    }

}
