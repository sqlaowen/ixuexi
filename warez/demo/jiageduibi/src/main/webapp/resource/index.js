$(function () {
    var url = getUrlParam('url');
    if (null == url) {
        //console.log('请求地址不正确...');
        return;
    }
    var msg = {};
    var xtitle = '';
    url = location.protocol + '//' + location.host + '/index' + location.search;
    $.ajax({
        url: url
        , type: 'GET'
        , data: {}
        , dataType: 'json'
        , async: false
        , success: function (rev) {
            //console.log(rev);
            if(null != rev.title) {
                xtitle = rev.title;
            }
            if(null != rev.data) {
                msg = JSON.parse(rev.data);
            }
        }
        , error: function (xhr, status, error) {
            //console.log('-----------error-----------')
            //console.log(xhr);
            //console.log(status);
            //console.log(error);
        }
    });

    if (typeof msg != 'object' || !msg.hasOwnProperty('startY')) {
        //console.log('没有查到数据...');
        $('#msg').text('SORRY,暂时没有收录'+location.search.replace(/^\?url=/,'')+'相关的商品,我们稍后提供');
        return;
    }

    var obj = {
        credits: {
            enabled: false
        },
        colors: [
            '#2f7ed8'
        ],
        chart: {
            renderTo: 'chart',
            type: 'line',
            spacingTop: 10,
            spacingBottom: 5,
            spacingLeft: 0,
            spacingRight: 20
        },
        title: {
            text: xtitle,
            style: {
                color: '#2f7ed8'
            }
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: {
                day: '%m-%d',
                week: '%m-%d',
                month: '%m-%d'
            },
            minorGridLineColor: '#dfdfdf',
            minorTickInterval: 'auto'
        },
        yAxis: {
            title: {
                text: '价格（元）'
            },
            minorGridLineColor: '#dfdfdf',
            minorTickInterval: 'auto'
        },
        tooltip: {
            xDateFormat: '%Y-%m-%d %H:%M:%S',
            shared: true
        },
        legend: {
            itemStyle: {
                paddingTop: 5,
                paddingLeft: 10,
                paddingRight: 10
            }
        },
        plotOptions: {
            series: {
                pointStart: Date.UTC(msg.startY, msg.startM, msg.startD),
                pointInterval: 86400000,
                marker: {
                    radius: 2
                },
                shadow: false
            }
        },
        series: []
    };
    if (msg.store.length > 0) {
        var len = msg.store.length;
        for (var i = 0; i < len; i++) {
            var store = msg.store[i];
            obj.series[i] = {
                visible: false,
                name: store.name,
                data: []
            };
            if (i == 0) {
                obj.series[i].visible = true;
            }
            if (store.data.length > 0) {
                var len2 = store.data.length;
                var datas = store.data;
                if (store.min_stamp * 1000 > parseInt(Date.UTC(msg.startY, msg.startM, msg.startD))) {
                    obj.series[i].data.push(null);
                }
                for (var j = 0; j < len2; j++) {
                    var arr = new Array(2);
                    arr[0] = parseInt(datas[j][0]);
                    arr[1] = parseFloat(datas[j][1]);
                    obj.series[i].data.push(arr);
                }
            }
        }
        console.log(obj);
        new Highcharts.Chart(obj);
    }
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURI(r[2]);
    }
    return null;
}
