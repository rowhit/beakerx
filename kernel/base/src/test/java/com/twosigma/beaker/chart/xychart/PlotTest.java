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

import com.twosigma.beaker.chart.xychart.plotitem.Area;
import com.twosigma.beaker.chart.xychart.plotitem.ConstantBand;
import com.twosigma.beaker.chart.xychart.plotitem.ConstantLine;
import com.twosigma.beaker.chart.xychart.plotitem.Line;
import com.twosigma.beaker.chart.xychart.plotitem.Rasters;
import com.twosigma.beaker.chart.xychart.plotitem.Text;
import com.twosigma.beaker.jupyter.KernelManager;
import com.twosigma.beaker.KernelTest;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class PlotTest {
  Plot plot;
  Line line;
  Area area;

  @Before
  public void initStubData() {
    KernelManager.register(new KernelTest());
    plot = new Plot();
    line = new Line();
    line.setX(Arrays.asList(1, 2, 3));
    line.setY(Arrays.asList(2, 3, 4));
    area = new Area();
    area.setX(Arrays.asList(1, 2, 3));
    area.setY(Arrays.asList(2, 3, 4));
  }

  @After
  public void tearDown() throws Exception {
    KernelManager.register(null);
  }

  @Test
  public void createPlotByEmptyConstructor_plotHasGraphicsListIsEmpty() {
    //when
    Plot plot = new Plot();
    //then
    Assertions.assertThat(plot.getGraphics().size()).isEqualTo(0);
  }

  @Test
  public void addLineToPlot_plotHasGraphicsListSizeIsOne() {
    //when
    plot.add(line);
    //then
    Assertions.assertThat(plot.getGraphics().size()).isEqualTo(1);
  }

  @Test
  public void addAreaToPlot_plotHasGraphicsListSizeIsOne() {
    //when
    plot.add(area);
    //then
    Assertions.assertThat(plot.getGraphics().size()).isEqualTo(1);
  }

  @Test
  public void addLineAndAreaToPlot_plotHasGraphicsListSizeIsTwo() {
    //when
    plot.add(line);
    plot.add(area);
    //then
    Assertions.assertThat(plot.getGraphics().size()).isEqualTo(2);
  }

  @Test
  public void leftShiftForRasters_plotHasRastersListSizeIsOne() {
    //when
    plot.add(new Rasters());
    //then
    Assertions.assertThat(plot.getRasters().size()).isEqualTo(1);
  }

  @Test
  public void addListOfPlotObjects_hasAllPlotObjects() {
    //when
    plot.add(Arrays.asList(
        line,
        new ConstantLine(),
        new ConstantBand(),
        new Rasters(),
        new Text()
    ));
    //then
    Assertions.assertThat(plot.getGraphics().size()).isEqualTo(1);
    Assertions.assertThat(plot.getConstantLines().size()).isEqualTo(1);
    Assertions.assertThat(plot.getConstantBands().size()).isEqualTo(1);
    Assertions.assertThat(plot.getRasters().size()).isEqualTo(1);
    Assertions.assertThat(plot.getTexts().size()).isEqualTo(1);
  }

  @Test
  public void setxAutoRangeByTrue_XAutoRangeIsTrue(){
    //when
    plot.setxAutoRange(true);
    //then
    Assertions.assertThat(plot.getXAutoRange()).isTrue();
  }

  @Test
  public void setxBoundByList_hasXLowerAndUpperBounds() throws Exception {
    //when
    plot.setxBound(Arrays.asList(1d, 10d));
    //then
    Assertions.assertThat(plot.getXLowerBound()).isEqualTo(1d);
    Assertions.assertThat(plot.getXUpperBound()).isEqualTo(10d);
  }
}
