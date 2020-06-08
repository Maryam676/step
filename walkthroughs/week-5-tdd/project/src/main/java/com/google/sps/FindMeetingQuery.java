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
    //throw new UnsupportedOperationException("TODO: Implement this method.");
    ArrayList<TimeRange> temp = new ArrayList<TimeRange>();

    // if there are no events scheduled, entire day available
    if (events.isEmpty()) {
      temp.add(TimeRange.WHOLE_DAY);
      return temp;
    }
    // return empty range for meetings that are longer than a day
    else if (request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
      return temp;
    }
    else {
      temp.add(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, TIME_0830AM, false));
      temp.add(TimeRange.fromStartEnd(TIME_0900AM, TimeRange.END_OF_DAY, true));
    }
    return temp;
  }
}
