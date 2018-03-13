package com.my.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.dao.ColumnDao;
import com.my.po.ColumnInfo;

import my.utils.hibernate.HibernateDAOImpl;
@Repository("columnDao")
public class ColumnDaoImpl extends HibernateDAOImpl<ColumnInfo> implements ColumnDao {


}
