package com.dublabs.Domain;

import org.springframework.http.RequestEntity;

import java.util.List;

/**
 * Created by tofiques on 11/28/17.
 */
public class Requests {

    List<RequestEntity> requests;

  public List<RequestEntity> getRequests() {
    return requests;
  }

  public void setRequests(List<RequestEntity> requests) {
    this.requests = requests;
  }
}
