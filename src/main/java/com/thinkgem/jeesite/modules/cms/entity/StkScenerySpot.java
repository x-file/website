/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.entity;




import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 视频Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class StkScenerySpot extends DataEntity<StkScenerySpot> {

	
	private Integer SpotId;// 景点ID
	private Integer CamId;//摄像机ID
	private String Name;	// 景点名称
    private String Comment;	// 描述
    
	public Integer getSpotId() {
		return SpotId;
	}
	public void setSpotId(Integer spotId) {
		SpotId = spotId;
	}
	public Integer getCamId() {
		return CamId;
	}
	public void setCamId(Integer camId) {
		CamId = camId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	

	
   
}


