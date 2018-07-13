<?php

$numConsultants = 0;

$consultants = array();

for ($i=0; $i < $numConsultants; $i++) { 
	array_push($main, new Consultant(4));
}

foreach ($consultants as $key => $value) {
	# code...
}

/**
 * 
 */
class Consultant
{

	$people = array();
	$hairCount = 0;
	$makeupCount = 0;
	$serviceTime = 0;
	$serviceCount = 0;

	function __construct($hairCountIn)
	{
		$hairCount = $hairCountIn;
	}
}


/**
 * 
 */
class Client
{

	$startTime;
	$endTime;
	$name = "";

	function __construct()
	{
		$startTime = null;
		$endTime = null;
	}

	function __construct($nameIn){
		$name = $nameIn;
	}

	function setStartTime($timeIn){
		$startTime = $timeIn;
	}

	function setEndTime($timeIn){
		$endTime = $timeIn;
	}

	function setName($nameIn){
		$name = $nameIn;
	}

	function getName(){
		return $name;
	}

}

?>