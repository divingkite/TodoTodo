function executer(url,method,params,callbackFunc) {

    var http = new XMLHttpRequest();
    http.open(method, url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            callbackFunc(http.responseText);
        }
        else if(http.status != 200){
            document.body.innerHTML = http.responseText;
        }
    }
    http.send(params);
}

function getTodos() {

    var method = "GET";
    var url = "/TodoTodo/home" + "?&date=" + window.date;
    var params = null;

    executer(url,method,params,formatter);
}

function addNecessary(todo){
    var str="";
    str = str.concat("<div class=\"panel-heading\"><h4 class=\"panel-title todo-title\"><a data-toggle=\"collapse\" href=\"#qq");
    str = str.concat(todo["todoId"].toString());
    str = str.concat("\">");
    str = str.concat(todo["name"]);

    if(todo["status"]=="0"){
        str = str.concat("<input style=\"float:right;\" type=\"text\" id=\"assignedTo" + todo["todoId"].toString() + "\" placeholder=\"assign to\" required />");
    }
    if(todo["status"]=="0"){
        str = str.concat("<button onclick=\"addAssignee(");
        str = str.concat(todo["todoId"].toString() + ");\" style=\"float:right;\" type=\"button\" class=\"btn btn-default btn-sm\"><span class=\"glyphicon glyphicon-chevron-right\">");
        str = str.concat("</span>Assign</button>");
    }
    else if(todo["status"]=="1"){
        str = str.concat("<button onclick=\"changeStatus(");
        str = str.concat(todo["todoId"].toString() + ");\" style=\"float:right;\" type=\"button\" class=\"btn btn-default btn-sm\"><span class=\"glyphicon glyphicon-chevron-right\">");
        str = str.concat("</span>Completed</button>");
    }
    else if(todo["status"]=="2"){
        str = str.concat("<button onclick=\"changeStatus(");
        str = str.concat(todo["todoId"].toString() + ");\" style=\"float:right;\" type=\"button\" class=\"btn btn-default btn-sm\"><span class=\"glyphicon glyphicon-chevron-right\">");
        str = str.concat("</span>Delete</button>");
    }

    str = str.concat("</a>");
    str = str.concat("</h4></div><div id=\"qq");
    str = str.concat(todo["todoId"].toString());
    str = str.concat("\" class=\"panel-collapse collapse\"><div class=\"panel-body\"><div class=\"row\"><div class=\"author-name col-sm-4\">Created By:   ");
    str = str.concat(todo["creator"]);
    str = str.concat("</div><div class=\"date-created col-sm-4\">Created on: ");
    str = str.concat(todo["date"].toString() + "</div>");

    if(todo["status"]!="0"){
        str = str.concat("<div class=\"assignedTo col-sm-4\">Assigned to: ");
        str = str.concat(todo["assigned"] + "</div>");
    }
    str = str.concat("<div class=\"row\">");
    str = str.concat("<div class=\"description col-sm-12\">Description: ");
    str = str.concat(todo["description"]);
    str = str.concat("</div></div></div></div></div>");
    return str;
}

function formatter(data){

    var data = JSON.parse(data);
    window.date = data["date"];
    var todos = data["todos"];
    var unassigned="";
    var assigned="";
    var completed="";

    if(window.prevTodos === undefined ){
        window.prevTodos = [];
    }
    for(var i=0;i<todos.length;i++){
        var changed = false;

        for(var j=0;j<window.prevTodos.length;j++){
            if(todos[i]["todoId"] == window.prevTodos[j]["todoId"]){
                window.prevTodos[j] = todos[i];
                changed = true;
            }
        }

        if(!changed){
            window.prevTodos.push(todos[i]);
        }
    }

    for(var todonum in window.prevTodos){
        var todo = window.prevTodos[todonum];
        if( todo["status"] == "0" ){
            unassigned = unassigned.concat(addNecessary(todo));
        }
        else if(todo["status"] == "1"){
            assigned = assigned.concat(addNecessary(todo));
        }
        else if(todo["status"] == "2"){
            completed = completed.concat(addNecessary(todo));
        }
        else{   //now we will remove the deleted todos

        }
    }
    document.getElementById("completed").innerHTML = completed;
    document.getElementById("unassigned").innerHTML = unassigned;
    document.getElementById("assigned").innerHTML = assigned;
}

function addTask(){
    var method = "post";
    var title = document.getElementById("title").value;
    var description = document.getElementById("description").value;
    var params = "title=" + title + "&description=" + description;
    executer("/TodoTodo/addtask",method,params,getTodos);
    //document.getElementById("addTaskForm").style.display="none";
}
function addTaskFunc(){
}

getTodos();


function addAssignee(id){
    var method="post";
    var assignedTo = document.getElementById("assignedTo" + id).value;
    var params = "todoId=" + id + "&assignedTo=" + assignedTo + "&date=" + window.date;
    executer("/TodoTodo/assign",method,params,changeStatusfunc);
}
function changeStatus(id){
    var method="post";
    var status;
    for(var i=0;i<window.prevTodos.length;i++){
        if(window.prevTodos[i]["todoId"] === id){
            status = window.prevTodos[i]["status"];
        }
    }
    var params = "todoId=" + id + "&status=" + status + "&date=" + window.date;

    executer("/TodoTodo/transfer",method,params,changeStatusfunc);
}
function changeStatusfunc(){
    getTodos();
}