<?php

$numConsultants = 3;

$makeupCount = 4;
$hairCount = 1;

$totalServices = floor(($makeupCount+$hairCount+2)/$numConsultants);

$main = array();

$brideDone = false;

for ($i=0; $i < $numConsultants; $i++) { 
	$main[] = new Consultant();
	$tempTotalService = $totalServices;
	while($tempTotalService > 0){
		if($brideDone == false){
			$main[$i]->addClient("Bride");
			$brideDone = true;
			$tempTotalService = $tempTotalService - 2;
		}else{
			if($tempTotalService > 0){
				$main[$i]->addClient("");	
			}
			
			$tempTotalService = $tempTotalService -1;
		}
	}
}

for($i=0; $i < count($main); $i++){
	// echo "cycling through consultants</br>";
	$int = $i +1;
	$inner = $main[$i]->callClientPrint();
	echo "<h2>Consultant".$int."</h2><div id=\"consultant\"><ul class=\"sortable\">" . $inner . "</ul></div>";
}

// var_dump($inner);

/**
 * 
 */
class Consultant
{

	private $clients;

	function __construct()
	{
		// echo "adding consultant</br>";
		$this->clients = array();
	}

	function addClient($nameIn){
		$this->clients[] = new Client($nameIn);
	}

	function callClientPrint(){
		$output = "";
		for ($i=0; $i < count($this->clients); $i++) { 
			$output .= $this->clients[$i]->printClient();
		}
		return $output;
	}

}

/**
 * 
 */
class Client
{
	
	private $name;

	function __construct($nameIn)
	{
		// echo "adding client to consultant</br>";
		if($nameIn == ""){
			$this->name = "service";
		}else{
			$this->name = $nameIn;	
		}
	}

	function printClient(){
		$output = "<li>" . $this->name . "</li>";
		return $output;
	}
}

?>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Sortable - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <style>
  .sortable{
    min-height: 50px;
  }
  #consultant{
    border-bottom: solid 2px black;
    width: 100%;
    min-height: 50px;
    background: grey;
  }
  </style>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
    $(function() {
        var oldList, newList, item;
        $('.sortable').sortable({
            start: function(event, ui) {
                item = ui.item;
                newList = oldList = ui.item.parent().parent();
            },
            stop: function(event, ui) {          
                // alert("Moved " + item.text() + " from " + oldList.attr('id') + " to " + newList.attr('id'));
            },
            change: function(event, ui) {  
                if(ui.sender) newList = ui.placeholder.parent().parent();
            },
            connectWith: ".sortable"
        }).disableSelection();
    });
  </script>
</head>
<body>
 


</body>
</html>