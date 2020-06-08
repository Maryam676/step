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
    return temp;
  }
}
