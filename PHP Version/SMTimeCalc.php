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
	$main[$i]->getMe();
	$main[$i]->callClientPrint();
}


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
		$clients[] = new Client();
	}

	function getMe(){
		echo "Consultant:</br>";
	}

	function callClientPrint(){
		for ($i=0; $i < count($this->clients); $i++) { 
			$clients[$i]->printClient();
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
		// echo "adding client to consultant</br>";
	}

	function printClient(){
		echo "Client</br>";
	}
}

?>