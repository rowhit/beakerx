/*
 *  Copyright 2014 TWO SIGMA OPEN SOURCE, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.twosigma.beaker.chart.xychart;

import com.twosigma.beaker.jupyter.KernelManager;
import com.twosigma.beaker.KernelTest;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class CombinedPlotTest {

  CombinedPlot combinedPlot;

  @Before
  public void setUp() throws Exception {
    KernelManager.register(new KernelTest());
    combinedPlot = new CombinedPlot();
  }

  @After
  public void tearDown() throws Exception {
    KernelManager.register(null);
  }

  @Test
  public void addTwoPlotsToCombinedPlot_hasSubplotsSizeIsTwoAndWeightListSizeIsTwo() {
    //when
    combinedPlot.add(new Plot());
    combinedPlot.add(new Plot());
    //then
    Assertions.assertThat(combinedPlot.getSubplots().size()).isEqualTo(2);
    Assertions.assertThat(combinedPlot.getWeights().size()).isEqualTo(2);
  }

  @Test
  public void addTwoPlotsAndWeightsToCombinedPlot_hasSubplotsSizeIsTwoAndWeightListSizeIsTwo() {
    //when
    combinedPlot.add(new Plot(), 3);
    combinedPlot.add(new Plot(), 3);
    //then
    Assertions.assertThat(combinedPlot.getSubplots().size()).isEqualTo(2);
    Assertions.assertThat(combinedPlot.getWeights().size()).isEqualTo(2);
  }

  @Test
  public void leftShiftWithPlot_shouldAddPlotToFirstPosition() {
    Plot plot = new Plot();
    //when
    combinedPlot.add(new Plot(), 1);
    combinedPlot.leftShift(plot);
    //then
    Assertions.assertThat(combinedPlot.getSubplots().get(1)).isEqualTo(plot);
  }

  @Test
  public void leftShiftWithListParam_shouldAddPlotToFirstPosition() {
    Plot plot = new Plot();
    //when
    combinedPlot.add(new Plot(), 1);
    combinedPlot.leftShift(Arrays.asList(plot, 3));
    //then
    Assertions.assertThat(combinedPlot.getSubplots().get(1)).isEqualTo(plot);
  }

}
