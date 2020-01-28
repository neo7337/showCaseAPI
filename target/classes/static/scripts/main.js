var stop = false;
var start = false;
$(document).ready(function () {
    $('#res').jQCloud([], {
        classPattern: null,
        colors: ["#800026", "#bd0026", "#f0134d", "#0f4c75", "#fd8d3c", "#af460f", "#fa163f", "#111d5e", "#232020"],
        fontSize: {
            from: 0.1,
            to: 0.02
        },
        width: 1100,
        height: 350
    });
    $("#continueModal").modal();
    $("#ok-modal").click(function(){
        start = true;
        $("#continueModal").modal('hide');
        function viewData(){
            if(!stop){
                var data= {};
                $.ajax({
                    type: 'GET',
                    data: data,
                    contentType: 'application/json',
                    url: '/api/v1/fetchData',
                    dataType: 'json',
                    success: (result) => {
                        $("#result").empty();
                        var items2 = [];
                        var returnMovies = JSON.stringify(result);
                        var jsonData = JSON.parse(returnMovies);                 
                        var lan = [],
                            count = [],
                            prev;
                        for (var key in jsonData) {
                            lan.push(key);
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
                        var data = [];
                        for(var a=0; a<lan.length; a++){
                            var obj = {};
                            obj["text"]=lan[a];
                            obj["weight"]=perc[a];
                            data.push(obj);
                        }
                        $('#res').jQCloud('update', data);
                    }
                });
                setTimeout(viewData, 5000);
            }
        }
        if(start)
            viewData();
    });
});

$("#reset").click(function(){
    var data = {};
    $.ajax({
        type: 'GET',
        data: data,
        contentType: 'application/json',
        url: '/api/v1/reset',
        dataType: 'json',
        success: (result) => {
            var returnMovies = JSON.stringify(result);
            var jsonData = JSON.parse(returnMovies);
            console.log('jsondata ' + JSON.stringify(jsonData));
        }
    });
});

$("#save").click(function(){
    $("#alertModal").modal();
    $("#alertModal").find('.modal-body').text('Work in Progress!');
    $("#wip-ok-modal").click(function(){
        $("#alertModal").modal('hide');
    });
});

$("#closeEvent").click(function(){
    stop = true;
    start = false;
    $.notify("Event Closed!", "success");
})