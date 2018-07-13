<?php

$numConsultants = 3;

$makeupCount = 1;
$hairCount = 1;

$totalServices = $makeupCount+$hairCount;

$main = array();

for ($i=0; $i < $numConsultants; $i++) { 
	$main[] = new Consultant();
	for ($j=0; $j < $totalServices; $j++) { 
		$main[$i]->addClient();
	}
}

for($i=0; $i < count($main); $i++){
	// echo "cycling through consultants</br>";
	echo "Consultant " . $i . ": </br>";
	$main[$i]->callClientPrint();
}

// var_dump($main);

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


	function addClient(){
		$this->clients[] = new Client();
	}

	function callClientPrint(){
		for ($i=0; $i < count($this->clients); $i++) { 
			$this->clients[$i]->printClient();
		}
	}

}

/**
 * 
 */
class Client
{
	
	function __construct()
	{
		echo "adding client to consultant</br>";
	}

	function printClient(){
		echo "Client</br>";
	}
}

?>