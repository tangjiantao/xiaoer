package com.tangjiantao.cms.domain;

import java.io.Serializable;
import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;





//声明该实体类是一个文档对象,并且指定索引库的名称(必须用纯小写字母,如果是大写字母,直接报错),指定类型(表名)

@Document(indexName = "article",type = "article")
public class Article implements Serializable{
	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = -2996162680181923170L;

	@Id
	private Integer id;
	//指定name的值是否索引,2.是否存储3.name的值的分词方式  4.搜索的关键字分词的方式  5指定该字段的值以什么样的数据类型来存储
	@Field(index = true,store = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word",type = FieldType.text)		
    private String title;

    private String picture;

    private Integer channelId;//栏目ID

    private Integer categoryId;//分类ID

    private Integer userId;

    private Integer hits;

    private Integer hot;

    private Integer status;

    private Integer deleted;

    private Date created;

    private Date updated;

  //指定name的值是否索引,2.是否存储3.name的值的分词方式  4.搜索的关键字分词的方式  5指定该字段的值以什么样的数据类型来存储
    @Field(index = true,store = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word",type = FieldType.text)
    private String content;
    
    //创建一个一方的对象
    private User user;
    
    
    

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", picture=" + picture + ", channelId=" + channelId
				+ ", categoryId=" + categoryId + ", userId=" + userId + ", hits=" + hits + ", hot=" + hot + ", status="
				+ status + ", deleted=" + deleted + ", created=" + created + ", updated=" + updated + ", content="
				+ content + ", user=" + user + "]";
	}
}
