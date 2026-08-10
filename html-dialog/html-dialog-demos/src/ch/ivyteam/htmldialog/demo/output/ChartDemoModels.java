package ch.ivyteam.htmldialog.demo.output;

import software.xdev.chartjs.model.charts.MixedChart;
import software.xdev.chartjs.model.charts.PieChart;
import software.xdev.chartjs.model.data.MixedData;
import software.xdev.chartjs.model.data.PieData;
import software.xdev.chartjs.model.dataset.BarDataset;
import software.xdev.chartjs.model.dataset.LineDataset;
import software.xdev.chartjs.model.dataset.PieDataset;
import software.xdev.chartjs.model.options.BarOptions;
import software.xdev.chartjs.model.options.PieOptions;
import software.xdev.chartjs.model.options.Plugins;
import software.xdev.chartjs.model.options.scale.Scales;
import software.xdev.chartjs.model.options.scale.cartesian.AbstractCartesianScaleOptions.Title;
import software.xdev.chartjs.model.options.scale.cartesian.linear.LinearScaleOptions;

public final class ChartDemoModels {

  private ChartDemoModels() {
  }

  public static MixedChart createComboChart() {
    MixedChart chart = new MixedChart();
    chart.setType("bar");

    MixedData data = new MixedData();
    LineDataset temperature = new LineDataset();
    temperature.setType("line");
    temperature.setLabel("Temperature");
    temperature.setFill(false);
    temperature.setBorderColor("#afca05");
    temperature.setYAxisID("right-y-axis");
    temperature.setData(21, 25, 27, 23, 18, 14, 11);
    data.addDataset(temperature);

    BarDataset rainfall = new BarDataset();
    rainfall.setLabel("Rainfall");
    rainfall.setBackgroundColor("#7cb5ec");
    rainfall.setYAxisID("left-y-axis");
    rainfall.setData(176, 135, 150, 216, 194, 95, 75);
    data.addDataset(rainfall);
    data.setLabels("Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
    chart.setData(data);

    BarOptions options = new BarOptions();
    software.xdev.chartjs.model.options.Title title = new software.xdev.chartjs.model.options.Title();
    title.setDisplay(true);
    title.setText("Multi Axis Demo Chart");
    options.setPlugins(new Plugins().setTitle(title));

    Scales scales = new Scales();
    LinearScaleOptions leftAxis = new LinearScaleOptions();
    leftAxis.setPosition("left");
    leftAxis.setTitle(new Title().setText("Rainfall [mm]").setDisplay(true));
    scales.addScale("left-y-axis", leftAxis);

    LinearScaleOptions rightAxis = new LinearScaleOptions();
    rightAxis.setPosition("right");
    rightAxis.setTitle(new Title().setText("Temperature [C°]").setDisplay(true));
    scales.addScale("right-y-axis", rightAxis);
    options.setScales(scales);
    chart.setOptions(options);
    return chart;
  }

  public static PieChart createPieChart() {
    PieChart chart = new PieChart();
    PieData data = new PieData();
    PieDataset dataset = new PieDataset();
    dataset.setData(540, 325, 702, 421);
    dataset.addBackgroundColors("#ddffaa", "#b7f17c", "#8ae82d", "#52c60d");
    data.addDataset(dataset);
    data.setLabels("Brand 1", "Brand 2", "Brand 3", "Brand 4");
    chart.setData(data);

    PieOptions options = new PieOptions();
    software.xdev.chartjs.model.options.Title title = new software.xdev.chartjs.model.options.Title();
    title.setDisplay(true);
    title.setText("Pie Demo Chart");
    options.setPlugins(new Plugins().setTitle(title));
    chart.setOptions(options);
    return chart;
  }
}
