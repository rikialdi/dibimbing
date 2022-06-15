package com.dibimbing.dibimbing.repository.oauth;

import com.dibimbing.dibimbing.model.oauth.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    Client findOneByClientId(String clientId);

}
