console.log("Koordynaty");
// wszystkie getElementsById -> getElementById, ponieważ bierzemy jeden element, nie zbiór elementów
let box = document.getElementById("moveMe");
// id zapisuje się jako string taki jaki występuje w htmlu bez dodatkowych znaków.
document.getElementById("move").addEventListener('click', ()=>{
	console.log("Moving");
	box.style.position="absolute";
	// brak jednostki w odległości
	box.style.top=150+"px";
	box.style.left=150+"px";
});
