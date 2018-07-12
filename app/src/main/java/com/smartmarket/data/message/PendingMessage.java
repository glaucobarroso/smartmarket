package com.smartmarket.data.message;

/**
* Created by Glauco on 27/05/2018.
*/

import java.util.List;

public class PendingMessage {

    public String userId;
    public List<Result> results = null;

    public class Result {
        public String order_id;
        public Integer count;
    }

}
