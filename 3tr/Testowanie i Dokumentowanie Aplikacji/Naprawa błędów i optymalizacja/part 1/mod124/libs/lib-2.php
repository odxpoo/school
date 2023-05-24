<?php
class Button{
	private $content;
	public function __construct($content){
		$this->content = $content;
	}
	public function render(){
		// $content -> content
		$out = '<button type="submit">'.$this->content.'</button>';
		return $out;
	}
}
?>
