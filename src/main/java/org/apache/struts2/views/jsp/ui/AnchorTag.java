package org.apache.struts2.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts2.components.Anchor;
import org.apache.struts2.components.Component;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 
 * @ClassName: AnchorTag
 * @Description: 用于改进struts2<s:a></s:a>标签的功能，加上权限判断
 * @author lizhehong
 * @date 2015年6月5日 下午1:26:21
 *
 */
public class AnchorTag extends AbstractClosingTag {

	private static final long serialVersionUID = -1034616578492431113L;

	protected String href;
	protected String includeParams;
	protected String scheme;
	protected String action;
	protected String namespace;
	protected String method;
	protected String encode;
	protected String includeContext;
	protected String escapeAmp;
	protected String portletMode;
	protected String windowState;
	protected String portletUrlType;
	protected String anchor;
	protected String forceAddSchemeHostAndPort;

	@Override
	public int doEndTag() throws JspException {
		// 获取当登录的用户
		User user = (User) pageContext.getSession().getAttribute("user");
		if (user == null)
			throw new RuntimeException("======没有登陆用户======");

		// 获取所需要的权限URL(在action的属性中)
		String privUrl = "/" + action;
		
		if (user.hasPrivilegeByUrl(privUrl)) {
			return super.doEndTag();// 输出<a>标签，并继续执行此标签后面的内容
		} else {
			return BodyTagSupport.EVAL_PAGE;// 没有输出<a>标签 继续执行此标签后面的jsp代码

		}
	}

	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new Anchor(stack, req, res);
	}

	protected void populateParams() {
		super.populateParams();

		Anchor tag = (Anchor) component;
		tag.setHref(href);
		tag.setIncludeParams(includeParams);
		tag.setScheme(scheme);
		tag.setValue(value);
		tag.setMethod(method);
		tag.setNamespace(namespace);
		tag.setAction(action);
		tag.setPortletMode(portletMode);
		tag.setPortletUrlType(portletUrlType);
		tag.setWindowState(windowState);
		tag.setAnchor(anchor);

		if (encode != null) {
			tag.setEncode(Boolean.valueOf(encode).booleanValue());
		}
		if (includeContext != null) {
			tag.setIncludeContext(Boolean.valueOf(includeContext)
					.booleanValue());
		}
		if (escapeAmp != null) {
			tag.setEscapeAmp(Boolean.valueOf(escapeAmp).booleanValue());
		}
		if (forceAddSchemeHostAndPort != null) {
			tag.setForceAddSchemeHostAndPort(Boolean.valueOf(
					forceAddSchemeHostAndPort).booleanValue());
		}
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public void setIncludeContext(String includeContext) {
		this.includeContext = includeContext;
	}

	public void setEscapeAmp(String escapeAmp) {
		this.escapeAmp = escapeAmp;
	}

	public void setIncludeParams(String name) {
		includeParams = name;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setPortletMode(String portletMode) {
		this.portletMode = portletMode;
	}

	public void setPortletUrlType(String portletUrlType) {
		this.portletUrlType = portletUrlType;
	}

	public void setWindowState(String windowState) {
		this.windowState = windowState;
	}

	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	public void setForceAddSchemeHostAndPort(String forceAddSchemeHostAndPort) {
		this.forceAddSchemeHostAndPort = forceAddSchemeHostAndPort;
	}
}
