(function (){
    var method = "GET";
    var url = "/TodoTodo/joinjs";
    var params = null;

    var http = new XMLHttpRequest();
    http.open(method, url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send(params);
})();