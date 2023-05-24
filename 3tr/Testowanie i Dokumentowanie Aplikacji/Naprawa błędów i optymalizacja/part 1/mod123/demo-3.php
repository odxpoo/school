<?php
class FileSystem
{
	// zmienne zapisujemy bez typu i z '$' na początku nazwy
	public $dmsg = "";
	
	function construct($id){
		echo "ID instancji: ".$id;
	}
	// return poza funkcją
	public function get($dir, $file){
		$out = false;
		if( is_dir($dir) )
		{
			// $dir'/'.$file -> $dir.'/'.$file
			$this->dmsg = 'Katalog istnieje: '.$dir;
			if(file_exists($dir.'/'.$file)){
				$this->dmsg = $dir.'/'.$file;
				$out = true;
			} else
				$this->dmsg = $dir.'/'.$file;
		} else
			$this->dmsg = $dir;
	return $out;
	}
}

	// formatowanie 💀
	// jeżeli plik gdzieś jest to wyświetla, że istnieje jeżeli go nie ma to wyświetla, że nie istnieje.
	$fs = new FileSystem();

	# jedno z wielu wywołań wybranego pliku, który może się znajdować w katalogu A lub A/B
	if($fs->get('./media', 'dokument.html') || $fs->get('./media/assets', 'dokument.html')) {
		echo 'znaleziono plik';
	}
	else {
		echo 'nieznaleziono pliku';
	}
?>
