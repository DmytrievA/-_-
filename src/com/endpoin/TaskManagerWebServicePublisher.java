package com.endpoin;

import javax.xml.ws.Endpoint;
import com.itroi.*;

public class TaskManagerWebServicePublisher {
	public static void main(String... args) {
        Endpoint.publish("http://localhost:1986/taskmanager", new TaskManagerImp());
    }
}
