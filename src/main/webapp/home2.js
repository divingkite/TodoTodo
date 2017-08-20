var globalDate;
var prevTodos;
var constants = {
    UNASSIGNED : "UNASSIGNED",
    ASSIGNED : "ASSIGNED",
    COMPLETED : "COMPLETED",
    DELETED : "DELETED"
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
    var url = "/TodoTodo/home" + "?&date=" + globalDate;
    var params = null;

    executer(url,method,params,formatter);
}

function getPanelBody(todo){
    var panelBodyOuter = document.createElement("div");
    panelBodyOuter.setAttribute("id","body" + todo["todoId"].toString());
    panelBodyOuter.className += "panel-collapse collapse";

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


    if(todo["status"]!="UNASSIGNED"){
        var assignedTo = document.createElement("div");
        row.appendChild(assignedTo);
        assignedTo.appendChild(document.createTextNode("Assigned to: " + todo["assigned"]));
    }

    var descriptionRow = document.createElement("div");
    panelBody.appendChild(descriptionRow);
    descriptionRow.className += "row";

    var description = document.createElement("div");
    descriptionRow.appendChild(description);
    description.className += "description col-sm-12";
    description.appendChild(document.createTextNode("Description: " + todo["description"]));
    return panelBodyOuter;
}
function getPanelHeading(todo){

    var panelHeading = document.createElement("div");
    panelHeading.className += "panel-heading";
    panelHeading.setAttribute("id","head" + todo["todoId"].toString());

    var panelTitle = document.createElement("h4");
    panelHeading.appendChild(panelTitle);
    panelTitle.className += "panel-title todo-title";

    var todoTitle = document.createElement("a");
    panelTitle.appendChild(todoTitle);
    todoTitle.setAttribute("data-toggle", "collapse");
    todoTitle.setAttribute("href", "#body" + todo["todoId"].toString());
    todoTitle.appendChild(document.createTextNode(todo["name"]));

    var button = document.createElement("button");
    todoTitle.appendChild(button);

    button.setAttribute("type","button");
    button.style.float = "right";
    button.className += "btn btn-default btn-sm";

    var rightSign = document.createElement("span");
    button.appendChild(rightSign);
    rightSign.className += "glyphicon glyphicon-chevron-right";

    if(todo["status"] == constants.UNASSIGNED ){
        button.onclick = function(){ addAssignee(todo["todoId"]);}

        var assignInput = document.createElement("input");
        todoTitle.insertBefore(assignInput,button);
        assignInput.setAttribute("type","text");
        assignInput.setAttribute("id","assignedTo" + todo["todoId"]);
        assignInput.setAttribute("placeholder","assign to");
        assignInput.style.float = "right";
        assignInput.required = true;

        button.appendChild(document.createTextNode("Assign"));
    }
    else if(todo["status"]== constants.ASSIGNED ){
        button.onclick = function(){ changeStatus(todo["todoId"]);}
        button.appendChild(document.createTextNode("Completed"));
    }
    else if(todo["status"]== constants.COMPLETED ){
        button.onclick = function(){ changeStatus(todo["todoId"]);}
        button.appendChild(document.createTextNode("Delete"));
    }
    return panelHeading;
}

function removeElement(id,str){
    var el = document.getElementById(str + id.toString());
    if(el)
        el.parentNode.removeChild(el);
}

function addElement(todo){

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

function formatter(data){


    var data = JSON.parse(data);
    globalDate = data["date"];
    var todos = data["todos"];

    if( prevTodos === undefined ){
        prevTodos = {};
    }
    for(var i=0;i<todos.length;i++){
        var id = todos[i]["todoId"].toString();
        if(prevTodos[id] == undefined)
        {
            prevTodos[id] = todos[i];
            addElement(todos[i]);
        }
        else if(todos[i]["status"] == constants.DELETED ){
            delete prevTodos.id;
            removeElement(id,"head");
            removeElement(id,"body");
        }
        else if( todos[i]["status"] != prevTodos[id]["status"] )
        {
            delete prevTodos[id];
            removeElement(id,"head");
            removeElement(id,"body");

            prevTodos[id] = todos[i];
            addElement(todos[i]);
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

getTodos();

function addAssignee(id){
    var method="post";
    var assignedTo = document.getElementById("assignedTo" + id).value;
    var params = "todoId=" + id + "&assignedTo=" + assignedTo + "&date=" + globalDate;
    executer("/TodoTodo/assign",method,params,changeStatusfunc);
}
function changeStatus(id){
    var method="post";
    var status = prevTodos[id]["status"];
    var params = "todoId=" + id + "&status=" + status;

    executer("/TodoTodo/transfer",method,params,changeStatusfunc);
}
function changeStatusfunc(){
    getTodos();
}