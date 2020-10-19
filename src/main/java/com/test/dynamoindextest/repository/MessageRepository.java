package com.test.dynamoindextest.repository;

import com.test.dynamoindextest.model.Message;
import com.test.dynamoindextest.model.MessagePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MessageRepository extends PagingAndSortingRepository<Message, MessagePK> {

  List<Message> findByToAndSendDateLessThanEqual(String to, Long sendDate);

  List<Message> findByToAndFrom(String to, String from);

  Page<Message> findByToAndFrom(String to, String from, Pageable pageable);

}
