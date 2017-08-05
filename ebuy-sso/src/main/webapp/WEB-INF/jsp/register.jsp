<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>

	<head lang="en">
		<meta charset="UTF-8">
		<title>注册</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />

		<link rel="stylesheet" href="/css/amazeui.min.css" />
		<link href="/css/dlstyle.css" rel="stylesheet" type="text/css">
		<script src="/js/jquery.min.js"></script>
		<script src="/js/amazeui.min.js"></script>

	</head>

	<body>

		<div class="login-boxtitle">
			<a href="home/demo.html"><img alt="" src="../images/logobig.png" /></a>
		</div>

		<div class="res-banner">
			<div class="res-main">
				<div class="login-banner-bg"><span></span><img src="../images/big.jpg" /></div>
				<div class="login-box">

						<div class="am-tabs" id="doc-my-tabs">
							<ul class="am-tabs-nav am-nav am-nav-tabs am-nav-justify">
								<li><a href="#">个人注册</a></li>
								<li><a href="/page/login">登录</a></li>
							</ul>

							<div class="am-tabs-bd">
								<div class="am-tab-panel am-active">
									<form id="personRegForm" method="post" onsubmit="return false;">
										
							         <!-- <div class="user-email">
										<label for="email"><i class="am-icon-envelope-o"></i></label>
										<input type="email" name="" id="email" placeholder="请输入用户名">
					                 </div>	 -->
					                 <div class="user-email">
				                        	<label for="email"><i class="am-icon-envelope-o"></i></label>
				                            <input type="text" id="regName" name="username" class="text" tabindex="1" autoComplete="off"
				                                   onpaste="return false;"
				                                   value=""
				                                   onfocus="if(this.value=='') this.value='';this.style.color='#333'"
				                                   onblur="if(this.value=='') {this.value='';this.style.color='#999999'}"
				                                   placeholder="请输入用户名">
				                            <i class="i-name"></i>
				                            <ul id="intelligent-regName" class="hide"></ul>
				                            <label id="regName_succeed" class="blank"></label>
				                            <label id="regName_error" class="hide"></label>
				                        
				                    </div>									
					                 <!-- <div class="user-pass">
										    <label for="password"><i class="am-icon-lock"></i></label>
										    <input type="password" name="" id="password" placeholder="设置密码">
					                 </div>-->	
					                 <div class="user-pass">
					                 	<label for="password"><i class="am-icon-lock"></i></label>
			                            <input type="password" id="pwd" name="password" class="text" tabindex="2"
			                                   style="ime-mode:disabled;"
			                                   onpaste="return  false" autocomplete="off"
			                                   placeholder="设置密码">
			                            <i class="i-pass"></i>
			                            <label id="pwd_succeed" class="blank"></label>
			                            <label id="pwd_error"></label>
			                            <span class="clr"></span>
			                        </div>							
					                <!--  <div class="user-pass">
									    <label for="passwordRepeat"><i class="am-icon-lock"></i></label>
									    <input type="password" name="" id="passwordRepeat" placeholder="确认密码">
					                 </div>	 -->
					                 <div class="user-pass">
					                 	<label for="passwordRepeat"><i class="am-icon-lock"></i></label>
			                            <input type="password" id="pwdRepeat" name="pwdRepeat" class="text" tabindex="3"
			                                   onpaste="return  false" autocomplete="off"
			                                   placeholder="确认密码">
			                            <i class="i-pass"></i>
			                            <label id="pwdRepeat_succeed" class="blank"></label>
			                            <label id="pwdRepeat_error"></label>
			                        </div>
					                 <!-- <div class="user-phone">
									    <label for="phone"><i class="am-icon-mobile-phone am-icon-md"></i></label>
									    <input type="tel" name="" id="phone" placeholder="请输入手机号">
					                 </div>	 -->
					                 <div class="user-phone">
					                 	<label for="phone"><i class="am-icon-mobile-phone am-icon-md"></i></label>
										<input type="text" id="phone" maxlength="11" name="phone"
											class="text" tabindex="4"
											autocomplete="off" placeholder="请输入手机号"> 
											<i class="i-phone"></i> 
											<label id="phone_succeed" class="blank"></label> 
											<label id="phone_error"></label>
									</div>
					                 </form>
                 
								 <div class="login-links">
										<label for="reader-me">
											<input id="reader-me" type="checkbox" onclick="agreeonProtocol();"> 点击表示您同意商城《服务协议》
										</label>
							  	</div>
										<!-- <div class="am-cf">
											<input type="submit" name="" value="注册" class="am-btn am-btn-primary am-btn-sm am-fl">
										</div> -->
										<div class="am-cf">
						                    <span class="label">&nbsp;</span>
						                    <input type="button" class="am-btn am-btn-primary am-btn-sm am-fl" id="registsubmit" value="立即注册" tabindex="8" clstag="regist|keycount|personalreg|07" onclick="REGISTER.reg();">
						                </div>

								</div>

								<div class="am-tab-panel">
									<!-- <form method="post">
                 																		
										<div class="verification">
											<label for="code"><i class="am-icon-code-fork"></i></label>
											<input type="tel" name="" id="code" placeholder="请输入验证码">
											<a class="btn" href="javascript:void(0);" onclick="sendMobileCode();" id="sendMobileCode">
												<span id="dyMobileButton">获取</span></a>
										</div>
					                 <div class="user-pass">
													    <label for="password"><i class="am-icon-lock"></i></label>
													    <input type="password" name="" id="password" placeholder="设置密码">
					                 </div>										
					                 <div class="user-pass">
													    <label for="passwordRepeat"><i class="am-icon-lock"></i></label>
													    <input type="password" name="" id="passwordRepeat" placeholder="确认密码">
					                 </div>	
									</form> -->
								 <!-- <div class="login-links">
										<label for="reader-me">
											<input id="reader-me" type="checkbox"> 点击表示您同意商城《服务协议》
										</label>
							  	</div> -->
										<!-- <div class="am-cf">
											<input type="submit" name="" value="注册" class="am-btn am-btn-primary am-btn-sm am-fl">
										</div> -->
								
									<hr>
								</div>

								<!-- <script>
									$(function() {
									    $('#doc-my-tabs').tabs();
									  })
								</script> -->

							</div>
						</div>

				</div>
			</div>
			
					<div class="footer ">
						<div class="footer-hd ">
							<p>
								
								<a href="# ">商城首页</a>
								<b>|</b>
								<a href="# ">支付宝</a>
								<b>|</b>
								<a href="# ">物流</a>
							</p>
						</div>
						<div class="footer-bd ">
							<p>
								<a href="# ">关于恒望</a>
								<a href="# ">合作伙伴</a>
								<a href="# ">联系我们</a>
								<a href="# ">网站地图</a>
								<em>© 2015-2025 Hengwang.com </em>
							</p>
						</div>
					</div>

	<script type="text/javascript">
	var REGISTER={
		param:{
			//单点登录系统的url
			surl:""
		},
		inputcheck:function(){
				//不能为空检查
				if ($("#regName").val() == "") {
					alert("用户名不能为空");
					$("#regName").focus();
					return false;
				}
				if ($("#pwd").val() == "") {
					alert("密码不能为空");
					$("#pwd").focus();
					return false;
				}
				if ($("#phone").val() == "") {
					alert("手机号不能为空");
					$("#phone").focus();
					return false;
				}
				//密码检查
				if ($("#pwd").val() != $("#pwdRepeat").val()) {
					alert("确认密码和密码不一致，请重新输入！");
					$("#pwdRepeat").select();
					$("#pwdRepeat").focus();
					return false;
				}
				return true;
		},
		beforeSubmit:function() {
				//检查用户是否已经被占用
				$.ajax({
	            	url : REGISTER.param.surl + "/user/check/"+escape($("#regName").val())+"/1?r=" + Math.random(),
	            	success : function(data) {
	            		if (data.data) {
	            			//检查手机号是否存在
	            			$.ajax({
	            				url : REGISTER.param.surl + "/user/check/"+$("#phone").val()+"/2?r=" + Math.random(),
				            	success : function(data) {
				            		if (data.data) {
					            		REGISTER.doSubmit();
				            		} else {
				            			alert("此手机号已经被注册！");
				            			$("#phone").select();
				            		}
				            	}
	            			});
	            		} else {
	            			alert("此用户名已经被占用，请选择其他用户名");
	            			$("#regName").select();
	            		}	
	            	}
				});
	            	
		},
		doSubmit:function() {
			$.post("/user/register",$("#personRegForm").serialize(), function(data){
				if(data.status == 200){
					alert('用户注册成功，请登录！');
					REGISTER.login();
				} else {
					alert("注册失败！");
				}
			});
		},
		login:function() {
			 location.href = "/page/login";
			 return false;
		},
		reg:function() {
			if (this.inputcheck()) {
				this.beforeSubmit();
			}
		}
	};
</script>
	</body>

</html>