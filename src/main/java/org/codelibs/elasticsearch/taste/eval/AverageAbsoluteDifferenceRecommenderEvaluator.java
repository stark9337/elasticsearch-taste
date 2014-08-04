/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codelibs.elasticsearch.taste.eval;

import org.codelibs.elasticsearch.taste.common.FullRunningAverage;
import org.codelibs.elasticsearch.taste.common.RunningAverage;
import org.codelibs.elasticsearch.taste.model.Preference;

/**
 * <p>
 * A {@link org.codelibs.elasticsearch.taste.eval.RecommenderEvaluator} which computes the average absolute
 * difference between predicted and actual ratings for users.
 * </p>
 *
 * <p>
 * This algorithm is also called "mean average error".
 * </p>
 */
public final class AverageAbsoluteDifferenceRecommenderEvaluator extends
        AbstractDifferenceRecommenderEvaluator {

    private RunningAverage average;

    @Override
    protected void reset() {
        average = new FullRunningAverage();
    }

    @Override
    protected void processOneEstimate(final float estimatedPreference,
            final Preference realPref) {
        average.addDatum(Math.abs(realPref.getValue() - estimatedPreference));
    }

    @Override
    protected double computeFinalEvaluation() {
        return average.getAverage();
    }

    @Override
    public String toString() {
        return "AverageAbsoluteDifferenceRecommenderEvaluator";
    }

}