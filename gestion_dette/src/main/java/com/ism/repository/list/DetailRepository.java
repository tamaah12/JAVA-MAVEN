package com.ism.repository.list;

import java.util.ArrayList;
import java.util.List;

import com.ism.entities.Detail;

public class DetailRepository {
    private List<Detail> details = new ArrayList<>();

    public void insert(Detail detail) {
        details.add(detail);
    }

    public List<Detail> selectAll() {
        return details;
    }
}
