var constants = {
    UNASSIGNED : "0",
    ASSIGNED   : "1",
    COMPLETED  : "2"
}

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


// remove this as this is not readable and use java scropt
function getPanelBody(todo){
    var panelBodyOuter = document.createElement("div");
    panelBody.setAttribute("id","qq" + todo["todoId"].toString());
    panelBody.className += "panel-collapse collapse";

    var panelBody = document.createElement("div");
    panelBodyOuter.appendChild(panelBody);

    var row = document.createElement("div");
    panelBody.appendChild(row);
    row.className += "row";

    var creator = document.createElement("div");
    row.appendChild(creator);
    creator.className += "author-name col-sm-4";
    creator.appendChild(document.createTextNode("Created By:   " + todo["creator"]));

    var dateCreated = document.createElement("div");
    row.appendChild(dateCreated);
    dateCreated.className += "date-created col-sm-4";
    dateCreated.appendChild(document.createTextNode("Created on: " + todo["date"].toString()));


    if(todo["status"]!=constants.UNASSIGNED){
        var assignedTo = document.createElement("div");
        row.appendChild(row);
        assignedTo.appendChild(document.createTextNode("Assigned to: " + todo["assigned"]));
    }

    var descriptionRow = document.createElement("div");
    panelBody.appendChild(descriptionRow);
    descriptionRow.className += "row";

    var description = document.createElement("div");
    description.className += "description col-sm-12";
    description.appendChild(document.createTextNode("Description: " + todo["description"]));
    return panelBodyOuter;
}
function getPanelHeading(todo){

    var panelHeading = document.createElement("div");
    panelHeading.className += "panel-heading";

    var panelTitle = document.createElement("h4");
    panelHeading.appendChild(panelTitle);
    panelTitle.className += "panel-title todo-title";

    var todoTitle = document.createElement("a");
    panelTitle.appendChild(todoTitle);
    todoTitle.setAttribute("data-toggle", "collapse");
    todoTitle.setAttribute("href", "#qq" + todo["todoId"].toString());
    todoTitle.appendChild(document.createTextNode(todo["name"]));

    var button = document.createElement("button");
    todoTitle.appendChild(button);
    button.onclick = addAssignee(todo["todoId"].toString());
    button.setAttribute("type","button");
    button.style.float = "right";
    button.className += "btn btn-default btn-sm";

    var rightSign = document.createElement("span");
    button.appendChild(rightSign);
    rightSign.className += "glyphicon glyphicon-chevron-right";

    if(todo["status"]==constants.UNASSIGNED){
        var assignInput = document.createElement("input");
        todoTitle.insertBefore(assignInput,button);
        assignInput.setAttribute("type","text");
        assignInput.setAttribute("id","assignedTo" + todo["todoId"].toString());
        assignInput.setAttribute("placeholder","assign to");
        assignInput.style.float = "right";
        assignInput.required = true;

        button.createTextNode("Assign");
    }
    else if(todo["status"]== constants.ASSIGNED ){
        button.createTextNode("Completed");
    }
    else if(todo["status"]== constants.COMPLETED ){
        button.createTextNode("Delete");
    }
    return panelHeading;
}

function formatter(data){

    var data = JSON.parse(data);
    window.date = data["date"];
    var todos = data["todos"];

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
        if( todo["status"] == constants.UNASSIGNED ){
            document.getElementById("unassigned").appendChild(getPanelHeading(todo));
            document.getElementById("unassigned").appendChild(getPanelBody(todo));
        }
        else if(todo["status"] == constants.ASSIGNED ){
            document.getElementById("assigned").appendChild(getPanelHeading(todo));
            document.getElementById("assigned").appendChild(getPanelBody(todo));
        }
        else if(todo["status"] == constants.COMPLETED ){
            document.getElementById("completed").appendChild(getPanelHeading(todo));
            document.getElementById("completed").appendChild(getPanelBody(todo));
        }
    }
}

function addTask(){
    var method = "post";
    var title = document.getElementById("title").value;
    var description = document.getElementById("description").value;
    var params = "title=" + title + "&description=" + description;
    executer("/TodoTodo/addtask",method,params,getTodos);
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