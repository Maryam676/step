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

package com.google.sps;

import java.util.Collection;
import java.util.Set;
import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;

public final class FindMeetingQuery {

  // All dates are the first day of the year 2020.
  private static final int TIME_0800AM = TimeRange.getTimeInMinutes(8, 0);
  private static final int TIME_0830AM = TimeRange.getTimeInMinutes(8, 30);
  private static final int TIME_0900AM = TimeRange.getTimeInMinutes(9, 0);
  private static final int TIME_0930AM = TimeRange.getTimeInMinutes(9, 30);
  private static final int TIME_1000AM = TimeRange.getTimeInMinutes(10, 0);
  private static final int TIME_1100AM = TimeRange.getTimeInMinutes(11, 00);

  private static final int DURATION_30_MINUTES = 30;
  private static final int DURATION_60_MINUTES = 60;
  private static final int DURATION_90_MINUTES = 90;
  private static final int DURATION_1_HOUR = 60;
  private static final int DURATION_2_HOUR = 120;

  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    
    ArrayList<TimeRange> temp = new ArrayList<TimeRange>(); // holds available meeting times

    // if there are no events scheduled, entire day available
    if (events.isEmpty() &&  (request.getDuration() < TimeRange.WHOLE_DAY.duration())){
      temp.add(TimeRange.WHOLE_DAY);
      return temp;
    }

    // return empty range for meetings that are longer than a day
    else if (request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
      return temp;
    }

    else {
      // for just one event, times before/after are available
      if (events.size() == 1) {
        Collection<String> attendees = request.getAttendees(); // store all event attendees into a collection
        
        // look at one event, determine who is attending, calculate free time
        for (Event activity : events) {
          Set<String> eventGoers = activity.getAttendees();

          // check if our meeting attendee is part of eventgoers
          for (String person : attendees) {
            // boolean con = eventGoers.contains(person);
            if (!eventGoers.contains(person)) {
              temp.add(TimeRange.WHOLE_DAY); // whole day available if meeting attendee does not have other events scheduled
              return temp;
            }
          }

          // calculate free time for meeting attendee
          int meetingStartTime = activity.getWhen().start();
          int meetingEndTime = activity.getWhen().end();
          temp.add(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, meetingStartTime, false));
          temp.add(TimeRange.fromStartEnd(meetingEndTime, TimeRange.END_OF_DAY, true));
          return temp;
        }
      }

      // check for conflicts between 2 events
      else if (events.size() == 2) {

        Event[] tempEvents = new Event[2];
        // store the events into an array
        int i = 0;
        for (Event activity : events) {
          tempEvents[i] = activity;
          i++;
        }

        Event a = tempEvents[0];
        Event b = tempEvents[1];

        // case where one event overlaps another
        if (a.getWhen().overlaps(b.getWhen()) || b.getWhen().overlaps(a.getWhen())) {
          int startBlock;
          int endBlock;

          // calculate earliest start time, latest end time
          if (a.getWhen().start() < b.getWhen().start()) {
            startBlock = a.getWhen().start();
          }
          else {
            startBlock = b.getWhen().start();
          }

          if (a.getWhen().end() > b.getWhen().end()) {
            endBlock = a.getWhen().end();
          }
          else {
            endBlock = b.getWhen().end();
          }
          temp.add(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, startBlock, false));
          temp.add(TimeRange.fromStartEnd(endBlock, TimeRange.END_OF_DAY, true));
          return temp;
        }
        // just enough room, not enough room check
        else if ((a.getWhen().start() == 0) && b.getWhen().end() == 1440) {
          // there's just enough room for the meeting
          if (request.getDuration() <= (b.getWhen().start() - a.getWhen().end())) {
            temp.add(TimeRange.fromStartEnd(a.getWhen().end(), b.getWhen().start(), false));
            return temp;
          }
          // meeting duration does not fit into gap in schedule
          else {
            System.out.println("There's not enough room for the duration of the meeting.");
            return temp;
          }
        }
        // allow plans between Event A and Event B
        else {
          temp.add(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, a.getWhen().start(), false));
          temp.add(TimeRange.fromStartEnd(a.getWhen().end(), b.getWhen().start(), false));
          temp.add(TimeRange.fromStartEnd(b.getWhen().end(), TimeRange.END_OF_DAY, true));
        }
      }
    }
    return temp;
  }
}
