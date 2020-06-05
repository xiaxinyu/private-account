package com.account.core.tool;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Summer.Xia on 08/27/2014.
 */
public class DataStructureTool {
	/**
	 * 判断集合是否为空，所有List都能判断
	 * @param collection
	 * @return
	 * @author 夏昕雨
	 * @version V0.1.0
	 * @since 2014-11-22
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Collection collection){
		boolean flag = false;
		if(collection != null){
			Iterator iterator = collection.iterator();
			flag = iterator.hasNext();
		}
		return flag;
	}
	
	/**
	 * 判断Map是否为空，所有Map都能判断
	 * @param map
	 * @return
	 * @author   夏昕雨
	 * @version  2015年6月17日
	 * @since    V0.1.0
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Map map){
		boolean flag = false;
		if(map != null && map.size() > 0){
			flag = true;
		}
		return flag;
	}
}
