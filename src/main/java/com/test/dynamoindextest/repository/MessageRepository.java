package com.test.dynamoindextest.repository;

import com.test.dynamoindextest.model.Message;
import com.test.dynamoindextest.model.MessagePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<Message, MessagePK> {

  Page<Message> findByCompositionToFromAndSendDateLessThanEqual(String compositionToFrom, Long sendDate, Pageable pageable);

}
