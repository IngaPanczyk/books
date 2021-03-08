package com.kodilla.books.grafs;


import io.quickchart.QuickChart;
import org.springframework.stereotype.Component;

@Component
public class Graf {

    public String createGraf() {

        QuickChart chart = new QuickChart();
        chart.setWidth(500);
        chart.setHeight(300);
        chart.setConfig("{"
                + "    type: 'pie',"
                + "    data: {"
                + "        labels: ['Q1', 'Q2', 'Q3', 'Q4'],"
                + "        datasets: [{"
                + "            label: 'Users',"
                + "            data: [50, 60, 70, 180]"
                + "        }]"
                + "    }"
                + "}"
        );

        String url = chart.getUrl();

        System.out.println(chart.getUrl());
        return url;
    }
}
