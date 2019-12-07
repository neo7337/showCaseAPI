$(document).ready(function () {
    function viewData(){
        var data = {};
        $.ajax({
            type: 'GET',
            data: data,
            contentType: 'application/json',
            url: '/api/v1/fetchData',
            dataType: 'json',
            success: (result) => {
                console.log(result);
                $("#result").empty();
                var items2 = [];
                var returnMovies = JSON.stringify(result);
                var jsonData = JSON.parse(returnMovies);
                console.log('jsondata ' + JSON.stringify(jsonData));                   
                var lan = [],
                    count = [],
                    prev;
                for (var key in jsonData) {
                    console.log(key);
                    lan.push(key);
                    console.log(jsonData[key]);
                    count.push(jsonData[key]);
                }
                var countSum = 0;
                for (var h = 0; h < count.length; h++) {
                    countSum = countSum + count[h];
                }
                for (var g = 0; g < lan.length; g++) {
                    items2.push('<li class="list-group-item">' + lan[g] + '<span class="badge">' + Math.round((count[g] / countSum) * 100) + ' % </span></li>');
                }
                var perc = [];
                for (var t = 0; t < count.length; t++) {
                    perc[t] = Math.round((count[t] / countSum) * 100);
                }
                var plotData=[];
                for (var t = 0; t < count.length; t++) {
                    plotData[t] = (Math.round((count[t] / countSum) * 100))*2;
                }
                $('#result').append(items2.join(''));
                var data = [{
                    x : lan,
                    y : perc,
                    type : 'bar'
                }]; 
                Plotly.newPlot('res', data);

                /* var trace1 = {
                    x: lan,
                    y: perc,
                    mode: 'markers',
                    marker: {
                        size: plotData
                    }
                };

                var data = [trace1];
                var layout = {
                title: 'Marker Size',
                showlegend: false,
                height: 600,
                width: 600
                };

                Plotly.newPlot('res', data, layout); */
            }
        });
        setTimeout(viewData, 5000);
    }
    viewData();
});