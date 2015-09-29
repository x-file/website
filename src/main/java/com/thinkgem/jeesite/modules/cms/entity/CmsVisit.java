/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 访问量统计Entity
 * @author sa
 * @version 2015-09-28
 */
public class CmsVisit extends DataEntity<CmsVisit> {
	
	private static final long serialVersionUID = 1L;
	private Integer no;			// number记录第N个用户
	private String memberName;	// 员会名称
	private String ip;			// 来访IP
	private String area;		// 访来国家/地区
	private String time;		//访问时间
	
	public CmsVisit() {
		super();
	}

	

	public CmsVisit(String id){
		super(id);
	}

	@Length(min=0, max=255, message="number记录第N个用户长度必须介于 0 和 255 之间")
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}
	
	@Length(min=0, max=11, message="员会名称长度必须介于 0 和 11 之间")
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	@Length(min=0, max=255, message="来访IP长度必须介于 0 和 255 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Length(min=0, max=255, message="访来国家/地区长度必须介于 0 和 255 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}