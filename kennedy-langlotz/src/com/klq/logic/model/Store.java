package com.klq.logic.model;

import com.klq.logic.Id;
import com.klq.logic.Question;

import java.util.Map;

/**
 * Created by Timon on 23.02.2015.
 */
public class Store {
    private Map<Id, Question> store;

    public Question add(Id questionId, Question question){
        return store.put(questionId, question);
    }

    public Question get(Id questionId){
        return store.get(questionId);
    }


}