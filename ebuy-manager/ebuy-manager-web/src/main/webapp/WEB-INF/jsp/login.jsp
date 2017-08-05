<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>

<!-- <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/bootstrap/easyui.css" /> -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
   <link href="css/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="css/FontAwesome/css/font-awesome.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>

<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>

</head>
<body style="background-color: #F3F3F3">
    <!-- <div class="easyui-dialog" title="管理员登录" data-options="closable:false,draggable:false" style="width:400px;height:300px;padding:10px;">
       	<div style="margin-left: 50px;margin-top: 50px;">
       		<div style="margin-bottom:20px;">
	            <div>
	            	用户名: <input name="username" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value="admin"/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px">
	            <div>
	            	密&nbsp;&nbsp;码: <input name="password" class="easyui-textbox" type="password" style="width:200px;height:32px" data-options="" value="admin"/>
	            </div>
	        </div>
	        <div>
	            <a id="login" class="easyui-linkbutton" iconCls="icon-ok" style="width:100px;height:32px;margin-left: 50px">登录</a>
	        </div>
       	</div>
	</div> -->
	<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control"  data-options="required:true" placeholder="用户名：必填" name="username" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control"  data-options="required:true" placeholder="密码：必填" name="password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">记住我
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
								<!-- <a href="index.html" class="btn btn-lg btn-success btn-block">Login</a>
								 -->
								 <a id="login" class="easyui-linkbutton btn btn-lg btn-success btn-block" iconCls="icon-ok">登录</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	$("#login").click(function(){
    		var username = $("[name=username]").val();
    		var password = $("[name=password]").val();
    		
    		if(username!="admin" || password!="admin"){
    			$.messager.alert('错误',"用户名密码不正确！");
    			return ;
    		}
    		window.location.href="/index";
    	});
    </script>
</body>
</html>