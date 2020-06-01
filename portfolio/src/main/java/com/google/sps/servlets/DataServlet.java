// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;


/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  public ArrayList<String> msgs = new ArrayList<String>();

  @Override
  // Get text input from comment form and respond with result
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String userComment = getParameter(request, "comments", "");

    // // Sentiment analysis
    // Document doc = Document.newBuilder().setContent(userComment).setType(Document.Type.PLAIN_TEXT).build();
    // LanguageServiceClient languageService = LanguageServiceClient.create();
    // Sentiment sentiment = languageService.analyzeSentiment(doc).getDocumentSentiment();
    // float score = sentiment.getScore();
    // languageService.close();

    // System.out.println("The sentiment score of the comment is: " + score);

    // Create a visitor entity with comment string as the only property
    Entity taskEntity = new Entity("Visitor");
    taskEntity.setProperty("comments", userComment);

    // Store the comment into the datastore
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(taskEntity);
  }

  // Return empty string if no comment, otherwise return text
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    else {
      return value;
    }
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Obtain comments from datastore and filter them into results query
    Query query = new Query("Visitor");
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    // Load the comments into the list of messages
    for (Entity entity : results.asIterable()) {
      String visitorComment = (String) entity.getProperty("comments");
      msgs.add(visitorComment);
    }

    // Display the messages
    Gson gson = new Gson();
    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(msgs));
  }
}
