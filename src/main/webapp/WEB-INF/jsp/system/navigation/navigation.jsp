<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "Private Account"); %>

<div id="navigationDiv" class="easyui-tabs" data-options="tools:'#tab-tools',fit:true,border:false">
	 <div  data-options="title:'Home'">
	 	<iframe id="frameIndex" name="frameIndex"  frameborder="0" src="<%=request.getContextPath() %>/account/index.html" width="99%" height="99%"  ></iframe>
    </div> 
</div>
<div id="tab-tools">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="removePanel()"></a>
</div> 
<script type="text/javascript">
	var navigationDiv = "#navigationDiv";
    /** 初始化页面 */
    function initPanel(title,url){
    	var tab = $(navigationDiv).tabs('getTab',title);  
    	if(tab != undefined && tab != null){
    		$(navigationDiv).tabs('select',title);
    	}else{
    		addPanel(title,url);
    	}
    }
    /** 添加面板 */
    function addPanel(title,url){
        var fullUrl = "<%=request.getContextPath()%>/" + url;
        console.info(fullUrl)
    	var iframe = '<iframe scrolling="no" id="frame" name="frame"  src="' + fullUrl + '" width="100%" height="100%" frameborder="0"></iframe>';
    	var tab = {
            title: title,
            content:iframe,
            closable: true,
            tools:[{    
                iconCls:'icon-reload',    
                handler:function(){    
                	refresh();  
                }    
            }]    
        };
        $(navigationDiv).tabs('add',tab);
    }
    /** 刷新面板数据 */
    function refresh(){
    	var tab = $(navigationDiv).tabs('getSelected');    
    	var panel = tab.panel('panel');
		var frame = panel.find('iframe');
		try {
			if (frame.length > 0) {
				for ( var i = 0; i < frame.length; i++) {
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
					frame[i].src = frame[i].src;
				}
				if ($.browser.msie) {//ie浏览器下
					CollectGarbage();//释放内存
				}
			}
		} catch (e) {
		}
    }
    /** 关闭页面 */
    function removePanel(){
        var tab = $(navigationDiv).tabs('getSelected');
        if($(tab).panel('options').title != 'Home'){
        	if (tab){
                var index = $(navigationDiv).tabs('getTabIndex', tab);
                $(navigationDiv).tabs('close', index);
            }
        }
    }
</script>    