package com.account.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class DataStructureTool {
    public static boolean isNotEmpty(Collection collection){
        boolean flag = false;
        if(collection != null){
            Iterator iterator = collection.iterator();
            flag = iterator.hasNext();
        }
        return flag;
    }

    public static boolean isNotEmpty(Map map){
        boolean flag = false;
        if(map != null && map.size() > 0){
            flag = true;
        }
        return flag;
    }
}
