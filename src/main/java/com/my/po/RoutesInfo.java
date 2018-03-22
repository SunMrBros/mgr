package com.my.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 线路实体
 * @author KangZheng
 *
 */
@Entity
@Table(name="t_routes")
public class RoutesInfo {

	private int id;
	private String title;
	private String basePic1;//基地图片
	private String basePic2;//基地图片
	private String routeDesc;//线路描述
	private int sortNum;
	private int status;
	private ColumnInfo column;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="base_pic1")
	public String getBasePic1() {
		return basePic1;
	}
	public void setBasePic1(String basePic1) {
		this.basePic1 = basePic1;
	}
	@Column(name="base_pic2")
	public String getBasePic2() {
		return basePic2;
	}
	public void setBasePic2(String basePic2) {
		this.basePic2 = basePic2;
	}
	@Column(name="route_desc",columnDefinition="text")
	public String getRouteDesc() {
		return routeDesc;
	}
	public void setRouteDesc(String routeDesc) {
		this.routeDesc = routeDesc;
	}
	@Column(name="sort_num")
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	@Column(name="status",columnDefinition="tinyint")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="column_id")
	public ColumnInfo getColumn() {
		return column;
	}
	public void setColumn(ColumnInfo column) {
		this.column = column;
	}
	
	
}
