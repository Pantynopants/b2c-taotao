<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>淘淘商城后台管理系统</title>


<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<script type="text/javascript"  src="js/bootstrap.js" ></script>

<!-- <link rel="stylesheet" type="text/css" href="css/bootstrap-treeview.css" /> -->
<!-- MetisMenu CSS -->
    <link href="css/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="css/FontAwesome/css/font-awesome.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
 <!-- <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/bootstrap/tree.css" /> -->
<!-- <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/bootstrap/menu.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/bootstrap/menubutton.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/bootstrap/panel.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/bootstrap/linkbutton.css" />
 <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/bootstrap/tree.css" />  -->

<link rel="stylesheet" type="text/css" href="css/taotao.css" />

<!-- <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-treeview.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-treeview.min.css" />
<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css" href="css/normalize.css" /> -->
 <!-- Metis Menu Plugin JavaScript -->
 
     <!-- <script src="js/metisMenu.min.js"></script>  -->

    
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>

    <!-- Custom Theme JavaScript -->
    <!-- <script src="js/sb-admin-2.js"></script> -->


<!-- <script type="text/javascript" src="js/bootstrap-treeview.js"></script>
<script type="text/javascript" src="js/bootstrap-treeview.min.js"></script> -->
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
  });
  
});

// console.log("ok")
</script>
</head>
<body class="easyui-layout">
       <!-- <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>商品管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'item-add'}">新增商品</li>
	         		<li data-options="attributes:{'url':'item-list'}">查询商品</li>
	         		<li data-options="attributes:{'url':'item-param-list'}">规格参数</li>
	         	</ul>
         	</li>
         	<li>
         		<span>网站内容管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'content-category'}">内容分类管理</li>
	         		<li data-options="attributes:{'url':'content'}">内容管理</li>
	         	</ul>
         	</li>
         </ul>
    </div> 

        
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	
		    </div>
		  </div>
    </div>   -->
 <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">neusoft.com</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
            <ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
                    <li>
                      <span>商品管理</span>
                      <ul>
                        <li data-options="attributes:{'url':'item-add'}"> 新增商品</li>
                        <li data-options="attributes:{'url':'item-list'}"> 查询商品</li>
                        <li data-options="attributes:{'url':'item-param-list'}"> 规格参数</li>
                      </ul>
                    </li>
                    <li>
                      <span>网站内容管理</span>
                      <ul>
                        <li data-options="attributes:{'url':'content-category'}"> 内容分类管理</li>
                        <li data-options="attributes:{'url':'content'}"> 内容管理</li>
                      </ul>
                    </li>
         </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div data-options="region:'center',title:''" style="overflow: scroll;min-height:700px;">
          <div id="tabs" class="easyui-tabs">
            <div title="首页" style="padding:20px;">
                  
            </div>
		      </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- <div class="row">
        <hr>
        <h2>Data</h2>
        <div class="col-sm-4">
          <h2>JSON Data</h2>
          <div id="treeview12" class=""></div>
        </div>        
    </div> -->
        
		<!-- <script type="text/javascript">

	  		$(function() {


        var json = '[' +
          '{' +
            '"text": "Parent 1",' +
            '"nodes": [' +
              '{' +
                '"text": "Child 1",' +
                '"nodes": [' +
                  '{' +
                    '"text": "Grandchild 1"' +
                  '},' +
                  '{' +
                    '"text": "Grandchild 2"' +
                  '}' +
                ']' +
              '},' +
              '{' +
                '"text": "Child 2"' +
              '}' +
            ']' +
          '},' +
          '{' +
            '"text": "Parent 2"' +
          '},' +
          '{' +
            '"text": "Parent 3"' +
          '},' +
          '{' +
            '"text": "Parent 4"' +
          '},' +
          '{' +
            '"text": "Parent 5"' +
          '}' +
        ']';

var $tree = $('#treeview12').treeview({
          data: json
        });
  		});
      </script> -->
        

</body>
    <script type="text/javascript">

</script>
</html>