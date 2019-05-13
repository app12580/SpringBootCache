package com.rwt.cache.springdata.dao;

import com.rwt.cache.springdata.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DocumentDao extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document> {

}
