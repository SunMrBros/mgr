package com.my.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 栏目实体
 * 
 * @author KangZheng
 *
 */
@Entity
@Table(name = "t_column")
public class ColumnInfo {

	private int id;
	private String columnName;
	private String columnPicUrl;
	private int sortNum;
	private int status;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="column_name")
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	@Column(name="column_pic_url")
	public String getColumnPicUrl() {
		return columnPicUrl;
	}

	public void setColumnPicUrl(String columnPicUrl) {
		this.columnPicUrl = columnPicUrl;
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

}
