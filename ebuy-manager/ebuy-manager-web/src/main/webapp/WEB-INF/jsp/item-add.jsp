<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
.spa{
width: 204px;
}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5"  class="table" style="width:1000px;">
			
	        <tr>
	            <td>商品类目:</td>
	            <td>
	            	 <a href="javascript:void(0)"  onclick="delHide()" class="btn btn-default selectItemCat">选择类目</a> 
					<!-- <button href="javascript:void(0)" class="btn btn-default selectItemCat ">选择类目</button> -->
	            	<input type="hidden" name="cid" style="width: 280px;"></input>
	            </td>
	        </tr>
	        <tr>
				<td>商品标题:</td>
                 <td><input  type="text" id="title" name="title" class="form-control" placeholder="请输入">
                 <!-- <span class="spa spa1"></span> -->
                 </td>	
                 <td style="width:170px;"><span class="spa spa1"></span></td>
	           <!-- <input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 280px;"></input></td> -->
	        </tr>
	        <tr>
	            <td>商品卖点:</td>
	            <td><input class="form-control"  type="text" name="sellPoint"  placeholder="请输入"  ></input>
	            
	            </td>
	            <!-- <td style="width:170px;"><span class="spa spa1"></span></td> -->
	        </tr>
	        <tr>
	            <td>商品价格:</td>
	            <td><input class="form-control" id="priceView" type="text" name="priceView"  placeholder="请输入数字" />
	           
	            	<input type="hidden" name="price"/>
	            </td>
	            <td style="width:170px;"><span class="spa spa2"></span></td>
	        </tr>
	        <tr>
	            <td>库存数量:</td>
	            <td><input class="form-control"  id="num" type="text" name="num" placeholder="请输入数字"/>
	            
	            </td>
	            <td style="width:170px;"><span class="spa spa3"></span></td>
	        </tr>
	        <tr>
	            <td>条形码:</td>
	            <td>
	                <input class="  form-control" type="text" name="barcode"  placeholder="请输入数字"  />
	            </td>
	        </tr>
	        <tr>
	            <td>商品图片:</td>
	            <td>
	            	 <a href="javascript:void(0)" class="btn btn-default picFileUpload">上传图片</a>
	                 <input type="hidden" name="image"/>
	            </td>
	        </tr>
	        <tr>
	            <td>商品描述:</td>
	            <td>
	                <textarea class="form-control" style="width:100%;height:300px;visibility:hidden;" name="desc"></textarea>
	            </td>
	        </tr>
	        <tr id= "params" class="params hide">
	        	<td>商品规格:</td>
	        	<td>
	        		<input  type="text"  class="form-control" placeholder="可选参数">
	        	</td>
			</tr>
	    </table>
	    <input type="hidden" name="itemParams"/>
	</form>
	<div style="width:1000px;float:left;padding-left:800px">
		<button type="submit" class="btn btn-primary"  onclick="submitForm()">提交</button>
		<button type="reset" class="btn btn-default" onclick="clearForm()">重置</button>
	    <!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a> -->
	</div>
</div>
<script type="text/javascript">

	var itemAddEditor ;
	//页面初始化完毕后执行此方法
	$(function(){
		//创建富文本编辑器
		//itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
		itemAddEditor = KindEditor.create("#itemAddForm [name=desc]", TT.kingEditorParams);
		//初始化类目选择和图片上传器
		TAOTAO.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。
			TAOTAO.changeItemParam(node, "itemAddForm");
		}});
		console.log("ok");
		/* barcode */
		$("#title").focus(function(){
			$(".spa1").text("标题必填").css("color","red")
			$(this).css("border","1px solid red")
		});
		$("#priceView").focus(function(){
			$(".spa2").text("价格必填").css("color","red")
			$(this).css("border","1px solid red")
		});
		$("#num").focus(function(){
			$(".spa3").text("请填写数字").css("color","red")
			$(this).css("border","1px solid red")
		});
		
	});
	function delHide(){
		$("#params").removeClass("hide");
	}
	//提交表单
	function submitForm(){
		//有效性验证
		/* if(!$('#itemAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		} */
		var flag = true;
		if($("#title").val()==""){
			$(".spa1").text('请你填写商品标题').css("color","red")
			$(".spa1").css("border","1px solid red");
			flag = false;
		} 
		if($("#priceView").val()==""){
			$(".spa2").text('请你填写价格').css("border","1px solid red");
			$(".spa2").css("border","1px solid red");
			flag = false;
		} 
		if($("#num").val()==""){
			$(".spa3").text('请你填写数量').css("border","1px solid red");
			$(".spa3").css("border","1px solid red");
			flag = false;
		}
		if(flag == false){
			return false;
		}
		//取商品价格，单位为“分”
		$("#itemAddForm [name=price]").val(eval($("#itemAddForm [name=priceView]").val()) * 100);
		//同步文本框中的商品描述
		itemAddEditor.sync();
		//取商品的规格
		var paramJson = [];
		$("#itemAddForm .params li").each(function(i,e){
			var trs = $(e).find("tr");
			var group = trs.eq(0).text();
			var ps = [];
			for(var i = 1;i<trs.length;i++){
				var tr = trs.eq(i);
				ps.push({
					"k" : $.trim(tr.find("td").eq(0).find("span").text()),
					"v" : $.trim(tr.find("input").val())
				});
			}
			paramJson.push({
				"group" : group,
				"params": ps
			});
		});
		//把json对象转换成字符串
		paramJson = JSON.stringify(paramJson);
		$("#itemAddForm [name=itemParams]").val(paramJson);
		
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/item/save",$("#itemAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增商品成功!');
			}
		});
	}
	
	function clearForm(){
		$('#itemAddForm').form('reset');
		itemAddEditor.html('');
	}
	
</script>
