<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="UTF-8">
		<title>登录</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />

		<link rel="stylesheet" href="/css/amazeui.css" />
		<link href="/css/dlstyle.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
	</head>

	<body>

		<div class="login-boxtitle">
			<a href="home.html"><img alt="logo" src="/images/logobig.png" /></a>
		</div>

		<div class="login-banner">
			<div class="login-main">
				<div class="login-banner-bg"><span></span><img src="/images/big.jpg" /></div>
				<div class="login-box">

							<h3 class="title">登录商城</h3>

							<div class="clear"></div>
						
						<div class="login-form">
						  <form id="formlogin" method="post" onsubmit="return false;">
							   <!-- <div class="user-name">
								    <label for="user"><i class="am-icon-user"></i></label>
								    <input type="text" name="" id="user" placeholder="邮箱/手机/用户名">
			                  </div> -->
			                  <div class="user-name">
			                  		<label for="user"><i class="am-icon-user"></i></label>
			                        <input type="text" id="loginname" name="username" class="text" placeholder="邮箱/手机/用户名" tabindex="1" autocomplete="off"/>
			                        <div class="i-name ico"></div>
			                        <label id="loginname_succeed" class="blank invisible"></label>
			                        <label id="loginname_error" class="hide"><b></b></label>
			                    </div>
			                  <script type="text/javascript">
			                    setTimeout(function () {
			                        if (!$("#loginname").val()) {
			                            $("#loginname").get(0).focus();
			                        }
			                    }, 0);
			                </script>
			                  <!-- <div class="user-pass">
								    <label for="password"><i class="am-icon-lock"></i></label>
								    <input type="password" name="" id="password" placeholder="请输入密码">
			                  </div> -->
			                  <div class="user-pass">
			                  	<label for="password"><i class="am-icon-lock"></i></label>
		                        <input type="password" id="nloginpwd" name="password" class="text" placeholder="请输入密码" tabindex="2" autocomplete="off"/>
		                        <div class="i-pass ico"></div>
		                        <label id="loginpwd_succeed" class="blank invisible"></label>
		                        <label id="loginpwd_error" class="hide"></label>
		                    </div>

			                  <div class="login-links">
					                <!-- <label for="remember-me"><input id="remember-me" type="checkbox">记住密码</label>
									<a href="#" class="am-fr">忘记密码</a> -->
									<a href="/page/register" class="zcnext am-fr am-btn-default">注册</a>
									<br />
					            </div>

			                  <!-- <div class="am-cf">
									<input type="submit" name="" value="登 录" class="am-btn am-btn-primary am-btn-sm">
							 </div> -->
							 <div class="am-cf">
			                    <input type="button" class="am-btn am-btn-primary am-btn-sm" id="loginsubmit" value="登录" tabindex="8" clstag="passport|keycount|login|06"/>
			                </div>
			              </form>
                        </div>
            
            
								
						<!-- <div class="partner">		
								<h3>合作账号</h3>
							<div class="am-btn-group">
								<li><a href="#"><i class="am-icon-qq am-icon-sm"></i><span>QQ登录</span></a></li>
								<li><a href="#"><i class="am-icon-weibo am-icon-sm"></i><span>微博登录</span> </a></li>
								<li><a href="#"><i class="am-icon-weixin am-icon-sm"></i><span>微信登录</span> </a></li>
							</div>
						</div>	 -->

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
	var redirectUrl = "${redirect}";
	var LOGIN = {
			checkInput:function() {
				if ($("#loginname").val() == "") {
					alert("用户名不能为空");
					$("#loginname").focus();
					return false;
				}
				if ($("#nloginpwd").val() == "") {
					alert("密码不能为空");
					$("#nloginpwd").focus();
					return false;
				}
				return true;
			},
			doLogin:function() {
				$.post("/user/login", $("#formlogin").serialize(),function(data){
					if (data.status == 200) {
						alert("登录成功！");
						if (redirectUrl == "") {
							location.href = "http://localhost:8082";
						} else {
							location.href = redirectUrl;
						}
					} else {
						alert("登录失败，原因是：" + data.msg);
						$("#loginname").select();
					}
				});
			},
			login:function() {
				if (this.checkInput()) {
					this.doLogin();
				}
			}
		
	};
	$(function(){
		$("#loginsubmit").click(function(){
			LOGIN.login();
		});
	});
</script>
	</body>

</html>