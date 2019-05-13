package com.rwt.cache.springdata.service;

import com.rwt.cache.springdata.dao.DocumentDao;
import com.rwt.cache.springdata.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    @Autowired
    private DocumentDao documentDao;

    public Document save(Document document) {
        Document save = documentDao.save(document);
        return save;
    }

    public Document get(Long id) {
        Document one = documentDao.findById(id).get();
        return one;
    }

    public void delete(Document document) {
        documentDao.delete(document);
    }




}
