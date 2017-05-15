package com.zjrfid.materialsmanage.acdbentity;

import java.util.Map;

/**
 * Created by Administrator on 2017/2/10 0010.
 */
public class InBound {
    private int audit;//审核标志位
    private InBoundHeader inBoundHeader;
    private Map<String,Material> materialMap;
}
