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

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.Gson;


/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  public ArrayList<String> msgs = new ArrayList<String>();

  @Override

  // Get text input from comment form and respond with result
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String userComment = getParameter(request, "comments", "");
    msgs.add(userComment);
    // String[] words = userComment.split("\\s*,\\s*");
    // for (int i = 0; i < words.length; i++) {
    //   System.out.println(words[i]);
    //   msgs.add(words[i]);
    // }

    response.setContentType("text/html;");
    response.getWriter().println(msgs);
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
    // response.setContentType("text/html;");
    // response.getWriter().println("<h1>Hello Maryam Ahmed!</h1>");

    Gson gson = new Gson();
    String json = gson.toJson(msgs);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
}
