<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Salon Maison Schedule Maker</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <style>
  .sortable{
    min-height: 50px;
	list-style-type: none;
  }
  [id*="consultant_id_"]{
    border-bottom: solid 2px black;
    width: 100%;
    min-height: 50px;
    background: grey;
  }
  </style>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<button name="addConst" id="addConst" onclick="addConsultant()">Add Consultant</button>
	<button name="addBride" id="addBride" onclick="addService('Bride')">Add Bride</button>	
	<button name="addHair" id="addHair" onclick="addService('Hair')">Add Hair</button>
	<button name="addMakeup" id="addMakeup" onclick="addService('Makeup')">Add Makeup</button>
	<button name="export" id="export" onclick="">Export Schedule</button>	

	
	<div name="results" id="results">
		
	</div>
	<div id="test"></div>
</body>
  <script>
  // sortable variables
    var oldList, newList, item;
	// count for the consultants
	var count = 1;
	// start time
	var startTime = new Date("27 July 2016 07:00:00");
	
	// add a consultant to the results div/schedule
	function addConsultant(){
		// checks to see if at least one consultant has been added
		if(!$("#main").length){
			document.getElementById("results").innerHTML += "<h2>Consultant "+count+"</h2><span>Makeup:</span><input type='text' id='consultant_makeup_"+count+"' value='45' placeholder='Makeup Time'/><span>Hair:</span><input type='text' id='consultant_hair_"+count+"' value='45' placeholder='Hair Time'/><span>Bride:</span><input type='text' id='consultant_bride_"+count+"' value='120' placeholder='Bride Time'/><button onclick=\"updateTime('"+count+"')\">Update</button><div id='consultant_id_"+count+"' data-makeup-time='45' data-hair-time='45' data-bride-time='120'><ul class='sortable' id='main'></ul></div>";
		}else{
			document.getElementById("results").innerHTML += "<h2>Consultant "+count+"</h2><span>Makeup:</span><input type='text' id='consultant_makeup_"+count+"' value='45' placeholder='Makeup Time'/><span>Hair:</span><input type='text' id='consultant_hair_"+count+"' value='45' placeholder='Hair Time'/><span>Bride:</span><input type='text' id='consultant_bride_"+count+"' value='120' placeholder='Bride Time'/><button onclick=\"updateTime('"+count+"')\">Update</button><div id='consultant_id_"+count+"' data-makeup-time='45' data-hair-time='45' data-bride-time='120'><ul class='sortable'></ul></div>";
		}
		count++;
		// make new elements sortable
		newSortable();
	}
	
	// update the data tags for the consultants hair and makeup completion times
	function updateTime(id){
		var newHair = document.getElementById("consultant_hair_" + id).value;
		var newMakeup = document.getElementById("consultant_makeup_" + id).value;
		var newBride = document.getElementById("consultant_bride_" + id).value;
		document.getElementById("consultant_id_" + id).setAttribute("data-makeup-time", newMakeup);
		document.getElementById("consultant_id_" + id).setAttribute("data-hair-time", newHair);
		document.getElementById("consultant_id_" + id).setAttribute("data-bride-time", newBride);
		recountTime("consultant_id_" + id,'');
	}	
	
	// adds a service of a type to the first consultant, can be dragged to other consultants from there
	function addService(name){
		if($("#main").length){
			document.getElementById("main").innerHTML += "<li><span id='service_start'>7:00</span> - <span id='service_end'>7:30</span> "+name+"</li>";
			recountTime('consultant_id_1', '');
		}else{
			alert("You dont have at least 1 consultant to add a service to!");
		}
		
	}
	
	// recounts the time line for the consultant the service was pulled from and the consultant the service was given to
	function recountTime(fromId, toId){
		// if the from id is set
		if(fromId != ''){
			// get the time from start time
			var time = startTime;
			//get the consultants hair and makeup times
			var hairTime = document.getElementById(fromId).getAttribute("data-hair-time");
			var makeupTime = document.getElementById(fromId).getAttribute("data-makeup-time");
			var brideTime = document.getElementById(fromId).getAttribute("data-bride-time");
			
			// get the div from the consultant
			var div = document.getElementById(fromId);
			//get the ul from aka the List of services
			var lists = div.getElementsByTagName("ul");
			//loop through all ul (USeless???)
			for (var i = 0; i < lists.length; ++i) {
				//loop through services
		        var items = lists[i].getElementsByTagName("li");
		        for (var j = 0; j < items.length; ++j) {
					// get the two spans for start and end time in an array
					var spans = items[j].getElementsByTagName("span");
					// if the service is a hair
					if(items[j].innerHTML.includes("Hair")){
						// update start time
						spans[0].innerHTML = time.toLocaleTimeString();
						// update time
						time = new Date(time.getTime() + hairTime*60000);
						// update end time
						spans[1].innerHTML = time.toLocaleTimeString();
					}
					if(items[j].innerHTML.includes("Makeup")){
						// update start time
						spans[0].innerHTML = time.toLocaleTimeString();
						// update time
						time = new Date(time.getTime() + makeupTime*60000);
						// update end time
						spans[1].innerHTML = time.toLocaleTimeString();
					}
					if(items[j].innerHTML.includes("Bride")){
						// update start time
						spans[0].innerHTML = time.toLocaleTimeString();
						// update time
						time = new Date(time.getTime() + brideTime*60000);
						// update end time
						spans[1].innerHTML = time.toLocaleTimeString();
					}
		        }
			}
		// if the toId is set
		}if(toId != ''){
			var time = startTime;
			var hairTime = document.getElementById(toId).getAttribute("data-hair-time");
			var makeupTime = document.getElementById(toId).getAttribute("data-makeup-time");
			var brideTime = document.getElementById(fromId).getAttribute("data-bride-time");
			
			var div = document.getElementById(toId);
			var lists = div.getElementsByTagName("ul");
			for (var i = 0; i < lists.length; ++i) {
				
		        var items = lists[i].getElementsByTagName("li");
		        for (var j = 0; j < items.length; ++j) {
					
					var spans = items[j].getElementsByTagName("span");
					if(items[j].innerHTML.includes("Hair")){
						// update start time
						spans[0].innerHTML = time.toLocaleTimeString();
						// update time
						time = new Date(time.getTime() + hairTime*60000);
						// update end time
						spans[1].innerHTML = time.toLocaleTimeString();
					}
					if(items[j].innerHTML.includes("Makeup")){
						// update start time
						spans[0].innerHTML = time.toLocaleTimeString();
						// update time
						time = new Date(time.getTime() + makeupTime*60000);
						// update end time
						spans[1].innerHTML = time.toLocaleTimeString();
					}
					if(items[j].innerHTML.includes("Bride")){
						// update start time
						spans[0].innerHTML = time.toLocaleTimeString();
						// update time
						time = new Date(time.getTime() + brideTime*60000);
						// update end time
						spans[1].innerHTML = time.toLocaleTimeString();
					}
		        }
			}
		}
	}
	
	// calls sortable for any existing consultants on load
	newSortable();
	
	// function makes all elements in results as a sortable so the services can be dragged around
	function newSortable(){
        $('.sortable').sortable({
            start: function(event, ui) {
                item = ui.item;
                newList = oldList = ui.item.parent().parent();
            },
            stop: function(event, ui) {          
                 //alert("Moved " + item.text() + " from " + oldList.attr('id') + " to " + newList.attr('id'));
				 recountTime(oldList.attr('id'), newList.attr('id'));
            },
            change: function(event, ui) {  
                if(ui.sender) newList = ui.placeholder.parent().parent();
            },
            connectWith: ".sortable"
			}).disableSelection();
	}
		
	</script>
</html>