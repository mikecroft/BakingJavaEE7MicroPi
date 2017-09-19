<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Payara Micro Stock Ticker</title>

    <script type="text/javascript" src="jquery-1.8.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            Highcharts.setOptions({
                global: {
                    useUTC: false
                }
            });

            var chart;
            document.chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'container',
                    defaultSeriesType: 'spline',
                    marginRight: 10,
                    plotBackgroundColor: '#FFFFFF',
                    plotShadow: true,
                    animation: {
                        duration: 200
                    }
                },
                title: {
                    text: 'Payara Stock Price: Server-Sent Events'
                },
                xAxis: {
                    type: 'datetime',
                    tickPixelInterval: 150
                },
                yAxis: {
                    title: {
                        text: 'Price ($)'
                    },
                    plotLines: [{
                            value: 0,
                            width: 2,
                            color: '#808080'
                        }]
                },
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + '</b><br/>' +
                                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>$' +
                                Highcharts.numberFormat(this.y, 2);
                    }
                },
                legend: {
                    enabled: true
                },
                exporting: {
                    enabled: false
                },
                series: [{
                        name: 'PAYARA',
                        data: (function () {
                            // generate an array of random data
                            var data = [],
                                    time = (new Date()).getTime(),
                                    i;

                            for (i = -19; i <= 0; i++) {
                                data.push({
                                    x: time + i * 1000,
                                    y: 0
                                });
                            }
                            return data;
                        })()
                    }]
            });
        });

    </script>
  </head>
  <body>
    <script type="text/javascript" src="js/highcharts.js"></script>
    <script type="text/javascript" src="js/modules/exporting.js"></script>

    <div id="container" style="width: 80%; height: 80%; margin: 0 auto"></div>

    <script type="text/javascript">

                var sseUri = "http://localhost:8081/StockTicker-1.0-SNAPSHOT/rest/sse";
                var source = new EventSource(sseUri);
                source.onmessage = function (event) {
                    onMessage(event)
                };

                console.log("CONNECT CALLED");

                function onMessage(event) {
                    var object = JSON.parse(event.data);
                    var x = (new Date()).getTime();
                    var y = object.price;
                    document.chart.series[0].addPoint([x, y], true, true, false);
                }
    </script>

  </body>
</html>
