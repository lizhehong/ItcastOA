<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>配置权限</title>
<%@ include file="/WEB-INF/run/jsp/public/header.jspf"%>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/file.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
<script type="text/javascript">
	$(function(){
		/* 增加onclick时间的处理函数 */
		$("input[name=privilegeIds]").click(function(){
			/* 当选中或取消某个权限，同时也选中或取消所有的下级权限*/
			$(this).siblings("ul").find("input").attr("checked",this.checked);
		/* 当选中某个权限时，应同时选中它的直系上的权限 */
		if(this.checked == true){
			$(this).parents().children("input[name=privilegeIds]").attr("checked",true);
		}
		else{
			/* 当取消某个权限时，如果同级的权限都没有选择，则也取消上级权限 */
			var secondSiblingsElement = $(this).parent().siblings("li").children("input:checked");
			if(secondSiblingsElement.size()==0){
				/* 取消上级 */
				$(this).parent().parent().siblings("input").attr("checked",false);
			}
			/* 第二层全部没有选中，取消第一层的选择,暂时不能处理，没有三层结构的树状，会出错 */
			var size = $(this).parent().parent().parent().siblings("li").children("input[name=privilegeIds]:checked").size();
			alert(size);
			if(size == 0){
				$("#tree").children().children("input[name=privilegeIds]").attr("checked",false);
			}
		}
		});
	});
</script>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				配置权限
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>

		<s:form action="role_setPrivilege">
			<s:hidden name="id"></s:hidden>

			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					正在为【${name}】配置权限
				</div>
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<!--表头-->
						<thead>
							<tr align="LEFT" valign="MIDDLE" id="TableTitle">
								<td width="300px" style="padding-left: 7px;">
								<input type="checkbox" id="cbSelectAll"
									onclick="  $('input[name=privilegeIds]').attr('checked', this.checked)  " />
								<label for="cbSelectAll">全选</label>
								</td>
							</tr>
						</thead>

						<!--显示数据列表，因为权限是事先初始化好的，不会轻易改变，所以只限制了三层，直接迭代出-->
						<tbody id="TableData">
							<tr class="TableDetail1">
								<!-- 显示权限树 -->
								<td>
									<!-- 用struts2的标签，不利于加样式  --> <%-- <s:checkboxlist name="privilegeIds" list="privilegeList" listKey="id" listValue="name"></s:checkboxlist> --%>
									<!-- 自己用迭代，实现复选框,回显功能 ，解决不能按一个循序排列，所以在对应的hibernate.hbm.xml中设置order-by-->
									<ul id="tree">
									<!-- 第一层 -->
										<s:iterator value="topPrivilegeList">
											<li>
												<input type="checkbox" name="privilegeIds"
													value="${id}" id="cb_${id}"
													<s:property value="%{id in privilegeIds ?'checked':''}"/> />
												<label for="cb_${id}"><span class="folder">${name}</span></label>
												<ul>
												<!-- 第二层 -->
													<s:iterator value=" children">
														<li>
															<input type="checkbox" name="privilegeIds"
																value="${id}" id="cb_${id}"
																<s:property value="%{id in privilegeIds ?'checked':''}"/> />
															<label for="cb_${id}"><span class="folder">${name}</span></label>
															<ul>
															<!-- 第三层 -->
																<s:iterator value="children">
																	<li>
																		<input type="checkbox" name="privilegeIds"
																			value="${id}" id="cb_${id}"
																			<s:property value="%{id in privilegeIds ?'checked':''}"/> />
																		<label for="cb_${id}"><span class="folder">${name}</span></label>
																	</li>
																</s:iterator>
															</ul>
														</li>
													</s:iterator>
												</ul>
											</li>
										</s:iterator>
									</ul> 
									<script type="text/javascript">
										$("#tree").treeview();
									</script>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image"
					src="${pageContext.request.contextPath}/style/images/save.png" />
				<a href="javascript:history.go(-1);"><img
					src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>

	<div class="Description">
		说明：<br /> 1，选中一个权限时：<br /> &nbsp;&nbsp;&nbsp;&nbsp; a，应该选中他的所有直系上级。<br />
		&nbsp;&nbsp;&nbsp;&nbsp; b，应该选中他的所有直系下级。<br /> 2，取消选择一个权限时：<br />
		&nbsp;&nbsp;&nbsp;&nbsp; a，应该取消选择他的所有直系下级。<br />
		&nbsp;&nbsp;&nbsp;&nbsp; b，如果同级的权限都是未选择状态，就应该取消选中他的直接上级，并向上做这个操作。<br />

		3，全选/取消全选。<br /> 4，默认选中当前岗位已有的权限。<br />
	</div>

</body>
</html>