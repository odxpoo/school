console.log("Demo-5");
let arr = []; for (let i = 0; i < 20; i++) { arr.push(i * 5); }

let btn = document.querySelector('#clear');
btn.addEventListener('click', () => {
  console.log("Czyszczenie");
  let ol = lst.getElementsByTagName('LI');
  while (ol.length > 0) { arr.shift(); ol[0].remove(); }
  console.log(arr);
});

let lst = document.querySelector('#list');
for (let i = 0; i < arr.length; i++) { let li = document.createElement('li'); let txt = document.createTextNode(arr[i]); li.appendChild(txt);lst.appendChild(li);
}
