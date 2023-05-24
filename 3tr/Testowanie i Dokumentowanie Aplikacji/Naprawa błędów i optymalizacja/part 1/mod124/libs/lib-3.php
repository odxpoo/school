<?php
class Label{
	private $content;
	public function __construct($content){
		// przypisanie wartości do prywatniej zmiennej
		$this->content = $content;
	}
	public function render(){
		// $content -> $this->content
		// wyciągnięcie content ze stringa
		$out = "<label>".$this->content."</label>";
		return $out;
	}
}
?>
