<?php
class Form{

	private $legend;
	private $ctrls;

	public function __construct($text){
		$this->legend = $text;
	}
	public function pack(...$controls){
		$this->ctrls = $controls;
	}

	private function getHeader(){
		return '<h2>'.$this->legend.'</h2>';
	}

	public function display(){
		$out = $out = '<form method="post" action="handleLogin.php">'.$this->getHeader();
		foreach($this->ctrls as $ctrl){
			$ctrl = $ctrl->render();
			$out = $out.$ctrl;
		}
		$out = $out.'</form>';
		return $out;
	}

}
?>
